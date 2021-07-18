package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 쇼핑몰
// 우선순위 큐
public class p17612 {
	static int n, k;
	static Queue<Customer> customerQueue = new LinkedList<Customer>();
	static PriorityQueue<Counter> counterQueue = new PriorityQueue<Counter>();
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		k = Integer.valueOf(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.valueOf(st.nextToken());
			int w = Integer.valueOf(st.nextToken());
			customerQueue.offer(new Customer(id, w));	//	1) 선입선출(큐)에 고객의 정보 저장
		}
		
//		2) 처음에, 앞에서 K만큼 (회원번호, 걸리는시간 = (개점 후 지난 시간 + 계산시간(w))) 계산대 큐(우선순위 큐) 삽입
		for(int i = 0; i < k; i++) {
			if(customerQueue.isEmpty()) {	// 줄서있는 고객보다 계산대 수가 많은 경우
				break;
			}
			
			counterQueue.offer(new Counter());
		}
//		2-1) 고객 큐에서 K만큼 꺼내기
//		3) 큐가 empty될 때까지 while문 반복수행하는데
//		3-1) 비어있는 계산대 우선순위 큐를 만들어서 계산대 큐에서 꺼내는 순간 계산대 번호 넣기 
//		4) 계산대 큐에서 가장 걸리는시간이 짧은거 꺼내기
//		4-1) 회원번호 ArrayList에 담기
		
		
	}
}

class Customer {
	int id;
	int w;
	
	Customer(int id, int w) {
		this.id = id;
		this.w = w;
	}
}

class Counter implements Comparable<Counter> {
	int index;
	int time;
	
	Counter(int index, int time) {
		this.index = index;
		this.time = time;
	}

	@Override
	public int compareTo(Counter o) {
		// 시간이 짧은 계산대부터 손님이 나간다.
		// 만약 같은 시간이 걸리는 경우, index가 큰(번호가 큰) 계산대부터 손님이 빠져나간다.
		if(this.time < o.time) {
			return -1;
		}
		if(this.time == o.time) {
			if(this.index < o.index) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		return 1;
	}
}