package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// ġŲ ��� ����
// ���Ʈ ����
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
		
		// ���� ġŲ�� �и�
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
		// �� �� ġŲ. ������� x���� ġŲ. y���� ��.
		road = new int[homeLen][chickenLen];
		
		// ġŲ �� �Ÿ� ���ϱ�
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
	// ������ ���ϴ� �Լ�. nCr�� ���ϴ� �Լ�. nCr = n-1Cr-1 + n-1Cr
	// arr : 0 ~ n - 1������ ���� �� ���� ���ڵ��� ��Ƴ� ��.
	// index : �ϳ��� ���������� 1�� �߰�. �迭�� �ε����� ��Ÿ��.
	// n : 0 ~ n ���� ����. 	r : ���տ��� �� ���� ������ ������
	// target : 0 ~ n���� �����Ǿ��ִ� ���� �� ������ ���� ����. ������ +1�� ���ش�.(�̹� �Լ����� ������ �� ������ ���ϱ� ����)
	public static void combination(int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			print(arr, index);
		}
		else if(target == n) {	// ��� ������ �̹� �� ���� ���
			return;
		}
		else {
			arr[index] = target;
			combination(arr, index + 1, n, r - 1, target + 1);	// �̹� ���� ������ ���
			combination(arr, index, n, r, target + 1);	// ���� ���� ������ ���
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
