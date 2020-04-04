package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//���� ���� ����
// �׸��� �˰��� 
// �켱���� ť ���
public class p1202 {
//	static LinkedList<Jewelry> jewelrys; 
//	static ArrayList<Jewelry> jewelrys;
	static Jewelry[] jewelrys;
	static ArrayList<Integer> bags;
	static PriorityQueue<Integer> possibleJewelrys;	//���ݸ� ���� 

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
		
		Collections.sort(bags);	// ���� �������� ����
		// ���� ���� ������ �������� ���� 
		Arrays.sort(jewelrys);
		
		long sum = 0;
		int startIndex = 0;
		for(int weight : bags) {
			while(startIndex < n) {
				if(weight >= jewelrys[startIndex].weight) {
					possibleJewelrys.add(-(jewelrys[startIndex].price));	//������ ū ���ݺ��� ������ ���� ������ �ִ´�.
					startIndex++;
				}
				else {
					break;
				}
				
			}
			if(!possibleJewelrys.isEmpty()) {	// ������ ���Ժ��� �� ���ſ� �����鸸 ���� �� �����Ƿ� 
				sum += Math.abs(possibleJewelrys.poll());	// �켱����ť������ ���� ���ݺ��� �������Ƿ� ������ ����� ���� ū ��
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
		// Comparison method violates its general contract! ���� �߻�.
		// ��ȣ�ؼ� ����.
		// < ���� <= ���ָ� 
		if(this.weight <= o.weight) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
