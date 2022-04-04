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
利用二分加速查找，问题转化为寻找第k小的数,k=(m+n)/2或(m+n)/2+1,注意奇数和偶数情况    
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            
            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}

```
