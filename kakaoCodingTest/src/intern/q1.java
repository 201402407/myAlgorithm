package intern;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class q1 {
    static Stack<Integer>[] stackBoard;
    public int solution(int[][] board, int[] moves) {
        
        int n = board.length; 
        stackBoard = new Stack[n + 1];  // 인덱스 1부터 시작
        for(int i = 0; i < board.length; i++) {
            stackBoard[i] = new Stack<Integer>();
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != 0) {  // 0이 아닌 경우만 넣기
                    stackBoard[i].push(board[i][j]);
                }
            }
        }
        
        Queue<Integer> basket = moveToBasket(moves);  
        Stack<Integer> currentBasket = new Stack<Integer>();
        if(basket.size() <= 1) {  // 인형 갯수가 하나 또는 아무것도 없으면
            return 0;
        }
        
        int answer = 0;
        int count = 0;
        // int compareDoll = basket.poll();
        currentBasket.push(basket.poll());
        while(!basket.isEmpty()) {  // 하나씩 탐색하면서 같은 거 찾기
            int doll = basket.poll();
            if(currentBasket.isEmpty()) { // 비교 바스켓이 비어있으면
                currentBasket.push(doll);
                continue;
            }
            int compareDoll = currentBasket.lastElement();
            if(compareDoll == doll) {   // 이전 인형이랑 같으면
                answer++;   // 같은 거 하나 추가
                currentBasket.pop();    // 제거
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
            if(stackBoard[move].isEmpty()) { // 비었으면
                continue;   // 아무 작업도 하지 않음
            }
            else {  // 비어있지 않으면
                int doll = stackBoard[move].pop();
                basket.offer(doll);
            }
        }
        return basket;
    } 
}
