package dfsbfs;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// 점프
// DFS + DP?
// 방문체크?
public class p1890 {
	static int n, count;
	static int[][] map;
	static int[] moveX = { 1, 0 };	// 오른쪽, 아래 
	static int[] moveY = { 0, 1 };
	static long[][] arrivalCount;
	
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		n = in.readInt();
		map = new int[n + 1][n + 1];
		arrivalCount = new long[n + 1][n + 1];
		
		// 맵 그리기 
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				int ele = in.readInt();
				map[i][j] = ele;
			}
		}
		
		dfs(1, 1);
		long result = arrivalCount[1][1];
		System.out.println(String.valueOf(result));
//		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= n; j++) {
//				System.out.print(arrivalCount[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
	}
	
	public static boolean dfs(int x, int y) {
		if(x == n && y == n) {
			arrivalCount[n][n] = 1;
//			count++;
			return true;
		}
		
		// 이동 불가능 
		if(map[y][x] == 0) {
			return false;
		}
		
		boolean result = false;
		// 이동이동 
		for(int i = 0; i < moveX.length; i++) {
			int nextX = moveX[i] == 0 ? x : x + map[y][x];
			int nextY = moveY[i] == 0 ? y : y + map[y][x];
//			int nextX = x + (moveX[i] * map[y][x]);
//			int nextY = y + (moveY[i] * map[y][x]);
			
			// 범위 체크 
			if(nextX < 1 || nextX > n || nextY < 1 || nextY > n) {
				continue;
			}
			
			// 기존에 방문체크 -> 이미 다음 지점에서 목적지까지 이동한 경우가 있으면 
			if(arrivalCount[nextY][nextX] > 0) {
//				count += arrivalCount[nextY][nextX];	// 해당 경우 개수만큼 count 더하기 
				arrivalCount[y][x] += arrivalCount[nextY][nextX];	// 현재 지점에서 도착지까지 이동할 수 있는 경우의 수 추가하기 
				result = true;
				continue;
			}
			
			if(dfs(nextX, nextY)) {
				arrivalCount[y][x] += arrivalCount[nextY][nextX];
				result = true;
			}
			
//			if(possibleArrival[nextY][nextX]) {
//				count++;
//				continue;
//			}
		}
		
		return result;
	}
	
	// INPUT 속도 증가
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
