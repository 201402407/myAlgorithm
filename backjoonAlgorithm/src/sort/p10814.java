package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p10814 {
	static List<Member> sort = new ArrayList<Member>();
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	    try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 1 || n > 100000)
				System.exit(0);
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int age = Integer.valueOf(st.nextToken());
				String name = st.nextToken();
				Member member = new Member(age, name);
				binaryInsertionSort(member);
			}
			sort.stream().forEach(member -> member.print());
		} 
	    catch (NumberFormatException e) {
	    	System.exit(0);
	    }
	    catch (IOException e) {
			System.exit(0);
		};
		
	}
	
	private static void insertionSort(int index, Member member) {		
		if(index == 0) {
			sort.add(member);
			return;
		}
		
		int j = sort.size() - 1;
		while(j >= 0 && sort.get(j).getAge() > member.getAge()) {
			j--;
		}
		sort.add(j + 1, member);
	}
	
	// ÀÌÁø »ðÀÔ Å½»ö
	private static void binaryInsertionSort(Member key) {
		if(sort.isEmpty()) {
			sort.add(key);
			return;
		}
		
		int mid;
		int left = 0;
		int right = sort.size();
		
		while(left < right) {
			mid = (left + right) / 2;
			Member compareMember = sort.get(mid);
			if(key.getAge() >= compareMember.getAge())
				left = mid + 1;
			else
				right = mid;
		}
		sort.add(right, key);
	}
}

class Member {
	private int age;
	private String name;
	
	Member(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void print() {
		System.out.println(this.age + " " + this.name);
	}
}
