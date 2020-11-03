## [129. 求根到叶子节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/)（中等）

给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

**说明:** 叶子节点是指没有子节点的节点。

**示例 1:**

```
输入: [1,2,3]
    1
   / \
  2   3
输出: 25
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.
```

**示例 2:**

```
输入: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
输出: 1026
解释:
从根到叶子节点路径 4->9->5 代表数字 495.
从根到叶子节点路径 4->9->1 代表数字 491.
从根到叶子节点路径 4->0 代表数字 40.
因此，数字总和 = 495 + 491 + 40 = 1026.
```

<br/>

### 答案：

#### 1，DFS解决

这题说的是**每条从根节点到叶子结点的路径都代表一个数字，然后再把这些数字加起来**即可。遍历一棵树从根节点到叶子结点的所有路径，最容易想到的是DFS，所以这题使用DFS是最容易解决的。如果对二叉树的DFS不熟悉的话，可以看下[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)

<br>

解决方式就是从根节点往下走的时候，那么**当前节点的值就是父节点的值*10+当前节点的值**。默认根节点的父节点的值是0，如果到达叶子结点，就用一个全局的变量把叶子结点的值加起来。这里就以示例2为例来画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0129/640.png)

搞懂了上的分析过程，代码就容易多了

```java
public int sumNumbers(TreeNode root) {
    //如果根节点是空，直接返回0即可
    if (root == null)
        return 0;
    //两个栈，一个存储的是节点，一个存储的是节点对应的值
    Stack<TreeNode> nodeStack = new Stack<>();
    Stack<Integer> valueStack = new Stack<>();
    //全局的，统计所有路径的和
    int res = 0;
    nodeStack.add(root);
    valueStack.add(root.val);
    while (!nodeStack.isEmpty()) {
        //当前节点和当前节点的值同时出栈
        TreeNode node = nodeStack.pop();
        int value = valueStack.pop();
        if (node.left == null && node.right == null) {
            //如果当前节点是叶子结点，说明找到了一条路径，把这条
            //路径的值加入到全局变量res中
            res += value;
        } else {
            //如果不是叶子节点就执行下面的操作
            if (node.right != null) {
                //把子节点和子节点的值分别加入到栈中，这里子节点的值
                //就是父节点的值*10+当前节点的值
                nodeStack.push(node.right);
                valueStack.push(value * 10 + node.right.val);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                valueStack.push(value * 10 + node.left.val);
            }
        }
    }
    return res;
}
```

如果嫌上面代码多，也可以改为递归的方式

```java
public int sumNumbers(TreeNode root) {
    return dfs(root, 0);
}

private int dfs(TreeNode root, int sum) {
    //终止条件的判断
    if (root == null)
        return 0;
    //计算当前节点的值
    sum = sum * 10 + root.val;
    //如果当前节点是叶子节点，说明找到了一条完整路径，直接
    //返回这条路径的值即可
    if (root.left == null && root.right == null)
        return sum;
    //如果当前节点不是叶子结点，返回左右子节点的路径和
    return dfs(root.left, sum) + dfs(root.right, sum);
}
```

<br>

#### 2，BFS解决

对于树的遍历，我们知道除了前序中序后续遍历以外，还有DFS和BFS，DFS上面已经讲过了，下面再来看一下BFS，他是一层一层的遍历的，就像下面这样，如果不太懂的也可以先看下[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/641.png)

原理和上面DFS类似，每遍历一个结点，我们就要重新计算当前节点的值，那么**当前节点的值就是父节点的值*10+当前节点的值**。

```java
public int sumNumbers(TreeNode root) {
    //边界条件的判断
    if (root == null)
        return 0;
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    Queue<Integer> valueQueue = new LinkedList<>();
    int res = 0;
    nodeQueue.add(root);
    valueQueue.add(root.val);
    while (!nodeQueue.isEmpty()) {
        //节点和节点对应的值同时出队
        TreeNode node = nodeQueue.poll();
        int value = valueQueue.poll();
        if (node.left == null && node.right == null) {
            //如果当前节点是叶子结点，说明找到了一条路径，把这条
            //路径的值加入到全局变量res中
            res += value;
        } else {
            //如果不是叶子节点就执行下面的操作
            if (node.left != null) {
                //把子节点和子节点的值分别加入到队列中，这里子节点的值
                //就是父节点的值*10+当前节点的值
                nodeQueue.add(node.left);
                valueQueue.add(value * 10 + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                valueQueue.add(value * 10 + node.right.val);
            }
        }
    }
    return res;
}
```

<br>

#### 3，总结

这题从根节点到每个叶子结点都是一个完整的数字，我们要做的就是把这每个数字加起来。使用DFS应该是最容易理解的，其实每条路径也可以把它想象成为一个链表，每个链表代表一个数字，然后把所有的链表所代表的数字加起来就是这题要求的结果。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

