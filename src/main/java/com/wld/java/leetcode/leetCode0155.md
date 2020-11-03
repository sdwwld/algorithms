## [155. 最小栈](https://leetcode-cn.com/problems/min-stack/)（简单）

设计一个支持 `push` ，`pop` ，`top` 操作，并能在常数时间内检索到最小元素的栈。

- push(x) —— 将元素 x 推入栈中。
- pop() —— 删除栈顶的元素。
- top() —— 获取栈顶元素。
- getMin() —— 检索栈中的最小元素。

<br/>

**示例:**

```
输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```

<br/>

**提示：**

- `pop`、`top` 和 `getMin` 操作总是在 **非空栈** 上调用。

<br/>

### 答案：

#### 1，使用辅助类解决

这道题让我们自定义一个栈，有push，pop，top，min四个函数。这题和官方的Stack相比就多了一个min函数。栈的实现我们可以使用链表，先来定义一个链表类

```java
class ListNode {
    public int val;
    public int min;//最小值
    public ListNode next;

    public ListNode(int val, int min, ListNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
```

这里对链表的操作永远都是链表的头，假如往栈中加入```3→2→5→4```，画个图来看一下使用链表怎么操作的

![image.png](https://pic.leetcode-cn.com/1597976534-pvpkHd-image.png)

代码比较简单，来看下

```java
class MinStack {
    //链表头，相当于栈顶
    private ListNode head;

    //压栈，需要判断栈是否为空
    public void push(int x) {
        if (empty())
            head = new ListNode(x, x, null);
        else
            head = new ListNode(x, Math.min(x, head.min), head);
    }

    //出栈，相当于把链表头删除
    public void pop() {
        if (empty())
            throw new IllegalStateException("栈为空……");
        head = head.next;
    }

    //栈顶的值也就是链表头的值
    public int top() {
        if (empty())
            throw new IllegalStateException("栈为空……");
        return head.val;
    }

    //链表中头结点保存的是整个链表最小的值，所以返回head.min也就是
    //相当于返回栈中最小的值
    public int min() {
        if (empty())
            throw new IllegalStateException("栈为空……");
        return head.min;
    }

    //判断栈是否为空
    private boolean empty() {
        return head == null;
    }
}

class ListNode {
    public int val;
    public int min;//最小值
    public ListNode next;

    public ListNode(int val, int min, ListNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
```

<br/>

上面解决方式是使用一个辅助的类，实际上如果使用辅助类，我们也可以使用官方提供的栈，像下面这样。

```java
class MinStack {
    private Stack<StackNode> stack = new Stack<>();

    //压栈
    public void push(int x) {
        if (empty()) {
            stack.push(new StackNode(x, x));
        } else {
            stack.push(new StackNode(x, Math.min(x, min())));
        }
    }

    //出栈
    public void pop() {
        if (empty())
            throw new IllegalStateException("栈为空……");
        stack.pop();
    }

    public int top() {
        if (empty())
            throw new IllegalStateException("栈为空……");
        return stack.peek().val;
    }

    public int min() {
        if (empty())
            throw new IllegalStateException("栈为空……");
        return stack.peek().min;
    }

    //判断栈是否为空
    private boolean empty() {
        return stack.isEmpty();
    }
}

class StackNode {
    public int val;
    public int min;

    public StackNode(int val, int min) {
        this.val = val;
        this.min = min;
    }
}
```



<br/>

#### 2，使用单个栈解决

也可以使用官方提供的栈，**当压栈的值小于栈中最小值时，先把最小值入栈，然后再把需要压栈的值入栈，最后再更新栈中最小值。如果压栈的值大于栈中最小值的时候，直接压栈**，这里就以[6，2，1，4]分别入栈来看一下

![image.png](https://pic.leetcode-cn.com/1597976736-zVWicM-image.png)

![image.png](https://pic.leetcode-cn.com/1597976746-snOkZl-image.png)

![image.png](https://pic.leetcode-cn.com/1597976753-raKQBr-image.png)

这是压栈的过程，出**栈的时候如果出栈的值等于最小值，说明最小值已经出栈了，要更新最小值**，估计直接看代码会更明白一些

```java
class MinStack {//push方法可能会加入很多min
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        //如果加入的值小于最小值，要更新最小值
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //如果把最小值出栈了，就更新最小值
        if (stack.pop() == min)
            min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}
```

这种方式虽然也能解决，但如果压栈的值一直递减的话，栈中会压入很多的min，实际上我们还可以在改一下，**栈中压入的是需要压栈的值和最小值的差值**，这样就不会压入min了，看下代码

```java
public class MinStack {
    long min;
    Stack<Long> stack = new Stack<>();

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        } else {
            //这里入栈的是入栈的值和最小值的差值，有可能为负，也有可能为正。
            stack.push(x - min);
            if (x < min)
                min = x;
        }
    }

    public void pop() {
        if (stack.isEmpty())
            return;
        long pop = stack.pop();
        //因为入栈的是差值，当出栈的为负数的时候，说明栈中最小值已经出栈了，
        //这里要重新更新最小值
        if (pop < 0)
            min -= pop;
    }

    public int top() {
        long top = stack.peek();
        if (top > 0) {
            //栈顶元素如果是正的，说明栈顶元素压栈的时候是比栈中最小值大的，根据
            //top=x - min，可以计算x=top+min
            return (int) (top + min);
        } else {
            //当栈顶元素是负数的时候，说明栈顶元素压栈的时候是比栈中最小值小的，
            //而压栈完之后他会更新最小值min，所以如果在使用上面公式肯定是不行
            //的。如果栈顶元素压栈的时候比最小值小，他会更新最小值，这个最小值
            //就是我们要压栈的值，所以这里直接返回min就行了。
            return (int) (min);
        }
    }

    public int min() {
        return (int) min;
    }
}
```

<br/>

#### 3，使用双栈解决

这个代码比较简洁，就不在说了，直接看下代码

```java
class MinStack {
    //栈1存放的是需要压栈的值
    Stack<Integer> stack1 = new Stack<>();
    //栈2存放的是最小值
    Stack<Integer> stack2 = new Stack<>();

    public void push(int x) {
        stack1.push(x);
        if (stack2.empty() || x <= min())
            stack2.push(x);
    }

    public void pop() {
        //如果出栈的值等于最小值，说明栈中的最小值
        //已经出栈了，因为stack2中的栈顶元素存放的
        //就是最小值，所以stack2栈顶元素也要出栈
        if (stack1.pop() == min())
            stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }
}
```

参照：

[剑指 Offer 30. 包含min函数的栈](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer30.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 