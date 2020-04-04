package dfsnfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 보물섬 문제
//DFS + BFS
public class p2589 {
	static boolean[][] map;
	static HashSet<Integer> results;
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int y = Integer.valueOf(st.nextToken());
		int x = Integer.valueOf(st.nextToken());
		map = new boolean[y][x];
		results = new HashSet<Integer>();
		
		for(int i = 0; i < y; i++) {
			String str = br.readLine();
			for(int j = 0; j < x; j++) {
				char ch = str.charAt(j);
				if(ch == 'W') {
					map[i][j] = true;
				}
			}
		}
		
		int result = 0;
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				if(!map[i][j]) {
					result = Math.max(result, getGold(i, j, y, x));
				}
			}
		}
		System.out.println(result);
	}
	
	// BFS를 이용해서 최장거리 구하기 
	private static int getGold(int y, int x, int maxY, int maxX) {
		boolean[][] visited = new boolean[maxY][maxX];
		int[][] weights = new int[maxY][maxX];
		
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.offer(new Vertex(x, y));
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Vertex v = q.poll();
			int nowX = v.x;
			int nowY = v.y;
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = nowX + moveX[i];
				int nextY = nowY + moveY[i];
				
				if((nextX < 0 || nextX >= maxX) || (nextY < 0 || nextY >= maxY)) {
					continue;
				}
				
				if(visited[nextY][nextX] || map[nextY][nextX]) {
					continue;
				}
				
				visited[nextY][nextX] = true;
				weights[nextY][nextX] = weights[nowY][nowX] + 1;
				q.add(new Vertex(nextX, nextY));
			}
		}
		
		// 최장 시간 찾기
		int result = 0;
		for(int i = 0; i < maxY; i++) {
			for(int j = 0; j < maxX; j++) {
				result = Math.max(result, weights[i][j]);
			}
		}
		
		return result;
	}
	
	// Java8에서 사용 가능. 2차원배열 깊은복사 함수.
//	private static boolean[][] arrayCopy_java8(boolean[][] aSource) {
//		return Arrays.stream(aSource).map(boolean[]::clone).toArray(boolean[][]::new);
//	}
}

class Vertex {
	int x;
	int y;
	
	Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
