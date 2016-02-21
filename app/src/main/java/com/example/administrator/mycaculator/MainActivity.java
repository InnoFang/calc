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
    private StringBuffer str_display = new StringBuffer(""); //显示
    private boolean flag = true;//小数点开关控制
    private boolean result_flag = false;
    //在点击等号之后为"true",若是在按完等号之后直接点数字,则默认清空str_display
    //若是按完等号之后点击了运算符,就不清空str_display
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        requestWindowFeature (Window.FEATURE_NO_TITLE);
        setContentView (R.layout.activity_main);

        display = (Display) findViewById (R.id.display);
        Button log = (Button) findViewById (R.id.log);
        Button ln = (Button) findViewById (R.id.ln);
        Button pi = (Button) findViewById (R.id.pi);
        Button e = (Button) findViewById (R.id.e);
        Button clear = (Button) findViewById (R.id.clear);
        Button tan = (Button) findViewById (R.id.tan);
        Button sin = (Button) findViewById (R.id.sin);
        Button cos = (Button) findViewById (R.id.cos);
        Button fac = (Button) findViewById (R.id.fac);
        Button back = (Button) findViewById (R.id.del);
        Button right = (Button) findViewById (R.id.right);
        Button left = (Button) findViewById (R.id.left);
        Button square = (Button) findViewById (R.id.square);
        Button sqrt = (Button) findViewById (R.id.sqrt);
        Button div = (Button) findViewById (R.id.div);
        Button add = (Button) findViewById (R.id.add);
        Button seven = (Button) findViewById (R.id.seven);
        Button eight = (Button) findViewById (R.id.eight);
        Button nine = (Button) findViewById (R.id.nine);
        Button sub = (Button) findViewById (R.id.sub);
        Button mul = (Button) findViewById (R.id.mul);
        Button four = (Button) findViewById (R.id.four);
        Button five = (Button) findViewById (R.id.five);
        Button six = (Button) findViewById (R.id.six);
        Button one = (Button) findViewById (R.id.one);
        Button two = (Button) findViewById (R.id.two);
        Button three = (Button) findViewById (R.id.three);
        Button zero = (Button) findViewById (R.id.zero);
        Button dot = (Button) findViewById (R.id.dot);
        Button equal = (Button) findViewById (R.id.equal);

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
    }

    @Override
    public void onClick (View view) {
        String str_result ;//结果显示
        switch(view.getId ()){
            case R.id.nine:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("9");
                display.setText (str_display.toString ());
                break;
            case R.id.eight:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("8");
                display.setText (str_display.toString ());
                break;
            case R.id.seven:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("7");
                display.setText (str_display.toString ());
                break;
            case R.id.six:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("6");
                display.setText (str_display.toString ());
                break;
            case R.id.five:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("5");
                display.setText (str_display.toString ());
                break;
            case R.id.four:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("4");
                display.setText (str_display.toString ());
                break;
            case R.id.three:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("3");
                display.setText (str_display.toString ());
                break;
            case R.id.two:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("2");
                display.setText (str_display.toString ());
                break;
            case R.id.one:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("1");
                display.setText (str_display.toString ());
                break;
            case R.id.zero:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("0");
                display.setText (str_display.toString ());
                break;
            case R.id.dot:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
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
                result_flag = false;
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
                result_flag = false;
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
                result_flag = false;
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
                result_flag = false;
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
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append("√");
                display.setText (str_display.toString ());
                break;
            case R.id.div:
                result_flag = false;
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
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("sin(");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.cos:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("cos(");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.tan:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("tan(");
                display.setText (str_display.toString ());
                flag = true;
                break;
            case R.id.fac:
                result_flag = false;
                str_display.append ("!");
                display.setText (str_display.toString ());
                break;
            case R.id.del:
                //在得到结果后,如果点击了del键就清空,并且打开dot的开关,没必要再继续执行下面的代码
                if(result_flag) {
                     str_display = new StringBuffer ("");
                    display.setText (str_display.toString ());
                    flag= true;
                    result_flag = false;
                    break;
            }
                /*
                 * 检测字符串,为back按钮的删除方式提供依据
                 * 若back_index = 4,表示str尾部为sin(、cos(、tan(、log(中的一个,应当一次删除4个
                 * 若back_index = 3,表示str尾部为ln(,应当一次删除2个
                 * 若back_index = 1,表示为除返回3、2外的所有情况，只需删除一个
                 */
                int back_index;//为back按钮的删除方式提供依据,默认为0
                try {
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
                    }else{
                        back_index = 1;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    Toast.makeText (MainActivity.this,"内容为空!",Toast.LENGTH_SHORT).show ();
                    break;
                }
                if(!str_display.toString ().equals ("")){
                    str_display.delete(str_display .length () - back_index,str_display.length ());
                    display.setText (str_display.toString ());
                }
                //在删除"."之后恢复"."的点击
                if(str_display.indexOf (".") == -1 && !flag){
                    flag = true;
                }
                break;
            case R.id.right:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append (")");
                display.setText (str_display.toString ());
                break;
            case R.id.left:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("(");
                display.setText (str_display.toString ());
                break;
            case R.id.log:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("log(");
                display.setText (str_display.toString ());
                break;
            case R.id.ln:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("ln(");
                display.setText (str_display.toString ());
                break;
            case R.id.pi:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("π");
                display.setText (str_display.toString ());
                break;
            case R.id.e:
                if (result_flag){
                    str_display = new StringBuffer ("");
                    result_flag = false;
                }
                str_display.append ("e");
                display.setText (str_display.toString ());
                break;
            case R.id.clear:
                str_display = new StringBuffer ("");
                flag = true;
                display.setText ("0.0");
                break;
            case R.id.equal:
                result_flag = true;
                try {
                    str_result = countingResult (str_display);
                    switch (str_result) {
                        case "mismatch":
                            Toast.makeText (MainActivity.this, "你的左括号数与右括号数不相等!\n            请检查后重新输入!", Toast.LENGTH_SHORT).show ();
                            break;
                        case "zero":
                            Toast.makeText (MainActivity.this, "     除数不能为0!\n请检查后重新输入!", Toast.LENGTH_SHORT).show ();
                            break;
                        default:
                            display.setText (str_result);
                            str_display = new StringBuffer (str_result);
                            break;
                       }
                } catch (Exception e) {
                    Toast.makeText (MainActivity.this,"你输入的内容有误!\n请检查后重新输入!",Toast.LENGTH_SHORT).show ();
                    String error = "Error!";
                    display.setText (error);
                    str_display = new StringBuffer ("");
                    break;
                }
                break;
            default:
                break;
                }
    }
   public String  countingResult(StringBuffer input) throws Exception{
       String mathematical ; //用来保存括号内的算式
       String result;    //用来获得算式的结果
       String temp;      //用于替换
        //如果有首尾括号则去除括号
       while(input.indexOf ("(") == 0 && input.lastIndexOf (")") == input.length () - 1){
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
       for(char str_inputs : str_input){
           if (str_inputs == ')'){
               right_num ++;
           }else if(str_inputs == '('){
               left_num ++;
           }
       }
       if(right_num != left_num){
           //如果不等就返回mismatch,并做出友好提示
           return "mismatch";
       }
       //为了防止(在同一个if语句里面判断)出现StringIndexOutOfBoundsException,所以在这里分开进行讨论
       while(input.indexOf ("(") != -1 && input.indexOf (")") != -1){
           if(input.toString().contains("ln")){
               //计算ln类型
               while(input.indexOf ("(") != -1 && input.indexOf (")") != -1){
                   //先获得第一对括号里的算式
                   mathematical = input.substring (input.indexOf ("(") + 1, input.indexOf (")"));
                   //计算获得的算式,并获得计算结果
                   result = countingMathematical (mathematical);
                   if(input.substring (input.indexOf ("(") - 2,input.indexOf ("(")).equals ("ln")) {
                       temp = new DecimalFormat ("#.########").format (Math.log (Double.parseDouble (result)));//log默认以e为底
                       //将ln()替换掉
                       input = input.replace (input.indexOf ("(") - 2, input.indexOf (")") + 1, temp);
                   }
               }
           }else if(input.toString().contains("sin") ||input.toString().contains("cos") ||input.toString().contains("tan") ||input.toString().contains("log") ){
               //先获得第一对括号里的算式
               mathematical = input.substring (input.indexOf ("(") + 1, input.indexOf (")"));
               //计算获得的算式,并获得计算结果
               result = countingMathematical (mathematical);
               //先判断类型再计算
               //计算sin()类型
               if(input.substring (input.indexOf ("(") - 3,input.indexOf ("(")).equals ("sin")){
                   temp = new DecimalFormat ("#.########").format (Math.sin (Double.parseDouble (result)));
                   //将sin()替换掉
                   input = input.replace (input.indexOf ("(") - 3,input.indexOf (")") + 1,temp);
               }
               // 计算cos()类型
               else if(input.substring (input.indexOf ("(") - 3,input.indexOf ("(")).equals ("cos")){
                   temp = new DecimalFormat ("#.########").format (Math.cos (Double.parseDouble (result)));
                   //将cos()替换掉
                   input = input.replace (input.indexOf ("(") - 3,input.indexOf (")") + 1,temp);
               }
               // 计算tan()类型
               else if(input.substring (input.indexOf ("(") - 3,input.indexOf ("(")).equals ("tan")){
                   temp = new DecimalFormat ("#.########").format (Math.tan (Double.parseDouble (result)));
                   //将tan()替换掉
                   input = input.replace (input.indexOf ("(") - 3,input.indexOf (")") + 1,temp);
               }
               // 计算log()类型
               else if(input.substring (input.indexOf ("(") - 3,input.indexOf ("(")).equals ("log")) {
                   temp = new DecimalFormat ("#.########").format (Math.log10 (Double.parseDouble (result)));
                   //将log()替换掉
                   input = input.replace (input.indexOf ("(") - 3, input.indexOf (")") + 1, temp);
               }
           }else {
               //最后一种情况,即就是仅仅一对()的情况
               //先获得第一对括号里的算式
               mathematical = input.substring (input.indexOf ("(") + 1, input.indexOf (")"));
               //计算获得的算式,并获得计算结果
               result = countingMathematical (mathematical);
               if(!result.equals("")){
                   input = input.replace (input.indexOf ("("), input.indexOf (")") + 1, result);
               }
           }
       }
        //全都替换完之后,计算最终结果
       result = countingMathematical (input.toString ());
       if(result.equals ("zero"))
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
    public String countingMathematical(String strMath) throws Exception{
        String result;  //用于记录算式结果并用于替换
        //替换π和e
        if(strMath.contains ("π")){
            strMath = strMath.replaceAll ("π",String.valueOf (Math.PI));
        }
        if (strMath.contains ("e")){
            strMath = strMath.replaceAll ("e",String.valueOf (Math.E));
        }
        //利用正则表达式将算式字符串按"+","-","x","÷"进行拆分
        if(strMath.contains ("!") || strMath.contains ("^") || strMath.contains ("√")) {
            String[] array = strMath.split ("\\+|-|x|÷");
            for (String arrays : array) {
                //计算阶乘
                if (arrays.contains ("!")) {
                    result = countFactorial (arrays);
                    strMath = strMath.replace (arrays, result);
                }
                //计算幂次
                //若有多个"^",即类似"2^2^2"的形式,就执行前面的条件,否则执行后面的条件
                if(arrays.contains ("^") && arrays.indexOf("^") != arrays.lastIndexOf("^")){
                    String[] squares = arrays.split("\\^");
                    int i;
                    String temp =  new DecimalFormat ("#.##").format (Math.pow (
                            Double.parseDouble (squares[squares.length-2]),
                            Double.parseDouble (squares[squares.length-1])));
                    for(i = squares.length - 3 ; i >= 0 ; --i){
                        temp = new DecimalFormat ("#.##").format (Math.pow (
                                Double.parseDouble (squares[i]),
                                Double.parseDouble (temp)));
                    }
                    if(i < 0){
                        strMath = strMath.replace (arrays, temp);
                    }
                }else if(arrays.contains ("^")){
                    result = new DecimalFormat ("#.##").format (Math.pow (
                            Double.parseDouble (arrays.substring (0, arrays.indexOf ("^"))),
                            Double.parseDouble (arrays.substring (arrays.lastIndexOf ("^") + 1, arrays.length ()))));
                    strMath = strMath.replace (arrays, result);
                }
                //计算开方
                //若有多个"√",即类似"√√16"的形式,就执行前面的条件,否则执行后面的条件
                String temp;			//保存上一个"√"的值
                String arraysChange;//用来在最后把计算完后的值替换掉当前的这个字符串
                if(arrays.contains("√") && arrays.indexOf("√") != arrays.lastIndexOf("√")){
                    arraysChange = arrays;
                    temp = new DecimalFormat ("#.########").format (Math.sqrt (
                            Double.parseDouble (arraysChange.substring (arraysChange.lastIndexOf ("√") + 1, arraysChange.length ()))));
                    arraysChange = arraysChange.replace(arraysChange.substring(arraysChange.lastIndexOf("√")), temp);
                    while(arraysChange.contains("√")){
                        temp = new DecimalFormat ("#.########").format (Math.sqrt (
                                Double.parseDouble (temp)));
                        arraysChange = arraysChange.replace(arraysChange.substring(arraysChange.lastIndexOf("√"),arraysChange.length()), temp);
                    }
                    strMath = strMath.replace (arrays, arraysChange);
                }else if(arrays.contains("√")){
                    result = new DecimalFormat ("#.########").format (Math.sqrt (
                            Double.parseDouble (arrays.substring (arrays.indexOf ("√") + 1, arrays.length ()))));
                    strMath = strMath.replace (arrays.substring (arrays.indexOf ("√"), arrays.length ()), result);
                }
            }
        }
        //在算式中有加号和减号时,先乘除后加减,先根据加减号拆分字符串;若没有加减号就是从左往右算
        if((strMath.contains ("×") || strMath.contains ("÷") ) && (strMath.contains ("+") || strMath.contains ("-"))){
            double acc = 1.0;
            String[] array = strMath.split ("\\+|-|÷");//array现在只含乘号或者只含乘号和除号
            for(String arrays : array) {
                //算式里有乘号的情况
                if (arrays.contains ("×")) {
                    //据乘号进行拆分
                    String[] array2 = arrays.split ("×");//array2是乘号的左右项
                    for (String arrays2 : array2) {
                        acc *= Double.parseDouble (arrays2);
                    }
                    strMath = strMath.replace (arrays, String.valueOf (acc));
                    acc = 1.0;
                }
            }
                if(strMath.contains("÷")) {
                    double quo;
                    String[] array3 = strMath.split ("\\+|-");
                    for (String arrays3 : array3) {
                        if (arrays3.contains ("÷")) {
                            String[] array4 = arrays3.split ("÷");
                            quo = Double.parseDouble (array4[0]);
                            for (int i = 1; i < array4.length; ++i) {
                                if (Double.parseDouble (array4[i]) == 0.0) {
                                    return "zero";  //除数不能为0
                                }
                                quo /= Double.parseDouble (array4[i]);
                            }
                            strMath = strMath.replace (arrays3, new DecimalFormat ("#.######").format (quo));
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
            for(char chars1 : chars){
                if(chars1 == '×'){
                    acc *= Double.parseDouble (strings[j]);
                    j++;
                }else if(chars1 == '÷'){
                    if (Double.parseDouble (strings[j]) == 0.0){
                        return "zero";   //除数不能为0
                    }
                    acc *= 1 / Double.parseDouble (strings[j]);
                }
            }
            //因为不含加减号,所以到这里计算就结束了
            strMath = strMath.replace (strMath,new DecimalFormat("#.########").format (acc));
            if(strMath.length () > 11){
                strMath = new DecimalFormat ("#.######E000").format (Double.parseDouble (strMath));
            }
            return strMath;
        }
        //若字符串首位为"-",则认为是负数,直接返回
        if(strMath.indexOf("-") == 0){
            return strMath;
        }
        if(strMath.contains ("+") || strMath.contains ("-")){
            double sum = 0.0;
            int j  = 0;    //各个数字的序号
            String[] strings = strMath.split ("\\+|-");//先将各个数字分割开
            sum += Double.parseDouble (strings[j++]);
            char[] chars = strMath.toCharArray ();//将字符串分割成单个字符,便于用于判读符号为加号还是减号
            for(char chars1 : chars){
                if (chars1 == '+'){
                    sum += Double.parseDouble (strings[j]);
                    j++;
                }else if(chars1 == '-'){
                    sum -= Double.parseDouble (strings[j]);
                    j++;
                }
            }
            strMath = strMath.replace (strMath,new DecimalFormat("#.########").format (sum));
        }
        if(strMath.length () > 11){
            strMath = new DecimalFormat ("#.######E000").format (Double.parseDouble (strMath));
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
        if(sum >= Long.MAX_VALUE/100000000){
            return new DecimalFormat ("#.######E000").format (sum);
        }
        return String.valueOf(sum);
    }
}