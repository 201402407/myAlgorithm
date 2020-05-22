package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 2146 : 다리 만들기
public class p2146 {
	static int[][] map;
	static boolean[][] visited;
	static List<ArrayList<Point>> list;
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		list = new ArrayList<ArrayList<Point>>();
		
		// 맵 그리기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int place = Integer.valueOf(st.nextToken());
				map[i][j] = place;
			}
		}
		
		// DFS
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					
					list.add(dfs(j, i, n, new ArrayList<Point>()));
				}
			}
		}
		
		// 다리 최단 길이 찾기
		int min = Integer.MAX_VALUE;
		int len = list.size();
		
//		for(Point p : list) {
//			System.out.println(p.x + " , " + p.y);
//		}
		
		// 다리 최소 길이 찾기
		for(int i = 0; i < len - 1; i++) {
			ArrayList<Point> list1 = list.get(i);
			for(int j = i + 1; j < len; j++) {
				ArrayList<Point> list2 = list.get(j);
				for(int a = 0; a < list1.size(); a++) {
					Point p1 = list1.get(a);
					for(int b = 0; b < list2.size(); b++) {
						Point p2 = list2.get(b);
						int gapX = Math.abs(p1.x - p2.x);
						int gapY = Math.abs(p1.y - p2.y);
						min = Math.min(min, Math.abs(gapX + gapY - 1));
					}
				}
			}
		}
		
		System.out.println(min);
	}
	
	// 붙어있는 육지 찾기 + 표면 찾기
	static ArrayList<Point> dfs(int x, int y, int n, ArrayList<Point> list) {
		if(visited[y][x]) {
			return null;
		}
		
		visited[y][x] = true;
		boolean isEdge = false;
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
				continue;
			}
			
			if(map[nextY][nextX] == 0) {
				isEdge = true;
				continue;
			}
			
			if(!visited[nextY][nextX] && map[nextY][nextX] == 1) {
				dfs(nextX, nextY, n, list);
			}
		}
		
		if(isEdge) {
			list.add(new Point(x, y));
		}
		
		return list;
	}
}

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
