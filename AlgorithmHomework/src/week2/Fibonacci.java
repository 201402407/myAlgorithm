package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

// �Ǻ���ġ ����
// recursive, Bottom-up, recursive squaring ������� ����
// ���� �ð��� ���� ���
// ��� ��� �ۼ� �� n�� 90���� �����ϰ� ����
// BigInteger : ���� ���� ���� ��� ����
public class Fibonacci {

	private static int method, n;
	private static long startTime;
	private static boolean[] visited1, visited2, visited3;
	private static BigInteger[] arrayResult;
	
	
	public static void main(String args[]) {
		System.out.println("�� ���� ���� �Է��ϼ���. (ù ��° �� : ���� ���.   �� ��° �� : n)");
		System.out.println("��� -> 1: Recursion,  2: Array,  3: Recursive squaring");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		try {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken()); // �˰��� ��� ����
			n = Integer.parseInt(st.nextToken());	// �ش� ��° ���� ���
			visited1 = new boolean[n + 1];
			visited2 = new boolean[n + 1];
			visited3 = new boolean[n + 1];
			arrayResult = new BigInteger[n + 1];
		}
		catch (NumberFormatException e) {
			System.out.println("���ڰ� �ƴ� ���ڸ� �Է¹޾ҽ��ϴ�. NumberFormatException");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("��ū �б� ����. IOException");
			System.exit(0);
		}
		catch (NoSuchElementException e) {
			System.out.println("��ū ������ �����մϴ�. NoSuchElementException");
			System.exit(0);
		}
    	
		// ��� 1�� �����ϴ� ������
		Thread RecursionThread = new Thread(() -> {
            startTime = System.nanoTime();
            BigInteger result = recursion(n);
            System.out.format("Recursion (n = %d) ���� ���� ��� �� : %d %n", n, result);
        });
		// ��� 2�� �����ϴ� ������
		Thread ArrayThread = new Thread(() -> {
            startTime = System.nanoTime();
            BigInteger result = arrayFibonacci(n);
            System.out.format("Recursion (n = %d) ���� ���� ��� �� : %d %n", n, result);
        });
		// ��� 3�� �����ϴ� ������
		Thread squaringThread = new Thread(() -> {
            startTime = System.nanoTime();
            BigInteger result = squaring(n);
            System.out.format("Recursion (n = %d) ���� ���� ��� �� : %d %n", n, result);
        });
		
		switch(method) {
		case 1:
			RecursionThread.start();
			break;
		case 2:
			ArrayThread.start();
			break;
		case 3:
			squaringThread.start();
			break;
			default:
				System.out.println("��� ������ �߸� �ϼ̽��ϴ�.");
				System.exit(0);
				break;
		}
		
	}
	
	// ù ��° ���
	private static BigInteger recursion(int n) {
		if (n == 0) {
			if(!visited1[n])
				printCurrentProgressTime(n, BigInteger.ZERO);
			return BigInteger.ZERO;
		}
		if (n == 1) {
			if(!visited1[n])
				printCurrentProgressTime(n, BigInteger.ONE);
			return BigInteger.ONE;
		}
		BigInteger result = recursion(n - 2).add(recursion(n - 1));
		if(!visited1[n])
			printCurrentProgressTime(n, result);
		return result;
		 
	}
	// �� ��° ���
	private static BigInteger arrayFibonacci(int n) {
		if(visited2[n])
			return arrayResult[n];
		else {
			if(n == 0) {
				printCurrentProgressTime(n, BigInteger.ZERO);
				arrayResult[n] = BigInteger.ZERO;
			}
			else if(n == 1) {
				printCurrentProgressTime(n, BigInteger.ONE);
				arrayResult[n] = BigInteger.ONE;
			}
			else {
				BigInteger result = arrayFibonacci(n - 2).add(arrayFibonacci(n - 1));
				printCurrentProgressTime(n, result);
				arrayResult[n] = result;
			}
			return arrayResult[n];
		}
	}
	// �� ��° ���
	// ��� �ŵ����� ��� + Powering a number ��� ���
	// Strassen Algorithm(��Ʈ�� �˰���) ��� -> �ϴ� ��� �⺻ �� ���� ����Ѵ�.
	// ��ǻ�ʹ� ���� ������ ��,���� ���꺸�� �δ��� ũ��.
	private static BigInteger squaring(int n) {
		// n�� 1�� ����� ���
		BigInteger[][] fibonacciMatrix = {{BigInteger.ONE, BigInteger.ONE}, 	// [ F_2, F_1 ]		-->		[ F(n+1), F(n)	]
				 						{BigInteger.ONE, BigInteger.ZERO}}; 	// [ F_1, F_0 ]		-->		[ F(n), F(n-1)	]
		// TODO : ���� ����� ���� ��Ʈ�� �˰��� ����� �� ���� �� ���Ѵ�.
		if(n == 0)
			return BigInteger.ZERO;
		if(n == 1)
			return BigInteger.ONE;
		if(n == 2)
			return matrixMul(fibonacciMatrix, fibonacciMatrix)[0][1];	// �� ��ġ�� F_1�� ����Ŵ
		
		return pow(fibonacciMatrix, n)[0][1];	// F(n) ����
	}
	
	// �ŵ����� ���ϱ�
	private static BigInteger[][] pow(BigInteger[][] A, int n) {
		if(n == 1)
			return A;
		if(n % 2 == 0) {	// n�� ¦���� ���
			return matrixMul(pow(A, n / 2), pow(A, n / 2));
		}
		if(n % 2 == 1)	{	// n�� Ȧ���� ���
			return matrixMul(A, matrixMul(pow(A, (n - 1) / 2), pow(A, (n - 1) / 2)));
		}
		return A;
	}
	
	// �⺻���� ��� ��.
	// �ð����⵵ : n^3(for�� 3��)
	private static BigInteger[][] matrixMul(BigInteger[][] A, BigInteger[][] B) {
		int n = A.length;
		BigInteger[][] result = new BigInteger[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				result[i][j] = BigInteger.ZERO;
				for(int k = 0; k < n; k++) {
					result[i][j] =  result[i][j].add(A[i][k].multiply(B[k][j]));
				}
			}
		}
		return result;
	}
	
	/*
	 * ���� ���� �ð� ���
	 * ���� ���� : n�� �湮�� ���� ���� ���
	 */
	private static void printCurrentProgressTime(int n, BigInteger result) {
		switch(method) {
		case 1:
			visited1[n] = true;
			break;
		case 2:
			visited2[n] = true;
			break;
		case 3:
			visited3[n] = true;
			break;
			default:
				break;
		}
		System.out.format("Recursion f[%d] = %d \t\t\t\t\t\t ���� �ð� : %f sec%n", n, result, (System.nanoTime() - startTime) / 1000000000.0);
	}
}
