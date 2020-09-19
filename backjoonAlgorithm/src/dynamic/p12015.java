package dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// 가장 긴 증가하는 부분 수열 2
// LIS + 이분탐색 
public class p12015 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		int[] arr = new int[n];
		
        // 입력 받자마자 계산하기
		for(int i = 0; i < n; i++) {
			arr[i] = in.readInt();
		}
		
		System.out.println(binarySearch(arr, n));
	}
	
	public static int binarySearch(int[] arr, int size) {
		int[] dp = new int[size + 1];	// 가장 긴 증가하는 부분 수열. 가장 뒤에 있는 값은 부분 수열 중 가장 최댓값 
		int dpLen = 0;
		dp[dpLen++] = arr[0];
		
		for(int i = 1; i < size; i++) {
			if(dp[dpLen - 1] < arr[i]) {	// 가장 긴 증가하는 부분순열의 최댓값보다 큰 경우 뒤에 삽입 
				dp[dpLen++] = arr[i];
			}
			else {
				// DP배열 이분탐색
				int start = 0;
				int end = dpLen;
				while(start <= end) {	
					int mid = (start + end) / 2;
					int midDpEle = dp[mid];
					
					if(midDpEle < arr[i]) {
						start = mid + 1;
					}
					else if(midDpEle >= arr[i]) {
						end = mid - 1;	
					}
				}
				
				// start < end 일 경우 start + 1, start <= end 일 경우 start 
				dp[start] = arr[i];
			}
		}
		
		return dpLen;
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
