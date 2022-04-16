## [1518. 换酒问题](https://leetcode-cn.com/problems/water-bottles/)（简单）

小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。

如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。

请你计算 最多 能喝到多少瓶酒。

 <br/>

**示例 1：**

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode1/1518/sample_1_1875.png)

```
输入：numBottles = 9, numExchange = 3
输出：13
解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
```

**示例 2：**

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode1/1518/sample_2_1875.png)

```
输入：numBottles = 15, numExchange = 4
输出：19
解释：你可以用 4 个空酒瓶兑换 1 瓶酒。
所以最多能喝到 15 + 3 + 1 = 19 瓶酒。
```

**示例 3：**

```
输入：numBottles = 5, numExchange = 5
输出：6
```

**示例 4：**

```
输入：numBottles = 2, numExchange = 3
输出：2
```

<br/>

**提示：**

- 1 <= numBottles <= 100
- 2 <= numExchange <= 100

<br/>

### 答案：

#### 1，问题分析

类似这种题估计很多人都见过，一般是作为一道脑筋急转弯的题出现，我们就以示例4为例来说下，如果3个空瓶子可以换一瓶酒的话，我们只有两瓶酒，喝完之后最多只有两个空瓶子，所以是换不成一瓶酒的。但如果我们找别人借一个空瓶子的话，正好是3个空瓶子，可以换一瓶酒，喝完之后再把这个空瓶子还给别人，所以我们可以喝3瓶酒，作为一道脑筋急转弯题这是正确的，但这道题答案返回的是2，也就是说我们不能找别人借。

<br>

我们假设一个空瓶子的价值是1，那么一瓶酒（**不包含瓶子**）的价值就是numExchange-1。我们最初所拥有的总价值就是numBottles* numExchange，因为不能找别人借，**所以最后我们至少会有一个空瓶子**，也就是说最后我们所能喝到的最大价值<=numBottles* numExchange-1，我们能喝的到酒的数量就很容易算出来了。

```java
public int numWaterBottles(int numBottles, int numExchange) {
    return (numBottles * numExchange - 1) / (numExchange - 1);
}
```

<br>

#### 2，另一种解法

每次喝完之后如果空瓶子数量大于等于numExchange，我们就把他兑换成酒，这个时候我们所拥有的瓶子数量就是我们兑换成酒的瓶子数量加上没有兑换成酒的瓶子数量，如果还大于numExchange就继续循环……，直到不能兑换为止。

```java
public int numWaterBottles(int numBottles, int numExchange) {
    int total = numBottles;
    while (numBottles >= numExchange) {
        //change表示的是兑换成酒的数量
        int change = numBottles / numExchange;
        //total再加上兑换的酒
        total += change;
        //瓶子数量变为兑换成酒的数量加上没有兑换成酒的数量
        numBottles = change + numBottles % numExchange;
    }
    return total;
}
```

<br>

#### 3，总结

这道题和脑筋急转弯题型还是有点区别的，如果空瓶子数量不够的情况下我们是不能借的，这两种解法都比较简单。

<br>



 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
