package dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BFS Ž��
public class p1697 {
	static int[] map;
	static int k;
	static final int[] move = {-1, 1, 2};	// x-1, x+1, 2x ����
	static boolean[] visited;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		// n�� k�� �ޱ�
		int n = sc.nextInt();
		if(n < 0 || n > 100000) {
			System.exit(0);
		}
		k = sc.nextInt();
		if(k < 0 || k > 100000) {
			System.exit(0);
		}
		
		// map �迭 ũ�� ����
		if(n >= k) {
			map = new int[n + 1];	// n�� 100000�� �� �� �����Ƿ� +1�� �ؼ� �ִ� ũ�⸦ �÷��� �Ѵ�.
			visited = new boolean[n + 1];
		}
		if(n < k) {
			map = new int[2 * k];
			visited = new boolean[2 * k];
		}
		
		// BFS ����
		int moveCount = findBrotherOrSister(n);
		System.out.println(moveCount);
	}
	
	// BFS ���.
	private static int findBrotherOrSister(int x) {
		if(x == k) {	// ���� ������ ������ �ִ� ���
			return 0;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(x);
		
		while(!queue.isEmpty()) {
			int currentX = queue.poll();
			if(map[currentX] >= 0 && !visited[currentX]) {	// �̹湮�� ��ġ
				visited[currentX] = true;
				if(currentX > k) {	// k�� ��������� ���ؼ��� x-1�� �����ؾ� ��. �ð� ���� �� �޸� �Ҹ� ���̱�
					int nextX = currentX + move[0];
					// �迭 �� �̵� �ð� ���� �� �����ϰ� ť�� ����
					addNextX(currentX, nextX, map, queue);
				}
				if(currentX < k) {	// �� ���� �̵��� ����
					for(int i = 0; i < move.length; i++) {
						int nextX;
						if(i == 2) {	// 2X�� ���
							nextX = currentX * move[2];
						}
						else {	// �� ��
							nextX = currentX + move[i];
						}
						// �迭 �� �̵� �ð� ���� �� �����ϰ� ť�� ����
						addNextX(currentX, nextX, map, queue);
					}
				}
			}
		}
		return map[k];
	}
	
	private static void addNextX(int currentX, int nextX, int[] map, Queue<Integer> queue) {
		if(nextX >= 0 && nextX < map.length) {	// nextX�� �迭 �� �ε����� �־�� ��
			if(map[nextX] == 0 && !visited[nextX]) {
				queue.add(nextX);
				map[nextX] = map[currentX] + 1;
			}
		}
	}
}