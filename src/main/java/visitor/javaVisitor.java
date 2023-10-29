package visitor;

import com.antlr.MiniJavaBaseVisitor;
import com.antlr.MiniJavaParser;
import segment.CentralStorage;
import segment.forLoopSegment.forLoopSegment;
import segment.localVarSegment.localVarSegment;
import segment.mainClassSegment.mainClassSegment;
import segment.whileLoopSegment.whileLoopSegment;

import java.util.HashMap;
import java.util.Map;

public class javaVisitor extends MiniJavaBaseVisitor<Void> {
    CentralStorage storage = CentralStorage.getInstance();
    Map<String, Boolean> segmentDecider = new HashMap<>();
    whileLoopSegment whileLoopSegment = new whileLoopSegment();
    localVarSegment localVarSegment = new localVarSegment();
    forLoopSegment forLoopSegment = new forLoopSegment();


    //below is the psvm program values call
    @Override
    public Void visitProgram(MiniJavaParser.ProgramContext ctx){
        storage.setWhileLoopSegment(whileLoopSegment);
        storage.setLocalVarSegment(localVarSegment);
        storage.setForLoopSegment(forLoopSegment);
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
//        if (null != ctx.localVarDeclaration()) { //for while loop too. TODO
//            visitLocalVarDeclaration(ctx.localVarDeclaration());
////            storage.getWhileLoopSegment().setWhileLoopVariable1(Integer.parseInt(ctx.localVarDeclaration().expression().INTEGER_LITERAL().getText()));
//            storage.getWhileLoopSegment().setWhileLoopVariable1(storage.getLocalVarSegment().getNumber1());
////            return super.visitStatement(ctx);
//        }
        if (null != ctx.WHILE()) {
            storage.getWhileLoopSegment().setWhileLoopVariable1(storage.getLocalVarSegment().getNumber1());
            storage.getWhileLoopSegment().setWhileLoopComparativeValue(Integer.parseInt(ctx.expression(0).expression(1).INTEGER_LITERAL().getText()));
        }
        if (null != ctx.forStatement()) {
            visitForStatement(ctx.forStatement());
        }
//        return super.visitStatement(ctx);
        return super.visitStatement(ctx);
    }

//    @Override
//    public Void visitLocalVarDeclaration(MiniJavaParser.LocalVarDeclarationContext ctx) {
//        if (null != storage.getLocalVarSegment().getNumber1()) {
//            int number2 = Integer.parseInt(ctx.expression().INTEGER_LITERAL().getText());
//            storage.getLocalVarSegment().setNumber2(number2);
//        }
//        if (null != storage.getLocalVarSegment().getNumber2()) {
//            int number3 = Integer.parseInt(ctx.expression().INTEGER_LITERAL().getText());
//            storage.getLocalVarSegment().setNumber3(number3);
//        }
//        int number = Integer.parseInt(ctx.expression().INTEGER_LITERAL().getText());
//        storage.getLocalVarSegment().setNumber1(number);
//        return super.visitLocalVarDeclaration(ctx);
//    }

    @Override
    public Void visitLocalVarDeclaration(MiniJavaParser.LocalVarDeclarationContext ctx) {
        int number = Integer.parseInt(ctx.expression().INTEGER_LITERAL().getText());
        if (storage.getLocalVarSegment().getNumber1() == null) {
            storage.getLocalVarSegment().setNumber1(number);
        } else if (storage.getLocalVarSegment().getNumber2() == null) {
            storage.getLocalVarSegment().setNumber2(number);
        } else if (storage.getLocalVarSegment().getNumber3() == null) {
            storage.getLocalVarSegment().setNumber3(number);
        }
        return null;
//        return super.visitLocalVarDeclaration(ctx);
    }

    @Override
    public Void visitExpression(MiniJavaParser.ExpressionContext ctx){
        return super.visitExpression(ctx);
    }

    @Override
    public Void visitForStatement(MiniJavaParser.ForStatementContext ctx) {
        visitForInit(ctx.forInit());
        int statement2limit = Integer.parseInt(ctx.expression().expression(1).INTEGER_LITERAL().getText());
        storage.getForLoopSegment().setMainClassName(storage.getMainClassSegment().getMainClassName());
        storage.getForLoopSegment().setStatement1initialValue(storage.getLocalVarSegment().getNumber2());
        storage.getForLoopSegment().setLocalVar1(storage.getLocalVarSegment().getNumber1()); //for loop
        if (null == storage.getLocalVarSegment().getNumber3()) {
            storage.getLocalVarSegment().setNumber3(statement2limit);
            storage.getForLoopSegment().setStatement2limit(storage.getLocalVarSegment().getNumber3()); //for loop
        }
        if (null == storage.getLocalVarSegment().getNumber2()) {
            storage.getLocalVarSegment().setNumber2(statement2limit);
            //for loop
        }
        if (null == storage.getLocalVarSegment().getNumber1()) {
            storage.getLocalVarSegment().setNumber1(statement2limit);
        }
        return null;
//        return super.visitForStatement(ctx);
    }

    @Override
    public Void visitForInit(MiniJavaParser.ForInitContext ctx) {
        visitLocalVarDeclaration(ctx.localVarDeclaration());
        return null;
    }

    @Override
    public Void visitForUpdate(MiniJavaParser.ForUpdateContext ctx) { return visitChildren(ctx); }

}
