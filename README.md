# MyCalculator

科学计算器

----

* 功能:
  * 提供π和e两个值
  * 计算sin(),cos(),tan(),log(),ln()函数,n!阶乘
  * 当数的大小大于或小于指定值后用科学计数法表示
  * 输入的文本的字体大小随着输入的文本的增多而逐渐减小
  * 当只有加减时或只有乘除时,从左向右计算;当加减乘除混合运算时,先加减后乘除;若有括号,则优先计算括号内的数
  * 计算数的幂,以及开方,并可以计算类似"2^2^2"求某数的幂的同时计算指数的幂(可以一直延伸)和"√√16"求数的开方的开方(可以一直延伸)等类型的值
  
* 优化:
  * 阶乘:可以计算到170的阶乘 
  * 显示:可以滑动显示部分来查看超出屏幕外的算式
  * . (dot)键:若点击之前无数字,则默认在前面添加"0"，即点击后显示"0.",并防止重复点击
  * +,-,x,÷,^键:点击时,若前面一个字符任为运算符,则将前面的运算符替换掉,并防止重复点击
  * 当输入不合法算式、除数为0以及左右括号数不匹配等情况做出友好提示,若是不合法字符将会显示"Error!"
  * DEL键:若前面是log(,sin(,cos(,tan(,ln(,可以根据相应的长度一键删除,不需要一直点击
  
* 计算原理:
  * 利用正则表达式拆分输入的算式,然后通过不断的运算和替换,最后得到算式的值
