# Calculadora con ANTLR4 (Java y Python)

Este proyecto muestra cómo construir una calculadora básica usando **ANTLR4**, primero en **Java** y luego en **Python**.  
La calculadora soporta operaciones de suma, resta, multiplicación, división, paréntesis y factorial (`!`).  

### Requisitos

Java :

alias antlr4='java -cp /path/to/antlr-4.13.1-complete.jar org.antlr.v4.Tool' 

Python:

sudo apt install python3-antlr4

## Ejemplo Calculadora 

### Gramatica:

<pre>
    grammar LabeledExpr; // rename to distinguish from Expr.g4
    
    prog:   stat+ ;
    
    stat:   expr NEWLINE                # printExpr
        |   ID '=' expr NEWLINE         # assign
        |   NEWLINE                     # blank
        ;
    
    expr:   expr op=('*'|'/') expr      # MulDiv
        |   expr op=('+'|'-') expr      # AddSub
        |   INT                         # int
        |   ID                          # id
        |   '(' expr ')'                # parens
        ;
    
    MUL :   '*' ; // assigns token name to '*' used above in grammar
    DIV :   '/' ;
    ADD :   '+' ;
    SUB :   '-' ;
    ID  :   [a-zA-Z]+ ;      // match identifiers
    INT :   [0-9]+ ;         // match integers
    NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
    WS  :   [ \t]+ -> skip ; // toss out whitespace
</pre>

### Visitor:

<pre>

  import java.util.HashMap;
  import java.util.Map;
  
  public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
      /** "memory" for our calculator; variable/value pairs go here */
      Map<String, Integer> memory = new HashMap<String, Integer>();
  
      /** ID '=' expr NEWLINE */
      @Override
      public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
          String id = ctx.ID().getText();  // id is left-hand side of '='
          int value = visit(ctx.expr());   // compute value of expression on right
          memory.put(id, value);           // store it in our memory
          return value;
      }
  
      /** expr NEWLINE */
      @Override
      public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
          Integer value = visit(ctx.expr()); // evaluate the expr child
          System.out.println(value);         // print the result
          return 0;                          // return dummy value
      }
  
      /** INT */
      @Override
      public Integer visitInt(LabeledExprParser.IntContext ctx) {
          return Integer.valueOf(ctx.INT().getText());
      }
  
      /** ID */
      @Override
      public Integer visitId(LabeledExprParser.IdContext ctx) {
          String id = ctx.ID().getText();
          if ( memory.containsKey(id) ) return memory.get(id);
          return 0;
      }
  
      /** expr op=('*'|'/') expr */
      @Override
      public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
          int left = visit(ctx.expr(0));  // get value of left subexpression
          int right = visit(ctx.expr(1)); // get value of right subexpression
          if ( ctx.op.getType() == LabeledExprParser.MUL ) return left * right;
          return left / right; // must be DIV
      }
  
      /** expr op=('+'|'-') expr */
      @Override
      public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
          int left = visit(ctx.expr(0));  // get value of left subexpression
          int right = visit(ctx.expr(1)); // get value of right subexpression
          if ( ctx.op.getType() == LabeledExprParser.ADD ) return left + right;
          return left - right; // must be SUB
      }
  
      /** '(' expr ')' */
      @Override
      public Integer visitParens(LabeledExprParser.ParensContext ctx) {
          return visit(ctx.expr()); // return child expr's value
      }
  }
</pre>

### Main:

<pre>
  import org.antlr.v4.runtime.*;
  import org.antlr.v4.runtime.tree.ParseTree;
  
  import java.io.FileInputStream;
  import java.io.InputStream;
  
  public class Calc {
      public static void main(String[] args) throws Exception {
          String inputFile = null;
          if ( args.length>0 ) inputFile = args[0];
          InputStream is = System.in;
          if ( inputFile!=null ) is = new FileInputStream(inputFile);
          ANTLRInputStream input = new ANTLRInputStream(is);
          LabeledExprLexer lexer = new LabeledExprLexer(input);
          CommonTokenStream tokens = new CommonTokenStream(lexer);
          LabeledExprParser parser = new LabeledExprParser(tokens);
          ParseTree tree = parser.prog(); // parse
  
          EvalVisitor eval = new EvalVisitor();
          eval.visit(tree);
      }
  }
</pre>

Se siguió unicamente el paso a paso del libro "The Definitive ANLTLR4 Reference", donde se implementó una gramatica, donde definimos tokens y un lenguaje, para luego usar un Visitor que nos genera los archivos necesarios para que el main ejecute la calculadora.

--- 

Relizar operaciones necesarias para que la calcuiladora pueda hacer las siguientes operaciones: 

a. Calcular: Sin (x), Cos(x), Tan(x)
b. Raiz Cuadrada usando sqrt
c. Logaritmo Natural ln(x) y Logaritmo en base 10 Log(x)
d. Considere el uso de grados y de radianes
e. Calcule el factorial de un numero usando "!"

### Gramatica

Esta Gramática es indiferente para el lenguaje objetivo, ya que las reglas que se le otorgan son las mismas.

