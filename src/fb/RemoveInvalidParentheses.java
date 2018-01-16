package fb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
/**
 * BFS
 */
public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses2(String s){
		int rml = 0, rmr = 0;
		for(int i = 0;i<s.length();i++){
			if(s.charAt(i) == '(')	rml++;
			else if(s.charAt(i) == ')'){
				if(rml!=0)	rml--;
				else		rmr++;
			}
		}
		Set<String> result = new HashSet<>();
		dfs(s, 0, result, new StringBuilder(), rml, rmr, 0);
		return new ArrayList<String>(result);
		
	}

	
	private void dfs(String s, int i, Set<String> result, StringBuilder sb, int rml, int rmr, int open) {
		if(rml < 0 || rmr <0||open<0)	return;
		if(i == s.length()){
			if(rml == 0 && rmr == 0 && open==0)
				result.add(sb.toString());
			return;
		}
		char c = s.charAt(i);
		int len = sb.length();
		if(c=='('){
			dfs(s, i+1, result, sb, rml-1, rmr, open);
			dfs(s, i+1, result, sb.append(c), rml, rmr, open+1);
		}else if(c==')'){
			dfs(s, i+1, result, sb, rml, rmr-1, open);
			dfs(s, i+1, result, sb.append(c), rml, rmr, open-1);
		}else{
			dfs(s, i+1, result, sb.append(c), rml, rmr, open);
		}
		sb.setLength(len);
	}


	public List<String> removeInvalidParentheses(String s){
		List<String> result = new ArrayList<>();
		if(s == null)
			return result;
		
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		
		queue.add(s);
		visited.add(s);
		
		boolean found = false;
		while(!queue.isEmpty()){
			s = queue.poll();
			if(isValid(s)){
				result.add(s);
				found = true;
			}
			if(found)	continue;
			// produce all possible states
			for(int i=0; i<s.length();i++){
				if(s.charAt(i) != '(' && s.charAt(i)!=')') continue;
				String t = s.substring(0, i) + s.substring(i+1);
				if(!visited.contains(t)){
					queue.add(t);
					visited.add(t);
				}
			}
		}
		return result;
	}

	private boolean isValid(String s) {
		int count = 0;
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '(') count++;
			if(c == ')') {
				if(count == 0)
					return false;
				count--;
			}
		}
		return count == 0;
	}
	public static void main(String[] args) {
		System.out.println(new RemoveInvalidParentheses().removeInvalidParentheses2("()())()").toString());
	}
}
