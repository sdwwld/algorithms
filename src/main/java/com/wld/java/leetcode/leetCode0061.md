## [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/)（中等）

给定一个链表，旋转链表，将链表每个节点向右移动 *k* 个位置，其中 *k* 是非负数。

**示例 1:**

```
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
```

**示例 2:**

```
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```

<br>

### 答案：

#### 1，双指针解决

这题k是非负数，但k有可能比链表的长度还要大，所以先要计算链表的长度len，需要旋转的步数就是（k%len）。一种比较简单的方式就是先把链表连接成一个环，然后再把链表在某个合适的位置断开。

<br>

我们可以使用两个指针，**一个快指针fast从头开始遍历直到走到链表的末尾，然后再把链表串成一个环形。还一个指针slow也是从头开始，走（len-k%len）步就是我们要返回的链表头**，这里可能有点疑问，为什么不是走（k%len）步，这是因为我们需要把链表后面的（k%len）个移到前面，因为单向链表我们没法从后往前遍历，所以我们只能从前往后移动（len-k%len）步。但实际上操作的时候会少走一步，具体来举个例子看一下，这里就以示例1为例画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0061/640.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0061/641.png)

原理比较简单，来直接看下代码

```java
public ListNode rotateRight(ListNode head, int k) {
    if (head == null)
        return head;
    ListNode fast = head, slow = head;
    //链表的长度
    int len = 1;
    //统计链表的长度，顺便找到链表的尾结点
    while (fast.next != null) {
        len++;
        fast = fast.next;
    }
    //首尾相连，先构成环
    fast.next = head;
    //慢指针移动的步数
    int step = len - k % len;
    //移动步数，这里大于1实际上是少移了一步
    while (step-- > 1) {
        slow = slow.next;
    }
    //temp就是需要返回的结点
    ListNode temp = slow.next;
    //因为链表是环形的，slow就相当于尾结点了，
    //直接让他的next等于空
    slow.next = null;
    return temp;
}
```

<br>

#### 2，总结

这道题使用快慢指针解决，快指针主要是获取链表的尾结点然后把链表串起来，慢指针要找到需要把链表截断的位置。

<br>



![](https://img-blog.csdnimg.cn/20200807155236311.png)

