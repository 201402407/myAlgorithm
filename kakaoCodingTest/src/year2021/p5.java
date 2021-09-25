package year2021;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class p5 {
	static List<Integer>[] graph;
	static int maxSheep = 0;
	public static void main(String args[]) {
		// 그래프
		int[] info = { 0,0,1,1,1,0,1,0,1,0,1,1 };
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		
		int graphSize = info.length;
		graph = new ArrayList[graphSize];
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		
		for(int i = 0; i < graphSize; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int y = 0; y < edges.length; y++) {
			int start = edges[y][0];
			int end = edges[y][1];
			graph[start].add(end);
		}
		
		Vertex[] dp = new Vertex[graphSize];
		for(int i = 0; i < graphSize; i++) {
			dp[i] = new Vertex(i, 0, 0);
		}
		
		// 루트노드에서 시작
		List<Integer> list = new ArrayList<Integer>();
		List<Boolean> boolList = new ArrayList<Boolean>();
		for(int next : graph[0]) {
//			Queue<Vertex> q = new LinkedList<Vertex>();
			int animal = info[next];
//			Vertex v = new Vertex(next, 1, 0);
//			q.offer();
			list.add(next);
			boolList.add(false);
		}
		for(int i = 0; i < list.size(); i++) {
			try {
				backTracking(list, boolList, info, new Vertex(i, 1, 0), i);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(maxSheep);
	}
	
	static List<Integer> deepCopy(List<Integer> list) throws CloneNotSupportedException {
		List<Integer> resultList = new ArrayList<Integer>();
		for(int v : list) {
			resultList.add(v);
		}
		
		return resultList;
	}
	
	static List<Boolean> deepCopyBool(List<Boolean> list) throws CloneNotSupportedException {
		List<Boolean> resultList = new ArrayList<Boolean>();
		for(boolean b: list) {
			resultList.add(b);
		}
		
		return resultList;
	}
	
	// 백트래킹
	static void backTracking(List<Integer> list, List<Boolean> visitedList, int[] info, Vertex nowV, int index) throws CloneNotSupportedException {
		int nowVertex = list.get(index);
		
		int nextAnimal = info[nowVertex];
		int nextSheep = (nextAnimal == 0 ? 1 : 0) + nowV.sheepCount;
		int nextWolf = (nextAnimal == 1 ? 1 : 0) + nowV.wolfCount;
		if(nextSheep <= nextWolf) {
			return;	// 버리는카드
		}
		
		List<Integer> nextList = deepCopy(list);	// 복사
		List<Boolean> nextVisitedList = deepCopyBool(visitedList);
		nextVisitedList.set(index, true);
		
		for(int nextPoint : graph[nowVertex]) {
			maxSheep = Math.max(maxSheep, nextSheep);
			nextList.add(nextPoint);
			nextVisitedList.add(false);
		}
		
		int nextListSize = nextList.size();
		for(int i = 0; i < nextListSize; i++) {
			if(nextVisitedList.get(i)) {
				continue;
			}
			
			backTracking(nextList, nextVisitedList, info, new Vertex(i, nextSheep, nextWolf), i);
		}
		
		
		
//		while(!q.isEmpty()) {
//			Vertex vertex = q.poll();
//			int start = vertex.num;
////			Vertex dpVertex = dp[start];
//			int nowAnimal = info[start];
////			int nowSheep = vertex.sheepCount;
////			int nowWolf = vertex.wolfCount;
//			
//			// 양 체크
//			if(nowAnimal == 0) {
//				vertex.sheepCount++;
//			}
//			else {
//				vertex.wolfCount++;
//			}
//			
//			if(vertex.sheepCount <= vertex.wolfCount) {	// 우선, 잡아먹히면 큐에 넣지 말자
//				continue;
//			}
//			
//			
//			System.out.print(start + " ");
//			if(dpVertex.compareTo(vertex) < 0) {	// 다음 지점
//				System.out.print("(cut) ");
//				continue;
//			}
//			
//			dp[start] = vertex;	// 현재 값으로 갱신(다익스트라급;;)
//			maxSheep = Math.max(maxSheep, vertex.sheepCount);
//			
//			for(int nextPoint : graph[start]) {
//				int nowSheep = vertex.sheepCount;
//				int nowWolf = vertex.wolfCount;
//				
//				pq.offer(new Vertex(nextPoint, nowSheep, nowWolf));
//			}
//		}
	}
}

class Vertex implements Comparable<Vertex>, Cloneable {
	int num;
	int sheepCount;	// 0: 양, 1: 늑대
	int wolfCount;
	boolean visited = false;
	
	Vertex(int num, int sheepCount, int wolfCount) {
		this.num = num;
		this.sheepCount = sheepCount;
		this.wolfCount = wolfCount;
	}
	
	void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	protected Vertex clone() throws CloneNotSupportedException {
		return (Vertex) super.clone();
	}
	
	@Override
	public int compareTo(Vertex v) {
		// 1) 양 개수 높은순
		// 2) 늑대 개수 적은 순
		if(this.sheepCount > v.sheepCount) {
			return -1;
		}
		else if(this.sheepCount == v.sheepCount) {
			if(this.wolfCount < v.wolfCount) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
 }
