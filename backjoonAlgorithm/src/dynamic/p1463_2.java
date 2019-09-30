package dynamic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BFS로 풀어보기
public class p1463_2 {
	static int[] count;
	static boolean[] visited;
	static Queue<Distance> queue = new LinkedList<Distance>();
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n < 1 || n > 1000000) {
			System.out.println("n의 값이 잘못되었습니다.");
			System.exit(0);
		}
		count = new int[n + 1];
		visited = new boolean[n + 1];
		int result = search(n);
		System.out.println(result);
		
	}
	
	private static int search(int n) {
		if(n == 1)
			return 0;
		// 시작
		getQueueAdd(n, 0);
		
		while(!queue.isEmpty()) {
			Distance current = queue.poll();
			int currentNum = current.num;
			int count = current.count;
			if(currentNum == 1) {
				return count;
			}
			if(currentNum % 3 == 0 && !visited[currentNum / 3]) {
				getQueueAdd(currentNum / 3, count + 1);
			}
			if(currentNum % 2 == 0 && !visited[currentNum / 2]) {
				getQueueAdd(currentNum / 2, count + 1);
			}
			if(!visited[currentNum - 1]) {
				getQueueAdd(currentNum - 1, count + 1);
			}
		}
		return 0;
	}
	
	private static void getQueueAdd(int num, int count) {
		queue.add(new Distance(num, count));
		visited[num] = true;
	}
	
	static class Distance {
		int num;
		int count;
		
		Distance(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}
}
