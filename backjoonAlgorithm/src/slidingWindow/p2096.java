package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다이나믹 프로그래밍 + 슬라이딩 윈도우
// 내려가기 문제 
// 즉, dp[i][k] 는 i층의 k번 째로 내려왔을 때의 최댓값 또는 최솟값 
// dp[i][k] = map[i][k] + Math.max(dp[i - 1][k], dp[i - 1][k + 1 또는 k 또는 k - 1] 
public class p2096 {
	static int[] map;
	static int[] dpMax;
	static int[] dpMin;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		dpMax = new int[3];	// 최댓값 DP 저장을 위한 2차원 배열. y : 1 ~ n 층을 나타냄. x : 0, 1, 2번째를 나타냄.  
		dpMin = new int[3];	// 최솟값 DP 저장을 위한 2차원 배열 
		map = new int[3];	// 내려가기 점수 맵. DP를 구하기 위한 최소 크기의 배열을 가지고 있다. 
		int[] temp = new int[3];
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
//			int realIndex = i % 2;
			// 입력 값 입력받기 
			for(int j = 0; j < 3; j++) { 	// 한 줄 입력받기 
				int ele = Integer.valueOf(st.nextToken());
//				map[realIndex][j] = ele;
				map[j] = ele;
			}
			
			// 초기 값 설정 
			if(i == 0) {
				dpMax[0] = map[0];
				dpMax[1] = map[1];
				dpMax[2] = map[2];
				
				dpMin[0] = map[0];
				dpMin[1] = map[1];
				dpMin[2] = map[2];
				continue;
			}
			
			// 최댓값 DP 구해서 임시배열에 담기  
			temp[0] = map[0] + Math.max(dpMax[0], dpMax[1]);
			temp[1] = map[1] + Math.max(dpMax[0], Math.max(dpMax[1], dpMax[2]));
			temp[2] = map[2] + Math.max(dpMax[1], dpMax[2]);
			// 배열 옮기기 
			dpMax[0] = temp[0];
			dpMax[1] = temp[1];
			dpMax[2] = temp[2];
			
			// 최솟값 DP 구해서 임시배열에 담기  
			temp[0] = map[0] + Math.min(dpMin[0], dpMin[1]);
			temp[1] = map[1] + Math.min(dpMin[0], Math.min(dpMin[1], dpMin[2]));
			temp[2] = map[2] + Math.min(dpMin[1], dpMin[2]);
			// 배열 옮기기 
			dpMin[0] = temp[0];
			dpMin[1] = temp[1];
			dpMin[2] = temp[2];
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		// 가장 최고값 도출 
		for(int ele : dpMax) {
			max = Math.max(max, ele);
		}
		
		// 가장 최솟값 도출 
		for(int ele : dpMin) {
			min = Math.min(min, ele);
		}
		
		System.out.println(max + " " + min);
	}
}
