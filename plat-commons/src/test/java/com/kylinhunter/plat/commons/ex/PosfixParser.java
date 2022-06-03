package com.kylinhunter.plat.commons.ex;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-01 16:28
 **/
public class PosfixParser {
    public static void main(String[] args){

        String express = "1-5+3-4+70*2*2-5";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack charStack = new ArrayStack(10);

        int index = 0;//遍历所需索引
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch;//将每次拿到的符号放在ch中
        String keepNum = "";//用于拼接数字。这里会涉及到多位数
        //遍历输入的字符串
        while (true){
            //依次取到字符串的每个字符
            ch = express.substring(index,index+1).charAt(0);
            System.out.println(ch);
            //判断ch是什么
            if(charStack.isOper(ch)){
                //判断符号栈中是否有符号在栈
                if (!charStack.isEmpty()){
                    //如果栈中有符号，则需要比较栈中的操作符的优先级与当前优先级，若当前操作符的优先级小于或等于栈中的，则需从数栈中pop出两个数值，将符号栈中的符号出栈进行计算
                    if (charStack.priority(ch) <= charStack.priority(charStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = charStack.pop();
                        res = numStack.calRes(num1,num2,oper);
                        //将res从新如数栈
                        numStack.push(res);
                        //将当前的符号入符号栈
                        charStack.push(ch);
                    }else{
                        //若当前的符号的优先级要高于栈中的优先级，则直接入栈
                        charStack.push(ch);
                    }
                }else{
                    //前面判断栈不为空，这里就是栈空，直接将数据人放入栈中
                    charStack.push(ch);
                }
            }else{
                //这里前面是判断是字符，这里else就是数字了
                //数字怎么处理？？直接入栈即可
                //numStack.push(ch-48);这里是ascii表示，48是0，这里用ch-48恰好就是其自己的10进制值
                //numStack.push(ch);

                //分析处理多位数怎么办？？
                /**
                 * 1. 不能发现一个数就直接入栈
                 * 2. 判断表达式的后一位是否是符号，若是符号，则将数字入栈，否则还得接着扫描
                 * 3. 需定义一个变量用于字符串的拼接
                 */
                keepNum += ch;
                if (index == express.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断后一位是否为数字
                    if(charStack.isOper(express.substring(index+1,index+2).charAt(0))){
                        //若最后一位是操作符，那好，则直接入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //每次一判断完一串数字，就将keepnum清空，否则，会导致keepnum中保存原来的值
                        keepNum = "";
                    }
                }
            }
            //往后进行遍历
            index ++;
            if (index >= express.length()){
                break;
            }
        }
        //遍历完毕后，从数栈和符号栈取出元素进行运算即可，并将数栈中的元素返回
        //如果符号栈为空，则表示最后的结果就是数栈中的值
        while (true){
            if(charStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = charStack.pop();
            res = numStack.calRes(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈最后的数出栈
        int res2 = numStack.pop();
        System.out.printf("表达式的值 %s=%d",express,res2);

    }

    //    public static void main(String[] args) {
    //        ArrayStack arrayStack = new ArrayStack(10);
    //        System.out.println(arrayStack.calRes(1, 3, '/'));
    //    }
}
//创建一个栈
class ArrayStack{

    private int maxSize;//站的大小
    private int[] stack;//数组栈
    private int top = -1;//表示栈顶


    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，返回当前栈顶的值
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        //判断栈是否为满
        if (isFull()){
            System.out.println("栈满，无法插入");
            return;
        }
        top ++;
        stack[top] = value;
    }

    public int pop(){
        //判断栈是否空,这里因为要涉及到取数据，故这里会有异常
        if (isEmpty()){
            throw new RuntimeException("栈空，元素无法出栈~~~");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void list(){
        //判断栈是否为空
        if (isEmpty()){
            System.out.println("无法遍历，栈中没有元素出栈");
            return;
        }
        for (int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d",i,stack[i]);
        }
    }

    //优先级的定位
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }
        else if (oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是否为一个运算符
    public boolean isOper(char ch){
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }
    //计算
    public int calRes(int num1,int num2,int oper){
        int res=0;//用来存放计算的结果

        switch (oper){
            case '+':
                res =  num1+num2;
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