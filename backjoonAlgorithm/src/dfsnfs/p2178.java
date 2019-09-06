package dfsnfs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// ������ �迭�� ���� ���縸 �ǰ� ���� ����� ���� �ʴ´�.
// ����Լ� �� �Ű�������� �ص� �������迭�̱� ������ ���� �������� ���� �ٲ��.
// DFS ��͹�� ����.
public class p2178 {
	static final int[] direction = {1, 2, -1, -2};
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		if(n < 2 || m > 100) {
			System.out.println("n, m�� ������ �Ѿ ���� �Է��߽��ϴ�.");
			System.exit(0);	
		}
		
		int[][] map = new int[n][m];
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
		
		// Ž�� ����
		int smallRouteCount = explore(map, 0, 0, -2);	// up�ؼ� �Դٰ� ����
		System.out.println(smallRouteCount);
	}
	
	// �ִ� ��� Ž��
	public static int explore(int[][] map, int y, int x, int prevDirection) {
		if(y == map.length - 1 && x == map[0].length - 1) {	// (y, x) == (N, M)�� ���
			return 1;
		}
		if(map[y][x] == 0)
			return 0;
		
		map[y][x] = 0;	// ������ ���̹Ƿ� 0���� �����ؾ� ���ѷ����� ������ ����
		ArrayList<Integer> moveCountList = new ArrayList<>();
		ArrayList<Integer> directionList = checkMoveDirection(map, y, x, prevDirection);
		if(directionList.isEmpty()) {	// ���̻� �̵��� ������ ���� ���
				return 0;
		}
		
		int[][] temp = new int[map.length][map[0].length];
		arrayCopy(map, temp);
		
		for(int i = 0; i < directionList.size(); i++) {
			int direction = directionList.get(i);
			int[] nextPos = getMovePosition(y, x, direction);
			int moveCount = explore(temp, nextPos[0], nextPos[1], -direction);	// ���������� �̵��ϸ� ���� ��ġ������ ���������� ������ �ǹǷ� �ݴ븦 ����� ���� - �߰�.
			if(moveCount != 0) {
				moveCountList.add(moveCount);
			}
		}
		
		if(moveCountList.isEmpty())	// ��� �̵� ������ ������ ���� ���� ���� ���
			return 0;
		
		return 1 + Collections.min(moveCountList);	// ���� �ּ� ����� ���̿� ���� ��ġ �� 1�� ���ؼ� ����.
	}
	
	// ���⿡ ���� ���� ��ǥ �� ����
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
	
	// ������ ������ �����ϰ�, ������ �� �ִ� ������ �ִ��� üũ�ؼ� direction ���� ArrayList�� �ְ� ArrayList ����
	// ������ �� ArrayList ���� -> ���� ������ �Ǵ�.
	public static ArrayList<Integer> checkMoveDirection(int[][] map, int y, int x, int prevDirection) {
		ArrayList<Integer> directionList = new ArrayList<>();
		for(int i = 0; i < direction.length; i++) {
			int directionElement = direction[i];
			if(directionElement == prevDirection)
				continue;
			int[] movePos = getMovePosition(y, x, directionElement);
			if(movePos[0] < 0 || movePos[0] >= map.length) {	// y(n) ���� üũ
				continue;
			}
			if(movePos[1] < 0 || movePos[1] >= map[0].length) {	// x(m) ���� üũ
				continue;
			}
			if(map[movePos[0]][movePos[1]] == 1) {
				directionList.add(directionElement);
			}
		}
		return directionList;
	}
	
	// �������迭 ���� ����
	public static void arrayCopy(int[][] array, int[][] copyArray) {
		for(int i = 0; i < array.length; i++) {	// �� ���� ����
			System.arraycopy(array[i], 0, copyArray[i], 0, array[i].length);
		}
	}
}