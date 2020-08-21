## [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)（困难）

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

<center><font size=2>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。</font></center>

**示例:**

```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

<br/>

### 答案：

#### 1，三指针求解

这题让求柱子中间能盛多少水，首先可以肯定两边的两个柱子是不能盛水的，只有两边之间的柱子有可能会盛水。最简单的一种方式就是使用3个指针，先找到最高的柱子，用一个指针top指向最高柱子，然后最高柱子左边用两个指针，一个left，一个right（这里的left和right指向柱子的高度）。

- 如果left大于right，那么肯定是能盛水的，因为left是小于等于最高柱子top的，并且right指向的柱子是在left和最高柱子top之间，根据木桶原理盛水量由最矮的柱子决定，所以盛水是left-right。
- 如果left不大于right，是不能盛水的，这时候我们要让left等于right。因为right是不能超过最高柱子的，我们增加left的高度，有利于后面计算的时候盛更多的水。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKlTKAHwune3HtDyg5iaTlC7aruceoLXaeqJGKD5JCSiaUpfLOibiag0ysaLw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKl5tTwmIurNc1elxichLUO8dsQbIUviaiaEicP2959ibHVuXCiagnSiaLXXGbwg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKl4I7bUsXORIuD8gnVybflNKuHsaUYrRCaU4WYs8osmDkH328n0RrPibg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKlPncRmlKXCxdHd6CA2OFKHyUDP1ET83zibibfbuicsrXdlickcf5B0oqc6g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKlZV9hY9ojiadAZ2979vEnnYqsmGpca4GCHVazt3t3w4FGHjiceVWPBuXg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上面的代码如下

```java
int left = height[0];//左边的柱子
int right = 0;//右边的柱子
int water = 0;//盛水量
    for (int i = 1; i < 最高柱子的下标; i++) {
    right = height[i];
    //如果right大于left，我们要让更新left的值
    if (right > left) {
        left = right;
    } else {
        //否则我们计算盛水量
        water += left - right;
    }
}
```

这里我们只是计算了左边的盛水量，我们还需要计算右边的盛水量，完整代码如下

```java
public int trap(int[] height) {
    if (height.length <= 2)
        return 0;
    //找到最高的柱子的下标
    int max = Integer.MIN_VALUE;
    int maxIndex = -1;
    for (int i = 0; i < height.length; i++) {
        if (height[i] > max) {
            max = height[i];
            maxIndex = i;
        }
    }

    //统计最高柱子左边能接的雨水数量
    int left = height[0];
    int right = 0;
    int water = 0;
    for (int i = 1; i < maxIndex; i++) {
        right = height[i];
        if (right > left) {
            left = right;
        } else {
            water += left - right;
        }
    }

    //统计最高柱子右边能接的雨水数量
    right = height[height.length - 1];
    for (int i = height.length - 2; i > maxIndex; i--) {
        left = height[i];
        if (height[i] > right) {
            right = left;
        } else {
            water += right - left;
        }
    }

    //返回盛水量
    return water;
}
```

<br/>

#### 2，双指针求解

这里我们还可以使用双指针，一个指向最左边，一个指向最右边，如下图所示。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKl2SrmODeogPDrib7LtF39xx0tA7cR7iaTZf9Jm4BZurr01MZsXQHDIOmw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

这里要明白一点，最开始的时候如果左边柱子从左往右是递增的，那么这些柱子是不能盛水的，比如像下面这样

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKlWLXQQ5ct82vLaH4KVPVibDc502eJFjI8rVdBXTmZhkGZV1vXT8TxxEQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

同理最开始的时候如果右边的柱子从右往左是递增的，也是不能盛水的。所以上面图中right指向的是右边第2根柱子。确定左右两边柱子的的代码如下

```java
int left = 0, right = height.length - 1;
while (left < right && height[left] <= height[left + 1])
    left++;
while (left < right && height[right] <= height[right - 1])
    right--;
