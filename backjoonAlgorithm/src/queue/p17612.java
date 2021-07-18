package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 쇼핑몰
// 우선순위 큐
public class p17612 {
	static int n, k, nowTime;	// nowTime : 개점한 후 지난 시간
	static Queue<Customer> customerQueue = new LinkedList<Customer>();	// 현재 대기중인 고객 큐
	static PriorityQueue<Counter> counterQueue = new PriorityQueue<Counter>();	// 현재 카운터 진행상태 우선순위큐
	static List<Integer> outCustomerTimeList = new ArrayList<Integer>();
	
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
			// 2-1) 고객 큐에서 K만큼 꺼내기
			Customer customer = customerQueue.poll();
			counterQueue.offer(new Counter(i, customer.id, customer.w));
		}
		
		PriorityQueue<Integer> waitedCustomerQueue = new PriorityQueue<Integer>();
		
		// 3) 큐가 empty될 때까지 while문 반복수행하는데
		while(!counterQueue.isEmpty()) {
			// 3-1) 비어있는 계산대 우선순위 큐를 만들어서 계산대 큐에서 꺼내는 순간 계산대 번호 넣기 
			// 4) 계산대 큐에서 가장 걸리는시간이 짧은거 꺼내기
			if(!customerQueue.isEmpty()) {
				Counter counter = counterQueue.poll();
				// 5) 시간 지났으니까 개장 후 시간 갱신
				nowTime = Math.max(nowTime, counter.time);
				waitedCustomerQueue.offer(counter.index);
				// 회원번호 ArrayList에 담기
				outCustomerTimeList.add(counter.id);
				while(!counterQueue.isEmpty()) {	// 나오는 시간이 같은 것들이 있을 경우, 가장 계산대 번호가 낮은 계산대부터 투입시키기
					if(counterQueue.peek().time == nowTime) {
						Counter tempCounter = counterQueue.poll();
						waitedCustomerQueue.offer(tempCounter.index);
						outCustomerTimeList.add(counter.id);
						continue;
					}
					
					break;
				}

				// 6) 새로운 고객 시간 계산해서 계산대에 넣기
				while(!waitedCustomerQueue.isEmpty()) {
					Customer newCustomer = customerQueue.poll();
					int nextIndex = waitedCustomerQueue.poll();
					Counter newCounter = new Counter(nextIndex, newCustomer.id, nowTime + newCustomer.w);
					counterQueue.offer(newCounter);
				}

				continue;
			}
			
			Counter counter = counterQueue.poll();
			// 4-1) 회원번호 ArrayList에 담기
			outCustomerTimeList.add(counter.id);
		}
		
		long result = 0;
		for(int i = 1; i <= outCustomerTimeList.size(); i++) {
			System.out.println(outCustomerTimeList.get(i - 1));
			result += i * outCustomerTimeList.get(i - 1);
		}
		
		System.out.println(result);
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
	int id;
	int time;
	
	Counter(int index, int time) {
		this.index = index;
		this.time = time;
	}
	
	Counter(int index, int id, int time) {
		this.index = index;
		this.id = id;
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