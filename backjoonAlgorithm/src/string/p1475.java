package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열 처리
// 방 번호 문제
// 배열 사용해보기 -> 6과 9 유의하기. 6은 9가 될 수 있어서 한 세트에 6과 9 두개 가능
public class p1475 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String num = st.nextToken();
		int[] counts = new int[10];
		for(int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);
			int index = Character.getNumericValue(ch);
			counts[index]++;
		}
		int max = 0;
		int temp = counts[9] + counts[6];
		counts[6] = (temp / 2) + (temp % 2);
		for(int i = 0; i < 9; i++) {	// 6이나 9나 같으므로 위에서 9에 대해 카운트한 부분을 6에 더한다.
			max = Math.max(max, counts[i]);
		}
		System.out.println(max);
	}
}
