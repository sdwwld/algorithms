## [剑指 Offer 52. 两个链表的第一个公共节点](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)（简单）

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表**：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

<br>

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

<br>

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png)

```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

<br>

**示例 3：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```

<br>

**注意：**

- 如果两个链表没有交点，返回 null.
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

<br>

### 答案：

#### 1，通过集合set解决

上面说了一大堆，其实就是**判断两个链表是否相交，如果相交就返回他们的相交的交点，如果不相交就返回null。**

<br>

做这题最容易想到的一种解决方式就是先把**第一个链表的节点全部存放到集合set中，然后遍历第二个链表的每一个节点，判断在集合set中是否存在，**如果存在就直接返回这个存在的结点。如果遍历完了，在集合set中还没找到，说明他们没有相交，直接返回null即可，原理比较简单，直接看下代码

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //创建集合set
    Set<ListNode> set = new HashSet<>();
    //先把链表A的结点全部存放到集合set中
    while (headA != null) {
        set.add(headA);
        headA = headA.next;
    }

    //然后访问链表B的结点，判断集合中是否包含链表B的结点，如果包含就直接返回
    while (headB != null) {
        if (set.contains(headB))
            return headB;
        headB = headB.next;
    }
    //如果集合set不包含链表B的任何一个结点，说明他们没有交点，直接返回null
    return null;
}
```

<br>

#### 2，先统计两个链表的长度

还可以先统计两个链表的长度，如果两个链表的长度不一样，就让链表长的先走，直到两个链表长度一样，这个时候两个链表再同时每次往后移一步，看节点是否一样，如果有相等的，说明这个相等的节点就是两链表的交点，否则如果走完了还没有找到相等的节点，说明他们没有交点，直接返回null即可，来画个图看一下。

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/offer/0052/640.png)

最后再来看下代码

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //统计链表A和链表B的长度
    int lenA = length(headA), lenB = length(headB);

    //如果节点长度不一样，节点多的先走，直到他们的长度一样为止
    while (lenA != lenB) {
        if (lenA > lenB) {
            //如果链表A长，那么链表A先走
            headA = headA.next;
            lenA--;
        } else {
            //如果链表B长，那么链表B先走
            headB = headB.next;
            lenB--;
        }
    }

    //然后开始比较，如果他俩不相等就一直往下走
    while (headA != headB) {
        headA = headA.next;
        headB = headB.next;
    }
    //走到最后，最终会有两种可能，一种是headA为空，
    //也就是说他们俩不相交。还有一种可能就是headA
    //不为空，也就是说headA就是他们的交点
    return headA;
}

//统计链表的长度
private int length(ListNode node) {
    int length = 0;
    while (node != null) {
        node = node.next;
        length++;
    }
    return length;
}
```

<br>

#### 3，双指针解决

我们还可以使用两个指针，最开始的时候一个指向链表A，一个指向链表B，然后他们每次都要往后移动一位，顺便查看节点是否相等。如果链表A和链表B不相交，基本上没啥可说的，我们**这里假设链表A和链表B相交**。那么就会有两种情况，

<br>

一种是链表A的长度和链表B的长度相等，他们每次都走一步，最终在相交点肯定会相遇。

<br>

一种是链表A的长度和链表B的长度不相等，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/offer/0052/641.png)

虽然他们有交点，但他们的长度不一样，所以他们完美的错开了，即使把链表都走完了也找不到相交点。

<br>

我们仔细看下上面的图，**如果A指针把链表A走完了，然后再从链表B开始走到相遇点就相当于把这两个链表的所有节点都走了一遍**，同理如果B指针把链表B走完了，然后再从链表A开始一直走到相遇点也相当于把这两个链表的所有节点都走完了

<br>

所以如果A指针走到链表末尾，下一步就让他从链表B开始。同理如果B指针走到链表末尾，下一步就让他从链表A开始。只要这两个链表相交最终肯定会在相交点相遇，如果不相交，最终他们都会同时走到两个链表的末尾，我们来画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/offer/0052/642.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/offer/0052/643.png)

如上图所示，A指针和B指针如果一直走下去，那么他们最终会在相交点相遇，最后再来看下代码

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //tempA和tempB我们可以认为是A,B两个指针
    ListNode tempA = headA;
    ListNode tempB = headB;
    while (tempA != tempB) {
        //如果指针tempA不为空，tempA就往后移一步。
        //如果指针tempA为空，就让指针tempA指向headB（注意这里是headB不是tempB）
        tempA = tempA == null ? headB : tempA.next;
        //指针tempB同上
        tempB = tempB == null ? headA : tempB.next;
    }
    //tempA要么是空，要么是两链表的交点
    return tempA;
}
```

**注意**：这里所说的指针并不是C语言中的指针，在这里其实他就是一个变量，千万不要搞混了。

<br>

#### 4，问题分析

第一种解法应该是都容易想到的，但效率不高，一个个存储，然后再拿另一个链表的节点一个个判断。最后一种解法没有统计链表的长度，而是当一个链表访问完如果没有找到相交点，就从下一个链表继续访问，一般不太容易想到，也算是比较经典的。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 