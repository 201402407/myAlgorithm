package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마술사 이민혁
// 구현 문제
public class p3023 {
	static int cardX, cardY;
	static char[][] card;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int y = Integer.valueOf(st.nextToken());
		int x = Integer.valueOf(st.nextToken());
		
		cardX = x * 2;
		cardY = y * 2;
		card = new char[cardY][cardX];
		
		// 입력 받기
		for(int i = 0; i < y; i++) {
			 String str = br.readLine();
			for(int j = 0; j < x; j++) {
				card[i][j] = str.charAt(j);
			}
		}
		
		// 오른쪽 대칭
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				int reverseX = cardX - 1 - j;
				card[i][reverseX] = card[i][j];
			}
		}
		
		// 아래 대칭
		for(int i = 0; i < y; i++) {
			int reverseY = cardY - 1 - i;
			for(int j = 0; j < cardX; j++) {
				card[reverseY][j] = card[i][j];	
			}
		}
		
		// 에러 수정
		st = new StringTokenizer(br.readLine());
		int errorX = Integer.valueOf(st.nextToken());
		int errorY = Integer.valueOf(st.nextToken());
		char ch = card[errorY - 1][errorX - 1];
		if(ch == '#') {
			card[errorY - 1][errorX - 1] = '.';
		}
		else {
			card[errorY - 1][errorX - 1] = '#';
		}
		
		// 결과 출력
		for(int i = 0; i < cardY; i++) {
			for(int j = 0; j < cardX; j++) {
				System.out.print(card[i][j]);
			}
			System.out.println();
		}
	}
}
