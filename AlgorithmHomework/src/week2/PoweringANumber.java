package week2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Powering a Number
// Divide and Conquer Algorithm 구현
// memorization 방식 사용
// a^n 의 값 구하기
public class PoweringANumber {

	static int result[];
	public static void main(String args[]) {
		try { 
			String inputFileSrc = new java.io.File("").getAbsolutePath() + "/src/input2.txt";	// 상대 경로 설정
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// 파일의 전체 크기만큼 바이트 버퍼 설정
			while(fileInputStream.read(buffer) != -1) {}	// 버퍼에 값 저장
			StringTokenizer st = new StringTokenizer(new String(buffer)); // 토큰으로 숫자 분리
	    	
			int a = Integer.parseInt(st.nextToken());	// 첫 번째 줄
			int n = Integer.parseInt(st.nextToken());	// 첫 번째 줄
			result = new int[n + 1];
			for(int count = 0; count < n; count++) {
				result[count] = 0;
			}
			
			Thread poweringThread = new Thread(() -> {
	            long startTime = System.currentTimeMillis();
	            System.out.format("powering 결과 : %d %n", getPoweringNumber(a, n));
	            System.out.format("powering 실행 시간 : %d ms%n%n", (System.currentTimeMillis() - startTime));
	        });
			
			poweringThread.start();
		}
		catch(NumberFormatException e ) {
			System.out.println("숫자가 아닌 문자를 입력받았습니다.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("해당 경로에 인풋 파일이 존재하지 않습니다.");
			System.exit(0);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int getPoweringNumber(int a, int n) {
		if(n == 0)
			return 1;
		if(n == 1)
			return a;
		if(result[n] != 0) // 이미 계산한 적이 있는 경우
			return result[n];
		
		if(n % 2 == 0) {	// 만약 n이 짝수이면
			int divide = n / 2;
			result[n] = getPoweringNumber(a, divide) * getPoweringNumber(a, divide);
			return result[n];
		}
		if(n % 2 == 1) {	// 만약 n이 홀수이면
			int divide = (n - 1) / 2;
			result[n] = a * getPoweringNumber(a, divide) * getPoweringNumber(a, divide);
			return result[n];
		}
		// 실패
		return 0;
	}
}
