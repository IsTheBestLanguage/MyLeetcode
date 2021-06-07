# 动态规划

## 思想
将问题分割成具体相同结构的子问题，并

<!-- GFM-TOC -->
* [动态规划](#动态规划)
    * [斐波那契数列](#斐波那契数列)
        * [413. 等差数列划分](#413-等差数列划分)
    * [矩阵路径](#矩阵路径)
        * [1. xxxxxx](#1-矩阵的最小路径和)
        * [2. xxxxxx](#2-矩阵的总路径数)
    * [递增子序列](#递增子序列)
        * [646. 最长数对链](#646-最长数对链)
        * [2. 矩阵的总路径数](#2-矩阵的总路径数)
    
<!-- GFM-TOC -->

### 413. 等差数列划分  
  
动态规划：dp[i]保存以i结尾的等差数列的长度，若满足 nums[i] - nums[i-1] == nums[i-1] - nums[i-2] ，则形成等差数列；由于等差数列至少有3项，从 i = 2 开始遍历；可用三个变量优化空间

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


### 646-最长数对链  

动态规划：dp[i]保存以i结尾的链的最长长度，从已遍历过的的dp项里选取能形成数对链且最大的的项  
时间复杂度：遍历 n 个数据，每次遍历向前遍历至多 n-1 个数据，故为O(n)

```java
public int findLongestChain(int[][] pairs) {
	int length = pairs.length;
	if(length == 1) {
		return 1;
	}
	Arrays.sort(pairs,(o1,o2)->(o1[0]-o2[0]));
	
	int[] dp = new int[length];
	Arrays.fill(dp, 1);
	for(int i=1;i<length;++i) {
		for(int j=0;j<i;j++) {
			if(pairs[i][0] > pairs[j][1]) {
			dp[i] = Math.max(dp[i], dp[j]+1);				
			}
		}
	}
		
	int ans = 0;
	for (int i : dp) {
		ans = i > ans ? i : ans;
	}
	return ans;
		
    } 
 ```
 
 动态规划 + 贪心：在可取的的候选项里取第二个数最小的，即使得下一个可加入项的可选范围最大；  
 时间复杂度：遍历 n 个数据，每次遍历通过 O(log n) 时间找到候选项里第二数最小的，故为 O(n log n)
 ```java
 
   
