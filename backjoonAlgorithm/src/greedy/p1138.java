package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 한 줄로 서기 문제
// 그리디 알고리즘 -> 순열 구하기와 비슷?!
public class p1138 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		
		LinkedList<Integer> indexs = new LinkedList<Integer>();
		int[] line = new int[n];
		
		// LinkedList 초기 값 설정
		for(int i = 0; i < n; i++) {
			indexs.add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int people = Integer.valueOf(st.nextToken());
			int index = indexs.remove(people);
			line[index] = i;	// 해당하는 키 i를 가진 사람은 index 번째 줄에 서있다.
		}
		for(int ele : line) {
			System.out.print(ele + " ");
		}
	}
}