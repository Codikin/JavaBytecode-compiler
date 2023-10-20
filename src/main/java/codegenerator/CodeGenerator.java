package codegenerator;

import com.antlr.MiniJavaParser;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import com.antlr.MiniJavaBaseListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

public class CodeGenerator extends MiniJavaBaseListener implements Opcodes {
    private Map<String, String> symbolTable; //to extract stored data
    ClassWriter cw = null;
    GeneratorAdapter generatorAdapter;
    public CodeGenerator(Map<String, String> symbolTable) {
        this.symbolTable = symbolTable;
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
        String mainClass = symbolTable.get("mainClassName");
        String printValue = symbolTable.get("printvalue");
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, mainClass, null, "java/lang/Object", null);

        MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructor.visitCode();
        GeneratorAdapter mg = new GeneratorAdapter(constructor, Opcodes.ACC_PUBLIC, "<init>", "()V");

        mg.loadThis();
        mg.invokeConstructor(Type.getType(Object.class), new Method("<init>", Type.VOID_TYPE, new Type[0]));
        mg.returnValue();
        mg.endMethod();
        constructor.visitEnd();

        MethodVisitor main = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, mainClass, "([Ljava/lang/String;)V", null, null);
        main.visitCode();
        GeneratorAdapter mg2 = new GeneratorAdapter(main, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V");

        mg2.getStatic(Type.getType(System.class), "out", Type.getType(System.out.getClass()));
        mg2.push(printValue);
        mg2.invokeVirtual(Type.getType(PrintStream.class), new Method("println", Type.VOID_TYPE, new Type[]{Type.getType(String.class)}));
        mg2.returnValue();
        mg2.endMethod();
        main.visitEnd();
    }

    @Override
    public void exitMainClass(MiniJavaParser.MainClassContext ctx) {
        cw.visitEnd();
        String mainClassName = symbolTable.get("mainClassName");
        byte[] bytecode = cw.toByteArray();

        try (FileOutputStream fos = new FileOutputStream(mainClassName + ".class")) {
            fos.write(bytecode);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
