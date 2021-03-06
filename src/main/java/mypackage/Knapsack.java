package mypackage;

import org.apache.commons.math3.util.Pair;
import java.util.*;

public class Knapsack {
	static final int N = 4;
	static final int max_weight = 5;
	static List<Pair<Integer,Integer>> item_lst; // Items Information given Problem
	static List<List<Pair<HashSet<Integer>, Integer>>> ans; // Set: Included item, Integer: sum of each index
	
	public static void main(String[] args) {
		item_lst = new ArrayList<>(N+1);
		Pair<Integer,Integer> pair = null;
		item_lst.add(new Pair<Integer,Integer>(0, 0));
		for(int i = 1; i<=N; i++) {
			pair = new Pair<Integer,Integer>(i+1, i+2);
			item_lst.add(pair);
		}
//		for(Pair<Integer,Integer> item : item_lst) {
//			System.out.println("Item weight: " + item.getFirst() + ", " + "value: " + item.getSecond());
//		}
		
		knap_init();
//		System.out.println("Weight sum: " + w_sum);
//		System.out.println("value sum: " + v_sum);
		System.out.println(ans.get(N).get(max_weight).getFirst());
		System.out.println(ans.get(N).get(max_weight).getSecond());
	}
	
	private static void knap_init() {
		ans = new ArrayList<>(N+1);
		for(int i = 0; i<= N; i++) {
			ans.add(new ArrayList<>(max_weight+1));
			for(int j = 0; j <= max_weight; j++) {
				ans.get(i).add(new Pair<>(new HashSet<>(),0));
			}
		}
		knap();
	}
	private static void knap() {
		for(int item_index = 1; item_index<=N; item_index++) {
			for(int w_index = 1; w_index<=max_weight; w_index++) {
//				Pair<HashSet<Integer>, Integer> cur_field = ans.get(item_index).get(w_index);
				Pair<HashSet<Integer>, Integer> pre_field = ans.get(item_index-1).get(w_index);
				
				Pair<Integer, Integer> item_info = item_lst.get(item_index);
				int _weight = item_info.getFirst();
				int _value = item_info.getSecond();
//				System.out.println("_weight: " + _weight);
//				System.out.println("_value: " + _value);
//				System.out.println("w_index: " + w_index);
				if(_weight <= w_index) {
					//try
					try {
						int cur_new_int = _value + ans.get(item_index).get(w_index - _weight).getSecond();
//						System.out.println("cur_new_int: " + cur_new_int);
						if(pre_field.getSecond() < cur_new_int) {
							HashSet<Integer> set = (HashSet<Integer>) ans.get(item_index).get(w_index - _weight).getFirst().clone();
							set.add(item_index);
							ans.get(item_index).set(w_index, new Pair<>(set,cur_new_int));
						} else {
							ans.get(item_index).set(w_index, new Pair<HashSet<Integer>,Integer>((HashSet<Integer>) pre_field.getFirst().clone(), pre_field.getSecond()));
						}
					} catch(Exception e) { ans.get(item_index).set(w_index, new Pair<HashSet<Integer>,Integer>((HashSet<Integer>) pre_field.getFirst().clone(), pre_field.getSecond())); }
				} else {
					ans.get(item_index).set(w_index, new Pair<HashSet<Integer>,Integer>((HashSet<Integer>) pre_field.getFirst().clone(), pre_field.getSecond()));
				}
				Pair<HashSet<Integer>,Integer> temp = ans.get(item_index).get(w_index);
//				System.out.println(temp.getFirst() + ", " + temp.getSecond());
//				System.out.println();
			}
		}
	}
}
