## [剑指 Offer 25. 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)（简单）

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

**示例1：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```



**限制：**

0 <= 链表长度 <= 1000

<br/>

### 答案：

因为链表是升序的，我们只需要遍历每个链表的头，比较一下哪个小就把哪个链表的头拿出来放到新的链表中，一直这样循环，直到有一个链表为空，然后我们再把另一个不为空的链表挂到新的链表中。
我们就以```3→4→7→9```和```2→5→6```两个链表来画个图看一下是怎么合并的。

![image.png](https://pic.leetcode-cn.com/cf5248a6f96113d0acf8150a5bed0d9d7e6bc64edae4e8f635e70f5edb62cc64-image.png)

![image.png](https://pic.leetcode-cn.com/73212b981525a9d6f2667c89bf9376a94d3c7e4dd0c5b375589d3e986248f335-image.png)

![image.png](https://pic.leetcode-cn.com/549b9fe49e8a48019cd1628dda2582763d9bc899693f3540c051a898cecf55c3-image.png)

看明白了上面的图，代码就很容易写了，我们来看一下非递归的代码

```java
    public ListNode mergeTwoLists(ListNode linked1, ListNode linked2) {
        //下面4行是空判断
        if (linked1 == null)
            return linked2;
        if (linked2 == null)
            return linked1;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (linked1 != null && linked2 != null) {
            //比较一下，哪个小就把哪个放到新的链表中
            if (linked1.val <= linked2.val) {
                curr.next = linked1;
                linked1 = linked1.next;
            } else {
                curr.next = linked2;
                linked2 = linked2.next;
            }
            curr = curr.next;
        }
        //然后把那个不为空的链表挂到新的链表中
        curr.next = linked1 == null ? linked2 : linked1;
        return dummy.next;
    }
```

看下运行结果

![image.png](https://pic.leetcode-cn.com/a1454c7f78e2d0cdd89558a99cb242935abcb6c8e622120dcf61f1d78349a1f6-image.png)

我们还可以把它改为递归的形式，看下递归的代码

```java
    public ListNode mergeTwoLists(ListNode linked1, ListNode linked2) {
        if (linked1 == null)
            return linked2;
        if (linked2 == null)
            return linked1;
        if (linked1.val < linked2.val) {
            linked1.next = mergeTwoLists(linked1.next, linked2);
            return linked1;
        } else {
            linked2.next = mergeTwoLists(linked1, linked2.next);
            return linked2;
        }
    }
```

递归代码我们还可以再改一下

```java
    public ListNode mergeTwoLists(ListNode linked1, ListNode linked2) {
        //只要有一个为空，就返回另一个
        if (linked1 == null || linked2 == null)
            return linked2 == null ? linked1 : linked2;
        //把小的赋值给first
        ListNode first = (linked2.val < linked1.val) ? linked2 : linked1;
        first.next = mergeTwoLists(first.next, first == linked1 ? linked2 : linked1);
        return first;
    }
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（24. 反转链表）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer24.md)

#### [下一题（26. 树的子结构）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer26.md)