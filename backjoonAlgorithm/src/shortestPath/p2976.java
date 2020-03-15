package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// �ִ� ��� ����
// ���ͽ�Ʈ��? BFS?
// JAVA�� ������ �ð� �ʰ�
public class p2976 {
	static int[][] opt;
	static int[] pays;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		pays = new int[n + 1];	// �ε��� 1 ~ n
		int[] nowPays = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.valueOf(st.nextToken());
			pays[i] = pay;
		}
		Arrays.fill(nowPays, Integer.MAX_VALUE);
		
		opt = new int[n + 1][n + 1];	// (���� ��ȣ, ���� ��) �� �ּ� ���
		System.out.println(opt(2, 1, n));
//		for(int i = 1; i <= n; i++) {
//			Arrays.fill(opt[i], Integer.MAX_VALUE);
//		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= n; j++) {
//				int front = i + j + 1;
//				int back = i - j;
//				
//				if(i - j >= 1 && i + j + 1 <= n) {
//					opt[i][j] = pays[i] + Math.min(opt[back][j], opt[front])
//				}
//				// i - j < 1 ���� ������ �ٴ°͸�
//				else if(i - j < 1) {
//					
//				}
//				// i + j > n ���� �ڷ� �ٴ� �͸�
//				// �� �� �ٱ�
//				// �� �� �ȶٱ�
//				// math.min�ؼ� opt �����ϱ�(���� ���α׷���)
//			}	
//		}
		
//		Move2 firstMove = new Move2(2, 1);
//		nowPays[2] = pays[2];
//		Queue<Move2> q = new LinkedList<Move2>();
//		q.add(firstMove);
//		while(q.isEmpty()) {
//			Move2 move = q.poll();
//			int front = move.num + move.lastMoving + 1;
//			int back = move.num - move.lastMoving;
//			if(front <= n) {
//				if(nowPays[move.num] + pays[front] <= nowPays[front]) {
//					nowPays[front] = nowPays[move.num] + pays[front];
//					q.add(new Move2(front, move.lastMoving + 1));
//				}
//			}
//			
//			if(back >= 1) {
//				if(nowPays[move.num] + pays[back] <= nowPays[front]) {
//					nowPays[front] = nowPays[move.num] + pays[front];
//					q.add(new Move2(front, move.lastMoving + 1));
//				}
//			}
//		}
		
		
//		int result = move(pays, nowPays, n);
//		System.out.println(result);
	}
	
	public static int opt(int num, int jump, int n) {
		if(num < 1 || num > n) {
			return Integer.MAX_VALUE;	// inf ����
		}
		if(num == n) {
			return pays[n];
		}
		if(opt[num][jump] != 0) {
			return opt[num][jump];
		}
		return pays[num] + Math.min(opt(num - jump, jump, n), opt(num + jump + 1, jump + 1, n));
	}
	
	public static int move(int[] pays, int[] nowPays, int n) {
		nowPays[2] = pays[2];
		// ù ������ 1������
		Move firstMove = new Move(2, 1, nowPays[2]);
		
		PriorityQueue<Move> queue = new PriorityQueue<Move>();
		queue.add(firstMove);
		
		int result = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			Move thisMove = queue.poll();
//			System.out.println(thisMove.num);
			if(thisMove.num == n) {
				return thisMove.pay;
			}

			// ���� ����� ���� ���� �� ���� ��� ����
			// ���� ����
			int frontMoving = thisMove.num + thisMove.lastMoving + 1; 
			if(frontMoving <= n) {
//				if(thisMove.pay + pays[frontMoving] <= nowPays[frontMoving]) {
					nowPays[frontMoving] = thisMove.pay + pays[frontMoving]; 
					queue.add(new Move(frontMoving, thisMove.lastMoving + 1, nowPays[frontMoving]));	
//					}
			}
			
			int backMoving = thisMove.num - thisMove.lastMoving;
			if(backMoving >= 1) {
//				if(thisMove.pay + pays[backMoving] <= nowPays[backMoving]) {
					nowPays[backMoving] = thisMove.pay + pays[backMoving]; 
					queue.add(new Move(backMoving, thisMove.lastMoving, nowPays[backMoving]));	
//				}
			}
		}
		return nowPays[n];
	}
}

class Move2 {
	int num;
	int lastMoving;
	
	Move2(int num, int lastMoving) {
		this.num = num;
		this.lastMoving = lastMoving;
	}
	
}

class Move implements Comparable<Move> {
	int num;
	int lastMoving;
	int pay;
	
	Move(int num, int lastMoving, int pay) {
		this.num = num;
		this.lastMoving	= lastMoving;
		this.pay = pay;
	}

	@Override
	public int compareTo(Move o) {
		if(this.pay < o.pay) {
			return -1;
		}
		return 1;
	}
}
