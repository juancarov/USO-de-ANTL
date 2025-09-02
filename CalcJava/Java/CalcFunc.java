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

