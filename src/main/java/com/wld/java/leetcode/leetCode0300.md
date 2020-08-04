

## [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)（中等）

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**示例:**

```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

**说明:**

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 O(*n2*) 。

<br/>

### 答案：

**1，动态规划**

我们用dp[i]表示数组的前i个元素构成的最长上升子序列，如果要求dp[i]，我们需要用num[i]和前面的数字一个个比较，如果比前面的任何一个数字大，说明加入到他的后面可以构成一个上升子序列 ，就更新dp[i]。我们就以[8，2，3，1，4]为例来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBGcIr3bf70rZWWrgq6Vf937xk0RLgmEXSHXMnqCpOOXic3p7Q3GXYZFHno2icJfLskKse44Jk60cIuA/640?wx_fmt=png)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBGcIr3bf70rZWWrgq6Vf937Ns4rP6l6FgsOVnteh1mxqWZDArvC3fB1Q48GHpDIcEr1lfQ2SlzVxQ/640?wx_fmt=png)

我们再来看下代码

```java
public int lengthOfLIS(int[] nums) {
    //边界条件判断
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int[] dp = new int[nums.length];
    //初始化数组dp的每个值为1
    Arrays.fill(dp, 1);
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            //如果当前值nums[i]大于nums[j]，说明nums[i]可以和
            //nums[j]结尾的上升序列构成一个新的上升子序列
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
                //记录构成的最大值
                max = Math.max(max, dp[i]);
            }
        }
    }
    return max;
}
```

<br/>

**2，上升子序列加入到集合中**

这题还有一种解决方式就是把找到的上升子序列放到一个集合list中，我们每次遍历的时候都会拿当前值nums[i]和list的最后一个元素比较



1，如果nums[i]比list最后一个元素大，说明nums[i]加入到list的末尾可以构成一个更长的上升子序列，我们就把nums[i]加入到list的末尾。



2，如果nums[i]不大于list的最后一个元素，说明nums[i]和list不能构成一个更长的上升子序列，但我们可以用nums[i]把list中第一个大于他的给替换掉。我们要保证list中元素不变的情况下，值越小越好，这样当我们加入一个新值的时候，构成上升子序列的可能性就越大。



再来看下代码

```java
public int lengthOfLIS(int[] nums) {
    //list中保存的是构成的上升子序列
    ArrayList<Integer> list = new ArrayList<>(nums.length);
    for (int num : nums) {
        //如果list为空，我们直接把num加进去。如果list的最后一个元素小于num，
        //说明num加入到list的末尾可以构成一个更长的上升子序列，我们就把num
        //加入到list的末尾
        if (list.size() == 0 || list.get(list.size() - 1) < num)
            list.add(num);
        else {
            //如果num不小于list的最后一个元素，我们就用num把list中第一
            //个大于他的值给替换掉,这样我们才能保证list中的元素在长度不变
            //的情况下，元素值尽可能的小
            int i = Collections.binarySearch(list, num);
            //因为list是从小到大排序的，所以上面使用的是二分法查找。当i大
            //于0的时候，说明出现了重复的，我们直接把他替换即可，如果i小于
            //0，我们对i取反，他就是list中第一个大于num值的位置，我们把它
            //替换即可
            list.set((i < 0) ? -i - 1 : i, num);
        }
    }
    return list.size();
}
```

<br/>

**总结**

这题也是比较常见的一道题，动态规划应该说是最好理解的。如果完全搞懂的话，下面的那种解法其实也是比较经典的。

#### [上一题（299. 猜数字游戏）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0299.md)
