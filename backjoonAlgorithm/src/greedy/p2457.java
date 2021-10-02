package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 공주님의 정원
// 그리디
public class p2457 {
	static int n;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		StringTokenizer st;
		Flower[] flowers = new Flower[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int startMonth = Integer.valueOf(st.nextToken());
			int startDay = Integer.valueOf(st.nextToken());
			int endMonth = Integer.valueOf(st.nextToken());
			int endDay = Integer.valueOf(st.nextToken());
			
			int start = startMonth * 100 + startDay;
			int end = endMonth * 100 + endDay;
			flowers[i] = new Flower(start, end);
		}
		
		Arrays.parallelSort(flowers);	// 조건에 맞게 정렬
		
		int endDay = 1201;
		int start = 301;
		int count = 0;
		int max = 0;
		int index = 0;
		
		// 현재 종료일이 12월 1일보다 적은 경우만 가능(12월 1일 초과면 이미 조건 만족했기 때문에)
		while(start < endDay) {
			boolean isFinded = false;	// 새 꽃 찾은지 여부 확인
			
			for(int i = index; i < n; i++) {
				if(flowers[i].start > start) {	// 종료일보다 시작일이 이후면 의미없음. 종료일에는 시작해야 이어지기 때문에
					break;
				}
				
				if(max < flowers[i].end) {
					isFinded = true;
					max = flowers[i].end;
					index = i + 1;
				}
			}
			
			if(isFinded) {
				start = max;
				count++;
			}
			else {
				break;
			}
		}
				
		if(max < endDay) {
			System.out.println(0);
		}
		else {
			System.out.println(count);
		}
	}
}

class Flower implements Comparable<Flower> {
	int start;
	int end;
	
	Flower(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	// 1) 시작일 낮은 순
	// 2) 종료일 높은 순
	@Override
	public int compareTo(Flower f) {
		if(this.start < f.start) {
			return -1;
		}
		else if(this.start == f.start) {
			if(this.end > f.end) {
				return -1;
			}
			else if(this.end == f.end) {
				return 0;
			}
			return 1;
		}
		else {
			return 1;
		}
	}
}
