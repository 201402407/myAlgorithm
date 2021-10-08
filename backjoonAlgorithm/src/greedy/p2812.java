package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 크게 만들기
// 그리디
public class p2812 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		String number = br.readLine();
		
		Stack<Integer> stack = new Stack<>();
		int deleteCount = 0;
		for(int i = 0; i < n; i++) {
			int num = Character.getNumericValue(number.charAt(i));
			while(!stack.isEmpty() && deleteCount < k && stack.peek() < num) {
				
				stack.pop();
				deleteCount++;
			}
			
			stack.push(num);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n - k; i++) {
			sb.append(stack.elementAt(i));
			
		}
		
		System.out.println(sb.toString());
	}
}
