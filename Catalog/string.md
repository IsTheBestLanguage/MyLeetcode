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
动态规划   
O(n^2),   n 是字符串的长度。动态规划的状态总数为 O(n^2)，对于每个状态，我们需要转移的时间为 O(1)
```java
public class Solution {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
```
