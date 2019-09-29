package stackQueue;
import java.util.LinkedList;

// 프린터 문제

public class q1 {
    static LinkedList<Integer> q = new LinkedList<Integer>();
    public int solution(int[] priorities, int location) {
        for(int i = 0; i < priorities.length; i++) {
            q.add(priorities[i]);
        }
        int answer = 1;
        int index = location;
        while(!q.isEmpty()) {
            boolean success = false;
            int element = q.poll();
            for(int i = 0; i < q.size(); i++) {
                int ele = q.get(i);
                if(ele > element) {
                    q.add(element);
                    success = true;
                    if(index == 0) 
                        index = q.size() - 1;
                    else 
                        index--;
                    break;
                }
            }
            if(!success) {
                if(index == 0) {
                    break;
                }
                index--;
                answer++;
            }
        }
        return answer;
    }
}
