## [剑指 Offer 32. 从上到下打印二叉树-I](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)（中等）

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

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

返回：

```
[3,9,20,15,7]
```

<br/>

**提示：**

1. 节点总数 <= 1000

<br/>

### 答案：

#### 1，BFS解决

其实这就是二叉树的BFS，也可以看下之前讲的[373，数据结构-6,树](https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)，

![image.png](https://pic.leetcode-cn.com/1597977299-QxmvLH-image.png)

就是这样，一层一层打印，使用队列解决

```java
public int[] levelOrder(TreeNode root) {
    if (root == null)
        return new int[0];
    //队列
    Queue<TreeNode> queue = new LinkedList<>();
    List<Integer> list = new ArrayList<>();
    //根节点入队
    queue.add(root);
    while (!queue.isEmpty()) {
        //出队
        TreeNode node = queue.poll();
        //把结点值存放到list中
        list.add(node.val);
        //左右子节点如果不为空就加入到队列中
        if (node.left != null)
            queue.add(node.left);
        if (node.right != null)
            queue.add(node.right);
    }
    //把list转化为数组
    int[] res = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
        res[i] = list.get(i);
    }
    return res;
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/1597977356-bUesPX-image.png)

<br/>

#### 2，递归方式解决

其实这题很明显就是二叉树的宽度优先搜索，使用上面代码就对了。实际上我们还可以改一下，改成DFS并且还是递归的，我想除了我应该没人会这么无聊吧，有兴趣的也可以看下

```java
public int[] levelOrder(TreeNode root) {
    List<List<Integer>> list = new ArrayList<>();
    levelHelper(list, root, 0);
    List<Integer> tempList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
        tempList.addAll(list.get(i));
    }

    //把list转化为数组
    int[] res = new int[tempList.size()];
    for (int i = 0; i < tempList.size(); i++) {
        res[i] = tempList.get(i);
    }
    return res;
}

public void levelHelper(List<List<Integer>> list, TreeNode root, int height) {
    if (root == null)
        return;
    if (height >= list.size()) {
        list.add(new ArrayList<>());
    }
    list.get(height).add(root.val);
    levelHelper(list, root.left, height + 1);
    levelHelper(list, root.right, height + 1);
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/1597977429-mQumDo-image.png)

参照：

[剑指 Offer 32. 从上到下打印二叉树-II](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-II.md)

[剑指 Offer 32. 从上到下打印二叉树-III](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-III.md)

![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（31. 栈的压入、弹出序列）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer31.md)

#### [下一题（32. 从上到下打印二叉树-II）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-II.md)