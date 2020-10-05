package anything;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

// 환승
// BFS? HashSet?
// 메모리 256MB로 메모리를 줄이기 위해 역 / 튜브 각각의 그래프를 생성함 
public class p5214 {
	static ArrayList<Integer>[] tubeStation;
	static int[] dijkstra;
	static int n, k, m;
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		n = in.readInt();
		k = in.readInt();
		m = in.readInt();
		tubeStation = new ArrayList[n + m + 1];	// 1 ~ n 역 + n+1 ~ m 튜브 
		dijkstra = new int[n + m + 1];
		for(int i = 1; i < tubeStation.length; i++) {
			tubeStation[i] = new ArrayList<Integer>();
		}
		
		// 입력받기 
		for(int i = 1; i <= m; i++) {	// 하이퍼튜브 인덱스 
			for(int j = 0; j < k; j++) {
				int station = in.readInt();
				tubeStation[station].add(n + i);	// 해당 역에 하이퍼튜브 번호 넣기 
				tubeStation[n + i].add(station);	// 해당 하이퍼튜브에 역 넣기 
			}
		}
		
		int result = -1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		dijkstra[1] = 1;	// 한 개의 역 지남(시작점) 
		while(!q.isEmpty()) {
			int station = q.poll();
			if(station == n) {
				result = dijkstra[n];
				break;
			}
			
			for(int nextStation : tubeStation[station]) {
				
				// 만약 한 번도 방문하지 않았거나, 최소 지점인 경우 
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