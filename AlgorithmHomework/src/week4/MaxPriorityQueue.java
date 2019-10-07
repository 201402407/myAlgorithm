package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
			}
			buildMaxHeap(heap);	// Max heap 정렬
			System.out.println();
			menu(heap);
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	// 메뉴 출력
	public static void menu(Heap heap) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			print(heap);
			System.out.println("--------------------------------------------------------");
			System.out.println("1 : 작업 추가 \t 2 : 최대값 \t 3 : 최대 우선순위 작업 처리");
			System.out.println("4 : 원소 키값 증가 \t 5 : 작업 제거 \t 6 : 종료");
			System.out.println("--------------------------------------------------------");
			String star = sc.next();
			int result = Integer.valueOf(star);
//			int result = sc.nextInt();
			
			switch(result) {
			case 1:
				System.out.println("키를 입력하세요.");
				int key = sc.nextInt();
				System.out.println("과목 이름을 입력하세요.");
				String name = sc.next();
				insert(heap, new Subject(key, name));
				break;
			case 2:
				StringBuilder sb = new StringBuilder();
				Subject s = max(heap);
				sb.append("최댓값 키 : ").append(s.getKey()).append(", 과목 이름 : ").append(s.getName());
				System.out.println(sb.toString());
				break;
			case 3:
				s = extract_max(heap);
				if(s != null) {
					System.out.println("최대 우선순위 작업 처리 완료.");	
				}
				else
					System.out.println("최대 우선순위 작업 처리 실패.");
				break;
			case 4:
				System.out.println("바꿀 원소의 키 값을 입력하세요.");
				key = sc.nextInt();
				System.out.println("바꿀 원소의 과목 이름을 입력하세요.");
				name = sc.next();
				System.out.println("변경할 키 값을 입력하세요.");
				int changeKey = sc.nextInt();
				if(increase_key(heap, new Subject(key, name), changeKey))
					System.out.println("원소 키값 증가 완료");
				else
					System.out.println("원소 키값 증가 실패");
				break;
			case 5:
				System.out.println("제거 할 원소의 키 값을 입력하세요.");
				key = sc.nextInt();
				System.out.println("제거 할 원소의 과목 이름을 입력하세요.");
				name = sc.next();
				if(delete(heap, new Subject(key, name)))
					System.out.println("작업 제거 완료");
				else
					System.out.println("작업 제거 실패");
				break;
			case 6:
				System.out.println("종료합니다.");
				System.exit(0);
				break;
			}
			System.out.println();
		}
	}
	
	// 현재 큐에 있는 노드 출력
	public static void print(Heap heap) {
		StringBuilder sb = new StringBuilder();
		System.out.println("------ 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다. ------ \n");
		for(int i = 1; i <= heap.size(); i++) {
			Subject s = heap.get(i);
			sb.append(s.getKey()).append(", ").append(s.getName()).append(" (index : " + i + ")").append("\n");
		}
		System.out.println(sb.toString());
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
		if(right <= heap.size() && heap.get(right).getKey() > heap.get(parent).getKey()) {
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
		for(int parent = length / 2; parent >= 1; parent = parent / 2) {
			if(heap.get(length).getKey() < heap.get(parent).getKey()) {
				break;
			}
			else {
				Collections.swap(heap.getList(), length, parent);
				length = parent;
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
		if(length < 1)
			return null;
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
//				maxHeapify(heap, i);
				buildMaxHeap(heap);
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
		for(int i = 1; i <= length; i++) {
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
		return this.index == 0 ? true : false;
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