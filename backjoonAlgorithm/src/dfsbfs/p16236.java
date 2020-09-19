package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ?ïÑÍ∏? ?ÉÅ?ñ¥ Î¨∏Ï†ú
// ?Çº?Ñ± SW Í∏∞Ï∂ú - BFS
public class p16236 {
	static Fish targetFish = null;
	static int n, sharkX, sharkY, sharkSize, sharkEatCount;
	static int[][] map;
	static boolean[][] visited;
	// ?ù¥?èô ?àú?Ñú : ?úÑ - ?ôº - ?ò§ - ?ïÑ?ûò
	static int[] moveX = { 0, -1, 1, 0 };
	static int[] moveY = { -1, 0, 0, 1 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		// Îß? Í∑∏Î¶¨Í∏?
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				int space = Integer.valueOf(st.nextToken());
				map[y][x] = space;
				if(space == 9) {
					sharkX = x;
					sharkY = y;
					sharkSize = 2;
				}
			}
		}
		
		int time = bfs();
		System.out.println(time);
	}
	
	static int bfs() {
		int time = 0;
		PriorityQueue<Fish> q = new PriorityQueue<Fish>();
		
		while(true) {
			q.add(new Fish(sharkX, sharkY));
			
			while(!q.isEmpty()) {
				Fish fish = q.poll();
				visited[fish.y][fish.x] = true;
				
				if(map[fish.y][fish.x] < sharkSize && map[fish.y][fish.x] > 0) {
					if(targetFish == null) {	
						targetFish = fish;
					}
					else {
						// Î¨ºÍ≥†Í∏? ÎπÑÍµê?ï¥?Ñú ?ù¥?†Ñ Î™©Ìëú Î¨ºÍ≥†Í∏∞Î≥¥?ã§ ?çî ?Çò?? Ï°∞Í±¥?ù¥Î©? Î∞îÍæ∏Í∏?
						if(targetFish.compareTo(fish) == -1) {
							targetFish = fish;
						}
					}
					break;
				}
				
				for(int i = 0; i < moveX.length; i++) {
					int nextX = fish.x + moveX[i];
					int nextY = fish.y + moveY[i];
					
					// Îß? Î≤îÏúÑ Ï°∞Í±¥ Ï≤¥ÌÅ¨
					if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
						continue;
					}
					
					// Î∞©Î¨∏ ?ó¨Î∂? Ï≤¥ÌÅ¨
					if(visited[nextY][nextX]) {
						continue;
					}
					
					// Î¨ºÍ≥†Í∏∞Í? ?ïÑ?ãàÎ©?(?òê?äî, ?Å¨Í∏∞Í? Í∞ôÏúºÎ©?) ?ÜµÍ≥?
					if(map[nextY][nextX] == sharkSize || map[nextY][nextX] == 0) {
						visited[nextY][nextX] = true;
						q.add(new Fish(nextX, nextY, fish.distance + 1));
						continue;
					}
					
					// Î¨ºÍ≥†Í∏? ?Å¨Í∏? ÎπÑÍµê
					if(map[nextY][nextX] < sharkSize && map[nextY][nextX] > 0) {
						visited[nextY][nextX] = true;
						q.add(new Fish(nextX, nextY, fish.distance + 1));
					}
				}
			}
			
			// ?û°?ïÑÎ®πÍ∏∞
			if(targetFish == null) {
				return time;
			}
			
			// Ïß??Çú ?ãúÍ∞?, ?ÉÅ?ñ¥ ?úÑÏπ?, ?Å¨Í∏?, ?û°?ïÑÎ®πÏ? Í∞úÏàò Î≥?Í≤?
			map[targetFish.y][targetFish.x] = 9;
			map[sharkY][sharkX] = 0;
			sharkX = targetFish.x;
			sharkY = targetFish.y;
			time += targetFish.distance;
			
			if(++sharkEatCount == sharkSize) {
				sharkSize++;
				sharkEatCount = 0;
			}
			
			// Ï¥àÍ∏∞?ôî
			targetFish = null;
			resetVisited();
			q.clear();
		}
	}
	
	static void resetVisited() {
		for(int y = 0; y < n; y++) {
			Arrays.fill(visited[y], false);
		}
	}
}

class Fish implements Comparable<Fish> {
	int x;
	int y;
	int distance;
	
	public Fish(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int compareTo(Fish myFish) {
		if(this.distance < myFish.distance) {
			return -1;
		}
		else if(this.distance == myFish.distance) {
			if(this.y < myFish.y) {
				return -1;
			}
			else if(this.y == myFish.y) {
				if(this.x < myFish.x) {
					return -1;
				}
				else if(this.x == myFish.x) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}

	public Fish(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}