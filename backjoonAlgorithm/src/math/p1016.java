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
//		int count = 0;
//		ArrayList<Long> squared = new ArrayList<Long>();	// 昔畿什 0戚 2
		int sqrt = ((int) Math.sqrt(max));
		boolean[] isSosu = new boolean[sqrt + 1];
		Arrays.fill(isSosu, true);
		// 社呪 姥馬奄
//		int sqrt2 = (int) Math.floor(Math.sqrt(sqrt));
//		for(int i = 2; i <= sqrt2; i++) {
////			int num = i;
//			if(!isSosu[i])
//				continue;
//			for(int j = i + 1; j <= sqrt; j++) {
//				if(!isSosu[j]) 
//					continue;
//				if(j % i == 0) 
//					isSosu[j] = false;
//			}
//		}
		
		// 薦咽呪 姥馬奄
//		for(int i = 2; i <= sqrt; i++) {
//			if(isSosu[i]) // 社呪昔 呪幻 薦咽呪稽 隔奄
//				squared.add((long) i * i);	
//		}
		
		boolean[] checks = new boolean[result]; // 薦咽 いい呪亜 焼還聖 端滴. false : 薦咽いい呪, true : 薦咽いい呪亜 焼還.
		long[] num = new long[result];
		long temp = min;
		
		// 壕伸 鎧拭 嬢恐 呪 X研 隔奄
		for(int i = 0; i < result; i++) {
			num[i] = temp++;
		}
		 
		// 拭虞塘什端革什税 端
		// 薦咽いい呪研 端滴馬奄
//		for(int i = 2; i <= sqrt; i++) {
////			if(isSosu[i]) {	// 社呪昔 呪幻 薦咽呪稽 隔奄 
//				long squared = i * i;
//				int moc = (int) (min % squared == 0 ? 0 : min % squared);	// index
//				for(int j = moc; j < result; j += squared) {	// 交聖 1梢 装亜獣鉄
//					if(!checks[j])
//						checks[j] = true;
//				}
////			}
//		}
		
		for(int i = 2; i <= sqrt; i++) { 
				long squared = i * i;
				long start = ((min - 1) / squared + 1) * squared;
//				int moc = (int) (min % squared == 0 ? 0 : min % squared);	// index
				for(long j = start; j <= max; j += squared) {	// 交聖 1梢 装亜獣鉄
					if(!checks[(int) (j - min)])
						checks[(int) (j - min)] = true;
				}
		}
		
		
//		for(int i = 0; i < result; i++) {
//			if(!checks[i]) {
//				for(long ele : squared) {
//					if(num[i] % ele == 0) {
//						checks[i] = true;
//						break;
//					}
//				}	
//			}
//		}
		
		// 薦咽いい呪 鯵呪 counting
		int count = 0;
		for(int i = 0; i < result; i++) {
			if(!checks[i])
				count++;
		}
		
		System.out.println(count);
//		while(temp <= max) {
//			for(long ele : squared) {
//				if(temp % ele == 0) {
//					result--;
//					break;
//				}
//			}
//			temp++;
//		}
		
	}
}
