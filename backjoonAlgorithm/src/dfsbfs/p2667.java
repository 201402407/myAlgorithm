package dfsbfs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// TODO : map을 전역변수, 지역변수로 놓고 시간 비교해보자
public class p2667 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int mapSize = sc.nextInt();
		if(mapSize < 5 || mapSize > 25) {	// 예외 처리
			System.out.println("범위를 벗어납니다.");
			System.exit(0);
		}
		int[][] map = new int[mapSize][mapSize];
		ArrayList<Integer> apartmentCount = new ArrayList<>();
		sc.nextLine();
		
		// 맵 입력받기
		for(int i = 0; i < mapSize; i++) {
			String strLine = sc.nextLine();
			for(int j = 0; j < strLine.length(); j++) {
				char house = strLine.charAt(j);
				if(house == 48 || house == 49) {	// 0 또는 1
					map[i][j] = Character.getNumericValue(strLine.charAt(j));	// 아스키코드가 아닌 char 숫자를 int로 변경
				}
				else {
					System.out.println("0 또는 1을 입력하지 않았습니다. 종료합니다.");
					System.exit(0);
				}
			}
		}
		
		// 단지번호 붙이기 위해 1 탐색
		for(int i = 0; i < mapSize; i++) {	// y 좌표
			for(int j = 0; j < mapSize; j++) {	// x 좌표
				if(map[i][j] == 1) {
					apartmentCount.add(mapSearchDFS(map, i, j));
				}
			}
		}
		
		// 오름차순 정렬
		Collections.sort(apartmentCount);
		// 출력
		int allApartmentCount = apartmentCount.size();
		System.out.println(allApartmentCount);
		for(int n = 0; n < allApartmentCount; n++)
			System.out.println(apartmentCount.get(n));
	}
	
	// Depth First Search(깊이 우선 탐색)
	// x : x위치 좌표 값.  y : y위치 좌표 값.
	public static int mapSearchDFS(int[][] map, int y, int x) {
		// y, x 좌표가 map의 크기를 벗어나는 경우에 대한 예외처리
		// 애초에 map 자체를 정사각형으로 만들었기 때문에 행의 길이로만 체크해도 이상 없다.
		if(y < 0 || y >= map.length) {
			return 0;
		}
		if(x < 0 || x >= map.length) {
			return 0;
		}
		if(map[y][x] == 0) { // 이미 탐색 완료된 집이거나 애초에 0인 집이면 종료
			return 0;
		}
		
		map[y][x] = 0;
		return 1 + mapSearchDFS(map, y + 1, x) + mapSearchDFS(map, y - 1, x) + mapSearchDFS(map, y, x + 1) + mapSearchDFS(map, y, x - 1);
	}
}
