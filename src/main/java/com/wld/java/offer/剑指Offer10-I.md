## [剑指 Offer 10. 斐波那契数列-I](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)（简单）

写一个函数，输入 `n` ，求斐波那契（Fibonacci）数列的第 `n` 项。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 <br/>

**示例 1：**

```
输入：n = 2
输出：1
```

**示例 2：**

```
输入：n = 5
输出：5
```

<br/>

**提示：**

- 0 <= n <= 100

<br/>

### 答案：

#### 1，递归解决

之前讲[356，青蛙跳台阶相关问题](https://mp.weixin.qq.com/s/hLpHLUfXsABzUNjuNflWzQ)的时候提到过斐波那契数列，代码比较简单

```java
public int fib(int n) {
    if (n < 2)
        return n;
    return fib(n - 1) + fib(n - 2);
}
```

当n很大的时候可能会出现数字溢出，所以我们需要用结果对1000000007求余，但实际上可能还没有执行到最后一步就已经溢出了，所以我们需要对每一步的计算都要对1000000007求余，代码如下

```java
int constant = 1000000007;

public int fib(int n) {
    if (n < 2)
        return n;
    int first = fib(n - 1) % constant;
    int second = fib(n - 2) % constant;
    return (first + second) % constant;
}
```

之前讲过斐波那契数列递归的时候会造成大量的重复计算，比如就计算fib(6)为例来看下

![image.png](https://pic.leetcode-cn.com/a6f819589ee5e50ec378f2c10835e9a510529bac44618444beb2202486062eee-image.png)

我们看到上面相同颜色的都是重复计算，当n越大，重复的越多，所以我们可以使用一个map把计算过的值存起来，每次计算的时候先看map中有没有，如果有就表示计算过，直接从map中取，如果没有就先计算，计算完之后再把结果存到map中。

```java
int constant = 1000000007;

public int fib(int n) {
    return fib(n, new HashMap());
}

public int fib(int n, Map<Integer, Integer> map) {
    if (n < 2)
        return n;
    if (map.containsKey(n))
        return map.get(n);
    int first = fib(n - 1, map) % constant;
    map.put(n - 1, first);
    int second = fib(n - 2, map) % constant;
    map.put(n - 2, second);
    int res = (first + second) % constant;
    map.put(n, res);
    return res;
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/0b31e037787b0db3cafeb1291272f9320a15745c646409efffc817d80bfeaa3a-image.png)

<br/>

#### 2，非递归解法

我们还可以把斐波那契递归改为非递归，代码很简单，可以看下

```java
public int fib(int n) {
    int constant = 1000000007;
    int first = 0;
    int second = 1;
    while (n-- > 0) {
        int temp = first + second;
        first = second % constant;
        second = temp % constant;
    }
    return first;
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/92483e711066fabe381cf189478d91f491013b952e9054de5827292898d1935f-image.png)



参照：

[10. 斐波那契数列-II](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-II.md)

[70. 爬楼梯](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0070.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（9. 用两个栈实现队列）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer09.md)

#### [下一题（10. 青蛙跳台阶问题-II）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-II.md)