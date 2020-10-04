package dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// NFS ���
// �ش� ��ġ�� �����¿츦 ���켭 1�� ������Ű�� ��
// ������ ��ġ�� �̵� ���� ����
public class p2178_2 {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static final int[][] direction = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };	// ������� ��, ��, ��, ��
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		if(n < 2 || m > 100) {
			System.out.println("n, m�� ������ �Ѿ ���� �Է��߽��ϴ�.");
			System.exit(0);	
		}
		
		map = new int[n][m];
		visited = new boolean[n][m];
//		Arrays.fill(visited, false);
		sc.nextLine();	// ���� �Է� ����
		
		// �� �Է¹ޱ�
		for(int i = 0; i < n; i++) {	// n = y
			String strLine = sc.nextLine();
			if(strLine.length() != m) {
				System.out.println("m�� ũ�⸸ŭ ���� �Է����� �ʾҽ��ϴ�.");
				System.exit(0);	
			}
			for(int j = 0; j < strLine.length(); j++) {	// m = x
				char maze = strLine.charAt(j);
				if(maze == 48 || maze == 49) {	// 0 �Ǵ� 1
					map[i][j] = Character.getNumericValue(strLine.charAt(j));	// �ƽ�Ű�ڵ尡 �ƴ� char ���ڸ� int�� ����
				}
				else {
					System.out.println("0 �Ǵ� 1�� �Է����� �ʾҽ��ϴ�. �����մϴ�.");
					System.exit(0);
				}
			}
		}
		
		int result = explore(0, 0);
		System.out.println(result);
	}

	private static int explore(int y, int x) {
		if(map[y][x] == 0) {
			return 0;
		}
		
		Queue<Integer> queueY = new LinkedList<>();
		Queue<Integer> queueX = new LinkedList<>();
		queueY.add(y);
		queueX.add(x);
		
		while(!queueY.isEmpty() && !queueX.isEmpty()) {	// �� ť ���ο� ���Ұ� ���� ������ �ݺ�
			int currentY = queueY.poll();	// y ��ġ �� ������
			int currentX = queueX.poll();	// x ��ġ �� ������
			
			if(map[currentY][currentX] >= 1 && !visited[currentY][currentX]) {
				visited[currentY][currentX] = true;	// 1�� �ش��ϴ� ���� ��ġ �湮
				for(int i = 0; i < direction.length; i++) {
					int nextY = currentY + direction[i][0];
					int nextX = currentX + direction[i][1];
					if((nextY >= 0 && nextY < n) && (nextX >= 0 && nextX < m)) { // ��ǥ ��ġ�� ���� ����� �ȵ�
						if(map[nextY][nextX] == 1 && !visited[nextY][nextX]) {	// ������ �湮�ߴ� ���� ���� �ȵ�
							map[nextY][nextX] = map[currentY][currentX] + 1;
							// ť�� �߰�
							queueY.add(nextY);
							queueX.add(nextX);
						}
					}
				}
				
			}
		}
		return map[n - 1][m - 1];
		
	}
}

class Temp {
	
}