package stackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// ��ɰ��� ����
// �� �迭�� �̸� �ϳ��� ť�� ��ġ�� ���� ����.
// ó���� �� �迭�� �ΰ��� ť�� ����Ϸ��� �ð� �ʰ���
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

            // ���� ���� �� ���� ������ ���� �ִ��� ��� üũ
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