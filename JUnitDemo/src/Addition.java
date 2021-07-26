public class Addition implements ArithmeticExpression{

    ArithmeticExpression exp1;
    ArithmeticExpression exp2;

    public ArithmeticExpression getExp1() {
        return exp1;
    }

    public ArithmeticExpression getExp2() {
        return exp2;
    }

    public Addition(ArithmeticExpression exp1, ArithmeticExpression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public int evaluate() {
        return exp1.evaluate() + exp2.evaluate();
    }

}
