## [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)（简单）

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

<br/>

**示例:**

```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

<br/>

### 答案：

**1，暴力破解法**

暴力破解是最容易想到的，每两两相加判断结果是否等于target，如果等于直接return

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }
}
```

<br/>

**2，使用HashMap解决**

暴力破解毕竟效率很差，我们还可以使用Map来解决

```java
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.get(target - nums[i]) != null) {
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
```

  <br/>

**3，先排序**

我本来以为前两种方式就够了，后来想了下使用排序可不可以，结果发现排序还能击败52.38%的用户，出乎我的意料，当然这种写法效率很差，看看就行。

*![微信截图_20200610224130.png](https://pic.leetcode-cn.com/a8c267f2b29ba4eb48b0b21fe94eeae1ce969d959cfe34f2d758379e4dbb872f-%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200610224130.png)*

```java
    public int[] twoSum(int[] nums, int target) {
        Object[] objects = new Object[nums.length];
        for (int i = 0; i < nums.length; i++) {
            objects[i] = new int[]{nums[i], i};
        }
        Arrays.sort(objects, new Comparator<Object>() {

            @Override
            public int compare(Object o, Object t1) {
                int[] arr1 = (int[]) o;
                int[] arr2 = (int[]) t1;
                return arr1[0] - arr2[0];
            }
        });
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int[] leftValue = (int[]) objects[left];
            int[] rightValue = (int[]) objects[right];
            int value = leftValue[0] + rightValue[0];
            if (value == target)
                return new int[]{leftValue[1], rightValue[1]};
            else if (value < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[2];
    }
```

 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   [下一题（2. 两数相加）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0002.md)

