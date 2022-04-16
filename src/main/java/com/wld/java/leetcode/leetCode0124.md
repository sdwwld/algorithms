## [124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)（困难）

给定一个**非空**二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径**至少包含一个**节点，且不一定经过根节点。

**示例 1:**

```
输入: [1,2,3]

       1
      / \
     2   3

输出: 6
```

**示例 2:**

```
输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42
```

<br/>

### 答案：

这道题要求的最大路径和如果是从根节点开始到叶子节点就好办了，我们可以通过递归的方式，从下往上，舍去比较小的路径节点，保留比较大的节点。



但这道题要求的最大路径和并不一定经过根节点，如果再使用上面的方式就行不通了，对于这道题我们可以分为4种情况来讨论



**1，只要当前节点，舍弃子节点**。比如下面结点2的左右子节点都是负数，如果是负数我们还不如不要，所以直接舍弃子节点。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw5KzYuVGGsSly2pV4jQV3KVtQv2QvqVCB1cotC2p5QjTvnvoWZn9F5IA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

**2，保留当前节点和左子节点**。比如下面结点2的右子节点是负数，我们直接舍弃右子节点，但左子节点不是负数，我们可以保留左子节点。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw5cF3KsExgD9Ivqx6ltqeHSSFGic7LIbmX9VKAyRloP39lRXLyp7jM4bw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

**3，保留当前节点和右子节点**。比如下面结点2的左子节点是负数，我们直接舍弃左子节点，但右子节点不是负数，我们可以保留右子节点。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw59icdz63jkaoWvcia8IiaOFRFPCTibCmicobiazV1pIW7UHrAicZmicick4hdEZg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

**4，保留当前节点和两个子节点**。比如下面结点2的左右子节点都不是负数，我们都可以留下。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw5JdwbgnYwGkiclXiaq4QboIjYPfMVckHCX3xq5Ig6IPCbLWOmN2d0vrxw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上面的1，2，3都可以作为子树的一部分再继续计算，我们可以使用同一个公式，取左右子节点最大的那个即可，如果都小于0我们不要了，下面公式中left是左子树的值，right是右子树的值

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw5BtFvfF4DFWaiaMnHTlkjyAqNb6LY9ZRTEFQAd0KUXn9dcru8hbeOTBg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



而4是不能在作为子树的一部分参与计算的，因为已经分叉了，比如下面的3→2→4是不能再和结点1进行组合的。第4种情况如果左右子树有一个是小于0的我们还不如不选，如果都大于0我们都要选的。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw5AOktcPicjI9YbZEbyEjECV3aVoT5OkHp8T39IPCjD3xQulIKPwtNEOQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBH8ia3xUiclpDfQoIwXmy8kw5b9x2YTb2gQ4061kQ4tPYmibyAibuAKJykp4iaicXBySpefnmM5C2g8TCWw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



搞懂了上面的分析过程，代码就很容易写出来了，我们最后来看下代码

```java
private int maxValue = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
    maxPathSumHelper(root);
    return maxValue;
}

public int maxPathSumHelper(TreeNode root) {
    if (root == null)
        return 0;
    //左子节点的值
    int left = maxPathSumHelper(root.left);
    //右子节点的值
    int right = maxPathSumHelper(root.right);
    //第4种情况
    int cur = root.val + Math.max(0, left) + Math.max(0, right);
    //第1,2,3三种情况,返回当前值加上左右子节点的最大值即可，如果左右子节点都
    //小于0，还不如不选
    int res = root.val + Math.max(0, Math.max(left, right));
    //记录最大value值
    maxValue = Math.max(maxValue, Math.max(cur, res));
    //第1,2,3种情况还可以再计算，所以返回的是res
    return res;
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 