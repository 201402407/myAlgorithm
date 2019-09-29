package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연속 합 문제
// 연속 합 부분에서는 이런식으로 사용하자
// 최댓값은 그대로 계속 비교하고, 연속한 합이 음수가 되면 새로 연속해서 더하기
// 왜냐하면, a + b + ... n 까지 음수일 때 양수를 더한 값보다 해당 수가 무조건 작기 때문.
public class p1912_bestAnswer {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int[] result = new int[n];
		st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for(int i = 0; i < n; i++) {
			result[i] = Integer.valueOf(st.nextToken());
			sum += result[i];
			max = Math.max(max, sum);
			if(sum < 0) {	// 음수로 가면 0으로 초기화. 다음 번 째 인덱스부터 시작
				sum = 0;
			}
		}
		System.out.println(max);
	}
}
