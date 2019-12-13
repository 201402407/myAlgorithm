package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열과 점수
// Alignment Algorithm 문제
// 염기 서열 알고리즘
public class p2216 {
	static char[] arr1;
	static char[] arr2;
	static int[][] opt;
	static int a, b, c;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.valueOf(st.nextToken());	// 두 문자가 공백이 아니고 같은 값인 경우
		b = Integer.valueOf(st.nextToken());	// 두 문자 중 적어도 하나는 공백인 경우
		c = Integer.valueOf(st.nextToken());	// 두 문자가 공백이 아니고 다른 값인 경우
		
		st = new StringTokenizer(br.readLine());
		String str1 = st.nextToken();
		st = new StringTokenizer(br.readLine());
		String str2 = st.nextToken();
		arr1 = str1.toCharArray();
		arr2 = str2.toCharArray();
		int arr1Len = arr1.length;
		int arr2Len = arr2.length;
		opt = new int[arr1Len + 1][arr2Len + 1];	// 0부터 시작하므로
		alignment(arr1Len, arr2Len);
		
//		for(int i = 1; i <= arr1Len; i++) {
//			for(int j = 1; j <= arr2Len; j++) {	// b는 둘 중 하나가 공백인 경우의 점수
//				System.out.print(opt[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(opt[arr1Len][arr2Len]);
	}
	
	public static void alignment(int arr1Len, int arr2Len) {
		for(int i = 0; i <= arr1Len; i++) {
			opt[i][0] = i * b;
		}
		for(int i = 0; i <= arr2Len; i++) {
			opt[0][i] = i * b;
		}
		// 
		for(int i = 1; i <= arr1Len; i++) {
			for(int j = 1; j <= arr2Len; j++) {	// b는 둘 중 하나가 공백인 경우의 점수
				opt[i][j] = Math.max(opt[i][j - 1] + b, opt[i - 1][j - 1] + comparePair(arr1[i - 1], arr2[j - 1]));	// 공백 제외하고 점수를 구하는 건지?
				opt[i][j] = Math.max(opt[i][j], opt[i - 1][j] + b);
			}
		}
	}
	
	public static int comparePair(char x, char y) {
		if(x == '\0' || y == '\0') {
			return b;
		}
		return x == y ? a : c;
	}
}
