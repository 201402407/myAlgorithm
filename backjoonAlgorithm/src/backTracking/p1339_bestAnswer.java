package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1339_bestAnswer {
	static char[][] list = new char[10][];
	static int[] alphabet;
	static int number = 9;
	static int ASCII = 65;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		alphabet = new int[26];	// A ~ Z 26°³.
		
		for(int i = 0; i < n; i++) {
			String word = br.readLine();
			list[i] = word.toCharArray();
			
			int len = word.length();
			for(int j = 0; j < len; j++) {
				alphabet[list[i][j] - ASCII] += Math.pow(10, len - j - 1);
			}
		}
		
		Arrays.sort(alphabet);
		
		int sum = 0;
		for(int i = 25; i >= 0; i--) {
			if(alphabet[i] == 0) {
				break;
			}
			sum += alphabet[i] * number--; 
		}
		System.out.println(sum);
	}
}
