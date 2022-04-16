## [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)（简单）

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

给定二叉树 `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最小深度  2.

<br/>

### 答案：

#### 1，非递归写法

这题其实不难，看到这道题我们首先想到的是BFS，就是一层一层的遍历，如果某一层的某个节点没有子节点了，我们就返回这个节点的层数即可。



比如上面的9在第二层，他没有子节点了，我们直接返回他所在的层数2即可，没必要在遍历第3层了。代码很简单，我们来看下。

```java
public int minDepth(TreeNode root) {
    if (root == null)
        return 0;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);//入队
    int level = 0;
    while (!queue.isEmpty()) {//队列不为空就继续循环
        level++;
        int levelCount = queue.size();
        for (int j = 0; j < levelCount; j++) {
            TreeNode node = queue.poll();//出队
            //如果当前node节点的左右子树都为空，直接返回level即可
            if (node.left == null && node.right == null)
                return level;
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }
    return -1;
}
```

<br/>

#### 2，递归写法

我们还可以使用递归的方式，返回**Math.min(左子树的深度，右子树的深度)+1**，看起来很有道理，但有一个问题，如果左右子树都不为空或者都为空是没问题的。但如果左右子树一个为空一个不为空，就会有问题了，因为为空的那个子节点的深度是0，我们不能用它，所以这里要有个判断。



比如下面7的左子树的深度是0，但他还有右子树，所以我们不能选择深度最小的（因为这时7的左子树的深度是0）。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFibmrlslPGmUjkbcVgxEqqicRVfCZg4QSQ8d1ln4V7TI2r36jLdiaSFFJMwoiavHlvdMwJKjibkM00jSg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public int minDepth(TreeNode root) {
    if (root == null)
        return 0;
    //左子树的最小深度
    int left = minDepth(root.left);
    //右子树的最小深度
    int right = minDepth(root.right);
    //如果left和right都为0，我们返回1即可，
    //如果left和right只有一个为0，说明他只有一个子结点，我们只需要返回它另一个子节点的最小深度+1即可。
    //如果left和right都不为0，说明他有两个子节点，我们只需要返回最小深度的+1即可。
    return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
}
```

**或者我们还可以换种方式**

```java
public static int minDepth(TreeNode root) {
    if (root == null)
        return 0;
    //如果左子树等于空，我们返回右子树的最小高度+1
    if (root.left == null)
        return minDepth(root.right) + 1;
    //如果右子树等于空，我们返回左子树的最小高度+1
    if (root.right == null)
        return minDepth(root.left) + 1;
    //如果左右子树都不为空，我们返回左右子树深度最小的那个+1
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
}
```







![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 