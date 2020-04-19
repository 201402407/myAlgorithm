package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ��Ϲ��� ����
// �ùķ��̼�
public class p14891 {
	@SuppressWarnings("rawtypes")
	static LinkedList[] gears;	// ������(0 ~ 3 : 1������ 4������)
	static int[][] moves;
	static boolean[] checked;
	static int[] directions;	// 1 : �ð����, 0 : ����, -1 : �ݽð�
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException {
		gears = new LinkedList[4];
		checked = new boolean[4];
		directions = new int[4];
		
		for(int i = 0; i < 4; i++) {
			gears[i] = new LinkedList<Character>();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// ���� �����
		for(int i = 0; i < 4; i++) {
			 st = new StringTokenizer(br.readLine());
			 char[] gear = st.nextToken().toCharArray();
			 for(char ch : gear) {
				 gears[i].add(ch);
			 }
		}
		
		// ȸ�� ���� �� ���� �ֱ�
		st = new StringTokenizer(br.readLine());
		int k = Integer.valueOf(st.nextToken());
		moves = new int[k][2];	// 0 : ��Ϲ��� ��ȣ, 1 : ��Ϲ��� ����
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.valueOf(st.nextToken());
			int direction = Integer.valueOf(st.nextToken());
			moves[i][0] = num - 1;	// �ε��� 0 ~ 3
			moves[i][1] = direction;
			Arrays.fill(checked, false);
			Arrays.fill(directions, 0);
			move(moves[i][0], moves[i][1]);
		}
		
		// ���� ���
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			if(gears[i].get(0).equals('1')) {
				sum += Math.pow(2, i);
			}
		}
		
		System.out.println(sum);
	}
	
	// �� ��Ϲ��� �̵����� ã�� �Լ�
	static void move(int gearIndex, int direction) {
		Queue<Integer> q = new LinkedList<Integer>(); // ���� �ε����� ���.
		checked[gearIndex] = true;
		directions[gearIndex] = direction;
		q.add(gearIndex);
		
		while(!q.isEmpty()) {	// ���� ������ ť�� ���� ���� ����������.
			int index = q.poll();
//			if(checked[index]) {
//				continue;
//			}
//			checked[index] = true;
			
			
			// ����
			if(index - 1 >= 0) {
				if(!checked[index - 1]) {
					// ���� ������ 3�� ���� ������� ���� ������ 9�� ���� ������� ���ؼ� �ٸ��� �ݴ� ����. ������ ����
					if(!gears[index - 1].get(2).equals(gears[index].get(6))) {
						directions[index - 1] = getThisGearDirection(directions[index]);
						checked[index - 1] = true;
						q.add(index - 1);
					}
				}
			}
			
			// ������
			if(index + 1 <= 3) {
				if(!checked[index + 1]) {
					// ������ ������ 9�� ���� ������� ���� ������ 3�� ���� ������� ���ؼ� �ٸ��� �ݴ� ����. ������ ����
					if(!gears[index + 1].get(6).equals(gears[index].get(2))) {
						directions[index + 1] = getThisGearDirection(directions[index]);
						checked[index + 1] = true;
						q.add(index + 1);
					}				
				}
			}
		}
		change();
	}
	
	// �ݴ� ���� ����
	static int getThisGearDirection(int direction) {
		if(direction == 1) {
			return -1;
		}
		return 1;
	}
	
	// �� ��Ϲ��� ������ �̵�
	static void change() {
		for(int i = 0; i < 4; i++) {
			switch(directions[i]) {
				case 1:		// �ð� ����
					gears[i].add(0, gears[i].remove(gears[i].size() - 1));
					break;
				case 0:
					break;
				case -1:	// �ݽð� ����
					gears[i].add(gears[i].remove(0));
					break;
			}
		}
	}
}
