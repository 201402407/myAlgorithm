package dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 피보나치
public class p1003_bestAnswer {
	private static int[][] arr = new int[41][2];	// 조건이 40 까지 였음

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();

        fibonacci();	 // 이 함수 한번으로 미리 다 만들어 놓음

        int testCase = Integer.parseInt(reader.readLine());

        for(int i=0 ; i<testCase ; i++){
            int input = Integer.parseInt(reader.readLine());
            builder.append(arr[input][0]).append(" ").append(arr[input][1]).append("\n");
        }
		
		System.out.println(builder);
	}

    private static void fibonacci(){	// 미리 40까지 설정해서 함수 호출 시간 줄임
        arr[0][0] = 1;	// n = 0 이면 0번 1, 1번 0 호출
        arr[1][1] = 1;	// n = 1 이면 0번 0, 1번 1 호출

        for(int i=2 ; i<=40 ; i++){	// i부터는 i-1, i-2번 값을 더하기만 하면 됨.
            arr[i][0] = arr[i-1][0] + arr[i-2][0];
            arr[i][1] = arr[i-1][1] + arr[i-2][1];
        }
    }
}
