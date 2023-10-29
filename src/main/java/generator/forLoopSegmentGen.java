package generator;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class forLoopSegmentGen {
    //for increasing ++
    public void generateForLoopBytecode(String mainClassName, int localVar1, int statement1initialValue, int statement2limit) {
        ClassWriter cw = new ClassWriter(0);

        // Define the class as a public class named "forLoop"
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, mainClassName, null, "java/lang/Object", null);

        // Create the default constructor
        Method initMethod = Method.getMethod("void <init> ()");
        GeneratorAdapter init = new GeneratorAdapter(Opcodes.ACC_PUBLIC, initMethod, null, null, cw);
        init.loadThis();
        init.invokeConstructor(Type.getType(Object.class), initMethod);
        init.returnValue();
        init.endMethod();

        // Create the main method
        Method mainMethod = Method.getMethod("void main (String[])");
        GeneratorAdapter ga = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, mainMethod, null, null, cw);

        // int a = 10;
        ga.push(localVar1);
        ga.storeLocal(1, Type.INT_TYPE);

        // for-loop logic
        ga.push(statement1initialValue);
        ga.storeLocal(2, Type.INT_TYPE); // i = 0

        Label loopStart = ga.newLabel();
        Label loopEnd = ga.newLabel();

        ga.mark(loopStart);
        ga.loadLocal(2, Type.INT_TYPE);
        ga.push(statement2limit);
        ga.ifCmp(Type.INT_TYPE, GeneratorAdapter.GE, loopEnd); // if (i >= 20) goto loopEnd

        ga.loadLocal(1, Type.INT_TYPE);
        ga.push(1);
        ga.math(GeneratorAdapter.ADD, Type.INT_TYPE); // a++
        ga.storeLocal(1, Type.INT_TYPE);

        ga.iinc(2, 1); // i++
        ga.goTo(loopStart);

        ga.mark(loopEnd);
        ga.returnValue();
        ga.endMethod();

        cw.visitEnd();

        byte[] bytecode = cw.toByteArray();

        try (FileOutputStream fos = new FileOutputStream("src/test/output/" + mainClassName + ".class")) {
            fos.write(bytecode);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

