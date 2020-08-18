## [剑指 Offer 27. 二叉树的镜像](https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/)（简单）

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

镜像输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

<br/>

**示例 1：**

```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

<br/>

**限制：**

0 <= 节点个数 <= 1000

<br/>

### 答案：

#### 1，BFS解决

之前讲[373，数据结构-6](https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect),树的时候，提到过二叉树的广度优先搜索，就是一层一层的访问，像下面这样

![image.png](https://pic.leetcode-cn.com/e5d5c8a52b341ee49b73a2dcfdf2cde4c7317a868bca35b81790af93475711da-image.png)

二叉树的BFS代码如下

```java
public static void treeBFS(TreeNode root) {
    //如果为空直接返回
    if (root == null)
        return;
    //队列
    Queue<TreeNode> queue = new LinkedList<>();
    //首先把根节点加入到队列中
    queue.add(root);
    //如果队列不为空就继续循环
    while (!queue.isEmpty()) {
        //poll方法相当于移除队列头部的元素
        TreeNode node = queue.poll();
        //打印当前节点
        System.out.println(node.val);
        //如果当前节点的左子树不为空，就把左子树
        //节点加入到队列中
        if (node.left != null)
            queue.add(node.left);
        //如果当前节点的右子树不为空，就把右子树
        //节点加入到队列中
        if (node.right != null)
            queue.add(node.right);
    }
}
```

这题要求的是输出二叉树的镜像，就是**每一个节点的左右子节点进行交换**，随便画个二叉树看一下

![image.png](https://pic.leetcode-cn.com/904dc7109f1f1f16cd8720fdae62ed751c70e544223c8f3f1b8bc6990aadc250-image.png)

我们需要遍历每一个节点，然后交换他的两个子节点，一直循环下去，直到所有的节点都遍历完为止，代码如下

```java
public TreeNode mirrorTree(TreeNode root) {
    //如果为空直接返回
    if (root == null)
        return null;
    //队列
    final Queue<TreeNode> queue = new LinkedList<>();
    //首先把根节点加入到队列中
    queue.add(root);
    while (!queue.isEmpty()) {
        //poll方法相当于移除队列头部的元素
        TreeNode node = queue.poll();
        //交换node节点的两个子节点
        TreeNode left = node.left;
        node.left = node.right;
        node.right = left;
        //如果当前节点的左子树不为空，就把左子树
        //节点加入到队列中
        if (node.left != null) {
            queue.add(node.left);
        }
        //如果当前节点的右子树不为空，就把右子树
        //节点加入到队列中
        if (node.right != null) {
            queue.add(node.right);
        }
    }
    return root;
}
```

看一下运行结果
![image.png](https://pic.leetcode-cn.com/ec657da8ebaaea68f37aca1a12c5a1e9d99790898c2c2eee5d14edfaaf5a729f-image.png)

<br/>

#### 2，DFS解决

无论是BFS还是DFS都会访问到每一个节点，访问每个节点的时候交换他的左右子节点，直到所有的节点都访问完为止，代码如下

```java
public TreeNode mirrorTree(TreeNode root) {//DFS
    //如果为空直接返回
    if (root == null)
        return null;
    //栈
    Stack<TreeNode> stack = new Stack<>();
    //根节点压栈
    stack.push(root);
    //如果栈不为空就继续循环
    while (!stack.empty()) {
        //出栈
        TreeNode node = stack.pop();
        //子节点交换
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        //左子节点不为空入栈
        if (node.left != null)
            stack.push(node.left);
        //右子节点不为空入栈
        if (node.right != null)
            stack.push(node.right);
    }
    return root;
}
```

看一下运行结果，这种效率就差了很多
![image.png](https://pic.leetcode-cn.com/a88825af8bace93e41f63ba5c841ef10a793a86bd08e4ed04118368bfb62be29-image.png)

<br/>

#### 3，中序遍历解决

这题其实解法比较多，只要访问他的每一个节点，然后交换子节点即可，我们知道二叉树不光有BFS和DFS访问顺序，而且还有前序遍历，中序遍历和后续遍历等，不管哪种访问方式，只要能把所有节点都能访问一遍然后交换子节点就能解决，我们这里就以中序遍历来看下，前序和后序就不在看了。在[373，数据结构-6,树](https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)中，提到二叉树中序遍历的非递归写法如下

```java
public static void inOrderTraversal(TreeNode tree) {
    Stack<TreeNode> stack = new Stack<>();
    while (tree != null || !stack.isEmpty()) {
        while (tree != null) {
            stack.push(tree);
            tree = tree.left;
        }
        if (!stack.isEmpty()) {
            tree = stack.pop();
            System.out.println(tree.val);
            tree = tree.right;
        }
    }
}
```

我们来对他改造一下，就是在访问每个节点的时候交换，代码如下

```java
public static TreeNode mirrorTree(TreeNode root) {
    //如果为空直接返回
    if (root == null)
        return null;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        if (!stack.isEmpty()) {
            node = stack.pop();
            //子节点交换
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            //注意这里以前是node.right，因为上面已经交换了
            //，所以这里要改为node.left
            node = node.left;
        }
    }
    return root;
}
```

<br/>

#### 4，递归方式解决

二叉树中序遍历的递归代码如下

```java
public void inOrderTraversal(TreeNode node) {
    if (node == null)
        return;
    inOrderTraversal(node.left);
    System.out.println(node.val);
    inOrderTraversal(node.right);
}
```

上面说了，只要能访问二叉树的每一个节点，然后交换左右子节点就行了，这里就以二叉树中序遍历递归的方式来看下

```java
public TreeNode mirrorTree(TreeNode root) {
    if (root == null)
        return null;
    mirrorTree(root.left);
    //子节点交换
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    //上面交换过了，这里root.right要变成root.left
    mirrorTree(root.left);
    return root;
}
```

看下运行结果
![image.png](https://pic.leetcode-cn.com/f8c92e4b9e01fb5cffa4e6c1fd0de38fa85101c3969e8bdae0b9c89515dfc19a-image.png)

再来看一个后续遍历的

```java
public TreeNode mirrorTree(TreeNode root) {
    if (root == null)
        return null;
    TreeNode left = mirrorTree(root.left);
    TreeNode right = mirrorTree(root.right);
    root.left = right;
    root.right = left;
    return root;
}
```

运行结果如下，这个执行时间和内存消耗应该是比较好的了

<br/>

#### 5，总结

这题没什么难度，但解法比较多，主要是因为二叉树的遍历方式比较多，如果每一种方式递归和非递归都写的话就更多了，这里不在一直写下去了。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（26. 树的子结构）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer26.md)

#### [下一题（28. 对称的二叉树）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer28.md)