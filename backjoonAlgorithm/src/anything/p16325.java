package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

// 삼성 SW A형 기출
// 나무 재테크. 시간 제한 유의
// Deque 사용
// 핵심 : 정렬 알고리즘 사용 X.
public class p16325 {
	static int[][] map;	// 현재 양분 맵
	static Deque<Tree> trees; // 나무들을 담은 Deque
	 
	static int[][] yangboon;	// 겨울마다 새로 받는 양분 맵
	static int[] moveX = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] moveY = { -1, -1, 0, 1, 1, 1, 0, -1 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		map = new int[n + 1][n + 1];
		yangboon = new int[n + 1][n + 1];
		trees = new ArrayDeque<Tree>();
		
		// 양분 입력받기
		for(int y = 1; y <= n; y++) {
			Arrays.fill(map[y], 5);
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= n; x++) {
				int num = Integer.valueOf(st.nextToken());
				yangboon[y][x] = num;
			}
		}
		
		// 나무 심기
		ArrayList<Tree> temp = new ArrayList<Tree>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.valueOf(st.nextToken());
			int x = Integer.valueOf(st.nextToken());
			int age = Integer.valueOf(st.nextToken());
			
			temp.add(new Tree(x, y, age));
		}
		
		// 나무 정렬
		Collections.sort(temp);
		// 정렬한 나무 Deque에 넣기
		for(Tree t : temp) {
			trees.offer(t);
		}
		
		// 매년 경과 지켜보기
		int year = 0;
		while(year < k) {
			// 봄, 여름
			springAndSummer();
			// 가을
			fall(n);
			year++;
			// 어차피 겨울에는 양분만 넣어주지 나무의 개수는 변동이 없으므로 조금이라도 시간 절약을 위한 조건문
			if(year == k) {	
				break;
			}
			// 겨울
			winter(n);
		}
		
		System.out.println(trees.size());
	}
	
	static void springAndSummer() {
		Deque<Tree> tempQueue = new ArrayDeque<Tree>();
		ArrayList<Tree> deadTrees = new ArrayList<Tree>();
		
		while(!trees.isEmpty()) {
			Tree tree = trees.poll();
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			if(map[y][x] < age) {	// 양분이 부족하면
				deadTrees.add(tree);
			}
			else {	// 양분이 충분하면
				map[y][x] -= age;
				tempQueue.offer(new Tree(x, y, age + 1));
			}
		}
		
		// 살아남은 나무 집어넣기
		trees = tempQueue;
		
		// 죽은 나무 양분주기 - 여름
		for(Tree t : deadTrees) {
			map[t.y][t.x] += Math.floor(t.age / 2);
		}
	}
	
	static void fall(int n) {
		Deque<Tree> tempTree = new ArrayDeque<Tree>();
		
		while(!trees.isEmpty()) {
			Tree tree = trees.pollFirst();	// 가장 작은 것들부터 빼내야 정렬된 채로 가질 수 있음
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			
			// 주위에 나무 심기
			if(age % 5 == 0) {
				for(int i = 0; i < moveX.length; i++) {
					int nextX = x + moveX[i];
					int nextY = y + moveY[i];
					
					if(nextX < 1 || nextX > n || nextY < 1 || nextY > n) {
						continue;
					}
					
					tempTree.offerFirst(new Tree(nextX, nextY, 1));
				}
			}
			
			tempTree.offerLast(tree);
		}
		
		trees = tempTree;
	}
	
	static void winter(int n) {
		for(int y = 1; y <= n; y++) {
			for(int x = 1; x <= n; x++) {
				map[y][x] += yangboon[y][x];
			}
		}
	}
}

class Tree implements Comparable<Tree> {
	int x;
	int y;
	int age;
	
	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
	
	// 내림차순
	@Override
	public int compareTo(Tree o2) {
		if(this.age < o2.age) {
			return -1;
		}
		else if(this.age == o2.age) {
			return 0;
		}
		else {
			return 1;
		}
	}
}