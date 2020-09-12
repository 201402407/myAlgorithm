package slidingWindow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// �ּڰ�ã�� ����
// �����̵� ������ ���� 
public class p11003 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int l = Integer.valueOf(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();	// �迭�� index�� ��� ���� Deque
		StringBuilder sb = new StringBuilder();
//		int[] result = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
			// ������ ����� �ε����� ������ �����ϱ�(�ּڰ����� �����ؾ� �Ѵ�. -> �ּڰ��� ã���ֱ� ������)
//			while(!deque.isEmpty() && deque.peekFirst() <= i - l) {
//				deque.removeFirst();
//			}
			
			if(!deque.isEmpty() && deque.getFirst() <= i - l) {
				deque.removeFirst();
			}
			// ���� �ε��� ������ �ּڰ� �ε����� �ֱ� ���� �۾�.
			// ���� A[i]���� ū �͵��� ������ ��������Ƿ� ó������.
			while(!deque.isEmpty() && arr[deque.getLast()] > arr[i]) {
				deque.removeLast();
			}
			
			
			deque.offer(i);	// add : �Ǿտ��� �ٱ������� push. push : �� �տ��� �������� push
			sb.append(arr[deque.getFirst()]).append(" ");
//			result[i] = arr[deque.peekFirst()];
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
//		for(int ele : result) {
//			System.out.print(ele + " ");
//		}
	}
}
