package dfsnfs;

public class q2 {
	// computers가 대칭이라는 가정 하에 진행. 대칭일 수 밖에 없음.
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
        if(computers[y][x] == 0)    // 네트워크 없으면 걍 종료
             return;

        computers[y][x] = 0;
        for(int i = 0; i < computers.length; i++) {
            checkNetwork(computers, y, i);
            checkNetwork(computers, x, i);
        }
    }
}
