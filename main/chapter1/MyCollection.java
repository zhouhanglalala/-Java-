package Test.chapter1;

import java.util.Arrays;

/**
 * 数据结构与算法第三版习题1.13
 *
 * 要求：设计一个泛型类Collection，它存储object对象的集合（在数组中），
 * 以及该集合当前的大小。提供public方法isEmtpy，makeEmpty,insert,remove,isPresent.
 * 方法isPresent(x)当且仅当在集合中存在（由equals定义） 等于x的一个object时返回true
 */
public class MyCollection<E> {
    //属性私有 方法公开
    private Object[] objs;
    private int size;

    public MyCollection() {
        size = 0;
        objs = new Object[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        size = 0;
        objs = new Object[0];
    }

    /**
     * 增加元素
     */
    public void insert(E obj) {
        size++;
        objs = Arrays.copyOf(objs, size);
        objs[size - 1] = obj;
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
     * 删除当前容器指定下标的元素，并生成新的容器
     *
     * @param index 下标
     */
    public void remove(int index) {
        if (size <= index) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        size--;
        objs = Arrays.copyOf(objs, size);
    }

    /**
     * 待查找对象是否存在于当前容器
     *
     * @param obj 待查找对象  O(n)
     * @return
     */
    public boolean isPresent(E obj) {
        return indexOf(obj) >= 0;
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
