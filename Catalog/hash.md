# 哈希
## 思想
利用哈希表储存数据，空间换时间，加速查找
   
   
727. 自除数   
打表+二分   
```java
class Solution {
    static List<Integer> list = new ArrayList<Integer>();
    static {
        for(int i=1;i<10000;i++){
            int cur = i;
            boolean flag = true;
            while(cur!=0){
                int div = cur%10;
                if(div == 0 || i % div != 0) {
                    flag = false;
                    break;
                }
                cur /= 10;
            }
            if(flag) list.add(i);
        }
    }
    public List<Integer> selfDividingNumbers(int left, int right) {
        int mid, n = list.size()-1;
        int le = 0, ri = n;
        while(le<ri){
            mid = le + ri >> 1;
            if(left>list.get(mid)) le = mid + 1;
            else ri = mid;
        }
        List<Integer> ans = new ArrayList<Integer>();
        while(le<n && list.get(le)<=right) ans.add(list.get(le++));
        return ans;
    }
}
```
打表+哈希   
其中 hash[x]hash[x] 的含义为值不超过 xx 的最大自除数在 list 中的下标   
```java

```
