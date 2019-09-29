package stackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 기능개발 문제
// 두 배열을 미리 하나의 큐로 합치는 것이 관건.
// 처음에 두 배열을 두개의 큐로 사용하려다 시간 초과됨
public class q4 {
    static List<Integer> result = new ArrayList<Integer>();
//    static Queue<Integer> progress = new LinkedList<Integer>();
//    static Queue<Integer> speed = new LinkedList<Integer>();
    static Queue<Integer> queue = new LinkedList<Integer>();
    public int[] solution(int[] progresses, int[] speeds) {
        int size = progresses.length;
        for(int i = 0; i < size; i++) {
            queue.add((int) Math.ceil((100 - progresses[i]) / speeds[i]));
        }

        // int count = 1;
        while(!queue.isEmpty()) {
            int count = 1;
            int day = queue.poll();

            // 다음 값들 중 배포 가능한 값이 있는지 계속 체크
            while(!queue.isEmpty()) {
                int nextDay = queue.peek();
                if(day >= nextDay) {
                    queue.poll();
                    count++;
                    continue;
                }
                else {
                    break;
                }
            }
            result.add(count);
        }
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}