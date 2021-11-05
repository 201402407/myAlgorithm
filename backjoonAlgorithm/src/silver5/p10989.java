package silver5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 수 정렬하기 3
// 정렬
public class p10989 {
	static int n;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		radixSort(arr, n);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int num : arr) {
			bw.write(num + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	/* 기수정렬 */
	public static void radixSort(int[] arr, int n) {
		int max = getMax(arr);

		// 최대값의 자리수만큼 countsort
		// exp: 자리 수(1, 10, 100 ...)
		for (int exp = 1; max / exp > 0; exp *= 10) {
			countSort(arr, n, exp);
		}
	}
	// 작은 자리수부터 정렬
	// exp: 자리 수
	static void countSort(int[] arr, int n, int exp) {
		int[] output = new int[n];	// 정렬된 배열을 담기 위한 공간
		int[] count = new int[10];	// 0~9까지의 자리수에 따른 정렬을 위한 별도 배열
		Arrays.fill(count, 0);	// 초기 0으로 설정
		
	    //발생횟수를 count[] 저장
	    for (int i = 0; i < n ; i++) {
	        count[(arr[i] / exp) % 10]++;
	    }
	    
	    //count[i]에 output[]의 숫자의 실제 위치가 포함되도록 count[i]를 변경
	    for (int i = 1; i < 10; i++) {
	    	count[i] += count[i - 1];
	    }
	    
	    //출력 배열 저장
	    for (int i = n - 1 ; i >= 0; i--) {
	       output[count[(arr[i] / exp) % 10] - 1] = arr[i];
	       count[(arr[i] / exp) % 10]--;
	    }
	    
	    // output[]을 arr[]에 복사
	    // arr []에 현재 배열에 따라 정렬 된 숫자가 포함됨
	    for (int i = 0; i < n; i++) {
	        arr[i] = output[i];
	    }
	}
	// 배열의 최대값을 갖는 함수를 통해 배열의 최댓값 찾음
	static int getMax(int[] arr) {
		if(arr.length == 0) {
			return 0;
		}
		
		int max = arr[0];
		for(int num : arr) {
			max = Math.max(max, num);
		}
		
		return max;
	}
}
