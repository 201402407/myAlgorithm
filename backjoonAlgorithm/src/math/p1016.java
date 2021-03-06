package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 薦咽 いい 呪
public class p1016 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long min = Long.valueOf(st.nextToken());
		long max = Long.valueOf(st.nextToken());
		
		int result = (int) (max - min + 1);
		int sqrt = ((int) Math.sqrt(max));
		
		boolean[] checks = new boolean[result]; // 薦咽 いい呪亜 焼還聖 端滴. false : 薦咽いい呪, true : 薦咽いい呪亜 焼還.
		long[] num = new long[result];
		 
		
		for(long i = 2; i <= sqrt; i++) { 
				long squared = i * i;
				long start = min % squared == 0 ? min / squared : (min / squared) + 1;
				for(long j = start; j * squared <= max; j ++) {	// 交聖 1梢 装亜獣鉄( j亜 交 )
					checks[(int) ( (j * squared) - min)] = true;
				}
		}
		
		// 薦咽いい呪 鯵呪 counting
		int count = 0;
		for(int i = 0; i < result; i++) {
			if(!checks[i])
				count++;
		}
		
		System.out.println(count);
	}
}
