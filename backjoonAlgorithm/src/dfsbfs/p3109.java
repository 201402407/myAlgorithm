package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����
//�׸��� + ��Ʈ��ŷ ���� 
public class p3109 {
	static int r, c;
	static char[][] map;
	static int[] moveY = {-1, 0, 1};	// X��ǥ�� ������ 1 ���ϸ� �ǹǷ� Y��ǥ �̵� �迭�� ���� 
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.valueOf(st.nextToken());
		c = Integer.valueOf(st.nextToken());
		map = new char[r][c];
		
		// �� �Է¹ޱ� 
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
//		boolean[][] visited = new boolean[r][c];
		// 1������ ����ϴϱ� 
		int count = 0;
		for(int i = 0; i < r; i++) {
			if(backTracking(0, i)) {
				count++;
			}
		}
		
		System.out.println(count);
		
	}
	
	static boolean backTracking(int x, int y) {
		if(x + 1 == c) {
			return true;
		}
		
		// ������ ������ ���� ��� �̵��ϴ� ��� 3���� 
		int nextX = x + 1;
		for(int i = 0; i < 3; i++) {
			int nextY = y + moveY[i];
			if(nextY >= 0 && nextY < r) {	// �ʿ� ������ ���
				if(map[nextY][nextX] == 'x') {
					continue;
				}
				
				// ��Ʈ��ŷ ����
                map[y][x] = 'x';
				// �� �� �������� ����Ǿ��ٴ� ���̹Ƿ� ��� �����Ű��
                // �� �������� ������ ������ ������ �ڸ��� ������ ����������
                // �ٸ� �������� �� ������ �͵� �� ���� ���� ��
                // �׷��� True�� ���Ͻ�Ų��.
				if(backTracking(nextX, nextY)) {
					return true;
				}
			}
		}
		
		// �� ���� ���̻� ���� ���
		return false;
	}
}