# 二分
## 思想
加速查找  
  
### 162 寻找峰值

一是注意边界，二是题目 “任何一个峰值 所在位置即可” 表明向左向右皆能找到答案
  
```java
public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        if(right == 1) return 0;
        if(right == 2) return nums[0] > nums[1] ? 0 : 1;
        while(left < right) {
        	int mid = left + (right - left)/2;
        	if(mid == 0 || mid == nums.length - 1) return mid;
        	if(nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1]) return mid;
        	else if(nums[mid] > nums[mid+1] && nums[mid] < nums[mid-1]) right = mid ;
        	else left = mid + 1;
        }
        return left;
    }
    ```
