package dynamic;

import java.util.Scanner;

// µ¹ °ÔÀÓ
// DP 
public class p9655 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str = n % 2 == 0 ? "CY" : "SK";
		System.out.println(str);
//		int index = 0;
//		
//		while(n > 0) {
//			n = n >= 3 ? n - 3 : n - 1;
//			index++;
//		}
//		
//		if(index % 2 == 0) {
//			System.out.println("CY");
//		}
//		else {
//			System.out.println("SK");
//		}
	}
}