```

通过上面的计算，确定left和right的值之后，在left和right之间相当于构成了一个桶，桶的高度是最矮的那根柱子。然后我们从两边往中间逐个查找，如果查找的柱子高度小于桶的高度，那么盛水量就是桶的高度减去我们查找的柱子高度，如果查找的柱子大于桶的高度，我们要更新桶的高度。我们来看下最终代码

```java
public int trap(int[] height) {
    if (height.length <= 2)
        return 0;
    int water = 0;
    int left = 0, right = height.length - 1;
    //最开始的时候确定left和right的边界，这里的left和right是
    //柱子的下标，不是柱子的高度
    while (left < right && height[left] <= height[left + 1])
        left++;
    while (left < right && height[right] <= height[right - 1])
        right--;

    while (left < right) {
        int leftValue = height[left];
        int rightValue = height[right];
        //在left和right两根柱子之间计算盛水量
        if (leftValue <= rightValue) {
            //如果左边柱子高度小于等于右边柱子的高度，根据木桶原理，
            // 桶的高度就是左边柱子的高度
            while (left < right && leftValue >= height[++left]) {
                water += leftValue - height[left];
            }
        } else {
            //如果左边柱子高度大于右边柱子的高度，根据木桶原理，
            // 桶的高度就是右边柱子的高度
            while (left < right && height[--right] <= rightValue) {
                water += rightValue - height[right];
            }
        }
    }
    return water;
}
```

上面有3个while循环，看的有点眼花缭乱，实际上我们还可以把它合并为一个，代码如下

```java
public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int water = 0;
    int leftmax = 0;
    int rightmax = 0;
    while (left < right) {
        //确定左边的最高柱子
        leftmax = Math.max(leftmax, height[left]);
        //确定左边的最高柱子
        rightmax = Math.max(rightmax, height[right]);
        //那么桶的高度就是leftmax和rightmax中最小的那个
        if (leftmax < rightmax) {
            //桶的高度是leftmax
            water += (leftmax - height[left++]);
        } else {
            //桶的高度是rightmax
            water += (rightmax - height[right--]);
        }
    }
    return water;
}
```

<br/>

#### 3，双指针代码简化

实际上我们还可以再进一步简化，我们看下下面这个图。此时left和right围成的桶的高度是4，这个时候如果right往左移，那么移动之后这个值是小于4的，也就是小于桶的高度，所以这个时候桶的高度是不变的。假如right往左移之后的值是大于4，比如5，那么桶的高度是要更新的。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEcpPf5y3glSO5RMluU7KKlS9RRB0ggVhcPh6fd03VrWspuTpcum3Fg1l2j5hRpG91nuiazLnFQa4Q/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

我们只要确定桶的高度之后，那么盛水量就好求了。

```java
public int trap(int[] height) {
    int left = 0, right = height.length - 1, water = 0, bucketHeight = 0;
    while (left < right) {
        //取height[left]和height[right]的最小值
        int minHeight = Math.min(height[left], height[right]);
        //如果最小值minHeight大于桶的高度bucketHeight，要更新桶的高度到minHeight
        bucketHeight = bucketHeight < minHeight ? minHeight : bucketHeight;
        water += height[left] >= height[right] ? (bucketHeight - height[right--]) : (bucketHeight - height[left++]);
    }
    return water;
}
```

<br/>

#### 4，总结

接雨水我们把它想象成两边的两根柱子围成一个桶，桶的高度就是最矮的那根柱子，只要确定了桶的高度，我们遍历中间柱子的时候就可以确定盛水量了。如果柱子的高度大于桶的高度，很明显是不能盛水的，只有柱子的高度小于桶的高度的时候才会盛水。这里有一点要注意的是当柱子的高度大于桶的高度的时候我们要更新桶的高度，当柱子的高度小于桶时候，桶的高度是不变的。这题使用双指针很巧妙的解决了上面的问题





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（41. 缺失的第一个正数）(困难)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0041.md)

#### [下一题（43. 字符串相乘）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0043.md)