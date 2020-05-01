package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// �巡�� Ŀ��
// �ùķ��̼�(�ٵ� DP ���?) ����
public class p15685 {
	// �� - �� - �Ʒ� - �� ����
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	static boolean[][] map = new boolean[101][101]; // True : Ŀ�� �׸� ����
	static boolean[][] squared = new boolean[100][100];
	
	
	static LinkedList<Integer>[] dragonCurves;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		dragonCurves = new LinkedList[11];
		
		// DP�� ���� K�� ° �巡�� Ŀ�� ���� List ���
		// �ӽ÷� ���� ������ 0�� ��츦 ����ؼ� ����
		dragonCurves[0] = new LinkedList<Integer>();
		dragonCurves[0].offer(0);
		for(int i = 1; i < 11; i++) {
			dragonCurves[i] = new LinkedList<Integer>();
			
			// �տ��� �Ȱ��� ���
			// �ڿ��� 90�� ȸ����Ų ���� �ݴ�� ���
			for(int index = dragonCurves[i - 1].size() - 1; index >= 0; index--) {
				int nowDirection = dragonCurves[i - 1].get(index);
				dragonCurves[i].offerFirst(nowDirection);
				
				if(nowDirection <= 0) {
					nowDirection = 4;
				}
				dragonCurves[i].offerLast((nowDirection - 1) % 4);
			}
			
//			for(int k = 0; k < dragonCurves[i].size(); k++) {
//				System.out.print(dragonCurves[i].get(k) + " ");
//			}
//			System.out.println();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			int direction = getSavedDragonCurvesDirection(Integer.valueOf(st.nextToken()));
			int g = Integer.valueOf(st.nextToken());
			
			// ���� ���� �׸���
			map[y][x] = true;
			
			// �巡�� Ŀ�� �ʿ� �׸���
			for(int j = 0; j < dragonCurves[g].size(); j++) {
				int nowDirection = dragonCurves[g].get(j);
				x += moveX[(nowDirection + direction) % 4];
				y += moveY[(nowDirection + direction) % 4];
				map[y][x] = true;
			}
		}
		
//		for(int i = 0; i < 101; i++) {
//			for(int j = 0; j < 101; j++) {
//				if(map[i][j]) {
//					System.out.print(1 + " ");	
//				}
//				else {
//					System.out.print(0 + " ");
//				}
//				
//			}
//			System.out.println();
//		}
		
		int count = 0;
		// �׸� �׸� �� �ִ��� �Ǵ��ϱ�
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j]) {
					if(checkSquared(j, i)) {
						count++;
					}	
				}
				
			}
		}
		
		System.out.println(count);
	}
	
	/*
	 * 0: x��ǥ�� �����ϴ� ���� (��)
	 * 1: y��ǥ�� �����ϴ� ���� (��)
	 * 2: x��ǥ�� �����ϴ� ���� (��)
	 * 3: y��ǥ�� �����ϴ� ���� (��)
	 * �� �Է� ���ε�, �� ������ ��, ��, ��, �� �� ��ȯ��Ų��.
	 */
	static int getSavedDragonCurvesDirection(int inputDirection) {
		switch (inputDirection) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		default:
			return -1;
		}
	}
	
	static boolean checkSquared(int x, int y) {
		// ���� �� -> ������ -> �Ʒ� -> ���� ������ �����ϸ� �簢���� �׸� �� �ִ��� �� ���� �Ǵ�
		for(int i = 1; i < 4; i++) {	
			x += moveX[i];
			y += moveY[i];
			if(!map[y][x]) {
				return false;
			}
		}
		return true;
	}
}
