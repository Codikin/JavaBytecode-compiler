package codegenerator;

import com.antlr.MiniJavaParser;
import generator.mainMethodSegmentGen;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import com.antlr.MiniJavaBaseListener;
import segment.CentralStorage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

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
        String printValue = storage.getMainClassSegment().getPrintValue();
        mainMethodSegmentGen mainMethodSegmentGen = new mainMethodSegmentGen();
        mainMethodSegmentGen.mainMethodGenerator(mainClass, printValue);
    }

    @Override
    public void exitMainClass(MiniJavaParser.MainClassContext ctx) {
        super.exitMainClass(ctx);
    }
}
