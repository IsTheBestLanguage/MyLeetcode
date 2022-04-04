# 数据结构
## 掌握常见的特殊数据结构

### 307. 区域和检索-数组可修改 range-sum-query-mutable
线段树和树状数组对比
- 两者的时间\空间复杂度同级,但是树状数组的常数明显优于线段树
- 线段树的适用范围大于树状数组,凡是可以用树状数组解决的问题,使用线段树一定能解决.
- 树状数组的编程非常短,使用lowbit可以在几行代码内完成核心操作
  

**线段树**  
用于维护区间信息  
线段树可以在O(logn)的时间复杂度内实现单点修改、区间修改、区间查询（区间求和，求区间最大值，求区间最小值）等操作。  
线段树 segmentTree 是一个二叉树，每个结点保存数组 nums 在区间 [s,e] 的最小值、最大值或者总和等信息。线段树可以用树也可以用数组（堆式存储）来实现。对于数组实现，假设根结点的下标为 0，如果一个结点在数组的下标为 node，那么它的左子结点下标为 node×2+1，右子结点下标为 node×2+2


```java
class NumArray {
    Node[] tr;
    class Node {
        int l, r, v;
        Node(int _l, int _r) {
            l = _l; r = _r;
        }
    }
    void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) return;
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }
    void update(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v += v;
            return ;
        }
        int mid = tr[u].l + tr[u].r >> 1;
        if (x <= mid) update(u << 1, x, v);
        else update(u << 1 | 1, x, v);
        pushup(u);
    }
    int query(int u, int l, int r) {
        if (l <= tr[u].l && tr[u].r <= r) return tr[u].v;
        int mid = tr[u].l + tr[u].r >> 1;
        int ans = 0;
        if (l <= mid) ans += query(u << 1, l, r);
        if (r > mid) ans += query(u << 1 | 1, l, r);
        return ans;
    }
    void pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }

    int[] nums;
    public NumArray(int[] _nums) {
        nums = _nums;
        int n = nums.length;
        tr = new Node[n * 4];
        build(1, 1, n);
        for (int i = 0; i < n; i++) update(1, i + 1, nums[i]);
    }
    public void update(int index, int val) {
        update(1, index + 1, val - nums[index]);
        nums[index] = val;
    }
    public int sumRange(int left, int right) {
        return query(1, left + 1, right + 1);
    }
}

```
**树状数组**：binary indexed tree  
用于维护区间信息；
只能区域查询，不能区域更新； 
构造函数：O(nlogn),add 函数的时间复杂度是 O(logn),prefixSum 函数的时间复杂度是 O(logn)
参考：https://leetcode-cn.com/problems/range-sum-query-mutable/solution/-by-hu-ge-8-t4rn/
```java
class NumArray {
    private int[] tree;
    private int[] nums;

    public NumArray(int[] nums) {
        this.tree = new int[nums.length + 1];
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        add(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {  //前缀和之差
        return prefixSum(right + 1) - prefixSum(left);
    }
    
    //更新树状数组时使用 x += lowBit(x) 来寻找被影响的数组下标
    //查询树状数组时使用 x -= lowBit(x) 来寻找小于x的下一个区间
    private int lowBit(int x) {  //找到x的二进制数的最后一个1所表示的二进制
        return x & -x;
    }

    private void add(int index, int val) {
        while (index < tree.length) {
            tree[index] += val;
            index += lowBit(index);
        }
    }

    private int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowBit(index);
        }
        return sum;
    }
}

```
