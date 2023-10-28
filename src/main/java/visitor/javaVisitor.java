package visitor;

import com.antlr.MiniJavaBaseVisitor;
import com.antlr.MiniJavaParser;
import segment.CentralStorage;
import segment.mainClassSegment.mainClassSegment;

import java.util.HashMap;
import java.util.Map;

public class javaVisitor extends MiniJavaBaseVisitor<Void> {
    CentralStorage storage = CentralStorage.getInstance();

    //below is the psvm program values call
    @Override
    public Void visitProgram(MiniJavaParser.ProgramContext ctx){
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitMainClass(MiniJavaParser.MainClassContext ctx) {
        String className = ctx.IDENTIFIER(0).getText();
        String printValue = ctx.statement(0).expression(0).STRING_LITERAL().getText();
        CentralStorage storage = CentralStorage.getInstance();
        mainClassSegment mainClassSegment = new mainClassSegment();
        storage.setMainClassSegment(mainClassSegment);
        storage.getMainClassSegment().setMainClassName(className);
        storage.getMainClassSegment().setPrintValue(printValue);
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

}
