package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p9095 {
	static int[] memory = new int[11];
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine()); 
	    try {
	    	int count = Integer.parseInt(st.nextToken());
		    for(int i = 0; i < count; i++) {
		    	st = new StringTokenizer(br.readLine());
			    int number = Integer.parseInt(st.nextToken());
			    if(number < 0 || number >= 11) {
			    	System.out.println("n의 범위가 맞지 않습니다.");
			    	System.exit(0);
			    }
			    System.out.println(getMethod(number));
		    }
	    }
	    catch(NumberFormatException e) {
	    	System.out.println("수가 아닌 값이 들어왔습니다.");
	    	System.exit(0);
	    }
	}
	
	// 5 = 4의 방법 + 3의 방법 + 2의 방법
	// memory[n] = getMethod(n - 1) + getMethod(n - 2) + getMethod(n - 3);
	private static int getMethod(int n) {
		if(n == 1) {
			return 1;
		}
		if(n == 2) {
			return 2;
		}
		if(n == 3) {
			return 4;
		}
		if(memory[n] != 0) {
			return memory[n];
		}
		else {
			memory[n] = getMethod(n - 1) + getMethod(n - 2) + getMethod(n - 3);
			return memory[n];
		}
	}
}
