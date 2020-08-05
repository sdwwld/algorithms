## [剑指 Offer 6. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)（简单）

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。



**示例 1：**

```
输入：head = [1,3,2]
输出：[2,3,1]
```



**限制：**

```
0 <= 链表长度 <= 10000
```



### 答案：

#### 1，使用栈来解决

从尾到头打印链表，首先这个链表是单向的，如果是双向的，直接从后往前打印就行了，这里链表不是单向的。

这里最容易想到的一种方式就是把链表的节点全部压栈，因为栈是先进后出的一种数据结构，全部压栈之后再一个



个出栈即可，

































#### [上一题（5. 替换空格）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer05.md)

#### [下一题（7. 重建二叉树）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer07.md)