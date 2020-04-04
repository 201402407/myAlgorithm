package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//보석 도둑 문제
// 그리디 알고리즘 
// 우선순위 큐 사용
public class p1202 {
//	static LinkedList<Jewelry> jewelrys; 
//	static ArrayList<Jewelry> jewelrys;
	static Jewelry[] jewelrys;
	static ArrayList<Integer> bags;
	static PriorityQueue<Integer> possibleJewelrys;	//가격만 담음 

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		jewelrys = new Jewelry[n];
		possibleJewelrys = new PriorityQueue<>();
		bags = new ArrayList<Integer>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			int price = Integer.valueOf(st.nextToken());
			Jewelry jewelry = new Jewelry(weight, price);
			jewelrys[i] = jewelry;
		}
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			bags.add(weight);
		}
		
		Collections.sort(bags);	// 가방 오름차순 정렬
		// 보석 무게 순서로 오름차순 정렬 
		Arrays.sort(jewelrys);
		
		long sum = 0;
		int startIndex = 0;
		for(int weight : bags) {
			while(startIndex < n) {
				if(weight >= jewelrys[startIndex].weight) {
					possibleJewelrys.add(-(jewelrys[startIndex].price));	//넣을때 큰 가격부터 꺼내기 위해 음수로 넣는다.
					startIndex++;
				}
				else {
					break;
				}
				
			}
			if(!possibleJewelrys.isEmpty()) {	// 가방의 무게보다 더 무거운 보석들만 있을 수 있으므로 
				sum += Math.abs(possibleJewelrys.poll());	// 우선순위큐에서는 낮은 가격부터 꺼내오므로 절댓값을 씌우면 가장 큰 가
			}
		}
		System.out.println(sum);
	}
}

class Jewelry implements Comparable<Jewelry> {
	int weight;
	int price;
	
	Jewelry(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}

	@Override
	public int compareTo(Jewelry o) {
		// Comparison method violates its general contract! 에러 발생.
		// 모호해서 생김.
		// < 말고 <= 해주면 
		if(this.weight <= o.weight) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
