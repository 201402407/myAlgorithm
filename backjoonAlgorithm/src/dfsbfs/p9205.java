package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기 
// 플로이드 알고리즘 (근데 BFS 써보자) 
public class p9205 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		while(t --> 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			
			Point9205[] point = new Point9205[n + 2];
			boolean[][] map = new boolean[n + 2][n + 2]; // 입력받은 순서대로 index를 매길 때, y, x 는 y index -> x index 로 이동할 때 가능한지 불가능한지 담아두는 배열
			
			for(int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				
				point[i] = new Point9205(x, y);
				
			}
			
			// 갈 수 있는지 체크 
			for(int i = 0; i < n + 1; i++) {
				Point9205 p1 = point[i];
				int p1X = p1.x;
				int p1Y = p1.y;
				for(int j = i + 1; j < n + 2; j++) {
					Point9205 p2 = point[j];
					
					// i index 지점부터 j index 지점까지 가능한 거리인지 체크 -> 가능하면 True
					if(Math.abs(p1X - p2.x) + Math.abs(p1Y - p2.y) <= 1000) {
						// 양방향 그래프 
						map[i][j] = true;
						map[j][i] = true;
					}
				}
			}
			
			if(bfs(map, n)) {
				sb.append("happy");
			}
			else {
				sb.append("sad");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// True: 방문 가능. False : 방문 불가능 
	static boolean bfs(boolean[][] map, int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[n + 2];
		visited[0] = true;
		queue.offer(0);	// 시작점 넣기
		
		while(!queue.isEmpty()) {
			int nowIndex = queue.poll();
			
			for(int i = 1; i < n + 2; i++) {	// 출발점으로 돌아갈 필요는 없으니까. 애초에 못감. 
				if(map[nowIndex][i]) {	// 이동 가능 여부
					if(i == n + 1) {	// 도착지점이면 종료 
						return true;
					}

					if(!visited[i]) {	// 이미 해당 편의점으로 방문한 적이 있는 경우 -> 굳이 한 번 더 큐에 넣고 경로 체크할 필요가 없다. 
						visited[i] = true;
						queue.offer(i);
					}
				}
			}
		}
		
		return false;
	}
}

class Point9205 {
	int x;
	int y;
	
	Point9205(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
