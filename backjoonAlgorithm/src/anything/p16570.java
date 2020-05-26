package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 앞뒤가 맞는 수열
public class p16570 {
	static int[] seq, countSum;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		seq = new int[n + 1];
		countSum = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int num = Integer.valueOf(st.nextToken());
			seq[i] = num;
		}
		
		// 구하기
		for(int index = n - 1; index >= 1; index--) {
			int count = 0;
			int j = n;
			int i = index;
			while(seq[i--] == seq[j--]) {
				count++;
			}
			countSum[count]++;
		}
		
		// 최댓값 및 count 찾기
		boolean checked = false;
		
		for(int i = n; i >= 1; i--) {
			if(countSum[i] != 0) {
				System.out.println(i + " " + countSum[i]);
				checked = true;
				break;
			}
		}
		
		if(!checked) {
			System.out.println(-1);
		}
	}
}
