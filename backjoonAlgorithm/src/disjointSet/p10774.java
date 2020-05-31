package disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// disjoint - set
// ���� ����
public class p10774 {
	static boolean[] selected;
	static int[] jersey;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int j = Integer.parseInt(br.readLine());
		int a = Integer.parseInt(br.readLine());
		
		selected = new boolean[j + 1];
		jersey = new int[j + 1];
		
		// ���� ������ �Է�
		for(int i = 1; i <= j; i++) {
			jersey[i] = getSizeNum(br.readLine());
		}
		
		// �л��� �Է�
		StringTokenizer st;
		int count = 0;
		for(int i = 1; i <= a; i++) {
			st = new StringTokenizer(br.readLine());
			String size = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			
			// ���� ������ �������� ���� ���
			if(!selected[num]) {
				if(jersey[num] >= getSizeNum(size)) {
					selected[num] = true;
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
	
	static int getSizeNum(String size) {
		switch(size) {
		case "S":
			return 1;
		case "M":
			return 2;
		case "L":
			return 3;
		default:
			return 0;
		}
	}
}
