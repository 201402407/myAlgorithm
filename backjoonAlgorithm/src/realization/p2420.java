package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���ĸ����� 
// ����
public class p2420 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long n = Long.valueOf(st.nextToken());
		long m = Long.valueOf(st.nextToken());
		
		long result = n + (m * -1);
		System.out.println(Math.abs(result));
	}
}
