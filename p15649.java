package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M(1)
// 백트래킹 문제. 기본
// 순열 문제
public class p15649 {
	static int[] arr;
	static int n, m;
	static StringBuilder sb;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		sb = new StringBuilder();
		
		// 순열에 들어가는 값을 위해 배열 생성 및 초기화 
		arr = new int[n];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		
		permutation(0, new int[m], new boolean[n]);
		System.out.print(sb.toString());
	}
	
	// nPm 순열 
	// r : 뽑은 개수
	// index : 현재 인덱스 
	static void permutation(int r, int[] eleArr, boolean[] checked) {
		if(r == m) {
			for(int ele : eleArr) {
				if(ele == 0) {
					continue;
				}
				sb.append(ele + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 다시 처음부터 끝까지 순회하며 뽑아야 함
		for(int i = 0; i < n; i++) {
			if(!checked[i]) { // 비교해야 하기 때문 
				// 첨엔 뽑고 함수 재귀호출
				checked[i] = true;
				eleArr[r] = arr[i];
				permutation(r + 1, eleArr, checked);
				// 그다음 원상태 복귀시키고 다음꺼 뽑아보기 
				checked[i] = false;
			}
		}
	}
}
