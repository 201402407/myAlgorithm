package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 큐
// 자료구조 - 큐 구현
public class p10845 {
	static int n;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		
		CustomQueue<Integer> customQueue = new CustomQueue<>();
		StringBuilder sb = new StringBuilder();	// Thread-safe 하지 않음
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int num = -1;
			if(st.hasMoreTokens()) {
				num = Integer.valueOf(st.nextToken());
			}
			
			switch(command) {
			case "push":
				if(num > -1) {
					customQueue.push(num);
				}
				break;
			case "pop":
				Integer pop = customQueue.pop();
				sb.append(pop == null ? -1 : pop);
				sb.append("\n");
				break;
			case "size":
				sb.append(customQueue.size());
				sb.append("\n");
				break;
			case "empty":
				sb.append(customQueue.isEmpty() ? 1 : 0);
				sb.append("\n");
				break;
			case "front":
				Integer front = customQueue.front();
				sb.append(front == null ? -1 : front);
				sb.append("\n");
				break;
			case "back":
				Integer back = customQueue.back();
				sb.append(back == null ? -1 : back);
				sb.append("\n");
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}

// 일급 컬렉션(컬렉션 클래스를 Wrapping한 클래스)
class CustomQueue<T> {
	final List<T> linkedList;
	
	CustomQueue() {
		linkedList = new LinkedList<T>();
	}
	
	public void push(T ele) {
		linkedList.add(ele);
	}
	
	public T pop() {
		return isEmpty() ? null : linkedList.remove(0);
	}
	
	public int size() {
		return linkedList.size();
	}
	
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	
	public T front() {
		return isEmpty() ? null : linkedList.get(0);
	}
	
	public T back() {
		return isEmpty() ? null : linkedList.get(size() - 1);
	}
}