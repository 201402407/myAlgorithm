package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

// 합이 0인 네 정수
// 정렬
// 이분탐색
// 투포인터
public class p7453 {
	static int n;
	static int[] a, b, c, d;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		a = new int[n];
		b = new int[n];
		c = new int[n];
		d = new int[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				int num = Integer.valueOf(st.nextToken());
				getArray(j)[i] = num;
			}
		}

		
		
		// 배열 오름차순 정렬
		for(int i = 0; i < 4; i++) {
			Arrays.sort(getArray(i));
		}
		
//		12초니까, ABCD를 AB, CD 이렇게 두 부분으로 나누는 경우의 수 -> 6개 -> 그렇지만, AB/CD = CD/AB 이므로 총 3가지
//		즉, 한 경우의수당 2초 내로 진행하게 함 -> O(n^2)
//		그럼, 두 부분으로 나눠서 각 배열끼리 전부 더해서 나온 값을 하나의 배열로 새로 만들기 -> O(n^2)
		
		int result = 0;
		
		// AB / CD
		List<Integer> abList = new ArrayList<Integer>();
		pushSumInSet(abList, a, b);
		Collections.sort(abList);
		List<Integer> cdList = new ArrayList<Integer>();
		pushSumInSet(cdList, c, d);
		Collections.sort(cdList);
		result += searchZero(abList, cdList);
		System.out.println(result);
	}
	
	static int searchZero(List<Integer> x, List<Integer> y) {
		int count = 0;
		
		// 이분탐색?
		for(int i = 0; i < x.size(); i++) {
			int xValue = -1 * x.get(i);
			int result = 0;
			if(map.containsKey(xValue)) {
				result = map.get(xValue);
			}
			else {
				result = binarySearch(y, xValue);
			}
			
			count += result;
		}
		
		return count;
	}
	
	// value에 해당하는 값이 x에 있는지 이분탐색
	static int binarySearch(List<Integer> x, int value) {
		// 깊은복사
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(x);
		int count = 0;
		boolean valid = false;
		while(!valid) {
			int start = 0;
			int end = temp.size() - 1;
			
			while(start <= end) {
				int mid = (start + end) / 2;
				int midValue = temp.get(mid);
				
				if(midValue < value) {
					start = mid + 1;
				}
				else if(midValue == value) {
					temp.remove(mid);
					count++;
					valid = true;
					break;
				}
				else {
					end = mid - 1;
				}
			}
			
			if(valid) {
				valid = false;
			}
			else {
				break;
			}
		}
		
		map.put(value, count);
		return count;
	}
	
	static void pushSumInSet(List<Integer> list, int[] temp, int[] temp2) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int sum = temp[i] + temp2[j];
				list.add(sum);
			}
		}
	}
	
	static int[] getArray(int j) {
		switch(j) {
		case 0:
			return a;
		case 1:
			return b;
		case 2:
			return c;
		case 3:
			return d;
		default:
			return null;
		}
	}
	
	static void setArray(int num, int i, int j) {
		switch(j) {
		case 0:
			a[i] = num;
			break;
		case 1:
			b[i] = num;
			break;
		case 2:
			c[i] = num;
			break;
		case 3:
			d[i] = num;
			break;
			
		}
	}
}
