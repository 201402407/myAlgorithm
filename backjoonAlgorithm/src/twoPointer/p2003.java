package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 占쎈떮占쎈７占쎌뵥占쎄숲 占쎈르�⑥쥓�봺筌앾옙
// 占쎈땾占쎈굶占쎌벥 占쎈�2 
public class p2003 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		
		// 占쎌뿯占쎌젾揶쏉옙 獄쏆룄由� 
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		
		System.out.println(twoPointer(arr, m));
	}
	
	static int twoPointer(int[] arr, int m) {
		int count = 0;
		int startPoint = 0, endPoint = 0;
		int len = arr.length;
		int sum = 0;
		
		while(true) {
			// M 癰귣��뼄 sum占쎌뵠 占쎄쾿筌롳옙 揶쏅�れ뱽 餓κ쑴肉э옙苑� M占쎌뱽 筌띿쉸�뼚占쎈튊 占쎈릭沃섓옙嚥∽옙 占쎌겱占쎌삺 startIndex占쎌벥 揶쏅�れ뱽 �뜮�눊�� 占쎈립 燁삼옙 占쎈링占쎌몵嚥∽옙 占쎌뵠占쎈짗占쎈퉸占쎈튊 占쎈립占쎈뼄.
			if(sum >= m) {  
				sum -= arr[startPoint++];
				
			}
			else if(endPoint >= len) {
				break;
			}
			else { // M 癰귣��뼄 sum占쎌뵠 占쎌삂占쎌몵筌롳옙 揶쏅�れ뱽 占쎈뮎占쎌젻占쎄퐣 M占쎌뱽 筌띿쉸�뼚占쎈튊 占쎈릭沃섓옙嚥∽옙 占쎌겱占쎌삺 endIndex�몴占� 占쎈립 燁삼옙 占쎈링占쎌몵嚥∽옙 占쎌뵠占쎈짗占쎈뻻占쎄텕�⑨옙 域밸챷�뵥占쎈쑔占쎈뮞占쎌벥 占쎌뜚占쎈꺖 揶쏅�れ뱽 占쎈쐭占쎈퉸餓μ꼷鍮� 占쎈립占쎈뼄.
				sum += arr[endPoint++];
			}
			
			if(sum == m) {
				count++;
			}
		}
		
		return count;
	}
}
