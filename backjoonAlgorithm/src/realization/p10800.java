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
			bolls[i] = new Boll(color, size);
		}
		
		Boll[] sortedBolls = bolls.clone();	// 깊은복사
		Arrays.sort(sortedBolls);	// 오름차순 정렬
		
		// for문 순회
		StringBuilder sb = new StringBuilder();
		for(Boll boll : bolls) {
			// 이분탐색
			int index = binarySearch(sortedBolls, boll.size);
			int sizeSum = 0;
			for(int i = index; i >= 0; i--) {
				if(sortedBolls[i].color != boll.color) {
					sizeSum += sortedBolls[i].size;
				}
			}
			
			sb.append(sizeSum).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int binarySearch(Boll[] sortedBolls, int size) {
		int start = 0;
		int end = sortedBolls.length - 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			if(sortedBolls[mid].size < size) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		return start;
	}
}

class Boll implements Comparable<Boll> {
	int color;
	int size;
	
	Boll(int color, int size) {
		this.color = color;
		this.size = size;
	}

	@Override
	public int compareTo(Boll b) {
		if(this.size < b.size) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
