package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// ls ����
// ���ڿ� + DP ���
// ����(����ǥ����) ? -> X.
// ��� ȣ���� ���� ���ڿ� ������ ���鼭 ���� üũ.
// True�� ������ True �����ؼ� �ᱹ ���� ���� ���� True��, False�� �����Ѵٸ� ���� ���� ���� False�� �ǹǷ� �̸� ���� ���� üũ ����
// DFS�� ��� ����(�Ǵ� ���) ã�� �Ͱ� �����
public class p5015 {
	static int[][] dp; 	// y : ���Ϲ��ڿ� index , x : ã������ ���ڿ� index
	static char[] pattern, input;
	
	// enum ó�� ����ϱ� ����
	static final int NONE = -1;
	static final int TRUE = 1;
	static final int FALSE = 0;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = br.readLine();
		pattern = inputStr.toCharArray();
		// �ִ�100����, 100��
		// 102�� �ϴ� ������ IndexOutOfBoundException ������ �߻��ϱ� ������(101����)
		dp = new int[102][102];	 
		
		
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
//		Pattern p = Pattern.compile(patternStr);
		
		// ����ǥ���� matches�� �ذ��Ϸ� ������, �ð� �ʰ� �߻�
		// N^2�� ������ �� ���ھ� Ž��?
		while(n --> 0) {
			String line = br.readLine();
			input = line.toCharArray();
			resetDpArray();	// DP�迭 �ʱ�ȭ
			
			if(recursivePatternCheck(0, 0) == TRUE) {
				sb.append(line).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static int recursivePatternCheck(int patternIndex, int inputIndex) {
		// �켱, ���ϰ� ã������ ���ڿ� �� �� ���� �񱳰� ���� ���
		if(patternIndex >= pattern.length && inputIndex >= input.length) {
			return TRUE;
		}
		
		// ���ϸ� ���� ���
		if(patternIndex >= pattern.length) {
			return FALSE;
		}
		
		// ���ڿ� ���� ���� ��� ���� ���ϴ� ������ '*'�� �ƴϸ� ���� ����.
		if(inputIndex >= input.length && pattern[patternIndex] != '*') {
			return FALSE;
		}
		
		// DP�� ���� �̹� �񱳰� ���� ��� ���� �ٽ� ���ȣ���� �ʿ䰡 �����Ƿ� 
		if(dp[patternIndex][inputIndex] != NONE) {
			return dp[patternIndex][inputIndex];
		}
		
		// ���ڿ� ���ϰ� ��
		// ���� ������ *�� ���
		if(pattern[patternIndex] == '*') {
			int result = FALSE;
			
			// �ش� ���ڿ��� �����ڱ����� ��� ȣ��. �� ���Ŀ��� �ǹ� ����.
			if(inputIndex <= input.length) {
				
				// ����, �ش� ���ڰ� 0�� ���Ե� ��쿡 ���� ���ϰ� �� 
				if(recursivePatternCheck(patternIndex + 1, inputIndex) == TRUE) { result = TRUE; }
				// ����, �ش� ���ڰ� 1�� ���Ե� ��쿡 ���� ����, ���ڿ��� ���� ���ڿ� �� 
				if(recursivePatternCheck(patternIndex + 1, inputIndex + 1) == TRUE) { result = TRUE; }
				// ����, �ش� ���ڰ� 2�� �̻� ���Ե� ��쿡 ���ڿ��� �ٿ� ���ڿ� ��
				if(recursivePatternCheck(patternIndex, inputIndex + 1) == TRUE) { result = TRUE; };
			}
			
			// ���ȣ�� ����� �״�� �����ͼ� DP�� �ְ� ���� 
			return dp[patternIndex][inputIndex] = result;	
			
		}
		else {	// �� �� .�̳� �ٸ� �����̸� 
			if(pattern[patternIndex] == input[inputIndex]) {	// ���ڰ� ��ġ�ϴ� ��� 
				return dp[patternIndex][inputIndex] = recursivePatternCheck(patternIndex + 1, inputIndex + 1);
			}
			
			// ���ڰ� ��ġ���� �ʴ� ��� 
			return dp[patternIndex][inputIndex] = FALSE;
		}
	}
	
	// DP �迭 NONE���� �ʱ�ȭ.���ο� ���ڿ� ����üũ�� ����
	static void resetDpArray() {
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], NONE);
		}
	}
}
