# 二分
## 思想
加速查找  
  
### 162. 寻找峰值

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
    
       
### 744. 寻找比目标字母大的最小字母 find-smallest-letter-greater-than-target   
二分；char可以直接比较；可以在二分后检查是否满足，无需专门处理循环比较   
```java
//raw
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if((int)letters[0] > (int)target || (int)letters[n-1] <= (int)target)  //beyond array
            return letters[0];
        
        int le = 0, ri = n-1;
        while(le<ri){
            int mid = le + ri >> 1;
            if((int)letters[mid] > (int)target ) {
                if(mid-1 >= 0 && (int)letters[mid] <= (int)target)
                    return letters[mid];
                else
                    ri = mid;
            }
            else  le = mid + 1;
        }
        return letters[le];
    }
}

//modified
class Solution {
    
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (letters[mid] > target) r = mid;
            else l = mid + 1;
        }
        return letters[r] > target ? letters[r] : letters[0];
    }
}


```
### 4. 寻找两个有序数组的中位数 median-of-two-sorted-arrays
二分
```java
```
