## [剑指 Offer 32. 从上到下打印二叉树-II](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/)（简单）

从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

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
  [9,20],
  [15,7]
]
```

<br/>

**提示：**

1. 节点总数 <= 1000

<br/>

### 答案：

#### 1，BFS解决

这题和上一题[剑指 Offer 32 - I. 从上到下打印二叉树](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-I.md)其实是一样的，只不过上一题返回的是数组，这一题返回的是list。返回数组，我们还要初始化数组，但不知道数组的大小，所以一般是先储存在list中再转化为数组，返回list就比较简单了。

![image.png](https://pic.leetcode-cn.com/1597977711-ytedjZ-image.png)

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    //边界条件判断
    if (root == null)
        return new ArrayList<>();
    //队列
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    //根节点入队
    queue.add(root);
    //如果队列不为空就继续循环
    while (!queue.isEmpty()) {
        //BFS打印，levelNum表示的是每层的结点数
        int levelNum = queue.size();
        //subList存储的是每层的结点值
        List<Integer> subList = new ArrayList<>();
        for (int i = 0; i < levelNum; i++) {
            //出队
            TreeNode node = queue.poll();
            subList.add(node.val);
            //左右子节点如果不为空就加入到队列中
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        //把每层的结点值存储在res中，
        res.add(subList);
    }
    return res;
}
```

看下运行结果

![image.png](https://pic.leetcode-cn.com/1597977757-jvjyNu-image.png)

<br/>

#### 2，DFS解决

这题让一层一层的打印，其实就是BFS，但使用DFS也是可以解决的，看一下

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    levelHelper(res, root, 0);
    return res;
}

public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
    //边界条件判断
    if (root == null)
        return;
    //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
    //要先把下一层的list初始化，防止下面add的时候出现空指针异常
    if (level >= list.size()) {
        list.add(new ArrayList<>());
    }
    //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
    list.get(level).add(root.val);
    //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
    levelHelper(list, root.left, level + 1);
    levelHelper(list, root.right, level + 1);
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/1597977813-TWdtOo-image.png)



参照：

[剑指 Offer 32. 从上到下打印二叉树-I](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-I.md)

[剑指 Offer 32. 从上到下打印二叉树-III](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-III.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（32. 从上到下打印二叉树-I）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-I.md)

#### [下一题（32. 从上到下打印二叉树-III）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-III.md)