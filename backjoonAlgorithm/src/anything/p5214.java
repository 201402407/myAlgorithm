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

// ȯ��
// BFS? HashSet?
// �޸� 256MB�� �޸𸮸� ���̱� ���� �� / Ʃ�� ������ �׷����� ������ 
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
		tubeStation = new ArrayList[n + m + 1];	// 1 ~ n �� + n+1 ~ m Ʃ�� 
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
		
		// �Է¹ޱ� 
		for(int i = 1; i <= m; i++) {	// ������Ʃ�� �ε��� 
			for(int j = 0; j < k; j++) {
				int station = in.readInt();
				tubeStation[station].add(n + i);	// �ش� ���� ������Ʃ�� ��ȣ �ֱ� 
				tubeStation[n + i].add(station);	// �ش� ������Ʃ�꿡 �� �ֱ� 
//				hyperTube[i].add(station);	// ������Ʃ�꿡�� �ش� ���� �ְ� 
//				hyperStation[station].add(i);	// �����ۿ����� �ش� Ʃ�긦 �ְ� 
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
		
//		// 3�� for���� �ɱ�? -> �ȵȴ�.
//		for(int i = 1; i <= m; i++) {	// ������Ʃ�� �ε��� 
//			int[] arr = new int[k];
//			for(int j = 0; j < k; j++) {
////				arr[j] = in.readInt();
//				int station = in.readInt();
//				hyperTube[i].add(station);	// ������Ʃ�꿡�� �ش� ���� �ְ� 
//				hyperStation[station].add(i);	// �����ۿ����� �ش� Ʃ�긦 �ְ� 
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
		// ���ͽ�Ʈ�� �˰������ ?
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
				
				// ��, �� -> Ʃ�� -> �� ���� ���� 
				for(int nextTubeNum : hyperStation[station.stationNum]) {	// ���� ���ԵǾ��ִ� Ʃ�� ���� ��ȸ 
					for(int nextStationNum : hyperTube[nextTubeNum]) {	// ���� ���ԵǾ��ִ� Ʃ���� ��� �� ��ȸ 
						if(nextStationNum == station.stationNum) {
							continue;
						}
						
						if(nextStationNum == n) {
							// 1 1 1
							// 1
							// �Է��� ��� 
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
						
						// �̹� ��ο� ���ԵǾ� �ִ� ���̸� �н� 
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
	// ���ͽ�Ʈ�� �˰������ ?
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
//				// �̹� ��ο� ���ԵǾ� �ִ� ���̸� �н� 
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