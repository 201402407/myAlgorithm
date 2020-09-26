package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS + 투포인터 
// 집배원 한상덕 
public class p2842 {
	static char[][] map;
	static int[][] pirodoMap;
	static Integer[] pirodoArr;
	static int[] moveX = { 0, 1, 1, 1, 0, -1, -1, -1 };	// 시계 방향 
	static int[] moveY = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int pX, pY, n, myPirodo;
	static int count = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		map = new char[n][n];
		pirodoMap = new int[n][n];
		myPirodo = Integer.MAX_VALUE;
		
		// MAP 입력받기 
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				char ch = line.charAt(j);
				map[i][j] = ch;
				if(ch == 'K') {
					count++;
				}
				if(ch == 'P') {	// 시작지점 정하기 
					pX = j;
					pY = i;
				}
			}	
		}
		
		// 피로도 입력받음 
		HashSet<Integer> pirodo = new HashSet<Integer>();
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int thisPirodo = Integer.valueOf(st.nextToken());
				pirodoMap[i][j] = thisPirodo;
				pirodo.add(thisPirodo);
			}
		}
		
		// 2차원 피로도 배열 -> 1차원 중복없는 피로도 배열(HashSet 사용)
		int index = 0;
		pirodoArr = pirodo.toArray(new Integer[pirodo.size()]);
		
		// 오름차순 정렬
		Arrays.sort(pirodoArr);
		
		bfs(pirodo.size());
		System.out.println(myPirodo);
	}
	
	private static void bfs(int pirodoLen) {
		Queue<SangDuk> queue = new LinkedList<SangDuk>();
		boolean[][] visited = new boolean[n][n];	// 방문자 체크
		SangDuk start = new SangDuk(pX, pY);	// 시작점  
		int startPointer = 0;
		int endPointer = 0;
		
		// 투포인터 시작 
		while(startPointer <= endPointer && endPointer < pirodoLen) {
			queue.clear();

			boolean isFinish = false;
			int kCount = 0;
			// 투 포인터의 시작지점 ~ 끝지점 범위 내에 시작 피로도값이 포함되어야 한다. 그래야 시작점에서 출발할 수 있는 투포인터 부분배열 이라는 거.
			if(pirodoMap[pY][pX] >= pirodoArr[startPointer] && pirodoMap[pY][pX] <= pirodoArr[endPointer]) {
				queue.offer(start);
				visited[pY][pX] = true;
			}
			
			// 출발 시작 (기존에 돌아던 경로는 제외)
			while(!queue.isEmpty()) {
				SangDuk sangDuk = queue.poll();
				for(int i = 0; i < moveX.length; i++) {
					int nextX = sangDuk.x + moveX[i];
					int nextY = sangDuk.y + moveY[i];
					
					// 외부 벽 체크
					if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
						continue;
					}
					
					if(visited[nextY][nextX]) {
						continue;
					}
					
					// 현재 최댓값보다 해당지점의 피로도가 크거나, 현재 최솟값보다 해당지점의 피로도가 낮으면 다음꺼 확인하러 가기 
					if(pirodoArr[startPointer] > pirodoMap[nextY][nextX] || pirodoArr[endPointer] < pirodoMap[nextY][nextX]) {
						continue;
					}
					
					// 집 체크 전에 최대값/최소값 체크 
					if(map[nextY][nextX] == 'K') {
						kCount++;
					}
					
					visited[nextY][nextX] = true;
					queue.offer(new SangDuk(nextX, nextY));
				}	
			}
			
			if(count == kCount) {
				myPirodo = Math.min(myPirodo, pirodoArr[endPointer] - pirodoArr[startPointer]);
				startPointer++;
			}
			else {
				endPointer++;
			}
		}
	}
}

class SangDuk {
	int x;
	int y;
	
	SangDuk(int x, int y) {
		this.x = x;
		this.y = y;
	}
}