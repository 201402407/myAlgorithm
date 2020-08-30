package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 그리디 + 문자열
// UCPC는 무엇의 약자일까? 
public class p15904 {
	static Queue<Character> queue;
	static char compareChar;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// UCPC 넣기 
		queue = new LinkedList<Character>();
		queue.offer('U');
		queue.offer('C');
		queue.offer('P');
		queue.offer('C');
		
		compareChar = queue.poll();
		boolean check = false;
		
		while(st.hasMoreTokens()) {
			String str = st.nextToken();
			for(int i = 0; i < str.length(); i++) {
				char alphabet = str.charAt(i);
				
				if(compareChar == alphabet) {
					if(queue.isEmpty()) {
						check = true;
						break;
					}
					
					compareChar = queue.poll();
				}
			}

			if(check) {
				break;
			}
		}
		
		if(check) {
			System.out.print("I love UCPC");
		}
		else {
			System.out.print("I hate UCPC");
		}
	}
}
