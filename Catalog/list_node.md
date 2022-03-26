# 链表
## 思想
注意指针是否为空（访问失效等）   
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
小技巧：对于链表问题，返回结果为头结点时，可以先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于简化处理头节点和非头节点的情况，最后答案返回pre->next即可

   
```java
class Solution {

    //1ms
    //41.5MB
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
