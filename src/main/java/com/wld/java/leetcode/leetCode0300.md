

**问题描述**





给定一个无序的整数数组，找到其中最长上升子序列的长度。



**示例:**

> **输入**: [10,9,2,5,3,7,101,18]
>
> **输出**: 4 
>
> **解释**: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。



**动态规划**





我们用dp[i]表示数组的前i个元素构成的最长上升子序列，如果要求dp[i]，我们需要用num[i]和前面的数字一个个比较，如果比前面的任何一个数字大，说明加入到他的后面可以构成一个上升子序列 ，就更新dp[i]。我们就以[8，2，3，1，4]为例来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBGcIr3bf70rZWWrgq6Vf937xk0RLgmEXSHXMnqCpOOXic3p7Q3GXYZFHno2icJfLskKse44Jk60cIuA/640?wx_fmt=png)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBGcIr3bf70rZWWrgq6Vf937Ns4rP6l6FgsOVnteh1mxqWZDArvC3fB1Q48GHpDIcEr1lfQ2SlzVxQ/640?wx_fmt=png)

我们再来看下代码

```
 1public int lengthOfLIS(int[] nums) {
 2    //边界条件判断
 3    if (nums == null || nums.length == 0) {
 4        return 0;
 5    }
 6    int[] dp = new int[nums.length];
 7    //初始化数组dp的每个值为1
 8    Arrays.fill(dp, 1);
 9    int max = 1;
10    for (int i = 1; i < nums.length; i++) {
11        for (int j = 0; j < i; j++) {
12            //如果当前值nums[i]大于nums[j]，说明nums[i]可以和
13            //nums[j]结尾的上升序列构成一个新的上升子序列
14            if (nums[i] > nums[j]) {
15                dp[i] = Math.max(dp[i], dp[j] + 1);
16                //记录构成的最大值
17                max = Math.max(max, dp[i]);
18            }
19        }
20    }
21    return max;
22}
```



**上升子序列加入到集合中**





这题还有一种解决方式就是把找到的上升子序列放到一个集合list中，我们每次遍历的时候都会拿当前值nums[i]和list的最后一个元素比较



1，如果nums[i]比list最后一个元素大，说明nums[i]加入到list的末尾可以构成一个更长的上升子序列，我们就把nums[i]加入到list的末尾。



2，如果nums[i]不大于list的最后一个元素，说明nums[i]和list不能构成一个更长的上升子序列，但我们可以用nums[i]把list中第一个大于他的给替换掉。我们要保证list中元素不变的情况下，值越小越好，这样当我们加入一个新值的时候，构成上升子序列的可能性就越大。



再来看下代码

```
 1public int lengthOfLIS(int[] nums) {
 2    //list中保存的是构成的上升子序列
 3    ArrayList<Integer> list = new ArrayList<>(nums.length);
 4    for (int num : nums) {
 5        //如果list为空，我们直接把num加进去。如果list的最后一个元素小于num，
 6        //说明num加入到list的末尾可以构成一个更长的上升子序列，我们就把num
 7        //加入到list的末尾
 8        if (list.size() == 0 || list.get(list.size() - 1) < num)
 9            list.add(num);
10        else {
11            //如果num不小于list的最后一个元素，我们就用num把list中第一
12            //个大于他的值给替换掉,这样我们才能保证list中的元素在长度不变
13            //的情况下，元素值尽可能的小
14            int i = Collections.binarySearch(list, num);
15            //因为list是从小到大排序的，所以上面使用的是二分法查找。当i大
16            //于0的时候，说明出现了重复的，我们直接把他替换即可，如果i小于
17            //0，我们对i取反，他就是list中第一个大于num值的位置，我们把它
18            //替换即可
19            list.set((i < 0) ? -i - 1 : i, num);
20        }
21    }
22    return list.size();
23}
```



**总结**





这题也是比较常见的一道题，动态规划应该说是最好理解的。如果完全搞懂的话，下面的那种解法其实也是比较经典的。





![img](https://mmbiz.qpic.cn/mmbiz_gif/PGmTibd8KQBHc4KrxIFDnhyFaGjmB7Cucq9m90TbibSccgs2NQzQBfymSibzbtUht756BmSwWrGrR3l3x8QRyleSg/640?wx_fmt=gif)

●[411，动态规划和递归求不同路径 II](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487679&idx=1&sn=9e3487051872e33231d1c589de5b9277&chksm=fb41839fcc360a897e49934e6789a935f505aaf16f6e03a484b469dd8d0828e451685b30869c&scene=21#wechat_redirect)

●[409，动态规划求不同路径](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487666&idx=1&sn=348938a0e110abdc081a07572a206561&chksm=fb418392cc360a84df035e84b8c08c6c38eeb603809cd13c373fe6840097054b798c93f94a6a&scene=21#wechat_redirect)

●[407，动态规划和滑动窗口解决最长重复子数组](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487632&idx=1&sn=1e018be0c624b7f0cc7d3302d3ebf4e4&chksm=fb4183b0cc360aa64386bdd35e3bacf34f57f19b75b5f10ecd12f64def2fde4572d6bbb19d5c&scene=21#wechat_redirect)

●[395，动态规划解通配符匹配问题](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487472&idx=1&sn=0a0249cc39b609a8fd0e9dacace2924c&chksm=fb419cd0cc3615c6f8fe11e2c818742276cdc88ae9e3d9f4e0e0a13eda412d025b6a85d33aa0&scene=21#wechat_redirect)

