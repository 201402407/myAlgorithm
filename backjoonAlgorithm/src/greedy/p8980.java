package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// �ù� ����
// �׸��� �˰���
public class p8980 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int truck = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.valueOf(st.nextToken());
		
//		// �ε��� : 1 ~ n����
//		// �ش� index �������� ���� �� �ִ� �ڽ��� ��ü ����
//		int[] loads = new int[n + 1];
//		// �ش� index �������� ���� �� �ִ� �ڽ��� ��ü ����
//		int[] unloads = new int[n + 1];	// �ε��� : 1 ~ n����
		
		int[] boxs = new int[n + 1]; // �ε��� : 1 ~ n����. �ش� index ������ �������� ���� Ʈ���� ���� �ڽ� ����
		ArrayList<Town> towns = new ArrayList<Town>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int box = Integer.valueOf(st.nextToken());
			towns.add(new Town(from, to, box));
		}
		
		Collections.sort(towns);	// �޴� ���� �������� ����
		
		int boxCount = 0;
		for(Town town : towns) {
			int start = town.start;
			int end = town.end;
			int box = town.box;
			
			int max = 0;
			boolean isLoad = true;
			for(int i = start; i < end; i++) {
				if(boxs[i] >= truck) {
					isLoad = false;
					break;
				}
				max = Math.max(max, boxs[i]);
			}
			
			if(isLoad) {
				int unloads = truck - max;
				if(unloads > box) {
					unloads = box;
				}
				boxCount += unloads;
				
				for(int i = start; i < end; i++) {
					boxs[i] += unloads;
				}
			}
		}
		System.out.println(boxCount);
		
//		int boxCount = 0;
//		int now = 0;
//		// 1�� �������� ������ Ž��
//		for(int i = 1; i <= n; i++) {
//			// ������ ���� �� ���� �Ʊ�
//			// ������
//			if(now - unloads[i] < 0) {	// ���� �� �ִ� ���� �� ������ ������ �ִ� ���� �ִ� ���Է� �Ѵ�.
//				boxCount += now;
//				now = 0;
//			}
//			else {
//				boxCount += unloads[i];
//				now -= unloads[i];
//			}
//			
//			// �Ʊ�
//			if(truck - now < loads[i]) { // �ִ� ���� �� �ִ� ���Ը� ���. �ִ� ���Ժ��� �ڽ��� ������ �ִ� ���Ը�ŭ�� ä���.
//				now = truck;
//			}
//			else {
//				now += loads[i];
//			}
//		}
	}
}

class Town implements Comparable<Town> {
	int start;
	int end;
	int box;
	
	Town(int start, int end, int box) {
		this.start = start;
		this.end = end;
		this.box = box;
	}

	// �������� ������ ���� Comparable Ŭ���� �Լ� ���
	@Override
	public int compareTo(Town town) {
		if(this.end < town.end) {	
			return -1;
		}
		else if(this.end == town.end){
			return 0;
		}
		else {
			return 1;	
		}
	}
}
