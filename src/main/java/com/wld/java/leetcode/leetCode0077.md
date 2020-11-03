## [77. 组合](https://leetcode-cn.com/problems/combinations/)（中等）

给定两个整数 *n* 和 *k*，返回 1 ... *n* 中所有可能的 *k* 个数的组合。

**示例:**

```
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

<br>

### 答案：

#### 1，回溯方式解决

这题要求的是从n个数字中选出k个弄成一组合，问最后总共有多少种组合，其实我们可以把它看做是一棵**n-k+1**叉树，比如n=4，k=2，那么就是一颗3叉树，这里以示例为例画个图看一下。

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0077/640.png)

注意**这里只能选择后面的数字不能选择前面的数字**，比如我选择了2，就能在选择1了，否则就会出现[1，2]和[2，1]这种重复的组合，同理我选择了3，那么1和2都不能再选了因为如果再选就会出现重复。

<br>

n叉树的遍历还记得吗

```java
private void backtrack() {
    if ("终止条件") {
        return;
    }

    for (int i = ?; i <= n - k + 1; i++) {
        //逻辑运算1，（可有可无，视情况而定）

        //递归调用，遍历每一个分支
        backtrack(list, n, k - 1, i + 1, tempList);

        //逻辑运算2，（可有可无，视情况而定）
    }
}
```

我们来看下上面的框架，逻辑运算1的时候，就表示沿着当前分支走下去，我们把当前选择的值添加到集合中。逻辑2表示这个分支走完了我们要跳到另一个分支，之前讲[426，什么是递归，通过这篇文章，让你彻底搞懂递归](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487910&idx=1&sn=2670aec7139c6b98e83ff66114ac1cf7&chksm=fb418286cc360b90741ed54fecd62fd45571b2caba3e41473a7ea0934f918d4b31537689c664&scene=21#wechat_redirect)的时候提到过，从一个分支跳到另一个分支的时候，要么之前分支在运算之前先复制一份，要么把当前分支添加的值给移除，否则会造成分支污染。这两种方式都可以，但后一种效率会更好，他不会大量的复制数据。那么终止条件是什么呢，就是集合中的数据大小为k的时候，就表示找到了一组组合。搞懂了这一点代码就比较容易写了

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> list = new LinkedList<>();
    backtrack(list, n, k, 1, new ArrayList<>());
    return list;
}

private void backtrack(List<List<Integer>> list, int n, int k, int start, List<Integer> tempList) {
    //终止条件，找到一组组合
    if (k == 0) {
        list.add(new LinkedList<>(tempList));
        return;
    }
    //注意这里的i不能从0开始，如果从0开始会出现重复的，比如[1，2]和[2，1]
    for (int i = start; i <= n - k + 1; i++) {
        //把当前值添加到集合中
        tempList.add(i);
        //递归调用
        backtrack(list, n, k - 1, i + 1, tempList);
        //从当前分支跳到下一个分支的时候要把之前添加的值给移除
        tempList.remove(tempList.size() - 1);
    }
}
```

<br>

#### 2，参考二进制位

我们知道二进制位中，每个位置有两种状态，一种是0一种是1，这里也可以参照位运算的表示方式，`每个数字都有选和不选两种状态`，具体画个图来看下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0077/641.png)

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> list = new ArrayList<>();
    backtrack(list, n, k, 1, new ArrayList<>());
    return list;
}

private void backtrack(List<List<Integer>> list, int n, int k, int start, List<Integer> tempList) {
    //终止条件，找到一对组合
    if (k == 0) {
        list.add(new ArrayList<>(tempList));
        return;
    }
    if (start <= n - k) {
        //不选当前值，k不变
        backtrack(list, n, k, start + 1, tempList);
    }
    //选择当前值，k要减1
    tempList.add(start);
    backtrack(list, n, k - 1, start + 1, tempList);
    //因为是递归调用，跳到下一个分支的时候，要把这个分支选的值给移除
    tempList.remove(tempList.size() - 1);
}
```

<br>

#### 3，递归方式解决

这题要求的是从n个数字中选出k个弄成一组合，问最后总共有多少种组合，如果用数学知识就是C(n,k)，这个公式又可以表示为C(n,k)=C(n-1,k-1)+C(n-1,k)，也就是说要么选第n个数字，要么不选第n个数字。

<br>

**（1），选第n个数字**

如果选第n个数字，我们需要从前面n-1个数字中选择k-1个，然后在和数字n组合

<br>

**（2），不选第n个数字**

如果不选第n个数字，我们可以直接从前面n-1个数字中选择k个即可

<br>

那么最终的结果就是上面两种的和，我们来直接看下代码

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    //边界条件判断
    if (n < k || k == 0)
        return res;
    //不选择第n个，从前面n-1个数字中选择k-1个构成一个集合
    res = combine(n - 1, k - 1);
    //如果res是空，添加一个空的集合
    if (res.isEmpty())
        res.add(new ArrayList<>());
    //然后在前面选择的集合res中的每个子集合的后面添加一个数字n
    for (List<Integer> list : res)
        list.add(n);
    //res中不光要包含选择第n个数字的集合，也要包含不选择第n
    //个数字的集合
    res.addAll(combine(n - 1, k));
    return res;
}
```

<br>

#### 4，总结

这题也不是特别难，但解题思路很多，每一种都比较经典。关于组合的题型也比较多，前面还讲过[391，回溯算法求组合问题](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487390&idx=1&sn=b7467c6109e9619e65ca5b2241a94406&chksm=fb419cbecc3615a82c2fc018918ddc748efcb19b13d7d898a42a17ae1faf05302f49b4c21e00&scene=21#wechat_redirect)，有兴趣的也可以看下



![](https://img-blog.csdnimg.cn/20200807155236311.png)

