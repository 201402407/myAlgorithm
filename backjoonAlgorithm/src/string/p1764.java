package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

// 듣보잡 문제
// 문자열 비교해서 따지는거
public class p1764 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		ArrayList<String> results = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			map.put(key, 1);
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String compareKey = st.nextToken();
			Integer val = map.get(compareKey);
			if(val != null) {
				results.add(compareKey);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Collections.sort(results);
		sb.append(results.size()).append("\n");
		for(String str : results) {
			sb.append(str).append("\n");
		}
		System.out.println(sb.toString());
	}
}
