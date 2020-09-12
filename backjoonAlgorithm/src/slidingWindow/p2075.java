package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// N�� ° ū��
// �����̵� ������ 
public class p2075 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
//		ArrayDeque<Integer> deque = new ArrayDeque<Integer>(); 	// �ȿ� �� ���
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();	// �ȿ� �� ��� 
		StringTokenizer st;
		
		// ù ���� �̸� �־�α� (�޸�, �ӵ� ȿ��)
		String line = br.readLine();
		st = new StringTokenizer(line);
		for(int i = 0; i < n; i++) {
			pq.offer(Integer.valueOf(st.nextToken()));
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				
				int num = Integer.parseInt(st.nextToken());
				// ������ ����ִ� �� �� window�� �°� ����(front�� �ּ�, rear�� �ִ�)
				if(pq.peek() < num) {
					pq.poll();
					pq.offer(num);
				}
//				pq.offer(num);
//				
//				// window ũ�⿡ �°� �տ������� �� ������ (N��°�� ū ���� ���ϱ� ����)
//				if(pq.size() > n) {
//					pq.poll();
//				}
			}
		}
		
		System.out.println(pq.poll());
	}
}
