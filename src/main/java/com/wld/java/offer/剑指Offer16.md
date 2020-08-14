## [剑指 Offer 16. 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)（中等）

实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。

<br/>

**示例 1:**

```
输入: 2.00000, 10
输出: 1024.00000
```

**示例 2:**

```
输入: 2.10000, 3
输出: 9.26100
```

**示例 3:**

```
输入: 2.00000, -2
输出: 0.25000
解释: 2^-2 = 1/22 = 1/4 = 0.25
```

<br/>

**说明:**

- -100.0 < *x* < 100.0
- *n* 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。

<br/>

### 答案：

这题看起来很简单，但能一步写成功很不容易，我们先来分析下。

- 当exponent是0的时候，直接返回1即可，
- 当exponent小于0的时候，需要把它转化为正数才能更方便计算，同时base要变为1/base。
- 当exponent大于0的时候要分为两种情况，一种是偶数，一种是奇数。

**1，** 如果exponent是偶数我们只需要计算
Power(base*base, exponent/2)。举个例子，比如我们要计算Power（3，8），我们可以改为Power（3\*3，8/2），也就是Power（9，4）。

**2，** 如果exponent是奇数，我们只需要计算
base*Power(base\*base, exponent/2)，比如Power（3，9），我们只需要计算3\*Power（3\*3，9/2），也就是3\*Power（9，4）。

<br/>

所以代码很容易写，我们来看下

```java
public double myPow(double x, int n) {
    //如果n等于0，直接返回1
    if (n == 0)
        return 1;
    //如果n小于0，把它改为正数
    if (n < 0)
        return myPow(1 / x, -n);
    //根据n是奇数还是偶数来做不同的处理
    return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
}
```

上面代码有一点瑕疵，就是如果当n=Integer.MIN_VALUE的时候，第7行代码就会出问题，这是因为**数字溢出问题，导致Integer.MIN_VALUE的相反数还是他自己**，所以会一直调用，直到最后出现堆栈溢出异常。所以有一种方式就是把1/x提取出来一个，代码如下

```java
public double myPow(double x, int n) {
    if (n == 0)
        return 1;
    //如果n小于0，把它改为正数，并且把1/x提取出来一个
    if (n < 0)
        return 1 / x * myPow(1 / x, -n - 1);
    return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
}
```

这样代码就不会有问题了。我们还可以把他改为非递归的写法，这样在计算的时候就不需要关心exponent究竟是正数还是负数了，只需要在最后返回的时候判断一下即可，代码也很简单

```java
public double myPow(double x, int n) {
    double result = 1.0;
    for (int i = n; i != 0; i /= 2, x *= x) {
        if (i % 2 != 0) {
            //i是奇数
            result *= x;
        }
    }
    return n < 0 ? 1.0 / result : result;
}
```



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（15. 二进制中1的个数）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer15.md)

#### [下一题（17. 打印从1到最大的n位数）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer17.md)