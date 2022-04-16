## [57. 插入区间](https://leetcode-cn.com/problems/insert-interval/)（困难）

给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

**示例 1:**

```
输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]
```

**示例 2:**

```
输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
```

<br>

### 答案：

#### 1，左右不重合的添加到集合list中，中间合并添加到集合list中

这里我们人为把数组分为3部分，左边不重合的添加到集合list中，右边不重合的也添加到集合list中，然后再合并中间的，这里以示例2为例画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0057/1604454534-aFstvJ-image.png)

```java
     public int[][] insert(int[][] intervals, int[] newInterval) {
        //边界条件判断
        if (intervals.length == 0)
            return new int[][]{newInterval};
        List<int[]> resList = new ArrayList<>();

        //一个从左边开始找不重合的
        int left = 0;
        //一个从右边开始找不重合的
        int right = intervals.length - 1;

        //左边不重合的添加到list中
        while (left < intervals.length && intervals[left][1] < newInterval[0]) {
            resList.add(intervals[left++]);
        }

        //右边不重合的添加到list中
        while (right >= 0 && intervals[right][0] > newInterval[1]) {
            resList.add(left, intervals[right--]);
        }

        //下面一大坨是合并中间重合的，注意一些边界条件的判断
        int[] newArr = new int[2];
        newArr[0] = left >= intervals.length ? newInterval[0] : Math.min(intervals[left][0], newInterval[0]);
        newArr[1] = right < 0 ? newInterval[1] : Math.max(intervals[right][1], newInterval[1]);
        resList.add(left, newArr);

        //这一大坨是把list转二维数组
        int[][] resArr = new int[resList.size()][2];
        for (int i = 0; i < resList.size(); i++) {
            resArr[i] = resList.get(i);
        }
        return resArr;
    }
```

<br>

#### 2，从左边开始，逐步合并

第一种方式是先把两边不重合的添加到集合list中之后在合并中间的。这里还可以从左边开始把不重合的（如果有不重合的）添加到集合list中，如果遇到重合的就找出重合的范围然后再添加到集合中，最后再把后面不重合的（如果有）添加到集合list中

```java
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resList = new ArrayList<>();
        int i = 0;
        //先把前面不重合的添加到list中
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            resList.add(intervals[i++]);

        int mergeStar = newInterval[0];
        int mergeEnd = newInterval[1];
        //前面不重合的都添加到集合list中了，从这里开始就出现重合了，我们要找到重合的开始和结束值
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            mergeStar = Math.min(mergeStar, intervals[i][0]);
            mergeEnd = Math.max(mergeEnd, intervals[i][1]);
            i++;
        }
        //然后再把重合的添加到list中
        resList.add(new int[]{mergeStar, mergeEnd});

        //把剩下的在添加到集合list中
        while (i < intervals.length)
            resList.add(intervals[i++]);

        //这一大坨是把list转二维数组
        int[][] resArr = new int[resList.size()][2];
        for (int j = 0; j < resList.size(); j++) {
            resArr[j] = resList.get(j);
        }
        return resArr;
    }
```

<br>





![](https://img-blog.csdnimg.cn/20200807155236311.png)

