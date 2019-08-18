package Test.chapter1.test;

import Test.chapter1.MyCollection;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * 测试MyCollection
 */
public class MyCollectionTest {
    private static MyCollection<Object> c;

    /**
     * 初始化容器 整个类只有一次
     */
    @BeforeClass
    public static void prepare() {
        c = new MyCollection<>();
    }

    /**
     * 清空容器 每次测试方法都有
     */
    @After
    public void finish() {
        c = new MyCollection<>();
    }

    /**
     * 测试集合为空判断
     */
    @Test
    public void testIsEmtpy() {
        assertTrue(c.isEmpty());
        c.insert(1);
        assertTrue(!c.isEmpty());
    }

    /**
     * 测试增加元素
     */
    @Test
    public void testInsert() {
        assertEquals(c.getSize(),0);
        c.insert(1);
        assertEquals(c.getSize(),1);
        assertTrue(c.get(c.getSize()-1) instanceof Integer);

        c.insert("5");
        assertEquals(c.getSize(),2);
        assertTrue(c.get(c.getSize()-1) instanceof String);
    }

    /**
     * 测试置空
     */
    @Test
    public void testMakeEmpty() {
        assertEquals(c.getSize(),0);
        c.insert(1);
        c.insert("5");
        assertEquals(c.getSize(),2);
        c.makeEmpty();
        assertEquals(c.getSize(),0);
    }

    /**
     * 测试移除元素
     */
    @Test
    public void testRemove() {
        //期望抛出越界异常
        try {
            c.remove(1);
        } catch (Exception e) {
            assertTrue(e instanceof  IndexOutOfBoundsException);
        }
        //期望size为0
        assertEquals(c.getSize(),0);
        c.insert(1);
        c.insert(1);
        //期望size为0
        assertEquals(c.getSize(),2);
        c.remove(0);
        assertEquals(c.getSize(),1);
    }

    /**
     * 测试是否包含元素
     */
    @Test
    public void testIspresent() {
        assertEquals(c.getSize(),0);
        assertTrue(!c.isPresent(1));
        c.insert(1);
        assertEquals(c.getSize(),1);
        assertTrue(c.isPresent(1));
        c.remove(0);
        assertTrue(!c.isPresent(1));
        assertEquals(c.getSize(),0);

    }
}
