package Test.chapter1.test;

import Test.chapter1.MyOrderedCollection;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.testng.Assert.assertEquals;

/**
 * 本类只测试findMax和findMin 其他方法在TestMyCollection中测试过了
 */
public class MyOrderedCollectionTest {
    private static MyOrderedCollection<Integer> c;

    /**
     * 初始化容器 整个类只有一次
     */
    @BeforeClass
    public static void prepare() {
        c = new MyOrderedCollection<>();
    }

    /**
     * 清空容器 每次测试方法都跑
     */
    @After
    public void finish() {
        c = new MyOrderedCollection<>();
    }

    /**
     * 测试找最小值 最大值
     */
    @Test
    public void testFindMax() {
        c.insert(1);
        c.insert(3);
        c.insert(1);
        c.insert(9);
        c.insert(1);
        c.insert(81);
        assertEquals((int)c.findMax(), 81);
        assertEquals((int)c.findMin(), 1);
    }

}
