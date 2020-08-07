## [剑指 Offer 9. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)（简单）

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

<br/>

**示例 1：**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例 2：**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

<br/>

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`

<br/>

### 答案：

做这题之前我们首先要明白一点就是，**栈是先进后出的，队列是先进先出的**。我们可以使用两个栈stackPop和stackPush，往队列中添加元素的时候直接把要添加的值压入到stackPush栈中。往队列中删除元素的时候如果stackPop中有元素我们就直接删除，如果没有元素，我们需要把stackPush中的元素全部出栈放到stackPop中，然后再删除stackPop中的元素。这样做的目的我们就可以保证stackPop中的元素永远都是比stackPush中的元素更老。

```java
class CQueue {
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public CQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void appendTail(int value) {
        stackPush.push(value);
    }

    public int deleteHead() {
        if (stackPop.isEmpty())
            while (!stackPush.isEmpty())
                stackPop.push(stackPush.pop());
        return stackPop.isEmpty() ? -1 : stackPop.pop();
    }
}
```

![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（7. 重建二叉树）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer07.md)

#### [下一题（10. 斐波那契数列-I）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-I.md)