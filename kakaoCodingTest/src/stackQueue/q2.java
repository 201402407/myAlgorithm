package stackQueue;

// �۽�ž ����
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
                    result[i] = j + 1;  // �ش� ��° ���ž �ε���(+1)
                    check = true;
                    break;
                }
            }
            if(!check) { // �ƹ��͵� ������ �� ���� ���
                result[i] = 0;
            }
        }
        result[0] = 0;  // ������ 0
        return result;
    }
}

