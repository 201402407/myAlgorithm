package week12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 201402407 이해원
// 허프만 코드
public class huffman {
	static HashMap<Character, String> encodedCode;
	
	public static void main(String args[]) throws IOException {
		String fileSrc = new java.io.File("").getAbsolutePath();
		
		// 인코딩 과정
		String inputFileSrc =  fileSrc + "/src/data12.txt";	// 상대 경로 설정
		String outputEncodedFileSrc =  fileSrc + "/src/hw12_05_201402407_encoded.txt";	// encoded 경로 설정
		String outputTableFileSrc =  fileSrc + "/src/hw12_05_201402407_table.txt";	// table 경로 설정
		FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
		
		byte[] buffer = new byte[fileInputStream.available()];	// 파일의 전체 크기만큼 바이트 버퍼 설정
		while(fileInputStream.read(buffer) != -1) {}	// 버퍼에 값 저장
//		StringTokenizer st = new StringTokenizer(new String(buffer), ""); // 토큰으로 숫자 분리
//		int len = st.countTokens();
//		int[] freq = new int[26];	// 소문자 a ~ z까지 26개. a의 아스키 코드는 97이다.
		HashMap<Character, Integer> freq = new HashMap<Character, Integer>();	// 빈도 수를 넣는 해시 맵
		PriorityQueue<Node> incodeQueue = new PriorityQueue<Node>();
		encodedCode = new HashMap<Character, String>();
		
		String data = new String(buffer);
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);
			if(freq.containsKey(ch)) {	// 이미 해시맵 안에 있는 알파벳이면(사전에 한 번이라도 존재하는 경우)
				freq.put(ch, freq.get(ch) + 1);	// 빈도수 1 증가
			}
			else {
				freq.put(ch, 1);
			}
		}
		
		for(Character c : freq.keySet()) {
			Node node = new Node();
			node.setAlphabet(c);
			node.setFreq(freq.get(c));
			incodeQueue.offer(node);
		}
		
		Node encodedRoot = huffMan(incodeQueue, incodeQueue.size());
		getStringHuffmanCode(encodedRoot, new String());
		
		// 파일에 쓰기 위한 변수 생성
		File outputEncodedFile = new File(outputEncodedFileSrc);
		File outputTableFile = new File(outputTableFileSrc);
		FileWriter encodedWriter = new FileWriter(outputEncodedFile, false);
		FileWriter tableWriter = new FileWriter(outputTableFile, false);
		
	    // 파일에 쓰기
		StringBuilder encodedBuilder = new StringBuilder();
		StringBuilder tableBuilder = new StringBuilder();
		
		// table txt 파일 생성, 원하는 값을 StringBuilder에 저장 
		for(Character ch : encodedCode.keySet()) {
			tableBuilder.append(ch).append(",").append(encodedCode.get(ch)).append("\n");
//			System.out.println(ch + "," + code.get(ch));
		}
		
		// encoded txt 파일 생성, 원하는 값을 StringBuilder에 저장
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);	// 알파벳 하나씩 꺼내기
			encodedBuilder.append(encodedCode.get(ch));
