## [19. 删除链表的倒数第N个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)（中等）

给定一个链表，删除链表的倒数第 *n* 个节点，并且返回链表的头结点。

**示例：**

```
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
```

**说明：**

<font size=2>给定的 *n* 保证是有效的。</font>

<br>

### 答案：

#### 1，非递归解决

这题让删除链表的倒数第n个节点，首先最容易想到的就是先求出链表的长度length，然后就可以找到要删除链表的前一个结点，让他的前一个结点指向要删除结点的下一个结点即可，这里就以示例为例画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0019/640.png)

再来看下代码

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode pre = head;
    int last = length(head) - n;
    //如果last等于0表示删除的是头结点
    if (last == 0)
        return head.next;
    //这里首先要找到要删除链表的前一个结点
    for (int i = 0; i < last - 1; i++) {
        pre = pre.next;
    }
    //然后让前一个结点的next指向要删除节点的next
    pre.next = pre.next.next;
    return head;
}

//求链表的长度
private int length(ListNode head) {
    int len = 0;
    while (head != null) {
        len++;
        head = head.next;
    }
    return len;
}
```

<br>

#### 2，双指针求解

上面是先计算链表的长度，其实不计算链表的长度也是可以，我们可以使用两个指针，一个指针fast先走n步，然后另一个指针slow从头结点开始，找到要删除结点的前一个结点，这样就可以完成结点的删除了。

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode fast = head;
    ListNode slow = head;
    //fast移n步，
    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }
    //如果fast为空，表示删除的是头结点
    if (fast == null)
        return head.next;

    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }
    //这里最终slow不是倒数第n个节点，他是倒数第n+1个节点，
    //他的下一个结点是倒数第n个节点,所以删除的是他的下一个结点
    slow.next = slow.next.next;
    return head;
}
```

<br>

#### 3，递归解决

我们知道获取链表的长度除了上面介绍的一种方式以外，还可以写成递归的方式，比如

```java
//求链表的长度
private int length(ListNode head) {
    if (head == null)
        return 0;
    return length(head.next) + 1;
}
```

上面计算链表长度的递归其实可以把它看做是从后往前计算，当计算的长度是n的时候就表示遍历到了倒数第n个节点了，这里只要求出倒数第n+1个节点，问题就迎刃而解了，来看下代码

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    int pos = length(head, n);
    // 说明删除的是头节点
    if (pos == n)
        return head.next;
    return head;
}

// 获取节点所在位置，逆序
public int length(ListNode node, int n) {
    if (node == null)
        return 0;
    int pos = length(node.next, n) + 1;
    //获取要删除链表的前一个结点，就可以完成链表的删除
    if (pos == n + 1)
        node.next = node.next.next;
    return pos;
}
```

<br>

#### 4，总结

要删除链表的倒数第n个节点，首先要找到链表的倒数第n+1个节点，这样就可以完成链表的删除了，关于怎么找，方式有多种。但要注意一点如果删除的是头结点的话，那么就不存在倒数第n+1个节点，所以这个要注意一下。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

