# 位运算
## 思想
利用特殊的位运算解决特定问题，大大减少空间复杂度

<!-- GFM-TOC -->
* [位运算](#位运算)
    * [tbd](#tbd)
        * [1. 爬楼梯](#1-爬楼梯)

<!-- GFM-TOC -->
  
## 136. 只出现一次的数字 single-number  
要求不使用额外的时间复杂度，容易想到位运算。利用异或的特性，相同的两数两两消除，最后剩下单独的数字。  
```java
class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for(int num:nums)
            single ^= num;
        return single;
    }
}
```
