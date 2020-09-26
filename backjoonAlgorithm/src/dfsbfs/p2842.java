package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS + �������� 
// ����� �ѻ�� 
public class p2842 {
	static char[][] map;
	static int[][] pirodoMap;
	static Integer[] pirodoArr;
	static int[] moveX = { 0, 1, 1, 1, 0, -1, -1, -1 };	// �ð� ���� 
	static int[] moveY = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int pX, pY, n, myPirodo;
	static int count = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		map = new char[n][n];
		pirodoMap = new int[n][n];
		myPirodo = Integer.MAX_VALUE;
		
		// MAP �Է¹ޱ� 
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				char ch = line.charAt(j);
				map[i][j] = ch;
				if(ch == 'K') {
					count++;
				}
				if(ch == 'P') {	// �������� ���ϱ� 
					pX = j;
					pY = i;
				}
			}	
		}
		
		// �Ƿε� �Է¹��� 
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
		
		// 2���� �Ƿε� �迭 -> 1���� �ߺ����� �Ƿε� �迭(HashSet ���)
		int index = 0;
		pirodoArr = pirodo.toArray(new Integer[pirodo.size()]);
		
		// �������� ����
		Arrays.sort(pirodoArr);
		
		bfs(pirodo.size());
		System.out.println(myPirodo);
	}
	
	private static void bfs(int pirodoLen) {
		Queue<SangDuk> queue = new LinkedList<SangDuk>();
		boolean[][] visited = new boolean[n][n];	// �湮�� üũ
		SangDuk start = new SangDuk(pX, pY);	// ������  
		int startPointer = 0;
		int endPointer = 0;
		
		// �������� ���� 
		while(startPointer <= endPointer && endPointer < pirodoLen) {
			queue.clear();

			boolean isFinish = false;
			int kCount = 0;
			// �� �������� �������� ~ ������ ���� ���� ���� �Ƿε����� ���ԵǾ�� �Ѵ�. �׷��� ���������� ����� �� �ִ� �������� �κй迭 �̶�� ��.
			if(pirodoMap[pY][pX] >= pirodoArr[startPointer] && pirodoMap[pY][pX] <= pirodoArr[endPointer]) {
				queue.offer(start);
				visited[pY][pX] = true;
			}
			
			// ��� ���� (������ ���ƴ� ��δ� ����)
			while(!queue.isEmpty()) {
				SangDuk sangDuk = queue.poll();
				for(int i = 0; i < moveX.length; i++) {
					int nextX = sangDuk.x + moveX[i];
					int nextY = sangDuk.y + moveY[i];
					
					// �ܺ� �� üũ
					if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
						continue;
					}
					
					if(visited[nextY][nextX]) {
						continue;
					}
					
					// ���� �ִ񰪺��� �ش������� �Ƿε��� ũ�ų�, ���� �ּڰ����� �ش������� �Ƿε��� ������ ������ Ȯ���Ϸ� ���� 
					if(pirodoArr[startPointer] > pirodoMap[nextY][nextX] || pirodoArr[endPointer] < pirodoMap[nextY][nextX]) {
						continue;
					}
					
					// �� üũ ���� �ִ밪/�ּҰ� üũ 
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