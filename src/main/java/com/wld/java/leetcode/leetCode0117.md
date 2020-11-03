## [117. 填充每个节点的下一个右侧节点指针 II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/)（中等）

给定一个二叉树

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

<br>

**进阶：**

- 你只能使用常量级额外空间。
- 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

<br>

**示例：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/15/117_sample.png)

```
输入：root = [1,2,3,4,5,null,7]
输出：[1,#,2,3,#,4,5,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
```

<br>

**提示：**

- 树中的节点数小于 `6000`
- `-100 <= node.val <= 100`

<br>

### 答案：

#### 1，BFS解决

看到关于二叉树的问题，首先要想到关于二叉树的一些常见遍历方式，

对于二叉树的遍历有

<br>

1. **前序遍历**
2. **中序遍历**
3. **后续遍历**
4. **深度优先搜索（DFS）**
5. **宽度优先搜索（BFS）**

<br>

除了上面介绍的5种以外，还有**Morris（莫里斯）的前中后3种遍历方式**，总共也就这8种。所以只要遇到二叉树相关的算法题，首先想到的就是上面的几种遍历方式，然后再稍加修改，基本上也就这个套路。

<br>

这题让求的就是让把二叉树中每行都串联起来，对于这道题来说最适合的就是BFS。也就是一行一行的遍历，如下图所示

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

在遍历每一行的时候，只要把他们串联起来就OK，下面就来把上面的代码改造一下

```java
public Node connect(Node root) {
    if (root == null)
        return root;
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        //每一层的数量
        int levelCount = queue.size();
        //前一个节点
        Node pre = null;
        for (int i = 0; i < levelCount; i++) {
            //出队
            Node node = queue.poll();
            //如果pre为空就表示node节点是这一行的第一个，
            //没有前一个节点指向他，否则就让前一个节点指向他
            if (pre != null) {
                pre.next = node;
            }
            //然后再让当前节点成为前一个节点
            pre = node;
            //左右子节点如果不为空就入队
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }
    return root;
}
```

<br>

#### 2，每一层看做一个链表

上面计算的时候把节点不停的入队然后再不停的出队，其实可以不需要队列，**每一行都可以看成一个链表，**比如第一行就是只有一个节点的链表，第二行是只有两个节点的链表（假如根节点的左右两个子节点都不为空）……，每次只遍历一层节点，然后顺便把子节点串成一个链表，接着遍历下一层节点的时候再把下下一层的结点串成一个链表……。画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0117/640.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0117/641.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0117/642.png)

代码如下

```java
public Node connect(Node root) {
    if (root == null)
        return root;
    //cur我们可以把它看做是每一层的链表
    Node cur = root;
    while (cur != null) {
        //遍历当前层的时候，为了方便操作在下一
        //层前面添加一个哑结点（注意这里是访问
        //当前层的节点，然后把下一层的节点串起来）
        Node dummy = new Node(0);
        //pre表示访下一层节点的前一个节点
        Node pre = dummy;
        //然后开始遍历当前层的链表
        while (cur != null) {
            if (cur.left != null) {
                //如果当前节点的左子节点不为空，就让pre节点
                //的next指向他，也就是把它串起来
                pre.next = cur.left;
                //然后再更新pre
                pre = pre.next;
            }
            //同理参照左子树
            if (cur.right != null) {
                pre.next = cur.right;
                pre = pre.next;
            }
            //继续访问这一行的下一个节点
            cur = cur.next;
        }
        //把下一层串联成一个链表之后，让他赋值给cur，
        //后续继续循环，直到cur为空为止
        cur = dummy.next;
    }
    return root;
}
```

<br>

#### 3，总结

看到二叉树首先要想到那8种遍历方式，然后根据题的要求再稍加修改，基本上可以完成大部分关于二叉树的算法题，所以二叉树的那几种遍历方式非常重要，熟练掌握之后对于二叉树的解题会有很大帮助，前面讲过二叉树的5种遍历方式[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)，其中包括递归和非递归的。还有二叉树莫里斯的3种遍历方式，后续有时间会再做介绍。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

