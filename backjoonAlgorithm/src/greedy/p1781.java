package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 컵라면
// 그리디 알고리즘
// 우선순위 큐
public class p1781 {
	static int n;
	static Question[] questionArr;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
//	1. 데드라인과 컵라면 수를 각각 변수로 가진 클래스 생성
//	2. for문 탐색으로 input 입력 시 클래스 인스턴스 생성해서 별도 Array에 저장
//	3. Array sort(class comparable 구현했기 때문에, 오버라이딩한 조건에 따라 sort됨)
//	4. 우선순위 큐 생성(컵라면 획득 개수를 담음 - 오름차순)
//	5. Array for문 탐색하면서 우선순위큐 원소 개수가 현재 index의 데드라인보다 작으면 바로 집어넣기
//	5-2. 만약 데드라인과 같은 우선순위큐 원소 개수이면 peek해서 열어본 컵라면 개수와 비교
//	5-3. 현재 문제의 컵라면 보상이 크면 poll한 후 해당 컵라면 개수를 넣음
//	6. 우선순위큐 원소 전부 꺼내면서 컵라면 갯수 세기
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		questionArr = new Question[n];
		
		// O(n)
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadLine = Integer.valueOf(st.nextToken());
			int cupNoodleCount = Integer.valueOf(st.nextToken());
			questionArr[i] = new Question(deadLine, cupNoodleCount);
//			pq.offer(new Question(deadLine, cupNoodleCount));
		}
		
		long nowCupNoodleCount = 0;
		Arrays.parallelSort(questionArr);	// Comparable interface overriding한 조건에 따라 정렬
		
		// O(n)
		for(Question question : questionArr) {
			int size = pq.size();
			if(size < question.deadLine) {
				pq.offer(question.cupNoodleCount);
			}
			else if(size == question.deadLine) {
				int noodleCount = pq.peek();
				if(noodleCount < question.cupNoodleCount) {
					pq.poll();
					pq.offer(question.cupNoodleCount);
				}
			}
		}
		
		// O(상수) -> 무시
		while(!pq.isEmpty()) {
			nowCupNoodleCount += pq.poll();
		}
		
		System.out.println(nowCupNoodleCount);
	
	}
}

class Question implements Comparable<Question> {
	int deadLine;
	int cupNoodleCount;
	
	Question(int deadLine, int cupNoodleCount) {
		this.deadLine = deadLine;
		this.cupNoodleCount = cupNoodleCount;
	}

	@Override
	public int compareTo(Question o) {
		// 1. 데드라인 낮은 순
		// 2. 컵라면 개수 높은 순
		if(this.deadLine < o.deadLine) {
			return -1;
		}
		else if(this.deadLine == o.deadLine) {
			if(this.cupNoodleCount > o.cupNoodleCount) {
				return -1;
			}
			else if(this.cupNoodleCount == o.cupNoodleCount) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
}
