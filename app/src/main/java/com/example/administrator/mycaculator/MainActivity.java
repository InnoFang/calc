package com.example.administrator.mycaculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements View.OnClickListener {

    private Display display;
    private Button  clear,tan,sin,cos,fac,back,log,ln,
            right,left,square,sqrt,div,add,pi,
            seven,eight,nine,sub,mul,four,e,
            five,six,one,two,three,zero,dot,equal;
    private StringBuffer str_display = new StringBuffer(); //显示
    private String str_result ;//结果显示
    private boolean flag = true;//小数点开关控制
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        requestWindowFeature (Window.FEATURE_NO_TITLE);
        setContentView (R.layout.activity_main);

        display = (Display) findViewById (R.id.display);
        log = (Button) findViewById (R.id.log);
        ln = (Button) findViewById (R.id.ln);
        pi = (Button) findViewById (R.id.pi);
        e = (Button) findViewById (R.id.e);
        clear = (Button) findViewById (R.id.clear);
        tan = (Button) findViewById (R.id.tan);
        sin = (Button) findViewById (R.id.sin);
        cos = (Button) findViewById (R.id.cos);
        fac = (Button) findViewById (R.id.fac);
        back = (Button) findViewById (R.id.back);
        right = (Button) findViewById (R.id.right);
        left = (Button) findViewById (R.id.left);
        square = (Button) findViewById (R.id.square);
        sqrt = (Button) findViewById (R.id.sqrt);
        div = (Button) findViewById (R.id.div);
        add = (Button) findViewById (R.id.add);
        seven = (Button) findViewById (R.id.seven);
        eight = (Button) findViewById (R.id.eight);
        nine = (Button) findViewById (R.id.nine);
        sub = (Button) findViewById (R.id.sub);
        mul = (Button) findViewById (R.id.mul);
        four = (Button) findViewById (R.id.four);
        five = (Button) findViewById (R.id.five);
        six = (Button) findViewById (R.id.six);
        one = (Button) findViewById (R.id.one);
        two = (Button) findViewById (R.id.two);
        three = (Button) findViewById (R.id.three);
        zero = (Button) findViewById (R.id.zero);
        dot = (Button) findViewById (R.id.dot);
        equal = (Button) findViewById (R.id.equal);

        display.setOnClickListener (this);
        log.setOnClickListener (this);
        ln.setOnClickListener (this);
        e.setOnClickListener (this);
        pi.setOnClickListener (this);
        clear.setOnClickListener (this);
        tan.setOnClickListener (this);
        sin.setOnClickListener (this);
        cos.setOnClickListener (this);
        fac.setOnClickListener (this);
        back.setOnClickListener (this);
        right.setOnClickListener (this);
        left.setOnClickListener (this);
        square.setOnClickListener (this);
        sqrt.setOnClickListener (this);
        div.setOnClickListener (this);
        add.setOnClickListener (this);
        seven.setOnClickListener (this);
        eight.setOnClickListener (this);
        nine.setOnClickListener (this);
        sub.setOnClickListener (this);
        mul.setOnClickListener (this);
        four.setOnClickListener (this);
        five.setOnClickListener (this);
        six.setOnClickListener (this);
        one.setOnClickListener (this);
        two.setOnClickListener (this);
        three.setOnClickListener (this);
        zero.setOnClickListener (this);
        dot.setOnClickListener (this);
        equal.setOnClickListener (this);

        display.setText ("0.0");
        display.setSelection (str_display.length ());//光标的位置,设置成最后
        display.setFocusableInTouchMode (false);//虚拟键盘隐藏
//        display.setFocusable (false);//失去焦点
    }

    @Override
    public void onClick (View view) {
        switch(view.getId ()){
            case R.id.nine:
                str_display.append ("9");
                display.setText (str_display.toString ());
                break;
            case R.id.eight:
                str_display.append ("8");
                display.setText (str_display.toString ());
                break;
            case R.id.seven:
                str_display.append ("7");
                display.setText (str_display.toString ());
                break;
            case R.id.six:
                str_display.append ("6");
                display.setText (str_display.toString ());
                break;
            case R.id.five:
                str_display.append ("5");
                display.setText (str_display.toString ());
                break;
            case R.id.four:
                str_display.append ("4");
                display.setText (str_display.toString ());
                break;
            case R.id.three:
                str_display.append ("3");
                display.setText (str_display.toString ());
                break;
            case R.id.two:
                str_display.append ("2");
                display.setText (str_display.toString ());
                break;
            case R.id.one:
                str_display.append ("1");
                display.setText (str_display.toString ());
                break;
            case R.id.zero:
                str_display.append ("0");
                display.setText (str_display.toString ());
                break;
            case R.id.dot:
                //第一次点击时没有数字,或者说如果点击之前没有数字(针对最后一个字符为运算符时),默认在前面添加0
                if(str_display.toString ().equals ("") || str_display.charAt (str_display.length () - 1) != '1' &&
                        str_display.charAt (str_display.length () - 1) != '2' &&
                        str_display.charAt (str_display.length () - 1) != '3' &&
                        str_display.charAt (str_display.length () - 1) != '4' &&
                        str_display.charAt (str_display.length () - 1) != '5' &&
                        str_display.charAt (str_display.length () - 1) != '6' &&
                        str_display.charAt (str_display.length () - 1) != '7' &&
                        str_display.charAt (str_display.length () - 1) != '8' &&
                        str_display.charAt (str_display.length () - 1) != '9' &&
                        str_display.charAt (str_display.length () - 1) != '0' ){
                    Toast.makeText (MainActivity.this,"please check",Toast.LENGTH_SHORT).show ();
                    str_display.append ("0");
                    display.setText (str_display.toString ());
                }
                //防止出现重复点击
                if(flag){
                    str_display.append (".");
                    display.setText (str_display.toString ());
                    flag = false;
                }
                break;
            case R.id.add:
                //若前面是运算符则删掉前面的运算符,添加新点击的运算符
                if( str_display.toString ().equals ("")){
                    Toast.makeText(MainActivity.this,"你的内容为空",Toast.LENGTH_SHORT).show ();
                    break;
                }else if(str_display.charAt (str_display.length () - 1) == '÷' ||
                        str_display.charAt (str_display.length () - 1) == '×'||
                        str_display.charAt (str_display.length () - 1) == '-'||
                        str_display.charAt (str_display.length () - 1) == '^'||
                        str_display.charAt (str_display.length () - 1) == '√'){
                    str_display.deleteCharAt (str_display.length () - 1);
                }
                str_display.append ("+");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.sub:
                //若前面是运算符则删掉前面的运算符,添加点击的运算符
                if( str_display.toString ().equals ("")){
                    Toast.makeText(MainActivity.this,"你的内容为空",Toast.LENGTH_SHORT).show ();
                    break;
                }else if(str_display.charAt (str_display.length () - 1) == '÷' ||
                        str_display.charAt (str_display.length () - 1) == '×'||
                        str_display.charAt (str_display.length () - 1) == '+'||
                        str_display.charAt (str_display.length () - 1) == '^'||
                        str_display.charAt (str_display.length () - 1) == '√'){

                    str_display.deleteCharAt (str_display.length () - 1);
                }
                str_display.append ("-");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.mul:
                //若前面是运算符则删掉前面的运算符,添加点击的运算符
                if( str_display.toString ().equals ("")){
                    Toast.makeText(MainActivity.this,"你的内容为空",Toast.LENGTH_SHORT).show ();
                    break;
                }else if(str_display.charAt (str_display.length () - 1) == '÷' ||
                        str_display.charAt (str_display.length () - 1) == '+'||
                        str_display.charAt (str_display.length () - 1) == '-'||
                        str_display.charAt (str_display.length () - 1) == '^'||
                        str_display.charAt (str_display.length () - 1) == '√'){

                    str_display.deleteCharAt (str_display.length () - 1);
                }
                str_display.append ("×");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.square:
                //若前面是运算符则删掉前面的运算符,添加点击的运算符
                if( str_display.toString ().equals ("")){
                    Toast.makeText(MainActivity.this,"你的内容为空",Toast.LENGTH_SHORT).show ();
                    break;
                }else if(str_display.charAt (str_display.length () - 1) == '÷' ||
                        str_display.charAt (str_display.length () - 1) == '×'||
                        str_display.charAt (str_display.length () - 1) == '-'||
                        str_display.charAt (str_display.length () - 1) == '+'||
                        str_display.charAt (str_display.length () - 1) == '√'){
                    str_display.deleteCharAt (str_display.length () - 1);
                }
                str_display.append("^");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.sqrt:
                str_display.append("√");
                display.setText (str_display.toString ());
                break;
            case R.id.div:
                //若前面是运算符则删掉前面的运算符,添加点击的运算符
                if( str_display.toString ().equals ("")){
                    Toast.makeText(MainActivity.this,"你的内容为空",Toast.LENGTH_SHORT).show ();
                    break;
                }else if(str_display.charAt (str_display.length () - 1) == '+' ||
                        str_display.charAt (str_display.length () - 1) == '×'||
                        str_display.charAt (str_display.length () - 1) == '-'||
                        str_display.charAt (str_display.length () - 1) == '^'||
                        str_display.charAt (str_display.length () - 1) == '√'){

                    str_display.deleteCharAt (str_display.length () - 1);
                }
                str_display.append("÷");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.sin:
                str_display.append ("sin(");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.cos:
                str_display.append ("cos(");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.tan:
                str_display.append ("tan(");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.fac:
                str_display.append ("!");
                display.setText (str_display.toString ());
                break;
            case R.id.back:
                /*
                 * 检测字符串,为back按钮的删除方式提供依据
                 * 若back_index = 4,表示str尾部为sin(、cos(、tan(、log(中的一个,应当一次删除4个
                 * 若back_index = 3,表示str尾部为ln(,应当一次删除2个
                 * 若back_index = 1,表示为除返回3、2外的所有情况，只需删除一个
                 */
                int back_index = 1;//为back按钮的删除方式提供依据,默认为1
                if(((str_display.charAt(str_display.length() - 1) == '(' &&
                        str_display.charAt(str_display.length() - 2) == 'n' &&
                        str_display.charAt(str_display.length() - 3) == 'i' &&
                        str_display.charAt(str_display.length() - 4) == 's') ||
                        (str_display.charAt(str_display.length() - 1) == '(' &&
                                str_display.charAt(str_display.length() - 2) == 's' &&
                                str_display.charAt(str_display.length() - 3) == 'o' &&
                                str_display.charAt(str_display.length() - 4) == 'c') ||
                        (str_display.charAt(str_display.length() - 1) == '(' &&
                                str_display.charAt(str_display.length() - 2) == 'n' &&
                                str_display.charAt(str_display.length() - 3) == 'a' &&
                                str_display.charAt(str_display.length() - 4) == 't') ||
                        (str_display.charAt(str_display.length() - 1) == '(' &&
                                str_display.charAt(str_display.length() - 2) == 'g' &&
                                str_display.charAt(str_display.length() - 3) == 'o' &&
                                str_display.charAt(str_display.length() - 4) == 'l'))){
                    back_index = 4;
                } else if((str_display.charAt(str_display.length() - 1) == '(' &&
                        str_display.charAt(str_display.length() - 2) == 'n' &&
                        str_display.charAt(str_display.length() - 3) == 'l') ) {
                    back_index = 3;
                } else {
                    back_index = 1;
                }
                if( !str_display.toString ().equals ("")){
                    str_display.delete(str_display .length () - back_index,str_display.length ());
                    display.setText (str_display.toString ());
                }
                //在删除"."之后恢复"."的点击
                if(str_display.indexOf (".") == -1 && !flag){
                    flag = true;
                }
                break;
            case R.id.right:
                str_display.append (")");
                display.setText (str_display.toString ());
                break;
            case R.id.left:
                str_display.append ("(");
                display.setText (str_display.toString ());
                break;
            case R.id.log:
                str_display.append ("log(");
                display.setText (str_display.toString ());
                break;
            case R.id.ln:
                str_display.append ("ln(");
                display.setText (str_display.toString ());
                break;
            case R.id.pi:
                str_display.append ("π");
                display.setText (str_display.toString ());
                break;
            case R.id.e:
                str_display.append ("e");
                display.setText (str_display.toString ());
                break;
            case R.id.clear:
                str_display = new StringBuffer ("");
                str_result = null;
                flag = true;
                display.setText ("0.0");
                break;
            case R.id.equal:
                str_result = countingResult (str_display);
                if(str_result.equals ("mismatch")){
                    Toast.makeText (MainActivity.this,"你的左括号数与右括号数不相等,请检查输入!",Toast.LENGTH_SHORT).show ();
                }else if(str_result.equals ("zero") ) {
                    Toast.makeText (MainActivity.this,"除数不能为0!",Toast.LENGTH_SHORT).show ();
                }else{
                    display.setText (str_result);
                    str_display = new StringBuffer ("");
                }
                break;
            default:
                break;
        }
    }

   public String  countingResult(StringBuffer input){
       String mathematical ; //用来保存括号内的算式
       String result;    //用来获得算式的结果

       //替换π和e
       if(input.indexOf ("π") != -1){
           input = input.replace (input.indexOf ("π") , input.indexOf ("π") + 1 ,String.valueOf (Math.PI));
       }
       if (input.indexOf ("e") != -1){
           input = input.replace (input.indexOf ("e") , input.indexOf ("e") + 1 ,String.valueOf (Math.E));
       }
        //如果有首尾括号则去除括号
       if(input.indexOf ("(") == 0 && input.indexOf (")") == input.length () - 1){
           input.deleteCharAt (0);
           input.deleteCharAt (input.length () - 1);
       }
       /**
        * 首先判断左括号数与右括号数是否相等,如果左括号数与右括号数不相等,则友好提示
        * 有括号的情况:sin(),cos()tan(),log(),ln(),还有就是仅仅一对()
        * 通过计算相应的情况,并替换掉
        */
       char[] str_input = input.toString ().toCharArray ();
       int right_num = 0;    //记录右括号数
       int left_num = 0;      //记录左括号数
       for(int i = 0;i < str_input.length ; ++i){
           if (str_input[i] == ')'){
               right_num ++;
           }else if(str_input[i] == '('){
               left_num ++;
           }
       }
       if(right_num != left_num){
            //如果不等就返回mismatch,并做出友好提示
           return "mismatch";
       }else {
           //只要有括号存在就一直进行计算和替换
           while(input.indexOf ("(") != -1 && input.indexOf (")") != -1){
               //先获得第一对括号里的算式
               mathematical = input.substring (input.indexOf ("(") + 1, input.indexOf (")"));
               //计算获得的算式,并获得计算结果
               result = countingMathematical (mathematical);
               //先判断类型再计算
                //计算sin()类型
               if(input.substring (input.indexOf ("(") - 3,input.indexOf (")")).equals ("sin")){
                    String resultSin = new DecimalFormat ("#.######").format (Math.sin (Double.parseDouble (result)));
                    //将sin()替换掉
                   input = input.replace (input.indexOf ("(") - 3,input.indexOf (")") + 1,resultSin);
               }
               // 计算cos()类型
               else if(input.substring (input.indexOf ("(") - 3,input.indexOf (")")).equals ("cos")){
                   String resultCos = new DecimalFormat ("#.######").format (Math.cos (Double.parseDouble (result)));
                   //将cos()替换掉
                   input = input.replace (input.indexOf ("(") - 3,input.indexOf (")") + 1,resultCos);
               }
               // 计算tan()类型
               else if(input.substring (input.indexOf ("(") - 3,input.indexOf (")")).equals ("tan")){
                   String resultTan = new DecimalFormat ("#.######").format (Math.tan (Double.parseDouble (result)));
                   //将tan()替换掉
                   input = input.replace (input.indexOf ("(") - 3,input.indexOf (")") + 1,resultTan);
               }
               // 计算log()类型
               else if(input.substring (input.indexOf ("(") - 3,input.indexOf (")")).equals ("log")) {
                   String resultLog = new DecimalFormat ("#.######").format (Math.log10 (Double.parseDouble (result)));
                   //将log()替换掉
                   input = input.replace (input.indexOf ("(") - 3, input.indexOf (")") + 1, resultLog);
               }
               // 计算ln()类型
               else if(input.substring (input.indexOf ("(") - 2,input.indexOf (")")).equals ("ln")) {
                   String resultLn = new DecimalFormat ("#.######").format (Math.log (Double.parseDouble (result)));//log默认以e为底
                   //将ln()替换掉
                   input = input.replace (input.indexOf ("(") - 3, input.indexOf (")") + 1, resultLn);
               }
               //最后一种情况,即就是仅仅一对()的情况
               else{
                   input = input.replace (input.indexOf ("(") , input.indexOf (")") + 1, result);
               }
           }
       }
        //全都替换完之后,计算最终结果
       result = countingMathematical (input.toString ());
       if(result == "zero" )
       {
           return "zero";
       }
       return result;
   }

    /**
     * 计算算式,思路:
     * 利用正则表达式先按加减乘除拆分字符串,先计算并替换掉阶乘,开方和幂计算
     * 再按加减拆分字符串,计算并替换掉乘除,最后在进行加减运算
     * @param strMath  传入的算式字符串
     * @return  返回算式计算结果
     */

    public String countingMathematical(String strMath){
        String result;  //用于记录结果并用于替换
        //利用正则表达式将算式字符串按"+","-","x","÷"进行拆分
        if(strMath.contains ("!") || strMath.contains ("^")){
            String[] array = strMath.split ("\\+|-|x|÷");
            for(int i = 0 ; i < array.length ; ++i){
                //计算阶乘
                if(array[i].contains("!")){
                    result = countFactorial (array[i]);
                    strMath = strMath.replace (array[i] , result);
                }
                //计算幂次
                if(array[i].contains ("^")){
                    result = String.valueOf (Math.pow (
                            Double.parseDouble (array[i].substring (0,array[i].indexOf ("^")))  ,
                            Double.parseDouble (array[i].substring (array[i].indexOf ("^") + 1,array[i].length ()))));
                    strMath = strMath.replace (array[i] , result);
                }
                //计算开方
                if (array[i].contains ("√")){
                    result = String.valueOf (Math.sqrt (
                            Double.parseDouble (array[i].substring (array[i].indexOf ("√"), array[i].length ()))));
                    strMath = strMath.replace (array[i] , result);
                }
            }
        }
        //在算式中有加号和减号时,先乘除后加减,先根据加减号拆分字符串;若没有加减号就是从左往右算
        if((strMath.contains ("×") || strMath.contains ("÷") ) && (strMath.contains ("+") || strMath.contains ("-"))){
            double acc = 1.0;
            String[] array = strMath.split ("\\+|-");//array现在只含乘号或者只含乘号和除号
            for(int i = 0 ; i < array.length ; ++i){
                //算式里有乘号的情况
                if(array[i].contains ("×") ){
                    //先乘后除,若有除号,则应拆分,若没有,下面这一行代码就不起作用,即会array==array1
                    String[] array1 = array[i].split ("÷"); //array1现在只含乘号
                    for(int j = 0 ; j  < array1.length ; ++j ){
                        if(array1[j].contains ("×")){
                            //根据乘号进行拆分
                            String[] array2 = array1[j].split ("×");//array2是乘号的左右项
                            for(int k = 0 ; k < array2.length ; ++k){
                                acc *= Double.parseDouble (array2[k]);
                            }
                            strMath = strMath.replace (array1[j] , String.valueOf (acc));
                            acc = 1.0;
                        }
                    }
                }
                if(strMath.contains("÷")){
                    double  quo = 1.0;
                    String[] array3 = strMath.split ("\\+|-");
                    for(int m = 0 ; m < array3.length ; ++m){
                        if(array3[m].contains("÷")){
                            String[] array4 = array3[m].split("÷");
                            for(int n = 0; n < array4.length ; ++n){
                                if(Double.parseDouble (array3[m]) == 0.0){
                                    return "zero";  //除数不能为0
                                }
                                quo /= Double.parseDouble (array4[n]);
                            }
                            strMath = strMath.replace(array3[m], new DecimalFormat("#.######").format(quo));
                        }
                    }
                }
            }
        }
        //只有乘号和除号就从左往右算
        else if(strMath.contains ("÷") || strMath.contains ("×")){
            double acc = 1.0;
            int j = 0 ; //各个数字的序号
            String[] strings = strMath.split ("×|÷");//先将各个数字分割开
            acc *= Double.parseDouble (strings[j++]);
            char[] chars = strMath.toCharArray ();//将字符串分割成单个字符,便于用于判读符号为乘号还是除号
            for(int i = 0;i < chars.length; ++i ){
                if(chars[i] == '×'){
                    acc *= Double.parseDouble (strings[j]);
                    j++;
                }else if(chars[i] == '÷'){
                    if (Double.parseDouble (strings[j]) == 0.0){
                        return "zero";   //除数不能为0
                    }
                    acc *= 1 / Double.parseDouble (strings[j]);
                }
            }
            //因为不含加减号,所以到这里计算就结束了
            strMath = strMath.replace (strMath,new DecimalFormat("#.######").format (acc));
            return strMath;
        }

        if(strMath.contains ("+") || strMath.contains ("-")){
            double sum = 0.0;
            int j  = 0;    //各个数字的序号
            String[] strings = strMath.split ("\\+|-");//先将各个数字分割开
            sum += Double.parseDouble (strings[j++]);
            char[] chars = strMath.toCharArray ();//将字符串分割成单个字符,便于用于判读符号为加号还是减号
            for(int i = 0 ; i < chars.length ; ++i){
                if (chars[i] == '+'){
                    sum += Double.parseDouble (strings[j]);
                    j++;
                }else if(chars[i] == '-'){
                    sum -= sum += Double.parseDouble (strings[j]);
                    j++;
                }
            }
            strMath = strMath.replace (strMath,new DecimalFormat("#.######").format (sum));
        }
        return strMath;
    }

    /**
     * 计算阶乘
     * @param strFac  传入的阶乘字符串
     * @return  返回阶乘计算结果
     */
    public String countFactorial(String strFac) {

        int number = Integer.parseInt(strFac.substring(0, strFac.length() - 1));
        long sum = 1;
        for (int i = 1; i <= number ; ++i) {
            sum *= i;
        }
        return String.valueOf(sum);
    }
}