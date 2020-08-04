## [4. 寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)（困难）

给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

<br/>

**示例 1:**

```
nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
```

**示例 2:**

```
nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
```

<br/>

### 答案：

**1，合并**

因为两个数组都是有序的，所以最简单的一种方式就是先把这两个有序数组合并为一个有序数组，然后再找出他的中间值

```java
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int temp[] = new int[totalLength];
        int index = 0;
        for (int i = 0, j = 0; i + j < totalLength; ) {
            //说明数组nums1的数据都遍历完了，我们只需要把nums2的数据添加到temp中即可
            if (i >= nums1.length) {
                temp[index++] = nums2[j++];
                continue;
            }
            //说明数组nums2的数据都遍历完了，我们只需要把nums1的数据添加到temp中即可
            if (j >= nums2.length) {
                temp[index++] = nums1[i++];
                continue;
            }
            //哪个小，先把哪个的值放到临时数组temp中
            if (nums1[i] > nums2[j]) {
                temp[index++] = nums2[j++];
            } else if (nums1[i] < nums2[j]) {
                temp[index++] = nums1[i++];
            } else {
                temp[index++] = nums1[i++];
                temp[index++] = nums2[j++];
            }
        }
        return (temp[totalLength >> 1] + temp[((totalLength - 1) >> 1)]) * .5;
    }
```



#### [上一题（3. 无重复字符的最长子串）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0003.md)

#### [下一题（5. 最长回文子串）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0005.md)
