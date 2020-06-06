package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진탐색(=이분 탐색)
// 보석 상자
public class p2792 {
	static int[] colors;
	static int max;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		colors = new int[m];
		
		// 이진탐색 시 필요한 high(최대 범위) 를 입력할 때 구한다.
		for(int i = 0; i < m; i++) {
			colors[i] = Integer.parseInt(br.readLine());
			if(max < colors[i]) {
				max = colors[i];
			}
		}
		
//		Arrays.sort(colors);
		int min = search(n, m);
		System.out.println(min);
	}
	
	// 이진탐색으로 질투심 크기 범위를 구하고, 최소 크기를 얻는다.
	static int search(int n, int m) {
		int min = Integer.MAX_VALUE;
		int low = 1;	// 무조건 1명은 들어가야함(=모든 보석을 나눠줘야함)
		int high = max;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			int sum = 0;
			for(int ele : colors) {
				// 해당 mid 질투심을 얻기 위한 최소 인원 구하기.
				// 질투심보다 보석 개수가 작으면 1명만 있어도 되니까 + 1.
				// 단, 질투심 개수와 같을 경우 1명 추가하지 않아도 됨.
				int people = ele % mid == 0 ? ele / mid : ele / mid + 1;
				sum += people;	
			}
			
			if(n >= sum) { // 해당 인원 내에 질투심을 만들 수 있으면 가능
				min = Math.min(min, mid);	
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		
		return min;
	}
}