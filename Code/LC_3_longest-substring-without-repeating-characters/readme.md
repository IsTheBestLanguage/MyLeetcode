## resource code

题目：[无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

```java
package com.zzy;

import java.util.HashSet;

public class Solution {
	
	public static void main(String[] args) {
		String given = "";
		System.out.println("answer:");
		System.out.println(lengthOfLongestSubstring(given));
	}
	
	//first version
	static public int lengthOfLongestSubstring_First(String s) {
		
		if(s.equals(""))
			return 0;
		HashSet<Character> hs = new HashSet<Character>();
		
		int len = s.length();
		int sub_len = 1;
		
		for(int i=0;i<len;i++) {
			
			boolean flag = true;
			hs.clear();
			for(int j=i;j<len;j++) {
				char c = s.charAt(j);
				flag = hs.add(c);
				if(!flag) 
					break;
			}
			
			sub_len = Math.max(sub_len, hs.size());
		
		}
		
		return sub_len;
	}
	
	//second version
	static public int lengthOfLongestSubstring(String s) {
		
		if(s.equals(""))
			return 0;
		
		HashSet<Character> hs = new HashSet<Character>();
		
		int len = s.length();
		int sub_len = 1;
		int next = 0;
		
		for(int i=0;i<len;i++) {
			
			for(int j=next;j<len;j++) {
				if(!hs.add(s.charAt(j))) {
					next = j;
					break;
				}
			}
			
			sub_len=Math.max(sub_len, hs.size());
			hs.remove(s.charAt(i));
		}
		
		return sub_len;
	}
	
	
	//god_version
	public int lengthOfLongestSubstring_Third(String s) {
	    int maxSize = 0;
	    int [] dict = new int[256]; //记录ASCII 码字符出现的位置，以字符作为下标
	    int base = 0;
	    int key = 0;
	    int i=0;
	    while(key < s.length()){
	        i = s.charAt(key);
	        if(dict[i] > base)
	            base = dict[i];
	        dict[i] = key+1; //以1作为起始位置，下标加1
	        maxSize = (maxSize>key-base+1)?maxSize:key-base+1;
	        key++;
	    }
	    return maxSize;
	}
	
}
```
