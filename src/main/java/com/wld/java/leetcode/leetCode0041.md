## [41. 缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/)（困难）

给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

**示例 1:**

```
输入: [1,2,0]
输出: 3
```

**示例 2:**

```
输入: [3,4,-1,1]
输出: 2
```

**示例 3:**

```
输入: [7,8,9,11,12]
输出: 1
```

**提示：**

你的算法的时间复杂度应为O(*n*)，并且只能使用常数级别的额外空间。

<br/>

### 答案：

#### 1，暴力求解

道题最容易想到的就是暴力求解，我们从1到数组的长度一个个找，如果找到了就继续寻找下一个，如果没找到就直接返回，代码很简单，我们来看下

```java
public int firstMissingPositive(int[] nums) {
    for (int i = 1; i <= nums.length; i++) {
        boolean has = false;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == i) {
                has = true;
                break;
            }
        }
        if (!has) {
            //没有找到这个数，直接返回
            return i;
        }
    }
    return nums.length + 1;
}
```

在算法中我们只要听到**暴力** 二字，就知道这种答案效率肯定不会很高，一般这种答案在面试中非常不占优势

<br/>

#### 2，排序之后再查找

```java
public int firstMissingPositive(int[] nums) {
    int len = nums.length;
    Arrays.sort(nums);//先排序
    for (int i = 1; i <= len; i++) {
        int res = binarySearch(nums, i);
        //一个个查找，如果没找到就返回
        if (res == -1)
            return i;
    }
    return len + 1;
}

//二分法查找
private int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
        int mid = left + ((right - left) >> 1);
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

这种我们可以对数组排序之后再一个个查找，因为排序之后我们可以使用二分法查找，代码也很简单。我们再来看下一种，通过集合来一个个判断

<br/>

### 3，存放到集合中再查找

```java
public int firstMissingPositive(int[] nums) {
    int len = nums.length;
    Set<Integer> hashSet = new HashSet<>();
    for (int num : nums) {
        hashSet.add(num);
    }
    for (int i = 1; i <= len; i++) {
        if (!hashSet.contains(i))
            return i;
    }
    return len + 1;
}
```

我们可以把数组中的元素全部放到集合set中，然后在第7到10行，从1到数组长度一个个判断集合set中是否包含这个值，如果没有直接返回。这个也很好理解，下面我们再来看第四种解法

<br/>

#### 4，存放到对应的位置判断

我们还可以把数组中的数字放到对应下标的位置，然后再循环一遍，查看每个位置和对应的下标是否一致，如果不一致直接返回即可，我们就以[3，4，-1，1，7]为例来画个图分析一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFxxBRnvQ4TsAAjQujsfUldEGzBohlXD4fdIcA1ib6uMzxy8WbgyicyD5JfribBQDMVq1hicd5uBJk4tg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFxxBRnvQ4TsAAjQujsfUldI2uVB2oEm2gLt7X25picL02TLiatKvCkwHPqicRzJnDhsU8mYheslwRLA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFxxBRnvQ4TsAAjQujsfUldwnQr3UMJcW0EeXsBq0akj1NSlcMgJ7nn4eficWG0qwCD8ApufRwf9nw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
public int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        //如果在指定的位置就不需要修改
        if (i + 1 == nums[i])
            continue;
        int x = nums[i];
        if (x >= 1 && x <= nums.length && x != nums[x - 1]) {
            swap(nums, i, x - 1);
            i--;//抵消上面的i++，如果交换之后就不++；
        }
    }
    //最后在执行一遍循环，查看对应位置的元素是否正确，如果不正确直接返回
    for (int i = 0; i < nums.length; i++) {
        if (i + 1 != nums[i])
            return i + 1;
    }
    return nums.length + 1;
}

//交换两个数的值
public void swap(int[] A, int i, int j) {
    if (i != j) {
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }
}
```

<br/>

#### 5，位运算求解

位运算的实现原理和上面第4种答案类似，只不过这里使用的是位运算的方式来解决的。我们知道在java中一个int类型占4个字节是32位，我们可以申请一个数组，1到32我们可以存放到数组的第一个元素中，33到64可以存放到第2个元素中……，有的同学可能好奇，一个数字怎么可能存放32个数呢。因为一个int类型数字是32位的，也就是由32个0和1组成，我们只要统计1在存储中的位置即可，我们来看下代码。

```java
public int firstMissingPositive(int[] nums) {
    int length = nums.length;
    int bit[] = new int[(length - 1) / 32 + 1];
    for (int i = 0; i < nums.length; i++) {
        int digit = nums[i];
        //数组必须在1到length之间才有效
        if (digit >= 1 && digit <= length) {
            int index = (digit - 1) / 32;
            bit[index] = bit[index] | (1 << ((digit - 1) % 32));
        }
    }
    //最后在执行一遍循环，查看对应位置的元素是否正确，如果不正确直接返回
    for (int i = 0; i < nums.length; i++) {
        if ((bit[i / 32] & (1 << (i % 32))) == 0)
            return i + 1;
    }
    return length + 1;
}
```

<br/>

#### 6，填补替换

下面再来看最后一种解法，也是比较经典的，如果某个元素的值是无效的，他会让数组后面的元素填补过来，然后再判断。注释写在代码中了，有兴趣的也可以看下。

```java
public int firstMissingPositive(int[] nums) {
    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
        int index = nums[start] - 1;
        if (index == start) {
            //存放的位置正确
            start++;
        } else if (index < 0 || index > end || nums[start] == nums[index]) {
            //前面两个表示数字不在存放位置范围内，就让数组end位置的元素把这个无效的
            // 元素覆盖掉，后面一个表示的是index这个位置存放正确了就不需要在存放了
            nums[start] = nums[end--];
        } else {
            //把start对应的元素存放到正确的位置
            nums[start] = nums[index];
            nums[index] = index + 1;
        }
    }
    return start + 1;
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（40. 组合总和 II）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0040.md)

#### [下一题（42. 接雨水）(困难)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0042.md)