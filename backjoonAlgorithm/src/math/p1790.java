package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구현
// 수학
public class p1790 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
//		1. K가 A자리의 개수보다 작으면 종료.
//		2. A-1개수와 A개수 구하기
//		A자리 = N > 10^A -> 9 * 10^(A-1) * A
		int maxTime = String.valueOf(n).length();	// n이 가질 수 있는 최대 자리 수
		long[] countArr = new long[maxTime + 1];	// DP. index 자리까지 나올 수 있는 최대 개수
//		int maxCount = 9 * (int)Math.pow(10, maxTime - 1) * maxTime;	// n이 가질 수 있는 최대 개수
		int index = 0;
		boolean isValid = false;
		for(int i = 1; i <= maxTime; i++) {
			int count = 9 * (int)Math.pow(10, i - 1) * i;
			countArr[i] = countArr[i - 1] + count;	//
			if(k <= countArr[i]) {
				isValid = true;
				index = i;
				break;
			}
		}
		
		if(!isValid) {	// k번째 자리의 숫자가 없는 경우
			System.out.println(-1);
		}
		else {
//			3. A-1 * m + k = nowK가 되는 m의 범위 구하기(이분탐색)
//			그럼, m은 최소 0부터 최대 countArr[A] - countArr[A-1] / A-1 까지고,
//			nowK에 맞는 m의 범위를 찾기 위해 이분탐색
			if(index == 1) {	// 1의 자리
				System.out.println(n >= k ? k : -1);
				return;
			}
			
			// A - 1자리의 전체개수까지 빼기 -> A-1자리중 가장 작은 숫자부터 시작해서 어떤 숫자안에서 찾는지 확인하기 위함
			// 1을 더한 이유는 0부터 index하려고
			long nowK = k - (countArr[index - 1] + 1);	
			long countForNumber = binarySearch(index, nowK, 0, (countArr[index] - countArr[index - 1]) / (index - 1)); // A - 1자리 중 가장 첫 숫자에서 시작해서 몇 번째 숫자인지 확인하기 위함
			if(countForNumber > n - ((int)Math.pow(10, index - 1))) {
				System.out.println(-1);
				return;
			}
			
			// 해당 숫자의 앞에서 몇 번째의 숫자인지 확인하기 위함
			// countFor
			long number = (int)Math.pow(10, index - 1) + countForNumber;	// k가 위치한 숫자
			long countForFrontTime = nowK % index;	//  k가 위치한 숫자는 앞에서 몇 번째인지?
			
			int result = Character.getNumericValue(String.valueOf(number).charAt((int)countForFrontTime));
			System.out.println(result);
		}
	}
	
	// 이분탐색으로 A자리 * mid <= k < A자리 *(mid + 1) 을 만족하는 mid를 구함
	static long binarySearch(int index, long nowK, long left, long right) {
		long mid = (left + right) / 2;
		while(left <= right) {
			mid = (left + right) / 2;
			long m = mid * index;
			if(m < nowK) {	// mid번째 자리의 수 보다 다음 숫자들에 해당되면	
				left = mid + 1;
			}
			else if(m == nowK) {
				return mid;
			}
			else {
				right = mid - 1;
			}
		}
		
		return mid;
	}
}
