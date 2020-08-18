## [剑指 Offer 28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)（简单）

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```

<br/>

**示例 1：**

```
输入：root = [1,2,2,3,4,4,3]
输出：true
```

**示例 2：**

```
输入：root = [1,2,2,null,3,null,3]
输出：false
```

<br/>

**限制：**

0 <= 节点个数 <= 1000

<br/>

### 答案：

#### 1，递归解决

判断二叉树是否是对称，需要从子节点开始比较，两个子节点的值必须相同，并且左子节点的右子节点（如果有）必须等于右子节点的左子节点，左子节点的左子节点必须等于右子节点的右子节点。就像下面图中那样

![image.png](https://pic.leetcode-cn.com/7e869a67d741d9ef4d5e51f18a5571f2a537f4393a65f2e205888f783074660a-image.png)

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null)
        return true;
    //从两个子节点开始判断
    return isSymmetricHelper(root.left, root.right);
}

public boolean isSymmetricHelper(TreeNode left, TreeNode right) {
    //如果左右子节点都为空，说明当前节点是叶子节点，返回true
    if (left == null && right == null)
        return true;
    //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
    if (left == null || right == null || left.val != right.val)
        return false;
    //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
    return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
}
```

看下运行结果

![image.png](https://pic.leetcode-cn.com/4af8ee5d3f600e503229a36f75dda82d7449445137f7822750b990207560c1de-image.png)

<br/>

#### 2，非递归解决

非递归解决和上面原理一样，直接看下代码，不懂的代码中有详细的注释

```java
public boolean isSymmetric(TreeNode root) {
    //队列
    Queue<TreeNode> queue = new LinkedList<>();
    if (root == null)
        return true;
    //左子节点和右子节点同时入队
    queue.add(root.left);
    queue.add(root.right);
    //如果队列不为空就继续循环
    while (!queue.isEmpty()) {
        //每两个出队
        TreeNode left = queue.poll(), right = queue.poll();
        //如果都为空继续循环
        if (left == null && right == null)
            continue;
        //如果一个为空一个不为空，说明不是对称的，直接返回false
        if (left == null ^ right == null)
            return false;
        //如果这两个值不相同，也不是对称的，直接返回false
        if (left.val != right.val)
            return false;
        //这里要记住入队的顺序，他会每两个两个的出队。
        //左子节点的左子节点和右子节点的右子节点同时
        //入队，因为他俩会同时比较。
        //左子节点的右子节点和右子节点的左子节点同时入队，
        //因为他俩会同时比较
        queue.add(left.left);
        queue.add(right.right);
        queue.add(left.right);
        queue.add(right.left);
    }
    return true;
}
```

看下运行结果，这种就差了很多

![image.png](https://pic.leetcode-cn.com/bb52a1452d9aca08c7d3b89f5e5893eb6a4a8920fa62cfb214d27b7d55660ca0-image.png)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（27. 二叉树的镜像）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer27.md)

#### [下一题（29. 顺时针打印矩阵）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer29.md)