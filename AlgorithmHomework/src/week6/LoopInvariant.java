package week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * N개의 서로 다른 원소를 갖는 두 개의 DB(여기선 배열) A, B에 대하여
 * A, B를 합한 2N개의 원소 중 N 혹은 N + 1번 째 원소를 O(LogN)의 시간 복잡도 이내로
 * 찾는 알고리즘을 설계하고 이를 프로그램으로 구현하는 문제
 * 단, 두 배열을 합치는 행위는 탈락
 * 201402407 이해원
 * 끝
 */
public class LoopInvariant {
	static int[] a, b;
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputAFileSrc =  fileSrc + "/src/data06_a.txt";	// 상대 경로 설정
		String inputBFileSrc =  fileSrc + "/src/data06_b.txt";	// 상대 경로 설정
		try {
			BufferedReader brA = new BufferedReader(new FileReader(inputAFileSrc));
			BufferedReader brB = new BufferedReader(new FileReader(inputBFileSrc));
			String strA, strB;
			StringTokenizer stA, stB;
			strA = brA.readLine();
			strB = brB.readLine();
			stA = new StringTokenizer(strA, ", ");
			stB = new StringTokenizer(strB, ", ");
			int n = stA.countTokens();
			
			a = new int[n + 1];
			b = new int[n + 1];	// 인덱스 1부터 시작
			for(int i = 1; i <= n; i++) {
				a[i] = Integer.valueOf(stA.nextToken());
				b[i] = Integer.valueOf(stB.nextToken());
			}
			int result = binarySearch(n);
			System.out.println(result);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// a, b 배열을 합친 2n 배열 중 k번째로 작은 값 구하기
	private static int binarySearch(int k) {
		int midA = 1;	// a, b 두 배열 모두 해당되는 k / 2 인덱스값.
		int midB = 1;
		int leftA = 1;
		int leftB = 1;
		int rightA = a.length - 1;
		int rightB = b.length - 1; 
		
		while(leftA <= rightA && leftB <= rightB) {
			midA = (leftA + rightA) / 2;
			midB = (leftB + rightB) / 2;
			if(a[midA] > b[midB]) {
				rightA = midA - 1;
				leftB = midB + 1;
			}
			else if(a[midA] < b[midB]) {
				rightB = midB - 1;
				leftA = midA + 1;
			}
		}
		if(midA + midB < 1000) {	// 만약 두 개의 합이 999(배열 크기와 인덱스의 홀-짝 차이)가 된 경우
			if(a[midA] > b[midB]) 
				return a[midA + 1];
			else
				return b[midB + 1];
		}
		
		return Math.max(a[midA], b[midB]);
	}
}
