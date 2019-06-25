package com.hobart.data.stack;

/**
 * 计算器  中缀表达式
 * <p>
 * 验证： 3+2*6-2 = 13
 * <p>
 * 使用栈完成表达式的计算 思路
 * 1. 通过一个 index  值（索引），来遍历我们的表达式
 * 2. 如果我们发现是一个数字, 就直接入数栈
 * 3. 如果发现扫描到是一个符号,  就分如下情况
 * 3.1 如果发现当前的符号栈为 空，就直接入栈
 * 3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
 * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
 * 5. 最后在数栈只有一个数字，就是表达式的结果
 */
public class Calculator {

    public static void main(String[] args) {
        //根据以上分析计算表达式
        String expression ="30+20*8-4";//如何解决多位数的问题
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(20);
        ArrayStack2 operStack = new ArrayStack2(20);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;//操作符
        int res = 0;//计算的中间结果
        char ch=' ';//将每次扫描得到char保存到ch
        String keepNum="";
        //开始while循环的扫描expression
        while (true){
            //依次得到expression的每个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后作相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,
                    // 在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.cal(num1, num2, operStack.pop());
                        //把运算的结果入数栈
                        numStack.push(res);
                        //把当前的运算符入符号栈
                        operStack.push(ch);
                    }else{
                        // 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈. 
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入符号栈
                    operStack.push(ch);// 1 + 3
                }
            }else {
                //如果是数,则直接如数栈
                //numStack.push(ch - 48);//因为当前是字符，数的ascii码-48 1+3 =》 '1'-48
                
                //分析思路
                //1、当处理多位数时，不能发现是一个数就立即入栈，因为它可能多位数
                //2、在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3、因此我们需要定义一个变量字符串，用入拼接
                
                //处理多位数
                keepNum += ch;
                
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看最后一位，不是index++
                    if (operStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                        //如果后一位是运算符，则入栈keepNum="1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        
        //当所有表达式扫描完成
        //就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
        while (true){
            //如果符号栈为空，数栈就是剩下为最后的计算结果
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = numStack.cal(num1, num2, operStack.pop());
            numStack.push(res);
        }
        //将数栈最后的数pop出，就是结果
        int res2= numStack.pop();
        System.out.printf("表达式: %s = %d",expression,res2);
    }

    //数组模拟栈
    private static class ArrayStack2 {
        private int maxSize;//栈的大小
        private int[] array;
        private int top = -1;

        public ArrayStack2(int maxSize) {
            this.maxSize = maxSize;
            array = new int[this.maxSize];
        }

        //栈是否满
        public boolean isFull() {
            return top == maxSize - 1;
        }

        //栈是否为空
        public boolean isEmpty() {
            return top == -1;
        }

        //查看栈顶的元素，但是出栈
        public int peek() {
            if (isEmpty()){
                throw new RuntimeException("栈为空,不能查看栈顶");
            }
            return array[top];
        }

        //压栈
        public void push(int value) {
            if (isFull()) {
                System.err.println("栈已满,不能压入数据");
                return;
            }
            top++;
            array[top] = value;
        }

        //出栈
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈为空,不能出栈");
            }
            int value = array[top];
            top--;
            return value;
        }

        //遍历栈，从栈顶往栈底遍历
        public void show() {
            if (isEmpty()) {
                System.err.println("栈为空,没有数据");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d\n", i, array[i]);
            }
        }
        
        //返回运算符的优先级，优先级是程序员确定，优先级由数字表示
        //数字越大，则优先级越高
        public int priority(int oper){
            if (oper == '*' || oper == '/'){
                return 1;
            }else if (oper == '+' || oper == '-'){
                return 0;
            }else{
                return -1;//假定目前表达式只有+,-,*,/
            }
        }
        //判断是不是一个运算符
        public boolean isOper(char val){
            return val == '+' || val =='-' || val == '*' || val == '/';
        }
        //计算方法
        public int cal(int num1,int num2,int oper){
            int res = 0;//res 用于存放计算结果
            switch (oper){
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2 / num1;
                    break;
                default:
                    break;
            }
            return res;
        }
    }
}
