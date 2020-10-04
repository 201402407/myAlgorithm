package dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// ���� �� �����ϴ� �κм��� 
public class p11722 {
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = in.readInt();
		}
		
		System.out.println(binarySearch(arr, n));
	}
	
	// �̺�Ž�� �˰����� (���������̶� ���� �ݴ�� ��ȸ)
	public static int binarySearch(int[] arr, int size) {
		int[] dp = new int[size + 1];	// ���� �� �����ϴ� �κ� ����. ���� �ڿ� �ִ� ���� �κ� ���� �� ���� �ִ� 
		int dpLen = 0;
		dp[dpLen++] = arr[size - 1];
		
		for(int i = size - 2; i >= 0; i--) {
			if(dp[dpLen - 1] < arr[i]) {	// ���� �� �����ϴ� �κм����� �ִ񰪺��� ū ��� �ڿ� ���� 
				dp[dpLen++] = arr[i];
			}
			else {
				// DP�迭 �̺�Ž��
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
				
				// start < end �� ��� start + 1, start <= end �� ��� start 
				dp[start] = arr[i];
			}
		}
		
		return dpLen;
	}
	
//	private static int binarySearch(int[] arr, int size) {
//		int[] window = new int[size];	// �������� �̾�� ��! �����ϴ� �κ� ���� �̹Ƿ� 
//		int windowSize = 0;
//		window[windowSize++] = arr[0];
//		
//		for(int i = 1; i < size; i++) {
//			if(arr[i] < window[windowSize - 1]) {
//				window[windowSize++] = arr[i];
//			}
//			else {
//				int start = 0;
//				int end = windowSize;
//				
//				while(start <= end) {
//					int mid = (start + end) / 2;
//					
//					if(window[mid] < arr[i]) {	
//						end = mid - 1;
//					}
//					else {
//						start = mid + 1;
//					}
//				}
//				
//				window[start] = arr[i];
//			}
//		}
//		
//		return windowSize;
//	}
	
	// INPUT �ӵ� ����
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