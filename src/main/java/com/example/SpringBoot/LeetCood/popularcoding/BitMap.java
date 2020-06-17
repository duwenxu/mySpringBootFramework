package com.example.springboot.leetcood.popularcoding;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;
import java.util.UUID;

/**
 * BitMap原理和实现
 *
 * 1. 问题引入：
 *  比较经典的问题是： 在只能够使用2G的内存中，如何完成以下操作：
 *  ①：对10亿个不重复的整数进行排序。
 *  ②：找出10亿个数字中重复的数字。
 *  无论是排序还是找重复的数字都需要将这10亿个数字加入到内存中在去进行操作，很明显，题目给出的2G内存限制说明了在这样的场景下是不能够将所有数都加入到内存中的
 *  1000000000*4/（1024*1024*1024） = 3.725G
 *  那么这时候就需要用到 BitMap结构了
 * 2. BitMap是什么?
 *  bitMap使用一个bit为0/1作为map的value来标记一个数字是否存在,而map的key值正是这个数字本身。
 *  相比于一般的数据结构需要用4个byte去存储数值本身，相当于是节省了 4*8：1 = 32倍的内存空间
 * 3. BitMap的实现：
 *  bitMap不一定要用bit数组,可以使用 int,long等等的基本数据类型实现，因为其实质都是在bit位上存数据，用哪种类型只是决定了最终实现出来的BitMap的内置数组中单个元素存放数据的多少
 *  例如：java中的BitSet使用Long数组
 *  BitMap的实现当然少不了位运算，先来明确几个常见位运算：
 *      any | 1 = 1
 *      any & 1 = any
 *      any | 0 = any
 *      ang & 0 = 0
 *  set(bitIndex): 添加操作实现
         *  1 .确定该数处于数组中的哪个元素的位上
 *              int wordIndex = bitIndex >> 5;
 *              因为我用的是int[]实现，所以这里右移 5 位（2^5 = 32）
         *  2 .确定相对于该元素中的位置偏移
 *              int bitPosition = bitIndex & ((1 << 5) - 1);
 *              这里相当于是 bitIndex % （1<<5）的取模运算，因为当取模运算的除数是2的次幂，所以可以使用以下的位运算来计算，提升效率
 *              （对比HashMap的容量为什么总是2的幂次方的问题，HashMap求下标时也是使用 hash&(n-1)）
 *              tips: 位运算的优先级是低于+,-等等的，所以要加上括号,防止发生不可描述的错误
 *          3 .将该位置1
 *              bits[wordIndex] |= 1 << bitPosition;
 *              相当于是将指定位置处的bit值置1，其他位置保持不变，也就是将以这个bitIndex为key的位置为1
 *              tips: 这里是参考了网上的各位大佬的文章,取余 + 按位或，又对比了下BitSet的源码：
 *                  words[wordIndex] |= (1L << bitIndex);
 *              没有取余操作，直接|，这两个一样吗？答案当然是一样的
 *              举个栗子：
 *                  1 << 148  == 1<< 20
 *                  1L << 125 ==1L<< 61
 *              即对于int和long型数据，直接左移其位数相当于是附带了对其取模操作
 * @create 2020-06-15 15:50
 */
public class BitMap {
    private final static int DEFINE_BIT_SIZE = 5;
    private final static int DEFINE_CAPACITY = 1 << DEFINE_BIT_SIZE;

    private int[] bits;
    private int capacity;

    public BitMap() {
        this.capacity = DEFINE_CAPACITY;
        initBits(DEFINE_CAPACITY);
    }

    public BitMap(int capacity) {
        this.capacity = capacity;
        initBits(capacity);
    }

    private void initBits(int nbs) {
        bits = new int[(nbs >> 5) + 1];
    }

    private void checkSize(int targetSize) {
        int requireSize = targetSize + 1;
        if (bits.length <= requireSize) {
            int require = Math.max(bits.length * 2, requireSize);
            bits = Arrays.copyOf(bits, require);
        }
    }

    private int getBitPosition(int bitIndex) {
        //注意： 移位运算的优先级要低于 +,-
        return bitIndex & ((1 << DEFINE_BIT_SIZE) - 1);
    }

    /**
     * 插入值
     * @param bitIndex
     */
    public void set(int bitIndex) {
        int wordIndex = bitIndex >> DEFINE_BIT_SIZE;
        checkSize(wordIndex);
        int bitPosition = getBitPosition(bitIndex);
        bits[wordIndex] |= 1 << bitPosition;
    }

    /**
     * 是否存在值
     * @param bitIndex
     * @return
     */
    public Boolean get(int bitIndex) {
        int wordIndex = bitIndex >> DEFINE_BIT_SIZE;
        int bitPosition = getBitPosition(bitIndex);
        return (bits.length >= wordIndex) && (bits[wordIndex] & 1 << bitPosition) != 0;
    }

    /**
     * 清除指定值
     * @param bitIndex
     * @return
     */
    public Boolean clear(int bitIndex){
        int wordIndex = bitIndex >> DEFINE_BIT_SIZE;
        int bitPosition = getBitPosition(bitIndex);
        bits[wordIndex] &= ~(1<<bitPosition);
        return !get(bitIndex);
    }


    public static void main(String[] args) {
//        BitMap bitMap = new BitMap();
//        TreeSet<Integer> dumpInt = new TreeSet<>();
//        Random random = new Random();
//        for (int i = 0; i < 200; i++) {
//            int nextInt = random.nextInt(200);
//            System.out.print(nextInt+" ");
//            if (!bitMap.get(nextInt)){
//                bitMap.set(nextInt);
//            }else {
//                dumpInt.add(nextInt);
//            }
//        }
//        System.out.println();
//        System.out.println(dumpInt.size());
//        System.out.println(dumpInt.toString());
//        System.out.println((1L<< 125) == (1L<<61));

        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }

}
