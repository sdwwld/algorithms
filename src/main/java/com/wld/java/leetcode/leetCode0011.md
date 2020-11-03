## [11. 盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)（中等）

给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

**说明：**你不能倾斜容器，且 *n* 的值至少为 2。

![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg)

<center><font size=2>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。</font></center>

<br/>

**示例：**

```
输入：[1,8,6,2,5,4,8,3,7]
输出：49
```

<br/>

### 答案：

#### 1，暴力求解

这种题最容易想到的是暴力求解，就是计算每两个柱子所围成的面积，把所有的都计算一遍，然后保留最大值即可。但暴力求解效率一般都不高，我们看看即可，代码如下

```java
public int maxArea(int[] height) {
    int maxarea = 0;
    int area = 0;
    int length = height.length;
    for (int i = 0; i < length - 1; i++) {
        for (int j = i + 1; j < length; j++) {
            area = Math.min(height[i], height[j]) * (j - i);
            maxarea = Math.max(maxarea, area);
        }
    }
    return maxarea;
}
```

<br/>

#### 2，双指针求解

根据木桶原理，桶的容量是由最短的木板决定的，所以这里矩形的高度也是由最矮的柱子所决定的。我们可以使用两个指针，**一个left指向左边的柱子，以他的高为矩形的高度，然后从最右边开始往左扫描，找到比left柱子高的为止**（如果没找到，那么矩形的宽度就是0）。计算矩形面积之后，left再往右移一位，再以同样的方式继续查找……。



比如下面的图中计算以第1个柱子的高度为矩形的高度，因为高度一定，要想使矩形的面积最大，就只能是矩形的宽度最大，所以这里从数组的最后面开始找，找到一个比3大或者等于3的值即可，如果没找到那么宽度就是0。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEtbFydsMel91LA6Y1WMZqPr4XObsGzeRrYBj2gRiaU1icT3iaQa3O6EEeo0XW37KMhJOBmkons3WKbA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

我们查找的时候为了防止遗漏，不光从前面开始找，而且还要从后面开始找，需要两遍查找，代码如下

```java
public int maxArea(int[] height) {
    int maxarea = 0, left = 0, length = height.length;
    int area;
    int right;
    //从前面开始找
    while (left < length) {
        right = length - 1;
        while (right > left) {
            if (height[right] < height[left]) {
                right--;
            } else {
                break;
            }
        }
        //计算矩形的面积
        area = height[left] * (right - left);
        //保存计算过的最大的面积
        maxarea = Math.max(maxarea, area);
        left++;
    }
    //从后面开始找，和上面类似
    right = length - 1;
    while (right > 0) {
        left = 0;
        while (right > left) {
            if (height[right] > height[left]) {
                left++;
            } else {
                break;
            }
        }
        area = height[right] * (right - left);
        maxarea = Math.max(maxarea, area);
        right--;
    }
    return maxarea;
}
```

<br/>

#### 3，双指针优化

上面的代码我们两个方向都要查找，是不是感觉有点麻烦，我们再认真看下这个图

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEtbFydsMel91LA6Y1WMZqPr4XObsGzeRrYBj2gRiaU1icT3iaQa3O6EEeo0XW37KMhJOBmkons3WKbA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

比如我们以3为矩形的高度，查找矩形宽度的时候从最右边开始往左找，找到比3大的为止，这里找到了4，那么柱子3到柱子4中间所围成的矩形高度就是柱子3的高度。如果我们从右边开始找的时候是小于3的，比如这里是2，那么我们这里是不是找到了以2为高度的矩形的最大面积。也就是相当于我们可以把从前往后和从后往前找合并为一个，所以这里代码就非常简洁了，我们来看下

```java
public int maxArea(int[] height) {
    int maxarea = 0, left = 0, right = height.length - 1;
    int area = 0;
    while (left < right) {
        //计算面积，面积等于宽*高，宽就是left和right之间的距离，高就是
        //left和right所对应的最低高度
        area = Math.min(height[left], height[right]) * (right - left);
        //保存计算过的最大的面积
        maxarea = Math.max(maxarea, area);
        //柱子矮的往中间靠
        if (height[left] < height[right])
            left++;
        else
            right--;
    }
    return maxarea;
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 