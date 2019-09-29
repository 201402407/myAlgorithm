package stackQueue;

// 송신탑 문제
public class q2 {
    static int[] result;
    public int[] solution(int[] heights) {
        result = new int[heights.length];
        int heightsSize = heights.length;
        for(int i = heightsSize - 1; i >= 1; i--) {
            boolean check = false;
            int height = heights[i];
            for(int j = i - 1; j >= 0; j--) {
                if(heights[j] > height) {
                    result[i] = j + 1;  // 해당 번째 통신탑 인덱스(+1)
                    check = true;
                    break;
                }
            }
            if(!check) { // 아무것도 수신할 게 없는 경우
                result[i] = 0;
            }
        }
        result[0] = 0;  // 무조건 0
        return result;
    }
}

