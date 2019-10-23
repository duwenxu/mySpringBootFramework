package com.example.springboot.leetcood.tencent;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

/**
 * 有效的括号
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 栈的应用之一： 表达式的括号检验
 * 思路：
 * 1. 初始化栈
 * 2. 左括号入栈，最后入栈的括号将置于栈顶
 * 3. 当遇到右括号时，查看栈顶此时是否是对应的左括号；若是，弹栈；不是，直接return false
 * 4. 直到目标队列为空时，查看当前栈内，栈为空，return true；栈不为空，return false
 *
 * @author duwenxu
 * @create 2019-09-25 15:49
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "()";
//        System.out.println(isValid(s));
//        System.out.println(isValid1(s));
        System.out.println(isValid2(s));
    }

    private static boolean isValid(String s) {
        if (s.length()%2==1) return false;
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.keySet().contains(s.charAt(i))) {
                if (!stack.isEmpty()) {
                    if (stack.pop() != map.get(s.charAt(i))) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
                if (stack.size()>s.length()/2){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * 使用 ArrayDeque 实现
     *  ArrayDeque：
     *          ArrayDeque实现了Deque是一个动态数组。
     *          ArrayDeque没有容量限制，容量会在使用时按需扩展。
     *          ArrayDeque不是线程安全的，前面一篇文章介绍Queue时提到的Java原生实现的 Stack是线程安全的，所以它的性能比Stack好
     *          禁止空元素
     *          ArrayDeque当作为栈使用时比Stack快，当作为队列使用时比LinkedList快。
     *          public class ArrayDeque<E> extends AbstractCollection<E>
     *                            implements Deque<E>, Cloneable, Serializable
     *          所以ArrayDeque既可以作为队列（包括双端队列xxxFirst,xxxLast），也可以作为栈（pop/push/peek）使用，而且它的效率也是非常高
     *
     * ArrayDeque应用场景以及总结：
     * 正如jdk源码中说的“ArrayDeque当作为栈使用时比Stack快，当作为队列使用时比LinkedList快。” 所以，当我们需要使用栈这种数据结构时，优先选择ArrayDeque，不要选择Stack。如果作为队列操作首位两端我们应该优先选用ArrayDeque。如果需要根据索引进行操作那我们就选择LinkedList.
     * ArrayDeque是一个双端队列，也是一个栈。
     * 内部数据结构是一个动态的循环数组，head为头指针，tail为尾指针
     * 内部elements数组的长度总是2的幂（目的是为了支持位与计算，以此得到下一个元素的位置）
     * 由于tail始终指向下一个将被添加元素的位置，所以容量大小至少比已插入元素多一个长度。
     * 内部是一个动态的循环数组，长度是动态扩展的，所以会有额外的内存分配，以及数组复制开销。
     *
     * @param s
     * @return
     */
    private static boolean isValid1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] arr = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '{') {
                stack.offerFirst('}');
            } else if (arr[i] == '(') {
                stack.offerFirst(')');
            } else if (arr[i] == '[') {
                stack.offerFirst(']');
            } else if (stack.isEmpty() || stack.pollFirst() != arr[i]) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 采用先构造栈再比较的方式
     * @param s
     * @return
     */
    private static boolean isValid2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '{') {
                stack.push('}');
            } else if (arr[i] == '(') {
                stack.push(')');
            } else if (arr[i] == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != arr[i]) {
                return false;
            }
        }
        return stack.isEmpty();
    }


}
