## [剑指 Offer 32. 从上到下打印二叉树-III](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)（中等）

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

<br/>

例如:
给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```
[
  [3],
  [20,9],
  [15,7]
]
```

<br/>

**提示：**

1. 节点总数 <= 1000

<br/>

### 答案：

#### 1，BFS打印

二叉树的的层次遍历就是一层一层的遍历，也就是我们俗称的BFS（宽度优先搜索算法（又称广度优先搜索））,之前在[373，数据结构-6,树](https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)中讲过树的宽度优先搜索，最简单的方式就是使用队列。但这题打印的时候多了一个条件，就是不能一直从一个方向打印，要先从左边打印然后再从右边打印……，就这样交替进行，所以这里要有个变量来判断是从左往右还是从右往左打印，代码比较简单，我们来看下。

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
        return res;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean leftToRight = true;
    while (!queue.isEmpty()) {
        List<Integer> level = new ArrayList<>();
        //统计这一行有多少个节点
        int count = queue.size();
        //遍历这一行的所有节点
        for (int i = 0; i < count; i++) {
            //poll移除队列头部元素（队列在头部移除，尾部添加）
            TreeNode node = queue.poll();
            //判断是从左往右打印还是从右往左打印。
            if (leftToRight) {
                level.add(node.val);
            } else {
                level.add(0, node.val);
            }
            //左右子节点如果不为空会被加入到队列中
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        res.add(level);
        leftToRight = !leftToRight;
    }
    return res;
}
```

上面代码中如果把第17-21行的代码直接换成第18行的代码就是我们之前讲过的BFS，就是一层一层往下打印。只不过这里多了一个条件的判断。

<br/>

当然我们还可以根据每一层是第几层来判断，如果根节点是第1层的话，那么我们在层数是奇数的时候从左往右打印，如果层数是偶数的时候从右往左打印。在前面我们讲队列的时候[359，数据结构-3,队列](https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486409&idx=2&sn=d6548abcc010f96dd632ba6928afd07e&chksm=fb4198e9cc3611ff0625fb40c3604856368f426c2c4434c78d544e4b5d6d468f220d6be6d2a4&scene=21#wechat_redirect)我们讲到了双端队列，就是一个可以在两边同时添加和删除的队列。这里我们使用上面两种方式的结合，来看下代码。

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
        return res;
    //双端队列，两边都可以操作
    Deque<TreeNode> deque = new LinkedList<>();
    //添加到队列的头
    deque.addFirst(root);
    while (!deque.isEmpty()) {
        List<Integer> level = new ArrayList<>();
        //统计这一行有多少个节点
        int count = deque.size();
        //遍历这一行的所有节点
        TreeNode cur;
        for (int i = 0; i < count; i++) {
            if (res.size() % 2 == 1) {
                //从左边往右边打印
                //移除队列头部的元素，如果子节点不为空加入到队列的尾部
                cur = deque.removeFirst();
                if (cur.right != null)
                    deque.addLast(cur.right);
                if (cur.left != null)
                    deque.addLast(cur.left);
            } else {
                //从右边往左边打印
                //移除队列尾部的元素，如果子节点不为空加入到队列的头部
                cur = deque.removeLast();
                if (cur.left != null)
                    deque.addFirst(cur.left);
                if (cur.right != null)
                    deque.addFirst(cur.right);
            }
            level.add(cur.val);
        }
        res.add(level);
    }
    return res;
}
```

<br/>

#### 2，DFS打印

这题除了使用BFS以外，我们还可以使用DFS。但这里我们要有个判断，如果走到下一层的时候集合没有创建，我们要先创建下一层的集合，代码也很简单，我们来看下。

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    travel(root, res, 0);
    return res;
}

private void travel(TreeNode cur, List<List<Integer>> res, int level) {
    if (cur == null)
        return;
    //如果res.size() <= level说明下一层的集合还没创建，所以要先创建下一层的集合
    if (res.size() <= level) {
        List<Integer> newLevel = new LinkedList<>();
        res.add(newLevel);
    }
    //遍历到第几层我们就操作第几层的数据
    List<Integer> list = res.get(level);
    //这里默认根节点是第0层，偶数层相当于从左往右遍历，
    // 所以要添加到集合的末尾，如果是奇数层相当于从右往左遍历，
    // 要把数据添加到集合的开头
    if (level % 2 == 0)
        list.add(cur.val);
    else
        list.add(0, cur.val);
    //分别遍历左右两个子节点，到下一层了，所以层数要加1
    travel(cur.left, res, level + 1);
    travel(cur.right, res, level + 1);
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/1597978341-SHVyIi-image.png)





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（32. 从上到下打印二叉树-II）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-II.md)

#### [下一题（33. 二叉搜索树的后序遍历序列）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer33.md)