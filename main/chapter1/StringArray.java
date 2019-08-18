package Test.chapter1;

import java.util.Arrays;

/**
 * 数据结构与算法第三版习题1.6
 *
 * 递归打印输入字符串的全排列
 *
 * 知识点：
 * JAVA值传递，调方法拷一份对象的地址或字面值到栈，各层方法的形参都指向同一个 数组
 * 每一层结束后回退一步，保证任意层的不同次触发 数组形态相同
 *
 *  排列 array A  n!/(n-m)!  字符不重复->n!     组合 combine C  n!/((n-m)!m!)
 *
 *  全排列 case length=3    3!=1*2*3=6
 *
 *  第一层方法的循环  low=0,i自增，把次循环把不同的字母作为首字母去递归
 *  //low表示递归层数  i表示当前待定位
 *
 *         固定A                          固定B                        固定C
 *           ABC            -→            ABC           -→            ABC
 *        i=0 low=0                    i=1 low=0                  i=2 low=0
 *        ↙     ↖                     ↙     ↖                   ↙     ↖
 *    ABC    →     ABC               BAC    →     BAC            CBA   →     CBA
 * i=1 low=1    i=2 low=1        i=1 low=1    i=2 low=1      i=1 low=1    i=2 low=1
 *     ↓↑           ↓↑               ↓↑           ↓↑             ↓↑           ↓↑
 *    ABC         ACB               BAC          BCA            CBA          CAB
 * i=2 low=2    i=2 low=2        i=2 low=2    i=2 low=2      i=2 low=2    i=2 low=2
 *     ↓↑           ↓↑               ↓↑           ↓↑             ↓↑           ↓↑
 *    ABC         ACB               BAC          BCA            CBA           CAB
 *   low=3       low=3             low=3        low=3          low=3         low=3
 */
public class StringArray {
    public static void main(String[] args) {
        StringArray sa = new StringArray();
        String[] task = {
                "advance"/*,"advance","candidate"*/
        };
        Arrays.asList(task).forEach(
            it -> sa.permute(it)
        );
    }

    private void permute(String str) {
        permute(str.toCharArray(), 0, str.toCharArray().length);
    }

    /**
     *
     * @param chars   待转换的字符数组
     * @param low     递归层数
     * @param high
     */
    private void permute(char[] chars, int low, int high) {
        if (low == high) {
            System.out.println(String.valueOf(chars));
        } else {
            for (int i = low; i < chars.length; i++) {
                this.swap(chars, low, i);
                //low表示递归层数  i表示当前待定位
                permute(chars, low + 1, chars.length);
                //回溯
                this.swap(chars, low, i);
            }
        }

    }

    private void swap(char[] chars, int low, int i) {
        char temp = chars[i];
        chars[i] = chars[low];
        chars[low] = temp;
    }
}