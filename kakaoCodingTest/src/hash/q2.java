package hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 전화번호 목록
// startsWith String 함수 사용해서 진행해도 됨.
// 나는 정규표현식 사용
// 사전정렬해서 인접 비교 해도 됨
class q2 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        if(phone_book.length == 1) {
            return answer;
        }

        Arrays.sort(phone_book, new Comparator<String>() {  // 문자열 길이 오름차순 정렬\
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        for(int i = 0; i < phone_book.length - 1; i++) {
            String startStr = phone_book[i];
            StringBuilder sb = new StringBuilder();
            sb.append("^").append(startStr).append("[0-9]+").append("$");
            Pattern pattern = Pattern.compile(sb.toString());
            for(int j = i + 1; j < phone_book.length; j++) {
                Matcher matcher = pattern.matcher(phone_book[j]);
                if(matcher.find()) {
                    return false;
                }
            }
        }
        return answer;
    }
}
