# 链表
## 思想
遍历不等长链表的方法：
```java
while(l1!=null || l2!=null){
    //操作
    
    if(l1!=null)
        l1 = l1.next;

    if(l2!=null)
        l2 = l2.next;
}
```
2. 两数之和 add two numbers
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode answer = null;
        ListNode pt = answer;
        int flag = 0;
        
        while(l1!=null || l2!=null){
            int oneval = l1==null ? 0 : l1.val;
            int twoval = l2==null ? 0 : l2.val;
            int val = oneval + twoval + flag;
            ListNode temp = new ListNode(val%10);
            flag = val > 9 ? 1 : 0;
            if(answer==null){
                answer = temp;
                pt = answer;
            }
                
            else {
                pt.next = temp;
                pt = pt.next;
            }

            if(l1!=null)
                l1 = l1.next;

            if(l2!=null)
                l2 = l2.next;
        }

        if(flag==1)
            pt.next = new ListNode(1);

        return answer;
    }
}
```
