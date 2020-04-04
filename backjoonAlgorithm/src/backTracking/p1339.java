package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �ܾ� ���� 
// ��Ʈ��ŷ ���� 
public class p1339 {
//	static int[] alphabet; //  �ε��� : ���ĺ�, �� : ���� 
	static int[] maxIndex;	// �ε��� : ���ĺ�, �� : �ִ� �ڸ� ��
	static boolean[] checked; // �ش� ���ĺ��� �ܾ�� �߿� ���ԵǾ��ִ��� �Ǻ�  
	static boolean[] isUsed; // �ش� ���ڸ� ����ߴ��� �Ǻ� 
//	static ArrayList<char[]> list = new ArrayList<char[]>();
	static char[][] list = new char[10][];
	static int ASCII = 65;
	static int possibleLen = 0;
	static int sum = 0;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		maxIndex = new int[26];
		checked = new boolean[26];
		isUsed = new boolean[10];
		
		int n = Integer.valueOf(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			String word = br.readLine();
			char[] charArr = word.toCharArray();
			list[i] = charArr;
			for(int j = charArr.length - 1; j >= 0; j--) {
				char ch = charArr[j];
//				maxIndex[ch - ASCII] = Math.max(maxIndex[ch - ASCII], j + 1);
				if(!checked[ch - ASCII]) {
					checked[ch - ASCII] = true;
					possibleLen++;
				}
			}
		}
		
		for(int i = 0; i < checked.length; i++) {
			if(checked[i]) {
				int[] alphabet = new int[26];
				backTracking(i, alphabet);	
				break;
			}
		}
		
		System.out.println(sum);
//		for(int i = 0; i < maxIndex.length; i++) {
//			if(maxIndex[i] != 0) {
//				checked[i] = true;
//			}
//		}
		
//		while(!allChecked()) {
//			int maxindexs = 0;
//			int max = 0;
//			for(int i = 0; i < maxIndex.length; i++) {
//				if(maxIndex[i] < max) {
//					
//				}
//			}
//		}
	}
	
	static void backTracking(int start, int[] alphabet) {
		if(start == isUsed.length) {
			sum = Math.max(sum, calculate(alphabet));
			return;
		}
		else {
			if(checked[start]) {
				for(int i = 9; i >= 10 - possibleLen; i--) {
					if(isUsed[i]) {
						continue;
					}
					
					alphabet[start] = i;
					isUsed[i] = true;
					backTracking(start + 1, alphabet);
//					alphabet[start] = 0;
					isUsed[i] = false;
				}
			}
			else {
				backTracking(start + 1, alphabet);
			}
		}
	}
	
	static int calculate(int[] alphabet) {
		int result = 0;
		for(char[] charArr : list) {
			int sum = 0;
			int len = charArr.length;
			for(int i = 0; i < len; i++) {
				sum += alphabet[charArr[i] - ASCII] * Math.pow(10, len - i - 1);
			}
			result += sum;
		}
		return result;
	}
	
//	static boolean allChecked() {
//		for(boolean check : checked) {
//			if(!check)
//				return false;
//		}
//		return true;
//	}
}

