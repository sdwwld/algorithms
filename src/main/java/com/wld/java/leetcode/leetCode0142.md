## [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)（中等）

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。**注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。**

**说明**：不允许修改给定的链表。

<br/>

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

**示例 2：**

```
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

**示例 3：**

```
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)



<br/>

**提示：**

- 链表中节点的数目范围在范围 `[0, 10^4]` 内
- `-10^5 <= Node.val <= 10^5`
- `pos` 的值为 `-1` 或者链表中的一个有效索引



<br/>

### 答案：

#### 1，双指针解决

在前面讲过[449，快慢指针解决环形链表](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247488545&idx=2&sn=44882cc020d52168c38c831f42d4d336&chksm=fb418701cc360e174d7fca463383e3d3b4401a14a78a40238b63284872c6ae2d0c24cec7c279&scene=21#wechat_redirect)，判断一个链表是否是环形链表，而这题比第449题稍微麻烦一点，就是如果确定链表有环，还要找出环的入口。如果一个链表没有环，就不存在环的入口。这里主要来看一下，如果一个链表有环怎么找到他的入口。

<br>

对于链表组成的环一般有两种，一种是O型，一种是6型，其实原理都一样，这里主要看一下6字型的链表，他会有两种情况，一种是环很大，如下图所示。

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0142/640.png)

根据第449题的分析，通过快慢指针可以判断一个链表是否有环。如果有环，那么**快指针走过的路径就是图中a+b+c+b**，**慢指针走过的路径就是图中a+b**，因为在相同的时间内，快指针走过的路径是慢指针的2倍，所以这里有a+b+c+b=2*(a+b)，整理得到a=c，也就是说图中a的路径长度和c的路径长度是一样的。

<br>

在相遇的时候再使用两个指针，一个从链表起始点开始，一个从相遇点开始，每次他们都走一步，直到再次相遇，那么这个相遇点就是环的入口。

<br>

还有一种情况就是环很小，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0142/641.png)

这种情况下当快慢指针在环上相遇的时候，快指针有可能在环上转了好几圈，我们假设相遇的时候快指针已经在环上转了n圈。

<br>

那么相遇的时候**快指针走过的路径就是a+b+(b+c)\*n**，（b+c其实就是环的长度），**慢指针走过的路径是a+b**。由于相同时间内快指针走过的路径是慢指针的2倍。

<br>

所以有a+b+(b+c)\*n=2\*(a+b)，整理得到a+b=n\*(b+c)，也就是说a+b等于n个环的长度。

<br>

我们还可以使用两个指针，一个从链表的起点开始，一个从相遇点开始，那么就会出现一种情况就是，一个指针在路径a上走，一个指针一直在环上转圈，最终会走到第一种情况。

<br>

所以无论哪种情况我们都可以使用第一种方式解决，代码如下

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        //快慢指针，快指针每次走两步，慢指针每次走一步
        fast = fast.next.next;
        slow = slow.next;
        //先判断是否有环，
        if (slow == fast) {
            //确定有环之后才能找环的入口
            while (head != slow) {
                //两指针，一个从头结点开始，
                //一个从相遇点开始每次走一步，直到
                //再次相遇为止
                head = head.next;
                slow = slow.next;
            }
            return slow;
        }
    }
    return null;
}
```

<br>

#### 2，通过集合set解决

上面解法可能不是太好理解，这里再来看一种比较好理解的，就把结点一个个全部存放到集合set中，在存放的时候如果出现了重复的结点，说明这个链表是有环的，并且首次重复的这个节点就是环的入口

```java
public ListNode detectCycle(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    while (head != null) {
        //如果重复出现说明有环
        if (!set.add(head))
            return head;
        //否则就把当前节点加入到集合中
        head = head.next;
    }
    return null;
}
```

set的add方法表示往集合中添加元素，添加的时候如果没有重复的就会返回true，如果有重复的就会返回false。

<br>

#### 3，总结

这道题最容易想到的应该是最后一种解法，比较简单，也很容易理解。但这道题有个要求，就是不能修改链表的结构，如果没这要求的话，还可以参照[449，快慢指针解决环形链表](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247488545&idx=2&sn=44882cc020d52168c38c831f42d4d336&chksm=fb418701cc360e174d7fca463383e3d3b4401a14a78a40238b63284872c6ae2d0c24cec7c279&scene=21#wechat_redirect)的最后一种解法，就是把链表节点一个个删除。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 