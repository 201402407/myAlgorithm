package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 단어암기
// 문자열
public class p18119 {
	static int n, m;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		List[] includeAlphabetList = new ArrayList[26];	// 알파벳은 26개
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			
		}
	}
}
