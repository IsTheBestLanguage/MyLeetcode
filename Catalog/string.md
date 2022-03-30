# 字符串
## 思想

   
      
5. 最长回文子串 longest palindromic substring
中心拓展（遍历回文子串中心）
```java
class Solution {

   //57ms
   //42.4MB
    public String longestPalindrome(String s) {

        if(s.length()==1)
            return s;
        if(s.length()==2)
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0,1);

        int max = 1;
        String ans = s.substring(0,1);
        for(int i=1;i<s.length();i++){
            int count = 1;
            int left = i-1, right = i+1;
            while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){  //odd
                count+=2;
                if(count>max){
                    max = count;
                    ans = s.substring(left,right+1);
                }
                left--;
                right++;
            }
            
            if(s.charAt(i) == s.charAt(i-1)){  //even
                if(max==1)
                    ans = s.substring(i-1,i+1);
                int le = i-2, ri = i+1;
                count = 2;
                while(le>=0 && ri<s.length() && s.charAt(le)==s.charAt(ri)){  
                    count+=2;
                    if(count>max){
                        max = count;
                        ans = s.substring(le,ri+1);
                    }
                    le--;
                    ri++;
                }
            }
            
        }
        return ans;
    }
}
```
