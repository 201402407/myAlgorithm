package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����Ž��(LIS �˰���(DP) + ����Ž��)
// ���� �� �����ϴ� �κ� ���� 3
public class p12738 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
	
		System.out.println(binarySearch(arr, n));
	}
	
	static int binarySearch(int[] arr, int size) {
		int[] dp = new int[size + 1];
		int dpLen = 0;
		dp[dpLen++] = arr[0];	// �ʱ� ���� �� ����
		
		for(int i = 1; i < size; i++) {
			if(dp[dpLen - 1] < arr[i]) {	// ����, �ش� �ε����� ���� dp �迭�� ����� �ִ� ������ ũ�� �� �����ʿ� �߰�
				dp[dpLen++] = arr[i];
			}
			// �̺�Ž������ ������ �����ϴ� �κм��� ��ġ ã��
			// �����ϴ� �κм����̱� ������ �������� ������ �Ǿ��ִ�. -> �̺�Ž�� ����
			else {
				int start = 0;
				int end = dpLen;
				
				while(start <= end) {
					int mid = (start + end) / 2;
					
					if(dp[mid] < arr[i]) {
						start = mid + 1;
					}
					else {
						end = mid - 1;
					}
				}
				
				dp[start] = arr[i];
			}
		}
		
		return dpLen;
	}
}
