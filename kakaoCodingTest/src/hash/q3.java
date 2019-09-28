package hash;

import java.util.HashMap;

// A, B, C 3���� ������ ��� -> A + B + C + AB + AC + BC + ABC = (A + 1)(B + 1)(C + 1) - 1�̴�.
public class q3 {
    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    static int[] count;
    public int solution(String[][] clothes) {
        for(int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            if(map.containsKey(type)) {
                map.put(type, map.get(type) + 1);
            }
            else {
                map.put(type, 1);
            }
        }

        int mapSize = map.size();    
        int answer = 1;
        for(String element : map.keySet()) {    // (A + 1)(B + 1) ������
            answer = answer * (map.get(element) + 1);
        }
        return answer - 1;
    }
}