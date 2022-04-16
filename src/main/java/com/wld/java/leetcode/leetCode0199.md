## [199. 二叉树的右视图](https://leetcode-cn.com/problems/binary-tree-right-side-view/)（中等）

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

**示例:**

```
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

<br/>

### 答案：

这题说的很明白，就是站在一棵二叉树的右边，你所能看到的结点值。对于二叉树的遍历，前面有简单的介绍过5种遍历方式（有兴趣的可以看下[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)），分别是：

1. 前序遍历
2. 中序遍历
3. 后续遍历
4. 深度优先搜索（DFS）
5. 宽度优先搜索（BFS）

<br>

除了上面介绍的5种以外，还有Morris（莫里斯）的前中后3种遍历方式，后面的3种后续会介绍。所以只要遇到二叉树相关的算法题，首先想到的就是上面的几种遍历方式，基本上也就这个套路，没有别的可选择。对于这道题来说最适合的两种遍历方式就是DFS和BFS。

<br>

#### 1，DFS解决

DFS的遍历顺序是从根节点开始一直往左子节点走下去，当走到叶子节点的时候会回到上一个结点，然后从上一个结点的右子节点继续同样的操作……，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/642.png)

二叉树的DFS代码如下

```java
public void treeDFS(TreeNode root) {
    if (root == null)
        return;
    System.out.println(root.val);
    treeDFS(root.left);
    treeDFS(root.right);
}
```

为了做这道题我们来对上面代码进行改造，上面代码先遍历的是左子树，而这题求的是二叉树的右视图，我们应该先遍历右子树才对。这里随便举个例子来画个图看一下，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0199/640.png)

从根节点开始往右子节点开始遍历，这么我们可以发现一个规律就是**每一层最先遍历的结点就是从右边最先看到的结点** 。如上图所示，我们可以看到，第一层最先遍历的结点是A，第二层最先遍历的结点是C，第三层最先遍历的结点是F，第四层最先看到的是G，而这4个节点值[A，C，F，G]就是我们要求的结果。搞懂了上面的分析过程，代码就so easy了。

```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    dfs(root, res, 0);
    return res;
}

public void dfs(TreeNode curr, List<Integer> res, int level) {
    //递归的终止条件判断
    if (curr == null) {
        return;
    }
    //level表示的是第几层，因为是先遍历右子树，所以每一层最先遍历
    //的结点值就是我们所需要的，当下面语句成立的时候，就表示当前节
    //点值所在的那一行是最先遍历的，所以要把他加入到集合res中
    if (level == res.size()) {
        res.add(curr.val);
    }
    //先遍历右子树，在遍历左子树
    dfs(curr.right, res, level + 1);
    dfs(curr.left, res, level + 1);
}
```

<br>

上是递归的方式解决，在[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)提到过二叉树DFS非递归的代码

```java
public void treeDFS(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    stack.add(root);
    while (!stack.empty()) {
        TreeNode node = stack.pop();
        System.out.println(node.val);
        if (node.right != null) {
            stack.push(node.right);
        }
        if (node.left != null) {
            stack.push(node.left);
        }
    }
}
```

我们也可以仿照上面代码来写下，这里使用两个栈，一个是存储当前节点的，一个是存储当前节点所对应的层数，代码如下

```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    //终止条件判断
    if (root == null)
        return res;
    //两个栈，一个存储当前节点，一个存储当期节点在第几层
    Stack<TreeNode> stackNode = new Stack<>();
    Stack<Integer> stackLevel = new Stack<>();
    //当前节点和当前节点的层数同时入栈
    stackNode.add(root);
    stackLevel.add(0);
    while (!stackNode.empty()) {
        //当前节点和当前节点的层数同时出栈
        TreeNode node = stackNode.pop();
        int level = stackLevel.pop();
        //下一层最先访问的结点就是我们需要的值
        if (res.size() == level)
            res.add(node.val);
        //先访问左子节点，在访问右子节点
        if (node.left != null) {
            stackNode.push(node.left);
            stackLevel.push(level + 1);
        }
        if (node.right != null) {
            stackNode.push(node.right);
            stackLevel.push(level + 1);
        }
    }
    return res;
}
```

<br>

#### 2，BFS解决

这里只是换了个写法，其实整体思路还是没变，二叉树的BFS是一层一层的往下访问，就像下面图中这样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/641.png)

二叉树的BFS代码如下

```java
public void levelOrder(TreeNode tree) {
    if (tree == null)
        return;
    //创建队列
    Queue<TreeNode> queue = new LinkedList<>();
    //入队
    queue.add(tree);
    while (!queue.isEmpty()) {
        //出队
        TreeNode node = queue.poll();
        System.out.println(node.val);
        if (node.left != null)
            queue.add(node.left);
        if (node.right != null)
            queue.add(node.right);
    }
}
```

我们来对他进行改造一下

```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    //终止条件判断
    if (root == null)
        return res;
    //创建队列
    Queue<TreeNode> queue = new LinkedList();
    queue.offer(root);
    while (!queue.isEmpty()) {
        //每层的数量
        int count = queue.size();
        while (count-- > 0) {
            //当前节点出队
            TreeNode cur = queue.poll();
            //因为每层是从左往右依次入队的，所以每层的
            //最后一个就是我们所需要的
            if (count == 0)
                res.add(cur.val);
            //左子树如果不为空，左子树入队，右子树如果不为空
            //右子树入队
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);
        }
    }
    return res;
}
```

<br>

#### 3，总结

对于二叉树的一些常见遍历一定要熟练掌握，总共加起来也就那8种，不是很多，如果掌握了那些遍历方式，对于二叉树的一些算法题只要是稍加修改基本上都能做的出来。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

