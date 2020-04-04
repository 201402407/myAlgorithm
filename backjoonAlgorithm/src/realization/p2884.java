package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 알람 시계 문제
// 구현
public class p2884 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int hour = Integer.valueOf(st.nextToken());
		int minute = Integer.valueOf(st.nextToken());
		
		if(minute >= 45) {
			System.out.println(hour + " " + (minute - 45));
		}
		else {
			minute = 60 + (minute - 45);
			if(--hour == -1) {
				hour = 23;
			}
			System.out.println(hour + " " + minute);
		}
	}
}
