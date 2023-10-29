package generator;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.ClassWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ifElseSegmentGen {
    public void generateIfElseBytecode(String mainClassName, int var1Value, int var2Value) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, mainClassName, null, "java/lang/Object", null);

        // Constructor method
        Method constructorMethod = Method.getMethod("void <init> ()");
        GeneratorAdapter constructorAdapter = new GeneratorAdapter(Opcodes.ACC_PUBLIC, constructorMethod, null, null, classWriter);
        constructorAdapter.loadThis();
        constructorAdapter.invokeConstructor(Type.getType(Object.class), constructorMethod);
        constructorAdapter.returnValue();
        constructorAdapter.endMethod();

        // Main method
        Method mainMethod = Method.getMethod("void main (String[])");
        GeneratorAdapter mainAdapter = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, mainMethod, null, null, classWriter);

        mainAdapter.push(var1Value);
        mainAdapter.storeLocal(1, Type.INT_TYPE);

        mainAdapter.push(var2Value);
        mainAdapter.storeLocal(2, Type.INT_TYPE);

        mainAdapter.loadLocal(2);
        mainAdapter.loadLocal(1);

        // if condition (b > a)
        Label labelElse = mainAdapter.newLabel();
        mainAdapter.ifCmp(Type.INT_TYPE, GeneratorAdapter.LE, labelElse);

        // then part
        mainAdapter.getStatic(Type.getType(System.class), "out", Type.getType(java.io.PrintStream.class));
        mainAdapter.loadLocal(2);
        mainAdapter.invokeVirtual(Type.getType(java.io.PrintStream.class), Method.getMethod("void println (int)"));
        Label labelEnd = mainAdapter.newLabel();
        mainAdapter.goTo(labelEnd);

        // else part
        mainAdapter.mark(labelElse);
        mainAdapter.getStatic(Type.getType(System.class), "out", Type.getType(java.io.PrintStream.class));
        mainAdapter.loadLocal(1);
        mainAdapter.invokeVirtual(Type.getType(java.io.PrintStream.class), Method.getMethod("void println (int)"));

        mainAdapter.mark(labelEnd);
        mainAdapter.returnValue();
        mainAdapter.endMethod();

        classWriter.visitEnd();

        byte[] bytecode = classWriter.toByteArray();

        try (FileOutputStream fos = new FileOutputStream("src/test/output/" + mainClassName + ".class")) {
            fos.write(bytecode);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

