## [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)（简单）

反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

<br/>

### 答案：

#### 1，使用栈解决

链表的反转是老生常谈的一个问题了，同时也是面试中常考的一道题。最简单的一种方式就是使用**栈**，因为**栈是先进后出**的。实现原理就是把**链表节点一个个入栈，当全部入栈完之后再一个个出栈，出栈的时候在把出栈的结点串成一个新的链表**。原理如下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHac4ba8uZgNN7pmWsxcqib9VUtSm4KJKrHFRlXhFRTkMJ6fEOI5ajicCiakSymq6lGfkMxsIvCVYgibQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

代码比较简单，来看下

```java
public ListNode reverseList(ListNode head) {
    Stack<ListNode> stack = new Stack<>();
    //把链表节点全部摘掉放到栈中
    while (head != null) {
        stack.push(head);
        head = head.next;
    }
    if (stack.isEmpty())
        return null;
    ListNode node = stack.pop();
    ListNode dummy = node;
    //栈中的结点全部出栈，然后重新连成一个新的链表
    while (!stack.isEmpty()) {
        ListNode tempNode = stack.pop();
        node.next = tempNode;
        node = node.next;
    }
    //最后一个结点就是反转前的头结点，一定要让他的next
    //等于空，否则会构成环
    node.next = null;
    return dummy;
}
```



<br/>

#### 2，双链表求解

双链表求解是把原链表的结点一个个摘掉，**每次摘掉的链表都让他成为新的链表的头结点**，然后更新新链表。下面以链表`1→2→3→4`为例画个图来看下。

![image.png](https://pic.leetcode-cn.com/0aff36d2713c964b35c8b90e62c2fa1c16a6dcae372d991724b7b56c46a53870-image.png)

![image.png](https://pic.leetcode-cn.com/740ff8077ab684e38570d6f4240451834664cfc91b42ff2c5bca3c2d7ed73f31-image.png)

他每次访问的原链表节点都会成为新链表的头结点，最后再来看下代码

```java
public ListNode reverseList(ListNode head) {
    //新链表
    ListNode newHead = null;
    while (head != null) {
        //先保存访问的节点的下一个节点，保存起来
        //留着下一步访问的
        ListNode temp = head.next;
        //每次访问的原链表节点都会成为新链表的头结点，
        //其实就是把新链表挂到访问的原链表节点的
        //后面就行了
        head.next = newHead;
        //更新新链表
        newHead = head;
        //重新赋值，继续访问
        head = temp;
    }
    //返回新链表
    return newHead;
}
```



<br/>

#### 3，递归解决

我们再来回顾一下递归的模板，终止条件，递归调用，逻辑处理。

```java
public ListNode reverseList(参数0) {
    if (终止条件)
        return;

    逻辑处理（可能有，也可能没有，具体问题具体分析）

    //递归调用
    ListNode reverse = reverseList(参数1);

    逻辑处理（可能有，也可能没有，具体问题具体分析）
}
```

终止条件就是链表为空，或者是链表没有尾结点的时候，直接返回

```java
if (head == null || head.next == null)
    return head;
```

递归调用是要从当前节点的下一个结点开始递归。逻辑处理这块是要把当前节点挂到递归之后的链表的末尾，看下代码

```java
public ListNode reverseList(ListNode head) {
    //终止条件
    if (head == null || head.next == null)
        return head;
    //保存当前节点的下一个结点
    ListNode next = head.next;
    //从当前节点的下一个结点开始递归调用
    ListNode reverse = reverseList(next);
    //reverse是反转之后的链表，因为函数reverseList
    // 表示的是对链表的反转，所以反转完之后next肯定
    // 是链表reverse的尾结点，然后我们再把当前节点
    //head挂到next节点的后面就完成了链表的反转。
    next.next = head;
    //这里head相当于变成了尾结点，尾结点都是为空的，
    //否则会构成环
    head.next = null;
    return reverse;
}
```

因为递归调用之后head.next节点就会成为reverse节点的尾结点，我们可以直接让head.next.next = head;，这样代码会更简洁一些，看下代码

```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null)
        return head;
    ListNode reverse = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return reverse;
}
```



这种递归往下传递的时候基本上没有逻辑处理，当往回反弹的时候才开始处理，也就是从链表的尾端往前开始处理的。我们还可以再来改一下，在链表递归的时候从前往后处理，处理完之后直接返回递归的结果，这就是所谓的**尾递归**，这种运行效率要比上一种好很多

```java
public ListNode reverseList(ListNode head) {
    return reverseListInt(head, null);
}

private ListNode reverseListInt(ListNode head, ListNode newHead) {
    if (head == null)
        return newHead;
    ListNode next = head.next;
    head.next = newHead;
    return reverseListInt(next, head);
}
```

尾递归虽然也会不停的压栈，但由于最后返回的是递归函数的值，所以在返回的时候都会一次性出栈，不会一个个出栈这么慢。但如果我们再来改一下，像下面代码这样又会一个个出栈了

```java
public ListNode reverseList(ListNode head) {
    return reverseListInt(head, null);
}

private ListNode reverseListInt(ListNode head, ListNode newHead) {
    if (head == null)
        return newHead;
    ListNode next = head.next;
    head.next = newHead;
    ListNode node = reverseListInt(next, head);
    return node;
}
```

参照：

[剑指 Offer 24. 反转链表](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer24.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 