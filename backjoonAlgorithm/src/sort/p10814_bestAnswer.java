package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// StringBuilder �迭 ���
// ���� �ð� �ſ� ����
// StringBuilder �˾Ƶ���
public class p10814_bestAnswer {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		StringBuilder[] result = new StringBuilder[201];	// �ִ� ���� 200��
		for(int i = 0; i < result.length; i++) {
			result[i] = new StringBuilder();
		}
		
		for(int j = 0; j < n; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.valueOf(st.nextToken());
			result[age].append(age).append(" ").append(st.nextToken() + "\n");
		}
		for(StringBuilder sb : result) {
			System.out.print(sb.toString());
		}
	}
}
