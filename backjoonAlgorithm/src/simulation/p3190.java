package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ��
// �ùķ��̼� 
public class p3190 {
	static int[][] map; // 0: ��ĭ,  1: ��� ����  2: ���� ����
	static int[] moveX = { 0, 1, 0, -1 };	//�������
	static int[] moveY = { -1, 0, 1, 0 };
	static HashMap<Integer, String> move;
	static Queue<Snake> mySnake;
	static int nowDirection = 1;	// ���� ���� �̵� ����
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		map = new int[n + 1][n + 1];	// �ε��� : 1 ~ N
		move = new HashMap<Integer, String>();
		mySnake = new LinkedList<Snake>();
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.valueOf(st.nextToken());	// ����� ����
		
		// �ʿ� ��� �ֱ�
		while(k --> 0) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.valueOf(st.nextToken());
			int x = Integer.valueOf(st.nextToken());
			
			map[y][x] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		int l = Integer.valueOf(st.nextToken());	// ���� ���� ��ȯ Ƚ��
		
		// �̵� ���� �ٲ�� �ð��� �̵� ���� ���
		while(l --> 0) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.valueOf(st.nextToken());
			String c = st.nextToken();
			move.put(time, c);
		}
		
//		Arrays.sort(move.keySet().toArray());	// �ð� �� �������� ����
		mySnake.add(new Snake(0, 0));
		int finishTime = start(2, 1, n);
		System.out.println(finishTime);
	}
	
	static int start(int x, int y, int n) {
		int nowTime = 1;
		
		while(true) {
//			System.out.println(x + " , " + y + " : " + nowTime);
			// ���� ���� �ε��� ���
			if((x <= 0 || x > n) || (y <= 0 || y > n)) {
				return nowTime;
			}
			
			if(map[y][x] == 2) {
				return nowTime;
			}
			
			if(map[y][x] == 0) {	// ���� ����
				Snake tail = mySnake.poll();
				map[tail.y][tail.x] = 0;
			}
			mySnake.add(new Snake(x, y));	// �Ӹ� �߰�
			map[y][x] = 2;
			
			if(move.containsKey(nowTime)) {
				String c = move.remove(nowTime);
				if(c.equals("L")) {
					
					nowDirection = nowDirection == 0 ? (moveX.length - 1) % 4 : (nowDirection - 1) % 4;
				}
				if(c.equals("D")) {
					nowDirection = (nowDirection + 1) % 4;
				}
			}
//			System.out.println(x + " , " + y + " : " + nowTime + ",, direction : " + nowDirection);
			x += moveX[nowDirection];
			y += moveY[nowDirection];
			
			nowTime++;
		}
	}
}

class Snake {
	int x;
	int y;
	
	Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
