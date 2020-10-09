package dfsbfs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

// ������
// �ִܰŸ�(���ͽ�Ʈ��?) -> 1�� �̵��ϸ鼭 ������ �� �Ÿ� ��� �����Ѵٰ� ���� 
// �׷��� 
public class p1446 {
	static int n, d;
	static int[] movedDistance;	// �ִ� ���� ������ �Ÿ�  
	static ShortCut[] shortCuts;
	
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		n = in.readInt();
		d = in.readInt();
		movedDistance = new int[d + 1];
		shortCuts = new ShortCut[n];
		
		// �ʱ�ȭ
		Arrays.fill(movedDistance, Integer.MAX_VALUE);
		
		// ������ �Է¹ޱ� 
		for(int i = 0; i < n; i++) {
			int start = in.readInt();
			int end = in.readInt();
			int dist = in.readInt();
			
			shortCuts[i] = new ShortCut(start, end, dist);
		}
		
		// ������ ���� �������� ���� 
		Arrays.sort(shortCuts);
		
		int nowDistance = 0;
		int nowIndex = 0;
		movedDistance[0] = 0;
		
		while(nowDistance < d) {
			while(nowIndex < n) {
				if(shortCuts[nowIndex].start != nowDistance) {
					break;
				}
				
				// ������ �̵� 
				if(shortCuts[nowIndex].end <= d) {
					int goShortCutDistance = movedDistance[nowDistance] + shortCuts[nowIndex].distance;
					if(goShortCutDistance < movedDistance[shortCuts[nowIndex].end]) {
						movedDistance[shortCuts[nowIndex].end] = goShortCutDistance;
					}
				}
			
				nowIndex++;
			}
			
			// �� ĭ �̵� 
			if(movedDistance[nowDistance] + 1 < movedDistance[nowDistance + 1]) {
				movedDistance[nowDistance + 1] = movedDistance[nowDistance] + 1;
			}
			
			nowDistance++;
		}
		
		System.out.println(movedDistance[d]);
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

class ShortCut implements Comparable<ShortCut> {
	int start;
	int end;
	int distance;
	
	ShortCut(int start, int end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	@Override
	public int compareTo(ShortCut o) {
		if(this.start < o.start) {
			return -1;
		}
		return 1;
	}
}
