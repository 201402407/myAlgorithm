package dynamic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

// ������ ��ġ
// �̺�Ž�� 
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
	
	// ������ x �̻��̳� ���ϳ�? ������ ���� �̺�Ž�� 
	static int binarySearch() {
		int start = 1;
		int end = max;
		
		// ������ mid�� �ؼ� �������� Ž��
		while(start <= end) {
			int mid = (start + end) / 2;
			int count = getCountOfPossibleRouter(mid);
//			System.out.println(mid + " �� ��, " + count);
			// ������ �ּ� ��ġ ������ C�� �� 
			if(count >= c) {	// c���� �� ���� ��ġ�ؾ��ϸ� ������ Ű������ 
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
//		System.out.println(start + " ,, " + end);
		return start - 1;
	}

	// �����⸦ Ư�� ���ݿ��� �ּ� �� �� ��ġ�� �� �ִ��� �Ǻ��ؼ� ���� �����ϴ� �Լ�  
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
