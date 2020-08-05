## [剑指 Offer 6. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)（简单）

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

<br/>

**示例 1：**

```
输入：head = [1,3,2]
输出：[2,3,1]
```

<br/>

**限制：**

```
0 <= 链表长度 <= 10000
```

<br/>

### 答案：

#### 1，使用栈来解决

从尾到头打印链表，首先这个链表是单向的，如果是双向的，直接从后往前打印就行了，这里链表不是单向的。

这里最容易想到的一种方式就是把链表的节点全部压栈，因为栈是先进后出的一种数据结构，全部压栈之后再一个个出栈即可，

![image.png](https://pic.leetcode-cn.com/25440a620b76a3345266bc83fe15ba46f2bb93affd95d69e7546c048258892e1-image.png)

压栈完之后再一个个出栈

```java
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop().val;
        }
        return res;
    }
```

看一下运行结果，效率不是很高

![image.png](https://pic.leetcode-cn.com/a741d742891f354dc427d73586cee65d1cbfb81bf5d8d23c798881889f99420e-image.png)

<br/>

#### 2，递归方式解决

我们知道如果逆序打印一个链表使用递归的方式还是很简单的，像下面这样

```java
    public void reversePrint(ListNode head) {
        if (head == null)
            return;
        reversePrint(head.next);
        System.out.println(head.val);
    }
```

但这里实际上是要我们返回逆序打印的结果，也就是返回一个数组，所以我们可以改一下

```java
    public int[] reversePrint(ListNode head) {
        int cout = length(head);
        int[] res = new int[cout];
        reversePrintHelper(head, res, cout - 1);
        return res;
    }

    //计算链表的长度
    public int length(ListNode head) {
        int cout = 0;
        ListNode dummy = head;
        while (dummy != null) {
            cout++;
            dummy = dummy.next;
        }
        return cout;
    }

    public void reversePrintHelper(ListNode head, int[] res, int index) {
        if (head == null)
            return;
        reversePrintHelper(head.next, res, index - 1);
        res[index] = head.val;
    }
```

再来看一下运行结果

![image.png](https://pic.leetcode-cn.com/2e41dd0edb69b1401d072ca36fed0bd2467cca7cc0648de65267f540414aa233-image.png)

<br/>

#### 3，先反转链表

关于链表的反转其实解法也比较多，这里先列出简单的两种，一个是递归的，一个是非递归的。

**递归的方式**

```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null)
        return head;
    ListNode tempList = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return tempList;
}
```

**非递归的方式**

```java
public ListNode reverseList(ListNode head) {
    ListNode pre = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = pre;
        pre = head;
        head = next;
    }
    return pre;
}
```

##### 链表反转之后在打印就方便多了，这里就不在写了。



#### [上一题（5. 替换空格）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer05.md)

#### [下一题（7. 重建二叉树）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer07.md)