<pre>
  grammar ExprFunc;
  
  prog:   stat+ ;
  
  stat:   expr NEWLINE                # printExpr
      |   ID '=' expr NEWLINE         # assign
      |   NEWLINE                     # blank
      ;
  
  expr: expr op=('*'|'/') expr      # MulDiv
      | expr op=('+'|'-') expr      # AddSub
      | func '(' expr ')'           # FuncExpr
      | expr '!'                    # FactorialExpr
      | INT                         # Int
      | FLOAT			                  # Float
      | ID                          # Id
      | '(' expr ')'                # Parens
      ;
  
  func:   'sin'
      |   'cos'
      |   'tan'
      |   'sqrt'
      |   'log'
      |   'ln'
      ;
  
  MUL :   '*' ;
  DIV :   '/' ;
  ADD :   '+' ;
  SUB :   '-' ;
  ID  :   [a-zA-Z]+ ;
  INT :   [0-9]+ ;
  FLOAT:	[0-9]+'.'[0-9]+;
  NEWLINE:'\r'? '\n' ;
  WS  :   [ \t]+ -> skip ;

</pre>

Creamos un apartado llamado func, para que las expreciones sean diferentes a las funciones y el visitor cree de forma mas ordenada todo lo necesario. Además, añadimos numero en Float para que la applicación no falle si usamos numero decimales.

### Visitor

<pre>
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
</pre>

El visitor básico de la calculadora solo implementa operaciones aritméticas simples como suma, resta, multiplicación, división y paréntesis. En cambio, el visitor extendido agrega soporte para números decimales, factoriales y funciones matemáticas (sin, cos, tan, sqrt, log, ln). La lógica es la misma —recorrer el árbol sintáctico—, pero se sobrescriben más métodos y se añade validación extra (por ejemplo, que el factorial solo aplique a enteros).

### Main

<pre>
  import org.antlr.v4.runtime.*;
  import org.antlr.v4.runtime.tree.ParseTree;
  
  import java.io.FileInputStream;
  import java.io.InputStream;
  
  public class CalcFunc {
      public static void main(String[] args) throws Exception {
          String inputFile = null;
          if (args.length > 0) inputFile = args[0];
          InputStream is = System.in;
          if (inputFile != null) is = new FileInputStream(inputFile);
  
          CharStream input = CharStreams.fromStream(is);
  
          // Lexer y parser generados por ExprFunc.g4
          ExprFuncLexer lexer = new ExprFuncLexer(input);
          CommonTokenStream tokens = new CommonTokenStream(lexer);
          ExprFuncParser parser = new ExprFuncParser(tokens);
  
          ParseTree tree = parser.prog();
  
          EvalVisitor eval = new EvalVisitor();
          eval.visit(tree);
      }
  }

</pre>

El main mantiene la misma estructura del ejemplo, ya que unicamente llama los demás archivos con el fin de que se ejecute todo junto.

---

Implemente la misma calculadora, pero usando python como lengiaje objetivo.

Para implementar la calculadora en ANTLR con lenguaje objetivo python, debemos unicamente "traducir" tanto el visitor como el main, la gramatica "archivo.g4" se mantiene igual.

### Visitor 

<pre>

from ExprFuncVisitor import ExprFuncVisitor
from ExprFuncParser import ExprFuncParser
import math

class EvalVisitor(ExprFuncVisitor):
    def __init__(self):
        self.memory = {}
        self.use_degrees = True

    def angle(self, x):
        return math.radians(x) if self.use_degrees else x

    def visitAssign(self, ctx:ExprFuncParser.AssignContext):
        id_ = ctx.ID().getText()
        value = self.visit(ctx.expr())
        self.memory[id_] = value
        return value

    def visitPrintExpr(self, ctx:ExprFuncParser.PrintExprContext):
        value = self.visit(ctx.expr())
        print(value)
        return 0.0

    def visitInt(self, ctx:ExprFuncParser.IntContext):
        return float(ctx.INT().getText())

    def visitFloat(self, ctx:ExprFuncParser.FloatContext):
        return float(ctx.FLOAT().getText())

    def visitId(self, ctx:ExprFuncParser.IdContext):
        id_ = ctx.ID().getText()
        return self.memory.get(id_, 0.0)

    def visitMulDiv(self, ctx:ExprFuncParser.MulDivContext):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == ExprFuncParser.MUL:
            return left * right
        return left / right

    def visitAddSub(self, ctx:ExprFuncParser.AddSubContext):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == ExprFuncParser.ADD:
            return left + right
        return left - right

    def visitParens(self, ctx:ExprFuncParser.ParensContext):
        return self.visit(ctx.expr())

    def visitFuncExpr(self, ctx:ExprFuncParser.FuncExprContext):
        value = self.visit(ctx.expr())
        func = ctx.func().getText()
        if func == "sin": return math.sin(self.angle(value))
        if func == "cos": return math.cos(self.angle(value))
        if func == "tan": return math.tan(self.angle(value))
        if func == "sqrt": return math.sqrt(value)
        if func == "log": return math.log10(value)
        if func == "ln": return math.log(value)
        raise Exception(f"Función desconocida: {func}")

    def visitFactorialExpr(self, ctx:ExprFuncParser.FactorialExprContext):
        value = self.visit(ctx.expr())
        if not value.is_integer():
            raise Exception(f"Factorial solo definido para enteros: {value}")
        return self.factorial(int(value))

    def factorial(self, n: int):
        if n < 0:
            raise Exception(f"Factorial no definido para negativos: {n}")
        result = 1
        for i in range(2, n+1):
            result *= i
        return result
</pre>

### Main

<pre>
import sys
from antlr4 import *
from ExprFuncLexer import ExprFuncLexer
from ExprFuncParser import ExprFuncParser
from EvalVisitor import EvalVisitor

def main(argv):
    input_stream = FileStream(argv[1] if len(argv) > 1 else None or sys.stdin.fileno())
    lexer = ExprFuncLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = ExprFuncParser(stream)
    tree = parser.prog()

    visitor = EvalVisitor()
    visitor.visit(tree)

if __name__ == '__main__':
    main(sys.argv)
</pre>

