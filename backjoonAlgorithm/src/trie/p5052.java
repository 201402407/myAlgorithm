package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 전화번호 목록 
// 트라이 알고리즘
// 개선 필요 
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

// 트라이 알고리즘에 사용되는 노드 
class TrieNode {
    private Map<Character, TrieNode> childNode = new HashMap<>();
    private boolean isLastChar;
   
    // 해당 문자가 마지막 문자인지
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

// 트라이 알고리즘 
class Trie {
    public TrieNode rootTemp;
   
    // 생성자 생성
    Trie() {
       this.rootTemp = new TrieNode();
    }
   
    // 삽입
    public void insert(String word) {
       TrieNode tempNode = this.rootTemp;
      
       for(int i = 0; i < word.length(); i++) {
    	   // 즉, childNode의 Key가 charAt(i) 이면 c(value = TrieNode) 리턴, 없으면 -> 다음에 작성한 { new TrieNode() } 함수를 실행하고, 그 결과값을 리턴해준다. 
    	   tempNode = tempNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());   // 두 값(단어의 문자와 현재 노드의 KEY값) 이 다르면 새로운 Node 생성
    	  
//    	  if(!tempNode.getChildNodes().containsKey(word.charAt(i))) {
//    		  System.out.println(word + " ,,, " + word.charAt(i) + " Fuck!!! ");
//    		  tempNode = new TrieNode();
//    	  }
    	  // 만약 여기서, 마지막 문자로 끝나있다면 접두사가 같은 것이므로 바로 종료 
//    	  if(tempNode.isLastChar()) {
////    		  System.out.println(word + " ,,, " + word.charAt(i) + " Fuck ");
//    		  return false;
//    	  }
       }
      
       tempNode.setLastChar(true);
//       return true;
    }
   
    // 존재 여부
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
      
       // 해당 단어로 죵료하는 문자가 있는 경우 중 자기 자신과 같은 단어일 경우는 false(조건에서 전화번호가 완전 같은 두 값은 존재하지 않는다고 함)
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
   
    // 삭제
    private void delete(TrieNode tempNode, String word, int index) {
       char ch = word.charAt(index);
      
       // 아예 처음부터 없는 단어인지 체크하기
       if(!tempNode.getChildNodes().containsKey(ch)) {
           throw new Error("TNE");
       }
      
       TrieNode childNode = tempNode.getChildNodes().get(ch);
       index++;
      
       if(index == word.length()) {
           if(!childNode.isLastChar()) { // 찾으려는 단어는 끝났는데, 이건 끝이 아닌 경우
              throw new Error("NOT LAST");
           }
          
           childNode.setLastChar(false);
          
           if(childNode.getChildNodes().isEmpty()) { // 더이상 하위 단어가 존재하지 않으면(단어와 일치하면) 삭제
              tempNode.getChildNodes().remove(ch);
           }
       }
       else {
          delete(childNode, word, index); // 재귀호출
          // 재귀호출 이후이므로
          if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty()) {
              tempNode.getChildNodes().remove(ch);
          }
		}
   }
}