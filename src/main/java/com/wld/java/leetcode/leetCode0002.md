## [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)（中等）

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```



### 答案：

**1，java的非递归实现**

我们可以把它想象为两个字符串相加，其实原理都是一样的，具体我们可以参照[389，两个超级大数相加](https://mp.weixin.qq.com/s/fbCw49WLo0FImAIFl4pinQ)

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode prev = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;//求两个节点相加的值
            carry = sum / 10;//取进位的值
            prev.next = new ListNode(sum % 10);//sum对10求余后放到节点中
            prev = prev.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }
        return head.next;
    }
```

 **2，java的递归实现**

我们还可以把非递归改为递归的方式，代码也比较简单

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        helper(head, l1, l2, 0);
        return head.next;
    }

    private void helper(ListNode result, ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0)
            return;
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
        result.next = new ListNode(0);
        result.next.val = sum % 10;
        carry = sum / 10;
        helper(result.next, l1 != null ? l1.next : null, l2 != null ? l2.next : null, carry);
    }
```



#### [上一题（1. 两数之和）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0001.md)(简单)

#### [下一题3. 无重复字符的最长子串(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0003.md)(中等)