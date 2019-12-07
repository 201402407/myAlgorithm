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
			if(n < 5 || n > 25) {	// 예외 처리
				System.out.println("n의 범위를 벗어납니다.");
				System.exit(0);
			}
			
			map = new int[n][n];
			ArrayList<Integer> apartmentCount = new ArrayList<>();
			
			// 맵 입력받기
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int lineLen = st.countTokens();
				for(int j = 0; j < lineLen; j++) {
//					char house = line.charAt(j);
					int val = Integer.valueOf(st.nextToken());
//					if(house == 48 || house == 49) {	// 아스키코드로 0 또는 1
//						// 아스키코드가 아닌 char 숫자를 int로 변경
//						map[i][j] = Character.getNumericValue(line.charAt(j));
					if(val == 0 || val == 1) {
						map[i][j] = val;
					}
					else {
						System.out.println("0 또는 1을 입력하지 않았습니다. 종료합니다.");
						System.exit(0);
					}
				}
			}
			
			// 단지번호 붙이기 위해 1 탐색
			for(int i = 0; i < n; i++) {	// y 좌표
				for(int j = 0; j < n; j++) {	// x 좌표
					if(map[i][j] == 1) {
						apartmentCount.add(mapSearchDFS(i, j));
					}
				}
			}
			
			// 오름차순 정렬
			Collections.sort(apartmentCount);
			// 출력
			int allApartmentCount = apartmentCount.size();
			System.out.println(allApartmentCount);
			for(int ele : apartmentCount) {
				System.out.println(ele);
			}
			
		}
		catch (NumberFormatException e) {
			System.out.println("입력받은 값이 숫자가 아닙니다. 오류 발생! 종료합니다.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOException 오류 발생! SW를 종료합니다.");
			System.exit(0);
		}
	}
	// Depth First Search(깊이 우선 탐색)
	// x : x위치 좌표 값.  y : y위치 좌표 값.
	public static int mapSearchDFS(int y, int x) {
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
		return 1 + mapSearchDFS(y + 1, x) + mapSearchDFS(y - 1, x) + mapSearchDFS(y, x + 1) + mapSearchDFS(y, x - 1);
	}
}
