package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

// 16235
public class temp2 {
	static int[][] map;	// 현재 양분 맵
	static ArrayList<Integer>[][] treeMap; // 현재 심어진 나무들 위치 맵
	// 나무가 있는 위치를 담음.
	// ex) (y, x) => "y,x"
	static HashSet<String> treeLocation = new HashSet<String>(); 
	static int[][] yangboon;	// 겨울마다 새로 받는 양분 맵
	static int[] moveX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] moveY = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int treeCount = 0;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		map = new int[n + 1][n + 1];
		yangboon = new int[n + 1][n + 1];
		treeMap = new ArrayList[n + 1][n + 1];
		
		// 양분 입력받기
		for(int y = 1; y <= n; y++) {
			Arrays.fill(map[y], 5);
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= n; x++) {
				int num = Integer.valueOf(st.nextToken());
				yangboon[y][x] = num;
				treeMap[y][x] = new ArrayList<Integer>();
			}
		}
		
		// 나무 심기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.valueOf(st.nextToken());
			int x = Integer.valueOf(st.nextToken());
			int age = Integer.valueOf(st.nextToken());
			treeMap[y][x].add(age);
			treeLocation.add(y + "," + x);
			treeCount++;
		}
		
		// 매년 경과 지켜보기
		int year = 0;
		while(year < k) {
			// 봄 , 여름
			springAndSummer();
			// 가을
			fall(n);
			year++;
			if(year == k) {
				break;
			}
			// 겨울
			winter(n);
		}
		
		System.out.println(treeCount);
	}
	
	static void springAndSummer() {
		HashSet<String> tempLocation = new HashSet<String>();
		// 모든 나무가 존재하는 위치들 진행
		Iterator<String> it = treeLocation.iterator();
		
		while(it.hasNext()) {
			String location = it.next();
			int y = Integer.valueOf(location.split(",")[0]);
			int x = Integer.valueOf(location.split(",")[1]);
			int nowYb = map[y][x];
			int newYb = 0; // 죽은 나무가 만든 양분
			Collections.sort(treeMap[y][x]);

			for(int i = 0; i < treeMap[y][x].size(); i++) {
				int age = treeMap[y][x].get(i);
				if(nowYb < age) {	// 양분이 부족하면
					treeMap[y][x].remove(i);
					newYb += Math.floor(age / 2);
					treeCount--;
					i--;
				}
				else {	 // 나이 1 증가
					nowYb -= age;
					treeMap[y][x].set(i, age + 1);
				}
			}
			
			if(treeMap[y][x].isEmpty()) {
				tempLocation.remove(location);
			}
			
			// 여름 - 양분 새로 넣기
			map[y][x] = nowYb + newYb;
		}
		
		// 없는 나무 위치 제거
		it = tempLocation.iterator();
		while(it.hasNext()) {
			treeLocation.remove(it.next());
		}
	}
	
	static void fall(int n) {
		Iterator<String> it = treeLocation.iterator();
		HashSet<String> tempLocation = new HashSet<String>();
		
		while(it.hasNext()) {
			String location = it.next();
			int y = Integer.valueOf(location.split(",")[0]);
			int x = Integer.valueOf(location.split(",")[1]);
			
			for(int i = 0; i < treeMap[y][x].size(); i++) {
				int age = treeMap[y][x].get(i);
				
				if(age % 5 == 0) {	 // 5의 배수
					for(int j = 0; j < moveX.length; j++) {
						int nextY = y + moveY[j];
						int nextX = x + moveX[j];
						
						if(nextX < 1 || nextX > n || nextY < 1 || nextY > n) {
							continue;
						}
						
						treeMap[nextY][nextX].add(1);
						treeCount++;
//						System.out.println(treeCount);
						tempLocation.add(nextY + "," + nextX);
					}
				}
			}
		}
		
		// 새로운 나무 좌표 넣기
		it = tempLocation.iterator();
		while(it.hasNext()) {
			treeLocation.add(it.next());
		}
	}
	
	static void winter(int n) {
		for(int y = 1; y <= n; y++) {
			for(int x = 1; x <= n; x++) {
				map[y][x] += yangboon[y][x];
			}
		}
	}
}
