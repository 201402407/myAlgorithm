package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 배열에서 이동
// BFS
public class p1981 {
	static final int MAX_VALUE = 1000;
	static int n, max, min, result;
	static int[][] map, minGap;
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		minGap = new int[n][n];
		map = new int[n][n];
		
		for(int y = 0; y < n; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Arrays.fill(minGap[y], MAX_VALUE);
			
			for(int i = 0; i < n; i++) {
				int num = Integer.valueOf(st.nextToken());
				map[y][i] = num;
				max = Math.max(max, num);
				min = Math.min(min, num);
			}
		}
		
		// 0: left(최소 시작), max - min : right(답이 가능한 최대의 값)
		binarySearch(0, max - min);
		System.out.println(result);
	}
	
	static void binarySearch(int left, int right) {
		while(left <= right) {
			int mid = (left + right) / 2;
			
			// n-1, n-1까지 가는 데 나온 결과값이 mid가 될 수 있는지 체크
			if(bfs(mid)) {
				result = mid;
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
	}
		
	// (0,0) 시작이 당연
	// (n-1, n-1)에 도달할 수 있는지 체크
	static boolean bfs(int mid) {
		// min부터 (min + mid ~ max) 사이 값들을 가지고 이동할 수 있는 경우가 있는지 체크
		// 이를 위해 최댓값이 될 수 있는 값들 중 가장 작은 min + mid 부터 시작
		// 찾으면 바로 리턴해서 이분탐색 수행
		for(int i = min; i + mid <= max; i++) {
			int start = i;
			int end = start + mid;
			
			// 시작지점값이 
			if(start > map[0][0] || map[0][0] > end) {
				continue;
			}
			
			Queue<Point> q = new LinkedList<Point>();
			boolean[][] visited = new boolean[n][n];
			q.offer(new Point(0, 0));	// 시작점부터 시작
			visited[0][0] = true;
			
			while(!q.isEmpty()) {
				Point newPoint = q.poll();
				
				// 도착지점이면 true
				if(newPoint.x == n - 1 && newPoint.y == n - 1) {
					return true;
				}
				
				for(int j = 0; j < 4; j++) {
					int nextX = newPoint.x + moveX[j];
					int nextY = newPoint.y + moveY[j];
					
					// start와 end 사이에 다음 위치 값이 포함되는지 체크
					if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
						continue;
					}
					if(!visited[nextY][nextX] && start <= map[nextY][nextX] && map[nextY][nextX] <= end) {
						q.offer(new Point(nextX, nextY));
						visited[nextY][nextX] = true;
					}
				}
			}
		}
		
		return false;
	}
}

class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/**
 * 슬라이딩 윈도우 + BFS
 */
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//class Main {
//    static class Node {
//        int x;
//        int y;
//
//        Node(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    private static int N;
//    private static int[][] arr;
//    private static final int[] X = { 1, 0, -1, 0 };
//    private static final int[] Y = { 0, 1, 0, -1 };
//
//    public static boolean bfs(int min, int max) {
//        if (arr[0][0] < min || arr[0][0] > max) {
//            return false;
//        }
//        Queue<Node> queue = new LinkedList<Node>();
//        boolean[][] visited = new boolean[N][N];
//        queue.offer(new Node(0, 0));
//        visited[0][0] = true;
//        while (!queue.isEmpty()) {
//            Node node = queue.poll();
//            if (node.x == N - 1 && node.y == N - 1) {
//                return true;
//            }
//            for (int i = 0; i < 4; i++) {
//                int nextX = node.x + X[i];
//                int nextY = node.y + Y[i];
//                if (nextX < 0 || nextX >= N) {
//                    continue;
//                }
//                if (nextY < 0 || nextY >= N) {
//                    continue;
//                }
//                if (visited[nextX][nextY]) {
//                    continue;
//                }
//                if (arr[nextX][nextY] >= min && arr[nextX][nextY] <= max) {
//                    queue.offer(new Node(nextX, nextY));
//                    visited[nextX][nextY] = true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        arr = new int[N][N];
//        ArrayList<Integer> numberkind = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//            for (int j = 0; j < N; j++) {
//                arr[i][j] = Integer.parseInt(st.nextToken());
//                if (!numberkind.contains(arr[i][j])) {
//                    numberkind.add(arr[i][j]);
//                }
//            }
//        }
//        Collections.sort(numberkind);
//        int start = 0, end = 0, gap = 0, Size = numberkind.size(), answer = Integer.MAX_VALUE;
//        while (start < Size && end < Size) {
//            if (bfs(numberkind.get(start), numberkind.get(end))) {
//                gap = numberkind.get(end) - numberkind.get(start);
//                if (answer > gap)
//                    answer = gap;
//                start++;
//            } else {
//                end++;
//            }
//        }
//        System.out.println(answer);
//        br.close();
//    }
//}
