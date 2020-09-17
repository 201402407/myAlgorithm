package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� ����
// ���� ���� �˰��� 
public class p2224 {
	static final int SIZE = 52;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		
		StringTokenizer st;
		boolean[][] map = new boolean[SIZE][SIZE];	// A - Z, a - z ���� 52�� 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " => ");
			char chA = st.nextToken().charAt(0);
			char chB = st.nextToken().charAt(0);
			int indexA = chA <= 90 ? chA - 'A' : chA - 'a' + 26;
			int indexB = chB <= 90 ? chB - 'A' : chB - 'a' + 26;
			
			map[indexA][indexB] = true;
		}
		
		bellmanFord(map);
		int count = 0;
		StringBuilder sb = new StringBuilder();
		// ��ȸ�ϸ鼭 ������ ����(True) �� ȹ�� 
		for(int i = 0; i < SIZE; i++) {
			int indexA = i >= 26 ? i + 'a' - 26 : i + 'A';
			for(int j = 0; j < SIZE; j++) {
				int indexB = j >= 26 ? j + 'a' - 26 : j + 'A';
				
				if(map[i][j] && i != j) {
					count++;
					sb.append((char) indexA).append(" => ").append((char) indexB);
					sb.append("\n");
				}
			}
		}
		
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	// �������� �˰��� 
	static void bellmanFord(boolean[][] map) {
		for(int k = 0; k < SIZE; k++) {	// ���� ���� 
			for(int i = 0; i < SIZE; i++) {	// ��� ����
				if(!map[i][k]) continue;
				for(int j = 0; j < SIZE; j++) {	// ���� ���� 
					if(map[k][j] && i != j) {
						map[i][j] = true;
					}
				}
			}
		}
	}
}
