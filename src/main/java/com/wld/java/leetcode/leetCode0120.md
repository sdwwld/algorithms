## [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)（中等）

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

<br>

例如，给定三角形：

```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```

自顶向下的最小路径和为 `11`（即，**2** + **3** + **5** + **1** = 11）。

<br>

**说明：**

如果你可以只使用 *O*(*n*) 的额外空间（*n* 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

<br>

### 答案：

#### 1，递归求解

这题让求路径的最小值，如果知道了下面路径的最小值，只需要选择最小的即可，描述不是很明白，这里以示例为例画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0120/640.png)

对于上面的元素他们的最小路径和都要依赖下面的元素，但最后一行的最小路径和就是他自己了，所以看到这题我们很容易想到的一种解决方式就是递归，来看下，代码中有详细注释。

```java
public int minimumTotal(List<List<Integer>> triangle) {
    return minimumTotal(triangle, 0, 0, triangle.size());
}

//line和row分别表示行和列，total表示总共多少行
public int minimumTotal(List<List<Integer>> triangle, int line, int row, int total) {
    //如果行或者列大于total，说明跑到三角形的外面去了，直接返回0。
    if (line >= total || row >= total)
        return 0;
    //left表示下一行左边的最小路径和
    int left = minimumTotal(triangle, line + 1, row, total);
    //left表示下一行右边的最小路径和
    int right = minimumTotal(triangle, line + 1, row + 1, total);
    //返回当前值加上下一行中左右两个最小的路径
    return triangle.get(line).get(row) + (left < right ? left : right);
}
```

这种解法虽然没有逻辑上的错误，但是执行效率很差，因为他包含大量的重复计算，有点类似斐波那契数量一样，在之前讲过[356，青蛙跳台阶相关问题](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486347&idx=1&sn=751a890c3eb9eb99924f1ebd41034a3a&chksm=fb4198abcc3611bdb7a943ef6fff7a54908583e001032d0ee02ad90e9e91b9f63054b88d8c7c&scene=21#wechat_redirect)的时候提到过斐波那契数列可以使用map把计算的结果存储起来，下次用的时候如果map中有就直接从map中取，如果map中没有再计算。



所以这题也可以使用一个map，把计算的结果存储起来，来看下代码

```java
public int minimumTotal(List<List<Integer>> triangle) {
    return minimumTotal(triangle, 0, 0, triangle.size(), new HashMap());
}

public int minimumTotal(List<List<Integer>> triangle, int line, int row, int total, Map<String, Integer> map) {
    //如果行或者列大于total，说明跑到三角形的外面去了，直接返回0。
    if (line >= total || row >= total)
        return 0;
    String key = line + "-" + row;
    if (map.containsKey(key))
        return map.get(key);
    int left = minimumTotal(triangle, line + 1, row, total, map);
    int right = minimumTotal(triangle, line + 1, row + 1, total, map);
    int sum = triangle.get(line).get(row) + (left < right ? left : right);
    //把计算的结果存储到map中
    map.put(key, sum);
    return sum;
}
```

<br>

#### 2，动态规划解决

递归使用的是从上到下的方式来计算（但实际上计算的时候由于递归的原因，他还是先从下面开始计算，然后把计算的结果返回给上一层调用递归的地方）。其实还可以不使用递归，倒数第一行的最小路径就是他自己，从倒数第二行开始，**当前元素的最小路径就是当前元素加上他下一行左右两个元素的最小路径**。画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0120/641.png)

我们来定义一个二维数组dp，他表示当前位置的最小路径和，我们可以得出递推公式

```java
dp[i][j]=min(dp[i+1][j]+dp[i+1][j+1])+triangle.get(i).get(j)；
```

他表示**当前位置的最小路径和是下一行左右两个最小的路径和加上当前的值**。最后再来看下代码

```java
public int minimumTotal(List<List<Integer>> triangle) {
    //定义一个二维数组
    int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
    //从最后一行开始计算
    for (int i = triangle.size() - 1; i >= 0; i--) {
        for (int j = 0; j < triangle.get(i).size(); j++) {
            //递归公式
            dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
        }
    }
    return dp[0][0];
}
```

上面代码使用的是二维数组，每一行使用之后就不会再使用了，造成了空间的浪费，其实我们还可以把它改成一维的，就像之前讲的[370，最长公共子串和子序列](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486892&idx=1&sn=4d4c122bf5139ba711b53e9ffd208408&chksm=fb419e8ccc36179a7518796a1339d348ef7786b89c8cc62ec26e9f5bc1c3ec5eb6e68a44e84d&scene=21#wechat_redirect)中提到的代码优化那样，最后再来看下代码

```java
public int minimumTotal(List<List<Integer>> triangle) {
    int[] dp = new int[triangle.size() + 1];
    for (int i = triangle.size() - 1; i >= 0; i--) {
        for (int j = 0; j < triangle.get(i).size(); j++) {
            //min函数中的dp[j]实际上是下一行的值这里还没有更新
            dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
        }
    }
    return dp[0];
}
```

<br>

#### 3，总结

三角形的最小路径和也是很常见的一道题，使用动态规划从下往上递推应该说效率是最高的，也很容易理解。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

