package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그리디 알고리즘
// 사과 담기 게임
public class p2828 {
	static int n, m, j, left, right;
	static int[] apples;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());	// 인덱스 1부터 시작
		m = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		j = Integer.valueOf(st.nextToken());
		apples = new int[j];
		
		for(int i = 0; i < j; i++) {
			apples[i] = Integer.valueOf(br.readLine());
		}
		
//		1) 바구니가 담긴 칸(왼 : 1, 오 : M)
//		2) 사과 떨어질 때 -> 인덱스 왼, 오범위 포함인지 확인
//		2-1) 범위 안일 때 -> 다음 사과 진행
//		2-2) 범위 밖일 때 -> left 보다 작으면 그 차이만큼 거리값 누적증가, 왼/오 인덱스 변경
//		2-3) right보다 크면 그 차이만큼 거리값 누적 증가, 왼/오 인덱스 변경
		
		int distance = 0;
		// 1) 바구니가 담긴 칸(왼 : 1, 오 : M)
		left = 1;
		right = m;
		
		for(int i = 0; i < j; i++) {
			int screenIndex = apples[i];
			// 2) 사과 떨어질 때 -> 인덱스 왼, 오범위 포함인지 확인
			// 2-1) 범위 안일 때
			if(left <= screenIndex && screenIndex <= right) {
				continue;
			}
			
			// 2-2) 범위 밖일 때
			int jDistance = 0;
			// 2-1) 사과 떨어지는 곳에 바구니 없으면 -> 양 옆으로 가장 가까운 바구니를 찾아, 절댓값(현재 인덱스 - 해당 인덱스) 을 거리 누적 합
			// 왼쪽 2-2) 범위 밖일 때 -> left 보다 작으면 그 차이만큼 거리값 누적증가, 왼/오 인덱스 변경
			if(left > screenIndex) {
				jDistance = Math.abs(screenIndex - left);
				left = screenIndex;
				right = left + m - 1;
			}
			// 
			// 오른쪽 2-3) right보다 크면 그 차이만큼 거리값 누적 증가, 왼/오 인덱스 변경
			else if(right < screenIndex) {
				jDistance = Math.abs(screenIndex - right);
				right = screenIndex;
				left = right - m + 1;
			}

			distance += jDistance;
		}
		
		System.out.println(distance);
		
		
	}
}
