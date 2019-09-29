package stackQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q3 {
    static Queue<Integer> wait = new LinkedList<Integer>();
    static Queue<Truck> move = new LinkedList<Truck>();
    static List<Integer> success = new ArrayList<Integer>();
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int truckSize = truck_weights.length;
        for(int i = 0; i < truckSize; i++) {
            wait.add(truck_weights[i]);
        }
        
        int time = 1;
        int nowWeight = 0;
        while(!move.isEmpty() || !wait.isEmpty() ) {    // ���� �ٸ� ������ ������ �ݺ�
            // �ٸ����� �������� �̵�
            if(!move.isEmpty()) { // �ٸ� �ǳʴ� Ʈ���� ������
                Truck truck = move.peek();
                if(truck.getStartTime() + bridge_length == time) { // �ٸ� ���� �ð� �Ǹ�
                    move.poll();
                    success.add(truck.getWeight());
                    nowWeight -= truck.getWeight();
                }
            }
            // ��⿡�� �ٸ��� �̵�
            if(!wait.isEmpty()) {   // ��� Ʈ���� �����ϴ� ���
                int waitTruck = wait.peek();    // ù ���� ���� ��������
                if(nowWeight + waitTruck <= weight) { // �ٸ��� ���Ը� ��Ƽ��
                    move.add(new Truck(wait.poll(), time)); // �ٸ��� ���� �߰�
                    nowWeight += waitTruck; // ��ü ���Կ� Ʈ�� ���� �߰�
                }
                else { // �ٸ��� ���Ը� ����Ƽ��
                    if(!move.isEmpty()) { // �ٸ��� Ʈ���� ������
                        Truck bridgeTruck = move.peek();  
                        // �� �� Ʈ���� ���� �ð��� �ٸ����̸� ���� �ð����� ����
                        // �ð� ���̱�
                        time = bridgeTruck.getStartTime() + bridge_length;
                        continue;
                    }
                }
                time++;
            }
            else {
                if(!move.isEmpty()) { // �ٸ��� Ʈ���� ������
                        Truck truck2 = move.peek();  
                        // �� �� Ʈ���� ���� �ð��� �ٸ����̸� ���� �ð����� ����
                        // �ð� ���̱�
                        time = truck2.getStartTime() + bridge_length;
                    }
                else
                    break;
                }
        }
        return time;
    }
}

class Truck {
    private int weight;
    private int startTime;
    
    Truck(int weight, int startTime) {
        this.weight = weight;
        this.startTime = startTime;
    }
    
    public int getWeight() {
        return this.weight;
    }
    public int getStartTime() {
        return this.startTime;
    }
}
