package week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * N���� ���� �ٸ� ���Ҹ� ���� �� ���� DB(���⼱ �迭) A, B�� ���Ͽ�
 * A, B�� ���� 2N���� ���� �� N Ȥ�� N + 1�� ° ���Ҹ� O(LogN)�� �ð� ���⵵ �̳���
 * ã�� �˰����� �����ϰ� �̸� ���α׷����� �����ϴ� ����
 * ��, �� �迭�� ��ġ�� ������ Ż��
 * 201402407 ���ؿ�
 * ��
 */
public class LoopInvariant {
	static int[] a, b;
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputAFileSrc =  fileSrc + "/src/data06_a.txt";	// ��� ��� ����
		String inputBFileSrc =  fileSrc + "/src/data06_b.txt";	// ��� ��� ����
		try {
			BufferedReader brA = new BufferedReader(new FileReader(inputAFileSrc));
			BufferedReader brB = new BufferedReader(new FileReader(inputBFileSrc));
			String strA, strB;
			StringTokenizer stA, stB;
			strA = brA.readLine();
			strB = brB.readLine();
			stA = new StringTokenizer(strA, ", ");
			stB = new StringTokenizer(strB, ", ");
			int n = stA.countTokens();
			
			a = new int[n + 1];
			b = new int[n + 1];	// �ε��� 1���� ����
			for(int i = 1; i <= n; i++) {
				a[i] = Integer.valueOf(stA.nextToken());
				b[i] = Integer.valueOf(stB.nextToken());
			}
			int result = binarySearch(n);
			System.out.println(result);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// a, b �迭�� ��ģ 2n �迭 �� k��°�� ���� �� ���ϱ�
	private static int binarySearch(int k) {
		int midA = 1;	// a, b �� �迭 ��� �ش�Ǵ� k / 2 �ε�����.
		int midB = 1;
		int leftA = 1;
		int leftB = 1;
		int rightA = a.length - 1;
		int rightB = b.length - 1; 
		
		while(leftA <= rightA && leftB <= rightB) {
			midA = (leftA + rightA) / 2;
			midB = (leftB + rightB) / 2;
			if(a[midA] > b[midB]) {
				rightA = midA - 1;
				leftB = midB + 1;
			}
			else if(a[midA] < b[midB]) {
				rightB = midB - 1;
				leftA = midA + 1;
			}
		}
		if(midA + midB < 1000) {	// ���� �� ���� ���� 999(�迭 ũ��� �ε����� Ȧ-¦ ����)�� �� ���
			if(a[midA] > b[midB]) 
				return a[midA + 1];
			else
				return b[midB + 1];
		}
		
		return Math.max(a[midA], b[midB]);
	}
}
