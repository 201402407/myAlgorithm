package dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// �Ǻ���ġ
public class p1003_bestAnswer {
	private static int[][] arr = new int[41][2];	// ������ 40 ���� ����

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();

        fibonacci();	 // �� �Լ� �ѹ����� �̸� �� ����� ����

        int testCase = Integer.parseInt(reader.readLine());

        for(int i=0 ; i<testCase ; i++){
            int input = Integer.parseInt(reader.readLine());
            builder.append(arr[input][0]).append(" ").append(arr[input][1]).append("\n");
        }
		
		System.out.println(builder);
	}

    private static void fibonacci(){	// �̸� 40���� �����ؼ� �Լ� ȣ�� �ð� ����
        arr[0][0] = 1;	// n = 0 �̸� 0�� 1, 1�� 0 ȣ��
        arr[1][1] = 1;	// n = 1 �̸� 0�� 0, 1�� 1 ȣ��

        for(int i=2 ; i<=40 ; i++){	// i���ʹ� i-1, i-2�� ���� ���ϱ⸸ �ϸ� ��.
            arr[i][0] = arr[i-1][0] + arr[i-2][0];
            arr[i][1] = arr[i-1][1] + arr[i-2][1];
        }
    }
}
