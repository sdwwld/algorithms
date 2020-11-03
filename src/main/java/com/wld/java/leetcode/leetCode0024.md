## [24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)（中等）

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

**你不能只是单纯的改变节点内部的值**，而是需要实际的进行节点交换。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg)

```
输入：head = [1,2,3,4]
输出：[2,1,4,3]
```

**示例 2：**

```
输入：head = []
输出：[]
```

**示例 3：**

```
输入：head = [1]
输出：[1]
```

<br>

**提示：**

- 链表中节点的数目在范围 `[0, 100]` 内
- `0 <= Node.val <= 100`

<br>

### 答案：

#### 1，非递归解决

这题主要考察的是链表节点的交换，要交换链表的节点首先要搞懂链表节点的断开和连接，之前写过一篇文章，《[352，数据结构-2,链表](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486243&idx=1&sn=708f4f6bfd2237ec6b5b6bf685d116a0&chksm=fb419803cc361115e10b3f578a60c19c2941ae7a8ad11b84b5ecac4a024e16892b8b49cea58c&scene=21#wechat_redirect)》，图文详细介绍了单向链表，双向链表，还有环形链表的断开和链接。

<br>

这道题我们可以人为的把链表分为两组，前面两个节点为一组，后面的为一组，我们首先交换第一组的两个节点，交换完之后把后面的节点再分为两组，继续这样交换下去……，直到不能交换为止。注意，如果能交换，那么原来链表的第二个节点就是我们要返回新链表的头结点。

这里就以示例为例画个图来看下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0024/640.png)

再来看下代码

```java
public ListNode swapPairs(ListNode head) {
    //链表至少有2个节点才能交换，否则就不要交换
    if (head == null || head.next == null)
        return head;
    //这里的first，second，third可以参照图中的标注
    ListNode first = head;
    ListNode second;
    ListNode third;
    //这个是交换链表之后的尾结点，他的next要指向新交换的链表
    ListNode preLast = null;
    //这个只赋值一次，它是要返回的新链表的头结点
    ListNode newHead = head.next;
    //如果能交换就继续操作
    while (first != null && first.next != null) {
        //给second，third赋值
        second = first.next;
        third = second.next;
        //first和second这两个节点交换
        first.next = third;
        second.next = first;
        //这个时候second就是交换之后新链表的头结点，
        //如果preLast不为空，说明前面还有交换完成的链表
        //，要让preLast的next指向新链表的头结点
        if (preLast != null) {
            preLast.next = second;
        }
        //因为first和second交换之后，first就变成新链表
        //的尾结点了，把它保存在preLast中
        preLast = first;
        //前两个交换了，然后从第3个在继续操作
        first = third;
    }
    //返回新链表
    return newHead;
}
```

<br>

#### 2，递归解决

之前讲过《[426，什么是递归，通过这篇文章，让你彻底搞懂递归](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487910&idx=1&sn=2670aec7139c6b98e83ff66114ac1cf7&chksm=fb418286cc360b90741ed54fecd62fd45571b2caba3e41473a7ea0934f918d4b31537689c664&scene=21#wechat_redirect)》，递归有2个条件，一个是终止条件，一个是调用自己，并且要同时具备，所以这题的递归模板很容易写出来。

```java
public ListNode swapPairs(ListNode head) {
    //终止条件，如果节点为空或者没有后继节点，就不能交换
    if (head == null || head.next == null)
        return head;

    //一些逻辑运算，可有可无，视情况而定

    //前面两个节点我们保留不要动，从第3个节点往后开始
    //两两交换
    ListNode newListNode = swapPairs(head.next.next);

    //一些逻辑运算，可有可无，视情况而定

    return 交换之后的链表;
}
```

假如使用递归从第3个节点往后的节点全部两两交换了，这个时候我们可以把链表分为3部分，**第一个节点**，**第二个节点**和**后面交换完成的链表**，就是1→2→3，这种形式，我们只要再把1和2位置交换了，那么这道题就完成了。来看下代码

```java
public ListNode swapPairs(ListNode head) {
    //边界条件判断
    if (head == null || head.next == null)
        return head;
    //从第3个链表往后进行交换
    ListNode third = swapPairs(head.next.next);
    //从第3个链表往后都交换完了，我们只需要交换前两个链表即可,
    //这里我们把链表分为3组，分别是第1个节点，第2个节点，后面
    //的所有节点，也就是1→2→3，我们要把它变为2→1→3
    ListNode second = head.next;
    head.next = third;
    second.next = head;

    return second;
}
```

<br>

#### 3，交换节点的值

还可以换种思路，因为交换链表的结点需要不停的断开和连接，比较麻烦。所以我们还可以不交换链表的节点，直接交换链表节点的值即可，这样就非常简单了

```java
public ListNode swapPairs(ListNode head) {
    int first;
    ListNode temp = head;
    while (temp != null && temp.next != null) {
        //直接交换链表节点的值
        first = temp.val;
        temp.val = temp.next.val;
        temp.next.val = first;

        temp = temp.next.next;
    }
    return head;
}
```

<br>

#### 4，总结

链表节点的交换要注意，在交换前两个节点之前，要把第3个节点给先保存起来，否则交换完了就找不到第3个节点了。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

