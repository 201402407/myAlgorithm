package slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// N번 째 큰수
// 슬라이딩 윈도우 
public class p2075 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
//		ArrayDeque<Integer> deque = new ArrayDeque<Integer>(); 	// 안에 값 담기
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();	// 안에 값 담기 
		StringTokenizer st;
		
		// 첫 줄은 미리 넣어두기 (메모리, 속도 효율)
		String line = br.readLine();
		st = new StringTokenizer(line);
		for(int i = 0; i < n; i++) {
			pq.offer(Integer.valueOf(st.nextToken()));
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				
				int num = Integer.parseInt(st.nextToken());
				// 이전에 들어있는 값 중 window에 맞게 빼기(front가 최소, rear가 최대)
				if(pq.peek() < num) {
					pq.poll();
					pq.offer(num);
				}
//				pq.offer(num);
//				
//				// window 크기에 맞게 앞에서부터 값 버리기 (N번째로 큰 값을 구하기 위해)
//				if(pq.size() > n) {
//					pq.poll();
//				}
			}
		}
		
		System.out.println(pq.poll());
	}
}
