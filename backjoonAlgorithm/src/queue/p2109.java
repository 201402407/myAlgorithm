package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 순회강연
// 우선순위큐
public class p2109 {
	static int n;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		StringTokenizer st;
		PriorityQueue<UniversityClass> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.valueOf(st.nextToken());
			int d = Integer.valueOf(st.nextToken());
			pq.offer(new UniversityClass(p, d));
		}
		
		// 1) 비용값만 넣은 우선순위 큐 만들기
		// size : 소모한 날(day)
		PriorityQueue<Integer> pays = new PriorityQueue<>();	
		int result = 0;
		// 최대 비용 강연 순서찾기
		while(!pq.isEmpty()) {
			UniversityClass universityClass = pq.poll();
			result += universityClass.p;
			pays.offer(universityClass.p);	// 무조건 강연 가능하기에 바로 넣는다
			if(universityClass.d < pays.size()) {
				result -= pays.poll();
			}
//			if(universityClass.d > pays.size()) {	// 현재 일수가 강연 가능 일수보다 작아야 강연가능
//				pays.offer(universityClass.p);	// 무조건 강연 가능하기에 바로 넣는다
//				result += universityClass.p;
//			}
//			else {
//				// 만약 내가 강연하고자 했던 강의들 중 가장 적은 보수보다 지금 강연 가능한 강의의 보수가 더 크면 이 강연을 선택해야지~
//				if(pays.peek() < universityClass.p) {	
//					result -= pays.poll();
//					pays.offer(universityClass.p);
//				}
//			}
		}
		
		System.out.println(result);
	}
}

class UniversityClass implements Comparable<UniversityClass> {
	int p;
	int d;
	
	UniversityClass(int p, int d) {
		this.p = p;
		this.d = d;
	}

	// 1) Day 낮은 순
	// 2) Pay 높은 순
	@Override
	public int compareTo(UniversityClass next) {
		if(this.d < next.d) {
			return -1;
		}
		else if(this.d == next.d) {
			return this.p > next.p ? -1 : 1;
		}
		else {
			return 1;
		}
	}
}
