package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Ʈ���� �˰���
// �޴��� ���� 
public class p5670 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		
		while(!str.equals("")) {
			int tc = Integer.valueOf(str);
			List<String> list = new ArrayList<String>();
			Trie5670 trie = new Trie5670();
			
			// �Է� �ޱ� 
			for(int i = 0; i < tc; i++) {
				String word = br.readLine();
				list.add(word);
				trie.insert(word);
			}
			
			// ���� ã��
			double sum = 0;
			for(String ele : list) {
				sum += trie.contains(ele);
			}
			
			// ��� ���
			double avg = sum / tc;
			sb.append(String.format("%.2f", avg));
			sb.append("\n");
			
			str = br.readLine();
		}
		
		System.out.println(sb.toString());
	}
}

//Ʈ���� �˰��� ���Ǵ� ��� 
class TrieNode5670 {
	private Map<Character, TrieNode5670> childNode = new HashMap<>();
	private boolean isLastChar;

	// �ش� ���ڰ� ������ ��������
	public boolean isLastChar() {
		return this.isLastChar;
	}

	public void setLastChar(boolean isLastChar) {
		this.isLastChar = isLastChar;
	}

	public Map<Character, TrieNode5670> getChildNodes() {
		return this.childNode;
	}
}

//Ʈ���� �˰��� 
class Trie5670 {
	public TrieNode5670 rootTemp;

	// ������ ����
	Trie5670() {
		this.rootTemp = new TrieNode5670();
	}

	// ����
	public void insert(String word) {
		TrieNode5670 tempNode = this.rootTemp;
   
    for(int i = 0; i < word.length(); i++) {
    	// ��, childNode�� Key�� charAt(i) �̸� c(value = TrieNode) ����, ������ -> ������ �ۼ��� { new TrieNode() } �Լ��� �����ϰ�, �� ������� �������ش�. 
    	tempNode = tempNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode5670());   // �� ��(�ܾ��� ���ڿ� ���� ����� KEY��) �� �ٸ��� ���ο� Node ����
    }
   
	tempNode.setLastChar(true);
	}

	 // ���� ����
	 public int contains(String word) {
		 TrieNode5670 tempNode = this.rootTemp;
		 int count = 1;
		 // ù ���ڴ� ������ �Է��ؾ� �ϹǷ� ù ���� ���� 
		 tempNode = tempNode.getChildNodes().get(word.charAt(0));
		 
		 for(int i = 1; i < word.length(); i++) {
			 if(tempNode.getChildNodes().size() >= 2) {
				 count++;
			 }
			 // ���� �ش� ���ڰ� ��� ���ڿ��� �������ε�, ���� �ٸ� �ܾ���� ������ �ڿ� �ִ� ���
			 else if(tempNode.getChildNodes().size() == 1 && tempNode.isLastChar()) {
				 count++;
			 }
			 
			 char ch = word.charAt(i);
			 TrieNode5670 node = tempNode.getChildNodes().get(ch);
			 
			 tempNode = node;
	    }
	   
	    return count;
	 }
}