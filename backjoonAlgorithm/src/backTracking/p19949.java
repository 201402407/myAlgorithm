package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 영재의 시험
// 백트래킹
public class p19949 {
	static int count;
	static int[] solutions;
	static final int N = 10;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		solutions = new int[N];
		for(int i = 0; i < N; i++) {
			solutions[i] = Integer.valueOf(st.nextToken());
		}
		
		backTracking(new int[N], 0, 0);
		System.out.println(count);
	}
	
	static void backTracking(int[] mySolutions, int solutionCount, int index) {
		if(N - index + solutionCount < 5) {	// 남은 문제갯수를 다 풀어도 정답이 되지 못하는 경우
			return;
		}
		
		if(index == N) {	// 10문제를 찍음!
			if(solutionCount >= 5) {	// 5문제 이상 풀음!
				count++;
			}
		}
		else {
			int notAnswer = 0;
			if(index >= 2) {	// 이전에 2문제 이상 풀었으면 3연속 같은 정답을 적으면 안됨!
				if(mySolutions[index - 1] == mySolutions[index - 2]) {
					notAnswer = mySolutions[index - 1];
				}
			}
			// 5지선다(1, 2, 3, 4, 5)
			for(int i = 1; i <= 5; i++) {
				if(i == notAnswer) {	// 3연속 정답은 패스
					continue;
				}
				
				// 정답이면
				mySolutions[index] = i;
				if(solutions[index] == i) {
					backTracking(mySolutions, solutionCount + 1, index + 1);
				}
				else {	// 오답이면
					backTracking(mySolutions, solutionCount, index + 1);
				}
				mySolutions[index] = 0;	// 백트래킹 초기화
			}
		}
	}
}
