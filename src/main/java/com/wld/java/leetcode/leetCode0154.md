## [154. 寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)（困难）

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

注意数组中可能存在重复的元素。

**示例 1：**

```
输入: [1,3,5]
输出: 1
```

**示例 2：**

```
输入: [2,2,2,0,1]
输出: 0
```

<br/>

### 答案：

#### 1，逐个查找和排序查找

这题要求的是数组中的最小元素，所以最简单的两种方式，一个是逐个查找，一个是排序之后再查找，先看一下逐个查找，就是一个个查找，把数组的元素全部都遍历一遍，即可找到最小值

```java
public int findMin(int[] numbers) {
    int min = numbers[0];
    for (int i = 1; i < numbers.length; i++) {
        if (min > numbers[i])
            min = numbers[i];
    }
    return min;
}
```

再来看一下排序查找，我们使用从小到大的顺序对数组进行升序排列，排序完之后直接返回数组的第一个元素即可。

```java
public int findMin(int[] numbers) {
    Arrays.sort(numbers);
    return numbers[0];
}
```

<br/>

#### 2，二分法查找

首先来说下上面两种方式肯定是都能解决的，如果来解决这道题总觉得不是很合适。因为这道题说了，他本来是一个递增排序的数组，然后经过了一次旋转。一想到排序数组的查找，第一个应该想到的是二分法。

**我们看下二分法查找的正常代码是什么样的**

```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;
        if (target < nums[mid])
            right = mid - 1;
        else
            left = mid + 1;
    }
    return -1;
}
```

但这道题的数组在中间有一次旋转，所以不能直接用以前的那种二分法来查找，我们可以参照上面代码换种思路，我们不断的缩小查找范围，当查找范围的长度为1的时候返回，下面代码中也就是left等于right的时候。

```java
public int findMin(int[] numbers) {
    int left = 0, right = numbers.length - 1;
    while (left < right) {
        //找出left和right中间值的索引
        int mid = left + (right - left) / 2;
        if (numbers[mid] > numbers[right]) {
            //如果中间值大于最右边的值，说明旋转之后最小的
            //数字肯定在mid的右边，比如[3, 4, 5, 6, 7, 1, 2]
            left = mid + 1;
        } else if (numbers[mid] < numbers[right]) {
            //如果中间值小于最右边的值，说明旋转之后最小的
            //数字肯定在mid的前面，比如[6, 7, 1, 2, 3, 4, 5],
            //注意这里mid是不能减1的，比如[3，1，3]，我们这里只是
            //证明了numbers[mid]比numbers[right]小，但有可能
            //numbers[mid]是最小的，所以我们不能把它给排除掉
            right = mid;
        } else {
            //如果中间值等于最后一个元素的值，我们是没法确定最小值是
            // 在mid的前面还是后面，但我们可以缩小查找范围，让right
            // 减1，因为即使right指向的是最小值，但因为他的值和mid
            // 指向的一样，我们这里并没有排除mid，所以结果是不会有影响的。
            //比如[3，1，3，3，3，3，3]和[3，3，3，3，3，1，3],中间的值
            //等于最右边的值，但我们没法确定最小值是在左边还是右边
            right--;
        }
    }
    return numbers[left];
}
```



参照：[剑指 Offer 11. 旋转数组的最小数字](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer11.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（153. 寻找旋转排序数组中的最小值）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0153.md)

#### [下一题（155. 最小栈）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0155.md)