## [剑指 Offer 10. 青蛙跳台阶问题-II](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)（简单）

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

<br/>

**示例 1：**

```
输入：n = 2
输出：2
```

**示例 2：**

```
输入：n = 7
输出：21
```

**提示：**

- 0 <= n <= 100

<br/>

### 答案：

这题和[上一题（10. 斐波那契数列-I）](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-I.md)很相似，也可以看下

我们来分析一下：

当n等于1的时候，只需要跳一次即可，只有一种跳法，记f(1)=1

当n等于2的时候，可以先跳一级再跳一级，或者直接跳二级，共有2种跳法，记f(2)=2

当n等于3的时候，他可以从一级台阶上跳两步上来，也可以从二级台阶上跳一步上来，所以总共有f(3)=f(2)+f(1)；

同理当等于n的时候，总共有f(n)=f(n-1)+f(n-2)(这里n>2)种跳法。

![image.png](https://pic.leetcode-cn.com/c8caf15fa204cac8c917b50ffb0257e3cdc0bbf1ea78388ce1208c962b562a2a-image.png)

所以如果我们求上到n阶有多少种，代码很简单，直接递归就行

```java
    public static int f(int n) {
        if (n <= 1)
            return 1;
        if (n < 3)
            return n;
        return f(n - 1) + f(n - 2);
    }
```

但递归由于重复计算，导致效率很差，我们来看一下，有很多重复的计算，相同的颜色表示计算多次

![image.png](https://pic.leetcode-cn.com/bad04b5001b64a098a27081084573db985bf760738cfa8e64a6b250d16dab2d8-image.png)

所以我们要把他给为非递归的

```java
public int numWays(int n) {
    if (n <= 1)
        return 1;
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```

由于这题要求的是结果要对1000000007求余，所以正确答案是

```java
    public int numWays(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }
```



#### [上一题（10. 斐波那契数列-I）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-I.md)

#### [下一题（11. 旋转数组的最小数字）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer11.md)