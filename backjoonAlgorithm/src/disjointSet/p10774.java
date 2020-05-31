package disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// disjoint - set
// 저지 문제
public class p10774 {
	static boolean[] selected;
	static int[] jersey;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int j = Integer.parseInt(br.readLine());
		int a = Integer.parseInt(br.readLine());
		
		selected = new boolean[j + 1];
		jersey = new int[j + 1];
		
		// 저지 사이즈 입력
		for(int i = 1; i <= j; i++) {
			jersey[i] = getSizeNum(br.readLine());
		}
		
		// 학생들 입력
		StringTokenizer st;
		int count = 0;
		for(int i = 1; i <= a; i++) {
			st = new StringTokenizer(br.readLine());
			String size = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			
			// 누가 저지를 선택하지 않은 경우
			if(!selected[num]) {
				if(jersey[num] >= getSizeNum(size)) {
					selected[num] = true;
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
	
	static int getSizeNum(String size) {
		switch(size) {
		case "S":
			return 1;
		case "M":
			return 2;
		case "L":
			return 3;
		default:
			return 0;
		}
	}
}
