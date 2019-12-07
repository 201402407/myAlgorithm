package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class q1 {
	static Long[] allCount;
	static LinkedList<Integer> numList;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 1 || n > 20) {	// ����ó��
				System.out.println("n�� ������ ����ϴ�.");
				System.exit(0);
			}
			allCount = new Long[n + 1];	// ���ڰ� 1���� �����ϹǷ�(1 ~ n) �����̹Ƿ�
			StringBuilder sb = new StringBuilder();
			numList = new LinkedList<Integer>();
			// 1���� N���� ���Ƿ� �迭�� ������ ��ü ����� �� ���ϱ�
			// 1���� N������ ���� �ϳ��� ArrayList�� �ֱ�
			allCount[0] = (long) 1;
			for(int i = 1; i <= n; i++) {
				numList.add(i);
//				allCount[i] = allCount[i - 1].multiply(BigInteger.valueOf(i));
				allCount[i] = allCount[i - 1] * i;
			}
			
			// 2���� �� �Է°� �ޱ�
			st = new StringTokenizer(br.readLine());
			int inputMethodNumber = Integer.valueOf(st.nextToken());
			if(!(inputMethodNumber == 1 || inputMethodNumber == 2)) {	// ����ó��
				System.out.println("1�Ǵ� 2�� ���� �Է� �����մϴ�. ���α׷��� �����մϴ�.");
				System.exit(0);
			}
			// 2�� °�� �Է� ���� ���� �ش��ϴ� �Լ� ����
			if(inputMethodNumber == 1) {	// �ش� k��°�� �ش��ϴ� ��� ��ȣ�� ��Ḧ ���ϴ� ���
//				int k = Integer.valueOf(st.nextToken());
				long k = Long.valueOf(st.nextToken());
				int[] elements = getElements(n, k);
				for(int ele : elements) {
					sb.append(ele).append(" ");
				}
			}
			if(inputMethodNumber == 2) {	// �ش� ������ �� ��° �������� ���ϴ� ���
				int size = st.countTokens();
				System.out.println(size);
				int[] eleArr = new int[n];
				for(int i = 0; i < size; i++) {	// 2�� ° �Է� ���� �޾����Ƿ� 1�� ����.
					int num = Integer.valueOf(st.nextToken());
					eleArr[i] = num;
				}
				long index = getIndex(n, eleArr);
				sb.append(index);
			}
			System.out.println(sb.toString());
		}
		catch (NumberFormatException e) {
			System.out.println("�Է¹��� ���� ���ڰ� �ƴմϴ�. ���� �߻�! �����մϴ�.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOException ���� �߻�! SW�� �����մϴ�.");
			System.exit(0);
		}
	}
	
	static int[] getElements(int n, long k) {
//		if(allCount[n] <= k) {
//			System.out.println("k�� °�� �ش��ϴ� ������ �����ϴ�. �����մϴ�.");
//			System.exit(0);
//		}
		
		int[] result = new int[n];
		int nn = n;
		k -= 1;
		for(int i = 0; i < n; i++) {
			long numIndex = k / (allCount[nn - 1]);
			k = k % allCount[nn - 1];
			if(nn == 2) {
				
			}
			int ele = numList.remove((int)numIndex);	// �ش� ���� ��������Ƿ� ����
			result[i] = ele;
			nn--;
		}
		return result;
	}
	
	static long getIndex(int n, int[] arr) {
		long index = 1;	// �ϳ��� �־ ù ��°�̹Ƿ� 1���� ����
		int nn = n;
		for(int i = 0; i < arr.length; i++) {
			int ele = numList.indexOf(arr[i]);
			if(ele == -1) {
				System.out.println(i);
				System.out.println(arr[i]);
				System.out.println("�Է� �� �� 1 ~ n ������ ���� �ƴ� ���� �ְų� �ߺ��� ���� �ֽ��ϴ�.");
				System.exit(0);
			}
			index += allCount[nn - 1] * ele;
			numList.remove(ele);	// �ش� ���� ��������Ƿ� ����
			nn--;
		}
		return index;
	}
}
