package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 네 수
// 구현
public class p10824 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long ab = Long.valueOf(st.nextToken() + st.nextToken());
		long cd = Long.valueOf(st.nextToken() + st.nextToken());
		System.out.println(ab + cd);
	}
}
