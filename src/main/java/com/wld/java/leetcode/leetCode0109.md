## [109. 有序链表转换二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/)（中等）

给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

**示例:**

```
给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
```

<br/>

### 答案：

**1，快慢指针**

题中说了，是按照升序排列的单链表，我们只需要找到他的中间结点，让他成为树的根节点，中间结点前面的就是根节点左子树的所有节点，中间节点后面的就是根节点右子树的所有节点，然后使用递归的方式再分别对左右子树进行相同的操作……

这里就以链表```1→2→3→4→5```为例来画个图看一下

![image.png](https://pic.leetcode-cn.com/2feb8feb89d47c5d482a2c12d265b97b41fed1e46c507897dc4e06dc8152f3e8-image.png)

```java
    public TreeNode sortedListToBST(ListNode head) {
        //边界条件的判断
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        //这里通过快慢指针找到链表的中间结点slow，pre就是中间
        //结点slow的前一个结点
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //链表断开为两部分，一部分是node的左子节点，一部分是node
        //的右子节点
        pre.next = null;
        //node就是当前节点
        TreeNode node = new TreeNode(slow.val);
        //从head节点到pre节点是node左子树的节点
        node.left = sortedListToBST(head);
        //从slow.next到链表的末尾是node的右子树的结点
        node.right = sortedListToBST(slow.next);
        return node;
    }
```

实际上还可以把链表中的值全都存储到集合list中，每次把list分为两部分，和上面原理一样

```java
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        //把链表节点值全部提取到list中
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return sortedListToBSTHelper(list, 0, list.size() - 1);

    }

    TreeNode sortedListToBSTHelper(List<Integer> list, int left, int right) {
        if (left > right)
            return null;
        //把list中数据分为两部分
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = sortedListToBSTHelper(list, left, mid - 1);
        root.right = sortedListToBSTHelper(list, mid + 1, right);
        return root;
    }
```



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（108. 将有序数组转换为二叉搜索树）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0108.md)

#### [下一题（110. 平衡二叉树）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0110.md)