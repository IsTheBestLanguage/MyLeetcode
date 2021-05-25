# 排序
## 思想
<!-- GFM-TOC -->
* [Leetcode 题解 - 动态规划](#leetcode-题解---动态规划)
    * [合并多个结构](#合并多个结构)
        * [23. 合并k个升序链表](#23-合并k个升序链表)

<!-- GFM-TOC -->

### 23. 合并k个升序链表
```java
package zzy;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
	
	//merge O(kn log k)
	//k个链表 kn个节点， 合并2个表复杂度O(N)，k个表两两合并：每条链被合并log k 次，共 k log k 次；一共 O(kn log k)  
	public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }


}
```
