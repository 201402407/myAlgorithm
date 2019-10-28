package week7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * 7주차 과제
 * Segmented Least Squares
 * 최소 제곱법
 * 참값과 근사값의 오차의 제곱합이 최소가 되게 함
 * 데이터의 분포를 최대한 잘 근사하는 직선을 찾는 일
 * 직선이 여러 개일 수 있음.
 */
public class SLS {
	static double[][] point;
	static double[] opt;
	static double[][] error;
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data07.txt";	// 상대 경로 설정
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, ",");
			int n = Integer.valueOf(st.nextToken()); 
			// 인덱스 : 1 ~ n까지.
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
			System.out.println("넘버포맷오류");
			System.out.println(e);
			System.exit(0);
		}
	}
	
	static double SLS(int n, int cost) {
		for(int j = 1; j <= n; j++) {
			for(int i = 1; i < j; i++) {
				// 평균 구하기
				double sum = 0;
				int count = 0;
				for(int k = i; k <= j; k++) {
					sum += point[k];
					count++;
				}
				double avg = (double) sum / count;
				
				// 분산 구하기
				sum = 0;
				for(int k = i; k <= j; k++) {
					sum += Math.pow(point[k] - avg, 2); // 각 원소에서 평균을 뺀 값을 제곱하여 더하기.
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
