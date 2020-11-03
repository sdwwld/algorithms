## [84. 柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)（困难）

给定 *n* 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram.png)

<center><font size=2>以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。</font></center>

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram_area.png)

<center><font size=2>图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。</font></center>

**示例:**

```
输入: [2,1,5,6,2,3]
输出: 10
```

<br/>

### 答案：

#### 1，暴力求解

最简单的方式就是暴力求解，我们都知道暴力求解的效率很差，但不妨碍我们做出来。暴力求解有两种方式。



**一种是从左边确定一根柱子，然后从左往右扫描，确定以当前柱子的高为最大高度所围成的最大矩形（这个矩形的高度不能超过当前柱子的高度），记录下最大面积。**



**还一种是确定一根柱子以后分别从他的前后两个方向扫描，确定以当前柱子高度为矩形的高所围成的最大矩形（这个矩形的高度就是当前这个柱子的高度），记录下最大面积。**



我们来分别看下这两种写法的代码

```java
public int largestRectangleArea(int[] heights) {
    int length = heights.length;
    int area = 0;
    // 枚举左边界
    for (int left = 0; left < length; ++left) {
        int minHeight = Integer.MAX_VALUE;
        // 枚举右边界
        for (int right = left; right < length; ++right) {
            // 确定高度，我们要最小的高度
            minHeight = Math.min(minHeight, heights[right]);
            // 计算面积，我们要保留计算过的最大的面积
            area = Math.max(area, (right - left + 1) * minHeight);
        }
    }
    return area;
}
```

暴力解法的另一种写法

```java
public int largestRectangleArea(int[] heights) {
    int area = 0, length = heights.length;
    // 遍历每个柱子，以当前柱子的高度作为矩形的高 h，
    // 从当前柱子向左右遍历，找到矩形的宽度 w。
    for (int i = 0; i < length; i++) {
        int w = 1, h = heights[i], j = i;
        //往左边找
        while (--j >= 0 && heights[j] >= h) {
            w++;
        }
        j = i;
        //往右边找
        while (++j < length && heights[j] >= h) {
            w++;
        }
        //记录最大面积
        area = Math.max(area, w * h);
    }
    return area;
}
```

<br/>

#### 2，使用栈求解

我们看一下暴力求解的第二种方式，他是每遍历一根柱子就会往左和往右查找，直到找到比他小的为止，然后**以当前柱子的高度为矩形的高，以不低于当前柱子的数量（必须是和当前柱子挨着的）为矩形的宽** 来计算矩形的面积，我们就用上面的示例以当前高度为5的柱子为例来画个图看一下。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFxxBRnvQ4TsAAjQujsfUld8n7z0ex0enxtoOKPPorQy6y2YyHtJYbBiaGt6kIKpz2qkp20827SHxg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFxxBRnvQ4TsAAjQujsfUldOOef6J9iauUFma0MaQoxrPSLn6OWpq6OibW56eSWof4MtWnHn89GX8RA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

看明白了上面的分析，我们是不是会有点启发，我们**如果以当前柱子的高度为矩形的高，我们只需要往左和往右找到小于当前的柱子，就可以确定矩形的宽度**。知道宽和高面积自然就求出来了。



但是矩形的宽度怎么求呢，我们这里并不是直接求，我们要维护一个递增的栈（从栈底到栈顶的元素所对应柱子的高度是递增的），**注意栈中存放的是柱子的下标，不是柱子的高度。**



**我们每遍历一个柱子的时候如果当前柱子i的值大于等于栈顶元素对应柱子的高度，我们就把当前柱子的下标压入到栈顶中。**



**如果当前柱子i的值小于栈顶元素柱子k的高度，说明栈顶元素对应的柱子k遇到了右边比它小的柱子，我们只需要弹出栈顶柱子k。那么怎么确定柱子k他左边比它小的柱子呢，很明显因为栈从栈底到栈顶是递增的，柱子k已经出栈了，现在栈顶元素w对应柱子的高度就是柱子k遇到的左边比他小的值（有可能这时候栈顶元素w对应柱子的高度和柱子k对应的高度一样大，但没关系，因为下一步我们还会在继续计算）。**根据上面的暴力求解，我们知道一个柱子左边和右边比它小的值，就可以以当前柱子的高度为矩形的高，计算出矩形的面积。然后我们在用栈顶元素w对应的值和柱子i对应的值比较，重复上面的步骤……直到柱子i对应的值大于栈顶元素对应的值（或栈为空）为止。（注意这里的比较是栈中元素对应柱子高度的比较，不是栈中元素的比较）



