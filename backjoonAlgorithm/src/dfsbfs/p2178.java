package dfsbfs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// 이차원 배열은 얕은 복사만 되고 깊은 복사는 되지 않는다.
// 재귀함수 내 매개변수라고 해도 이차원배열이기 때문에 값이 정적으로 같이 바뀐다.
// DFS 재귀방식 실패.
public class p2178 {
	static final int[] direction = {1, 2, -1, -2};
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		if(n < 2 || m > 100) {
			System.out.println("n, m의 범위를 넘어선 값을 입력했습니다.");
			System.exit(0);	
		}
		
		int[][] map = new int[n][m];
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
		
		// 탐색 시작
		int smallRouteCount = explore(map, 0, 0, -2);	// up해서 왔다고 가정
		System.out.println(smallRouteCount);
	}
	
	// 최단 경로 탐색
	public static int explore(int[][] map, int y, int x, int prevDirection) {
		if(y == map.length - 1 && x == map[0].length - 1) {	// (y, x) == (N, M)인 경우
			return 1;
		}
		if(map[y][x] == 0)
			return 0;
		
		map[y][x] = 0;	// 지나온 길이므로 0으로 세팅해야 무한루프에 빠지지 않음
		ArrayList<Integer> moveCountList = new ArrayList<>();
		ArrayList<Integer> directionList = checkMoveDirection(map, y, x, prevDirection);
		if(directionList.isEmpty()) {	// 더이상 이동할 방향이 없는 경우
				return 0;
		}
		
		int[][] temp = new int[map.length][map[0].length];
		arrayCopy(map, temp);
		
		for(int i = 0; i < directionList.size(); i++) {
			int direction = directionList.get(i);
			int[] nextPos = getMovePosition(y, x, direction);
			int moveCount = explore(temp, nextPos[0], nextPos[1], -direction);	// 오른쪽으로 이동하면 다음 위치에서는 이전방향이 왼쪽이 되므로 반대를 만들기 위해 - 추가.
			if(moveCount != 0) {
				moveCountList.add(moveCount);
			}
		}
		
		if(moveCountList.isEmpty())	// 모든 이동 가능한 방향의 길이 전부 막힌 경우
			return 0;
		
		return 1 + Collections.min(moveCountList);	// 가장 최소 경로의 길이와 현재 위치 값 1을 더해서 리턴.
	}
	
	// 방향에 따라 변한 좌표 값 리턴
	// @param : (int)direction -> -1 : left, 1 : right, -2 : up, 2 : down
	public static int[] getMovePosition(int y, int x, int direction) {
		int[] movePos = {y, x};
		switch(direction) {
		case -1:	// left
			movePos[1]--;
			break;
		case 1:	// right
			movePos[1]++;
			break;
		case -2:	// up
			movePos[0]--;
			break;
		case 2:	// down
			movePos[0]++;
			break;
			default:
				break;
		}
		return movePos;
	}
	
	// 지나온 방향을 제외하고, 움직일 수 있는 방향이 있는지 체크해서 direction 값을 ArrayList에 넣고 ArrayList 리턴
	// 없으면 빈 ArrayList 리턴 -> 길이 값으로 판단.
	public static ArrayList<Integer> checkMoveDirection(int[][] map, int y, int x, int prevDirection) {
		ArrayList<Integer> directionList = new ArrayList<>();
		for(int i = 0; i < direction.length; i++) {
			int directionElement = direction[i];
			if(directionElement == prevDirection)
				continue;
			int[] movePos = getMovePosition(y, x, directionElement);
			if(movePos[0] < 0 || movePos[0] >= map.length) {	// y(n) 범위 체크
				continue;
			}
			if(movePos[1] < 0 || movePos[1] >= map[0].length) {	// x(m) 범위 체크
				continue;
			}
			if(map[movePos[0]][movePos[1]] == 1) {
				directionList.add(directionElement);
			}
		}
		return directionList;
	}
	
	// 이차원배열 깊은 복사
	public static void arrayCopy(int[][] array, int[][] copyArray) {
		for(int i = 0; i < array.length; i++) {	// 행 별로 복사
			System.arraycopy(array[i], 0, copyArray[i], 0, array[i].length);
		}
	}
}
