package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 합이 0인 네 정수
// 정렬
// 이분탐색
// 투포인터
public class p7453 {
	static int n;
	static int[] a, b, c, d;
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
		
		System.out.println("____________________________");
		Arrays.sort(a);
		for(int ele : a) {
			System.out.print(ele + " ");
		}
		System.out.println();
		System.out.println("____________________________");
		System.out.println("____________________________");
		Arrays.sort(b);
		for(int ele : b) {
			System.out.print(ele + " ");
		}
		System.out.println();
		System.out.println("____________________________");
		System.out.println("____________________________");
		Arrays.sort(c);
		for(int ele : c) {
			System.out.print(ele + " ");
		}
		System.out.println();
		System.out.println("____________________________");
		System.out.println("____________________________");
		Arrays.sort(d);
		for(int ele : d) {
			System.out.print(ele + " ");
		}
		System.out.println();
		System.out.println("____________________________");
		// AB / CD
		List<Integer> abList = new ArrayList<Integer>();
		pushSumInSet(abList, a, b);
		Collections.sort(abList);
		System.out.println("======================");
		for(int ele : abList) {
			System.out.print(ele + " ");
		}
		System.out.println();
		System.out.println("======================");
		List<Integer> cdList = new ArrayList<Integer>();
		pushSumInSet(cdList, c, d);
		Collections.sort(cdList);
		System.out.println("======================");
		for(int ele : cdList) {
			System.out.print(ele + " ");
		}
		System.out.println();
		System.out.println("======================");
		result += searchZero(abList, cdList);
		System.out.println("------------------------");
		// AC / BD
		List<Integer> acList = new ArrayList<Integer>();
		pushSumInSet(acList, a, c);
		Collections.sort(acList);
		List<Integer> bdList = new ArrayList<Integer>();
		pushSumInSet(bdList, b, d);
		Collections.sort(bdList);
		result += searchZero(acList, bdList);
		System.out.println("------------------------");
		// AD / BC
		List<Integer> adList = new ArrayList<Integer>();
		pushSumInSet(adList, a, d);
		Collections.sort(adList);
		List<Integer> bcList = new ArrayList<Integer>();
		pushSumInSet(bcList, b, c);
		Collections.sort(bcList);
		result += searchZero(adList, bcList);
		System.out.println("------------------------");
		System.out.println(result);
	}
	
	static int searchZero(List<Integer> x, List<Integer> y) {
		int count = 0;
		
		// 이분탐색?
		for(int i = 0; i < x.size(); i++) {
			int xValue = x.get(i);
			if(binarySearch(y, -1 * xValue)) {	// 정확히 부호만 다른 절댓값이 다른 배열안에 있어야 합이 0이 된다.
				System.out.println("x : " + xValue);
				count++;
			}
		}
		
		return count;
	}
	
	// value에 해당하는 값이 x에 있는지 이분탐색
	static boolean binarySearch(List<Integer> x, int value) {
		int start = 0;
		int end = x.size() - 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int midValue = x.get(mid);
			
			if(midValue < value) {
				start = mid + 1;
			}
			else if(midValue == value) {
				System.out.print("y-mid : " + mid + ",, ");
				return true;
			}
			else {
				end = mid - 1;
			}
		}
		
		return false;
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
