package intern;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class q1 {
    static Stack<Integer>[] stackBoard;
    public int solution(int[][] board, int[] moves) {
        
        int n = board.length; 
        stackBoard = new Stack[n + 1];  // �ε��� 1���� ����
        for(int i = 0; i < board.length; i++) {
            stackBoard[i] = new Stack<Integer>();
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != 0) {  // 0�� �ƴ� ��츸 �ֱ�
                    stackBoard[i].push(board[i][j]);
                }
            }
        }
        
        Queue<Integer> basket = moveToBasket(moves);  
        Stack<Integer> currentBasket = new Stack<Integer>();
        if(basket.size() <= 1) {  // ���� ������ �ϳ� �Ǵ� �ƹ��͵� ������
            return 0;
        }
        
        int answer = 0;
        int count = 0;
        // int compareDoll = basket.poll();
        currentBasket.push(basket.poll());
        while(!basket.isEmpty()) {  // �ϳ��� Ž���ϸ鼭 ���� �� ã��
            int doll = basket.poll();
            if(currentBasket.isEmpty()) { // �� �ٽ����� ���������
                currentBasket.push(doll);
                continue;
            }
            int compareDoll = currentBasket.lastElement();
            if(compareDoll == doll) {   // ���� �����̶� ������
                answer++;   // ���� �� �ϳ� �߰�
                currentBasket.pop();    // ����
            }
            else {
                currentBasket.push(doll);
            }
        }
        return answer;
    }
    
    static Queue<Integer> moveToBasket(int[] moves) {
        Queue<Integer> basket = new LinkedList<Integer>();
        for(int move : moves) {
            if(stackBoard[move].isEmpty()) { // �������
                continue;   // �ƹ� �۾��� ���� ����
            }
            else {  // ������� ������
                int doll = stackBoard[move].pop();
                basket.offer(doll);
            }
        }
        return basket;
    } 
}
