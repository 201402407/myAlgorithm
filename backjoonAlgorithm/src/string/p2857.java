package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// FBI 문제
// 문자열 정규표현식으로 문자 포함 여부 체크해뵈
public class p2857 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] agents = new String[5]; 
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String fbi = st.nextToken();
			agents[i] = fbi;
		}
		
		ArrayList<Integer> results = searchFBI(agents);
		if(results.isEmpty()) {
			System.out.println("HE GOT AWAY!");
		}
		else {
			for(int ele : results) {
				System.out.print(ele + " ");
			}	
		}
	}
	
	private static ArrayList<Integer> searchFBI(String[] agents) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) {
			Pattern pattern = Pattern.compile(".*FBI.*");
			Matcher matcher = pattern.matcher(agents[i]);
			if(matcher.find()) {
				results.add(i + 1);
			}
		}
		return results;
	}
}
