package test.arithmetic;

//现在有一批大小不一（0-100）的沙袋要装箱运走，箱子的大小为100，要求： 
//每个车厢为封闭箱，不能超出箱子的容量， 
//尽量将每个箱子装满，以尽量少的箱子装下这些沙袋。 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arithmetic1 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// 沙袋
		// int[] nums = new int[] { 50, 60, 70, 80, 40, 20, 28, 36, 27, 46, 55,
		// 58, 12, 15 };

		// int[] nums = new int[] { 5, 6, 7, 8, 4, 2, 2, 3, 2, 4, 5, 5, 1, 100
		// };
		// int[] nums = new int[]{1,2,4,96,96};
		int[] nums = new int[] { 99, 90, 80, 20, 20, 10, 1 };
		// 沙袋排序
		ArrayList bagList = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			bagList.add(nums[i]);
		}
		Collections.sort(bagList);
		// 装箱子的火车
		ArrayList<ArrayList> train = new ArrayList<ArrayList>();
		// 箱子的容积
		int tankSize = 100;
		// 装箱
		while (bagList.size() > 0) {
			// 取出一个箱子
			ArrayList tank = new ArrayList();
			// TODO 请填写代码完成装箱..........
			// answer1(bagList, tankSize, tank);
			answer2(bagList, tankSize, tank);
			// 装车
			train.add(tank);
		}
		System.out.println(train.size());
	}

	private static void answer1(ArrayList<Integer> bagList, int tankSize,
			ArrayList tank) {
		tank.add(bagList.get(bagList.size() - 1));
		for (int i = 0; (i < bagList.size() - 1); i++) {
			if ((tankSize(tank) + bagList.get(i)) <= tankSize) {
				tank.add(bagList.get(i));
			}
		}

		bagList.removeAll(tank);
		printTank(tank);
	}

	private static void answer2(ArrayList<Integer> bagList, int tankSize,
			ArrayList tank) {
		tank.add(bagList.get(bagList.size() - 1));
		for (int i = bagList.size() - 2; i >= 0; i--) {
			if ((tankSize(tank) + bagList.get(i)) <= tankSize) {
				tank.add(bagList.get(i));
			}
		}

		bagList.removeAll(tank);
		printTank(tank);
	}

	private static void printTank(ArrayList<Integer> tank) {
		for (Integer integer : tank) {
			System.out.print(integer + "+");

		}
		System.out.println();
	}

	static int tankSize(List<Integer> tank) {
		int size = 0;
		for (Integer item : tank) {
			size += item;
		}
		return size;
	}
}
