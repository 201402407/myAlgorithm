package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 달팽이는 올라가고 싶다
public class p2869 {
	static int A, B, V;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null;
		try {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());	// 첫 번째 줄
			B = Integer.parseInt(st.nextToken());	
			V = Integer.parseInt(st.nextToken());
			if((A < 1) || (B < 1) || (V < 1)) {
				System.exit(0);
			}
			if(B >= A) {
				System.exit(0);
			}
			if((A > V) || (B >= V)) {
				System.exit(0);
			}
		} 
		catch (IOException e) {
			System.exit(0);
		} 
		catch (NumberFormatException e) {
			System.exit(0);
		}
		
		// V <= n(A-B) + A
		// n >= (V - A) / (A - B)
		double x = V - A;
		double y = A - B;
		// ceil : 반올림
		// 나눈 값이 0이면 하루 지나지 않고 끝난 것이므로 0으로 계산
		// 나눈 값이 0.~ 등 소숫점 숫자가 존재하는 경우 하루 지난 것으로 계산
		int result = (int)Math.ceil(x / y) + 1;	
		System.out.println(result);
	}
}
