package anything;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� �� �����ϴ� �κ� ���� 2
// LIS + �̺�Ž�� 
public class p12015 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[] arr = new int[n];
        // ó�� �� ���� 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        // �Է� ���ڸ��� ����ϱ�
		for(int i = 0; i < n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			arr[i] = ele;
		}
		
		System.out.println(binarySearch(arr, n));
	}
	
	public static int binarySearch(int[] arr, int size) {
		int[] dp = new int[size + 1];	// ���� �� �����ϴ� �κ� ����. ���� �ڿ� �ִ� ���� �κ� ���� �� ���� �ִ� 
		int dpLen = 0;
		dp[dpLen++] = arr[0];
		
		for(int i = 1; i < size; i++) {
			if(dp[dpLen - 1] < arr[i]) {	// ���� �� �����ϴ� �κм����� �ִ񰪺��� ū ��� �ڿ� ���� 
				dp[dpLen++] = arr[i];
			}
			else {
				// DP�迭 �̺�Ž��
				int start = 0;
				int end = dpLen;
				while(start <= end) {	
					int mid = (start + end) / 2;
					int midDpEle = dp[mid];
					
					if(midDpEle < arr[i]) {
						start = mid + 1;
					}
					else if(midDpEle >= arr[i]) {
						end = mid - 1;	
					}
				}
				
				// start < end �� ��� start + 1, start <= end �� ��� start 
				dp[start] = arr[i];
			}
		}
		
		return dpLen;
	}
}
