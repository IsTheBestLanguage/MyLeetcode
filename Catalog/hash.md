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
    
    //1ms 39.2MB
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
其中 hash[x] 的含义为值不超过 x 的最大自除数在 list 中的下标   
```java
class Solution {

    static List<Integer> list = new ArrayList<>();
    static int[] hash = new int[10001];
    static {
        for (int i = 1; i <= 10000; i++) {
            int cur = i;
            boolean ok = true;
            while (cur != 0 && ok) {
                int u = cur % 10;
                if (u == 0 || i % u != 0) ok = false;
                cur /= 10;
            }
            if (ok) list.add(i);
            hash[i] = list.size() - 1;
        }
    }
    
    //4ms 38.8MB
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        int idx = list.get(hash[left]) == left ? hash[left] : hash[left] + 1;
        while (idx < list.size() && list.get(idx) <= right) ans.add(list.get(idx++));
        return ans;
    }
}


```
