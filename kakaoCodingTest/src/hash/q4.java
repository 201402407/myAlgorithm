package hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// ����Ʈ �ٹ� ����
// �ؽ�
// �� ���� ���� �� ����
public class q4 {
    static HashMap<String, List<Music>> musicBox = new HashMap<String, List<Music>>();
    static HashMap<String, Integer> musicAllPlays = new HashMap<String, Integer>();
    static List<Integer> result = new ArrayList<Integer>();
    public int[] solution(String[] genres, int[] plays) {
        int genreSize = genres.length;
        // �Է°� �迭 ����
        for(int i = 0; i < genreSize; i++) {
            String index = genres[i];
            int play = plays[i];
            
            // musicBox�� ���� �ֱ�
            List<Music> musics = new ArrayList<Music>();
            if(musicBox.containsKey(index)) {    // musicBox�� ������ ����� ���� �ִ� ���
                musics = musicBox.get(index);
            }
            musics.add(new Music(i, play));
            musicBox.put(index, musics);
            
            // music �� Ƚ���� ���� HashMap�� Ƚ�� �߰��ϱ�
            if(musicAllPlays.containsKey(index)) {
                musicAllPlays.put(index, musicAllPlays.get(index) + play);
            }
            else {
                musicAllPlays.put(index, play);
            }
        }
        
        // �� Ƚ�� �������� �����ؼ� �ϳ��� ����Ʈ �ٹ� ã��
        List<String> sorted = getListOfSortedMapKey(musicAllPlays);
        for(String element : sorted) {
            List<Music> musicList = musicBox.get(element);
            if(musicList.size() == 1) {
                result.add(musicList.get(0).getCount());
                continue;
            }
            // ���� ����� �뷡 �� ���� ���� ����.(+ ������ȣ���� ��)
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
            // �� �� �ֱ�
            result.add(musicList.get(0).getCount());
            result.add(musicList.get(1).getCount());
        }
        int[] answer = new int[result.size()];
        for(int y = 0; y < result.size(); y++) {
            answer[y] = result.get(y);
        }
        return answer;
    }
    
    // �����ϴ� �Լ�
    public static List<String> getListOfSortedMapKey(HashMap<String, Integer> map) {
        List<String> list = new ArrayList<String>();
        list.addAll(map.keySet());
        Collections.sort(list, new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
              int a1 = map.get(o1);
              int a2 = map.get(o2);
               // ����� Ƚ���� �ٸ��ٰ� �����Ƿ�
              if(a1 < a2)
                  return 1;
              else if(a1 == a2)
                  return 0;
              else
                  return -1;    // -1�� �� ������� ������Ű��! ��
          } 
        });
        // Collections.reverse(list);	// ���� ����
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