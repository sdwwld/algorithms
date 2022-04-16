## [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)（中等）

给定一个包含 *m* x *n* 个元素的矩阵（*m* 行, *n* 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

**示例 1:**

```
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
```

**示例 2:**

```
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```

<br/>

### 答案：

逆时针打印，也就是下面这张图这样

![image.png](https://pic.leetcode-cn.com/1597976260-SlaOSS-image.png)

代码没什么难度，主要是在打印的时候做一些边界的判断，看下代码

```java
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return res;
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1, index = 0;
        while (true) {
            // 上面行，从左往右打印（行不变，改变列的下标）
            for (int col = left; col <= right; col++)
                res.add(index++, matrix[up][col]);
            if (++up > down)
                break;

            // 右边列，从上往下打印（列不变，改变行的下标）
            for (int row = up; row <= down; row++)
                res.add(index++, matrix[row][right]);
            if (--right < left)
                break;

            // 下面行，从右往左打印（行不变，改变列的下标）
            for (int col = right; col >= left; col--)
                res.add(index++, matrix[down][col]);
            if (--down < up)
                break;

            // 左边列，从下往上打印（列不变，改变行的下标）
            for (int row = down; row >= up; row--)
                res.add(index++, matrix[row][left]);
            if (++left > right)
                break;
        }
        return res;
    }
```

<br/>

再来看一种方式，就是每次打印的时候上面一行和下面一行都是完整打印，左边一列和右边一列打印的值是夹在上下两行之间的，打印一圈之后，再缩小圈的范围。和上面有一点点区别，但原理还是没变。

![image.png](https://pic.leetcode-cn.com/1597976332-HKaNge-image.png)

```java
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) return res;
        int n = matrix.length, m = matrix[0].length;
        int up = 0, down = n - 1;
        int left = 0, right = m - 1;
        int total = m * n;
        while (res.size() < total) {
            //上面，从左往右打印
            for (int j = left; j <= right && res.size() < total; j++)
                res.add(matrix[up][j]);
            //右边，从上往下打印(注意这里i的取值范围)
            for (int i = up + 1; i <= down - 1 && res.size() < total; i++)
                res.add(matrix[i][right]);
            //下边，从右往左打印
            for (int j = right; j >= left && res.size() < total; j--)
                res.add(matrix[down][j]);
            //左边，从下往上打印(注意这里i的取值范围)
            for (int i = down - 1; i >= up + 1 && res.size() < total; i--)
                res.add(matrix[i][left]);
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
```



参照：

[剑指 Offer 29. 顺时针打印矩阵](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer29.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 