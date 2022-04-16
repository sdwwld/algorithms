## [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)（简单）

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。**注意：pos 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

<br/>

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

**示例 2：**

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

**示例 3：**

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)

<br/>

**提示：**

- 链表中节点的数目范围是 `[0, 10^4]`
- `-10^5 <= Node.val <= 10^5`
- `pos` 为 `-1` 或者链表中的一个 **有效索引** 。



<br/>

### 答案：

#### 1，快慢指针解决

判断链表是否有环应该是老生常谈的一个话题了，最简单的一种方式就是快慢指针，**慢指针针每次走一步，快指针每次走两步**，如果相遇就说明有环，如果有一个为空说明没有环。代码比较简单

```java
public boolean hasCycle(ListNode head) {
    if (head == null)
        return false;
    //快慢两个指针
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
        //慢指针每次走一步
        slow = slow.next;
        //快指针每次走两步
        fast = fast.next.next;
        //如果相遇，说明有环，直接返回true
        if (slow == fast)
            return true;
    }
    //否则就是没环
    return false;
}
```

到这里问题好像并没有结束，为什么快慢指针就一定能判断是否有环。我们可以这样来思考一下，**假如有环，那么快慢指针最终都会走到环上**，假如环的长度是m，快慢指针最近的间距是n，如下图中所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0141/640.png)

快指针每次走两步，慢指针每次走一步，所以每走一次快慢指针的间距就要缩小一步，在图一中当走n次的时候就会相遇，在图二中当走m-n次的时候就会相遇。

<br>

#### 2，存放到集合中

这题还可以把节点存放到集合set中，每次存放的时候判断当前节点是否存在，如果存在，说明有环，直接返回true，比较容易理解

```java
public boolean hasCycle(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    while (head != null) {
        //如果重复出现说明有环
        if (set.contains(head))
            return true;
        //否则就把当前节点加入到集合中
        set.add(head);
        head = head.next;
    }
    return false;
}
```

<br>

#### 3，逐个删除

一个链表从头节点开始一个个删除，**所谓删除就是让他的next指针指向他自己**。如果没有环，从头结点一个个删除，最后肯定会删完，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0141/641.png)

如果是环形的，那么有两种情况，一种是o型的，一种是6型的。原理都是一样，我们就看一下o型的

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0141/642.png)

如上图所示，如果删到最后，肯定会出现**head=head.next；**

```java
public boolean hasCycle(ListNode head) {
    //如果head为空，或者他的next指向为空，直接返回false
    if (head == null || head.next == null)
        return false;
    //如果出现head.next = head表示有环
    if (head.next == head)
        return true;
    ListNode nextNode = head.next;
    //当前节点的next指向他自己，相当于把它删除了
    head.next = head;
    //然后递归，查看下一个节点
    return hasCycle(nextNode);
}
```

<br>

#### 4，总结

这题是很常见的一道题了。来思考这样一个问题，这里的快慢指针是快指针每次走2步，慢指针每次走1步。如果慢指针还是每次走1步，快指针每次走3步能不能判断。或者快指针每次走m步，慢指针每次都n步，并且m≠n，这种情况下能不能判断？



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 