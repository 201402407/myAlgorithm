package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���̳��� ���α׷��� + �����̵� ������
// �������� ���� 
// ��, dp[i][k] �� i���� k�� °�� �������� ���� �ִ� �Ǵ� �ּڰ� 
// dp[i][k] = map[i][k] + Math.max(dp[i - 1][k], dp[i - 1][k + 1 �Ǵ� k �Ǵ� k - 1] 
public class p2096 {
	static int[] map;
	static int[] dpMax;
	static int[] dpMin;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		dpMax = new int[3];	// �ִ� DP ������ ���� 2���� �迭. y : 1 ~ n ���� ��Ÿ��. x : 0, 1, 2��°�� ��Ÿ��.  
		dpMin = new int[3];	// �ּڰ� DP ������ ���� 2���� �迭 
		map = new int[3];	// �������� ���� ��. DP�� ���ϱ� ���� �ּ� ũ���� �迭�� ������ �ִ�. 
		int[] temp = new int[3];
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
//			int realIndex = i % 2;
			// �Է� �� �Է¹ޱ� 
			for(int j = 0; j < 3; j++) { 	// �� �� �Է¹ޱ� 
				int ele = Integer.valueOf(st.nextToken());
//				map[realIndex][j] = ele;
				map[j] = ele;
			}
			
			// �ʱ� �� ���� 
			if(i == 0) {
				dpMax[0] = map[0];
				dpMax[1] = map[1];
				dpMax[2] = map[2];
				
				dpMin[0] = map[0];
				dpMin[1] = map[1];
				dpMin[2] = map[2];
				continue;
			}
			
			// �ִ� DP ���ؼ� �ӽù迭�� ���  
			temp[0] = map[0] + Math.max(dpMax[0], dpMax[1]);
			temp[1] = map[1] + Math.max(dpMax[0], Math.max(dpMax[1], dpMax[2]));
			temp[2] = map[2] + Math.max(dpMax[1], dpMax[2]);
			// �迭 �ű�� 
			dpMax[0] = temp[0];
			dpMax[1] = temp[1];
			dpMax[2] = temp[2];
			
			// �ּڰ� DP ���ؼ� �ӽù迭�� ���  
			temp[0] = map[0] + Math.min(dpMin[0], dpMin[1]);
			temp[1] = map[1] + Math.min(dpMin[0], Math.min(dpMin[1], dpMin[2]));
			temp[2] = map[2] + Math.min(dpMin[1], dpMin[2]);
			// �迭 �ű�� 
			dpMin[0] = temp[0];
			dpMin[1] = temp[1];
			dpMin[2] = temp[2];
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		// ���� �ְ� ���� 
		for(int ele : dpMax) {
			max = Math.max(max, ele);
		}
		
		// ���� �ּڰ� ���� 
		for(int ele : dpMin) {
			min = Math.min(min, ele);
		}
		
		System.out.println(max + " " + min);
	}
}
