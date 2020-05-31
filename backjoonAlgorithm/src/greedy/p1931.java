package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1931 : 회의실 배정
// 택배와 비슷. 그리디
public class p1931 {
//	static ArrayList<ConferenceTime> list;
	static ConferenceTime[] arr;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
//		list = new ArrayList<ConferenceTime>();
		arr = new ConferenceTime[n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			arr[i] = new ConferenceTime(start, end);
//			list.add(new ConferenceTime(start, end));
		}
		
		// 정렬
//		Collections.sort(list);
		Arrays.sort(arr);
		
		int count = search(n);
		System.out.println(count);
	}
	
	// 순회하면서 회의 선택
	static int search(int n) {
		int time = 0;
		int count = 0;
		
		for(ConferenceTime ct : arr) {
//			ConferenceTime ct = list.get(i);
			
			// 선택
			if(ct.start >= time) {
				count++;
				time = ct.end;
			}
		}
		
		return count;
	}
}

class ConferenceTime implements Comparable<ConferenceTime> {
	int start;
	int end;
	
	public ConferenceTime(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(ConferenceTime ct) {
		if(this.end < ct.end) {
			return -1;
		}
		else if(this.end == ct.end) {
			if(this.start < ct.start) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
}
