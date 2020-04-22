package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 상근날드
// 구현
public class p5543 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] hamburgers = new int[3];
		int[] drinks = new int[2];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			hamburgers[i] = Integer.valueOf(st.nextToken());	
		}

		for(int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			drinks[i] = Integer.valueOf(st.nextToken());	
		}

		Arrays.sort(hamburgers);
		Arrays.sort(drinks);
		
		System.out.println(hamburgers[0] + drinks[0] - 50);
	}
}
