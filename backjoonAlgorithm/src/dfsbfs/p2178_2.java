package dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// NFS 방식
// 해당 위치의 상하좌우를 살펴서 1씩 증가시키게 함
// 마지막 위치의 이동 수를 리턴
public class p2178_2 {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static final int[][] direction = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };	// 순서대로 좌, 밑, 우, 상
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		if(n < 2 || m > 100) {
			System.out.println("n, m의 범위를 넘어선 값을 입력했습니다.");
			System.exit(0);	
		}
		
		map = new int[n][m];
		visited = new boolean[n][m];
//		Arrays.fill(visited, false);
		sc.nextLine();	// 엔터 입력 방지
		
		// 맵 입력받기
		for(int i = 0; i < n; i++) {	// n = y
			String strLine = sc.nextLine();
			if(strLine.length() != m) {
				System.out.println("m의 크기만큼 값을 입력하지 않았습니다.");
				System.exit(0);	
			}
			for(int j = 0; j < strLine.length(); j++) {	// m = x
				char maze = strLine.charAt(j);
				if(maze == 48 || maze == 49) {	// 0 또는 1
					map[i][j] = Character.getNumericValue(strLine.charAt(j));	// 아스키코드가 아닌 char 숫자를 int로 변경
				}
				else {
					System.out.println("0 또는 1을 입력하지 않았습니다. 종료합니다.");
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
		
		while(!queueY.isEmpty() && !queueX.isEmpty()) {	// 두 큐 내부에 원소가 없을 때까지 반복
			int currentY = queueY.poll();	// y 위치 값 꺼내기
			int currentX = queueX.poll();	// x 위치 값 꺼내기
			
			if(map[currentY][currentX] >= 1 && !visited[currentY][currentX]) {
				visited[currentY][currentX] = true;	// 1에 해당하는 현재 위치 방문
				for(int i = 0; i < direction.length; i++) {
					int nextY = currentY + direction[i][0];
					int nextX = currentX + direction[i][1];
					if((nextY >= 0 && nextY < n) && (nextX >= 0 && nextX < m)) { // 좌표 위치가 맵을 벗어나면 안됨
						if(map[nextY][nextX] == 1 && !visited[nextY][nextX]) {	// 무조건 방문했던 곳을 가면 안됨
							map[nextY][nextX] = map[currentY][currentX] + 1;
							// 큐에 추가
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
