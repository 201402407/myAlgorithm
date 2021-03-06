package dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BFS 탐색
public class p1697 {
	static int[] map;
	static int k;
	static final int[] move = {-1, 1, 2};	// x-1, x+1, 2x 순서
	static boolean[] visited;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		// n과 k값 받기
		int n = sc.nextInt();
		if(n < 0 || n > 100000) {
			System.exit(0);
		}
		k = sc.nextInt();
		if(k < 0 || k > 100000) {
			System.exit(0);
		}
		
		// map 배열 크기 설정
		if(n >= k) {
			map = new int[n + 1];	// n이 100000이 될 수 있으므로 +1을 해서 최대 크기를 늘려야 한다.
			visited = new boolean[n + 1];
		}
		if(n < k) {
			map = new int[2 * k];
			visited = new boolean[2 * k];
		}
		
		// BFS 시작
		int moveCount = findBrotherOrSister(n);
		System.out.println(moveCount);
	}
	
	// BFS 사용.
	private static int findBrotherOrSister(int x) {
		if(x == k) {	// 시작 지점에 동생이 있는 경우
			return 0;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(x);
		
		while(!queue.isEmpty()) {
			int currentX = queue.poll();
			if(map[currentX] >= 0 && !visited[currentX]) {	// 미방문한 위치
				visited[currentX] = true;
				if(currentX > k) {	// k에 가까워지기 위해서는 x-1만 진행해야 함. 시간 감소 및 메모리 소모 줄이기
					int nextX = currentX + move[0];
					// 배열 내 이동 시간 원소 값 수정하고 큐에 삽입
					addNextX(currentX, nextX, map, queue);
				}
				if(currentX < k) {	// 세 가지 이동이 가능
					for(int i = 0; i < move.length; i++) {
						int nextX;
						if(i == 2) {	// 2X인 경우
							nextX = currentX * move[2];
						}
						else {	// 그 외
							nextX = currentX + move[i];
						}
						// 배열 내 이동 시간 원소 값 수정하고 큐에 삽입
						addNextX(currentX, nextX, map, queue);
					}
				}
			}
		}
		return map[k];
	}
	
	private static void addNextX(int currentX, int nextX, int[] map, Queue<Integer> queue) {
		if(nextX >= 0 && nextX < map.length) {	// nextX가 배열 내 인덱스에 있어야 함
			if(map[nextX] == 0 && !visited[nextX]) {
				queue.add(nextX);
				map[nextX] = map[currentX] + 1;
			}
		}
	}
}
