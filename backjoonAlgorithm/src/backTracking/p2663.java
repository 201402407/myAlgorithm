package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 좋은수열
// 백트래킹 문제
public class p2663 {
	static int[] arr;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		arr = new int[n];
		arr[0] = 1;
		
		for(int i = 1; i < n; i++) {
//			System.out.println(i);
			backTracking(n, i);
		}
		
		System.out.println(Arrays.toString(arr).replaceAll("[^0-9]",""));
	}
	
	// 만약 2번째면(parameter + 1) 1부터 번째 / 2를 한 값(중복 수열) 개수까지 for문으로 개수를 뒤로 가서(인접한 수열이 같은지 찾는 것이므로) 같은지 확인.
	// 같은 것이 없으면 그 것이 가장 작은 수열
	public static void backTracking(int n, int index) {
		int len = (index + 1) / 2;
		boolean check = false;
		for(int num = 1; num <= 3; num++) {
//			System.out.println(num);
			if(check) {
				break;
			}
			if(arr[index - 1] == num) {
				continue;
			}
			arr[index] = num;	// 임시로 넣어두기
			boolean isPossible = true;
			
			for(int i = 1; i < len; i++) {
				int[] nowElement = Arrays.copyOfRange(arr, index - i, index);
				int[] prevElement = Arrays.copyOfRange(arr, index - i - 1 - i, index - i - 1);
				if(Arrays.toString(nowElement).equals(Arrays.toString(prevElement).toString())) {	// 인덱스가 생성되기 전이므로
					isPossible = false;
					break;
				}
			}
			
			if(isPossible) {
				break;
			}
		}
	}
}
