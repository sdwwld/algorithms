## [257. 二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)（简单）

给定一个二叉树，返回所有从根节点到叶子节点的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

```
输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
```

<br/>

### 答案：

#### 1，DFS解决

这题让求的是从根节点到叶子节点的所有路径，最常见的一种方式就是DFS（深度优先搜索），也就是从根节点沿着最左边节点一直走下去（如果没有左子节点，有右子节点，会沿着右子节点走下去），当到达叶子节点的时候在返回到父节点，然后沿着父节点的右子节点开始走下去，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0257/640.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0257/641.png)

在前面讲过二叉树的dfs，[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)，他的代码如下

```java
public static void treeDFS(TreeNode root) {
    if (root == null)
        return;
    System.out.println(root.val);
    treeDFS(root.left);
    treeDFS(root.right);
}
```

他就是从根节点到叶子节点的所有路径都会访问一遍，我们只需要把这个路径串联起来即可，这里来仿照上面的代码改造一下

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if (root == null)
        return res;
    dfs(root, "", res);
    return res;
}

private void dfs(TreeNode root, String path, List<String> res) {
    //如果到达叶子节点，就把结果存放到集合res中
    if (root.left == null && root.right == null)
        res.add(path + root.val);
    //如果左子节点不为空，就沿着左子节点走下去
    if (root.left != null)
        dfs(root.left, path + root.val + "->", res);
    //如果右子节点不为空，就沿着右子节点走下去
    if (root.right != null)
        dfs(root.right, path + root.val + "->", res);
}
```

在第373题讲到二叉树DFS遍历的时候，还有一种非递归的写法，代码如下

```java
public static void treeDFS(TreeNode root) {
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

因为他的遍历过程没变，只不过写法改变了，我们也可以来对他进行改造，看下最终代码

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if (root == null)
        return res;
    //存储节点的栈
    Stack<TreeNode> stackNode = new Stack<>();
    //存储路径的栈，和上面的栈是同步进行的，这里路径指的是
    //从根节点到当前节点的路径
    Stack<String> stackPath = new Stack<>();
    //根节点和根节点的路径同时入栈
    stackNode.push(root);
    stackPath.push(root.val + "");
    while (!stackNode.empty()) {
        //当前节点和对应的路径同时出栈
        TreeNode node = stackNode.pop();
        String path = stackPath.pop();
        //如果到达叶子节点，就把路径加入到集合res中
        if (node.left == null && node.right == null) {
            res.add(path);
        }
        //如果右子节不为空，就把右子节点和对应的路径分别加入到栈中
        if (node.right != null) {
            stackPath.push(path + "->" + node.right.val);
            stackNode.push(node.right);
        }
        //同上
        if (node.left != null) {
            stackPath.push(path + "->" + node.left.val);
            stackNode.push(node.left);
        }
    }
    return res;
}
```

<br>

#### 2，BFS解决

BFS就是一层一层的打印，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/641.png)

只需要使用一个队列，把每层的节点都存放到队列中，然后再一个个出队，顺便把子节点在一个个存放到队列中……一直这样循环下去，直到队列为空为止，在[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)的时候也提到过二叉树的BFS遍历，他的代码如下

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

也可以对上面的代码进行改造，改造的原理和DFS的非递归解法一样，就是使用一个变量存放从根节点到当前节点的路径，代码如下

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if (root == null)
        return res;
    //队列，节点和路径成对出现，路径就是从根节点到当前节点的路径
    Queue<Object> queue = new LinkedList<>();
    queue.add(root);
    queue.add(root.val + "");
    while (!queue.isEmpty()) {
        TreeNode node = (TreeNode) queue.poll();
        String path = (String) queue.poll();
        //如果到叶子节点，说明找到了一条完整路径
        if (node.left == null && node.right == null) {
            res.add(path);
        }

        //右子节点不为空就把右子节点和路径存放到队列中
        if (node.right != null) {
            queue.add(node.right);
            queue.add(path + "->" + node.right.val);
        }

        //左子节点不为空就把左子节点和路径存放到队列中
        if (node.left != null) {
            queue.add(node.left);
            queue.add(path + "->" + node.left.val);
        }
    }
    return res;
}
```

<br>

#### 3，递归解法

我们来思考这样一个问题，如果知道了左子树和右子树的所有路径，我们在用根节点和他们连在一起，是不是就是从根节点到所有叶子节点的所有路径，所以这里最容易想到的就是递归

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0257/642.png)

最后再来看下代码

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if (root == null)
        return res;
    //到达叶子节点，把路径加入到集合中
    if (root.left == null && root.right == null) {
        res.add(root.val + "");
        return res;
    }
    //遍历左子节点的所有路径
    for (String path : binaryTreePaths(root.left)) {
        res.add(root.val + "->" + path);
    }
    //遍历右子节点的所有路径
    for (String path : binaryTreePaths(root.right)) {
        res.add(root.val + "->" + path);
    }
    return res;
}
```

<br>

#### 4，总结

二叉树的遍历常见的也就是前序遍历，中序遍历，后续遍历，BFS，DFS，以及莫里斯的前序，中序和后续这几种，有的还说前序遍历就是DFS，其实可以这么说。但DFS却不是前序遍历，因为DFS不光可以先从左子树开始遍历还可以先从右子树开始遍历。前面五种之前在[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)已经讲过，后面的3种后续有时间会再做介绍。只要掌握二叉树的这几种遍历方式，对于大部分关于二叉树的问题都可以参照这几种方式进行修改来解决。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

