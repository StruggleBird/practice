/**
 * 
 */
package org.zt.test.arithmetic;

/**
 * String 全排列打印字符串
 * 
 * @author Ternence
 * @create 2015年3月30日
 */
public class StringFullPermutation {

    public static void main(String[] args) {
        permutation("abc");
    }


    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0)
            System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }
}
