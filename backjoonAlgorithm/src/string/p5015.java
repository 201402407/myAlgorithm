package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// ls 문제
// 문자열 + DP 사용
// 패턴(정규표현식) ? -> X.
// 재귀 호출을 통해 문자열 끝까지 가면서 패턴 체크.
// True면 줄줄이 True 리턴해서 결국 시작 지점 또한 True고, False를 리턴한다면 시작 지점 또한 False가 되므로 이를 통해 패턴 체크 가능
// DFS로 경로 개수(또는 경로) 찾는 것과 비슷함
public class p5015 {
	static int[][] dp; 	// y : 패턴문자열 index , x : 찾으려는 문자열 index
	static char[] pattern, input;
	
	// enum 처럼 사용하기 위함
	static final int NONE = -1;
	static final int TRUE = 1;
	static final int FALSE = 0;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = br.readLine();
		pattern = inputStr.toCharArray();
		// 최대100글자, 100개
		// 102로 하는 이유는 IndexOutOfBoundException 오류가 발생하기 때문에(101에서)
		dp = new int[102][102];	 
		
		
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
//		Pattern p = Pattern.compile(patternStr);
		
		// 정규표현식 matches로 해결하려 했으나, 시간 초과 발생
		// N^2로 끝내게 한 문자씩 탐색?
		while(n --> 0) {
			String line = br.readLine();
			input = line.toCharArray();
			resetDpArray();	// DP배열 초기화
			
			if(recursivePatternCheck(0, 0) == TRUE) {
				sb.append(line).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static int recursivePatternCheck(int patternIndex, int inputIndex) {
		// 우선, 패턴과 찾으려는 문자열 둘 다 전부 비교가 끝난 경우
		if(patternIndex >= pattern.length && inputIndex >= input.length) {
			return TRUE;
		}
		
		// 패턴만 끝난 경우
		if(patternIndex >= pattern.length) {
			return FALSE;
		}
		
		// 문자열 전부 비교한 경우 현재 비교하는 패턴이 '*'가 아니면 패턴 실패.
		if(inputIndex >= input.length && pattern[patternIndex] != '*') {
			return FALSE;
		}
		
		// DP를 통해 이미 비교가 끝난 경우 굳이 다시 재귀호출할 필요가 없으므로 
		if(dp[patternIndex][inputIndex] != NONE) {
			return dp[patternIndex][inputIndex];
		}
		
		// 문자열 패턴과 비교
		// 현재 패턴이 *인 경우
		if(pattern[patternIndex] == '*') {
			int result = FALSE;
			
			// 해당 문자열의 끝문자까지만 재귀 호출. 그 이후에는 의미 없다.
			if(inputIndex <= input.length) {
				
				// 만약, 해당 문자가 0개 포함된 경우에 다음 패턴과 비교 
				if(recursivePatternCheck(patternIndex + 1, inputIndex) == TRUE) { result = TRUE; }
				// 만약, 해당 문자가 1개 포함된 경우에 다음 패턴, 문자열의 다음 문자와 비교 
				if(recursivePatternCheck(patternIndex + 1, inputIndex + 1) == TRUE) { result = TRUE; }
				// 만약, 해당 문자가 2개 이상 포함된 경우에 문자열의 다움 문자와 비교
				if(recursivePatternCheck(patternIndex, inputIndex + 1) == TRUE) { result = TRUE; };
			}
			
			// 재귀호출 결과를 그대로 가져와서 DP에 넣고 리턴 
			return dp[patternIndex][inputIndex] = result;	
			
		}
		else {	// 그 외 .이나 다른 문자이면 
			if(pattern[patternIndex] == input[inputIndex]) {	// 문자가 일치하는 경우 
				return dp[patternIndex][inputIndex] = recursivePatternCheck(patternIndex + 1, inputIndex + 1);
			}
			
			// 문자가 일치하지 않는 경우 
			return dp[patternIndex][inputIndex] = FALSE;
		}
	}
	
	// DP 배열 NONE으로 초기화.새로운 문자열 패턴체크를 위해
	static void resetDpArray() {
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], NONE);
		}
	}
}
