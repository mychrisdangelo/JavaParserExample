import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.LiteralExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.visitor.GenericVisitorAdapter;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class ParserExample {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        if (args.length < 1) {
            System.err.println("Usage: <<filename.java>>");
            return;
        }
        FileInputStream in = new FileInputStream(args[0]);

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        
        // visit and print the methods names
        new MethodVisitor().visit(cu, null);
        
        // visit and print the string literals
        new MyGenericVisitor().visit(cu, null);

//        // prints the resulting compilation unit to default system output
//        System.out.println(cu.toString());
    }
    
    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
            System.out.println(n.getName());
        }
    }
    
    private static class MyGenericVisitor extends GenericVisitorAdapter {
    	@Override
    	public Object visit(StringLiteralExpr n, Object arg) {
			System.out.println(n.getValue());
    		return arg;
    		
    	}
    }
}