//			System.out.print(code.get(ch));
		}
		
		// 파일에 쓰기
		encodedWriter.write(encodedBuilder.toString());
		tableWriter.write(tableBuilder.toString());
		encodedWriter.flush();
		tableWriter.flush();
		encodedWriter.close();
		tableWriter.close();
		
		
		// 디코딩 과정
		String inputDecodedFileSrc =  fileSrc + "/src/data12_encoded.txt";	// 상대 경로 설정
		String inputTableFileSrc =  fileSrc + "/src/data12_table.txt";	// 상대 경로 설정
		String outputFileSrc = fileSrc + "/src/hw12_05_201402407_decoded.txt";
		 
		FileReader tableReader = new FileReader(new File(inputTableFileSrc));
		BufferedReader tableBr = new BufferedReader(tableReader);
		
		PriorityQueue<DecodeNode> decodeQueue = new PriorityQueue<DecodeNode>();
		String tableStr;
		while((tableStr = tableBr.readLine()) != null) {	// 버퍼에 값 저장
			StringTokenizer st = new StringTokenizer(tableStr, ",");
			char ch = st.nextToken().charAt(0);
			String code = st.nextToken();
			DecodeNode node = new DecodeNode(code, ch);
			decodeQueue.offer(node);
		}
		
		FileInputStream decodedInputStream = new FileInputStream(inputDecodedFileSrc);
		FileOutputStream decodedOutputStream = new FileOutputStream(outputFileSrc);
		DecodeNode decodeRoot = makeHuffmanTree(decodeQueue);
		DecodeNode current = decodeRoot;	// 첫 시작 노드
		byte[] decodedBuffer = new byte[1024];	// 파일의 전체 크기만큼 바이트 버퍼 설정
		String line;
		StringBuilder sb = new StringBuilder();
		
		// 파일 전부 읽기
		while(decodedInputStream.read(decodedBuffer, 0, decodedBuffer.length) != -1) {
			line = new String(decodedBuffer);
			int len = line.length();
			
			for(int i = 0; i < len; i++) {	// 한 글자씩 탐색
				char direction = line.charAt(i);
				DecodeNode next = searchHuffmanTree(direction, current);
				if(next.getCode() != null && next.getCode().length() != 0) {	// 코드가 들어있으면
					decodedOutputStream.write(next.getAlphabet());
					current = decodeRoot;
				}
				else {	// 자식 노드로 이동하고 다음 코드 문자 탐색
					current = next;
				}
			}
		}
		
		decodedOutputStream.flush();
		decodedOutputStream.close();
		decodedInputStream.close();
	}
	
	// 자식 노드 리턴 함수
	public static DecodeNode searchHuffmanTree(char direction, DecodeNode parent) {
		if(direction == '0') {
			return parent.getLeft();
		}
		if(direction == '1') {
			return parent.getRight();
		}
		return null;
	}
	
	// 디코딩에 필요한 허프만 트리를 만드는 함수
	public static DecodeNode makeHuffmanTree(PriorityQueue<DecodeNode> q) {
		DecodeNode root = new DecodeNode(null, '-'); 
		
		while(!q.isEmpty()) {
			DecodeNode node = q.poll();
			String code = node.getCode();
			char alphabet = node.getAlphabet();
			DecodeNode currentNode = root;
			for(int i = 0; i < code.length() - 1; i++) {	// 맨 끝 자리 수 전까지 탐색해서 노드를 만들어놓는다.
				char direction = code.charAt(i);
				if(direction == '0') {
					if(currentNode.getLeft() == null) {	//	왼쪽 자식의 노드가 널이면
						currentNode = addLeftNode(currentNode, null, '-');
					}
					else {
						currentNode = currentNode.getLeft();
					}
				}
				if(direction == '1') {
					if(currentNode.getRight() == null) {	//	오른쪽 자식의 노드가 널이면
						currentNode = addRightNode(currentNode, null, '-');
					}
					else {
						currentNode = currentNode.getRight();
					}
				}
//				addNode(direction, code, '-');
			}
			char direction = code.charAt(code.length() - 1);	// 가장 끝 코드 문자 얻기
			if(direction == '0') {
				addLeftNode(currentNode, code, alphabet);
			}
			if(direction == '1') {
				addRightNode(currentNode, code, alphabet);
			}
		}
		return root;
	}
	
	// 왼쪽 자식 노드를 추가하는 함수. 만약 아무것도 없으면 -를 한다.
	public static DecodeNode addLeftNode(DecodeNode parent, String code, char Alphabet) {
		parent.setLeft(new DecodeNode(code, Alphabet));
		return parent.getLeft();
	}
	
	// 오른쪽 자식 노드를 추가하는 함수. 만약 아무것도 없으면 -를 한다.
	public static DecodeNode addRightNode(DecodeNode parent, String code, char Alphabet) {
		parent.setRight(new DecodeNode(code, Alphabet));
		return parent.getRight();
	}
	
	// 허프만 트리에서 빈도 수를 판단하여 코드로 바꾸는 함수
	public static void getStringHuffmanCode(Node node, String str) {
		if(node == null) {
			return;
		}
		
		getStringHuffmanCode(node.getLeft(), str + "0");	// 좌측 노드 재귀 호출
		getStringHuffmanCode(node.getRight(), str + "1");	// 우측 노드 재귀 호출
		if(node.getAlphabet() != '\0') {
			encodedCode.put(node.getAlphabet(), str);
		}
	}
	
	// 빈도 수를 보고 허프만 트리를 만드는 함수
	public static Node huffMan(PriorityQueue<Node> q, int n) {
		for(int i = 1; i < n; i++) {
			Node z = new Node();
			z.setLeft(q.poll());
			z.setRight(q.poll());
			z.setFreq(z.getLeft().getFreq() + z.getRight().getFreq());
			q.offer(z);
		}
		return q.poll();
	}
	
//	public static 
}

class Node implements Comparable<Node> {
	private int freq;
	private char alphabet;
	private Node left, right;	// 왼쪽과 오른쪽 자식 노드
	
	Node() {
		
	}
	
	Node(int freq, char alphabet) {
		this.freq = freq;
		this.alphabet = alphabet;
	}
	
	Node(int freq, char alphabet, Node left, Node right) {
		this.freq = freq;
		this.alphabet = alphabet;
		this.left = left;
		this.right = right;
	}
	
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public char getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(char alphabet) {
		this.alphabet = alphabet;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public int compareTo(Node o) {
		if(this.freq < o.getFreq())
			return -1;
		else
			return 1;
	}
}

class DecodeNode implements Comparable<DecodeNode> {
	private String code;
	private char alphabet;
	private DecodeNode left, right;
	
	DecodeNode() {
		
	}
	
	DecodeNode(String code, char alphabet) {
		this.code = code;
		this.alphabet = alphabet;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public char getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(char alphabet) {
		this.alphabet = alphabet;
	}

	public DecodeNode getLeft() {
		return left;
	}

	public void setLeft(DecodeNode left) {
		this.left = left;
	}

	public DecodeNode getRight() {
		return right;
	}

	public void setRight(DecodeNode right) {
		this.right = right;
	}

	@Override
	public int compareTo(DecodeNode o) {
		if(this.code.length() < o.getCode().length()) {
			return -1;
		}
		else
			return 1;
	}
	
	
}
