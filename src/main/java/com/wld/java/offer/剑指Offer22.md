## [剑指 Offer 22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)（简单）

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

<br/>

**示例：**

```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

<br/>

### 答案：

#### 1，双指针求解

这题要求链表的倒数第k个节点，最简单的方式就是使用两个指针，第一个指针先移动k步，然后第二个指针再从头开始，这个时候这两个指针同时移动，当第一个指针到链表的末尾的时候，返回第二个指针即可。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHyJ6wibZRgATib8BzAQibDiaOl6LEuuINFicK4XiaqRKkkvddZqs27spTHic2EgroAjBmHcR0L1ZLfOFDLQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public ListNode getKthFromEnd(ListNode head, int k) {
    ListNode first = head;
    ListNode second = head;
    //第一个指针先走k步
    while (k-- > 0) {
        first = first.next;
    }
    //然后两个指针在同时前进
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    return second;
}
```

<br/>

#### 2，使用栈解决

这题要求的是返回后面的k个节点，我们只要把原链表的结点全部压栈，然后再把栈中最上面的k个节点出栈，出栈的结点重新串成一个新的链表即可，原理也比较简单，直接看下代码。

```java
public ListNode getKthFromEnd(ListNode head, int k) {
    Stack<ListNode> stack = new Stack<>();
    //链表节点压栈
    while (head != null) {
        stack.push(head);
        head = head.next;
    }
    //在出栈串成新的链表
    ListNode firstNode = stack.pop();
    while (--k > 0) {
        ListNode temp = stack.pop();
        temp.next = firstNode;
        firstNode = temp;
    }
    return firstNode;
}
```

<br/>

#### 3，递归求解

我们之前讲过链表的逆序打印[410，剑指 Offer-从尾到头打印链表](https://mp.weixin.qq.com/s/caOEBjxDY7rxU5yG-ilfOw)，其中有这样一段代码

```java
public void reversePrint(ListNode head) {
    if (head == null)
        return;
    reversePrint(head.next);
    System.out.println(head.val);
}
```

这段代码其实很简单，我们要理解他就要弄懂递归的原理，递归分为两个过程，**递**和**归**，看一下下面的图就知道了，先往下传递，当遇到终止条件的时候开始往回走。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHyJ6wibZRgATib8BzAQibDiaOlgENhjjsL7eae1zB0NLYzibeNb1bKxWUuDkbux6IZzayoNDcybHvqyAg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

前面也刚讲过递归的原理[426，什么是递归，通过这篇文章，让你彻底搞懂递归](https://mp.weixin.qq.com/s/PgSSYc50ajnbh8zD6Ei07g)，这题如果使用递归的话，我们先来看一下递归的模板

```java
public ListNode getKthFromEnd(ListNode head, int k) {
    //终止条件
    if (head == null)
        return head;
    //递归调用
    ListNode node = getKthFromEnd(head.next, k);
    //逻辑处理
    ……
}
```

终止条件很明显就是当节点head为空的时候，就没法递归了，这里主要看的是逻辑处理部分，当递归往下传递到最底端的时候，就会触底反弹往回走，在往回走的过程中记录下走过的节点，当达到k的时候，说明到达的那个节点就是倒数第k个节点，直接返回即可，如果没有达到k，就返回空，搞懂了上面的过程，代码就很容易写了

```java
//全局变量，记录递归往回走的时候访问的结点数量
int size;

public ListNode getKthFromEnd(ListNode head, int k) {
    //边界条件判断
    if (head == null)
        return head;
    ListNode node = getKthFromEnd(head.next, k);
    ++size;
    //从后面数结点数小于k，返回空
    if (size < k) {
        return null;
    } else if (size == k) {
        //从后面数访问结点等于k，直接返回传递的结点k即可
        return head;
    } else {
        //从后面数访问的结点大于k，说明我们已经找到了，
        //直接返回node即可
        return node;
    }
}
```

上面代码在仔细一看，当size小于k的时候node节点就是空，所以我们可以把size大于k和小于k合并为一个，这样代码会更简洁一些

```java
int size;

public ListNode getKthFromEnd(ListNode head, int k) {
    if (head == null)
        return head;
    ListNode node = getKthFromEnd(head.next, k);
    if (++size == k)
        return head;
    return node;
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（21. 调整数组顺序使奇数位于偶数前面）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer21.md)

#### [下一题（24. 反转链表）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer24.md)