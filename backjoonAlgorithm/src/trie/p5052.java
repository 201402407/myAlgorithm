package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// ��ȭ��ȣ ��� 
// Ʈ���� �˰���
// ���� �ʿ� 
public class p5052 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int tc = Integer.valueOf(st.nextToken());
		while(tc --> 0) {
			Trie trie = new Trie();
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			boolean isContain = false;
			List<String> keys = new ArrayList<String>();
			
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				trie.insert(str);
				keys.add(str);
			}
			
			for(String key : keys) {
				if(trie.contains(key)) {
					isContain = true;
					break;
				}
			}
			
			if(isContain) {
				sb.append("NO");
			}
			else {
				sb.append("YES");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

// Ʈ���� �˰��� ���Ǵ� ��� 
class TrieNode {
    private Map<Character, TrieNode> childNode = new HashMap<>();
    private boolean isLastChar;
   
    // �ش� ���ڰ� ������ ��������
    public boolean isLastChar() {
       return this.isLastChar;
    }
   
    public void setLastChar(boolean isLastChar) {
       this.isLastChar = isLastChar;
    }
   
    public Map<Character, TrieNode> getChildNodes() {
       return this.childNode;
    }
}

// Ʈ���� �˰��� 
class Trie {
    public TrieNode rootTemp;
   
    // ������ ����
    Trie() {
       this.rootTemp = new TrieNode();
    }
   
    // ����
    public void insert(String word) {
       TrieNode tempNode = this.rootTemp;
      
       for(int i = 0; i < word.length(); i++) {
    	   // ��, childNode�� Key�� charAt(i) �̸� c(value = TrieNode) ����, ������ -> ������ �ۼ��� { new TrieNode() } �Լ��� �����ϰ�, �� ������� �������ش�. 
    	   tempNode = tempNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());   // �� ��(�ܾ��� ���ڿ� ���� ����� KEY��) �� �ٸ��� ���ο� Node ����
    	  
//    	  if(!tempNode.getChildNodes().containsKey(word.charAt(i))) {
//    		  System.out.println(word + " ,,, " + word.charAt(i) + " Fuck!!! ");
//    		  tempNode = new TrieNode();
//    	  }
    	  // ���� ���⼭, ������ ���ڷ� �����ִٸ� ���λ簡 ���� ���̹Ƿ� �ٷ� ���� 
//    	  if(tempNode.isLastChar()) {
////    		  System.out.println(word + " ,,, " + word.charAt(i) + " Fuck ");
//    		  return false;
//    	  }
       }
      
       tempNode.setLastChar(true);
//       return true;
    }
   
    // ���� ����
    public boolean contains(String word) {
       TrieNode tempNode = this.rootTemp;
      
       for(int i = 0; i < word.length(); i++) {
           char ch = word.charAt(i);
           TrieNode node = tempNode.getChildNodes().get(ch);
           if(node == null) {
                  return false;
           }
          
           tempNode = node;
       }
      
       // �ش� �ܾ�� �շ��ϴ� ���ڰ� �ִ� ��� �� �ڱ� �ڽŰ� ���� �ܾ��� ���� false(���ǿ��� ��ȭ��ȣ�� ���� ���� �� ���� �������� �ʴ´ٰ� ��)
       if(tempNode.isLastChar()) {
    	   if(tempNode.getChildNodes().isEmpty()) {
    		   return false;
    	   }
       }
       
       return true;
    }
   
    public void delete(String word) {
       delete(this.rootTemp, word, 0);
    }
   
    // ����
    private void delete(TrieNode tempNode, String word, int index) {
       char ch = word.charAt(index);
      
       // �ƿ� ó������ ���� �ܾ����� üũ�ϱ�
       if(!tempNode.getChildNodes().containsKey(ch)) {
           throw new Error("TNE");
       }
      
       TrieNode childNode = tempNode.getChildNodes().get(ch);
       index++;
      
       if(index == word.length()) {
           if(!childNode.isLastChar()) { // ã������ �ܾ�� �����µ�, �̰� ���� �ƴ� ���
              throw new Error("NOT LAST");
           }
          
           childNode.setLastChar(false);
          
           if(childNode.getChildNodes().isEmpty()) { // ���̻� ���� �ܾ �������� ������(�ܾ�� ��ġ�ϸ�) ����
              tempNode.getChildNodes().remove(ch);
           }
       }
       else {
          delete(childNode, word, index); // ���ȣ��
          // ���ȣ�� �����̹Ƿ�
          if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty()) {
              tempNode.getChildNodes().remove(ch);
          }
		}
   }
}