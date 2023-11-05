package visitor;

import com.antlr.MiniJavaBaseVisitor;
import com.antlr.MiniJavaParser;
import segment.CentralStorage;
import segment.array2DSegment.array2DSegment;
import segment.arraySegment.arraySegment;
import segment.forLoopSegment.forLoopSegment;
import segment.ifElseSegment.ifElseSegment;
import segment.localVarSegment.localVarSegment;
import segment.mainClassSegment.mainClassSegment;
import segment.whileLoopSegment.whileLoopSegment;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class javaVisitor extends MiniJavaBaseVisitor<Void> {
    CentralStorage storage = CentralStorage.getInstance();
    whileLoopSegment whileLoopSegment = new whileLoopSegment();
    localVarSegment localVarSegment = new localVarSegment();
    forLoopSegment forLoopSegment = new forLoopSegment();
    ifElseSegment ifElseSegment = new ifElseSegment();
    arraySegment arraySegment = new arraySegment();
    array2DSegment array2DSegment = new array2DSegment();


    //below is the psvm program values call
    @Override
    public Void visitProgram(MiniJavaParser.ProgramContext ctx) {
        storage.setWhileLoopSegment(whileLoopSegment);
        storage.setLocalVarSegment(localVarSegment);
        storage.setForLoopSegment(forLoopSegment);
        storage.setIfElseSegment(ifElseSegment);
        storage.setArraySegment(arraySegment);
        storage.setArray2DSegment(array2DSegment);
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitMainClass(MiniJavaParser.MainClassContext ctx) {
        String className = ctx.IDENTIFIER(0).getText();
        mainClassSegment mainClassSegment = new mainClassSegment();
        storage.setMainClassSegment(mainClassSegment);
        int statementSize = ctx.statement().size();
        for (int i = 0; i < statementSize; i++) {
            if (!ctx.statement(i).expression().isEmpty()) {
                Optional<String> printValueOptional = Optional.ofNullable(ctx.statement(0))
                        .map(statement -> statement.expression(0))
                        .map(expression -> expression.STRING_LITERAL())
                        .map(stringLiteral -> stringLiteral.getText());
                String printValue = printValueOptional.orElse("hi"); // Default to "hi" if null
                storage.getMainClassSegment().setPrintValue(printValue);
            }
        }
        storage.getMainClassSegment().setMainClassName(className);
        return super.visitMainClass(ctx);
    }

    @Override
    public Void visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        return super.visitClassDeclaration(ctx);
    }

    @Override
    public Void visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) {
        return super.visitVarDeclaration(ctx);
    }

    @Override
    public Void visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        return super.visitMethodDeclaration(ctx);
    }

    @Override
    public Void visitType(MiniJavaParser.TypeContext ctx){
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
        if (null != ctx.IF() && null != ctx.ELSE()) {
            // if & else conditons are written in generationFile. Will add support for custom lines later.
            storage.getIfElseSegment().setMainClassName(storage.getMainClassSegment().getMainClassName());
            storage.getIfElseSegment().setVar1Value(storage.getLocalVarSegment().getNumber1());
            storage.getIfElseSegment().setVar2Value(storage.getLocalVarSegment().getNumber2());
        }
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
    public Void visitArrayInitializer(MiniJavaParser.ArrayInitializerContext ctx) {
        List<Integer> arrayElements = new LinkedList<>();
        List<List<Integer>> array2DElements = new LinkedList<>();
        if (!ctx.expression().isEmpty()) {
            for (int i = 0; i < ctx.expression().size(); i++) {
                int number = Integer.parseInt(ctx.expression(i).INTEGER_LITERAL().getText());
                arrayElements.add(number);
            }
            storage.getArraySegment().setArrayElements(arrayElements); //1D array
        }

        int array2Dsize = ctx.arrayInitializer().size(); //is 0 if 1D array
        for (int i = 0; i < array2Dsize ; i++) {
            int elementSize = ctx.arrayInitializer(i).expression().size();
            for (int j = 0; j < elementSize; j++) {
                int number = Integer.parseInt(ctx.arrayInitializer(i).expression(j).INTEGER_LITERAL().getText());
                arrayElements.add(number);
            }
            array2DElements.add(arrayElements);
            arrayElements = new LinkedList<>();
        }
        storage.getArray2DSegment().setArray2DElements(array2DElements);
        return null;
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
