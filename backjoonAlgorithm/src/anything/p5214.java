package anything;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

// ȯ��
// BFS? HashSet?
// �޸� 256MB�� �޸𸮸� ���̱� ���� �� / Ʃ�� ������ �׷����� ������ 
public class p5214 {
	static ArrayList<Integer>[] tubeStation;
	static int[] dijkstra;
	static int n, k, m;
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		n = in.readInt();
		k = in.readInt();
		m = in.readInt();
		tubeStation = new ArrayList[n + m + 1];	// 1 ~ n �� + n+1 ~ m Ʃ�� 
		dijkstra = new int[n + m + 1];
		for(int i = 1; i < tubeStation.length; i++) {
			tubeStation[i] = new ArrayList<Integer>();
		}
		
		// �Է¹ޱ� 
		for(int i = 1; i <= m; i++) {	// ������Ʃ�� �ε��� 
			for(int j = 0; j < k; j++) {
				int station = in.readInt();
				tubeStation[station].add(n + i);	// �ش� ���� ������Ʃ�� ��ȣ �ֱ� 
				tubeStation[n + i].add(station);	// �ش� ������Ʃ�꿡 �� �ֱ� 
			}
		}
		
		int result = -1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		dijkstra[1] = 1;	// �� ���� �� ����(������) 
		while(!q.isEmpty()) {
			int station = q.poll();
			if(station == n) {
				result = dijkstra[n];
				break;
			}
			
			for(int nextStation : tubeStation[station]) {
				
				// ���� �� ���� �湮���� �ʾҰų�, �ּ� ������ ��� 
				if(dijkstra[nextStation] == 0) {
					if(station > n) {
						dijkstra[nextStation] = dijkstra[station];
					}
					else {
						dijkstra[nextStation] = dijkstra[station] + 1;
					}
					q.offer(nextStation);
				}
			}
		}
		
		System.out.println(result);
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