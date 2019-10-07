package heap;

import java.util.PriorityQueue;

// �� �ʰ� ����
// �켱���� ť ���
class q1 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int element : scoville) {
            queue.add(element);
        }
        
        int count = 0;
        while(!queue.isEmpty()) {
            int scov = queue.poll();
            if(scov < K) {
                if(queue.peek() == null) {
                    return -1;
                }
                int nextScoville = queue.poll();
                queue.add(scov + (nextScoville * 2));
                count++;
            }
            else
                return count;
        }
        return -1;
    }
}
