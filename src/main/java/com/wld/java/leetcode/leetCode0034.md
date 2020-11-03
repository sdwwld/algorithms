### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)（中等）

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

**示例 1:**

```
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
```

**示例 2:**

```
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
```

<br/>

### 答案：

#### 1，二分法查找

题中说了是升序的数组，也就是排过序的，对于排过序的数组查找我们

很容易想到的就是**二分法**。这里要返回的是目标值在数组中的**开始位置和结束位置**，因为相同的数字在排序数组中肯定是挨着的，所以我们通过二分法查到之后，还要往前和往后继续查找，直到不等于目标值为止。



如果是做Android的并且经常看源码的可能知道，Android中有个类ArrayMap，他存储的时候hash值是排过序的，查找的时候也是通过二分法查找，但有可能hash值会有冲突，所以他查找之后也是分别往前和往后继续查找然后再比较key值，和这题解法很相似。我们来画个简图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHeBlKPWO8qfhic8GUoNiawkOicIvGxvgR7VqFdzPAYuJUPj0D8iaLYwJz619dCUKE3Z1J2FNXftjZ8gQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

比如我们通过二分法查找7，然后还要往他的前面和后面继续查找，目的是要找到最开始7和最后7的位置，来看下代码

```java
public int[] searchRange(int[] nums, int target) {
    int find = searchRangeHelper(nums, target);
    //如果没找到，返回{-1, -1}
    if (find == -1)
        return new int[]{-1, -1};
    int left = find - 1;
    int right = find + 1;
    //查看前面是否还有target
    while (left >= 0 && nums[left] == target)
        left--;
    //查看后面是否还有target
    while (right < nums.length && nums[right] == target)
        right++;
    return new int[]{left + 1, right - 1};
}

//二分法查找
public int searchRangeHelper(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        int midVal = nums[mid];
        if (midVal < target) {
            low = mid + 1;
        } else if (midVal > target) {
            high = mid - 1;
        } else {
            return mid;
        }
    }
    return -1;
}
```

<br/>

#### 2，二分法的另一种写法

二分查找一般我们找到某个值之后会直接返回。其实我们有时候还可以对二分法进行改造，当查找某个值的时候不直接返回，而是要继续查找，直到左右两个指针相遇为止。像下面这样，代码中有详细的注释，可以看一下

```java
public int[] searchRange(int[] nums, int target) {
    //查找第一个出现的target
    int first = searchRangeHelper(nums, target);
    //判断有没有查找到
    if (first < nums.length && nums[first] == target) {
        //如果查找到了，说明有这个值，我们再来查(target + 1)，如果有这个值，
        //那么查找的结果也是第一次出现的(target + 1)的下标，我们减去1，就是target
        //最后一次出现的下标。如果没有(target + 1)这个值，那么查找的结果就是第一个
        // 大于target的下标，我们减去1也是target最后一次出现的下标。所以这里
        // 无论有没有(target + 1)这个值，都不影响
        int last = searchRangeHelper(nums, target + 1) - 1;
        return new int[]{first, last};
    } else {
        //没有找到这个值，直接返回{-1, -1}
        return new int[]{-1, -1};
    }
}

//如果数组nums中有多个target，那么返回值就是第一个出现的target的下标
//如果数组nums中没有target，那么返回的就是第一个大于target的下标
public static int searchRangeHelper(int[] nums, double target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int m = low + (high - low) / 2;
        if (target > nums[m])
            low = m + 1;
        else
            high = m - 1;
    }
    return low;
}
```

看到这里可能有的同学灵光乍现，通过二分法能找到target第一次出现的位置，那么通过二分法能不能找到target最后一次出现的位置。当然也是可以的，代码在下面给你准备好了

```java
public int[] searchRange(int[] nums, int target) {
    int first = searchFirst(nums, target);
    //判断有没有查找到
    if (first < nums.length && nums[first] == target) {
        int last = searchLast(nums, target);
        return new int[]{first, last};
    } else {
        //没有找到这个值，直接返回{-1, -1}
        return new int[]{-1, -1};
    }
}

//如果数组nums中有多个target，那么返回值就是第一个出现的target的下标
//如果数组nums中没有target，那么返回的就是第一个大于target的下标
public static int searchFirst(int[] nums, double target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int m = low + (high - low) / 2;
        if (target > nums[m])
            low = m + 1;
        else
            high = m - 1;
    }
    return low;
}

public static int searchLast(int[] nums, double target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
        int m = low + (high - low) / 2;
        if (target >= nums[m])
            low = m + 1;
        else
            high = m - 1;
    }
    return high;
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 