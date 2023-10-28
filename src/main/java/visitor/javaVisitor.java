package visitor;

import com.antlr.MiniJavaBaseVisitor;
import com.antlr.MiniJavaParser;
import segment.CentralStorage;
import segment.mainClassSegment.mainClassSegment;
import segment.whileLoopSegment.whileLoopSegment;

import java.util.HashMap;
import java.util.Map;

public class javaVisitor extends MiniJavaBaseVisitor<Void> {
    CentralStorage storage = CentralStorage.getInstance();
    Map<String, Boolean> segmentDecider = new HashMap<>();
    whileLoopSegment whileLoopSegment = new whileLoopSegment();


    //below is the psvm program values call
    @Override
    public Void visitProgram(MiniJavaParser.ProgramContext ctx){
        storage.setWhileLoopSegment(whileLoopSegment);
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitMainClass(MiniJavaParser.MainClassContext ctx) {
        String className = ctx.IDENTIFIER(0).getText();
        mainClassSegment mainClassSegment = new mainClassSegment();
        storage.setMainClassSegment(mainClassSegment);
        if (!ctx.statement(0).expression().isEmpty()) {
            String printValue = ctx.statement(0).expression(0).STRING_LITERAL().getText();
            storage.getMainClassSegment().setPrintValue(printValue);
        }
        storage.getMainClassSegment().setMainClassName(className);
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
        if (null != ctx.localVarDeclaration()) {
            storage.getWhileLoopSegment().setWhileLoopVariable1(Integer.parseInt(ctx.localVarDeclaration().expression().INTEGER_LITERAL().getText()));
            return super.visitStatement(ctx);
        }
        if (null != ctx.WHILE()) {
            storage.getWhileLoopSegment().setWhileLoopComparativeValue(Integer.parseInt(ctx.expression(0).expression(1).INTEGER_LITERAL().getText()));
        }
        return super.visitStatement(ctx);
    }

    @Override
    public Void visitExpression(MiniJavaParser.ExpressionContext ctx){
        return super.visitExpression(ctx);
    }

}
