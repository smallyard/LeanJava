package cn.smallyard.lean.java.current.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CompareAndSwap {
    private static Unsafe unsafe = null;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String str;

    public void test() throws NoSuchFieldException {
        // 获取字段的偏移量
        long strOffset = unsafe.objectFieldOffset(MutexLock.class.getDeclaredField("str"));

        System.out.println(str);
        boolean success = unsafe.compareAndSwapObject(this, strOffset, null, "aaa");
        System.out.println(success + " " + str);
        success = unsafe.compareAndSwapObject(this, strOffset, null, "bbb");
        System.out.println(success + " " + str);
        success = unsafe.compareAndSwapObject(this, strOffset, "aaa", "bbb");
        System.out.println(success + " " + str);
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new CompareAndSwap().test();
    }
}
