package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 10711번 : 모래성
public class p10711 {
	static int[][] map;
	static boolean[][] boolMap;
	static int[] moveX = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] moveY = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.valueOf(st.nextToken());
		int w = Integer.valueOf(st.nextToken());
		
		map = new int[h + 1][w + 1];	// 인덱스 : 1 ~ h , 1 ~ w
		boolMap = new boolean[h + 1][w + 1];
		Queue<EmptySand> mainQueue = new LinkedList<EmptySand>();
		
		// INPUT 값 입력받음
		for(int y = 1; y <= h; y++) {
			String line = br.readLine();
			for(int x = 1; x <= w; x++) {
				char num = line.charAt(x - 1);
				if(num == '.') {	// . == -1
					map[y][x] = -1;
					boolMap[y][x] = true;
					mainQueue.offer(new EmptySand(x, y));
				}
				else {
					map[y][x] = Character.getNumericValue(num);
				}
			}
		}
		
		int time = comeToWave(h, w, mainQueue);
		System.out.println(time);

	}
	
	// 파도가 친다
	static int comeToWave(int h, int w, Queue<EmptySand> mainQueue) {
		int time = -1;
			
		// 빈 모래들 하나하나 파도 작업
		int len = mainQueue.size();
		int nowCount = 0;
		while(!mainQueue.isEmpty()) {
			EmptySand emptySand = mainQueue.poll();
			nowCount++;
			
			// 빈 모래 주위 모래성 깎기
			ArrayList<EmptySand> list = setEmptySand(emptySand.x, emptySand.y, h, w);
			if(!list.isEmpty()) {
				for(EmptySand ele : list) {
					mainQueue.add(ele);
				}
			}
			
			if(nowCount == len) {
				time++;
				nowCount = 0;
				len = mainQueue.size();
			}
		}
		
		return time;
	}
	
	// 빈 모래 주위 모래 깎고, 빈 모래가 생기면 그걸 반환하는 함수
	static ArrayList<EmptySand> setEmptySand(int x, int y, int h, int w) {
		ArrayList<EmptySand> list = new ArrayList<EmptySand>();
		for(int i = 0; i < moveY.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if((nextX < 1 || nextX > w) || (nextY < 1 || nextY > h)) {
				continue;
			}
			
			if(map[nextY][nextX] > 0) {
				map[nextY][nextX]--;
			}
			
			if(map[nextY][nextX] == 0 && !boolMap[nextY][nextX]) {
				map[nextY][nextX] = -1;
				boolMap[nextY][nextX] = true;
				list.add(new EmptySand(nextX, nextY));
			}
		}
		
		return list;
	}
}

class EmptySand {
	int x;
	int y;
	
	EmptySand(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
