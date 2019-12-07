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
			if(n < 1 || n > 20) {	// 예외처리
				System.out.println("n의 범위를 벗어납니다.");
				System.exit(0);
			}
			allCount = new Long[n + 1];	// 숫자가 1부터 시작하므로(1 ~ n) 까지이므로
			StringBuilder sb = new StringBuilder();
			numList = new LinkedList<Integer>();
			// 1부터 N까지 임의로 배열한 순열의 전체 경우의 수 구하기
			// 1부터 N까지의 수를 하나씩 ArrayList에 넣기
			allCount[0] = (long) 1;
			for(int i = 1; i <= n; i++) {
				numList.add(i);
//				allCount[i] = allCount[i - 1].multiply(BigInteger.valueOf(i));
				allCount[i] = allCount[i - 1] * i;
			}
			
			// 2번쨰 줄 입력값 받기
			st = new StringTokenizer(br.readLine());
			int inputMethodNumber = Integer.valueOf(st.nextToken());
			if(!(inputMethodNumber == 1 || inputMethodNumber == 2)) {	// 예외처리
				System.out.println("1또는 2의 값만 입력 가능합니다. 프로그램을 종료합니다.");
				System.exit(0);
			}
			// 2번 째에 입력 받은 값에 해당하는 함수 실행
			if(inputMethodNumber == 1) {	// 해당 k번째에 해당하는 재료 번호의 재료를 구하는 경우
//				int k = Integer.valueOf(st.nextToken());
				long k = Long.valueOf(st.nextToken());
				int[] elements = getElements(n, k);
				for(int ele : elements) {
					sb.append(ele).append(" ");
				}
			}
			if(inputMethodNumber == 2) {	// 해당 순열이 몇 번째 연구인지 구하는 경우
				int size = st.countTokens();
				System.out.println(size);
				int[] eleArr = new int[n];
				for(int i = 0; i < size; i++) {	// 2번 째 입력 값을 받았으므로 1을 뺀다.
					int num = Integer.valueOf(st.nextToken());
					eleArr[i] = num;
				}
				long index = getIndex(n, eleArr);
				sb.append(index);
			}
			System.out.println(sb.toString());
		}
		catch (NumberFormatException e) {
			System.out.println("입력받은 값이 숫자가 아닙니다. 오류 발생! 종료합니다.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOException 오류 발생! SW를 종료합니다.");
			System.exit(0);
		}
	}
	
	static int[] getElements(int n, long k) {
//		if(allCount[n] <= k) {
//			System.out.println("k번 째에 해당하는 순열은 없습니다. 종료합니다.");
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
			int ele = numList.remove((int)numIndex);	// 해당 수는 사용했으므로 삭제
			result[i] = ele;
			nn--;
		}
		return result;
	}
	
	static long getIndex(int n, int[] arr) {
		long index = 1;	// 하나만 있어도 첫 번째이므로 1부터 시작
		int nn = n;
		for(int i = 0; i < arr.length; i++) {
			int ele = numList.indexOf(arr[i]);
			if(ele == -1) {
				System.out.println(i);
				System.out.println(arr[i]);
				System.out.println("입력 값 중 1 ~ n 까지의 수가 아닌 것이 있거나 중복된 수가 있습니다.");
				System.exit(0);
			}
			index += allCount[nn - 1] * ele;
			numList.remove(ele);	// 해당 수는 사용했으므로 삭제
			nn--;
		}
		return index;
	}
}
