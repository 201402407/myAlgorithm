package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 센서
// 그리디 알고리즘
public class p2212 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int k = Integer.valueOf(br.readLine());
		
		/* 1. ArrayList 활용 */
		/*List<Integer> sensors = new ArrayList<Integer>();
		List<Integer> distances = new ArrayList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.valueOf(st.nextToken());
			sensors.add(num);
		}
		
		// 1) 센서 좌표 오름차순
		Collections.sort(sensors);
		
		// 2) 양 사이 간격 값 구해서 오름차순
		for(int i = 0; i < n - 1; i++) {
			int distance = sensors.get(i + 1) - sensors.get(i);
			distances.add(distance);
		}
		Collections.sort(distances);
		
		// 3) 인덱스 0부터 시작해서, (간격 값 개수 - 1) - (K - 1) 까지 더하기
		int len = distances.size();	// n - 1개
		int sum = 0;
		for(int i = 0; i < len - (k - 1); i++) {
			sum += distances.get(i);
		}*/
		
		/* 2. Array 활용 -> 훨씬 빠르다. */
		int[] sensors = new int[n];
		int[] distances = new int[n - 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.valueOf(st.nextToken());
			sensors[i] = num;
		}
		
		// 1) 센서 좌표 오름차순
		Arrays.sort(sensors);
		
		// 2) 양 사이 간격 값 구해서 오름차순
		for(int i = 0; i < n - 1; i++) {
			int distance = sensors[i + 1] - sensors[i];
			distances[i] = distance;
		}
		Arrays.sort(distances);
		
		// 3) 인덱스 0부터 시작해서, (간격 값 개수 - 1) - (K - 1) 까지 더하기
		// int len = n - 1;	// n - 1개
		int sum = 0;
		for(int i = 0; i < n - k; i++) {
			sum += distances[i];
		}
				
		System.out.println(sum);
	}
}
