package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 컬러볼
// 구현

// 1) 공 오름차순 정렬(크기 순)
// 2) 공 하나씩 선택하고, 선택 시 해당크기 이분탐색(같으면 같은 인덱스 끝, 다르면 이전 인덱스)
// 3) 이분탐색 결과 index부터 1번 인덱스까지 순회하며 다른 색이면 크기 누적합 저장
// 4) StringBuilder 출력
public class p10800 {
	static Boll[] bolls;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		StringTokenizer st;
		bolls = new Boll[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.valueOf(st.nextToken());
			int size = Integer.valueOf(st.nextToken());
			bolls[i] = new Boll(i, color, size);
		}
		
		Arrays.sort(bolls);	// 오름차순 정렬
		int[] resultBollSize = new int[n];
		int[] sizeOfBollColor = new int[n + 1];
		
		int sum = 0;
		// for문 순회
		StringBuilder sb = new StringBuilder();
		for(int i = 0, j = 0; i < n; i++) {
			Boll nextBoll = bolls[i];
			Boll prvBoll = bolls[j];
			
			while(prvBoll.size < nextBoll.size) {	// 사이즈가 다를 때만 공을 사로잡은
				sum += prvBoll.size;
				sizeOfBollColor[prvBoll.color] += prvBoll.size;
				
				prvBoll = bolls[++j];
			}
			
			// 공 사로잡기 로직은 위에서 처리
			// 누적합
			resultBollSize[nextBoll.index] = sum - sizeOfBollColor[nextBoll.color];
		}
		
		for(int ele : resultBollSize) {
			sb.append(ele).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

class Boll implements Comparable<Boll> {
	int index;
	int color;
	int size;
	
	Boll(int index, int color, int size) {
		this.index = index;
		this.color = color;
		this.size = size;
	}

	@Override
	public int compareTo(Boll b) {
		if(this.size < b.size) {
			return -1;
		}
		else if(this.size == b.size) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
