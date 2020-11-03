## [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)（简单）

假设你正在爬楼梯。需要 *n* 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

**注意：**给定 *n* 是一个正整数。

**示例 1：**

```
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```

**示例 2：**

```
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```

<br/>

### 答案：

#### 1，递归的写法

这题和[（10. 斐波那契数列-I）](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-I.md)很相似，也可以看下

我们来分析一下：

当n等于1的时候，只需要跳一次即可，只有一种跳法，记f(1)=1

当n等于2的时候，可以先跳一级再跳一级，或者直接跳二级，共有2种跳法，记f(2)=2

当n等于3的时候，他可以从一级台阶上跳两步上来，也可以从二级台阶上跳一步上来，所以总共有f(3)=f(2)+f(1)；

同理当等于n的时候，总共有f(n)=f(n-1)+f(n-2)(这里n>2)种跳法。

![image.png](https://pic.leetcode-cn.com/c8caf15fa204cac8c917b50ffb0257e3cdc0bbf1ea78388ce1208c962b562a2a-image.png)

所以如果我们求上到n阶有多少种，代码很简单，直接递归就行

```java
    public static int climbStairs(int n) {
        if (n <= 1)
            return 1;
        if (n < 3)
            return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
```

这种当n比较大的时候会超时，所以不推荐，但如果还想使用递归我们可以改为尾递归的方式，我们来看下代码

```java
    public static int climbStairs(int n) {
        return Fibonacci(n, 1, 1);
    }

    public static int Fibonacci(int n, int a, int b) {
        if (n <= 1)
            return b;
        return Fibonacci(n - 1, b, a + b);
    }
```

![image.png](https://pic.leetcode-cn.com/3bc3602c2e6a7e8f4a39dc908c9668873910feb38993ebc3fd334b354a8208d0-image.png)
上面是运行的结果，我们看到执行时间击败了100%的用户

<br/>

#### 2，非递归

但递归由于重复计算，导致效率很差，我们来看一下，有很多重复的计算，相同的颜色表示计算多次

![image.png](https://pic.leetcode-cn.com/bad04b5001b64a098a27081084573db985bf760738cfa8e64a6b250d16dab2d8-image.png)

所以我们要把他给为非递归的,这个也是击败了100%的用户

```java
    public int climbStairs(int n) {
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

<br/>

#### 3，非递归优化

我们看到上面的数组当前值是依赖他前面两个值的（前两个除外），我们只需要用两个临时变量即可，不需要申请一个数组，这个也是击败了100%的用户

```java
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int first = 1, second = 2, sum = 0;
        while (n-- > 2) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }
```

<br/>

#### 4，公式计算

还可以参照我公众号的这篇文章[356，青蛙跳台阶相关问题](https://mp.weixin.qq.com/s/hLpHLUfXsABzUNjuNflWzQ)，我们可以找到他的递推公式是

![image.png](https://pic.leetcode-cn.com/ae684255d88d2094c9555b308b5fdb5fffb6a7876d9305c4020cf5a2579f4470-image.png)

这个公式其实就是斐波那契公式，1,1,2,3,5,8,13……，
但我们这道题的前几项是1,2,3,5,8……明显比那公式少了一项，所以这里计算第n项的指数应该是n+1
这种解决方式也击败了100%的用户

```java
public static int climbStairs(int n) {
    double sqrt = Math.sqrt(5);
    return (int) ((Math.pow((1 + sqrt) / 2, n + 1) - Math.pow((1 - sqrt) / 2, n + 1)) / sqrt);
}
```

参照：

[10. 斐波那契数列-I](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-I.md)

[10. 斐波那契数列-II](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer10-II.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 