package year2019;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class q4 {
    static int[] answer;
    public int[] solution(String[] words, String[] queries) {
        int wordsLength = words.length;
        int queriesLength = queries.length;
        
        if(wordsLength < 2 || wordsLength > 100000) {
            System.out.println("words의 길이를 벗어났습니다.");
            System.exit(0);
        }
        if(queriesLength < 1 || queriesLength > 100000) {
            System.out.println("queries의 길이를 벗어났습니다.");
            System.exit(0);
        }
        answer = new int[queriesLength];
        Arrays.sort(answer, new Comparator<>);
        for(int i = 0; i < queriesLength; i++) {
            String element = queries[i];
            if(element.length() < 1 || element.length() > 10000) {
                System.out.println("queries 내 원소 하나가 길이를 벗어났습니다.");
                System.exit(0);
            }
            answer[i] = search(words, element);
        }
        return answer;
    }
    
    private int search(String[] words, String element) {
        int count = 0;
        element = element.replace("?", ".");
        String patternStr = "^" + element + "$";
        Pattern pattern = Pattern.compile(patternStr);
        for(String word : words) {
            if(word.length() != element.length()) {
                continue;
            }
            Matcher matcher = pattern.matcher(word);
            if(matcher.find()) {
                count++;
            }
        }
        return count;
    }
}
