## [209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)（中等）

给定一个含有 **n** 个正整数的数组和一个正整数 **s** ，找出该数组中满足其和 **≥ s** 的长度最小的 **连续** 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

 <br/>

**示例：**

```
输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
```

<br/>

### 答案：

### 1，暴力求解

首先这题最容易想到的是暴力求解，使用两个for循环，一个for循环固定一个数字比如m，另一个for循环从m的下一个元素开始累加，当和大于等于s的时候终止内层循环，顺便记录下最小长度

```java
public int minSubArrayLen(int s, int[] nums) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
        int sum = nums[i];
        if (sum >= s)
            return 1;
        for (int j = i + 1; j < nums.length; j++) {
            sum += nums[j];
            if (sum >= s) {
                min = Math.min(min, j - i + 1);
                break;
            }
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```

暴力求解虽然也能解出来，但毕竟效率很差，我们来看下其他的几种解题方法

<br/>

#### 2，使用队列（一）

实际上我们也可以把它称作是滑动窗口，这里的队列其实就相当于一个窗口。我们把数组中的元素不停的入队，直到总和大于等于s为止，接着记录下队列中元素的个数，然后再不停的出队，直到队列中元素的和小于s为止（如果不小于s，也要记录下队列中元素的个数，这个个数其实就是不小于s的连续子数组长度，我们要记录最小的即可）。接着再把数组中的元素添加到队列中……重复上面的操作，直到数组中的元素全部使用完为止。

这里以[2,3,1,2,4,3]举例画个图来看下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHDd5agdYfIc6DkCIRux6nPqKoCRqLsP389MNDU3NDEstMWVahkRp8dIpQtkSrK58M7gPR22mQkBw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHDd5agdYfIc6DkCIRux6nPsyCDfAJk8n2KLSE8Qkc3zybAG67siac0nibDMW3EcMeobhv6jfr0f0KA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHDd5agdYfIc6DkCIRux6nPhicZHg7qPN8T6icuE9bnOAiatnb2jLAPj4aWCicJAtpEDicQlFvIoTAicnHQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHDd5agdYfIc6DkCIRux6nPe6FJsiaYQIjGeKctIVjXvKPLiaImbbkaBtEY6sd54TbpoU6ts9d2VDvQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上面画的图是使用队列，但在代码中我们不直接使用队列，我们使用两个指针，一个指向队头一个指向队尾，和使用队列类似，我们来看下代码

```java
public int minSubArrayLen(int s, int[] nums) {
    int lo = 0, hi = 0, sum = 0, min = Integer.MAX_VALUE;
    while (hi < nums.length) {
        sum += nums[hi++];
        while (sum >= s) {
            min = Math.min(min, hi - lo);
            sum -= nums[lo++];
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```

<br/>

#### 3，使用队列（二）

上面使用的是相加的方式，也就是说队列中（或者是窗口中）的元素相加，然后判断是否大于等于s。其实我们还可以改为相减的方式，判断s是否小于等于0，其实基本原理和上面差不多，我们来看下

```java
public int minSubArrayLen(int s, int[] nums) {
    int lo = 0, hi = 0, min = Integer.MAX_VALUE;
    while (hi < nums.length) {
        s -= nums[hi++];
        while (s <= 0) {
            min = Math.min(min, hi - lo);
            s += nums[lo++];
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```

<br/>

#### 4，二分法查找

我们还可以申请一个临时数组sums，其中sums[i]表示的是原数组nums中前i个元素的和，题中说了“给定一个含有 n 个**正整数** 的数组”，既然是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。我们只需要找到**sums[k]-sums[j]>=s** ，那么**k-j** 就是满足的连续子数组，但不一定是最小的，所以我们要继续找，直到找到最小的为止。



怎么找呢，我们可以使用两个for循环来枚举，但这又和第一种暴力求解一样了，所以我们可以换种思路，求**sums[k]-sums[j]>=s** 我们可以求**sums[j]+s<=sums[k]** ，那这样就好办了，因为数组sums中的元素是递增的，也就是排序的，我们只需要求出sum[j]+s的值，然后使用二分法查找即可找到这个k值。

```java
public int minSubArrayLen(int s, int[] nums) {
    int length = nums.length;
    int min = Integer.MAX_VALUE;
    int[] sums = new int[length + 1];
    for (int i = 1; i <= length; i++) {
        sums[i] = sums[i - 1] + nums[i - 1];
    }
    for (int i = 0; i <= length; i++) {
        int target = s + sums[i];
        int index = Arrays.binarySearch(sums, target);
        if (index < 0)
            index = ~index;
        if (index <= length) {
            min = Math.min(min, index - i);
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```

注意这里的查找函数

Arrays.binarySearch(sums, target);

如果找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值应该在数组中的位置



举个例子，比如排序数组[2，5，7，10，15，18，20]如果我们查找18，因为数组中有这个数，所以会返回18的下标5，如果我们查找9，因为数组中没这个数，所以会返回-4（至于这个是怎么得到的，大家可以看下源码，这里不再过多展开讨论），我们对他取反之后就是3，也就是说如果我们在数组中添加一个9，他在数组的下标是3，也就是第4个位置（也可以这么理解，只要取反之后不是数组的长度，那么他就是原数组中第一个比他大的值的下标）

<br/>

#### 5，直接使用窗口

上面第2种解法我们使用的是使用两个指针，我们也可以把它看做是一个窗口，每次往窗口中添加元素来判断是否满足。其实我们可以逆向思维，先固定一个窗口大小比如leng，然后遍历数组，查看在数组中leng个元素的和是否有满足的，如果没有满足的我们就扩大窗口的大小继续查找，如果有满足的我们就记录下窗口的大小leng，因为这个leng不一定是最小的，我们要缩小窗口的大小再继续找……

```java
public int minSubArrayLen(int s, int[] nums) {
    int lo = 1, hi = nums.length, min = 0;
    while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        if (windowExist(mid, nums, s)) {
            hi = mid - 1;//找到就缩小窗口的大小
            min = mid;//如果找到就记录下来
        } else
            lo = mid + 1;//没找到就扩大窗口的大小
    }
    return min;
}

//size窗口的大小
private boolean windowExist(int size, int[] nums, int s) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i >= size)
            sum -= nums[i - size];
        sum += nums[i];
        if (sum >= s)
            return true;
    }
    return false;
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（208. 实现 Trie (前缀树)）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0208.md)

#### [下一题（210. 课程表 II）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0210.md)