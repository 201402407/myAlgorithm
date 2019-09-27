package hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// ��ȭ��ȣ ���
// startsWith String �Լ� ����ؼ� �����ص� ��.
// ���� ����ǥ���� ���
// ���������ؼ� ���� �� �ص� ��
class q2 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        if(phone_book.length == 1) {
            return answer;
        }

        Arrays.sort(phone_book, new Comparator<String>() {  // ���ڿ� ���� �������� ����\
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
