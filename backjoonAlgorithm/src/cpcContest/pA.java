package cpcContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2019.09.28
// 중앙대학교 프로그래밍 경진대회(CPC) 오픈 컨테스트
public class pA {
	static StringBuilder[] line;
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		if(n < 1 || n > 50) {
			System.exit(0);
		}
		line = new StringBuilder[n + 1];
		for(int i = 0; i <= n; i++) {
			line[i] = new StringBuilder();
		}
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
		recursive(n, 0);
		System.out.print(sb.toString());
	}
	
	private static void recursive(int n, int count) {
		sb.append(addLine(count));
		sb.append("\"재귀함수가 뭔가요?\"").append("\n");
		if(n == 0) {
			sb.append(addLine(count));
			sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
			sb.append(addLine(count));
			sb.append("라고 답변하였지.").append("\n");
			return;
		}
		sb.append(addLine(count));
		sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n");
		sb.append(addLine(count));
		sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n");
		sb.append(addLine(count));
		sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n");
		recursive(n - 1, count + 1);
		sb.append(addLine(count));
		sb.append("라고 답변하였지.").append("\n");
	}
	
	private static String addLine(int count) {
		if(count == 0) {
			line[0] = new StringBuilder();
			return "";
		}
		
		if(!line[count].toString().isEmpty()) {
			return line[count].toString();
		}
		// copy
		line[count] = new StringBuilder();
		line[count].append(line[count - 1].toString());
		
		for(int i = 0; i < 4; i++) {
			line[count].append("_");
		}
		return line[count].toString();
	}
}
