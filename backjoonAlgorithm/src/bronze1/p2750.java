package bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수 정렬하기 1
// 정렬
public class p2750 {
	static int n;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		quickSort(arr, 0, n - 1);
		StringBuilder sb = new StringBuilder();
		for(int num : arr) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 퀵정렬
	public static void quickSort(int[] arr, int curLeft, int curRight) {
		int left = curLeft;
		int right = curRight;
		int pivot = arr[(left + right) / 2];	// 피봇 생성(중간 값)
		
		// left와 right가 반대가 되는 시점 전까지 진행
		do {
			// left로 pivot보다 큰 값을 찾을 때 까지 탐색
			while(arr[left] < pivot) {
				left++;
			}
			
			// right로 pivot보다 작은 값을 찾을 때 까지 탐색
			while(arr[right] > pivot) {
				right--;
			}
			
			// 두 값 위치 변경
			if(left <= right) {
				int temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;
				left++;
				right--;
			}
		} while(left <= right);
		
		if(left < curRight) {
			quickSort(arr, left, curRight);
		}
		if(right > curLeft) {
			quickSort(arr, curLeft, right);	
		}
	}
}
