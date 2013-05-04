
package test.arithmetic;

import java.util.Arrays;

/**
 * 折半查找法，又称二分法查找  查找的对象是一个按序排好的数组 
 * @author zt
 *
 */
public class Dichotomy
{
    /**
     * 循环查找目标值在数组中的索引
     * @param initVals  要进行查找的数组
     * @param targetVal 目标值
     * @return 目标值在数组中的索引 未找到时返回-1
     */
    public static int query(int[] initVals, int targetVal)
    {
        int startP = 0;
        int endP = initVals.length;
        int middleIndex = 0;
        while (true)
        {
            middleIndex = (startP + endP) / 2;
            if (initVals[middleIndex] == targetVal)
            {
                //找到了元素 直接返回
                return middleIndex;
            }
            else if (initVals[middleIndex] > targetVal)
            {
                //中间的数比要找的大，要向前找
                endP = middleIndex;
            }
            else
            {
                //中间的数比要找的小，要向后找
                startP = middleIndex;
            }

            //没有找到
            if (middleIndex == 0 || middleIndex == initVals.length - 1)
            {
                return -1;
            }
        }
    }

    /**
     * 通过递归的方法查找目标值在数组中的索引（目前还没有找到更好的递归方法）
     * @param initVals 要进行查找的数组
     * @param targetVal 目标值
     * @param starP 当前锁定的起始点
     * @param endP 当前锁定的终点
     * @return 目标值在数组中的索引 未找到时返回-1
     */
    public static int query(int[] initVals, int targetVal, int starP, int endP)
    {
        int middleIndex = (starP + endP) / 2;

        //判断 如果中间索引到达当前数组的最前端或最后端，如果不和目标数相等，则标记为未找到并返回
        if (middleIndex == 0 || middleIndex == initVals.length - 1)
        {
            if (initVals[middleIndex] == targetVal)
            {
                return middleIndex;
            }
            return -1;
        }

        if (initVals[middleIndex] < targetVal)
        {
            //向后查找
            return query(initVals, targetVal, middleIndex, endP);
        }
        else if (initVals[middleIndex] > targetVal)
        {
            //向前查找
            return query(initVals, targetVal, starP, middleIndex);
        }
        else
        {
            return middleIndex;
        }
    }

    public static void main(String[] args)
    {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1, -5};
        //排个序先
        Arrays.sort(array);
        //查找-1在数组中的索引
        System.out.println(query(array, -1));
        //查找10 在数组中的索引  后面两个参数固定
        System.out.println(query(array, 11, 0, array.length));
    }
}
