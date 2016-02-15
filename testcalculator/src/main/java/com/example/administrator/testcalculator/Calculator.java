package com.example.administrator.testcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends Activity {
    private Button[] btn = new Button[10];    //0~9十个按键
    private EditText input;                   //显示器
    private TextView mem;                     //显示器下方的记忆器，用于记录上一次计算结果
    private TextView _drg;                    //三角计算时标志显示：角度还是弧度
    private TextView tip;                     //小提示，用于加强人机交互的弱检测、提示
    private Button
            div, mul, sub, add, equal,            // ÷ × - + =
            sin, cos, tan, log, ln,               //函数
            sqrt, square, factorial, bksp,  //根号  平方  阶乘   退格
            left, right, dot, exit, drg,          //（     ）  .  退出     角度弧度控制键
            mc, c;                                // mem清屏f键    input清屏键

    public String str_old;           //保存原来的算式样子，为了输出时好看，因计算时，算式样子被改变
    public String str_new;           //变换样子后的式子
    public boolean vbegin = true;    //输入控制，true为重新输入，false为接着输入
    public boolean drg_flag = true;  //控制DRG按键，true为角度，false为弧度
    public double pi=4*Math.atan(1); //3.14
    public boolean tip_lock = true;  //true表示正确，可以继续输入；false表示有误，输入被锁定
    public boolean equals_flag = true;  //判断是否是按=之后的输入，true表示输入在=之前，false反之

    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        mem = (TextView)findViewById(R.id.mem);
        tip = (TextView)findViewById(R.id.tip);
        _drg = (TextView)findViewById(R.id._drg);
        btn[0] = (Button)findViewById(R.id.zero);
        btn[1] = (Button)findViewById(R.id.one);
        btn[2] = (Button)findViewById(R.id.two);
        btn[3] = (Button)findViewById(R.id.three);
        btn[4] = (Button)findViewById(R.id.four);
        btn[5] = (Button)findViewById(R.id.five);
        btn[6] = (Button)findViewById(R.id.six);
        btn[7] = (Button)findViewById(R.id.seven);
        btn[8] = (Button)findViewById(R.id.eight);
        btn[9] = (Button)findViewById(R.id.nine);
        div = (Button)findViewById(R.id.divide);
        mul = (Button)findViewById(R.id.mul);
        sub = (Button)findViewById(R.id.sub);
        add = (Button)findViewById(R.id.add);
        equal = (Button)findViewById(R.id.equal);
        sin = (Button)findViewById(R.id.sin);
        cos = (Button)findViewById(R.id.cos);
        tan = (Button)findViewById(R.id.tan);
        log = (Button)findViewById(R.id.log);
        ln = (Button)findViewById(R.id.ln);
        sqrt = (Button)findViewById(R.id.sqrt);
        square = (Button)findViewById(R.id.square);
        factorial = (Button)findViewById(R.id.factorial);
        bksp = (Button)findViewById(R.id.bksp);
        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        dot = (Button)findViewById(R.id.dot);
        exit = (Button)findViewById(R.id.exit);
        drg = (Button)findViewById(R.id.drg);
        mc = (Button)findViewById(R.id.mc);
        c = (Button)findViewById(R.id.c);

        for(int i = 0; i < 10; ++i) {
            btn[i].setOnClickListener(actionPerformed);
        }
        div.setOnClickListener(actionPerformed);
        mul.setOnClickListener(actionPerformed);
        sub.setOnClickListener(actionPerformed);
        add.setOnClickListener(actionPerformed);
        equal.setOnClickListener(actionPerformed);
        sin.setOnClickListener(actionPerformed);
        cos.setOnClickListener(actionPerformed);
        tan.setOnClickListener(actionPerformed);
        log.setOnClickListener(actionPerformed);
        ln.setOnClickListener(actionPerformed);
        sqrt.setOnClickListener(actionPerformed);
        square.setOnClickListener(actionPerformed);
        factorial.setOnClickListener(actionPerformed);
        bksp.setOnClickListener(actionPerformed);
        left.setOnClickListener(actionPerformed);
        right.setOnClickListener(actionPerformed);
        dot.setOnClickListener(actionPerformed);
        exit.setOnClickListener(actionPerformed);
        drg.setOnClickListener(actionPerformed);
        mc.setOnClickListener(actionPerformed);
        c.setOnClickListener(actionPerformed);
    }

    /*
     * 键盘命令扑捉
     */
    String[] Tipcommand = new String[500];  //命令缓存，用于检测输入合法性
    int tip_i = 0;         //Tipcommand的指针
    private View.OnClickListener actionPerformed = new View.OnClickListener( {
        public void onClick(View v) {
            String
                    command = ((Button)v).getText().toString(),       //按键上的命令获取
                    str = input.getText().toString();                 //显示器上的字符串

            if(equals_flag == false && "0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1) {
                if(right(input.getText().toString())) {
                    if("+-×÷√^)".indexOf(command) != -1) {
                        for(int i =0 ; i < input.getText().toString().length(); i++) {
                            Tipcommand[tip_i] = String.valueOf(input.getText().toString().charAt(i));
                            tip_i++;
                        }
                        vbegin = false;
                    }
                } else {
                    input.setText("0");
                    vbegin = true;
                    tip_i = 0;
                    tip_lock = true;
                    tip.setText("Welcome");
                }

                equals_flag = true;
            }
            if(tip_i > 0)
                TipChecker(Tipcommand[tip_i-1] , command);
            else if(tip_i == 0) {
                TipChecker("#" , command);
            }
            if("0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1 && tip_lock) {
                Tipcommand[tip_i] = command;
                tip_i++;
            }

            if("0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1
                    && tip_lock) { //共25个按键
                print(command);
            } else if(command.compareTo("DRG") == 0 && tip_lock) {
                if(drg_flag == true) {
                    drg_flag = false;
                    _drg.setText("   RAD");
                } else {
                    drg_flag = true;
                    _drg.setText("   DEG");
                }
            } else if(command.compareTo("Bksp") == 0 && equals_flag) {
                if(TTO(str) == 3) {
                    if(str.length() > 3)
                        input.setText(str.substring(0, str.length() - 3));
                    else if(str.length() == 3) {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                        tip.setText("Welcome");
                    }
                } else if(TTO(str) == 2) {
                    if(str.length() > 2)
                        input.setText(str.substring(0, str.length() - 2));
                    else if(str.length() == 2) {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                        tip.setText("Welcome");
                    }
                } else if(TTO(str) == 1) {
                    if(right(str)) {
                        if(str.length() > 1)
                            input.setText(str.substring(0, str.length() - 1));
                        else if(str.length() == 1) {
                            input.setText("0");
                            vbegin = true;
                            tip_i = 0;
                            tip.setText("Welcome");
                        }
                    } else {
                        input.setText("0");
                        vbegin = true;
                        tip_i = 0;
                        tip.setText("Welcome");
                    }
                }
                if(input.getText().toString().compareTo("-") == 0 || equals_flag == false) {
                    input.setText("0");
                    vbegin = true;
                    tip_i = 0;
                    tip.setText("Welcome");
                }
                tip_lock = true;
                if(tip_i > 0)
                    tip_i--;
            } else if(command.compareTo("Bksp") == 0 && equals_flag ==false) {
                input.setText("0");
                vbegin = true;
                tip_i = 0;
                tip_lock = true;
                tip.setText("Welcome");
            } else if(command.compareTo("C") == 0) {
                input.setText("0");
                vbegin = true;
                tip_i = 0;
                tip_lock = true;
                equals_flag = true;
                tip.setText("Welcome");
            } else if(command.compareTo("MC") == 0) {
                mem.setText("0");
            } else if(command.compareTo("exit") == 0) {
                System.exit(0);
            } else if(command.compareTo("=") == 0 && tip_lock && right(str) && equals_flag) {
                tip_i = 0;
                tip_lock = false;
                equals_flag = false;
                str_old = str;//保存原来算式样子
                str = str.replaceAll("sin", "s");
                str = str.replaceAll("cos", "c");
                str = str.replaceAll("tan", "t");
                str = str.replaceAll("log", "g");
                str = str.replaceAll("ln", "l");
                str = str.replaceAll("n!", "!");
                vbegin = true;
                str_new = str.replaceAll("-", "-1×");
                new calc().process(str_new);
            }

            tip_lock = true;
        }
    };

    /*
     * 向input输出字符
     * input.setText(str);为清屏后输出
     * input.append(str);为在屏幕原str后增添字符
     */
    private void print(String str) {
        if(vbegin)
            input.setText(str);
        else
            input.append(str);

        vbegin = false;
    }

    /*
     * 判断一个str是否是合法的，返回值为true、false
     * 只包含0123456789.()sincostanlnlogn!+-×÷√^的是合法的str，返回true
     * 包含了除0123456789.()sincostanlnlogn!+-×÷√^以外的字符的str为非法的，返回false
     */
    private boolean right(String str) {
        int i = 0;
        for(i = 0;i < str.length();i++) {
            if(str.charAt(i)!='0' && str.charAt(i)!='1' && str.charAt(i)!='2' &&
                    str.charAt(i)!='3' && str.charAt(i)!='4' && str.charAt(i)!='5' &&
                    str.charAt(i)!='6' && str.charAt(i)!='7' && str.charAt(i)!='8' &&
                    str.charAt(i)!='9' && str.charAt(i)!='.' && str.charAt(i)!='-' &&
                    str.charAt(i)!='+' && str.charAt(i)!='×' && str.charAt(i)!='÷' &&
                    str.charAt(i)!='√' && str.charAt(i)!='^' && str.charAt(i)!='s' &&
                    str.charAt(i)!='i' && str.charAt(i)!='n' && str.charAt(i)!='c' &&
                    str.charAt(i)!='o' && str.charAt(i)!='t' && str.charAt(i)!='a' &&
                    str.charAt(i)!='l' && str.charAt(i)!='g' && str.charAt(i)!='(' &&
                    str.charAt(i)!=')' && str.charAt(i)!='!')
                break;
        }
        if(i == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 检测函数，返回值为3、2、1  表示应当一次删除几个？  Three+Two+One = TTO
     * 为Bksp按钮的删除方式提供依据
     * 返回3，表示str尾部为sin、cos、tan、log中的一个，应当一次删除3个
     * 返回2，表示str尾部为ln、n!中的一个，应当一次删除2个
     * 返回1，表示为除返回3、2外的所有情况，只需删除一个（包含非法字符时要另外考虑：应清屏）
     */
    private int TTO(String str) {
        if((str.charAt(str.length() - 1) == 'n' &&
                str.charAt(str.length() - 2) == 'i' &&
                str.charAt(str.length() - 3) == 's') ||
                (str.charAt(str.length() - 1) == 's' &&
                        str.charAt(str.length() - 2) == 'o' &&
                        str.charAt(str.length() - 3) == 'c') ||
                (str.charAt(str.length() - 1) == 'n' &&
                        str.charAt(str.length() - 2) == 'a' &&
                        str.charAt(str.length() - 3) == 't') ||
                (str.charAt(str.length() - 1) == 'g' &&
                        str.charAt(str.length() - 2) == 'o' &&
                        str.charAt(str.length() - 3) == 'l')) {
            return 3;
        } else if((str.charAt(str.length() - 1) == 'n' &&
                str.charAt(str.length() - 2) == 'l') ||
                (str.charAt(str.length() - 1) == '!' &&
                        str.charAt(str.length() - 2) == 'n')) {
            return 2;
        } else { return 1; }
    }

    /*
     * 检测函数，对str进行前后语法检测
     * 为Tip的提示方式提供依据，与TipShow()配合使用
     *  编号 字符    其后可以跟随的合法字符
     *   1  （                 数字|（|-|.|函数
     *   2   ）                算符|）|√ ^
     *   3  .      数字|算符|）|√ ^
     *   4   数字        .|数字|算符|）|√ ^
     *   5   算符             数字|（|.|函数
     *   6 √ ^     （ |. | 数字
     *   7  函数           数字|（|.
     *
     * 小数点前后均可省略，表示0
     * 数字第一位可以为0
     */
    private void TipChecker(String tipcommand1,String tipcommand2) {
        int Tipcode1 = 0 , Tipcode2 = 0;//Tipcode1表示错误类型，Tipcode2表示名词解释类型
        int tiptype1 = 0 , tiptype2 = 0;//表示命令类型
        int bracket = 0;      //括号数
        if(tipcommand1.compareTo("#") == 0 && (tipcommand2.compareTo("÷") == 0 ||
                tipcommand2.compareTo("×") == 0 || tipcommand2.compareTo("+") == 0 ||
                tipcommand2.compareTo(")") == 0 || tipcommand2.compareTo("√") == 0 ||
                tipcommand2.compareTo("^") == 0)) {
            Tipcode1 = -1;//不能作为第一位
        }
        if(tipcommand1.compareTo("#") != 0) {
            if(tipcommand1.compareTo("(") == 0) {
                tiptype1 = 1;
            } else if(tipcommand1.compareTo(")") == 0) {
                tiptype1 = 2;
            } else if(tipcommand1.compareTo(".") == 0) {
                tiptype1 = 3;
            } else if("0123456789".indexOf(tipcommand1) != -1) {
                tiptype1 = 4;
            } else if("+-×÷".indexOf(tipcommand1) != -1) {
                tiptype1 = 5;
            } else if("√^".indexOf(tipcommand1) != -1) {
                tiptype1 = 6;
            } else if("sincostanloglnn!".indexOf(tipcommand1) != -1) {
                tiptype1 = 7;
            }

            if(tipcommand2.compareTo("(") == 0) {
                tiptype2 = 1;
            } else if(tipcommand2.compareTo(")") == 0) {
                tiptype2 = 2;
            } else if(tipcommand2.compareTo(".") == 0) {
                tiptype2 = 3;
            } else if("0123456789".indexOf(tipcommand2) != -1) {
                tiptype2 = 4;
            } else if("+-×÷".indexOf(tipcommand2) != -1) {
                tiptype2 = 5;
            } else if("√^".indexOf(tipcommand2) != -1) {
                tiptype2 = 6;
            } else if("sincostanloglnn!".indexOf(tipcommand2) != -1) {
                tiptype2 = 7;
            }

            switch(tiptype1) {
                case 1:
                    if(tiptype2 == 2 || (tiptype2 == 5 && tipcommand2.compareTo("-") != 0) ||
                            tiptype2 == 6)
                        Tipcode1 = 1;
                    break;
                case 2:
                    if(tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4 || tiptype2 == 7) Tipcode1 = 2;
                    break;
                case 3:
                    if(tiptype2 == 1 || tiptype2 == 7) Tipcode1 = 3;
                    if(tiptype2 == 3) Tipcode1 = 8;
                    break;
                case 4:
                    if(tiptype2 == 1 || tiptype2 == 7) Tipcode1 = 4;
                    break;
                case 5:
                    if(tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6) Tipcode1 = 5;
                    break;
                case 6:
                    if(tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6 || tiptype2 == 7) Tipcode1 = 6;
                    break;
                case 7:
                    if(tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6 || tiptype2 == 7) Tipcode1 = 7;
                    break;
            }
        }

        if(Tipcode1 == 0 && tipcommand2.compareTo(".") == 0) {//检测小数点的重复性
            int tip_point = 0;
            for(int i = 0;i < tip_i;i++) {
                if(Tipcommand[i].compareTo(".") == 0) {
                    tip_point++;
                }
                if(Tipcommand[i].compareTo("sin") == 0 || Tipcommand[i].compareTo("cos") == 0 ||
                        Tipcommand[i].compareTo("tan") == 0 || Tipcommand[i].compareTo("log") == 0 ||
                        Tipcommand[i].compareTo("ln") == 0 || Tipcommand[i].compareTo("n!") == 0 ||
                        Tipcommand[i].compareTo("√") == 0 || Tipcommand[i].compareTo("^") == 0 ||
                        Tipcommand[i].compareTo("÷") == 0 || Tipcommand[i].compareTo("×") == 0 ||
                        Tipcommand[i].compareTo("-") == 0 || Tipcommand[i].compareTo("+") == 0 ||
                        Tipcommand[i].compareTo("(") == 0 || Tipcommand[i].compareTo(")") == 0 ) {
                    tip_point = 0;
                }
            }
            tip_point++;
            if(tip_point > 1) {
                Tipcode1 = 8;
            }
        }
        if(Tipcode1 == 0 && tipcommand2.compareTo(")") == 0) {
            int tip_right_bracket = 0;//右括号匹配数，主要用于右括号
            for(int i = 0;i < tip_i;i++) {
                if(Tipcommand[i].compareTo("(") == 0) {
                    tip_right_bracket++;
                }
                if(Tipcommand[i].compareTo(")") == 0) {
                    tip_right_bracket--;
                }
            }
            if(tip_right_bracket == 0) {
                Tipcode1 = 10;
            }
        }
        if(Tipcode1 == 0 && tipcommand2.compareTo("=") == 0) {
            int tip_bracket = 0; //括号匹配数，主要用于左括号
            for(int i = 0;i < tip_i;i++) {
                if(Tipcommand[i].compareTo("(") == 0) {
                    tip_bracket++;
                }
                if(Tipcommand[i].compareTo(")") == 0) {
                    tip_bracket--;
                }
            }
            if(tip_bracket > 0) {
                Tipcode1 = 9;
                bracket = tip_bracket;
            } else if(tip_bracket == 0) {
                if("√^sincostanloglnn!".indexOf(tipcommand1) != -1) {
                    Tipcode1 = 6;
                }
                if("+-×÷".indexOf(tipcommand1) != -1) {
                    Tipcode1 = 5;
                }
            }
        }

        if(tipcommand2.compareTo("MC") == 0) Tipcode2 = 1;
        if(tipcommand2.compareTo("C") == 0) Tipcode2 = 2;
        if(tipcommand2.compareTo("DRG") == 0) Tipcode2 = 3;
        if(tipcommand2.compareTo("Bksp") == 0) Tipcode2 = 4;
        if(tipcommand2.compareTo("sin") == 0) Tipcode2 = 5;
        if(tipcommand2.compareTo("cos") == 0) Tipcode2 = 6;
        if(tipcommand2.compareTo("tan") == 0) Tipcode2 = 7;
        if(tipcommand2.compareTo("log") ==0) Tipcode2 = 8;
        if(tipcommand2.compareTo("ln") == 0) Tipcode2 = 9;
        if(tipcommand2.compareTo("n!") == 0) Tipcode2 = 10;
        if(tipcommand2.compareTo("√") == 0) Tipcode2 = 11;
        if(tipcommand2.compareTo("^") == 0) Tipcode2 = 12;

        TipShow(bracket , Tipcode1 , Tipcode2 , tipcommand1 , tipcommand2);
    }

    /*
     * 反馈Tip信息，加强人机交互，与TipChecker()配合使用
     */
    private void TipShow(int bracket , int tipcode1 , int tipcode2 ,
                         String tipcommand1 , String tipcommand2){
        String tipmessage="";
        if(tipcode1!=0)tip_lock=false;
        switch(tipcode1){
        /*case -1:
            tipmessage = tipcommand2 + "  不能作为第一个算符\n";
            break;
        case 1:
            tipmessage = tipcommand1 + "  后应输入：数字/(/./-/函数 \n";
            break;
        case 2:
            tipmessage = tipcommand1 + "  后应输入：)/算符 \n";
            break;
        case 3:
            tipmessage = tipcommand1 + "  后应输入：)/数字/算符 \n";
            break;
        case 4:
            tipmessage = tipcommand1 + "  后应输入：)/./数字 /算符 \n";
            break;
        case 5:
            tipmessage = tipcommand1 + "  后应输入：(/./数字/函数 \n";
            break;
        case 6:
            tipmessage = tipcommand1 + "  后应输入：(/./数字 \n";
            break;
        case 7:
            tipmessage = tipcommand1 + "  后应输入：(/./数字 \n";
            break;
        case 8:
            tipmessage = "小数点重复\n";
            break;*/
        case 9:
        tipmessage="不能计算，缺少 "+bracket+" 个 )";
        break;
        /*case 10:
            tipmessage = "不需要  )";
            break;*/
        }
        switch(tipcode2){
        /*case 1:
            tipmessage = tipmessage + "[MC 用法: 清除记忆 MEM]";
            break;
        case 2:
            tipmessage = tipmessage + "[C 用法: 归零]";
            break;
        case 3:
            tipmessage = tipmessage + "[DRG 用法: 选择 DEG 或 RAD]";
            break;
        case 4:
            tipmessage = tipmessage + "[Bksp 用法: 退格]";
            break;*/
        case 5:
        tipmessage=tipmessage+"sin 函数用法示例：\n"+
        "DEG：sin30 = 0.5      RAD：sin1 = 0.84\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "sin(cos45)，而不是sincos45";
        break;
        case 6:
        tipmessage=tipmessage+"cos 函数用法示例：\n"+
        "DEG：cos60 = 0.5      RAD：cos1 = 0.54\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "cos(sin45)，而不是cossin45";
        break;
        case 7:
        tipmessage=tipmessage+"tan 函数用法示例：\n"+
        "DEG：tan45 = 1      RAD：tan1 = 1.55\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "tan(cos45)，而不是tancos45";
        break;
        case 8:
        tipmessage=tipmessage+"log 函数用法示例：\n"+
        "log10 = log(5+5) = 1\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "log(tan45)，而不是logtan45";
        break;
        case 9:
        tipmessage=tipmessage+"ln 函数用法示例：\n"+
        "ln10 = le(5+5) = 2.3   lne = 1\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "ln(tan45)，而不是lntan45";
        break;
        case 10:
        tipmessage=tipmessage+"n! 函数用法示例：\n"+
        "n!3 = n!(1+2) = 3×2×1 = 6\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "n!(log1000)，而不是n!log1000";
        break;
        case 11:
        tipmessage=tipmessage+"√ 用法示例：开任意次根号\n"+
        "如：27开3次根为  27√3 = 3\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "(函数)√(函数) ， (n!3)√(log100) = 2.45";
        break;
        case 12:
        tipmessage=tipmessage+"^ 用法示例：开任意次平方\n"+
        "如：2的3次方为  2^3 = 8\n"+
        "注：与其他函数一起使用时要加括号，如：\n"+
        "(函数)√(函数) ， (n!3)^(log100) = 36";
        break;
        }

        tip.setText(tipmessage);
}
