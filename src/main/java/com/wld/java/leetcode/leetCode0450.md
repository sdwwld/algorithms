## [450. 删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)（中等）

给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

1. 首先找到需要删除的节点；
2. 如果找到了，删除它。

**说明：** 要求算法时间复杂度为 O(h)，h 为树的高度。

**示例:**

```
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7
```



<br/>

### 答案：

#### 1，问题分析

二叉搜索树的特点是左子树的值都比他小，右子树的值都比他大，删除一个节点之后我们还要保证二叉搜索树的这个特点不变。如果要删除一个结点，我们先要找到这个节点，然后才能删除，但这里要分几种情况。

- 如果要删除的节点是叶子节点，我们直接删除即可。
- 如果删除的结点不是叶子节点，并且有一个子节点为空，我们直接返回另一个不为空的子节点即可。
- 如果删除的结点不是叶子节点，并且左右子树都不为空，我们可以用左子树的最大值替换掉要删除的节点或者用右子树的最小值替换掉要删除的节点都是可以的。

<br>

这里使用递归的方式是最容易理解的，我们来看下代码

```java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null)
        return null;
    //通过递归的方式要先找到要删除的结点
    if (key < root.val) {
        //要删除的节点在左子树上
        root.left = deleteNode(root.left, key);
    } else if (key > root.val) {
        //要删除的节点在右子树上
        root.right = deleteNode(root.right, key);
    } else {
        //找到了要删除的节点。
        //如果左子树为空，我们只需要返回右子树即可
        if (root.left == null)
            return root.right;
        //如果右子树为空，我们只需要返回左子树即可
        if (root.right == null)
            return root.left;
        //说明两个子节点都不为空，我们可以找左子树的最大值，
        //也可以找右子树的最小值替换

        //这里是用右子树的最小值替换
        //TreeNode minNode = findMin(root.right);
        //root.val = minNode.val;
        //root.right = deleteNode(root.right, root.val);

        //这里是用左子树的最大值替换
        TreeNode maxNode = findMax(root.left);
        root.val = maxNode.val;
        root.left = deleteNode(root.left, root.val);
    }
    return root;
}

//    找右子树的最小值
//    private TreeNode findMin(TreeNode node) {
//        while (node.left != null)
//            node = node.left;
//        return node;
//    }

//找左子树的最大值
private TreeNode findMax(TreeNode node) {
    while (node.right != null)
        node = node.right;
    return node;
}
```

上面节点删除的时候我们使用左子树的最大值或者右子树的最小值替换都是可以的。其实我们还可以改一下，如果要删除结点左右子树只要有一个为空，我们就返回另一棵子树，如果都不为空，我们可以让左子树成为右子树最小结点的左子树或者让右子树成为左子树最大结点的右子树，我们来看下代码。

```java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null)
        return null;
    if (root.val > key) {
        //要删除的节点在左子树上
        root.left = deleteNode(root.left, key);
    } else if (root.val < key) {
        //要删除的节点在右子树上
        root.right = deleteNode(root.right, key);
    } else {
        //找到要删除的结点之后
        if (root.left == null)
            return root.right;
        if (root.right == null)
            return root.left;

        /*
        //左右子树都不为空，找到要删除结点右子树的最小值
        TreeNode rightSmallest = root.right;
        while (rightSmallest.left != null)
            rightSmallest = rightSmallest.left;
        //这个最小值对应的节点一定是没有左子树的，
        // 如果有他肯定不是最小的，然后让删除结点的
        //左子树成为这个最小值的左子树
        rightSmallest.left = root.left;
        //直接返回要删除结点的右子树
        return root.right;
         */

        //左右子树都不为空，找到要删除结点左子树的最大值
        TreeNode leftBig = root.left;
        while (leftBig.right != null)
            leftBig = leftBig.right;
        //这个最大值对应的节点一定是没有右子树的，
        // 如果有他肯定不是最大的，然后让删除结点的
        //右子树成为这个最大值的右子树
        leftBig.right = root.right;
        //直接返回要删除结点的左子树
        return root.left;
    }
    return root;
}
```

<br>

#### 2，总结

删除结点的时候并不一定要直接删除，在之前讲[378，数据结构-7,堆](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487143&idx=1&sn=293a762267e087032c8d6a6f5bdd8d13&chksm=fb419d87cc361491db965c4db6e6f3c187fe63e4aa80e03f6baf2716ed4f983b4d687dab747d&scene=21#wechat_redirect)的时候删除结点直接使用其他节点来替换掉要删除的结点即可。这里也是使用同样的方式，对于二叉搜索树节点的删除，我们可以用它左子树的最大值或者右子树的最小值来替换，如果没有左子树或者右子树那就更方便了。

<br>



 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
