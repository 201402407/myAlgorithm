package cpcContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 2019.09.28
// �߾Ӵ��б� ���α׷��� ������ȸ(CPC) ���� ���׽�Ʈ
public class pB {
	static HashMap<String, Integer> normalMenu = new HashMap<String, Integer>();
	static HashMap<String, Integer> specialMenu = new HashMap<String, Integer>();
	static HashMap<String, Integer> serviceMenu = new HashMap<String, Integer>();
//	static List<String> serviceMenu = new ArrayList<String>();
	static int[] price;
	static boolean canSpecial = false;
	static boolean canService = false;
	static boolean checkSpecialMenu = false;	// ����� �޴��� �ֹ��� ���ԵǾ� �ִ��� Ȯ��
	static boolean checkServiceMenu = false;	// ���� �޴��� �ֹ��� ���ԵǾ� �ִ��� Ȯ��
	static int sumPrice, a, b, c;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.valueOf(st.nextToken());
			b = Integer.valueOf(st.nextToken());
			c = Integer.valueOf(st.nextToken());
			price = new int[a + b + c];
			int priceIndex = 0;
			for(int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				normalMenu.put(st.nextToken(), Integer.valueOf(st.nextToken()));
			}
			
			for(int i = 0; i < b; i++) {
				st = new StringTokenizer(br.readLine());
				specialMenu.put(st.nextToken(), Integer.valueOf(st.nextToken()));
			}
			
			for(int i = 0; i < c; i++) {
				st = new StringTokenizer(br.readLine());
				serviceMenu.put(st.nextToken(), 0);
			}
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(checkOrder(n, br))
				System.out.println("Okay");
			else
				System.out.println("No");
		} 
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static boolean checkOrder(int n, BufferedReader br) throws IOException {
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String food = st.nextToken();
			if(serviceMenu.containsKey(food)) {
				if(checkServiceMenu)
					return false;
				checkServiceMenu = true;
				continue;
			}
			
			if(sumPrice >= 50000)
				continue;
			
			Integer price = normalMenu.get(food);
			if(price == null) {
				Integer specialPrice = specialMenu.get(food);
				if(specialPrice == null) {
					return false;
				}
				checkSpecialMenu = true;
				sumPrice += specialPrice;
			}
			else {
				sumPrice += price;
			}
		}
		
		checkMenu();
		
		if(canService) {
			return true;
		}
		if(canSpecial) {
			if(checkServiceMenu) {	// ���� �޴� ������ ����
				return false;
			}
			return true;
		}
		if(checkSpecialMenu || checkServiceMenu) {	// ����� ���� �ȵǴµ� ������̳� ���� �޴��� ���Ե� ���
			return false;
		}
		
		return true;
	}
	
	private static void checkMenu() {
		if(sumPrice >= 50000) {
			canService = true;
			return;
		}
		if(sumPrice >= 20000) {
			canSpecial = true;
		}
	}
}
