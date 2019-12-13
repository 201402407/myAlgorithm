package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ��� ���� ����
// 0-1 knapsack �˰���
// ���� ���α׷��� -> ������ �ȵǹǷ�
public class p2662 {
	static int[][] company;
	static int[][] opt;
	static int[][] investment;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		company = new int[m + 1][n + 1];	// y : �� ����, x : ��� ����
		opt = new int[m + 1][n + 1];	// y : ��� ��, x : �� ����
		investment = new int[m + 1][n + 1];	// �ش� ������ ��� ����� �����ؼ� ����������� ����
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.valueOf(st.nextToken());
			for(int j = 1; j <= m; j++) {
				int value = Integer.valueOf(st.nextToken());
				company[j][index] = value;
			}
		}
		
		getOPT(n, m);
		getInvestmentCompany(n, m);
		
	}
	
	// OPT ���ϴ� �Լ�
	public static void getOPT(int n, int m) {
		for(int i = 1; i <= m; i++) {	// Aȸ��, A�� B ȸ��, A�� B�� Cȸ�� ---
			for(int j = 1; j <= n; j++) {	// 1�������� n��������
				opt[i][j] = opt[i - 1][j];	// �ʱ�ȭ
//				investment[i][j] = 0;	// �ʱ� �����ε� ������ 0���� �����Ǿ� �����Ƿ� ����
				// �ش� j �������� 0������ �ݺ�. �ִ� j������ ��, j - 1���� ��  iȸ���� 1������ ������ ���� i - 1ȸ���� j������ ���� ��
				for(int k = j; k >= 0; k--) {	
					// �ش� i ȸ���� j������ ������ ������ i - 1ȸ����� �� j������ �����ϱ� �� ������ ���ϱ�.
					// �׷��� ���� ����  i ȸ�翡 �ƿ� ���ڸ� ������ ���� �ִ� ��.
					// �̸� opt[i][j]�� ���ؼ� ū ������ ����.
					int betterValue = company[i][k] + opt[i - 1][j - k];
					if(opt[i][j] < betterValue) {
						opt[i][j] = betterValue;
						investment[i][j] = k;	// �ʱ� ����. 
					}
				}
			}
		}
		int result = 0;
		for(int i = 1; i <= m; i++) {
			result = Math.max(result, opt[i][n]);
		}
		System.out.println(result);
	}
	
	// ��� ȸ�翡 �� �����ߴ��� �ľ��ϴ� �Լ�
	public static void getInvestmentCompany(int n, int m) {
		int[] result = new int[m + 1];
		StringBuilder sb = new StringBuilder();
		for(int i = m; i >= 1; i--) {
			if(investment[i][n] != 0 && n >= 0) {
				result[i] = investment[i][n];
				n -= investment[i][n];
			}
		}
		
		for(int i = 1; i <= m; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
