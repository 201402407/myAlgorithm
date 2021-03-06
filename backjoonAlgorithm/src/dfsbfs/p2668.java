package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ?«?κ³ λ₯΄κΈ?
// DFS
public class p2668 {
	static int[] cards;
	static boolean[] checked;
	static boolean isCycle;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		// input κ°? λ°°μ΄? ???₯ 
		cards = new int[n + 1];
		checked = new boolean[n + 1]; // ?΄?Ή μΉ΄λ λ½μ?μ§? ?¬λΆ? ??Έ
		for(int i = 1; i <= n; i++) {
			cards[i] = Integer.valueOf(br.readLine());
		}
		
		// ?Έ?±?€ - κ°? ?΄ κ°μ? ? μ°ΎκΈ° -> λ¬΄μ‘°κ±? κ°κ°? μ§ν© ?? ??₯? μ£Όμ? ?κΈ? ?λ¬Έμ.
		// ? κ°μ΄ κ°μΌλ©? ?΄?€ μΉ΄λλ₯? λ½λ  ?΄ μΉ΄λλ₯? λ½μ?Ό μ΅λ? κ°μκ°? μ¦κ???€.
		for(int i = 1; i <= n; i++) {
			if(i == cards[i]) {
				checked[i] = true;
				continue;
			}
			
			if(checked[i]) {
				continue;
			}
			
			isCycle = false;
			
			// λ°±νΈ??Ή 
			checked[i] = true;
			getCycle(i, i, n);
			if(!isCycle) {
				checked[i] = false;	
			}
		}
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			if(checked[i]) {
				count++;
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	// ?¬?΄?΄ μ°Ύμ? λ¦¬μ€?Έ? ???₯?κΈ? 
	// DFS
	static void getCycle(int index, int last, int n) {
		int ele = cards[index];
		if(!checked[ele]) {
			checked[ele] = true;
			getCycle(ele, last, n);
			
			if(!isCycle) {
				checked[ele] = false;	
			}
		}
		
		if(ele == last) {
			isCycle = true;
		}
	}
}