package week2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Powering a Number
// Divide and Conquer Algorithm ����
// memorization ��� ���
// a^n �� �� ���ϱ�
public class PoweringANumber {

	static int result[];
	public static void main(String args[]) {
		try { 
			String inputFileSrc = new java.io.File("").getAbsolutePath() + "/src/input2.txt";	// ��� ��� ����
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(new String(buffer)); // ��ū���� ���� �и�
	    	
			int a = Integer.parseInt(st.nextToken());	// ù ��° ��
			int n = Integer.parseInt(st.nextToken());	// ù ��° ��
			result = new int[n + 1];
			for(int count = 0; count < n; count++) {
				result[count] = 0;
			}
			
			Thread poweringThread = new Thread(() -> {
	            long startTime = System.currentTimeMillis();
	            System.out.format("powering ��� : %d %n", getPoweringNumber(a, n));
	            System.out.format("powering ���� �ð� : %d ms%n%n", (System.currentTimeMillis() - startTime));
	        });
			
			poweringThread.start();
		}
		catch(NumberFormatException e ) {
			System.out.println("���ڰ� �ƴ� ���ڸ� �Է¹޾ҽ��ϴ�.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("�ش� ��ο� ��ǲ ������ �������� �ʽ��ϴ�.");
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
		if(result[n] != 0) // �̹� ����� ���� �ִ� ���
			return result[n];
		
		if(n % 2 == 0) {	// ���� n�� ¦���̸�
			int divide = n / 2;
			result[n] = getPoweringNumber(a, divide) * getPoweringNumber(a, divide);
			return result[n];
		}
		if(n % 2 == 1) {	// ���� n�� Ȧ���̸�
			int divide = (n - 1) / 2;
			result[n] = a * getPoweringNumber(a, divide) * getPoweringNumber(a, divide);
			return result[n];
		}
		// ����
		return 0;
	}
}
