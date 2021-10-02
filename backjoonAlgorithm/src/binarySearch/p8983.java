package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 사냥꾼
// 이진탐색
public class p8983 {
	static int m, n, l;
	static int[] mXList;	// 사대 좌표 리스트
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.valueOf(st.nextToken());	// 사대의 개수
		n = Integer.valueOf(st.nextToken());	// 동물의 수
		l = Integer.valueOf(st.nextToken());	// 사정거리
		mXList = new int[m];
		st = new StringTokenizer(br.readLine());
		
		// 사대 좌표 획득
		for(int i = 0; i < m; i++) {
			int mX = Integer.valueOf(st.nextToken());
			mXList[i] = mX;
		}
		
		Arrays.parallelSort(mXList);
		
		// 동물 좌표 획득
		int count = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			
			// 사정거리보다 y가 큰 경우 -> 절대 잡을 수 없다.
			if(l < y) {
				continue;
			}
			
			// 사정거리보다 x가 큰 경우 -> 절대 잡을 수 없다.
			if(x < mXList[0] - l || mXList[m - 1] + l < x) {
				continue;
			}
			
			// 이분탐색
			if(getIndexForBinarySearch(x, y)) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	static int getRange(int value, int x, int y) {
		return Math.abs(value - x) + y;
	}
	
	// 이분탐색으로 동물 가까이에 붙은 사대를 찾아내서 사정거리 내에 가능한 사대를 찾기
	static boolean getIndexForBinarySearch(int x, int y) {
		int start = 0;
		int end = m - 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int range = getRange(mXList[mid], x, y);
			// 사정거리 범위 이내
			if(range <= l) {	
				return true;
			}
			
			// 사정거리 범위 밖인 경우
			// 동물의 y좌표는 고정이니, x좌표와 가까이에 있는 사대를 찾아야한다.
			else if(mXList[mid] > x) {	// 현재 찾은 사대보다 동물이 앞에 있으면(0에 가까이)
				end = mid - 1;	// 앞쪽 사대를 찾기
			}
			else {	// 현재 찾은 사대보다 동물이 뒤에 있으면(0에 멀리)
				start = mid + 1;	// 뒤쪽 사대를 찾기
			}
		}
		
		return false;
	}
}