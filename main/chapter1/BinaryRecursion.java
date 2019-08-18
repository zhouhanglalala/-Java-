package Test.chapter1;

/**
*  数据结构与算法第三版习题1.5
 *
 * 编写一种递归方法，它返回数N的二进制中表示1的个数。利用这样一个事实：N为奇数，其1的个数为N/2的二进制中1的个数加1.
 * 知识点：
 *  >> 右移 高位正补0负补1  >>> 无符号右移 高位补0   << 左移 低位补0
 *  奇数的二进制最后一位肯定是1，右移一位砍掉一个1则少一个1  即 N/2（理解位右移一位）的二进制中1的个数比N的少1个
 */
public class BinaryRecursion {
    public static void main(String[] args){
        int n=11;
        System.out.println(countOne(n));
        System.out.println(Integer.toBinaryString(n));
    }
    static int countOne(int n){
        if(n==0){
            return 0;
        }
        //11 5 2 1--3个奇数
        //模2即只保留对应二进制的最后一位
        if(n%2==1){
            return countOne(n>>1)+1;
        }else{
            return countOne(n>>1);
        }
    }
}
