package dfsnfs;

public class q2 {
	// computers�� ��Ī�̶�� ���� �Ͽ� ����. ��Ī�� �� �ۿ� ����.
    public int solution(int n, int[][] computers) {
        int answer = 0;
        for(int y = 0; y < computers.length; y++) {
            for(int x = 0; x < computers[0].length; x++) {
                int network = computers[y][x];
                if(network == 1) {
                    checkNetwork(computers, y, x);
                    answer++;
                }
            }
        }
        return answer;
    }

    public void checkNetwork(int[][] computers, int y, int x) {
        if(computers[y][x] == 0)    // ��Ʈ��ũ ������ �� ����
             return;

        computers[y][x] = 0;
        for(int i = 0; i < computers.length; i++) {
            checkNetwork(computers, y, i);
            checkNetwork(computers, x, i);
        }
    }
}
