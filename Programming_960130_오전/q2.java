package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class q2 {
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.valueOf(st.nextToken());
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.valueOf(st.nextToken());
//				ArrayList<Integer> phoneBook = new ArrayList<Integer>();
				String[] phoneBook = new String[n];
				for(int j = 0; j < n; j++) {
					st = new StringTokenizer(br.readLine());
					String phone = st.nextToken();
					phoneBook[j] = phone;
				}
				
				Arrays.sort(phoneBook, new Comparator<String>() {  // 문자열 길이 오름차순 정렬
		            @Override
		            public int compare(String s1, String s2) {
		                return Integer.compare(s1.length(), s2.length());
		            }
		        });
				
				boolean result = true;	// 일관성 체크 변수
				for(int k = 0; k < phoneBook.length - 1; k++) {
		            String startStr = phoneBook[k];	
		            StringBuilder sbTemp = new StringBuilder();
		        	// 정규표현식
		            sbTemp.append("^").append(startStr).append("[0-9]+").append("$");
		            Pattern pattern = Pattern.compile(sbTemp.toString());
		            for(int j = k + 1; j < phoneBook.length; j++) {
		                Matcher matcher = pattern.matcher(phoneBook[j]);
		                if(matcher.find()) {	// 접두어가 같으면
		                	if(startStr.length() == phoneBook[j].length()) {	// 길이가 같은 예외 처리
		                		continue;
		                	}
		                    result = false;
		                    break;
		                }
		            }
		            if(!result) {
		            	break;
		            }
		        }
				if(result) {
					sb.append("YES");
				}
				else {
					sb.append("NO");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
		} 
		catch (NumberFormatException e) {
			System.out.println("입력받은 값이 숫자가 아닙니다. 오류 발생! 종료합니다.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOException 오류 발생! SW를 종료합니다.");
			System.exit(0);
		}
	}
}
