# 动态规划

## 思想
将问题分割成具体相同结构的子问题，并

<!-- GFM-TOC -->
* [动态规划](#动态规划)
    * [斐波那契数列](#斐波那契数列)
        * [413. 等差数列划分](#413-等差数列划分)
    * [矩阵路径](#矩阵路径)
        * [1. 矩阵的最小路径和](#1-矩阵的最小路径和)
        * [2. 矩阵的总路径数](#2-矩阵的总路径数)
    
<!-- GFM-TOC -->

### 413. 等差数列划分
```java
package zzy;

public class Solution {
	
	//dp
	//0ms 36MB
	public int numberOfArithmeticSlices(int[] nums) {
		
		int pre = 0, cur = 0, sum = 0;
        for(int i=2;i<nums.length;i++) {
        	if(nums[i] - nums[i-1] == nums[i - 1] - nums[i - 2]) {
        		cur = 1 + pre;
        	}
        	else {
        		cur = 0;
        	}
        	pre = cur;
        	sum += pre;
        }
        return sum;
        
    }
}
```

### 1. 矩阵的最小路径和
sadad
asdasdsad
asdsadsada
jtyh
ertfwegfew
dfsdvsdvf
