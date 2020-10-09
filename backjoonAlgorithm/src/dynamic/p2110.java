package dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

// 공유기 설치
// 이분탐색 
public class p2110 {
	static int n, c, min, max;
	static int[] location;
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		n = in.readInt();
		c = in.readInt();
		location = new int[n];
		
		for(int i = 0; i < n; i++) {
			location[i] = in.readInt();
		}
	
		Arrays.sort(location);
		max = location[n - 1] - location[0];
		
		System.out.println(binarySearch());
	}
	
	// 간격이 x 이상이냐 이하냐? 질문에 대한 이분탐색 
	static int binarySearch() {
		int start = 1;
		int end = max;
		
		// 간격을 mid로 해서 가능한지 탐색
		while(start <= end) {
			int mid = (start + end) / 2;
			int count = getCountOfPossibleRouter(mid);
//			System.out.println(mid + " 일 때, " + count);
			// 공유기 최소 설치 개수와 C와 비교 
			if(count >= c) {	// c보다 더 많이 설치해야하면 간격을 키워보자 
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
//		System.out.println(start + " ,, " + end);
		return start - 1;
	}

	// 공유기를 특정 간격에서 최소 몇 개 설치할 수 있는지 판별해서 개수 리턴하는 함수  
	static int getCountOfPossibleRouter(int gap) {
		int setUpIndex = location[0];
		int count = 1;
		for(int i = 1; i < n; i++) {
			if(location[i] - setUpIndex >= gap) {
				setUpIndex = location[i];
				count++;
			}
		}
		
		return count;
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
