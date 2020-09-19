package dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// 가장 큰 증가 부분 수열
// LIS + DP ?
public class p11055 {
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		int[] arr = new int[n];
//		int[][] dp = new int[n][2];	// 0: 0 ~ i 인덱스까지의 수 중 가장 합이 큰 부분수열의 최댓값.  1: 0 ~ i 인덱스까지의 수 중 가장 합이 큰 부분수열의 전체합 
		int[] dpp = new int[n];
		int max = 0;
		for(int i = 0; i < n; i++) {
			int ele = in.readInt();
			arr[i] = ele;
//			dp[i][0] = arr[i];
//			dp[i][1] = arr[i];
			dpp[i] = arr[i];
			boolean isPossible = false;
			for(int j = i - 1; j >= 0; j--) {
//				if(dp[j][0] < arr[i] && dp) {
//					dp[i][0] = arr[i];
//					dp[i][1] = dp[j][1] + arr[i];
//					isPossible = true;
//					break;
//				}
				
				if(arr[j] < arr[i] && dpp[i] < dpp[j] + arr[i]) {
					dpp[i] = dpp[j] + arr[i];
				}
			}
//			max = Math.max(max, dp[i][1]);
			max = Math.max(max, dpp[i]);
		}
		
		System.out.println(max);
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
