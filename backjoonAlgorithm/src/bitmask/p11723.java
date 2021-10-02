package bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 집합
// 비트마스킹
public class p11723 {
	static final int MAX_SIZE = 20;
	static int s = ~((1 << (MAX_SIZE + 1)) - 1);	// 00000 00000 00000 00000(2)
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		StringBuilder sb = new StringBuilder();
		int n = Integer.valueOf(br.readLine());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int value = st.hasMoreTokens() ? Integer.valueOf(st.nextToken()) : -1;
			execute(command, value, sb);
		}
		
		System.out.println(sb.toString());
	}
	
	// 명령어 실행 함수
	static void execute(String command, int value, StringBuilder sb) {
		switch(command) {
			case "add":
				add(value);
				break;
			case "remove":
				remove(value);
				break;
			case "check":
				if(check(value)) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
				sb.append("\n");
				break;
			case "toggle":
				toggle(value);
				break;
			case "all":
				all();
				break;
			case "empty":
				empty();
				break;
		}
	}
	
	static void add(int value) {
		s |= 1 << value;
	}
	
	static void remove(int value) {
		s &= ~(1 << value);
	}
	
	static boolean check(int value) {
		int bit = 1 << value;
		return (s & bit) == bit;
	}
	
	static void toggle(int value) {
		if(check(value)) {
			remove(value);
		}
		else {
			add(value);
		}
	}
	
	static void all() {
		s = (1 << (MAX_SIZE + 1)) - 1;
	}
	
	static void empty() {
		s = ~((1 << (MAX_SIZE + 1)) - 1);
	}
}
