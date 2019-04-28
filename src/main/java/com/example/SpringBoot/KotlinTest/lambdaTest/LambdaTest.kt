package com.example.SpringBoot.KotlinTest.lambdaTest

import org.apache.coyote.http11.Constants.a
import org.junit.Assert
import org.junit.Test

open class LambdaTest {
    @Test
    fun test() {
        val test = { println("canshu") }
        test()   //lambda表达式定义的量可以直接调用

        val test1 = { a: Int, b: Int -> a + b }
        print(test1(2, 4))
        print("\n")
        /**
         * it用法： 高阶函数中表达式的参数只有一个时可以用it来使用此参数；
         *          it表示对单个参数的隐式名称
         */
        //lambda example:
        fun test2(num: Int, bool: (Int) -> Boolean): Int {   //方法声明中lambda表达式不写大括号  参数名：表达式
            return if (bool(num)) {
                num
            } else 0
        }

        print("${test2(10, { it < 9 })}\n")
        print("${test2(1, { it < 9 })}\n")

        /**
         * 下划线：_   使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数
         *              主要使用于遍历一个Map集合时
         */
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        map.forEach { key, value -> println("$key \t $value") }
        map.forEach { _, value -> println("$value") }
    }

    /**
     * lambda高阶函数----sumBy()
     */
    @Test
    fun testSumBy() {
        val testStr = "abc"
        print("${testStr.sumBy { it.toInt() }}")
    }

    /**
     * ? 空测试
     */
    @Test
    fun nulltest() {
        var a: String? = "1"   //?.表示不为空时调用   ?：表示为空时返回
        // 希望为空时直接抛出异常则  !!.   如果a不为空，则返回a.length，如果b为空，则抛出异常NullPointerException
        // val b=a!!.length
        val b = a?.length ?: 5
        print(b)
    }

    val list = mutableListOf(1, 2, 3, 4)
    @Test
    fun testCountAndFold() {
//        Assert.assertEquals(5, list.count { it >= 2 })
        /**
         * fold 函数有两个参数：initial 和 operation。
        其中 initial 为初始值，operation 为一个高阶函数，这个函数有两个参数 R 和 T 并返回 R。
        fold 函数会在所有集合元素上调用 operation 函数，在第一次调用的时候， R 值为 initial， T 为第一个集合元素，返回值作为下次调用 operation 的 R 参数，而 下一个集合元素为下一次 operation 调用的 T 参数，依次来遍历集合。
         */
//        Assert.assertEquals(2880, list.fold(4) { total, next -> total * next }) //fold：计算集合累积值    720*4=2880
//        Assert.assertEquals(31,list.fold(10){sum,next->sum+next})
//        /**
//         * 和fold 类似，只不过是从集合中最后一个元素开始。
//         */
//        Assert.assertEquals(31,list.foldRight(10){sum,next->sum+next})
//        Assert.assertEquals(-11,list.foldRight(10){sum,next->sum-next})
        print(list.foldRight(10) { cut, next -> cut - next })

        /**
         * reduce 和 reduceRight
         *   没有初始值的 fold
         */
    }


}