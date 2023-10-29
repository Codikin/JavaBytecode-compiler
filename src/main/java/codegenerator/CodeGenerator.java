package codegenerator;

import com.antlr.MiniJavaParser;
import generator.forLoopSegmentGen;
import generator.ifElseSegmentGen;
import generator.mainMethodSegmentGen;
import generator.whileLoopSegmentGen;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.GeneratorAdapter;
import com.antlr.MiniJavaBaseListener;
import segment.CentralStorage;


public class CodeGenerator extends MiniJavaBaseListener implements Opcodes {
    CentralStorage storage = CentralStorage.getInstance();
    ClassWriter cw = null;
    GeneratorAdapter generatorAdapter;
    public CodeGenerator() {

    }

    @Override
    public void enterProgram(MiniJavaParser.ProgramContext ctx) {
        super.enterProgram(ctx);
    }

    @Override
    public void exitProgram(MiniJavaParser.ProgramContext ctx) {
        super.exitProgram(ctx);
    }

    @Override
    public void enterMainClass(MiniJavaParser.MainClassContext ctx) {
        String mainClass = storage.getMainClassSegment().getMainClassName();
        if (null != ctx.statement(0)){
            String printValue = storage.getMainClassSegment().getPrintValue();
            mainMethodSegmentGen mainMethodSegmentGen = new mainMethodSegmentGen();
            mainMethodSegmentGen.mainMethodGenerator(mainClass, printValue);
        }
        super.enterMainClass(ctx);
    }

    @Override
    public void exitMainClass(MiniJavaParser.MainClassContext ctx) {
        super.exitMainClass(ctx);
    }

    @Override public void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterType(MiniJavaParser.TypeContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitType(MiniJavaParser.TypeContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterLocalVarDeclaration(MiniJavaParser.LocalVarDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLocalVarDeclaration(MiniJavaParser.LocalVarDeclarationContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterForStatement(MiniJavaParser.ForStatementContext ctx) {
        String mainclassName = storage.getForLoopSegment().getMainClassName();
        int localVar1 = storage.getForLoopSegment().getLocalVar1();
        int statement1initialValue = storage.getForLoopSegment().getStatement1initialValue();
        int statement2Limit = storage.getForLoopSegment().getStatement2limit();
        forLoopSegmentGen forloopGenerator = new forLoopSegmentGen();
        forloopGenerator.generateForLoopBytecode(mainclassName, localVar1, statement1initialValue, statement2Limit);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitForStatement(MiniJavaParser.ForStatementContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterForInit(MiniJavaParser.ForInitContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitForInit(MiniJavaParser.ForInitContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterForUpdate(MiniJavaParser.ForUpdateContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitForUpdate(MiniJavaParser.ForUpdateContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterStatement(MiniJavaParser.StatementContext ctx) {
        if (null != ctx.WHILE()) {
            String mainClassName = storage.getMainClassSegment().getMainClassName();
            int var1value = storage.getWhileLoopSegment().getWhileLoopVariable1();
            int comparativeValue1 = storage.getWhileLoopSegment().getWhileLoopComparativeValue();
            whileLoopSegmentGen whileLoopSegmentGen = new whileLoopSegmentGen();
            whileLoopSegmentGen.generateWhileLoopBytecode(mainClassName, var1value, comparativeValue1);
        }
        if (null != ctx.IF() && null != ctx.ELSE()) {
            String mainClassName = storage.getIfElseSegment().getMainClassName();
            int var1Value = storage.getIfElseSegment().getVar1Value();
            int var2Value = storage.getIfElseSegment().getVar2Value();
            ifElseSegmentGen ifElseSegmentGen = new ifElseSegmentGen();
            ifElseSegmentGen.generateIfElseBytecode(mainClassName, var1Value, var2Value);
        }

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitStatement(MiniJavaParser.StatementContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterExpression(MiniJavaParser.ExpressionContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitExpression(MiniJavaParser.ExpressionContext ctx) { }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitTerminal(TerminalNode node) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitErrorNode(ErrorNode node) { }
}
