package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 줄세우기
// 구현
public class p10431 {
	static int n;
	static final int MAX_STUDENT_COUNT = 20;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(n --> 0) {
			st = new StringTokenizer(br.readLine());
			sb.append(Integer.valueOf(st.nextToken()));	// 테스트 케이스 인덱스
			
			// 학생 20명 입력받기 시작
			int count = 0;
			int[] studentsLine = new int[MAX_STUDENT_COUNT];
			for(int i = 0; i < MAX_STUDENT_COUNT; i++) {
				int student = Integer.valueOf(st.nextToken());
				int tallerIndex = findTallerStudentIndex(studentsLine, i, student);
				if(tallerIndex == -1) {
					studentsLine[i] = student;
				}
				else {
					count += i - tallerIndex;	// 앞에서 가장 키큰 학생부터 뒤로 한 칸씩 이동하는 횟수
					rightShiftArray(studentsLine, tallerIndex, i);
					studentsLine[tallerIndex] = student;
				}
			}
			
			sb.append(" ").append(count);
			if(n > 0) {
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	// 한 명씩 오른쪽으로 이동
	static void rightShiftArray(int[] studentsLine, int tallerIndex, int nowIndex) {
//		int next = studentsLine[nowIndex];
		for(int i = nowIndex - 1; i >= tallerIndex; i--) {
			studentsLine[i + 1] = studentsLine[i];
		}
	}
	
	// 입력받은 인덱스의 학생보다 키가 큰 사람의 인덱스(가장 앞) 구하기
	// 어차피 나보다 키 큰 사람이 한 명 이상이라도 있으면 가장 앞에 있는 사람으로 바꾸기 때문
	static int findTallerStudentIndex(int[] studentsLine, int myIndex, int student) {
		for(int i = 0; i < myIndex; i++) {
			if(studentsLine[i] > student) {
				// 가장 앞에 있는 사람
				return i;
			}
		}
		// -1: 미존재
		return -1;
	}
}
