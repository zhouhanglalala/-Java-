package Test.chapter1;

import java.util.Arrays;

/**
 * 数据结构与算法第三版习题1.14
 *
 * 设计一个泛型类orderedCollection，它存储Comparable对象的集合（在数组中），
 * 以及该集合的当前大小。提供public方法isEmpty，makeEmpty，insert，remove，findMin和 findMax。
 * finfMin和findMax分别返回该集合中最小的和最大T对象的引用（如果该集合为空，则返回null）
 * @param <E>
 */
public class MyOrderedCollection<E extends Comparable<E>> {
    //属性私有 方法公开
    private Object[] objs;
    private int size;

    public enum FindTarget{
        MIN,MAX
    }
    /**
     *  需要初始化乘Comparable 要不没法比较了，Object没有compareto方法
     */
    public MyOrderedCollection() {
        size = 0;
        objs = new Comparable[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        size = 0;
        objs = new Comparable[0];
    }

    /**
     *
     */
    public void insert(E obj) {
        size++;
        objs = Arrays.copyOf(objs, size);
        objs[size-1] = obj;
    }


    /**
     * 删除当前容器指定下标的元素，并生成新的容器
     *
     * @param index 下标
     */
    public void remove(int index) {
        if (size <= index) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        size--;
        objs = Arrays.copyOf(objs, size--);
    }

    //通过排序找到最大最小值
    /*public E findMin() {
        sort((E[])objs);
        return (E)objs[0];
    }
    public E findMax() {
        sort((E[])objs);
        return (E)objs[objs.length-1];
    }*/


    public E findMin() {
        return findResult((E[])objs,FindTarget.MIN);
    }
    public E findMax() {
        return findResult((E[])objs,FindTarget.MAX);
    }

    /**
     * 一层遍历找最大最小值
     * @return
     */
    public E findResult(E[] arr,FindTarget target) {
        int targetIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean compareResult=false;
            if(target==FindTarget.MIN){
                //找最小 当前值比目标小 当前值作为目标值
                compareResult=arr[i].compareTo(arr[targetIndex]) < 0;
            }else if(target==FindTarget.MAX){
                //找最大 当前值比目标大 当前值作为目标值
                compareResult=arr[i].compareTo(arr[targetIndex]) > 0;
            }
            if (compareResult) {
                targetIndex = i;
            }
        }
        return arr[targetIndex];
    }
    /**
     * 插入排序 得到从小到大排序的数组
     * @param array 未排序数组
     */
    private E[] sort(E[] array) {
        for (int i = 0; i <array.length-1 ; i++) {
            //a.compareTo(b) a<b->-1 a=b->0 a>b->1, 确定当前的有序区，有序则不进入内层
            //前一个比后一个小或等于后一个则跳过当前轮次
            if(array[i+1].compareTo(array[i])!=-1){
                //只能用continue 不能用i++，最外层for里面已经有i++了，里面用i++会下标越界
                continue;
            }
            //第i+1个前面的全部和它去做比较
            for (int j=0; j <i+1 ; j++) {
                //如果第j个大于第i+1个
                if(array[j].compareTo(array[i+1])==1){
                    E temp=array[i+1];
                    //第j到第i个全部往后移动一位
                    for (int k = i+1; k >j ; k--) {
                        array[k]=array[k-1];
                    }
                    array[j]=temp;
                }
            }
        }
        return array;
    }
    public static void main(String[] args){
        //测试插入排序
        Integer[] arr={2,2,1,3,3,3,3,33};
        Arrays.asList(new MyOrderedCollection<Integer>().sort(arr)).forEach(
                it-> System.out.println(it)
        );
    }

    /**
     * 返回欲访问的下标和当前容器的容量
     *
     * @param index 下标
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }


    /**
     * 待查找对象在当前容器中的位置
     *
     * @param obj 待查找对象  O(n)
     * @return
     */
    public int indexOf(E obj) {
        boolean isNull = false;
        if (obj == null) isNull = true;
        if (isNull) {
            //找集合中第一个null值的下标
            for (int i = 0; i < size; i++)
                if (objs[i] == null)
                    return i;
        } else {
            //找集合中第一个obj的下标
            for (int i = 0; i < size; i++)
                if (obj.equals(objs[i]))
                    return i;
        }

        return -1;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public E get(int index) {
        return (E)objs[index--];
    }
}
