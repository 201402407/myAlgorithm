package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// ¾Ï±â¿Õ
// ÇØ½Ã ¸Ê »ç¿ë?
public class p2776 {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(st.nextToken());
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int ele = Integer.valueOf(st.nextToken());
				map.put(ele, 1);
			}
			
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int k = 1; k <= m; k++) {
				int ele2 = Integer.valueOf(st.nextToken());
				Integer data = map.get(ele2);
				if(data == null) {
					sb.append(0).append("\n");
				}
				else {
					sb.append(1).append("\n");
				}	
			}
		}
		System.out.println(sb.toString());
	}
}
