## [剑指 Offer 3. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof)（简单）

找出数组中重复的数字。

<br/>


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

**示例 1：**

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

<br/>

**限制：**

```
2 <= n <= 100000
```

<br/>

### 答案：

**1，使用集合set**

最简单的方式就是把数组中的元素一个个加入到集合set中，加入的时候如果有重复的，则直接返回


    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
       	    //如果add失败，表示有重复的，直接返回
            if (!set.add(num))
                return num;
        }
        return -1;
    }
<br/>

**2，先排序在查找**

第二种方式是先排序在查找，排序之后有重复的肯定是挨着的，然后前后两两比较，如果有重复的直接返回


    public int findRepeatNumber(int[] nums) {
    	//先排序
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                return nums[i];
        }
        return -1;
    }
<br/>

**3，使用临时数组**

这道题有个很明显的特点，就是数字的大小在0~n-1之间，所以使用上面两种方式肯定不是最好的选择。这里我们可以申请一个临时数组temp，因为nums元素中的每个元素的大小都在0~n-1之间，所以我们可以把nums中元素的值和临时数组temp建立映射关系，就是nums中元素的值是几，我们就把temp中对应的位置值加1，当temp某个位置的值大于1的时候，就表示出现了重复，我们直接返回即可

![image.png](https://pic.leetcode-cn.com/49ac8043437dfa731c6d26c44a2ec5da49735ffee87042124d552b2dd6aef5c1-image.png)

    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[nums[i]]++;
            if (temp[nums[i]] > 1)
                return nums[i];
        }
        return -1;
    }
<br/>

**4，放到指定的位置**

我们还可以不使用临时数组，我们在遍历的时候把数组nums中的值放到对应的位置上，比如某个元素是5，我们就把他放到nums[5]中，每次放入的时候查看一下这个位置是否放入了正确的值，如果已经放入了正确的值，就说明重复了，我们直接返回即可。


    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //位置正确，先不用管
            if (i == nums[i])
                continue;
            //出现了重复，直接返回
            if (nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            //交换
            int temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
            //这里的i--是为了抵消掉上面的i++，
            //交换之后需要原地再比较
            i--;
        }
        return -1;
    }



#### [下一题（4. 二维数组中的查找）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer04.md)