package org.zt.test.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * @date 2018年01月09日
 * @author ternence
 *
 */
public class DifferentCompare {

	@Test
	public void testGetDifferent() {
		List<String> list2 = Arrays.asList(new String[] { "1", "2", "3" });
		List<String> list1 = Arrays.asList(new String[] { "2", "3", "4", "5" });
		System.out.println(JSON.toJSONString(getDifferent(list1, list2)));
		System.out.println(JSON.toJSONString(getDifferent(list2, list1)));
	}
	
	@Test
	public void testGetDifferent2() {
		List<String> list2 = Arrays.asList(new String[] { "1", "2", "3" });
		List<String> list1 = Arrays.asList(new String[] { "2", "3", "4", "5" });
		System.out.println(JSON.toJSONString(getDifferent2(list1, list2)));
		System.out.println(JSON.toJSONString(getDifferent2(list2, list1)));
	}

	/**
	 * 比较两个集合的差异，list1中多出的项和list2中多出的项整理成一个数组并返回 支持复杂类型 ，需重写equals方法。
	 * 
	 * @param list1
	 *            集合1
	 * @param list2
	 *            集合2
	 * @return 长度为2的数组，第一个元素返回list1中多出的项，第二个元素返回list2中多出的项
	 */
	public static <T> List<T>[] getDifferent(List<T> list1, List<T> list2) {
		List<T> leftDiff = new ArrayList<>();
		List<T> rightDiff = new ArrayList<>();

		Map<T, Integer> map = new HashMap<T, Integer>(list1.size());
		for (T t : list1) {
			map.put(t, 1);
		}
		for (T t : list2) {
			if (map.get(t) != null) {
				map.put(t, 2);
				continue;
			}
			rightDiff.add(t);
		}
		for (Map.Entry<T, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				leftDiff.add(entry.getKey());
			}
		}

		@SuppressWarnings("unchecked")
		List<T>[] diffArr = new List[2];
		diffArr[0] = leftDiff;
		diffArr[1] = rightDiff;
		return diffArr;

	}

	/**
	 * 比较两个集合，并返回差异项。不区分差异项来自于list1 还是 list2
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> getDifferent2(List<T> list1, List<T> list2) {
		List<T> diff = new ArrayList<>();
		List<T> maxList = list1;
		List<T> minList = list2;
		if (list1.size() < list2.size()) {
			maxList = list2;
			minList = list1;
		}

		Map<T, Integer> map = new HashMap<T, Integer>(maxList.size());
		for (T t : maxList) {
			map.put(t, 1);
		}
		for (T t : minList) {
			if (map.get(t) != null) {
				map.put(t, 2);
				continue;
			}
			diff.add(t);
		}
		for (Map.Entry<T, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				diff.add(entry.getKey());
			}
		}

		return diff;

	}

}
