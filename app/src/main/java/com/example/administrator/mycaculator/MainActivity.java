package com.example.administrator.mycaculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView display;
    private Button  clear,tan,sin,cos,fac,back,
                            right,left,square,sqrt,div,add,
                            seven,eight,nine,sub,mul,four,
                            five,six,one,two,three,zero,dot,equal;
    private StringBuffer str_display = new StringBuffer(); //显示
    private String str_result ;//结果显示
    private boolean flag = true;//小数点开关控制
    private double num1;
    private double num2;
    private boolean f_add,f_sub,f_div,f_mul,f_square; //运算符开关控制
    private String for_equal = "+";//用于最后点击"="时的判定
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        requestWindowFeature (Window.FEATURE_NO_TITLE);
        setContentView (R.layout.activity_main);

        display = (EditText) findViewById (R.id.display);
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
                if(flag == true){
                    str_display.append (".");
                    display.setText (str_display.toString ());
                    flag = false;
                }
                break;
            case R.id.add:
                for_equal = "+";
                if("" != str_display.toString () ){
                    num1 += Double.parseDouble (str_display.toString ());
                    str_display = new StringBuffer ("");
                }
                if(null != str_result){
                    num1 = Double.parseDouble (str_result);
                    str_result = null;
                }
                display.setText(String.valueOf(num1));
                flag = true;
                break;
            case R.id.sub:
                for_equal = "-";
                if(!f_sub && "" != str_display.toString () ){
                    num1 = Double.parseDouble (str_display.toString ());
                    display.setText(String.valueOf(num1));
                    str_display = new StringBuffer("");
                    f_sub = true;
                }else{
                    if("" != str_display.toString () ){
                        num1 -= Double.parseDouble(str_display.toString ());
                        str_display = new StringBuffer ("");
                    }
                    if(null != str_result){
                        num1 = Double.parseDouble (str_result);
                        str_result = null;
                    }
                    display.setText (String.valueOf (num1));
                }
                break;
            case R.id.mul:
                for_equal = "×";
                if( !f_mul && "" != str_display.toString ()){
                    num1 *= Double.parseDouble (str_display.toString ());
                    display.setText(String.valueOf(num1));
                    str_display = new StringBuffer();
                    f_mul = true;
                }else {
                    if("" != str_display.toString () ){
                        num1 *= Double.parseDouble (str_display.toString ());
                        str_display = new StringBuffer ("");
                    }
                    if(null != str_result){
                        num1 = Double.parseDouble (str_result);
                        str_result = null;
                    }
                    display.setText (String.valueOf (num1));
                }
                flag = true;
                break;
            case R.id.square:
                for_equal = "^";
                display.setText (square.getText ().toString ());
                break;
            case R.id.sqrt:
                for_equal = "√";
                display.setText (sqrt.getText ().toString ());
                break;
            case R.id.div:
                for_equal = "÷";
//                display.setText (div.getText ().toString ());
                if(!f_div && "" != str_display.toString ()){
                    num1  = Double.parseDouble (str_display.toString ());
                    display.setText (String.valueOf (num1));
                    str_display = new StringBuffer ("");
                    f_div = true;
                }else {
                    if("" != str_display.toString ()){
                        if(Double.parseDouble (str_display.toString ()) == 0){
                            Toast.makeText (MainActivity.this,"除数不能为0!",Toast.LENGTH_LONG).show ();
                        }else {
                            num1 /= Double.parseDouble (str_display.toString ());
                            str_display = new StringBuffer ("");
                        }
                    }
                    if(null != str_result){
                        num1 = Double.parseDouble (str_result);
                        str_result = null;
                    }
                    flag = true;
                }
                break;
            case R.id.tan:
//                str_display.append ("tan");
//                display.setText (str_display.toString ());
                if("" != str_display.toString ()){
                   num1 = Math.tan (Double.parseDouble (str_display.toString ()));
                    display.setText(String.valueOf(num1));
                    str_display = new StringBuffer();
                }else {
                    if(null != str_result){
                        num1 = Double.parseDouble (str_result);
                        str_result = null;
                    }
                    display.setText (String.valueOf (num1));
                }
                flag = true;
                break;
            case R.id.sin:
//                str_display.append ("sin");
//                display.setText (str_display.toString ());
                if("" != str_display.toString ()){
                    num1 = Math.sin (Double.parseDouble (str_display.toString ()));
                    display.setText(String.valueOf(num1));
                    str_display = new StringBuffer();
                }else {
                    if(null != str_result){
                        num1 = Double.parseDouble (str_result);
                        str_result = null;
                    }
                    display.setText (String.valueOf (num1));
                }
                flag = true;
                break;
            case R.id.cos:
//                str_display.append ("cos");
//                display.setText (str_display.toString ());
                if("" != str_display.toString ()){
                    num1 = Math.cos (Double.parseDouble (str_display.toString ()));
                    display.setText(String.valueOf(num1));
                    str_display = new StringBuffer();
                }else {
                    if(null != str_result){
                        num1 = Double.parseDouble (str_result);
                        str_result = null;
                    }
                    display.setText (String.valueOf (num1));
                }
                flag = true;
                break;
            case R.id.fac:
//                str_display.append ("!");
//                display.setText (str_display.toString ());

                break;
            case R.id.back:
                if(str_display.length () != 0){
                    str_display.deleteCharAt(str_display.length () - 1);
                    display.setText (str_display.toString ());
                }
                if(str_display.indexOf (".") == -1 && flag == false){
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
            case R.id.clear:
                str_display = new StringBuffer ("");
                str_result = null;
                num1 = 0;
                num2 = 0;
                flag = true;
                f_sub = false;
                f_div = false;
                f_add = false;
                f_mul = false;
                f_square = false;
                for_equal = "+";
                display.setText ("0.0");
                break;
            case R.id.equal:
                if("" == str_display.toString ()){
                    Toast.makeText (MainActivity.this,"内容为空!",Toast.LENGTH_SHORT).show();
                }else{
                    if(for_equal.equals ("+")){
                        num2 = Double.parseDouble (str_display.toString ());
                        str_result = String.valueOf (num1 + num2);
                        display.setText (str_result);
                        str_display = new StringBuffer ("");
                    }
                    if(for_equal.equals ("-")){
                        num2 = Double.parseDouble (str_display.toString ());
                        str_result = String.valueOf (num1 - num2);
                        display.setText (str_result);
                        str_display = new StringBuffer ("");
                    }
                    if(for_equal.equals ("×")){
                        num2 = Double.parseDouble (str_display.toString ());
                        str_result = String.valueOf (num1 * num2);
                        display.setText (str_result);
                        str_display = new StringBuffer ("");
                    }
                    if(for_equal.equals ("÷")){
                        num2 = Double.parseDouble (str_display.toString ());
                        str_result = String.valueOf (num1 / num2);
                        display.setText (str_result);
                        str_display = new StringBuffer ("");
                    }
                }
                break;
            default:
                break;
        }
    }
}
