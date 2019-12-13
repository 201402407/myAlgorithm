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

// 201402407 ���ؿ�
// ������ �ڵ�
public class huffman {
	static HashMap<Character, String> encodedCode;
	
	public static void main(String args[]) throws IOException {
		String fileSrc = new java.io.File("").getAbsolutePath();
		
		// ���ڵ� ����
		String inputFileSrc =  fileSrc + "/src/data12.txt";	// ��� ��� ����
		String outputEncodedFileSrc =  fileSrc + "/src/hw12_05_201402407_encoded.txt";	// encoded ��� ����
		String outputTableFileSrc =  fileSrc + "/src/hw12_05_201402407_table.txt";	// table ��� ����
		FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
		
		byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
		while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
//		StringTokenizer st = new StringTokenizer(new String(buffer), ""); // ��ū���� ���� �и�
//		int len = st.countTokens();
//		int[] freq = new int[26];	// �ҹ��� a ~ z���� 26��. a�� �ƽ�Ű �ڵ�� 97�̴�.
		HashMap<Character, Integer> freq = new HashMap<Character, Integer>();	// �� ���� �ִ� �ؽ� ��
		PriorityQueue<Node> incodeQueue = new PriorityQueue<Node>();
		encodedCode = new HashMap<Character, String>();
		
		String data = new String(buffer);
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);
			if(freq.containsKey(ch)) {	// �̹� �ؽø� �ȿ� �ִ� ���ĺ��̸�(������ �� ���̶� �����ϴ� ���)
				freq.put(ch, freq.get(ch) + 1);	// �󵵼� 1 ����
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
		
		// ���Ͽ� ���� ���� ���� ����
		File outputEncodedFile = new File(outputEncodedFileSrc);
		File outputTableFile = new File(outputTableFileSrc);
		FileWriter encodedWriter = new FileWriter(outputEncodedFile, false);
		FileWriter tableWriter = new FileWriter(outputTableFile, false);
		
	    // ���Ͽ� ����
		StringBuilder encodedBuilder = new StringBuilder();
		StringBuilder tableBuilder = new StringBuilder();
		
		// table txt ���� ����, ���ϴ� ���� StringBuilder�� ���� 
		for(Character ch : encodedCode.keySet()) {
			tableBuilder.append(ch).append(",").append(encodedCode.get(ch)).append("\n");
//			System.out.println(ch + "," + code.get(ch));
		}
		
		// encoded txt ���� ����, ���ϴ� ���� StringBuilder�� ����
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);	// ���ĺ� �ϳ��� ������
			encodedBuilder.append(encodedCode.get(ch));
//			System.out.print(code.get(ch));
		}
		
		// ���Ͽ� ����
		encodedWriter.write(encodedBuilder.toString());
		tableWriter.write(tableBuilder.toString());
		encodedWriter.flush();
		tableWriter.flush();
		encodedWriter.close();
		tableWriter.close();
		
		
		// ���ڵ� ����
		String inputDecodedFileSrc =  fileSrc + "/src/data12_encoded.txt";	// ��� ��� ����
		String inputTableFileSrc =  fileSrc + "/src/data12_table.txt";	// ��� ��� ����
		String outputFileSrc = fileSrc + "/src/hw12_05_201402407_decoded.txt";
		 
		FileReader tableReader = new FileReader(new File(inputTableFileSrc));
		BufferedReader tableBr = new BufferedReader(tableReader);
		
		PriorityQueue<DecodeNode> decodeQueue = new PriorityQueue<DecodeNode>();
		String tableStr;
		while((tableStr = tableBr.readLine()) != null) {	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(tableStr, ",");
			char ch = st.nextToken().charAt(0);
			String code = st.nextToken();
			DecodeNode node = new DecodeNode(code, ch);
			decodeQueue.offer(node);
		}
		
		FileInputStream decodedInputStream = new FileInputStream(inputDecodedFileSrc);
		FileOutputStream decodedOutputStream = new FileOutputStream(outputFileSrc);
		DecodeNode decodeRoot = makeHuffmanTree(decodeQueue);
		DecodeNode current = decodeRoot;	// ù ���� ���
		byte[] decodedBuffer = new byte[1024];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
		String line;
		StringBuilder sb = new StringBuilder();
		
		// ���� ���� �б�
		while(decodedInputStream.read(decodedBuffer, 0, decodedBuffer.length) != -1) {
			line = new String(decodedBuffer);
			int len = line.length();
			
			for(int i = 0; i < len; i++) {	// �� ���ھ� Ž��
				char direction = line.charAt(i);
				DecodeNode next = searchHuffmanTree(direction, current);
				if(next.getCode() != null && next.getCode().length() != 0) {	// �ڵ尡 ���������
					decodedOutputStream.write(next.getAlphabet());
					current = decodeRoot;
				}
				else {	// �ڽ� ���� �̵��ϰ� ���� �ڵ� ���� Ž��
					current = next;
				}
			}
		}
		
		decodedOutputStream.flush();
		decodedOutputStream.close();
		decodedInputStream.close();
	}
	
	// �ڽ� ��� ���� �Լ�
	public static DecodeNode searchHuffmanTree(char direction, DecodeNode parent) {
		if(direction == '0') {
			return parent.getLeft();
		}
		if(direction == '1') {
			return parent.getRight();
		}
		return null;
	}
	
	// ���ڵ��� �ʿ��� ������ Ʈ���� ����� �Լ�
	public static DecodeNode makeHuffmanTree(PriorityQueue<DecodeNode> q) {
		DecodeNode root = new DecodeNode(null, '-'); 
		
		while(!q.isEmpty()) {
			DecodeNode node = q.poll();
			String code = node.getCode();
			char alphabet = node.getAlphabet();
			DecodeNode currentNode = root;
			for(int i = 0; i < code.length() - 1; i++) {	// �� �� �ڸ� �� ������ Ž���ؼ� ��带 �������´�.
				char direction = code.charAt(i);
				if(direction == '0') {
					if(currentNode.getLeft() == null) {	//	���� �ڽ��� ��尡 ���̸�
						currentNode = addLeftNode(currentNode, null, '-');
					}
					else {
						currentNode = currentNode.getLeft();
					}
				}
				if(direction == '1') {
					if(currentNode.getRight() == null) {	//	������ �ڽ��� ��尡 ���̸�
						currentNode = addRightNode(currentNode, null, '-');
					}
					else {
						currentNode = currentNode.getRight();
					}
				}
//				addNode(direction, code, '-');
			}
			char direction = code.charAt(code.length() - 1);	// ���� �� �ڵ� ���� ���
			if(direction == '0') {
				addLeftNode(currentNode, code, alphabet);
			}
			if(direction == '1') {
				addRightNode(currentNode, code, alphabet);
			}
		}
		return root;
	}
	
	// ���� �ڽ� ��带 �߰��ϴ� �Լ�. ���� �ƹ��͵� ������ -�� �Ѵ�.
	public static DecodeNode addLeftNode(DecodeNode parent, String code, char Alphabet) {
		parent.setLeft(new DecodeNode(code, Alphabet));
		return parent.getLeft();
	}
	
	// ������ �ڽ� ��带 �߰��ϴ� �Լ�. ���� �ƹ��͵� ������ -�� �Ѵ�.
	public static DecodeNode addRightNode(DecodeNode parent, String code, char Alphabet) {
		parent.setRight(new DecodeNode(code, Alphabet));
		return parent.getRight();
	}
	
	// ������ Ʈ������ �� ���� �Ǵ��Ͽ� �ڵ�� �ٲٴ� �Լ�
	public static void getStringHuffmanCode(Node node, String str) {
		if(node == null) {
			return;
		}
		
		getStringHuffmanCode(node.getLeft(), str + "0");	// ���� ��� ��� ȣ��
		getStringHuffmanCode(node.getRight(), str + "1");	// ���� ��� ��� ȣ��
		if(node.getAlphabet() != '\0') {
			encodedCode.put(node.getAlphabet(), str);
		}
	}
	
	// �� ���� ���� ������ Ʈ���� ����� �Լ�
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
	private Node left, right;	// ���ʰ� ������ �ڽ� ���
	
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
