package zzy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Backtracking
//2ms 38.7MB
//没必要搞复杂的转换，善用包装类型
public class Solution {
	
	int[] candidate;
	
	public List<String> letterCombinations(String digits) {
		int size = digits.length();
		List<String> res = new ArrayList<>();
		if(size == 0)
			return res;
		
		candidate = new int[size];
		for(int i=0;i<size;i++) {
			candidate[i] = digits.charAt(i) - '0';
		}
		
		Map<Integer,List<Character>> table = init();
		//System.out.println(table);
		dfs(table,size,0,new ArrayDeque<>(),res);
		
		
		return res;
    }
	
	public Map<Integer,List<Character>> init(){
		Map<Integer,List<Character>> table = new HashMap<>();
		List<Character> temp = new ArrayList<Character>();
		List<Character> list = new ArrayList<Character>();
		 
		for(char letter = 'a'; letter <= 'z'; letter++)
			temp.add(letter);
		for(int i=2;i<10;i++) {
			int round = i==7 || i ==9 ? 4 : 3;
			for(int j=0;j<round;j++) {
				char let = temp.remove(0);
				list.add(let);
			}
			//System.out.println(list);
			table.put(i,new ArrayList<>(list));
			list.clear();
		}
		return table;
	}
	
	private void dfs(Map<Integer,List<Character>> map,
            		 int end,
            		 int begin,
            		 Deque<Character> path,
            		 List<String> res) {
		if(begin == end) {
			StringBuilder string = new StringBuilder();
			for(char c:path) {
				string.append(c);
			}
			res.add(string.toString());
			return;
		}
		
		int num = candidate[begin];
		int round = num ==7 || num ==9 ? 4 : 3;
		for(int i=0;i<round;i++) {
			path.addLast(map.get(num).get(i));
			dfs(map,end,begin+1,path,res);
			path.removeLast();
		}
	}
	
}
