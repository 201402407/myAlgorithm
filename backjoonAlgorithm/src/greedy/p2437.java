package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// 저울 문제
// 그리디 알고리즘
public class p2437 {
	static ArrayList<Integer> list;
	static HashSet<Integer> lists = new HashSet<Integer>();
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] elements = new int[n];
		for(int i = 0; i < n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			elements[i] = ele;
		}
		
		Arrays.sort(elements);
		
	
		int max = 0;
		boolean check = true;	// 최소값 발견 여부(True : 발견, False : 발견 X)
		for(int i = 0; i < n; i++) {
			if(max + 1 >= elements[i]) {
				max += elements[i];
			}
			else {
				break;
			}
		}
		int result = max + 1;
		System.out.println(result);
	}
}

//package greedy;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.StringTokenizer;
//
//// 저울 문제
//// 그리디 알고리즘
//public class p2437 {
//	static ArrayList<Integer> list;
//	static HashSet<Integer> lists = new HashSet<Integer>();
//	
//	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		int n = Integer.valueOf(st.nextToken());
//		st = new StringTokenizer(br.readLine());
//		int[] elements = new int[n];
//		for(int i = 0; i < n; i++) {
//			int ele = Integer.valueOf(st.nextToken());
//			elements[i] = ele;
//		}
//		for(int i = 1; i <= n; i++) {
////			int[] result = new int[n];
////			combination(result, 0, n, i, 0);
//			int[] result = new int[n];
//			boolean[] visited = new boolean[n];
//			combination(elements, visited, 0, n, i);
//		}
//		
//		Arrays.sort(elements);
//		
//		list = new ArrayList<Integer>(lists);
//		Collections.sort(list);
//		
////		for(int ele : list) {
////			System.out.println(ele);
////		}
//		
//		boolean check = true;
//		int result = list.size();
//		for(int i = 0; i < list.size(); i++) {
//			if(i != list.get(i)) {
//				result = i;
//				break;
//			}
//		}
//		System.out.println(result);
//	}
//	
//	// 백트래킹 사용
//	// 사용 예시 : combination(arr, visited, 0, n, r)
//	static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
//	    if(r == 0) {
//	    	int sum = 0;
//	    	for(int i = 0; i < n; i++) {
//	    		if(!visited[i]) {
////	    			System.out.print(arr[i] + " , ");
//	    			sum += arr[i];
//	    		}
//	    	}
//	    	lists.add(sum);
//	        return;
//	    } else {
//	        for(int i=start; i<n; i++) {
//	            visited[i] = true;
//	            combination(arr, visited, i + 1, n, r - 1);
//	            visited[i] = false;
//	        }
//	    }
//	}
//}
