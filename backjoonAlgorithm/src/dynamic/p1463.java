package dynamic;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class p1463 {
	static int[] method;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n < 1 || n > 1000000) {
			System.out.println("n의 값이 잘못되었습니다.");
			System.exit(0);
		}
		method = new int[n + 1];
		int result = search(n);
		System.out.println(result);
	}
	
	private static int search(int n) {
		if(n == 1) {
			return 0;
		}
		if(n == 2) {
			return 1;
		}
		if(n == 3) {
			return 1;
		}
		
		if(method[n] != 0) {
			return method[n];
		}
		else {
			ArrayList<Integer> array = new ArrayList<Integer>();
			if(n % 3 == 0) {
				array.add(search(n / 3));
			}
			if(n % 2 == 0) {
				array.add(search(n / 2));
			}
			array.add(search(n - 1));
			Collections.sort(array);	// 오름차순 정렬
			method[n] = array.get(0) + 1;
			return method[n];
		}
	}
}
