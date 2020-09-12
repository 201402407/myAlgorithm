package slidingWindow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 최솟값찾기 문제
// 슬라이딩 윈도우 문제 
public class p11003 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int l = Integer.valueOf(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();	// 배열의 index를 담기 위한 Deque
		StringBuilder sb = new StringBuilder();
//		int[] result = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
			// 범위를 벗어나는 인덱스가 있으면 제거하기(최솟값부터 제거해야 한다. -> 최솟값을 찾고있기 때문에)
//			while(!deque.isEmpty() && deque.peekFirst() <= i - l) {
//				deque.removeFirst();
//			}
			
			if(!deque.isEmpty() && deque.getFirst() <= i - l) {
				deque.removeFirst();
			}
			// 범위 인덱스 내에서 최솟값 인덱스를 넣기 위한 작업.
			// 현재 A[i]보다 큰 것들은 어차피 쓸모없으므로 처분하자.
			while(!deque.isEmpty() && arr[deque.getLast()] > arr[i]) {
				deque.removeLast();
			}
			
			
			deque.offer(i);	// add : 맨앞에서 바깥쪽으로 push. push : 맨 앞에서 안쪽으로 push
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
