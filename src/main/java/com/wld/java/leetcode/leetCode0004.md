1，合并

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









