package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 명제 증명
// 벨만 포드 알고리즘 
public class p2224 {
	static final int SIZE = 52;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		
		StringTokenizer st;
		boolean[][] map = new boolean[SIZE][SIZE];	// A - Z, a - z 개수 52개 
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
		// 순회하면서 명제가 참인(True) 것 획득 
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
	
	// 벨만포드 알고리즘 
	static void bellmanFord(boolean[][] map) {
		for(int k = 0; k < SIZE; k++) {	// 경유 지점 
			for(int i = 0; i < SIZE; i++) {	// 출발 지점
				if(!map[i][k]) continue;
				for(int j = 0; j < SIZE; j++) {	// 도착 지점 
					if(map[k][j] && i != j) {
						map[i][j] = true;
					}
				}
			}
		}
	}
}
