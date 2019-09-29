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
        while(!move.isEmpty() || !wait.isEmpty() ) {    // 전부 다리 지나기 전까지 반복
            // 다리에서 목적지로 이동
            if(!move.isEmpty()) { // 다리 건너는 트럭이 있으면
                Truck truck = move.peek();
                if(truck.getStartTime() + bridge_length == time) { // 다리 지날 시간 되면
                    move.poll();
                    success.add(truck.getWeight());
                    nowWeight -= truck.getWeight();
                }
            }
            // 대기에서 다리로 이동
            if(!wait.isEmpty()) {   // 대기 트럭이 존재하는 경우
                int waitTruck = wait.peek();    // 첫 번쨰 원소 꺼내보기
                if(nowWeight + waitTruck <= weight) { // 다리가 무게를 버티면
                    move.add(new Truck(wait.poll(), time)); // 다리에 원소 추가
                    nowWeight += waitTruck; // 전체 무게에 트럭 무게 추가
                }
                else { // 다리가 무게를 못버티면
                    if(!move.isEmpty()) { // 다리에 트럭이 있으면
                        Truck bridgeTruck = move.peek();  
                        // 맨 앞 트럭의 시작 시간과 다리길이를 더한 시간으로 변경
                        // 시간 줄이기
                        time = bridgeTruck.getStartTime() + bridge_length;
                        continue;
                    }
                }
                time++;
            }
            else {
                if(!move.isEmpty()) { // 다리에 트럭이 있으면
                        Truck truck2 = move.peek();  
                        // 맨 앞 트럭의 시작 시간과 다리길이를 더한 시간으로 변경
                        // 시간 줄이기
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
