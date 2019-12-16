package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 치킨 배달 문제
// 브루트 포스
public class p15686 {
	static int[][] road;
	static int result = Integer.MAX_VALUE;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		ArrayList<Point> home = new ArrayList<Point>();
		ArrayList<Point> chicken = new ArrayList<Point>();
		
		// 집과 치킨집 분리
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				int num = Integer.valueOf(st.nextToken());
				if(num == 1) {
					home.add(new Point(x, y));
				}
				if(num == 2) {
					chicken.add(new Point(x, y));
				}
			}
		}
		int chickenLen = chicken.size();
		int homeLen = home.size();
		// 집 별 치킨. 순서대로 x축은 치킨. y축은 집.
		road = new int[homeLen][chickenLen];
		
		// 치킨 별 거리 구하기
		for(int y = 0; y < homeLen; y++) {
			Point homePoint = home.get(y);
			for(int x = 0; x < chickenLen; x++) {
				Point chickenPoint = chicken.get(x);
				int distance = Math.abs(chickenPoint.x - homePoint.x) + Math.abs(chickenPoint.y - homePoint.y);
				road[y][x] = distance;
			}
		}

//		for(int y = 0; y < home.size(); y++) {
//			for(int x = 0; x < chicken.size(); x++) {
//				System.out.print(road[y][x] + " ");
//			}
//			System.out.println();
//		}
		
		int[] combinations = new int[chickenLen];
		combination(combinations, 0, chickenLen, m, 0);
		System.out.println(result);
		
	}
	// 조합을 구하는 함수. nCr을 구하는 함수. nCr = n-1Cr-1 + n-1Cr
	// arr : 0 ~ n - 1까지의 숫자 중 뽑은 숫자들을 모아논 곳.
	// index : 하나가 정해졌으면 1을 추가. 배열의 인덱스를 나타냄.
	// n : 0 ~ n 개의 숫자. 	r : 조합에서 몇 개를 추출할 것인지
	// target : 0 ~ n으로 나열되어있는 원소 중 선택할 때의 숫자. 무조건 +1을 해준다.(이번 함수에서 선택할 지 안할지 정하기 때문)
	public static void combination(int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			print(arr, index);
		}
		else if(target == n) {	// 모든 갯수를 이미 다 뽑은 경우
			return;
		}
		else {
			arr[index] = target;
			combination(arr, index + 1, n, r - 1, target + 1);	// 이번 것을 선택한 경우
			combination(arr, index, n, r, target + 1);	// 다음 것을 선택할 경우
		}
	}
	
	public static void print(int[] arr, int len) {
		int ele = 0;
		for(int y = 0; y < road.length; y++) {
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < len; i++) {
				int x = arr[i];
				min = Math.min(road[y][x], min);
//				System.out.print(arr[i] + " ");
			}
			ele += min;
		}
//		System.out.println(ele);
		result = Math.min(result, ele);
		
//		for(int i = 0; i < len; i++) {
////			int x = arr[i];
////			min = Math.min(road[y][x], min);
//			System.out.print(arr[i] + " ");
//		}
//		System.out.println();
	}
}

class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
