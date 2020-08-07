## [剑指 Offer 14. 剪绳子-II](https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/)（中等）

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。



**示例 1：**

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

**示例 2:**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```



**提示：**

- 2 <= n <= 1000



### 答案：

这题可以参照[剑指 Offer 14- I. 剪绳子](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer14-I.md)

#### 1，数学知识解决

在做这题之前我们先来看这样一个问题，一个整数先把他分成两部分，x+y=n（假设x>=y并且x-y<=1,也就是说x和y非常接近）那么乘积是xy。然后我们再把这两部分的差放大(x+1)+(y-1)=n(假设x>=y)；他们的乘积是（x+1）(y-1)=xy-(x-y)-1，很明显是小于xy的，所以我们得出结论，**如果把整数n分为两部分，那么这两部分的值相差越小乘积越大。**



同理还可以证明如果分成3部分，4部分……也是相差越小乘积会越大。

![image.png](https://pic.leetcode-cn.com/875a8a4619d70cbe2d52f15a4f96f9727b363d2387caadfd320a8c192f35b2a0-image.png)

根据上面的证明，如果我们把长度为n的绳子分为x段，则每段只有在长度相等的时候乘积最大，那么每段的长度是n/x。所以他们的乘积是(n/x)^x。我们来对这个函数求导

![image.png](https://pic.leetcode-cn.com/f5eceba47b6cd65e5b575e212c38161f5ea7a94ccb6fef77154407345b551aa6-image.png)

通过对函数求导我们发现，当x=n/e的时候，也就是每段绳子的长度是n/x=n/(n/e)=e的时候乘积最大。我们知道e=2.718281828459。而题中我们的绳子剪的长度都是整数，所以不可能取e，我们只能取接近e的值，也就是3的时候乘积最大。
但也有例外，当n<=4的时候会有特殊情况，因为22>13。明白了这点代码就容易多了，如果n大于4，我们不停的把绳子减去3，来看下代码

```java
public int cuttingRope(int n) {
    if (n == 2 || n == 3)
        return n - 1;
    int res = 1;
    while (n > 4) {
        //如果n大于4，我们不停的让他减去3
        n = n - 3;
        //计算每段的乘积
        res = res * 3;
    }
    return n * res;
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/64bd71867e0b2ce814303c57c53fe03d4c71c0b3e3c0c5fa403a55631e1676bd-image.png)



![image.png](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（14. 剪绳子-I）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer14-I.md)

#### [下一题（15. 二进制中1的个数）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer15.md)