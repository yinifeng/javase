package com.hobart.data.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算器 后缀表达式  逆波兰表达式
 * 
 * 中缀表达式(平常阅读的就是)：(30+4)*5-6
 * 
 * 后缀表达式:30 4 + 5 * 6 -
 * 
 * 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
 * 
 * 
 * 计算器目前只支持整数计算
 */
public class PolandNotation {

    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(30+4)*5-6 => 30 4 + 5 * 6 - =>164
        //测试
        //说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1、先将"30 4 + 5 * 6 -" =>放到ArrayList中
        //2、将ArrayList传一个方法，遍历ArrayList配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("list="+list);
        int res = calculate(list);
        System.out.println("计算结果是="+res);
    }

    /**
     * 完成对逆波兰表达式的计算
     * 
     * 逆波兰表达式：30 4 + 5 * 6 -
     * 
     * 1、从左至右扫描，将30和4压入堆栈
     * 2、遇到+运算符，因此弹出4和30(4为栈顶元素,30为次顶元素),计算出30+4的值，得34，再将34入栈
     * 3、将5入栈
     * 4、接下来是*运算符，因此弹出5和34，计算34*5=170，将170入栈
     * 5、将6入栈
     * 6、最后是-运算符，计算出170-6=164，由此得到最终结果
     * 
     * @param list
     * @return
     */
    private static int calculate(List<String> list) {
        //创建栈，保存数
        Stack<String> stack = new Stack<>();
        for (String item:list){
            //这里正则表达式来取数
            if (item.matches("\\d+")){
                //可以匹配多位整数
                //入栈
                stack.push(item);
            }else{
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*") || item.equals("x")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("非法操作符");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
    
    //将逆波兰表达式，依次将数据和运算符 放到ArrayList中
    private static List<String> getListString(String suffixExpression) {
        //先将表达式分隔成数组
        String[] split = suffixExpression.split(" ");
        List<String> list =new ArrayList<>();
        for (String ele: split){
            list.add(ele);
        }
        return list;
    }
}
