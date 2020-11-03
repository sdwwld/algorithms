## [701. 二叉搜索树中的插入操作](https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/)（简单）

给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

**注意，** 可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 **任意有效的结果** 。

 <br/>

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg)



```
输入：root = [4,2,7,1,3], val = 5
输出：[4,2,7,1,3,5]
解释：另一个满足题目要求可以通过的树是：
```

![img](https://assets.leetcode.com/uploads/2020/10/05/bst.jpg)

**示例 2：**

```
输入：root = [40,20,60,10,30,50,70], val = 25
输出：[40,20,60,10,30,50,70,null,null,25]
```

**示例 3：**

```
输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
输出：[4,2,7,1,3,5]
```

<br/>

**提示：**

- 给定的树上的节点数介于 0 和 10^4 之间
- 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
- -10^8 <= val <= 10^8
- 新值和原始二叉搜索树中的任意节点值都不同



<br/>

### 答案：

#### 1，非递归方式解决

这题说的是让在二叉搜索树中插入一个节点，最简单的一种方式就是插入到叶子节点。**二叉搜索树的特点是左子树的值都小于当前节点，右子树的值都大于当前节点，并且左右子树都具有这个特性**。所以我们需要用插入的值val和根节点比较，



- **如果val大于根节点，说明值为val的节点应该插入到root节点的右子树上**
- **如果val小于根节点，说明值为val的节点应该插入到root节点的左子树上**



然后再继续执行上面的操作，直到找到叶子节点为止，然后再把它插进去，就以题中示例为例画个图来看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHSke9x4b90LyCTvwR1oMN3biafia9YOQ8W6aoZ9dNjDicmj1Bw5aBLKszcWZ14ClCFUQdp5a6ZsLguQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHSke9x4b90LyCTvwR1oMN3HVoQoPdn7gVrlqiaVia2zNtC5wd6jGCwPuRaxtC5Zf8YsTJWNClCSxyg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

代码如下

```java
public TreeNode insertIntoBST(TreeNode root, int val) {
    //边界条件判断
    if (root == null)
        return new TreeNode(val);
    TreeNode cur = root;
    while (true) {
        //如果当前节点cur的值大于val，说明val值应该插入到
        //当前节点cur的左子树，否则就插入到当前节点cur的右子树
        if (cur.val > val) {
            //如果左子节点不为空，就继续往下找
            if (cur.left != null) {
                cur = cur.left;
            } else {//如果左子节点为空，就直接插入去，然后再返回root节点
                cur.left = new TreeNode(val);
                return root;
            }
        } else {//同上
            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur.right = new TreeNode(val);
                return root;
            }
        }
    }
}
```



<br>



#### 2，递归方式解决

递归的方式原理还和上面一样，一直往下找，直到找到叶子节点为止，代码如下

```java
public TreeNode insertIntoBST(TreeNode root, int val) {
    //如果root为空，就直接创建一个新的节点
    if (root == null)
        return new TreeNode(val);
    //如果root节点的值大于val，说明值为val的节点应该在root
    //节点的左子树上
    if (root.val > val)
        root.left = insertIntoBST(root.left, val);
    else
        root.right = insertIntoBST(root.right, val);
    return root;
}
```

<br>

#### 3，总结

做这题之前首先要弄懂什么是二叉搜索树，然后才能进行后面的操作，最简单的方式就是把值插入到二叉搜索树的叶子节点。





 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
