package graph;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;

// 등산
// 그래프 문제(다익스트라) 
public class p16681 {
	static int n, m, d, e;
	static long[] heightArr;
	static List<Vertex16681>[] graph;
	
	public static void main(String args[]) {
		InputReader in = new InputReader(System.in);
		n = in.readInt();
		m = in.readInt();
		d = in.readInt();
		e = in.readInt();
		
		heightArr = new long[n + 1];	// 인덱스 : 1 ~ n
		graph = new ArrayList[n + 1];
		
		// 높이 저장 + 그래프 객체 생성  
		for(int i = 1; i <= n; i++) {
			heightArr[i] = in.readInt();
			graph[i] = new ArrayList<Vertex16681>();
		}
		
		for(int i = 0; i < m; i++) {
			int start = in.readInt();
			int end = in.readInt();
			int distance = in.readInt();
			
			graph[start].add(new Vertex16681(end, distance));
			graph[end].add(new Vertex16681(start, distance));
		}
		
		long[] upDijkstra = bfs(true);
		long[] downDijkstra = bfs(false);
		long maxValue = Long.MIN_VALUE;
		for(int i = 1; i <= n; i++) {
			if(upDijkstra[i] == Long.MAX_VALUE || downDijkstra[i] == Long.MAX_VALUE) {
				continue;
			}
			
			maxValue = Math.max(maxValue, (heightArr[i] * e) - ((upDijkstra[i] + downDijkstra[i]) * d));
		}
		
		if(maxValue == Long.MIN_VALUE) {
			System.out.println("Impossible");
		}
		else {
			System.out.println(maxValue);
		}
	}
	
	static long[] bfs(boolean isUp) {
		long[] dijkstra = new long[n + 1];
		Arrays.fill(dijkstra, Long.MAX_VALUE);
		PriorityQueue<Vertex16681> pq = new PriorityQueue<Vertex16681>();
		if(isUp) {	// 등산하는 경우 
			pq.offer(new Vertex16681(1, 0));
			dijkstra[1] = 0;
		}
		else {	// 하산하는 경우 
			pq.offer(new Vertex16681(n, 0));
			dijkstra[n] = 0;
		}
		
		while(!pq.isEmpty()) {
			Vertex16681 v = pq.poll();
			if(dijkstra[v.endVertex] < v.nowDistance) {	// 지금까지 이동한 거리보다 다익스트라로 구한 거리가 더 작을 경우 굳이 계산안해도 되니까 패스 
				continue;
			}
			
			for(Vertex16681 nextV : graph[v.endVertex]) {
				if(heightArr[v.endVertex] >= heightArr[nextV.endVertex]) {
					continue;
				}
				
				if(v.nowDistance + nextV.nowDistance >= dijkstra[nextV.endVertex]) {
					continue;
				}
				
				dijkstra[nextV.endVertex] = v.nowDistance + nextV.nowDistance;
				pq.offer(new Vertex16681(nextV.endVertex, dijkstra[nextV.endVertex]));
			}
		}
		
		return dijkstra;
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

class Vertex16681 implements Comparable<Vertex16681> {
	int endVertex;
	long nowDistance;

	Vertex16681(int endVertex, long nowDistance) {
		this.endVertex = endVertex;
		this.nowDistance = nowDistance;
	}
	
	@Override
	public int compareTo(Vertex16681 o) {
		if(this.nowDistance < o.nowDistance) {
			return -1;
		}
		else if(this.nowDistance == o.nowDistance) {
			return 0;
		}
		else {
			return 1;
		}
	}
}

