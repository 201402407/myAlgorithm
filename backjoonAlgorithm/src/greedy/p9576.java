package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 책 나눠주기
// 그리ㄷㅣ 
public class p9576 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int count = 0;
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			boolean[] gived = new boolean[n + 1]; // index : 1 ~ n
			ArrayList<Student> students = new ArrayList<Student>();
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				Student s = new Student(start, end);
				students.add(s);
			}
			
			// gap 별 정렬
			Collections.sort(students);
			
			// 책 나눠주기 시작 
			int index = 0;
			int bookCount = n;
			while(index < m && bookCount > 0) {
				Student s = students.get(index);
				for(int i = s.start; i <= s.end; i++) {
					if(!gived[i]) {
						count++;
						gived[i] = true;
						bookCount--;
						break;
					}
				}
				index++;
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb.toString());
	}
}

class Student implements Comparable<Student> {
	int start;
	int end;
	int gap;
	
	Student(int start, int end) {
		this.start = start;
		this.end = end;
		this.gap = end - start + 1;
	}

	@Override
	public int compareTo(Student o) {
		if(this.end < o.end) {
			return -1;
		}
		else if(this.end == o.end) {
			return this.gap < o.gap ? -1 : 1;
		}
		else {
			return 1;
		}
//		if(this.gap < o.gap) {
//			return -1;
//		}
//		else if(this.gap == o.gap) {
//			return this.end < o.end ? -1 : 1;
//		}
//		else {
//			return 1;
//		}
	}
	
	
}