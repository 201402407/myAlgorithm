package greedyAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;

public class q1 {
	public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        ArrayList<Integer> lostArray = new ArrayList<>();
        ArrayList<Integer> reserveArray = new ArrayList<>();
        
        for(int element : lost) {
            lostArray.add(element);
        }
        for(int element : reserve) {
            reserveArray.add(element);
        }
    
        int cnt = 0;
        while(cnt != lostArray.size()) {
            boolean isSame = false;
            for(int j = 0; j < reserveArray.size(); j++) {
                int lostElement = lostArray.get(cnt);
                if(lostElement == reserveArray.get(j)) {
                    lostArray.remove(cnt);
                    reserveArray.remove(j);
                    isSame = true;
                    break;
                }
            }
            if(!isSame)
                cnt++;
        }
        
        int answer = n - lostArray.size(); // ��ü - �Ҿ���� �л� ��
        
        for(int i = 0; i < lostArray.size(); i++) {
            int lostElement = lostArray.get(i);
            for(int j = 0; j < reserveArray.size(); j++) {
            int reserveElement = reserveArray.get(j);
            // reserve�� lost�� ����ų �� �ִٸ�
            if(lostElement - 1 == reserveElement || lostElement + 1 == reserveElement) {
                answer++;
                reserveArray.remove(j);
                break;
                }
            }
        }
        return answer;
    }
}
