## [234. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)（简单）

请判断一个链表是否为回文链表。

**示例 1:**

```
输入: 1->2
输出: false
```

**示例 2:**

```
输入: 1->2->2->1
输出: true
```

<br/>

### 答案：

#### 1，反转后半部分链表

这题是让判断链表是否是回文链表，**所谓的回文链表就是以链表中间为中心点两边对称**。我们常见的有判断一个字符串是否是回文字符串，这个比较简单，可以使用两个指针，一个最左边一个最右边，两个指针同时往中间靠，判断所指的字符是否相等。

<br>

但这题判断的是链表，因为这里是单向链表，只能从前往后访问，不能从后往前访问，所以使用判断字符串的那种方式是行不通的。但我们可以**通过找到链表的中间节点然后把链表后半部分反转**（关于链表的反转可以看下[432，剑指 Offer-反转链表的3种方式](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247488340&idx=1&sn=c3d6adc9f737672aab544931502dda2e&chksm=fb418074cc360962b46cb764068a5818f58bed6a4cd05ef61057823918d95f3a192550f02408&scene=21#wechat_redirect)），**最后再用后半部分反转的链表和前半部分一个个比较即可**。这里以示例2为例画个图看一下。

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0234/640.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0234/641.png)

最后再来看下代码

```java
public boolean isPalindrome(ListNode head) {
    ListNode fast = head, slow = head;
    //通过快慢指针找到中点
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    //如果fast不为空，说明链表的长度是奇数个
    if (fast != null) {
        slow = slow.next;
    }
    //反转后半部分链表
    slow = reverse(slow);

    fast = head;
    while (slow != null) {
        //然后比较，判断节点值是否相等
        if (fast.val != slow.val)
            return false;
        fast = fast.next;
        slow = slow.next;
    }
    return true;
}

//反转链表
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```

<br>

#### 2，使用栈解决

我们知道**栈是先进后出的一种数据结构**，这里还可以使用栈先把链表的节点全部存放到栈中，然后再一个个出栈，这样就相当于链表从后往前访问了，通过这种方式也能解决，看下代码

```java
public boolean isPalindrome(ListNode head) {
    ListNode temp = head;
    Stack<Integer> stack = new Stack();
    //把链表节点的值存放到栈中
    while (temp != null) {
        stack.push(temp.val);
        temp = temp.next;
    }

    //然后再出栈
    while (head != null) {
        if (head.val != stack.pop()) {
            return false;
        }
        head = head.next;
    }
    return true;
}
```

这里相当于链表从前往后全部都比较了一遍，其实我们只需要拿链表的后半部分和前半部分比较即可，没必要全部比较，所以这里可以优化一下

```java
public boolean isPalindrome(ListNode head) {
    if (head == null)
        return true;
    ListNode temp = head;
    Stack<Integer> stack = new Stack();
    //链表的长度
    int len = 0;
    //把链表节点的值存放到栈中
    while (temp != null) {
        stack.push(temp.val);
        temp = temp.next;
        len++;
    }
    //len长度除以2
    len >>= 1;
    //然后再出栈
    while (len-- >= 0) {
        if (head.val != stack.pop())
            return false;
        head = head.next;
    }
    return true;
}
```

<br>

#### 3，递归方式解决

我们知道，如果对链表逆序打印可以这样写

```java
private void printListNode(ListNode head) {
    if (head == null)
        return;
    printListNode(head.next);
    System.out.println(head.val);
}
```

也就是说最先打印的是链表的尾结点，他是从后往前打印的，看到这里是不是有灵感了，我们来对上面的对面进行改造一下

```java
ListNode temp;

public boolean isPalindrome(ListNode head) {
    temp = head;
    return check(head);
}

private boolean check(ListNode head) {
    if (head == null)
        return true;
    boolean res = check(head.next) && (temp.val == head.val);
    temp = temp.next;
    return res;
}
```

<br>

#### 4，问题分析

回文链表的判断，相比回文字符串的判断稍微要麻烦一点，但难度也不是很大，如果对链表比较熟悉的话，这3种解决方式都很容易想到，如果不熟悉的话，可能最容易想到的就是第2种了，也就是栈和链表的结合。

<br>

如果对栈和链表不熟悉的话，可以看下[352，数据结构-2,链表](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486243&idx=1&sn=708f4f6bfd2237ec6b5b6bf685d116a0&chksm=fb419803cc361115e10b3f578a60c19c2941ae7a8ad11b84b5ecac4a024e16892b8b49cea58c&scene=21#wechat_redirect)，这里详细介绍了**单向链表，双向链表，以及环形链表的断开和连接**。也可以看下[363，数据结构-4,栈](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486485&idx=1&sn=b1a4a49c79703bd4fad69d8dfc40f097&chksm=fb419f35cc3616235c2b6a9c7839f0b5f3e52e80f3c9eec4b4dfd14d89915b6d990055bd4917&scene=21#wechat_redirect)，这里有对栈的一些简单介绍和实例讲解。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

