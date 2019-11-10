package intern;

class q5 {
    public int solution(int[] stones, int k) {
        int count = 0;
        for(int i = 0; i < k; i++) {
            while(stones[i] != 0) {
                // int[] temp = new int[stones.length];
                // System.arraycopy(stones, 0, temp, 0, stones.length);
                if(dfs(stones, k, i)) {
                    count++;
                    // System.arraycopy(temp, 0, stones, 0, stones.length);
                }
            }
        }
        return count;
    }
    
    static boolean dfs(int[] stones, int k, int current) {
        if(current >= stones.length) {
            return true;
        }
        stones[current]--;
        for(int i = 1; i <= k; i++) {
            if(current + i >= stones.length) {
                return true;
            }
            if(stones[current + i] != 0) {
                if(dfs(stones, k, current + i)) {
                    return true;   
                }
            }
        }
        return false;
    }
    
//     static int bfs(int[] stones, int k, int start) {
//         if(stones.length == 1) {
//             return 1;
//         }
//         Queue<Integer> queue = new LinkedList<Integer>();
//         queue.offer(start);
//         int count = 0;
//         while(!queue.isEmpty()) {
//             int currentIndex = queue.poll();
//             if(currentIndex >= stones.length) {
//                 return 1;
//             }
            
//             stones[currentIndex]--;
//             for(int i = 1; i <= k; i++) {
//                 if(current + i >= stones.length) {
//                     return 1;
//                 }
//                 if(stones[current + i] != 0) {
//                     if(dfs(stones, k, current + i)) {
//                         return true;   
//                     }
//                 }
//             }
            
//             // if(stones[currentIndex] != 0) {
//             //     stones[currentIndex]--;
//             //     for(int i = 1; i <= k; i++) {
//             //         queue.offer(currentIndex + i);    
//             //     }
//             // }
//         }
//         return count;
//     }
}