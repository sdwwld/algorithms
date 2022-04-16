## [剑指 Offer 31. 栈的压入、弹出序列](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)（中等）

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

<br/>

**示例 1：**

```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```

**示例 2：**

```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```

<br/>

**提示：**

1. 0 <= pushed.length == popped.length <= 1000
2. 0 <= pushed[i], popped[i] < 1000
3. pushed 是 popped 的排列。。

<br/>

### 答案：

这题主要考察对栈的理解，**栈是一种先进后出的数据结构**，如果栈顶元素不出栈，那么栈顶元素下面的元素都是不能出栈的。

一种解决方式就是```把pushed数组的元素逐个压栈，当栈顶元素等于popped数组中第一个元素的时候，就让栈顶元素出栈，这个时候再用popped数组的第2个元素和栈顶元素比较，如果相同继续出栈……```，最后判断栈是否为空即可，来看下代码

```java
public boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack<Integer> stack = new Stack<>();
    int index = 0;
    for (int val : pushed) {
        //pushed数组中的元素逐个压栈
        stack.push(val);
        while (!stack.empty() && stack.peek() == popped[index]) {
            stack.pop();
            index++;
        }
    }
    return stack.empty();
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（30. 包含min函数的栈）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer30.md)

#### [下一题（32. 从上到下打印二叉树-I）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer32-I.md)