package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 과제
// 그리디
public class p13904 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		List<Subject> list = new ArrayList<Subject>();
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.valueOf(st.nextToken());
			int score = Integer.valueOf(st.nextToken());
			Subject subject = new Subject(score, day);
			list.add(subject);
		}
		
		// 오름차순 정렬
		Collections.sort(list);
		
		// O(n) 진행
		// 우선순위큐 활용
		int result = 0;
		PriorityQueue<Integer> getScoreQueue = new PriorityQueue<>();
		for(Subject subject : list) {
			result += subject.score;	// 일단 넣고본다
			getScoreQueue.offer(subject.score);
			
			// 과제 푼 개수가 날짜보다 많으면 조건에 맞지 않음
			if(getScoreQueue.size() > subject.day) {
				result -= getScoreQueue.poll();	// 우선순위큐에서 하나 제거
			}
		}
		
		System.out.println(result);
	}
}

class Subject implements Comparable<Subject> {
	int score;
	int day;
	
	public Subject(int score, int day) {
		this.score = score;
		this.day = day;
	}

	@Override
	public int compareTo(Subject o) {
		// 1) 마감일 낮은 순
		// 2) 점수 높은 순
		if(this.day < o.day) {
			return -1;
		}
		else if(this.day == o.day) {
			return this.score > o.score ? -1 : 1;
		}
		else {
			return 1;
		}
	}
}