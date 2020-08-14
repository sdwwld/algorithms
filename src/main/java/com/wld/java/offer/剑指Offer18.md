## [剑指 Offer 18. 删除链表的节点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)（简单）

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

**注意：**此题对比原题有改动

**示例 1:**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2:**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

<br/>

**说明：**

- 题目保证链表中节点的值互不相同
- 若使用 C 或 C++ 语言，你不需要 `free` 或 `delete` 被删除的节点

<br/>

### 答案：

**1，双指针求解**

题中说了**链表中的节点值互不相同**，也就是说最多只能删除一个。最简单的一种方式就是双指针求解，我们使用两个指针一个指向当前的节点，一个指向当前的上一个节点，以示例1为例画个图看下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFl3kl5KpUKrGu3QkLniatNfibrhDp9n7gXL98pQguqexnpH39icKHnlPGibeUEDziczZPFvFJHHSbR5bA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFl3kl5KpUKrGu3QkLniatNf4gp1MichiaRD3vyiaTl9QniaX9N6uiaowNtAamwKOJ1ZPQdD7rdUu2EPG3Q/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public ListNode deleteNode(ListNode head, int val) {
    //初始化一个虚拟节点
    ListNode dummy = new ListNode(0);
    //让虚拟节点指向头结点
    dummy.next = head;
    ListNode cur = head;
    ListNode pre = dummy;
    while (cur != null) {
        if (cur.val == val) {
            //如果找到要删除的结点，直接把他删除
            pre.next = cur.next;
            break;
        }
        //如果没找到，pre指针和cur指针都同时往后移一步
        pre = cur;
        cur = cur.next;
    }
    //最后返回虚拟节点的下一个结点即可
    return dummy.next;
}
```

上面代码添加虚拟节点是为了方便操作，当然不添加虚拟节点也是可以的，像下面这样

```java
public ListNode deleteNode(ListNode head, int val) {
    //边界条件判断
    if (head == null)
        return head;
    //如果要删除的是头结点，直接返回头结点的下一个结点即可
    if (head.val == val)
        return head.next;
    ListNode cur = head;
    //找到要删除结点的上一个结点
    while (cur.next != null && cur.next.val != val) {
        cur = cur.next;
    }
    //删除结点
    cur.next = cur.next.next;
    return head;
}
```

**2，递归解决**

之前讲过递归，[426，什么是递归，通过这篇文章，让你彻底搞懂递归](https://mp.weixin.qq.com/s/PgSSYc50ajnbh8zD6Ei07g)，提到递归的模板，我们看下

```java

public void recursion(参数0) {
    if (终止条件) {
        return;
    }

    可能有一些逻辑运算
    recursion(参数1)
    可能有一些逻辑运算
    recursion(参数2)
            ……
    recursion(参数n)
    可能有一些逻辑运算
}
```

我们来定义一个递归的函数

**public ListNode deleteNode(ListNode head, int val)，**

他表示的是删除链表中值等于val的结点，那么递归的终止条件就是当head等于空的时候，我们直接返回head，因为一个空的链表我们是没法删除的，也就是下面这样

```java
if (head == null)
    return head;
```

如果head结点不等于空，并且head结点的值等于val，我们直接返回head结点的下一个结点

```java
if (head.val == val)
    return head.next;
```

否则也就是说头结点是删不掉的，我们就递归调用，从头结点的下一个开始继续上面的操作，直到删除为止。

```java
head.next = deleteNode(head.next, val);
return head;
```

完整代码如下

```java
public ListNode deleteNode(ListNode head, int val) {
    if (head == null)
        return head;
    if (head.val == val)
        return head.next;
    head.next = deleteNode(head.next, val);
    return head;
}
```



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（17. 打印从1到最大的n位数）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer17.md)

#### [下一题（19. 正则表达式匹配）(困难)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer19.md)