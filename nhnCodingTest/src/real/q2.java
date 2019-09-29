package real;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class q2 {
	static List<Integer> queue = new LinkedList<Integer>();
	static List<Integer> sameList = new ArrayList<Integer>();
	static int[] bindo;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		bindo = new int[101];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int value = 0;
			if(command.equals("enqueue")) {
				value = Integer.valueOf(st.nextToken());
			}
			switch(command) {
			case "enqueue":
				enqueue(value);
				break;
			case "dequeue":
				dequeue();
				break;
			}
		}
		System.out.print(sb.toString());
	}
	
	private static void enqueue(int value) {
		queue.add(value);
		bindo[value]++;
	}
	
	private static void dequeue() {
		if(queue.isEmpty()) {
			sb.append("-1").append(" ");
			return;
		}
		int max = 0;
		sameList.clear();
		for(int i = 0; i < 101; i++) {
			if(bindo[i] == 0) {
				continue;
			}
			if(max == bindo[i]) {
				sameList.add(bindo[i]);
				continue;
			}
			if(max < bindo[i]) {
				max = bindo[i];
				sameList.clear();
				sameList.add(i);
			}
		}
		
		int sameListSize = sameList.size();
		
		for(int i = 0; i < queue.size(); i++) {
			int element = queue.get(i);
			for(int j = 0; j < sameListSize; j++) {
				if(sameList.get(j) == element) {
					queue.remove(i);
					sb.append(element).append(" ");
					bindo[sameList.get(j)]--;
					return;
				}
			}
		}
	}
}
