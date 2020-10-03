package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// ���� �г���
// Ʈ���� ���� 
public class p16934 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		StringBuilder sb = new StringBuilder();
		Trie16934 trie = new Trie16934();
		
		for(int i = 0; i < n; i++) {
			String word = br.readLine();
			int aliasIndex = trie.insertAndGetAliasIndex(word);	// ������ ���� ���� ���λ��� ����ġ �ε��� ���� 
			if(aliasIndex == -1) {	// ������ ��ġ�ϴ� �ܾ �����ϸ� 
				int count = trie.getSameCount(word);
				
				if(count == 1) {	// ���� �ܾ 1���� �����ϴ� ���	 
					sb.append(word);
				}
				if(count > 1) {
					sb.append(word).append(count);
				}
			}
			else {
				sb.append(word.substring(0, aliasIndex));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

//Ʈ���� �˰��� ���Ǵ� ��� 
class TrieNode16934 {
	private Map<Character, TrieNode16934> childNode = new HashMap<>();
	private boolean isLastChar;
	private int count;

	// �ش� ���ڰ� ������ ��������
	public boolean isLastChar() {
		return this.isLastChar;
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setLastChar(boolean isLastChar) {
		this.isLastChar = isLastChar;
	}

	public Map<Character, TrieNode16934> getChildNodes() {
		return this.childNode;
	}
	
	public TrieNode16934 makeChildNodes(char ch) {
		this.childNode.put(ch, new TrieNode16934());
		return this.childNode.get(ch);
	}
}

//Ʈ���� �˰��� 
class Trie16934 {
	public TrieNode16934 rootTemp;

	// ������ ����
	Trie16934() {
		this.rootTemp = new TrieNode16934();
	}

	// ���� �� ��Ī�� ���� �� �ִ� �ε��� ��ġ ����
	// -1 ���� : �Ȱ��� �ܾ ������ ���� 
	public int insertAndGetAliasIndex(String word) {
		TrieNode16934 tempNode = this.rootTemp;
		int index = -1;
		boolean isEnd = false;
		
		for(int i = 0; i < word.length(); i++) {
			// �̹� ������ i��° �ܾ���� ���λ簡 ���� ��� 
			if(tempNode.getChildNodes().containsKey(word.charAt(i))) {
				tempNode = tempNode.getChildNodes().get(word.charAt(i));
			}
			else {	// ���� ���λ簡 �������� ��� 
				if(!isEnd) {
					index = i + 1;
					isEnd = true;
				}
			
				tempNode = tempNode.makeChildNodes(word.charAt(i));
			}
		}
	
		if(index != -1) {
			tempNode.setCount(1);
		}
		else {
			tempNode.setCount(tempNode.getCount() + 1);
		}
		
		return index;
	}

	// ���� ���� ������ ��, ���� �������� 
	public int getSameCount(String word) {
		TrieNode16934 tempNode = this.rootTemp;
		
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode16934 node = tempNode.getChildNodes().get(ch); 
			tempNode = node;
		}
	  	
		return tempNode.getCount();
	}
}
