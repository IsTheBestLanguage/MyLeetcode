# 字符串
## 思想

   
      
5. 最长回文子串 longest palindromic substring   

中心拓展（遍历回文子串中心）   
O(n^2),有n/n-1个中心，最多拓展O(n)次
   
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
动态规划，P(i,j)表示S[i,j]是否为回文字符串，递推公式为 P(i,j)=(P(i+1,j−1) && S[i]==S[j])
O(n^2),   n 是字符串的长度。动态规划的状态总数为 O(n^2)，对于每个状态，我们需要转移的时间为 O(1)   

```java
public String longestPalindrome(String s) {
    int length = s.length();
    boolean[][] P = new boolean[length][length];
    int maxLen = 0;
    String maxPal = "";
    for (int len = 1; len <= length; len++) //遍历所有的长度
        for (int start = 0; start < length; start++) {
            int end = start + len - 1;
            if (end >= length) //下标已经越界，结束本次循环
                break;
            P[start][end] = (len == 1 || len == 2 || P[start + 1][end - 1]) && s.charAt(start) == s.charAt(end); //长度为 1 和 2 的单独判断下
            if (P[start][end] && len > maxLen) {
                maxPal = s.substring(start, end + 1);
            }
        }
    return maxPal;
}

  
```
