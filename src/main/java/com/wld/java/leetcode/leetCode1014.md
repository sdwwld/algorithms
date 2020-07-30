**问题描述**



给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。



一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。



返回一对观光景点能取得的最高分。



**示例：**

> 输入：[8,1,5,2,6]
>
> 输出：11
>
> 解释：i = 0, j = 2,
>
> A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11



**提示：**

1. 2 <= A.length <= 50000
2. 1 <= A[i] <= 1000



**暴力求解**





这题其实最容易想到的就是暴力求解，每两个两个计算，记录下最大值，最后再返回，代码比较简单

```
1public int maxScoreSightseeingPair(int[] A) {
2    int res = 0;
3    for (int i = 0; i < A.length; i++) {
4        for (int j = i + 1; j < A.length; j++) {
5            res = Math.max(res, A[i] + i + A[j] - j);
6        }
7    }
8    return res;
9}
```



**根据公式求解**





暴力求解毕竟效率不高，我们还可以根据上面的计算公式来找规律。根据公式

res = A[i] + A[j] + i - j （i < j）

我们求每个景点j的时候，他的A[j] - j 实际上是固定的，要想让res最大，我们就要想办法让A[i] + i尽可能大。所以我们可以使用一个变量cur记录下遍历过的最大值（当前值+当前下标），所以代码很容易想到

```
1public int maxScoreSightseeingPair(int[] A) {
2    int res = 0, cur = A[0] + 0;
3    for (int j = 1; j < A.length; ++j) {
4        res = Math.max(res, cur + A[j] - j);
5        cur = Math.max(cur, A[j] + j);
6    }
7    return res;
8}
```



**总结**





这题没什么难度，主要还是要搞懂这个公式。





![img](https://mmbiz.qpic.cn/mmbiz_gif/PGmTibd8KQBHc4KrxIFDnhyFaGjmB7Cucq9m90TbibSccgs2NQzQBfymSibzbtUht756BmSwWrGrR3l3x8QRyleSg/640?wx_fmt=gif)

●[413，动态规划求最长上升子序列](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487722&idx=1&sn=8c55b4e41fa403f9677cc9213579aa45&chksm=fb4183cacc360adcfd55966d305c2549c2971d03ef1c2a229b463b9c18fcd403713d1ec20f96&scene=21#wechat_redirect)

●[407，动态规划和滑动窗口解决最长重复子数组](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487632&idx=1&sn=1e018be0c624b7f0cc7d3302d3ebf4e4&chksm=fb4183b0cc360aa64386bdd35e3bacf34f57f19b75b5f10ecd12f64def2fde4572d6bbb19d5c&scene=21#wechat_redirect)

●[392，检查数组对是否可以被 k 整除](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487420&idx=1&sn=143bd6d1d147d1c3eeec3529802083b1&chksm=fb419c9ccc36158af3b58b733d0787f64a2a6db8533b7e06a3a6c08ecc39284fa8e644c22058&scene=21#wechat_redirect)

●[390，长度最小的子数组](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487380&idx=1&sn=2a3d1f8f1e79213961af1c819f439bae&chksm=fb419cb4cc3615a2fe7d0d2196f8ce070b3d90f2ad5ff9c5e4b3d5877a08eef934d0a9fe0e2c&scene=21#wechat_redirect)





![img](https://mmbiz.qpic.cn/mmbiz_jpg/PGmTibd8KQBGPeMjzrGIiaQYTmlQkXGepCInRnVCpo4OUQA6GFQ8KYEIj5ibg7I3FKNXMIw1VtKJJR2EFMJvjgQ5g/640?wx_fmt=jpeg)

长按上图，识别图中二维码之后即可关注。



如果喜欢这篇文章就点个"赞"吧