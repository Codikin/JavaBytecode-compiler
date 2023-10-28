package codegenerator;

import com.antlr.MiniJavaParser;
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
    @Override public void enterForStatement(MiniJavaParser.ForStatementContext ctx) { }
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
