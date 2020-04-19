package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 톱니바퀴 문제
// 시뮬레이션
public class p14891 {
	@SuppressWarnings("rawtypes")
	static LinkedList[] gears;	// 바퀴들(0 ~ 3 : 1번부터 4번까지)
	static int[][] moves;
	static boolean[] checked;
	static int[] directions;	// 1 : 시계방향, 0 : 정지, -1 : 반시계
	
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
		
		// 바퀴 만들기
		for(int i = 0; i < 4; i++) {
			 st = new StringTokenizer(br.readLine());
			 char[] gear = st.nextToken().toCharArray();
			 for(char ch : gear) {
				 gears[i].add(ch);
			 }
		}
		
		// 회전 바퀴 및 방향 넣기
		st = new StringTokenizer(br.readLine());
		int k = Integer.valueOf(st.nextToken());
		moves = new int[k][2];	// 0 : 톱니바퀴 번호, 1 : 톱니바퀴 방향
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.valueOf(st.nextToken());
			int direction = Integer.valueOf(st.nextToken());
			moves[i][0] = num - 1;	// 인덱스 0 ~ 3
			moves[i][1] = direction;
			Arrays.fill(checked, false);
			Arrays.fill(directions, 0);
			move(moves[i][0], moves[i][1]);
		}
		
		// 점수 계산
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			if(gears[i].get(0).equals('1')) {
				sum += Math.pow(2, i);
			}
		}
		
		System.out.println(sum);
	}
	
	// 각 톱니바퀴 이동방향 찾는 함수
	static void move(int gearIndex, int direction) {
		Queue<Integer> q = new LinkedList<Integer>(); // 바퀴 인덱스만 담김.
		checked[gearIndex] = true;
		directions[gearIndex] = direction;
		q.add(gearIndex);
		
		while(!q.isEmpty()) {	// 바퀴 방향은 큐에 들어가기 전에 정해져있음.
			int index = q.poll();
//			if(checked[index]) {
//				continue;
//			}
//			checked[index] = true;
			
			
			// 왼쪽
			if(index - 1 >= 0) {
				if(!checked[index - 1]) {
					// 왼쪽 바퀴의 3시 방향 바퀴축과 현재 바퀴의 9시 방향 바퀴축과 비교해서 다르면 반대 방향. 같으면 정지
					if(!gears[index - 1].get(2).equals(gears[index].get(6))) {
						directions[index - 1] = getThisGearDirection(directions[index]);
						checked[index - 1] = true;
						q.add(index - 1);
					}
				}
			}
			
			// 오른쪽
			if(index + 1 <= 3) {
				if(!checked[index + 1]) {
					// 오른쪽 바퀴의 9시 방향 바퀴축과 현재 바퀴의 3시 방향 바퀴축과 비교해서 다르면 반대 방향. 같으면 정지
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
	
	// 반대 방향 리턴
	static int getThisGearDirection(int direction) {
		if(direction == 1) {
			return -1;
		}
		return 1;
	}
	
	// 각 톱니바퀴 방향대로 이동
	static void change() {
		for(int i = 0; i < 4; i++) {
			switch(directions[i]) {
				case 1:		// 시계 방향
					gears[i].add(0, gears[i].remove(gears[i].size() - 1));
					break;
				case 0:
					break;
				case -1:	// 반시계 방향
					gears[i].add(gears[i].remove(0));
					break;
			}
		}
	}
}
