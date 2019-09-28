package cpcContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2019.09.28
// �߾Ӵ��б� ���α׷��� ������ȸ(CPC) ���� ���׽�Ʈ
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
		sb.append("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.").append("\n");
		recursive(n, 0);
		System.out.print(sb.toString());
	}
	
	private static void recursive(int n, int count) {
		sb.append(addLine(count));
		sb.append("\"����Լ��� ������?\"").append("\n");
		if(n == 0) {
			sb.append(addLine(count));
			sb.append("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"").append("\n");
			sb.append(addLine(count));
			sb.append("��� �亯�Ͽ���.").append("\n");
			return;
		}
		sb.append(addLine(count));
		sb.append("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.").append("\n");
		sb.append(addLine(count));
		sb.append("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.").append("\n");
		sb.append(addLine(count));
		sb.append("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"").append("\n");
		recursive(n - 1, count + 1);
		sb.append(addLine(count));
		sb.append("��� �亯�Ͽ���.").append("\n");
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
