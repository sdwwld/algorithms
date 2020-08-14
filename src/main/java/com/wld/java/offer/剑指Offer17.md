## [剑指 Offer 17. 打印从1到最大的n位数](https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)（简单）

输入数字 `n`，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

**示例 1:**

```
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]
```

<br/>

说明：

- 用返回一个整数列表来代替打印
- n 为正整数

<br/>

### 答案：

直接求出n位数的最大值，然后从1开始打印即可，没什么难度，看下代码

```java
public int[] printNumbers(int n) {
    //统计总共需要打印多少个数字
    int size = (int) Math.pow(10, n) - 1;
    int[] res = new int[size];
    for (int i = 0; i < size; i++) {
        res[i] = i + 1;
    }
    return res;
}
```



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（16. 数值的整数次方）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer16.md)

#### [下一题（18. 删除链表的节点）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer18.md)