## [剑指 Offer 21. 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)（简单）

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

<br/>

**示例：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```

<br/>

**提示：**

1. `1 <= nums.length <= 50000`
2. `1 <= nums[i] <= 10000`

<br/>

### 答案：

#### 1，临时数组求解

这题没什么难度，首先最容易想到的是申请一个同样大小的临时数组，把原数组的值放到临时数组中，奇数从前面放，偶数从后面放，我们来看下代码

```java
public int[] exchange(int[] nums) {
    if (nums == null || nums.length == 0)
        return nums;
    int left = 0;
    int right = nums.length - 1;
    int temp[] = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
        if ((nums[i] & 1) == 0) {
            //偶数从后面放
            temp[right--] = nums[i];
        } else {
            //奇数从前面放
            temp[left++] = nums[i];
        }
    }
    return temp;
}
```

<br/>

#### 2，双指针求解

我们可以使用两个指针left和right。left从左边开始扫描，如果是奇数就往右走，如果遇到偶数就停下来（此时left指向的是偶数），right从右边开始扫描，如果是偶数就往左走，如果是奇数就停下来（此时right指向的是奇数），交换left和right指向的值。继续循环，直到left==right为止。我们就以数组[3，2，4，9，5，8，1]为例来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHdnAVjCFE0jh4Pic4vMPZwxf3Qj2ky2cIZ7Qaib2eq7LlTg6g1IicdJYuDQkMCC6dBssHPS0B50OkrQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHdnAVjCFE0jh4Pic4vMPZwxOiccCGcSCJlibCYDUDmk7KfaEJg18TZOpsxtk7JdpUSiakcCiaD0dpmLfg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public static int[] exchange(int[] nums) {
    if (nums == null || nums.length == 0)
        return nums;
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
        //如果是奇数，就往后挪，直到遇到偶数为止
        while (left < right && (nums[left] & 1) == 1) {
            left++;
        }
        //如果是偶数，就往前挪，直到遇到奇数为止
        while (left < right && (nums[right] & 1) == 0) {
            right--;
        }
        //交换两个值
        if (left < right) {
            nums[left] ^= nums[right];
            nums[right] ^= nums[left];
            nums[left] ^= nums[right];
        }
    }
    return nums;
}
```

代码16到20行是交换两个数字的值，交换两个数的值有多种方式，这里选择的是通过异或来交换，如果看不明白可以看一下[357，交换两个数字的值](https://mp.weixin.qq.com/s/2Ll_LyG37qkoRn6A1EMRVQ)。

<br/>

#### 3，快慢指针求解

第三种方式使用的是快慢指针，和上一种解决方式有一点区别，上一种是一前一后扫描。我们这里使用的快慢指针都是从头开始扫描。我们使用两个指针，一个快指针fast，一个慢指针slow。慢指针slow存放下一个奇数应该存放的位置，快指针fast往前搜索奇数，搜索到之后然后就和slow指向的值交换，我们还以上面的数据为例画个图来分析下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHdnAVjCFE0jh4Pic4vMPZwxLI3QxcBDvLGm3IUrLHq4qlpmncXB8kic6emAd9atjDDKOJeCCvpVbng/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHdnAVjCFE0jh4Pic4vMPZwxI9tF3e2H1vTAUPhPiaHuKjyddJFjEVPgulWI1b6fNxraCoED7ubibNFg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public static int[] exchange(int[] nums) {
    int slow = 0, fast = 0;
    while (fast < nums.length) {
        if ((nums[fast] & 1) == 1) {//奇数
            if (slow != fast) {
                nums[slow] ^= nums[fast];
                nums[fast] ^= nums[slow];
                nums[slow] ^= nums[fast];
            }
            slow++;
        }
        fast++;
    }
    return nums;
}
```



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（20. 表示数值的字符串）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer20.md)

#### [下一题（22. 链表中倒数第k个节点）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer22.md)