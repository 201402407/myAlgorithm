package dfsbfs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// TODO : map�� ��������, ���������� ���� �ð� ���غ���
public class p2667 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int mapSize = sc.nextInt();
		if(mapSize < 5 || mapSize > 25) {	// ���� ó��
			System.out.println("������ ����ϴ�.");
			System.exit(0);
		}
		int[][] map = new int[mapSize][mapSize];
		ArrayList<Integer> apartmentCount = new ArrayList<>();
		sc.nextLine();
		
		// �� �Է¹ޱ�
		for(int i = 0; i < mapSize; i++) {
			String strLine = sc.nextLine();
			for(int j = 0; j < strLine.length(); j++) {
				char house = strLine.charAt(j);
				if(house == 48 || house == 49) {	// 0 �Ǵ� 1
					map[i][j] = Character.getNumericValue(strLine.charAt(j));	// �ƽ�Ű�ڵ尡 �ƴ� char ���ڸ� int�� ����
				}
				else {
					System.out.println("0 �Ǵ� 1�� �Է����� �ʾҽ��ϴ�. �����մϴ�.");
					System.exit(0);
				}
			}
		}
		
		// ������ȣ ���̱� ���� 1 Ž��
		for(int i = 0; i < mapSize; i++) {	// y ��ǥ
			for(int j = 0; j < mapSize; j++) {	// x ��ǥ
				if(map[i][j] == 1) {
					apartmentCount.add(mapSearchDFS(map, i, j));
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
	public static int mapSearchDFS(int[][] map, int y, int x) {
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
		return 1 + mapSearchDFS(map, y + 1, x) + mapSearchDFS(map, y - 1, x) + mapSearchDFS(map, y, x + 1) + mapSearchDFS(map, y, x - 1);
	}
}