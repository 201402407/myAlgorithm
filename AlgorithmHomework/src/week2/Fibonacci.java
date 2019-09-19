package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// �Ǻ���ġ ����
// recursive, Bottom-up, recursive squaring ������� ����
// ���� �ð��� ���� ���
// ��� ��� �ۼ� �� n�� 90���� �����ϰ� ����
// BigInteger : ���� ���� ���� ��� ����
public class Fibonacci {

	private static int method, n;
	private static long startTime;
	private static boolean[] visited;
	
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		try {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken()); // �˰��� ��� ����
			n = Integer.parseInt(st.nextToken());	// �ش� ��° ���� ���
			visited = new boolean[n + 1];
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
            startTime = System.nanoTime();
            BigInteger result = recursion(n);
            System.out.format("Recursion (n = %d) ���� ���� ��� �� : %d %n", n, result);
        });
		RecursionThread.start();
	}
	
	// ù ��° ���
	private static BigInteger recursion(int n) {
		if (n == 0) {
			if(!visited[n])
				printCurrentProgressTime(n, BigInteger.ZERO);
			return BigInteger.ZERO;
		}
		if (n == 1) {
			if(!visited[n])
				printCurrentProgressTime(n, BigInteger.ONE);
			return BigInteger.ONE;
		}
		BigInteger result = recursion(n - 2).add(recursion(n - 1));
		if(!visited[n])
			printCurrentProgressTime(n, result);
		return result;
		 
	}
	
	/*
	 * ���� ���� �ð� ���
	 * ���� ���� : n�� �湮�� ���� ���� ���
	 */
	private static void printCurrentProgressTime(int n, BigInteger result) {
		visited[n] = true;
		System.out.format("Recursion f[%d] = %d \t\t\t\t\t ���� �ð� : %f sec%n", n, result, (System.nanoTime() - startTime) / 1000000000.0);
	}
}
