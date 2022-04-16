## [235. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)（简单）

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)

<br/>

**示例 1:**

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
```

**示例 2:**

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
```

<br/>

**说明:**

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉搜索树中。

<br/>

### 答案：

#### 1，非递归解决

这题让求二叉搜索树的最近公共祖先，而二叉搜索树的特点就是**左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点**，所以这题就好办了，从更节点开始遍历

<br>

- **如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找**
- **如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找**
- **如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，那么根节点就是他们的最近公共祖先节点。**

<br>

画个图看一下，比如要找0和5的最近公共祖先节点，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0235/640.png)

搞懂了上面的过程，代码就容易写了，我们来看下

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //如果根节点和p,q的差相乘是正数，说明这两个差值要么都是正数要么都是负数，也就是说
    //他们肯定都位于根节点的同一侧，就继续往下找
    while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right;
    //如果相乘的结果是负数，说明p和q位于根节点的两侧，如果等于0，说明至少有一个就是根节点
    return root;
}
```

<br>

#### 2，递归解决

也可把它改为递归的方式

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //如果小于等于0，说明p和q位于root的两侧，直接返回即可
    if ((root.val - p.val) * (root.val - q.val) <= 0)
        return root;
    //否则，p和q位于root的同一侧，就继续往下找
    return lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
}
```

如果嫌代码行数太多，那就一行解决

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    return (root.val - p.val) * (root.val - q.val) <= 0 ? root : lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
}
```

之前讲过[372，二叉树的最近公共祖先](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487027&idx=1&sn=fc4304bfd5d0fd3c628a306bacbed777&chksm=fb419d13cc361405434a506bc70fe92543254c36b2ff17076faf5b3e56f99465495efda056f3&scene=21#wechat_redirect)，也可以参照这道题看一下，第372题的树不是二叉搜索树，而是一般普通的树，所以第372题的解都可以拿到这题来用，这里代码就不在写了，有兴趣的可以看下

<br>

#### 3，总结

这题相对于372还是比较简单的，可以使用二叉搜索树的规律，就是左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

