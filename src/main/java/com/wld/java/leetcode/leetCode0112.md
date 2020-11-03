## [112. 路径总和](https://leetcode-cn.com/problems/path-sum/)（简单）

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

**说明:** 叶子节点是指没有子节点的节点。

**示例:** 

给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
```

返回 `true`, 因为存在目标和为 22 的根节点到叶子节点的路径 `5->4->11->2`。

<br>

### 答案：

#### 1，递归求解

这题让判断从根节点到叶子节点的所有路径中，有没有和等于sum的，如果看过之前讲的[442，剑指 Offer-回溯算法解二叉树中和为某一值的路径](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247488384&idx=1&sn=a493184aa793fee33d756b22cf873dc7&chksm=fb4180a0cc3609b63c48bafcc89a73d087e5827ec6acc982d9cb69d9702221119c2b990ddb22&scene=21#wechat_redirect)，再来看这一题就觉得这题有点简单了。第442题要求的是把所有的和等于sum的路径都打印出来，而这题只要判断有一个路径的和等于sum即可。

<br>

我们可以**从根节点往下走，走的时候减去当前节点的值，一直到叶子节点，如果减到最后，值等于叶子节点的值，说明存在这样的结果，直接返回true**，否则如果把所有节点都遍历完了也不存在这样的结果，就返回false。我们就以示例为例画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0112/640.png)

再来看下代码

```java
public boolean hasPathSum(TreeNode root, int sum) {
    //如果根节点为空，或者叶子节点也遍历完了也没找到这样的结果，就返回false
    if (root == null)
        return false;
    //如果到叶子节点了，并且剩余值等于叶子节点的值，说明找到了这样的结果，直接返回true
    if (root.left == null && root.right == null && sum - root.val == 0)
        return true;
    //分别沿着左右子节点走下去，然后顺便把当前节点的值减掉，左右子节点只要有一个返回true，
    //说明存在这样的结果
    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
}
```

<br>

#### 2，非递归解决

上面使用的是递归的方式，我们还可以使用非递归的方式，在遍历的时候有两种方式，**一种是从0开始累加，到叶子节点的时候如果累加的值等于sum，说明存在这样的一条路径**。还一种是减，**从根节点一直减下去，如果到叶子节点的时候，值等于叶子节点的值，说明也存在这样的一条路径**。原理都一样，这里就以加的方式来看下代码该怎么写

```java
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
        return false;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);//根节点入栈
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pop();//出栈
        //累加到叶子节点之后，结果等于sum，说明存在这样的一条路径
        if (cur.left == null && cur.right == null) {
            if (cur.val == sum)
                return true;
        }
        //右子节点累加，然后入栈
        if (cur.right != null) {
            cur.right.val = cur.val + cur.right.val;
            stack.push(cur.right);
        }
        //左子节点累加，然后入栈
        if (cur.left != null) {
            cur.left.val = cur.val + cur.left.val;
            stack.push(cur.left);
        }
    }
    return false;
}
```

如果大家看过[464. BFS和DFS解二叉树的所有路径](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247488825&idx=1&sn=8361c0d12f3125f5df0ee1b0b993d7eb&chksm=fb418619cc360f0fccb4a4b7bab1e69353ce8ad96c6390cd1965f460b4c483136b9ee63ea949&scene=21#wechat_redirect)，还可以不直接操作节点的值，可以再使用一个额外的栈，专门存放累加或者往下减的值，这个值是和节点一一对应的，他们会同时出栈，以及同时入栈，实现过程比较简单，这里就不在介绍。

<br>

#### 2，BFS解决

之前在讲[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)的时候，讲到树的BFS，就是一层一层的往下打印，像下面这样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/641.png)

他的代码如下

```java
public void levelOrder(TreeNode tree) {
    if (tree == null)
        return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(tree);//相当于把数据加入到队列尾部
    while (!queue.isEmpty()) {
        //poll方法相当于移除队列头部的元素
        TreeNode node = queue.poll();
        System.out.println(node.val);
        if (node.left != null)
            queue.add(node.left);
        if (node.right != null)
            queue.add(node.right);
    }
}
```

在一层一层打印的时候，我们可以把值累加或累减都可以，这里使用累减的方式来看下代码

```java
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
        return false;
    Queue<TreeNode> queue = new LinkedList<>();
    root.val = sum - root.val;
    queue.add(root);
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        //累减到根节点之后，结果为0，说明存在这样一条路径，直接返回true
        if (node.left == null && node.right == null && node.val == 0)
            return true;
        //左子节点累减
        if (node.left != null) {
            node.left.val = node.val - node.left.val;
            queue.add(node.left);
        }
        //右子节点累减
        if (node.right != null) {
            node.right.val = node.val - node.right.val;
            queue.add(node.right);
        }
    }
    return false;
}
```

<br>

#### 3，总结

如果对二叉树的各种遍历比较熟悉的话，这题还算是比较简单的，这题比较灵活，解法比较多，如果想写还可以继续写下去。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

