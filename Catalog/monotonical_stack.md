# 单调栈

## 在一个数组中找左右两边第一个大于、小于、等于的数这种问题可以使用单调栈来解决  

### 907 子数组的最小值之和
找到规律：对任一数，左数m个数都比它大，右数n个数都比它大，产生的最小值之和为：(m+1)x(n+1)  
逐个进栈，当考察的当前数arr\[i\]小于栈顶，计算并栈顶出栈：  
此时栈里是递增的，计算栈顶index，若不空则m=index-新栈顶index'（下一个数一定比栈顶小，找之间的所有数），若空则m=index（之前的所有项都出栈了，一定比当前数大）；n=i-index    
否则当前数进栈

```java  
final int BASE = 1_000_000_007;

int sumSubarrayMins_1(int[] arr) {
	Stack<Integer> stack = new Stack<Integer>();
	int[] arrs = new int[arr.length+1];
	for(int i=0;i<arr.length;i++) arrs[i] = arr[i];
	arrs[arrs.length-1] = 0;
	long res = 0;
	for(int i=0;i<arrs.length;i++) {
		while(!stack.isEmpty() && arrs[stack.peek()] >= arrs[i]) {
			int index = stack.pop();
			int left = stack.isEmpty() ? index+1 : index - stack.peek();
			int right = i - index;
			res += (long)arrs[index]*left*right%BASE;
			res %= BASE;
		}
		stack.push(i);
	}
	return (int)res;
}
```
