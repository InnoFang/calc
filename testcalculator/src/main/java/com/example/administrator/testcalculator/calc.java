package com.example.administrator.testcalculator;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/2/14.
 */
public class calc {
    public calc(){

    }
    final int MAXLEN = 500;
    /*
     * 计算表达式
     * 从左向右扫描，数字入number栈，运算符入operator栈
     * +-基本优先级为1，×÷基本优先级为2，log ln sin cos tan n!基本优先级为3，√^基本优先级为4
     * 括号内层运算符比外层同级运算符优先级高4
     * 当前运算符优先级高于栈顶压栈，低于栈顶弹出一个运算符与两个数进行运算
     * 重复直到当前运算符大于栈顶
     * 扫描完后对剩下的运算符与数字依次计算
     */
    public void process(String str) {
        int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
        //weightPlus为同一（）下的基本优先级，weightTemp临时记录优先级的变化
        //topOp为weight[]，operator[]的计数器；topNum为number[]的计数器
        //flag为正负数的计数器，1为正数，-1为负数
        int weight[];  //保存operator栈中运算符的优先级，以topOp计数
        double number[];  //保存数字，以topNum计数
        char ch, ch_gai, operator[];//operator[]保存运算符，以topOp计数
        String num;//记录数字，str以+-×÷()sctgl!√^分段，+-×÷()sctgl!√^字符之间的字符串即为数字
        weight = new int[MAXLEN];
        number = new double[MAXLEN];
        operator = new char[MAXLEN];
        String expression = str;
        StringTokenizer expToken = new StringTokenizer(expression,"+-×÷()sctgl!√^");
        int i = 0;
        while (i < expression.length()) {
            ch = expression.charAt(i);
            if (i == 0) {
                if (ch == '-') flag = -1;
            } else if(expression.charAt(i-1) == '(' && ch == '-') flag = -1;
            if (ch <= '9' && ch >= '0'|| ch == '.' || ch == 'E') {
                num = expToken.nextToken();
                ch_gai = ch;
                while (i < expression.length() &&
                        (ch_gai <= '9' && ch_gai >= '0'|| ch_gai == '.' || ch_gai == 'E'))
                    ch_gai = expression.charAt(i++);
                if (i >= expression.length()) i-=1; else i-=2;
                if (num.compareTo(".") == 0) number[topNum++] = 0;
                else {
                    number[topNum++] = Double.parseDouble(num)*flag;
                    flag = 1;
                }
            }
            if (ch == '(') weightPlus+=4;
            if (ch == ')') weightPlus-=4;
            if (ch == '-' && flag == 1 || ch == '+' || ch == '×'|| ch == '÷' ||
                    ch == 's' ||ch == 'c' || ch == 't' || ch == 'g' || ch == 'l' ||
                    ch == '!' || ch == '√' || ch == '^') {
                switch (ch) {
                    case '+':
                    case '-':
                        weightTemp = 1 + weightPlus;
                        break;
                    case '×':
                    case '÷':
                        weightTemp = 2 + weightPlus;
                        break;
                    case 's':
                    case 'c':
                    case 't':
                    case 'g':
                    case 'l':
                    case '!':
                        weightTemp = 3 + weightPlus;
                        break;
                    //case '^':
                    //case '√':
                    default:
                        weightTemp = 4 + weightPlus;
                        break;
                }
                if (topOp == 0 || weight[topOp-1] < weightTemp) {
                    weight[topOp] = weightTemp;
                    operator[topOp] = ch;
                    topOp++;
                }else {
                    while (topOp > 0 && weight[topOp-1] >= weightTemp) {
                        switch (operator[topOp-1]) {
                            case '+':
                                number[topNum-2]+=number[topNum-1];
                                break;
                            case '-':
                                number[topNum-2]-=number[topNum-1];
                                break;
                            case '×':
                                number[topNum-2]*=number[topNum-1];
                                break;
                            case '÷':
                                if (number[topNum-1] == 0) {
                                    showError(1,str_old);
                                    return;
                                }
                                number[topNum-2]/=number[topNum-1];
                                break;
                            case '√':
                                if(number[topNum-1] == 0 || (number[topNum-2] < 0 &&
                                        number[topNum-1] % 2 == 0)) {
                                    showError(2,str_old);
                                    return;
                                }
                                number[topNum-2] =
                                        Math.pow(number[topNum-2], 1/number[topNum-1]);
                                break;
                            case '^':
                                number[topNum-2] =
                                        Math.pow(number[topNum-2], number[topNum-1]);
                                break;
                            case 's':
                                if(drg_flag == true) {
                                    number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                                } else {
                                    number[topNum-1] = Math.sin(number[topNum-1]);
                                }
                                topNum++;
                                break;
                            case 'c':
                                if(drg_flag == true) {
                                    number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                                } else {
                                    number[topNum-1] = Math.cos(number[topNum-1]);
                                }
                                topNum++;
                                break;
                            case 't':
                                if(drg_flag == true) {
                                    if((Math.abs(number[topNum-1])/90)%2 == 1) {
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                                } else {
                                    if((Math.abs(number[topNum-1])/(pi/2))%2 == 1) {
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1] = Math.tan(number[topNum-1]);
                                }
                                topNum++;
                                break;
                            case 'g':
                                if(number[topNum-1] <= 0) {
                                    showError(2,str_old);
                                    return;
                                }
                                number[topNum-1] = Math.log10(number[topNum-1]);
                                topNum++;
                                break;
                            case 'l':
                                if(number[topNum-1] <= 0) {
                                    showError(2,str_old);
                                    return;
                                }
                                number[topNum-1] = Math.log(number[topNum-1]);
                                topNum++;
                                break;
                            case '!':
                                if(number[topNum-1] > 170) {
                                    showError(3,str_old);
                                    return;
                                } else if(number[topNum-1] < 0) {
                                    showError(2,str_old);
                                    return;
                                }
                                number[topNum-1] = N(number[topNum-1]);
                                topNum++;
                                break;
                        }
                        topNum--;
                        topOp--;
                    }
                    weight[topOp] = weightTemp;
                    operator[topOp] = ch;
                    topOp++;
                }
            }
            i++;
        }
        while (topOp>0) {
            switch (operator[topOp-1]) {
                case '+':
                    number[topNum-2]+=number[topNum-1];
                    break;
                case '-':
                    number[topNum-2]-=number[topNum-1];
                    break;
                case '×':
                    number[topNum-2]*=number[topNum-1];
                    break;
                case '÷':
                    if (number[topNum-1] == 0) {
                        showError(1,str_old);
                        return;
                    }
                    number[topNum-2]/=number[topNum-1];
                    break;
                case '√':
                    if(number[topNum-1] == 0 || (number[topNum-2] < 0 &&
                            number[topNum-1] % 2 == 0)) {
                        showError(2,str_old);
                        return;
                    }
                    number[topNum-2] =
                            Math.pow(number[topNum-2], 1/number[topNum-1]);
                    break;
                case '^':
                    number[topNum-2] =
                            Math.pow(number[topNum-2], number[topNum-1]);
                    break;
                case 's':
                    if(drg_flag == true) {
                        number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                    } else {
                        number[topNum-1] = Math.sin(number[topNum-1]);
                    }
                    topNum++;
                    break;
                case 'c':
                    if(drg_flag == true) {
                        number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                    } else {
                        number[topNum-1] = Math.cos(number[topNum-1]);
                    }
                    topNum++;
                    break;
                case 't':
                    if(drg_flag == true) {
                        if((Math.abs(number[topNum-1])/90)%2 == 1) {
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                    } else {
                        if((Math.abs(number[topNum-1])/(pi/2))%2 == 1) {
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1] = Math.tan(number[topNum-1]);
                    }
                    topNum++;
                    break;
                case 'g':
                    if(number[topNum-1] <= 0) {
                        showError(2,str_old);
                        return;
                    }
                    number[topNum-1] = Math.log10(number[topNum-1]);
                    topNum++;
                    break;
                case 'l':
                    if(number[topNum-1] <= 0) {
                        showError(2,str_old);
                        return;
                    }
                    number[topNum-1] = Math.log(number[topNum-1]);
                    topNum++;
                    break;
                case '!':
                    if(number[topNum-1] > 170) {
                        showError(3,str_old);
                        return;
                    } else if(number[topNum-1] < 0) {
                        showError(2,str_old);
                        return;
                    }
                    number[topNum-1] = N(number[topNum-1]);
                    topNum++;
                    break;
            }
            topNum--;
            topOp--;
        }

        if(number[0] > 7.3E306) {
            showError(3,str_old);
            //input.setText("\""+str_old+"\": 太大了，我不行了");
            return;
        }
        input.setText(String.valueOf(FP(number[0]))); //输出最终结果
        tip.setText("计算完毕，要继续请按归零键 C");
        mem.setText(str_old+"="+String.valueOf(FP(number[0])));
    }

    /*
     * FP = floating point 控制小数位数，达到精度
     * 否则会出现 0.6-0.2=0.39999999999999997的情况，用FP即可解决，使得数为0.4
     * 本格式精度为15位
     */
    public double FP(double n) {
        //NumberFormat format=NumberFormat.getInstance();  //创建一个格式化类f
        //format.setMaximumFractionDigits(18);    //设置小数位的格式
        DecimalFormat format = new DecimalFormat("0.#############");
        return Double.parseDouble(format.format(n));
    }

    /*
     * 阶乘算法
     */
    public double N(double n) {
        int i = 0;
        double sum = 1;
        for(i = 1;i <= n;i++) {
            sum = sum*i;
        }
        return sum;
    }

    /*
     * 错误提示，按了"="之后，若计算式在process()过程中，出现错误，则进行提示
     */
    public void showError(int code ,String str) {
        String message="";
        switch (code) {
            case 1:
                message = "零不能作除数";
                break;
            case 2:
                message = "函数格式错误";
                break;
            case 3:
                message = "值太大了，我不行了";
        }
        input.setText("\""+str+"\""+": "+message);
        tip.setText(message+"\n"+"计算完毕，要继续请按归零键 C");
    }
}

