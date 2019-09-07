package dfsnfs;
import java.util.LinkedList;
import java.util.Queue;

public class q3 {
	boolean[] visited;
    int[] changeCount;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];    // 중복 탐색 체크를 위한 bool 배열
        changeCount = new int[words.length];    // 변환 단계를 적기 위한 int 배열
        int answer = changeWord(begin, target, words);
        return answer;
    }
    
    private int changeWord(String word, String target, String[] words) {
        if(getIndexOfWord(target, words) == -1) // target 단어가 words 배열 안에 없는 경우 
            return 0;
        
        int wordLength = word.length();    
        Queue<String> queue = new LinkedList<String>();
        queue.add(word);
        
        while(!queue.isEmpty()) {
            String currentWord = queue.poll();
            int currentIndex = getIndexOfWord(currentWord, words);
            if(currentIndex != -1) {  // begin 문자가 아닌 경우
                if(currentWord.equals(target) && visited[currentIndex]) {
                    return changeCount[currentIndex];
                }
            }            
            
            for(int i = 0; i < wordLength; i++) {   // 해당 위치 문자 제외한 나머지 문자를 하나씩 비교해보자.
                for(int j = 0; j < words.length; j++) { // 문자열 하나씩 방문
                    if(visited[j]) {    // 방문한 문자는 패스하고 다음 문자로 진행
                        continue;
                    }
                    String nextWord = words[j];
                    boolean hasNextWord = true;
                    for(int k = 0; k < nextWord.length(); k++) {
                        if(k == i) {    // 해당 번째의 문자는 비교하지 않기(바꿀 알파벳의 위치이므로)
                         continue;   
                        }
                        if(currentWord.charAt(k) != nextWord.charAt(k)) {
                            hasNextWord = false;
                            break;
                        }
                    }
                    if(hasNextWord) {   // 변환 가능한 알파벳인 경우
                        visited[j] = true;  // 방문했다고 설정
                        if(currentIndex == -1) {
                            changeCount[j] = 1;
                        }
                        else {
                            changeCount[j] = changeCount[currentIndex] + 1;
                        }
                        queue.add(nextWord);    // 큐에 추가
                    }
                }
            }
        }
        return changeCount[getIndexOfWord(target, words)];
    }
    
    private int getIndexOfWord(String word, String[] words) {
        for(int i = 0; i < words.length; i++) {
            if(word.equals(words[i]))
                return i;
        }
        return -1;
    }
}
