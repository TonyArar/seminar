public class Calculator {

    double ans = Double.NaN;
    double memory = Double.NaN;

    // speichert das Ergenbnis einer Operation im Speicher
    public void memorize(){
        memory = ans;
    }

    // loescht den Wert im Speicher
    public void forget(){
        this.memory = Double.NaN;
    }

    // Addition
    public double add(double a, double b) {
        return ans = a + b;
    }

    // Multiplikation
    public double mul(double a, double b){
        return ans = a*b;
    }

    // Subtraktion
    public double sub(double a, double b){
        return ans = a-b;
    }

    // Division
    public double div(double a, double b) throws Exception {
        if (b == 0){
            throw new ArithmeticException("Undefined");
        }
        return ans = a/b;
    }

    // Logarithmus zur Basis 2
    public double log2(double a) {
        if (a <= 0){
            throw new ArithmeticException("Undefined");
        }
        return ans = Math.log(a)/Math.log(2);
    }

    // Potenzfunktion
    public double pow(double a, double b){
        double fraction = b - Math.floor(b);
        double result = 1;
        for (int i = 1; i <= (int) b; i++){
            result = result*a;
        }
        if (fraction > 0){
            result = result*Math.pow(10,Math.log10(a)*fraction);
        }
        return ans = result;
    }

    // n-te Fibonacci Zahl, naive Imlementierung
    public int fib(int n) {
        if(n <= 1) {
            return n;
        }
        return (int) (ans = fib(n-1) + fib(n-2));
    }

    // n-te Fibonacci Zahl, schelle rekursive Implementierung
    public int fastFib(int n){
        return (int) (ans = fib_aux(n,0,1));
    }

    // Hilfsfunktion fuer fastFib
    private int fib_aux(int n, int acc_0, int acc_1) {
        if (n == 0){
            return acc_0;
        }
        int temp = acc_1;
        acc_1 = acc_0 + acc_1;
        acc_0 = temp;
        return fib_aux(n-1, acc_0, acc_1);
    }

}

