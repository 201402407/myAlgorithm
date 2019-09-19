package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �Ǻ���ġ ����
// recursive, Bottom-up, recursive squaring ������� ����
// ���� �ð��� ���� ���
// ��� ��� �ۼ� �� n�� 90���� �����ϰ� ����

public class Fibonacci {

	private static int method, n;

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		try {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken()); // �˰��� ��� ����
			n = Integer.parseInt(st.nextToken());	// �ش� ��° ���� ���
		}
		catch (NumberFormatException e) {
			System.out.println("���ڰ� �ƴ� ���ڸ� �Է¹޾ҽ��ϴ�. NumberFormatException");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("��ū �б� ����. IOException");
			System.exit(0);
		} 
    	
		Thread RecursionThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            // System.out.format("powering ��� : %d %n", getPoweringNumber(a, n));
            System.out.format("Recursion ���� �ð� : %d ms%n%n", (System.currentTimeMillis() - startTime));
        });
		RecursionThread.start();
	}
}
