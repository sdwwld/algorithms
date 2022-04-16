## [剑指 Offer 26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)（中等）

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

```
     3
    / \
   4   5
  / \
 1   2
```

给定的树 B：

```
   4 
  /
 1
```

返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

**示例 1：**

```
输入：A = [1,2,3], B = [3,1]
输出：false
```

**示例 2：**

```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```



**限制：**

0 <= 节点个数 <= 10000

<br/>

### 答案：

要判断B是否是A的子结构，像下面这样，我们只需要从根节点开始判断，通过递归的方式比较他的每一个子节点即可，所以代码也很容易写

![image.png](https://pic.leetcode-cn.com/19a78f61f77a982e31505fe36ceae2b906be5a8c05f22d112d3aff132bc0b3a5-image.png)

```java
public boolean isSubStructure(TreeNode A, TreeNode B) {
    //边界条件判断，如果A和B有一个为空，返回false
    if (A == null || B == null)
        return false;
    return isSub(A, B);
}

boolean isSub(TreeNode A, TreeNode B) {
    //这里如果B为空，说明B已经访问完了，确定是A的子结构
    if (B == null)
        return true;
    //如果B不为空A为空，或者这两个节点值不同，说明B树不是
    //A的子结构，直接返回false
    if (A == null || A.val != B.val)
        return false;
    //当前节点比较完之后还要继续判断左右子节点
    return isSub(A.left, B.left) && isSub(A.right, B.right);
}
```

但实际上B如果是A的子结构的话，不一定是从根节点开始的，也可能是下面这样

![image.png](https://pic.leetcode-cn.com/497c3b250f980963da6d8aabf3b7beea478174f2ac94f535d78d3fdf0d65796f-image.png)

也就是说**B不光有可能是A的子结构，也有可能是A左子树的子结构或者右子树的子结构**，所以如果从根节点判断B不是A的子结构，还要继续判断B是不是A左子树的子结构和右子树的子结构，代码如下

```java
public boolean isSubStructure(TreeNode A, TreeNode B) {
    if (A == null || B == null)
        return false;
    //先从根节点判断B是不是A的子结构，如果不是在分别从左右两个子树判断，
    //只要有一个为true，就说明B是A的子结构
    return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
}

boolean isSub(TreeNode A, TreeNode B) {
    //这里如果B为空，说明B已经访问完了，确定是A的子结构
    if (B == null)
        return true;
    //如果B不为空A为空，或者这两个节点值不同，说明B树不是
    //A的子结构，直接返回false
    if (A == null || A.val != B.val)
        return false;
    //当前节点比较完之后还要继续判断左右子节点
    return isSub(A.left, B.left) && isSub(A.right, B.right);
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（25. 合并两个排序的链表）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer25.md)

#### [下一题（27. 二叉树的镜像）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer27.md)