import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ExprFuncBaseVisitor<Double> {
    /** "memory" para variables */
    Map<String, Double> memory = new HashMap<>();

    /** Configuración: usar grados o radianes para trigonometría */
    private boolean useDegrees = true;

    private double angle(double x) {
        return useDegrees ? Math.toRadians(x) : x;
    }

    /** ID '=' expr NEWLINE */
    @Override
    public Double visitAssign(ExprFuncParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        double value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    /** expr NEWLINE */
    @Override
    public Double visitPrintExpr(ExprFuncParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());
        System.out.println(value);
        return 0.0;
    }

    /** INT */
    @Override
    public Double visitInt(ExprFuncParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    /** FLOAT */
    @Override
    public Double visitFloat(ExprFuncParser.FloatContext ctx) {
        return Double.valueOf(ctx.FLOAT().getText());
    }

    /** ID */
    @Override
    public Double visitId(ExprFuncParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0.0;
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Double visitMulDiv(ExprFuncParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        return ctx.op.getType() == ExprFuncParser.MUL ? left * right : left / right;
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Double visitAddSub(ExprFuncParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        return ctx.op.getType() == ExprFuncParser.ADD ? left + right : left - right;
    }

    /** '(' expr ')' */
    @Override
    public Double visitParens(ExprFuncParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    /** func '(' expr ')' */
    @Override
    public Double visitFuncExpr(ExprFuncParser.FuncExprContext ctx) {
        double value = visit(ctx.expr());
        String funcName = ctx.func().getText();

        switch (funcName) {
            case "sin": return Math.sin(angle(value));
            case "cos": return Math.cos(angle(value));
            case "tan": return Math.tan(angle(value));
            case "sqrt": return Math.sqrt(value);
            case "log": return Math.log10(value);
            case "ln": return Math.log(value);
            default: throw new RuntimeException("Función desconocida: " + funcName);
        }
    }

    /** expr '!' */
    @Override
    public Double visitFactorialExpr(ExprFuncParser.FactorialExprContext ctx) {
        double value = visit(ctx.expr());
        long intValue = (long) value;

        if (value != intValue) {
            throw new RuntimeException("Factorial solo definido para enteros: " + value);
        }

        return factorial(intValue);
    }

    private double factorial(long n) {
        if (n < 0) throw new RuntimeException("Factorial no definido para negativos: " + n);
        long result = 1;
        for (long i = 2; i <= n; i++) {
            result *= i;
        }
        return (double) result;
    }
}

