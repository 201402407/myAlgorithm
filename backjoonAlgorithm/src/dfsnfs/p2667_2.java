package dfsnfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// TODO : map�� ��������, ���������� ���� �ð� ���غ���
// ����ϴ�. ���, ���������� ������ �ڵ� ���̰� �ش�.
public class p2667_2 {
    static int[][] map;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int mapSize = sc.nextInt();
		if(mapSize < 5 || mapSize > 25) {	// ���� ó��
			System.out.println("������ ����ϴ�.");
			System.exit(0);
		}
		map = new int[mapSize][mapSize];
		ArrayList<Integer> apartmentCount = new ArrayList<>();
		sc.nextLine();
		
		// �� �Է¹ޱ�
		for(int i = 0; i < mapSize; i++) {
			String strLine = sc.nextLine();
			for(int j = 0; j < strLine.length(); j++) {
				try {
					map[i][j] = Character.getNumericValue(strLine.charAt(j));	// �ƽ�Ű�ڵ尡 �ƴ� char ���ڸ� int�� ����
				}
				catch (NumberFormatException e) {
					System.out.println("���ڸ� �Է����� �ʾҽ��ϴ�. �����մϴ�.");
					return;
				}
				
			}
		}
		
		// ������ȣ ���̱� ���� 1 Ž��
		for(int i = 0; i < mapSize; i++) {	// y ��ǥ
			for(int j = 0; j < mapSize; j++) {	// x ��ǥ
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
		for(int n = 0; n < allApartmentCount; n++)
			System.out.println(apartmentCount.get(n));
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