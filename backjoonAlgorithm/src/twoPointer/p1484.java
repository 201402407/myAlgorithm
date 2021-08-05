package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 다이어트
// 투포인터
public class p1484 {
	static int g;
	static final int MAX = 100000;
	static int[] powArr;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		g = Integer.valueOf(br.readLine());
		
//		즉, x^2 - y^2 = G 가 되는 x의 값을 구하는 문제
//		x^2 = G + y^2
				
//		start = 0(y), end = 1(x) 라고 가정하고,
//		배열은 물론 1에서 N(임의의 끝 지점) 으로 잡고 진행한다. -> G < 100000
//		end^2 - start^2 과 G를 비교해서, > G 이면 start를 늘리고, <= G 이면 end를 늘려볼까?
//		개수 찾으면 end의 값을 리스트에 저장
		
		// 0KG 나가는 사람은 없으니까.
		// 최소 1Kg 이상 나가는걸로
//		end^2 = G + start^2
		
		// 임시 저장
		// 초기 제곱값 선언
		powArr = new int[MAX + 1];
		for(int i = 1; i <= MAX; i++) {
			powArr[i] = i * i;
		}
		
		int start = 1;	
		int end = 2;
		List<Integer> result = new ArrayList<Integer>();
		
		while(end <= MAX) {
			int weight = powArr[end] - powArr[start];
			if(weight == g) {
				result.add(end);
			}
			if(weight > g) {
				start++;
			}
			if(weight <= g) {
				end++;
			}
		}
		
			
		Collections.sort(result);
		if(result.isEmpty() || g == 1) {
			System.out.println(-1);
		}
		else {
			for(int ele : result) {
				System.out.println(ele);
			}
		}

	}
}
