package disjointSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// Union-Find 알고리즘
// 집합의 표현 
public class p1717 {
	static int[] arr;
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		int m = in.readInt();
		
		arr = new int[n + 1];	// 0 ~ n 까지 존재하므로
		// 초기화 
		for(int i = 1; i <= n; i++) {
			arr[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			int menu = in.readInt();
			int a = in.readInt();
			int b = in.readInt();
			
			if(menu == 0) {	// 합치기(Union)
				if(a == b) {
					continue;
				}
				union(a, b);
			}
			if(menu == 1) {	// 찾기
				if(a == b) {
					sb.append("YES").append("\n");
					continue;
				}
				
				if(isSameParent(a, b)) {
					sb.append("YES");
				}
				else {
					sb.append("NO");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static int find(int x) {
		if(x == arr[x]) {
			return x;
		}
		return arr[x] = find(arr[x]);
	}
	
	public static boolean isSameParent(int x, int y) {
		return arr[x] == arr[y] ? true : false;
	}
	
	// 항상 x의 부모 -> y 방향임.
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
        if(x != y) {
            arr[x] = y;  	
        }
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
