package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 은행 ATM
public class p11399 {
	static List<Integer> result = new ArrayList<Integer>();
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 
	    try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 1 || n > 1000)
				System.exit(0);
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int time = Integer.valueOf(st.nextToken());	
				if(time < 1 || time > 1000)
					System.exit(0);
				result.add(time);
			}
			
			Collections.sort(result);
			
			
			int sum = 0;
			int prevSum = 0;
			for(int element : result) {
				sum += prevSum + element;
				prevSum += element;
			}
			
			// 또 다른 방법
//			for(int i = 0; i < n; i++) {
//				sum += result.get(i) * (n - i);
//			}
			
			System.out.println(sum);
		} 
	    catch (NumberFormatException e) {
	    	System.exit(0);
	    }
	    catch (IOException e) {
			System.exit(0);
		};
	}
}
