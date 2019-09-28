package hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// 베스트 앨범 문제
// 해쉬
// 한 번에 설계 후 맞춤
public class q4 {
    static HashMap<String, List<Music>> musicBox = new HashMap<String, List<Music>>();
    static HashMap<String, Integer> musicAllPlays = new HashMap<String, Integer>();
    static List<Integer> result = new ArrayList<Integer>();
    public int[] solution(String[] genres, int[] plays) {
        int genreSize = genres.length;
        // 입력값 배열 정리
        for(int i = 0; i < genreSize; i++) {
            String index = genres[i];
            int play = plays[i];
            
            // musicBox에 음악 넣기
            List<Music> musics = new ArrayList<Music>();
            if(musicBox.containsKey(index)) {    // musicBox에 기존에 저장된 값이 있는 경우
                musics = musicBox.get(index);
            }
            musics.add(new Music(i, play));
            musicBox.put(index, musics);
            
            // music 총 횟수를 담은 HashMap에 횟수 추가하기
            if(musicAllPlays.containsKey(index)) {
                musicAllPlays.put(index, musicAllPlays.get(index) + play);
            }
            else {
                musicAllPlays.put(index, play);
            }
        }
        
        // 총 횟수 내림차순 정렬해서 하나씩 베스트 앨범 찾기
        List<String> sorted = getListOfSortedMapKey(musicAllPlays);
        for(String element : sorted) {
            List<Music> musicList = musicBox.get(element);
            if(musicList.size() == 1) {
                result.add(musicList.get(0).getCount());
                continue;
            }
            // 많이 재생된 노래 두 곡을 위해 정렬.(+ 고유번호까지 비교)
            Collections.sort(musicList, new Comparator<Music>() {
               public int compare(Music m1, Music m2) {
                   if(m1.getPlay() < m2.getPlay()) {
                       return 1;
                   }
                   else if(m1.getPlay() == m2.getPlay()) {
                       if(m1.getCount() < m2.getCount())
                           return -1;
                       else
                           return 1;
                   }
                   else {
                       return -1;
                   }
               } 
            });
            // 두 곡 넣기
            result.add(musicList.get(0).getCount());
            result.add(musicList.get(1).getCount());
        }
        int[] answer = new int[result.size()];
        for(int y = 0; y < result.size(); y++) {
            answer[y] = result.get(y);
        }
        return answer;
    }
    
    // 정렬하는 함수
    public static List<String> getListOfSortedMapKey(HashMap<String, Integer> map) {
        List<String> list = new ArrayList<String>();
        list.addAll(map.keySet());
        Collections.sort(list, new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
              int a1 = map.get(o1);
              int a2 = map.get(o2);
               // 재생된 횟수는 다르다고 했으므로
              if(a1 < a2)
                  return 1;
              else if(a1 == a2)
                  return 0;
              else
                  return -1;    // -1이 이 순서대로 고정시키자! 뜻
          } 
        });
        // Collections.reverse(list);	// 역순 정렬
        return list;
    }
}
class Music {
    private int count;
    private int play;
    Music(int count, int play) {
        this.count = count;
        this.play = play;
    }
    public int getPlay() {
        return this.play;
    }
    public int getCount() {
        return this.count;
    }
}