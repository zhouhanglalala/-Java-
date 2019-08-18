package Test.chapter1;

import java.util.Comparator;

/**
 * 数据结构与算法第三版习题1.15
 * <p>
 * 定义一个Rectangle类，该类提供getLength和getWidth方法。
 * 主方法中创建一个Rectangle数组并首先找出面积最大的Rectangle对象，
 * 然后找出周长最大的Rectangle对象。
 */
public class Rectangle {
    private int length;
    private int width;

    /**
     * 枚举比较的维度 面积/周长
     */
    public enum Dimension {
        AREA, PERIMETER
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public static <T> T findMax(T[] arr, Comparator<? super T> cmp) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    static class MyComparator implements Comparator<Rectangle> {
        /**
         * @param lhs  left hand side
         * @param rhs  right hand side
         * @return
         */
        private Dimension dimen;

        public MyComparator(Dimension dimen) {
            this.dimen = dimen;
        }

        public int compare(Rectangle lhs, Rectangle rhs) {
            if (dimen == Dimension.AREA)//{
                return Integer.compare(lhs.getLength() * lhs.getWidth(), rhs.getLength() * rhs.getWidth());
            // } else if (dimen == Dimension.PERIMETER) {
            return Integer.compare(2 * (lhs.getLength() + lhs.getWidth()), 2 * (rhs.getLength() * rhs.getWidth()));
        }

    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        Rectangle[] rts = new Rectangle[]{
                new Rectangle(3, 7),
                new Rectangle(4, 6),
                new Rectangle(5, 5)
        };
        System.out.println("最大面积 "+findMax(rts, new MyComparator(Dimension.AREA)).toString());
        System.out.println("最大周长 "+findMax(rts, new MyComparator(Dimension.PERIMETER)).toString());
    }

    @Override
    public String toString() {
        return "长：" + length + " " + "宽：" + width;
    }
}
