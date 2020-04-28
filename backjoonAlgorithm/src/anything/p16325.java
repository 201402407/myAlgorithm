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

// �Ｚ SW A�� ����
// ���� ����ũ. �ð� ���� ����
// Deque ���
// �ٽ� : ���� �˰��� ��� X.
public class p16325 {
	static int[][] map;	// ���� ��� ��
	static Deque<Tree> trees; // �������� ���� Deque
	 
	static int[][] yangboon;	// �ܿ︶�� ���� �޴� ��� ��
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
		
		// ��� �Է¹ޱ�
		for(int y = 1; y <= n; y++) {
			Arrays.fill(map[y], 5);
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= n; x++) {
				int num = Integer.valueOf(st.nextToken());
				yangboon[y][x] = num;
			}
		}
		
		// ���� �ɱ�
		ArrayList<Tree> temp = new ArrayList<Tree>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.valueOf(st.nextToken());
			int x = Integer.valueOf(st.nextToken());
			int age = Integer.valueOf(st.nextToken());
			
			temp.add(new Tree(x, y, age));
		}
		
		// ���� ����
		Collections.sort(temp);
		// ������ ���� Deque�� �ֱ�
		for(Tree t : temp) {
			trees.offer(t);
		}
		
		// �ų� ��� ���Ѻ���
		int year = 0;
		while(year < k) {
			// ��, ����
			springAndSummer();
			// ����
			fall(n);
			year++;
			// ������ �ܿ￡�� ��и� �־����� ������ ������ ������ �����Ƿ� �����̶� �ð� ������ ���� ���ǹ�
			if(year == k) {	
				break;
			}
			// �ܿ�
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
			if(map[y][x] < age) {	// ����� �����ϸ�
				deadTrees.add(tree);
			}
			else {	// ����� ����ϸ�
				map[y][x] -= age;
				tempQueue.offer(new Tree(x, y, age + 1));
			}
		}
		
		// ��Ƴ��� ���� ����ֱ�
		trees = tempQueue;
		
		// ���� ���� ����ֱ� - ����
		for(Tree t : deadTrees) {
			map[t.y][t.x] += Math.floor(t.age / 2);
		}
	}
	
	static void fall(int n) {
		Deque<Tree> tempTree = new ArrayDeque<Tree>();
		
		while(!trees.isEmpty()) {
			Tree tree = trees.pollFirst();	// ���� ���� �͵���� ������ ���ĵ� ä�� ���� �� ����
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			
			// ������ ���� �ɱ�
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
	
	// ��������
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