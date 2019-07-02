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
        List<String> stringList = toInfixExpressionList("1+((2+3)*4)-5");
        System.out.println("中缀表达式="+stringList);
        //中缀表达式的集合 =>  后缀表达式（逆波兰表达式）的集合
        List<String> suffixExpreesionList = parseSuffixExpreesionList(stringList);
        System.out.println("后缀表达式="+suffixExpreesionList);
        System.out.printf("转换后缀表达式计算结果=%d\n",calculate(suffixExpreesionList));

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>");
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

    //中缀表达式的集合 =>  后缀表达式（逆波兰表达式）的集合
    public static List<String> parseSuffixExpreesionList(List<String> list){
        //定义两个栈
        Stack<String> s1=new Stack<>();//符号栈
        //说明：因为s2这个栈在pop的过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String> s2
        //Stack<String> s2 = new Stack<String>(); //存储中间结果的栈s2
        List<String> s2=new ArrayList<>();//存储中间结果
        //遍历list
        for (String item: list){
            //如果是一个数直接加入
            if (item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是")"依次弹出s1中的运算符，并压入到s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//消除"("
            }else{
                //当item的优先级小于等于s1栈顶运算符，
                // 将s1栈顶的运算符弹出并加入到s2中，再次转到（4.1）与s1新的栈顶做比较
                //问题我们缺少一个比较有限级高低的方法
                while (s1.size() != 0 && OperationEnum.getOperationValue(s1.peek()) 
                        >= OperationEnum.getOperationValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);//还需要将item压入到s1
            }
        }
        
        //处理剩下栈中的元素 ，加入到s2中
         while (s1.size() > 0){
             s2.add(s1.pop());
         }
        return s2;
    }
    
    
    private static enum OperationEnum{
        ADD("+",1),
        SUB("-",1),
        MUL("*",2),
        MUL2("x",2),
        DIV("/",2);

        private String type;
        
        private int value;

        OperationEnum(String type,int value){
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
        
        public static int getOperationValue(String type){
            for (OperationEnum enm:OperationEnum.values()){
                if (enm.getType().equals(type)){
                    return enm.getValue();
                }
            }
            //throw new RuntimeException("非法运算符"+type);
            System.err.println("运算符有误："+type);
            return 0;
        }
    }
    
    //将中缀表达式转成对应的List
    // s="1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        int index = 0;//这是一个指针用于遍历中缀表达式的字符串
        String str;//对多位数的拼接
        char c;//遍历到没个字符，就放入到c
        do{
            if ((c =s.charAt(index)) < 48 || (c =s.charAt(index)) > 57) {//非数字
                list.add(String.valueOf(c));
                index++;
            }else{
                str = "";
                //数字，考虑多位数问题
                while (index < s.length() && (c =s.charAt(index)) >= 48 && (c =s.charAt(index)) <=57){
                    str += c;
                    index ++;
                }
                list.add(str);
            }
        }while (index < s.length());
        return list;
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
