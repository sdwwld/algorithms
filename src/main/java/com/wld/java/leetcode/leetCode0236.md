## [236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)（中等）

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png)

<br/>

**示例 1:**

```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
```

**示例 2:**

```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```

<br/>

**说明:**

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉树中。

<br/>

### 答案：

#### 1，非递归写法

要想找到两个节点的最近公共祖先节点，我们可以从两个节点**往上找**，每个节点都往上走，一直走到**根节点**，那么根节点到这两个节点的连线肯定有**相交** 的地方，如果是从上往下走，那么最后一次相交的节点就是他们的最近公共祖先节点。我们就以找6和7的最近公共节点来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEar03fibBgbZBrmmUvKBg7eay0CDHGJ4xkjws58O3SadsUibt3gQaRjSxZiawuPdibP0MftPXwaVf1aw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

我们看到6和7公共祖先有5和3，但最近的是5。我们只要往上找，找到他们第一个相同的公共祖先节点即可，但怎么找到每个节点的父节点呢，我们只需要把每个节点都遍历一遍，然后顺便记录他们的父节点存储在Map中。我们先找到其中的一条路径，比如6→5→3，然后在另一个节点往上找，由于7不在那条路径上，我们找7的父节点是2，2也不在那条路径上，我们接着往上找，2的父节点是5，5在那条路径上，所以5就是他们的最近公共子节点。



其实这里我们可以优化一下，**我们没必要遍历所有的结点**，我们一层一层的遍历（也就是BFS），只需要这两个节点都遍历到就可以了，比如上面2和8的公共结点，我们只需要遍历到第3层，把2和8都遍历到就行了，没必要再遍历第4层了。



我们来看下代码

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //记录遍历到的每个节点的父节点。
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    parent.put(root, null);//根节点没有父节点，所以为空
    queue.add(root);
    //直到两个节点都找到为止。
    while (!parent.containsKey(p) || !parent.containsKey(q)) {
        //队列是一边进一边出，这里poll方法是出队，
        TreeNode node = queue.poll();
        if (node.left != null) {
            //左子节点不为空，记录下他的父节点
            parent.put(node.left, node);
            //左子节点不为空，把它加入到队列中
            queue.add(node.left);
        }
        //右节点同上
        if (node.right != null) {
            parent.put(node.right, node);
            queue.add(node.right);
        }
    }
    Set<TreeNode> ancestors = new HashSet<>();
    //记录下p和他的祖先节点，从p节点开始一直到根节点。
    while (p != null) {
        ancestors.add(p);
        p = parent.get(p);
    }
    //查看p和他的祖先节点是否包含q节点，如果不包含再看是否包含q的父节点……
    while (!ancestors.contains(q))
        q = parent.get(q);
    return q;
}
```

<br/>

#### 2，递归写法

这题我们还可以改一下，使用递归的写法，代码中有注释，就不在详细介绍。

```java
public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
    if (cur == null || cur == p || cur == q)
        return cur;
    TreeNode left = lowestCommonAncestor(cur.left, p, q);
    TreeNode right = lowestCommonAncestor(cur.right, p, q);
    //如果left为空，说明这两个节点在cur结点的右子树上，我们只需要返回右子树查找的结果即可
    if (left == null)
        return right;
    //同上
    if (right == null)
        return left;
    //如果left和right都不为空，说明这两个节点一个在cur的左子树上一个在cur的右子树上，
    //我们只需要返回cur结点即可。
    return cur;
}
```

这道题如果一开始就知道每个节点的父节点就更简单了，从每个节点到根节点我们都可以把它看成是一个链表，如果求两个节点的最近公共祖先节点，我们只需要找到这两个链表第一次的交点即可，所以这个时候又是另外一道算法题了。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（235. 二叉搜索树的最近公共祖先）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0235.md)

#### [下一题（237. 删除链表中的节点）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0237.md)