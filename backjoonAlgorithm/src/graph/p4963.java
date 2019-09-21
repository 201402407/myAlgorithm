package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 섬의 개수
// DFS 방식 사용
// StringBuilder 꼭 사용 -> 시간 단축
// int 배열보단 boolean 배열 사용 -> 시간 단축
public class p4963 {
//	static List<Integer> result = new ArrayList<Integer>();
	// 팔방으로 이동하기 위한 두 배열
	static int[] changeX = {0, 0, 1, -1, 1, 1, -1, -1};	
	static int[] changeY = {1, -1, 0, 0, 1, -1, -1, 1};
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		try {
			while(true) {
				st = new StringTokenizer(br.readLine());
				int w = Integer.valueOf(st.nextToken());
				int h = Integer.valueOf(st.nextToken());
				if(w == 0 && h == 0)
					break;
				
				int[][] map = new int[h + 2][w + 2];	// 진짜 맵의 테두리를 0으로 채워서 NullPointerException 방지하기. 어차피 0이면 넘기면되니
				// 맵 생성
				for(int i = 1; i <= h; i++) {
					st = new StringTokenizer(br.readLine());
					for(int j = 1; j <= w; j++) {
						map[i][j] = Integer.valueOf(st.nextToken());
					}
				}
				// 섬 찾기
				int count = 0;
				for(int i = 1; i <= h; i++) {
					for(int j = 1; j <= w; j++) {
						if(map[i][j] == 0) {
							continue;
						}
						else {
							dfs(map, i, j);
							count++;
						}
					}
				}
				sb.append(count).append("\n");
//				result.add(count);
			}
			System.out.print(sb);
//			result.stream().forEach(element -> System.out.println(element));
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	// DFS 방식 사용해봅시다
	private static void dfs(int[][] map, int y, int x) {
		map[y][x] = 0;
		for(int i = 0; i < changeX.length; i++) {	// 팔방으로 전부 뻗어나감
			int nextY = y + changeY[i];
			int nextX = x + changeX[i];
			if(map[nextY][nextX] == 0)	// 함수 호출 방지
				continue;
			dfs(map, nextY, nextX);
		}
	}
}
