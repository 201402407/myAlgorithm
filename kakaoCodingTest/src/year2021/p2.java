package year2021;

import java.util.HashSet;

public class p2 {
	static HashSet<String> primeNumbers;	// 소수 저장
	static final int A = 'A';
	static int maxNumber = 1;
	public static void main(String args[]) {
		int n = 437674;
		int k = 3;
		int answer = 0;
		primeNumbers = new HashSet<String>();	// 	소수 저장
		// 1) n을 k진수로 변환
		String nStr = change(n, k);
		System.out.println(nStr);
		// 2) for문으로 0탐색
		int len = nStr.length();
		char[] nCharArr = nStr.toCharArray();
		for(int i = 1; i < len; i++) {	// 0인 경우에는 비교할 수 없으므로 생략
			if(nCharArr[i] == '0') {
				String downNumber = getDownNumber(i, nCharArr);
				if(downNumber.equals("")) {
					continue;
				}
				
				long num = Long.valueOf(downNumber);
				if(checkPrimeNumber(num)) {	// 소수이면
					answer++;
				}
			}
		}
		
		// 가장 마지막 0 뒤에 존재하는 수 찾기
		boolean isValid = false;
		StringBuilder sb = new StringBuilder();
		for(int i = len - 1; i >= 0; i--) {
			if(nCharArr[i] == '0') {
				isValid = true;
				break;
			}
			
			sb.append(nCharArr[i]);
		}
		
		if(isValid && sb.length() > 0) {
			long num = Long.valueOf(sb.toString());
			if(checkPrimeNumber(num)) {	// 소수이면
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	// P 여부 체크(에라토스체네스의 체)
	public static boolean checkPrimeNumber(long num) {
		if(primeNumbers.contains(String.valueOf(num))) {
			return true;
		}
		if(num == 1 || num == 0) {
			return false;
		}
		
		int min = maxNumber;	// 이전까지 구했던 최댓값 부터 소수 구하기
		if(num <= min) {	// 입력값이 이전에 구했던 소수의 최댓값보다 작으면 무조건 소수가 아니다(contains not이기 때문)
			return false;
		}
		
		int sqrt = ((int) Math.sqrt(num));
		int result = (int) (num - min + 1);
		boolean[] checks = new boolean[result];
		
		// min ~ num까지의 소수 구하기
		for(int i = 2; i <= sqrt; i++) {
			int minI = (int) (min - (min % i));
			
			for(int temp = minI + i; temp <= num; temp += i) {
				checks[temp - min] = true;
			}
		}
		
		for(int i = 0; i < result; i++) {
			if(!checks[i]) {	// 구한 소수 HashSet에 추가
				primeNumbers.add(String.valueOf((long)(i + min)));
			}
		}
		
		maxNumber = (int) num;
		return primeNumbers.contains(String.valueOf(num));
	}
	
	// parameter index 밑으로 0이 나올 때 까지 길이 String 리턴
	public static String getDownNumber(int startIndex, char[] nCharArr) {
		StringBuilder sb = new StringBuilder();
		for(int i = startIndex - 1; i >= 0; i--) {
			if(nCharArr[i] == '0') {
				break;
			}
			
			sb.append(nCharArr[i]);
		}
		
		return sb.length() == 0 ? "" : sb.reverse().toString();
	}
	
	// k진수로 변환
    public static String change(int number, int k){
        StringBuilder sb = new StringBuilder();
	    int currentNumber = number;
	    
        // 진법 변환할 숫자가 0보다 큰 경우 지속 진행
        while(currentNumber > 0) {
            // 만약 N으로 나누었는데 10보다 작다면 해당 숫자를 바로 append
            if(currentNumber % k < 10){
                sb.append(currentNumber % k);
                
            // 만약 N이 10보다 큰 경우, N으로 나누었는데 10 이상이면 A, B등으로 표현하므로 기존 숫자는 10진법이므로 10만큼 빼고 'A'를 더한다. 
            // 왜냐면 1~9까지는 숫자로 표기하지만, 10 부터는 'A', 'B' 순서로 나타내기 때문이다.
            // 나머지가 10이라면 'A' + 10이 아니라 'A'로 나타내야 하기 때문
            } else {
            	char ch = (char)((currentNumber % k) - 10 + A);
                sb.append(ch);
            }
            
            currentNumber /= k;
        }
        // StringBuilder의 reverse를 사용해야 정상적으로 출력 가능. 안그러면 거꾸로 출력됨
//        System.out.println("숫자 : " + number + "를 " + N + "진법으로 변환한 수 : " + sb.reverse());
        return sb.reverse().toString();
    }
}
