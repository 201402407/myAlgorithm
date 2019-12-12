package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 안녕 문제
// 0-1 knapsack 알고리즘
// 동적 프로그래밍
public class p1535 {
	static int[][] opt;
	static int[][] member;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		member = new int[n + 1][2];	// 0 : 잃는 체력, 1 : 얻는 기쁨 
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int health = Integer.valueOf(st.nextToken());
			member[i][0] = health;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int happy = Integer.valueOf(st.nextToken());
			member[i][1] = happy;
		}
		
		opt = new int[n + 1][100];	// 총 체력이 100이니까, 잃는 체력의 합이 99를 넘기면 안됨. 0 ~ 99
		getOPT(n, 100);
		int result = searchOPT(n, 100);
		System.out.println(result);
	}
	
	// 0 : 체력,  1 : 기쁨
	public static void getOPT(int n, int health) {
		for(int i = 1; i <= n; i++) {
			for(int w = 0; w < health; w++) {	// 건강이 0 부터 99까지
				if(member[i][0] > w) {
					opt[i][w] = opt[i - 1][w];
				}
				else {
					opt[i][w] = Math.max(opt[i - 1][w], member[i][1] + opt[i - 1][w - member[i][0]]);
				}
			}
		}
	}
	
	public static int searchOPT(int n, int health) {
		int result = 0;
		for(int i = 1; i <= n; i++) {
			for(int w = 0; w < health; w++) {	// 건강이 0 부터 99까지
				result = Math.max(result, opt[i][w]);
			}
		}
		return result;
	}
}
