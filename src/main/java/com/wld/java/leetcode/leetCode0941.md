

## [941. 有效的山脉数组](https://leetcode-cn.com/problems/valid-mountain-array/)（简单）

给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

- A.length >= 3

- 在 0 < i < A.length - 1 条件下，存在 i 使得：
  - A[0] < A[1] < ... A[i-1] < A[i]
  - A[i] > A[i+1] > ... > A[A.length - 1]

![img](https://assets.leetcode.com/uploads/2019/10/20/hint_valid_mountain_array.png)

**示例 1：**

```
输入：[2,1]
输出：false
```

**示例 2：**

```
输入：[3,5,5]
输出：false
```

**示例 3：**

```
输入：[0,3,2,1]
输出：true
```



**提示：**

1. `0 <= A.length <= 10000`
2. `0 <= A[i] <= 10000 `



<br/>

### 答案：

可以使用两种指针，一个从左边找最高山峰，一个从右边找最高山峰，最后判断找到的是不是同一个山峰
![image.png](https://pic.leetcode-cn.com/1604367864-BSFQoM-image.png)

```java
    public boolean validMountainArray(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;
        //从左边往右边找，一直找到山峰为止
        while (left + 1 < len && A[left] < A[left + 1])
            left++;
        //从右边往左边找，一直找到山峰为止
        while (right > 0 && A[right - 1] > A[right])
            right--;
        //判断从左边和从右边找的山峰是不是同一个
        return left > 0 && right < len - 1 && left == right;
    }
```

看下运行结果
![image.png](https://pic.leetcode-cn.com/1604367905-TnPBfN-image.png)



<br>

![](https://img-blog.csdnimg.cn/20200807155236311.png)


