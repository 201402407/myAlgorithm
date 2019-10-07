package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// Max Heap과 우선순위 큐로 구현한 클래스
public class MaxPriorityQueue {
	
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data04.txt";	// 상대 경로 설정
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str;
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			int i = 1;
			Heap heap = new Heap();	// 힙 객체 생성
			while((str = br.readLine()) != null) {
				st = new StringTokenizer(str, ", ");
				int key = Integer.valueOf(st.nextToken());
				String subjectName = st.nextToken();
				heap.push(new Subject(key, subjectName));
				sb.append(key).append(" , ").append(subjectName).append(" , ").append(i++).append("\n");
			}
			System.out.println(sb.toString());
			buildMaxHeap(heap);
			System.out.println("---------------------------------------------------");
			for(Subject s : heap.getList()) {
				if(s == null)
					continue;
				System.out.println(s.getKey() + ", " + s.getName());
			}
//			heap.getList().stream().forEach(s -> System.out.println(s.getKey() + " , " + s.getName()));
			
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	public static void buildMaxHeap(Heap heap) {
		int heapSize = heap.size();
		for(int i = heapSize / 2; i >= 1; i--) {
			maxHeapify(heap, i);
		}
	}
	
	public static void maxHeapify(Heap heap, int i) {
		int parent = i;
		int left = heap.leftChild(i);
		int right = heap.rightChild(i);
		if(left <= heap.size() && heap.get(left).getKey() > heap.get(parent).getKey()) {
			parent = left;
		}
		if(right <= heap.size() && heap.get(right).getKey() > heap.get(i).getKey()) {
			parent = right;
		}
		if(parent != i) {
			Collections.swap(heap.getList(), i, parent);
			maxHeapify(heap, parent);
		}
	}
	
	// 원소 s를 heap에 새로 넣는다
	public static void insert(Heap heap, Subject s) {
		heap.push(s);
		int length = heap.size();
		for(int parent = length / 2; parent <= 1; parent = parent / 2) {
			if(heap.get(length).getKey() < heap.get(parent).getKey()) {
				break;
			}
			else {
				Collections.swap(heap.getList(), length, parent);
			}
		}
	}
	
	// 키 값이 최대인 원소 리턴
	public static Subject max(Heap heap) {
		if(heap.isEmpty())
			return null;
		return heap.get(1);
	}
	
	// 키 값이 최대인 원소 제거
	public static Subject extract_max(Heap heap) {
		int length = heap.size();
		Collections.swap(heap.getList(), length, 1);
		Subject result = heap.remove(length);
		maxHeapify(heap, 1);
		return result;
	}
	
	// 원소 x의 키 값을 k로 증가시킨다. 이 때 k는 x의 현재 키 값보다 작아지지 않는다고 가정한다.
	public static boolean increase_key(Heap heap, Subject s, int key) {
		if(s.getKey() > key) {
			return false;
		}
		int prevKey = s.getKey();
		String name = s.getName();
		int length = heap.size();
		for(int i = 1; i < length; i++) {
			if((heap.get(i).getKey() == prevKey) && (heap.get(i).getName().equals(name))) {
				s.setKey(key);
				heap.getList().set(i, s);	// 덮어쓰기
				maxHeapify(heap, i);
				return true;
			}
		}
		return false;
	}
	
	// heap에서 원소 x 제거. 제거 후 Max heap 유지
	public static boolean delete(Heap heap, Subject s) {
		int key = s.getKey();
		String name = s.getName();
		int length = heap.size();
		for(int i = 1; i < length; i++) {
			if((heap.get(i).getKey() == key) && (heap.get(i).getName().equals(name))) {
				Collections.swap(heap.getList(), length, i);
				heap.remove(length);
				maxHeapify(heap, i);
				return true;
			}
		}
		return false;
	}
}

//최대 힙 구조체 이너 클래스 구현
// 인덱스는 1부터 시작
class Heap {
	List<Subject> ArrayHeap;
	int index;
	// 객체 생성
	public Heap() {
		this.ArrayHeap = new ArrayList<Subject>();
		this.ArrayHeap.add(null);	// 인덱스 1부터 시작하기 때문에 0을 채운다.
		this.index = 0;
	}

	public List<Subject> getList() {
		return this.ArrayHeap;
	}
	
	public void push(Subject subject) {
		this.ArrayHeap.add(subject);
		this.index++;
	}
	
	public Subject pop() {
		if(this.index == 0)	// 비었으면
			return null;
		return this.ArrayHeap.remove(this.index--);
	}
	
	public boolean isEmpty() {
		return this.index == 0 ? false : true;
	}
	
	public int size() {	// 한 개면 1, 두 개면 2 리턴
		return this.index;
	}
	
	public Subject get(int i) {
		if(this.index < i)
			return null;
		return this.ArrayHeap.get(i);
	}
	
	public void add(int i, Subject element) {
		if(this.index >= i) {
			this.ArrayHeap.add(i, element);
			this.index++;	
		}
	}
	
	public Subject remove(int i) {
		if(this.index < i)
			return null;
		this.index--;
		return this.ArrayHeap.remove(i);
	}
	
	public int parent(int i) {
		return Math.abs(i / 2);
	}
	
	public int leftChild(int i) {
		return 2 * i;
	}
	
	public int rightChild(int i) {
		return (2 * i) + 1;
	}
	
	public Subject parentNode(int i) {
		int parent = Math.abs(i / 2);
		if(this.index == 0 || i == 0)
			return null;
		if(this.index < parent)	// 아무것도 없는 경우 size는 1이므로
			return null;
		return this.ArrayHeap.get(parent);
	}
	
	public Subject leftChildNode(int i) {
		int left = 2 * i;
		if(this.index == 0 || i == 0)
			return null;
		if(this.index < left)	// 아무것도 없는 경우 size는 1이므로
			return null;
		return this.ArrayHeap.get(left);
	}
	
	public Subject rightChildNode(int i) {
		int right = (2 * i) + 1;
		if(this.index == 0 || i == 0)
			return null;
		if(this.index < right)	// 아무것도 없는 경우 size는 1이므로
			return null;
		return this.ArrayHeap.get(right);
	}
}
	
// 입력을 위해 과목 클래스 구현
class Subject {
	private int key;
	private String name;
	
	Subject(int key, String name) {
		this.key = key;
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}