上面的解说比较绕，看不明白可以多读几遍，我们来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWut1sFTt9eyy4CcJtuTqrNwo2DEDPhCrmA70iacj5PfKgnw5Y3ecPtDVw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWu5P8oTsCDdoApXvURu0QAia1rUtGJ3bA4Zxahia7OEtibpfic0ABUlW34Hw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWuZGiaoxJwrbgric8yCXtQmJmjAXgo7Pribia2SrsbFW7qm51SRmOWNialSCQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWuXYBSicEflwjsgvurgzicFo7G0gjF6qfvicyfyQxAT5g1c28jXDU3uvNQg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWu316TJGdZ8UddQBvJEYV1FdylZHhVGSicuDkIAmOnsianTdQKPohqicjIw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWuQqSNKeeCIDjGoDODcqRL8rRZQKVu7W6sPA3RQcf2X9iadPsyJskM68w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWutGZHXgj4krrKeU5biczliaxb4Vic30JYsXL8bkDhEH4myjE1lwzQf3b0A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWu8P9f9Vlqib4icHa6hibrGtOr8dNNTIHBR8ic9a5dVgOsPeEulbCBEL4p0g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEPQmtpTSKLB7zcxDJ5WzWufG3K8otIlBduo7lBtuVfBjo1KB81bNbmnuibBA7VTPc3WdDmRwAZL2A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public int largestRectangleArea(int[] heights) {
    int length = heights.length;
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i <= length; i++) {
        int h = (i == length ? 0 : heights[i]);
        //如果栈是空的，或者当前柱子的高度大于等于栈顶元素所对应柱子的高度,
        //直接把当前元素压栈
        if (stack.isEmpty() || h >= heights[stack.peek()]) {
            stack.push(i);
        } else {
            int top = stack.pop();
            int area = heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek());
            maxArea = Math.max(maxArea, area);
            i--;
        }
    }
    return maxArea;
}
```

这题标注是难，确实有一定的难度，如果上面图看懂了，上面代码也就不难理解了。其实我们还可以换种思路，在柱状图的最左边和最右边分别增加一个高度为0的柱子，这样代码写起来也比较容易理解，图就不再画了，代码中有详细的注释，我们直接看代码

```java
public int largestRectangleArea(int[] heights) {
    //申请一个比heights长度大2的临时数组
    int[] tmp = new int[heights.length + 2];
    //把数组heights的值复制到数组tmp中，并且tmpd第一个元素
    // 和最后一个元素都是0，表示高度为0的柱子
    System.arraycopy(heights, 0, tmp, 1, heights.length);
    Stack<Integer> stack = new Stack<>();//栈
    int maxArea = 0;
    for (int i = 0; i < tmp.length; i++) {
        //如果当前值tem[i]比栈顶元素对应的柱子高度小，说明栈顶元素的柱子遇到
        // 了右边比它小的柱子。那么他左边比它小的就是栈顶元素所对应的柱子高度
        // (因为栈中元素从栈底到栈顶对应柱子的高度是递增的)，知道左右两边比
        // 它小的就可以确定矩形的面积了，但这个矩形不一定是最大的，所以我们要保存下来
        while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
            int h = tmp[stack.pop()];
            //计算矩形的面积
            int area = (i - 1 - stack.peek()) * h;
            //哪个大留哪个
            maxArea = Math.max(maxArea, area);
        }
        //注意这里入栈的是柱子的下标，不是柱子的高度
        stack.push(i);
    }
    return maxArea;
}
```

<br/>

#### 3，通过两边的临界值求解

根据上面的分析，我们知道对于第i根柱子所围成的最大矩形是

**s=(right-left-1)*height[i]**

其中right是右边比它小的柱子的下标，left是左边比它小的柱子的下标，height[i]是当前柱子的高度。



如果我们知道每根柱子左右两边比它小的值，我们就可以求出最大面积

```java
    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
        maxArea = Math.max(maxArea, height[i] * (rightLess[i] - leftLess[i] - 1));
    }
```

但问题是我们怎么求出左右两边比它小的值呢？比如我们想求左边比它小的值，我们可以这样来计算

```java
    for (int i = 1; i < height.length; i++) {
        int p = i - 1;
        while (p >= 0 && height[p] >= height[i]) {
            p--;
        }
        leftLess[i] = p;
    }
```

代码很简单，就是从他的左边挨着的那个一直往左找，直到找到为止。如果没找到p就会为-1，比如一直递减的柱子每一个p都是-1，-1符合上面的公式。同理右边的也一样。



但我们看到上面的查找效率真的不是很高，实际上代码我们还可以再优化一下，如果左边的柱子i比当前柱子k高，那么柱子i左边比柱子i高的肯定也比当前柱子k高，这种我们就不需要在找了，我们要找柱子i左边比柱子i矮的柱子再和当前柱子k对比，我们来看下

```java
    for (int i = 1; i < height.length; i++) {
        int p = i - 1;
        while (p >= 0 && height[p] >= height[i]) {
            p = leftLess[p];
        }
        leftLess[i] = p;
    }
```

看明白了上面的分析，代码就容易多了，我们再来看下

```java
public static int largestRectangleArea(int[] height) {
    if (height == null || height.length == 0) {
        return 0;
    }
    //存放左边比它小的下标
    int[] leftLess = new int[height.length];
    //存放右边比它小的下标
    int[] rightLess = new int[height.length];
    rightLess[height.length - 1] = height.length;
    leftLess[0] = -1;

    //计算每个柱子左边比它小的柱子的下标
    for (int i = 1; i < height.length; i++) {
        int p = i - 1;
        while (p >= 0 && height[p] >= height[i]) {
            p = leftLess[p];
        }
        leftLess[i] = p;
    }
    //计算每个柱子右边比它小的柱子的下标
    for (int i = height.length - 2; i >= 0; i--) {
        int p = i + 1;
        while (p < height.length && height[p] >= height[i]) {
            p = rightLess[p];
        }
        rightLess[i] = p;
    }
    int maxArea = 0;
    //以每个柱子的高度为矩形的高，计算矩形的面积。
    for (int i = 0; i < height.length; i++) {
        maxArea = Math.max(maxArea, height[i] * (rightLess[i] - leftLess[i] - 1));
    }
    return maxArea;
}
```

<br/>

#### 4，总结

这题如果单从暴力破解的方式上来看不是很难，但我们都知道暴力二字是什么意思，在面试中暴力求解往往不占优势。如果不使用暴力破解这题还是有一定的难度的。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 