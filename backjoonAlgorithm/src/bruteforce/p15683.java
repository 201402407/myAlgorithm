package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 감시
// 브루트 포스
public class p15683 {
	static int[][] map;
	static ArrayList<CCTV> cctvs;
	static int allCount = 0;
	static int x, y;
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	static int monitorCount = Integer.MAX_VALUE;	// 감시 가능한 영역 개수
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		y = Integer.valueOf(st.nextToken());
		x = Integer.valueOf(st.nextToken());
		
		map = new int[y][x];
		cctvs = new ArrayList<CCTV>();
		
		// map 입력 그리기
		for(int i = 0; i < y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < x; j++) {
				int area = Integer.valueOf(st.nextToken());
				if(area == 0) {
					allCount++;
				}
				else if(area != 6) {
					cctvs.add(new CCTV(j, i, area));
				}
				
				map[i][j] = area;
			}
		}
		
		backTracking(0, cctvs.size(), new CCTV[cctvs.size()]);
		
		System.out.println(monitorCount);
	}
	
	// 백트래킹 진행
	public static void backTracking(int index, int size, CCTV[] selectedCctvs) {
		if(index == size) {	// CCTV 전부 선택한 경우
			countSagakArea(selectedCctvs, new boolean[y][x]);
		}
		else {
			CCTV selectCCTV = cctvs.get(index);
			for(int i = 0; i < 4; i++) {
				CCTV c = new CCTV(selectCCTV.x, selectCCTV.y, selectCCTV.type);
				switch(selectCCTV.type) {
				case 1:
					c.addDirection(i);
					selectedCctvs[index] = c;
					backTracking(index + 1, size, selectedCctvs);
					break;
				case 2:
					if(i >= 2) {	// 2가지 경우의 수 밖에 없으므로
						return;	
					}
					c.addDirection(i);
					c.addDirection(i + 2);
					selectedCctvs[index] = c;
					backTracking(index + 1, size, selectedCctvs);
					break;
				case 3:
					c.addDirection(i);
					c.addDirection((i + 1) % 4);
					selectedCctvs[index] = c;
					backTracking(index + 1, size, selectedCctvs);
					break;
				case 4:
					c.addDirection(i);
					c.addDirection((i + 1) % 4);
					c.addDirection((i + 2) % 4);
					selectedCctvs[index] = c;
					backTracking(index + 1, size, selectedCctvs);
					break;
				case 5:
					if(i >= 1) {	// 1가지 경우의 수 밖에 없으므로
						return;
					}
					c.addDirection(i);
					c.addDirection((i + 1) % 4);
					c.addDirection((i + 2) % 4);
					c.addDirection((i + 3) % 4);
					selectedCctvs[index] = c;
					backTracking(index + 1, size, selectedCctvs);
					break;
				}
			}
		}
	}
	
	// 사각지대 개수 검사
	public static void countSagakArea(CCTV[] selectedCctvs, boolean[][] visited) {
		int count = 0;
		for(int i = 0; i < selectedCctvs.length; i++) {
			CCTV c = selectedCctvs[i];
			// 방향별로 쭉 검사
			for(int d = 0; d < c.DirectionSize(); d++) {
				int direction = c.directions.get(d);
				int nextX = c.x + moveX[direction];
				int nextY = c.y + moveY[direction];
				while((nextX >= 0 && nextX < x) && (nextY >= 0 && nextY < y)) {
					if(map[nextY][nextX] == 0) {
						if(!visited[nextY][nextX]) {
							count++;
							visited[nextY][nextX] = true;	
						}
					}
					else if(map[nextY][nextX] == 6) {
						break;
					}
					nextX += moveX[direction];
					nextY += moveY[direction];
				}
			}
		}
		monitorCount = Math.min(monitorCount, allCount - count);
	}
}

class CCTV {
	int x;
	int y;
	int type;
	ArrayList<Integer> directions = new ArrayList<Integer>();
	
	CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public void addDirection(int direction) {
		directions.add(direction);
	}
	
	public int DirectionSize() {
		return directions.size();
	}
}