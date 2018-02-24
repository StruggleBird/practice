package org.zt.test.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 现在有一批大小不一（0-100）的沙袋要装箱运走，箱子的大小为100， 要求： 每个车厢为封闭箱，不能超出箱子的容量，
 * 尽量将每个箱子装满，以尽量少的箱子装下这些沙袋。
 * 
 * @author potm iteye 面试算法题
 */
public class SandToBox {

	private static int weight = 0; // 沙袋的总重量
	private static List train = new ArrayList();

	public static void main(String[] args) {
		// 沙袋
		int[] nums = new int[] { 50, 60, 70, 80, 40, 20, 28, 36, 27, 46, 55,
				58, 12, 15 };
		// int[] nums = new int[] { 5, 6, 7, 8, 4, 2, 2, 3, 2, 4, 5, 5, 1, 100
		// };
		// int[] nums = new int[]{1,2,4,96,96};

		List bagList = arrayToList(nums);
		System.out.println("沙袋的总重量为：" + weight); // 可根据沙袋的总重量来估计最少需要多少箱子
		sandToBox(bagList);
		System.out.println();
		System.out.println("至少需要装：" + train.size() + "箱");

	}

	// 返回排序后的list
	public static List arrayToList(int[] nums) {
		List bagList = new ArrayList();
		for (int i = 0; i < nums.length; i++) {
			weight += nums[i];
			bagList.add(new Integer(nums[i]));
		}
		Collections.sort(bagList);
		// for(int i = 0;i < nums.length; i++){
		// System.out.println(bagList.get(i));
		// }
		return bagList;
	}

	// 计算应该将哪些沙袋装到箱子里面，并且装入
	public static void sandToBox(List bagList) {
		int min = ((Integer) bagList.get(0)).intValue(); // 当前沙袋的最小重量
		int listLen = bagList.size();
		int max = ((Integer) bagList.get(listLen - 1)).intValue(); // 当前沙袋的最大重量
		final int tankSize = 100;

		System.out.print(max + " + ");
		for (int i = 0; i < listLen; i++) {
			System.out.print(((Integer) bagList.get(0)).intValue() + " + ");
			max += ((Integer) bagList.get(0)).intValue();
			if (max > tankSize) {
				max = max - ((Integer) bagList.get(0)).intValue();
				train.add(new Integer(max)); // 装箱
				bagList.remove(listLen - 1); // 每次装箱后至少要移走最大的沙袋
				if (listLen >= 2) {
					max = ((Integer) bagList.get(listLen - 2)).intValue();
					System.out.println();
					System.out.print(max + " + ");
				}
				i = 0;
			} else {
				i = 0;
				bagList.remove(0); // 动态的缩小 bagList
				listLen = bagList.size();
			}
		}
		if (max <= tankSize) {
			train.add(new Integer(max)); // 装箱
		}
	}

}
