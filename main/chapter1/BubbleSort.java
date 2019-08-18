package Test.chapter1;

import java.util.Random;

/**
 *  数据结构与算法第三版习题1.1
 *
 *  编写一个程序解决选择问题 令k=N/2 从第N个数据中按升或者降序选择第K个数据
 *  @author zh
 *  
 *  
 */
public class BubbleSort {
    /**
     * 创建随机数组
     */
    public static final Random RANDOM = new Random();

    public int[] getRandArray(int length) {
        int array[] = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM.nextInt(length * 2);
        }
        return array;
    }

    public int[] sort(int[] randArray) {
        //是否进入了内层
        boolean flag;
        //i-1轮 最后一个不用轮询
        for (int i = 0; i < randArray.length-1; i++) {
            flag=false;
            //已经有i个有序了
            for (int j = 0; j <randArray.length-1-i ; j++) {
                if (randArray[j] > randArray[j + 1]) {
                    int temp = randArray[j];
                    randArray[j] = randArray[j + 1];
                    randArray[j + 1] = temp;
                    flag=true;
                }
            }
            //没进入内层表示已经排序好了，优化初始数组已有值有序的情况
            if(!flag){
                break;
            }
        }
        for (int i = 0; i <randArray.length ; i++) {
            if(i<randArray.length-1){
                System.out.print(randArray[i]+",");
            }else{
                System.out.print(randArray[i]);
            }
        }
        System.out.println("");
        return randArray;
    }

    public int select(int[] randArray) {
        int k;
        //解决数组长度的奇偶问题
        if(randArray.length%2==0){
            k=randArray.length / 2-1;
        }else{
            k=(randArray.length-1) / 2;
        }

        System.out.println("第k个值的下标" + k);
        randArray = this.sort(randArray);
        return randArray[k];
    }

    public static void main(String[] args) {
        int[] task=new int[]{10};
        for (int i = 0; i <task.length ; i++) {
            BubbleSort s = new BubbleSort();
            int[] randArray = s.getRandArray(task[i]);
            long start=System.nanoTime();
            int kValue = s.select(randArray);
            long end=System.nanoTime();
            System.out.println("第K个值："+kValue);
            System.out.println("耗时："+(end-start)/1000+"ms");
            System.out.println("-------------------------------------");
        }
   }
}

