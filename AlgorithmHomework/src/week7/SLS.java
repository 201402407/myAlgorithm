package week7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * 7���� ����
 * Segmented Least Squares
 * �ּ� ������
 * ������ �ٻ簪�� ������ �������� �ּҰ� �ǰ� ��
 * �������� ������ �ִ��� �� �ٻ��ϴ� ������ ã�� ��
 * ������ ���� ���� �� ����.
 */
public class SLS {
	static double[][] point;
	static double[] opt;
	static double[][] error;
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data07.txt";	// ��� ��� ����
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, ",");
			int n = Integer.valueOf(st.nextToken()); 
			// �ε��� : 1 ~ n����.
			point = new double[n + 1][2];
			opt = new double[n + 1];
			error = new double[n + 1][n + 1];
			for(int i = 1; i <= n; i++) {
//				System.out.println(st.nextToken());
				point[i][0] = Double.valueOf(st.nextToken());
				point[i][1] = Double.valueOf(st.nextToken());
			}
			int cost = Integer.valueOf(st.nextToken());
			double lastOPT = SLS(n, cost);
			System.out.println(lastOPT);
		}
		catch(IOException e) {
			System.exit(0);
		}
		catch (NumberFormatException e) {
			System.out.println("�ѹ����˿���");
			System.out.println(e);
			System.exit(0);
		}
	}
	
	static double SLS(int n, int cost) {
		for(int j = 1; j <= n; j++) {
			for(int i = 1; i < j; i++) {
				// ��� ���ϱ�
				double sum = 0;
				int count = 0;
				for(int k = i; k <= j; k++) {
					sum += point[k];
					count++;
				}
				double avg = (double) sum / count;
				
				// �л� ���ϱ�
				sum = 0;
				for(int k = i; k <= j; k++) {
					sum += Math.pow(point[k] - avg, 2); // �� ���ҿ��� ����� �� ���� �����Ͽ� ���ϱ�.
				}
				double v = sum / (count - 1);
				error[i][j] = v * (j - i + 1); 
			}
		}
		
		for(int j = 1; j <= n; j++) {
			for(int i = 1; i <= j; i++) {
				opt[j] = Math.min(opt[j], (error[i][j] + cost + opt[i - 1]));
			}
		}
		return opt[n];
	}
}
