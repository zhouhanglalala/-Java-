package Test.chapter1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * 数据结构与算法第三版习题1.3
 *
 * 打印任意大小的double值 可以是负数
 * 用处理I/O的 printDigit方法打印（一位一位打印）
 */
public class DigitPrint {
    public static void main(String[] args) {
        Random rand = new Random();
        BigDecimal[] task = new BigDecimal[1];
        int min=-10000;
        int max=10000;
        //生成任务数组
        for (int i = 0; i <task.length ; i++) {
            //随机数介于 [min,max)
            task[i]=new BigDecimal((min+rand.nextDouble()*(max-min))*Math.pow(10,i));
            System.out.println(task[i]);
//            new PrintDigit().printDigitByiterate(task[i]);
            String numberString= String.valueOf(task[i]);
            String integerString=null;
            String decimalString=null;
            if(task[i].doubleValue()<0){
                integerString=numberString.substring(1,numberString.indexOf("."));
            }else{
                integerString=numberString.substring(0,numberString.indexOf("."));
            }
            decimalString=numberString.substring(numberString.indexOf(".")+1);
            System.out.println(integerString);
            System.out.println(decimalString);
            new DigitPrint().printDigitByrecursion(new BigInteger(decimalString));
            new DigitPrint().printDigitByrecursion(new BigInteger(integerString));
        }


    }

    /**
     * 转换成String然后循环打印
     * @param number
     */
    public void printDigitByiterate(BigDecimal number){
        String numberString= String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            if(!(numberString.toCharArray()[i]=='-')&&!(numberString.toCharArray()[i]=='.')){
                System.out.println(numberString.toCharArray()[i]);
            }
        }
    }

    /**
     * 转换成string然后递归打印数位
     * @param number
     */
    public void printDigitByrecursion(BigInteger number){
        BigInteger radix=new BigInteger("10");
        System.out.println(number.mod(radix));
        if(number.compareTo(radix)>=0){
            printDigitByrecursion(number.divide(radix));
        }
    }

}
