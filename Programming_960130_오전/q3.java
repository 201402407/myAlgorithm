package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class q3 {
	static int[][] map;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 5 || n > 25) {	// ���� ó��
				System.out.println("n�� ������ ����ϴ�.");
				System.exit(0);
			}
			
			map = new int[n][n];
			ArrayList<Integer> apartmentCount = new ArrayList<>();
			
			// �� �Է¹ޱ�
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int lineLen = st.countTokens();
				for(int j = 0; j < lineLen; j++) {
//					char house = line.charAt(j);
					int val = Integer.valueOf(st.nextToken());
//					if(house == 48 || house == 49) {	// �ƽ�Ű�ڵ�� 0 �Ǵ� 1
//						// �ƽ�Ű�ڵ尡 �ƴ� char ���ڸ� int�� ����
//						map[i][j] = Character.getNumericValue(line.charAt(j));
					if(val == 0 || val == 1) {
						map[i][j] = val;
					}
					else {
						System.out.println("0 �Ǵ� 1�� �Է����� �ʾҽ��ϴ�. �����մϴ�.");
						System.exit(0);
					}
				}
			}
			
			// ������ȣ ���̱� ���� 1 Ž��
			for(int i = 0; i < n; i++) {	// y ��ǥ
				for(int j = 0; j < n; j++) {	// x ��ǥ
					if(map[i][j] == 1) {
						apartmentCount.add(mapSearchDFS(i, j));
					}
				}
			}
			
			// �������� ����
			Collections.sort(apartmentCount);
			// ���
			int allApartmentCount = apartmentCount.size();
			System.out.println(allApartmentCount);
			for(int ele : apartmentCount) {
				System.out.println(ele);
			}
			
		}
		catch (NumberFormatException e) {
			System.out.println("�Է¹��� ���� ���ڰ� �ƴմϴ�. ���� �߻�! �����մϴ�.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOException ���� �߻�! SW�� �����մϴ�.");
			System.exit(0);
		}
	}
	// Depth First Search(���� �켱 Ž��)
	// x : x��ġ ��ǥ ��.  y : y��ġ ��ǥ ��.
	public static int mapSearchDFS(int y, int x) {
		// y, x ��ǥ�� map�� ũ�⸦ ����� ��쿡 ���� ����ó��
		// ���ʿ� map ��ü�� ���簢������ ������� ������ ���� ���̷θ� üũ�ص� �̻� ����.
		if(y < 0 || y >= map.length) {
			return 0;
		}
		if(x < 0 || x >= map.length) {
			return 0;
		}
		if(map[y][x] == 0) { // �̹� Ž�� �Ϸ�� ���̰ų� ���ʿ� 0�� ���̸� ����
			return 0;
		}
		
		map[y][x] = 0;
		return 1 + mapSearchDFS(y + 1, x) + mapSearchDFS(y - 1, x) + mapSearchDFS(y, x + 1) + mapSearchDFS(y, x - 1);
	}
}
