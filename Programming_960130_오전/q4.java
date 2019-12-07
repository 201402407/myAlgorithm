package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class q4 {
	static int[][] map;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			int k = Integer.valueOf(st.nextToken());
			map = new int[m][n];
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.valueOf(st.nextToken());
				}
			}
		} 
		catch (NumberFormatException e) {
			System.out.println("입력받은 값이 숫자가 아닙니다. 오류 발생! 종료합니다.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOException 오류 발생! SW를 종료합니다.");
			System.exit(0);
		}
	}
}
