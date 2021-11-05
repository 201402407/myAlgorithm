package silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수 정렬하기 2
// 정렬
public class p2751 {
	static int n;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		arr = mergeSort(arr, new int[n], 0, n - 1);
		StringBuilder sb = new StringBuilder();
		for(int num : arr) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	/* 합병정렬 */
	public static int[] mergeSort(int[] arr, int[] temp, int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, temp, left, mid);
			mergeSort(arr, temp, mid + 1, right);
			
			merge(arr, temp, left, mid, right);
		}
		
		return arr;
	}
	public static void merge(int[] arr, int[] temp, int left, int mid, int right) {
		int pLeft = left;
		int pRight = mid + 1;
		int tempIndex = left;
		
		while(pLeft <= mid && pRight <= right) {
			// 더 작은 값을 합칠 때 앞에다 둔다(오름차순 정렬)
			if(arr[pLeft] < arr[pRight]) {
				temp[tempIndex++] = arr[pLeft++]; 
			}
			else {
				temp[tempIndex++] = arr[pRight++]; 
			}
		}
		
		while(pLeft <= mid) {
			temp[tempIndex++] = arr[pLeft++];
		}
		while(pRight <= right) {
			temp[tempIndex++] = arr[pRight++];
		}
		
		// 복사한 배열 옮기기
		for(int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}
	}
}
