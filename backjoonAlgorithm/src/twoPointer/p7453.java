package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 합이 0인 네 정수
// 정렬
// 이분탐색
// 투포인터
public class p7453 {
	static int n;
	static int[][] numbers;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		numbers = new int[4][n];	// a, b, c, d
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				int num = Integer.valueOf(st.nextToken());
				numbers[j][i] = num;
			}
		}
		
		int[] ab = new int[n * n];
		int[] cd = new int[n * n];
		int index = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int abSum = numbers[0][i] + numbers[1][j];
				int cdSum = numbers[2][i] + numbers[3][j];
				ab[index] = abSum;
				cd[index] = cdSum;
				index++;
			}
		}
		
		long result = 0;
		
		// AB 와 CD
		Arrays.parallelSort(ab);
		Arrays.parallelSort(cd);
		result += searchZero(ab, cd);
		System.out.println(result);
	}
	
	static long searchZero(int[] x, int[] y) {
		long count = 0;
		
		// 이분탐색?
		for(int i = 0; i < x.length; i++) {
			int xValue = -x[i];
			int result = 0;
			
			int upper = upperBound(y, xValue);	// xValue값보다 큰 원소의 첫 번째 리턴(없으면 해당 인덱스)
			int lower = lowerBound(y, xValue);	// xValue값보다 같거나 큰 원소의 첫 번째 리턴(같은 것 충 가장 첫 번째 원소도 해당)
			result = upper - lower;	// 이 두 값의 차이가 xValue의 개수와 같다.
			count += result;
		}
		
		return count;
	}
	
	// 특정 target보다 큰 첫 번째 원소의 인덱스 리턴
	private static int upperBound(int[] data, int target) {
	    int begin = 0;
	    int end = data.length;
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data[mid] <= target) {
	        	begin = mid + 1;
	        }
	        else {
	        	end = mid;
	        }
	    }
	    return end;
	}
	
	// 특정 target보다 크거나 같은 첫 번째 원소의 인덱스 리턴
	private static int lowerBound(int[] data, int target) {
	    int begin = 0;
	    int end = data.length;
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data[mid] >= target) {
	        	end = mid;
	        }
	        else {
	        	begin = mid + 1;
	        }
	    }
	    return end;
	}
}
