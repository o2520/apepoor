/**
 * Created by yougq on 2017/6/2.
 */
public class ArithmeticExpression {

    private String infix;
    private int index;

    public ArithmeticExpression(String infix) {
        this.infix = infix;
        this.index = 0;
    }

    public int intValue(){
        int value1 = term();
        while (this.index < this.infix.length()){
            char op = this.infix.charAt(this.index);
            if(op == '+' || op == '-'){
                this.index++;
                int value2 = term();
                switch (op){
                    case '+': value1 += value2; break;
                    case '-': value1 -= value2; break;
                }
            }else break;
        }
        return value1;
    }

    private int term() {
        int value1 = factor();
        while (this.index < this.infix.length()){
            char op = this.infix.charAt(this.index);
            if(op == '*' || op == '/' || op == '%'){
                this.index++;
                int value2 = factor();
                switch (op){
                    case '*': value1 *= value2; break;
                    case '/': value1 /= value2; break;
                    case '%': value1 %= value2; break;
                }
            }else break;
        }
        return value1;
    }

    private int factor() {
        if(this.infix.charAt(this.index)=='('){
            this.index++;
            int value = intValue();
            this.index++;
            return value;
        }
        return constant();
    }

    private int constant() {
        if(this.index < this.infix.length()){
            char op = this.infix.charAt(this.index);
            int sign = 1;
            int value = 0;
            if(op == '+' || op == '-'){
                sign = op=='-'?-1:1;
                this.index++;
            }
            while (this.index < this.infix.length()){
                char ch = this.infix.charAt(this.index);
                if(ch >= '0' && ch <= '9'){
                    value = value * 10 + ch - '0';
                    this.index++;
                }else break;
            }
            return value * sign;
        }
        return 0;
    }

    public static void main(String[] args) {
        String infix = "+123+10*(+53-49+20)/((-25+35)*2+10)+(-11)+0";
        System.out.println(new ArithmeticExpression(infix).intValue());
    }
}
