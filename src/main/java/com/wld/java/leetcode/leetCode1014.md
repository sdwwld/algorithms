## [1014. 最佳观光组合](https://leetcode-cn.com/problems/best-sightseeing-pair/)（中等）

给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

返回一对观光景点能取得的最高分。



**示例：**

```
输入：[8,1,5,2,6]
输出：11
解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
```



**提示：**

1. 2 <= A.length <= 50000
2. 1 <= A[i] <= 1000



### 答案：

这题其实最容易想到的就是暴力求解，每两个两个计算，记录下最大值，最后再返回，代码比较简单

```java
public int maxScoreSightseeingPair(int[] A) {
    int res = 0;
    for (int i = 0; i < A.length; i++) {
        for (int j = i + 1; j < A.length; j++) {
            res = Math.max(res, A[i] + i + A[j] - j);
        }
    }
    return res;
}
```



**根据公式求解**

暴力求解毕竟效率不高，我们还可以根据上面的计算公式来找规律。根据公式

res = A[i] + A[j] + i - j （i < j）

我们求每个景点j的时候，他的A[j] - j 实际上是固定的，要想让res最大，我们就要想办法让A[i] + i尽可能大。所以我们可以使用一个变量cur记录下遍历过的最大值（当前值+当前下标），所以代码很容易想到

```java
public int maxScoreSightseeingPair(int[] A) {
    int res = 0, cur = A[0] + 0;
    for (int j = 1; j < A.length; ++j) {
        res = Math.max(res, cur + A[j] - j);
        cur = Math.max(cur, A[j] + j);
    }
    return res;
}
```



**总结**

这题没什么难度，主要还是要搞懂这个公式。
