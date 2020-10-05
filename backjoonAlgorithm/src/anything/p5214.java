package anything;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 환승
// BFS? HashSet?
// 메모리 256MB로 메모리를 줄이기 위해 역 / 튜브 각각의 그래프를 생성함 
public class p5214 {
	static ArrayList<Integer>[] hyperTube;
	static ArrayList<Integer>[] hyperStation;
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
		
		hyperStation = new ArrayList[n + 1];
		hyperTube = new ArrayList[m + 1];
//		for(int i = 1; i <= n; i++) {
//			hyperStation[i] = new ArrayList<Integer>();
//		}
//		for(int i = 1; i <= m; i++) {
//			hyperTube[i] = new ArrayList<Integer>();
//		}
		
		// 입력받기 
		for(int i = 1; i <= m; i++) {	// 하이퍼튜브 인덱스 
			for(int j = 0; j < k; j++) {
				int station = in.readInt();
				tubeStation[station].add(n + i);	// 해당 역에 하이퍼튜브 번호 넣기 
				tubeStation[n + i].add(station);	// 해당 하이퍼튜브에 역 넣기 
//				hyperTube[i].add(station);	// 하이퍼튜브에는 해당 역을 넣고 
//				hyperStation[station].add(i);	// 하이퍼역에는 해당 튜브를 넣고 
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
		
//		// 3중 for문이 될까? -> 안된다.
//		for(int i = 1; i <= m; i++) {	// 하이퍼튜브 인덱스 
//			int[] arr = new int[k];
//			for(int j = 0; j < k; j++) {
////				arr[j] = in.readInt();
//				int station = in.readInt();
//				hyperTube[i].add(station);	// 하이퍼튜브에는 해당 역을 넣고 
//				hyperStation[station].add(i);	// 하이퍼역에는 해당 튜브를 넣고 
//			}
//		}
//		
//		int result = bfs();
//		if(result == Integer.MAX_VALUE) {
//			System.out.println(-1);	
//		}
//		else {
//			System.out.println(result);	
//		}
	}
	
	// BFS
		// 다익스트라 알고리즘까지 ?
		static int bfs() {
			int result = Integer.MAX_VALUE;
			int[] dijkstra = new int[n + 1];
			Arrays.fill(dijkstra, Integer.MAX_VALUE);
			PriorityQueue<Station5214> pq = new PriorityQueue<Station5214>();
			Station5214 start = new Station5214(1, 1);
			start.addPath(1);
			pq.offer(start);
			dijkstra[1] = 1;
			
			while(!pq.isEmpty()) {
				Station5214 station = pq.poll();
//				System.out.println(station.stationNum + " , " + station.count);
				
				if(dijkstra[station.stationNum] < station.count) {
					continue;
				}
				
				// 즉, 역 -> 튜브 -> 역 과정 수행 
				for(int nextTubeNum : hyperStation[station.stationNum]) {	// 역이 포함되어있는 튜브 전부 순회 
					for(int nextStationNum : hyperTube[nextTubeNum]) {	// 역이 포함되어있는 튜브의 모든 역 순회 
						if(nextStationNum == station.stationNum) {
							continue;
						}
						
						if(nextStationNum == n) {
							// 1 1 1
							// 1
							// 입력인 경우 
							if(station.stationNum == nextStationNum) {
								result = Math.min(result, station.count);
							}
							else {
								result = Math.min(result, station.count + 1);	
							}
							
							return result;
						}

						if(dijkstra[nextStationNum] <= station.count + 1) {
							continue;
						}
						
						// 이미 경로에 포함되어 있는 역이면 패스 
//						if(station.containsPath(nextStationNum)) {
//							continue;
//						}

						
						dijkstra[nextStationNum] = station.count + 1; 
//						Station5214 nextStation = new Station5214(nextStationNum, station.count + 1, station.getPath());
//						nextStation.addPath();
						pq.offer(new Station5214(nextStationNum, station.count + 1));
					}	
				}
			}
			
			return result;
		}
		
	
	// BFS
	// 다익스트라 알고리즘까지 ?
//	static int bfs() {
//		int result = Integer.MAX_VALUE;
//		int[] dijkstra = new int[n + 1];
//		Arrays.fill(dijkstra, Integer.MAX_VALUE);
//		PriorityQueue<Station5214> pq = new PriorityQueue<Station5214>();
//		Station5214 start = new Station5214(1, 1);
//		start.addPath(1);
//		pq.offer(start);
//		dijkstra[1] = 1;
//		
//		while(!pq.isEmpty()) {
//			Station5214 station = pq.poll();
////			System.out.println(station.stationNum + " , " + station.count);
//			
//			if(dijkstra[station.stationNum] < station.count) {
//				continue;
//			}
//			
//			for(int nextStationNum : hyperTube[station.stationNum]) {
//				if(nextStationNum == n) {
//					if(station.stationNum == nextStationNum) {
//						result = Math.min(result, station.count);
//					}
//					else {
//						result = Math.min(result, station.count + 1);	
//					}
//					break;
//				}
//				
//				// 이미 경로에 포함되어 있는 역이면 패스 
//				if(station.containsPath(nextStationNum)) {
//					continue;
//				}
//
//				if(dijkstra[nextStationNum] < station.count + 1) {
//					continue;
//				}
//				
//				dijkstra[nextStationNum] = station.count + 1; 
//				Station5214 nextStation = new Station5214(nextStationNum, station.count + 1, station.getPath());
//				nextStation.addPath(nextStationNum);
//				pq.offer(nextStation);
//			}
//		}
//		
//		return result;
//	}
	
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

class Station5214 implements Comparable<Station5214> {
	int stationNum;
	int count;
	HashSet<Integer> path;
	
	Station5214(int stationNum, int count) {
		this.stationNum = stationNum;
		this.count = count;
		path = new HashSet<Integer>();
	}
	
	Station5214(int stationNum, int count, HashSet<Integer> path) {
		this.stationNum = stationNum;
		this.count = count;
		this.path = path;
	}
	
	public HashSet<Integer> getPath() {
		return this.path;
	}
	
	public void addPath(int ele) {
		this.path.add(ele);
	}
	
	public boolean containsPath(int ele) {
		return this.path.contains(ele);
	}

	@Override
	public int compareTo(Station5214 o) {
		if(this.count < o.count) {
			return -1;
		}
		else {
			return 1;
		}
		
	}
}