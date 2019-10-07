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

// Max Heap�� �켱���� ť�� ������ Ŭ����
public class MaxPriorityQueue {
	
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data04.txt";	// ��� ��� ����
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str;
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			int i = 1;
			Heap heap = new Heap();	// �� ��ü ����
			while((str = br.readLine()) != null) {
				st = new StringTokenizer(str, ", ");
				int key = Integer.valueOf(st.nextToken());
				String subjectName = st.nextToken();
				heap.push(new Subject(key, subjectName));
			}
			buildMaxHeap(heap);	// Max heap ����
			System.out.println();
			menu(heap);
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	// �޴� ���
	public static void menu(Heap heap) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			print(heap);
			System.out.println("--------------------------------------------------------");
			System.out.println("1 : �۾� �߰� \t 2 : �ִ밪 \t 3 : �ִ� �켱���� �۾� ó��");
			System.out.println("4 : ���� Ű�� ���� \t 5 : �۾� ���� \t 6 : ����");
			System.out.println("--------------------------------------------------------");
			String star = sc.next();
			int result = Integer.valueOf(star);
//			int result = sc.nextInt();
			
			switch(result) {
			case 1:
				System.out.println("Ű�� �Է��ϼ���.");
				int key = sc.nextInt();
				System.out.println("���� �̸��� �Է��ϼ���.");
				String name = sc.next();
				insert(heap, new Subject(key, name));
				break;
			case 2:
				StringBuilder sb = new StringBuilder();
				Subject s = max(heap);
				sb.append("�ִ� Ű : ").append(s.getKey()).append(", ���� �̸� : ").append(s.getName());
				System.out.println(sb.toString());
				break;
			case 3:
				s = extract_max(heap);
				if(s != null) {
					System.out.println("�ִ� �켱���� �۾� ó�� �Ϸ�.");	
				}
				else
					System.out.println("�ִ� �켱���� �۾� ó�� ����.");
				break;
			case 4:
				System.out.println("�ٲ� ������ Ű ���� �Է��ϼ���.");
				key = sc.nextInt();
				System.out.println("�ٲ� ������ ���� �̸��� �Է��ϼ���.");
				name = sc.next();
				System.out.println("������ Ű ���� �Է��ϼ���.");
				int changeKey = sc.nextInt();
				if(increase_key(heap, new Subject(key, name), changeKey))
					System.out.println("���� Ű�� ���� �Ϸ�");
				else
					System.out.println("���� Ű�� ���� ����");
				break;
			case 5:
				System.out.println("���� �� ������ Ű ���� �Է��ϼ���.");
				key = sc.nextInt();
				System.out.println("���� �� ������ ���� �̸��� �Է��ϼ���.");
				name = sc.next();
				if(delete(heap, new Subject(key, name)))
					System.out.println("�۾� ���� �Ϸ�");
				else
					System.out.println("�۾� ���� ����");
				break;
			case 6:
				System.out.println("�����մϴ�.");
				System.exit(0);
				break;
			}
			System.out.println();
		}
	}
	
	// ���� ť�� �ִ� ��� ���
	public static void print(Heap heap) {
		StringBuilder sb = new StringBuilder();
		System.out.println("------ ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�. ------ \n");
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
	
	// ���� s�� heap�� ���� �ִ´�
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
	
	// Ű ���� �ִ��� ���� ����
	public static Subject max(Heap heap) {
		if(heap.isEmpty())
			return null;
		return heap.get(1);
	}
	
	// Ű ���� �ִ��� ���� ����
	public static Subject extract_max(Heap heap) {
		int length = heap.size();
		if(length < 1)
			return null;
		Collections.swap(heap.getList(), length, 1);
		Subject result = heap.remove(length);
		
		maxHeapify(heap, 1);
		return result;
	}
	
	// ���� x�� Ű ���� k�� ������Ų��. �� �� k�� x�� ���� Ű ������ �۾����� �ʴ´ٰ� �����Ѵ�.
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
				heap.getList().set(i, s);	// �����
//				maxHeapify(heap, i);
				buildMaxHeap(heap);
				return true;
			}
		}
		return false;
	}
	
	// heap���� ���� x ����. ���� �� Max heap ����
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

//�ִ� �� ����ü �̳� Ŭ���� ����
// �ε����� 1���� ����
class Heap {
	List<Subject> ArrayHeap;
	int index;
	// ��ü ����
	public Heap() {
		this.ArrayHeap = new ArrayList<Subject>();
		this.ArrayHeap.add(null);	// �ε��� 1���� �����ϱ� ������ 0�� ä���.
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
		if(this.index == 0)	// �������
			return null;
		return this.ArrayHeap.remove(this.index--);
	}
	
	public boolean isEmpty() {
		return this.index == 0 ? true : false;
	}
	
	public int size() {	// �� ���� 1, �� ���� 2 ����
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
		if(this.index < parent)	// �ƹ��͵� ���� ��� size�� 1�̹Ƿ�
			return null;
		return this.ArrayHeap.get(parent);
	}
	
	public Subject leftChildNode(int i) {
		int left = 2 * i;
		if(this.index == 0 || i == 0)
			return null;
		if(this.index < left)	// �ƹ��͵� ���� ��� size�� 1�̹Ƿ�
			return null;
		return this.ArrayHeap.get(left);
	}
	
	public Subject rightChildNode(int i) {
		int right = (2 * i) + 1;
		if(this.index == 0 || i == 0)
			return null;
		if(this.index < right)	// �ƹ��͵� ���� ��� size�� 1�̹Ƿ�
			return null;
		return this.ArrayHeap.get(right);
	}
}
	
// �Է��� ���� ���� Ŭ���� ����
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