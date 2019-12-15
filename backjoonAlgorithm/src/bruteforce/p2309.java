package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 일곱 난쟁이
// 브루트 포스 -> 걍 조지기?
public class p2309 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<Integer> nanjaeng = new ArrayList<Integer>();
		int sum = 0;
		for(int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			int kee = Integer.valueOf(st.nextToken());
			nanjaeng.add(kee);
			sum += kee;
		}
		
		for(int i = 0; i < 8; i++) {
			int a = nanjaeng.get(i);
			int temp = sum - a;
			for(int j = 1; j < 9; j++) {
				int b = nanjaeng.get(j);
				if(temp - b == 100) {
					nanjaeng.remove(j);	// 무조건 j가 i보다 크므로 인덱스 변동 X
					nanjaeng.remove(i);
					Collections.sort(nanjaeng);
					for(int ele : nanjaeng) {
						System.out.println(ele);
					}
					return;
				}
			}
		}
	}
}
