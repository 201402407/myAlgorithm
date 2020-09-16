package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����
// �������� �˰��� 
public class p1613 {
	static boolean[][] history;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		history = new boolean[n + 1][n + 1]; // 1���� ���� 
		for(int i = 0 ; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.valueOf(st.nextToken());
			int after = Integer.valueOf(st.nextToken());
			history[before][after] = true;	// �ܹ��� �׷��� 
		}
		
		bellmanFord(n);
		
		StringBuilder sb = new StringBuilder();
		int s = Integer.valueOf(br.readLine());
		
		for(int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.valueOf(st.nextToken());
			int after = Integer.valueOf(st.nextToken());
			
			// ������ ������ ������ ���� üũ�ؾ� ��.
            boolean jung = history[before][after];
            boolean reverse = history[after][before];
            
            if(jung && !reverse) {
                sb.append("-1");
            }
            else if(!jung && reverse) {
                sb.append("1");
            }
            else if(!jung && !reverse) {
                sb.append("0");
            }
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// ����-���� �˰��� ���� 
	static void bellmanFord(int n) {
		for(int i = 1; i <= n; i++) {    // ���� ����
			for(int j = 1; j <= n; j++) { // ��� ����
                if(!history[j][i]) continue;
				for(int k = 1; k <= n; k++) { // ���� ����
                    if(history[i][k]) history[j][k] = true;
				}
			}
		}
	}
}