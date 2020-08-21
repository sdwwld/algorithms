## [63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)（中等）

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)

<center><font size=2>网格中的障碍物和空位置分别用 1 和 0 来表示。</font></center>

**说明：** m* 和 *n* 的值均不超过 100。

**示例 1:**

```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```

<br/>

### 答案：

#### 1，动态规划解决

上一篇我们讲过和这非常类似的一道题，只不过上一篇没有障碍物，但并不影响我们解这道题，我们还用dp[i][j]表示到坐标(i，j)这个格内有多少条不同的路径，所以最终的答案就是求dp\[m-1][n-1]。



这里的递推分为两种情况，一种是当前网格没有障碍物，一种是当前网格有障碍物。



1，如果当前网格dp[i][j]有障碍物，那么这里肯定是走不过去的，所以dp[i][j]=0。



2，如果当前网格dp[i][j]没有障碍物，那么递推公式就和上一题[409，动态规划求不同路径](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487666&idx=1&sn=348938a0e110abdc081a07572a206561&chksm=fb418392cc360a84df035e84b8c08c6c38eeb603809cd13c373fe6840097054b798c93f94a6a&scene=21#wechat_redirect)一样了。

因为只能从上面或左边走过来，所以递推公式是

**dp\[i][j]=dp\[i-1][j]+dp\[i][j-1]。**

- **dp\[i-1][j]** 表示的是从上面走过来的路径条数。
- **dp\[i][j-1]** 表示的是从左边走过来的路径条数。



边界条件也好判断，如果当前行没有障碍物，那么当前行的值都是1，如果有障碍物，那么第一个障碍物前面都是1，其他的都是0。同理第一列也一样。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHiciapMSpptISdz8WaaticZEdSusG3D0wFSibE4T5tVjyUJUQGZJcpyjTaOfmTa2RGJYzNldcT0Iwd2g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

我们来看下代码

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int dp[][] = new int[m][n];
    //第一列初始化
    for (int i = 0; i < m; i++) {
        if (obstacleGrid[i][0] == 0)
            dp[i][0] = 1;
        else
            break;
    }
    //第一行初始化
    for (int i = 0; i < n; i++) {
        if (obstacleGrid[0][i] == 0)
            dp[0][i] = 1;
        else
            break;
    }
    for (int i = 1; i < m; ++i)
        for (int j = 1; j < n; ++j)
            if (obstacleGrid[i][j] == 0)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    return dp[m - 1][n - 1];
}
```

代码和[409，动态规划求不同路径](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487666&idx=1&sn=348938a0e110abdc081a07572a206561&chksm=fb418392cc360a84df035e84b8c08c6c38eeb603809cd13c373fe6840097054b798c93f94a6a&scene=21#wechat_redirect)差不多，只不过在第一行和第一列还有上面第21行多了一些判断。

<br/>

#### 2，动态规划代码量优化

上面代码虽然也能实现，但有那么多条件判断总感觉很繁琐，所以我们还有一种方式就是把二维数组的长和宽都放大一格，这样数组的第一行和第一列都不存储任何值，但初始条件要变了

- dp\[1][1] = obstacleGrid\[0][0] ^ 1;
- dp\[0][1] = 1;
- dp\[1][0] = 1;

上面3种初始条件都可以，我们来任选一个，看下代码

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int dp[][] = new int[m + 1][n + 1];
    //初始条件，下面3个任选一个
    //dp[1][1] = obstacleGrid[0][0] ^ 1;
    //dp[0][1] = 1;
    dp[1][0] = 1;
    for (int i = 1; i <= m; ++i)
        for (int j = 1; j <= n; ++j)
            if (obstacleGrid[i - 1][j - 1] == 0)
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
    return dp[m][n];
}
```

<br/>

#### 3，动态规划空间优化

我们可以参照上一题把二维空间改为一维的，原理很简单，我们来直接看代码

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[] dp = new int[n + 1];
    dp[1] = 1;
    for (int i = 0; i < m; i++) {
        for (int j = 1; j <= n; j++) {
            if (obstacleGrid[i][j - 1] == 1) {
                dp[j] = 0;//有障碍物
            } else {//无障碍物
                dp[j] += dp[j - 1];
            }
        }
    }
    return dp[n];
}
```

上一题有人问过一个问题说看不懂第11行，这里再说一下，因为是一行一行的遍历，在当前行遍历之前dp（这里是一维数组）表示的是上一行的值，然后遍历到当前行的时候，假如遍历当前行的第j列的时候，那么当前行第j列之前的数据都会被更新掉，当前行第j列之后的数据还是上一行的，所以dp[j]=**dp[j]**+dp[j-1]（为了区分，这里标成了不同的颜色），**dp[j]**表示的是当前列的上一行值，dp[j-1]表示的是当前行的前一个值。

<br/>

#### 4，递归方式

上一题我们提到过，使用递归的方式会造成大量的重复计算，所以为了减少重复计算，这里使用一个map把计算过的值存储起来，下次用的时候先从map中取，如果有就返回，如果没有再计算。

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    return helper(obstacleGrid, 0, 0, new HashMap<String, Integer>());
}

public static int helper(int[][] obstacleGrid, int down, int right, Map<String, Integer> map) {
    String key = down + "and" + right;
    int result = 0;
    if (map.containsKey(key))
        return map.get(key);
    if (obstacleGrid[down][right] == 1) {
        result = 0;
        map.put(key, result);
        return result;
    }
    if (right == obstacleGrid[0].length - 1 && down == obstacleGrid.length - 1) {
        if (obstacleGrid[down][right] == 1) {
            result = 0;
        } else {
            result = 1;
        }
        map.put(key, result);
        return result;
    }
    if (right == obstacleGrid[0].length - 1 || down == obstacleGrid.length - 1) {
        if (right == obstacleGrid[0].length - 1) {
            result = helper(obstacleGrid, down + 1, right, map);
        } else {
            result = helper(obstacleGrid, down, right + 1, map);
        }
        map.put(key, result);
        return result;
    }
    result = helper(obstacleGrid, down, right + 1, map) + helper(obstacleGrid, down + 1, right, map);
    map.put(key, result);
    return result;
}
```

这种不看也可以，因为动态规划非常简单，没人会傻到会使用这种方式，但他也算是提供了一种思路，有时间看看也行。

<br/>

#### 5，总结

这题多了一个障碍物的判断，但难度其实并没有增加多少，如果当前位置出现了障碍物，说明不能从当前位置通过，所以当前位置的路径是0，如果当前位置不是0，那么计算就还和以前一样了。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（62. 不同路径）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0062.md)

#### [下一题（64. 最小路径和）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0064.md)