package visitor;

import codegenerator.CodeGenerator;
import com.antlr.MiniJavaBaseVisitor;
import com.antlr.MiniJavaParser;

import java.util.HashMap;
import java.util.Map;

public class javaVisitor extends MiniJavaBaseVisitor<Void> {

    public Map<String, String> getSymtable() {
        return symtable;
    }

    public void setSymtable(Map<String, String> symtable) {
        this.symtable = symtable;
    }

    private Map<String, String> symtable = new HashMap<>(); //to store values


    //below is the psvm program values call
    @Override
    public Void visitProgram(MiniJavaParser.ProgramContext ctx){
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitMainClass(MiniJavaParser.MainClassContext ctx) {
        String className = ctx.IDENTIFIER(0).getText();
        String argument = ctx.IDENTIFIER(1).getText();
        String printValue = ctx.statement().expression(0).IDENTIFIER().getText();
        symtable.put("mainClassName", className);
        symtable.put("argument", argument);
        symtable.put("printvalue", printValue);
        return super.visitMainClass(ctx);
    }

    @Override
    public Void visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        System.out.println("Visiting class declaration...");
        return super.visitClassDeclaration(ctx);
    }

    @Override
    public Void visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) {
        System.out.println("Visiting variable declaration...");
        return super.visitVarDeclaration(ctx);
    }

    @Override
    public Void visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        System.out.println("Visiting method declaration...");
        return super.visitMethodDeclaration(ctx);
    }

    @Override
    public Void visitType(MiniJavaParser.TypeContext ctx){
        System.out.println("Visiting type declaration");
        return super.visitType(ctx);
    }

    @Override
    public Void visitStatement(MiniJavaParser.StatementContext ctx){
        return super.visitStatement(ctx);
    }

    @Override
    public Void visitExpression(MiniJavaParser.ExpressionContext ctx){
        return super.visitExpression(ctx);
    }

//    public byte[] getBytecode() {
//        cw.visitEnd();
//        return cw.toByteArray();
//    }
//    CodeGenerator codeGenerator = new CodeGenerator(symtable);
}
