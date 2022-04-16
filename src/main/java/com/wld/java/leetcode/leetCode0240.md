## [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)（中等）

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

**示例:**

现有矩阵 matrix 如下：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = `5`，返回 `true`。

给定 target = `20`，返回 `false`。

<br/>

### 答案：

#### 1，暴力求解

当然最容易想到的是暴力求解，就是一个个查找，如果找到就返回true，没找到就返回false，代码很简单，没什么可说的。

```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    int rows = matrix.length, columns = matrix[0].length;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            if (matrix[i][j] == target) {
                return true;
            }
        }
    }
    return false;
}
```

<br/>

#### 2，线性查找

题中说了每行都是递增的，每列也是递增的。所以我们查找的时候可以利用这个特性，如果我们从左上角开始找，当目标值target大于当前值的时候，我们需要往更大的找，但这个时候无论往右找还是往下找都是比当前值大，所以我们无法确定该往哪个方向找。同理右下角也一样，所以我们只能从右上角或者左下角开始找。我们就用上面的数据当target等于23的时候从右上角开始找，来画个图看一下

![image.png](https://pic.leetcode-cn.com/2514f408951415f07de5174dc0003d1e320905455709e959465d2259ca5d51d3-image.png)

从右上角开始找有个方便的地方就是他左边的都是比他小的，他下边的都是比他大的，如果target大于当前值我们就往下边找，如果target小于当前值我们就往左边找，来看下代码。

```java
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, col = matrix[0].length;
        //从第0行col - 1列开始查找，也就是第1行最后一列的那个数字开始
        int row = 0;
        int column = col - 1;
        while (row < rows && column >= 0) {
            //num表示当前值
            int num = matrix[row][column];
            if (num == target) {
                //如果找到直接返回
                return true;
            } else if (num > target) {
                //到前面查找
                column--;
            } else {
                //到下面查找
                row++;
            }
        }
        return false;
    }
```

当然从左下角查找也是可以的，因为左下角右边的值是比他大的，上边的值是比他小的，也能区分，代码和上面差不多，来看下

```java
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, col = matrix[0].length;
        int row = rows - 1;
        int column = 0;
        while (row >= 0 && column < col) {
            int num = matrix[row][column];
            if (num == target) {
                //如果找到直接返回
                return true;
            } else if (num > target) {
                //往上面找
                row--;
            } else {
                //往右边找
                column++;
            }
        }
        return false;
    }
```



参考：[剑指 Offer 4. 二维数组中的查找](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer04.md)

![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 