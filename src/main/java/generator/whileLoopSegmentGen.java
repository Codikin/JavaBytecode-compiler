package generator;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class whileLoopSegmentGen {
    public void generateWhileLoopBytecode(String mainClassName, int var1Value, int comparativeValue1) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        GeneratorAdapter ga;


        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, mainClassName, null, "java/lang/Object", null);

        // Constructor
        ga = new GeneratorAdapter(Opcodes.ACC_PUBLIC, new Method("<init>", "()V"), null, null, cw);
        ga.loadThis();
        ga.invokeConstructor(Type.getType(Object.class), new Method("<init>", "()V"));
        ga.returnValue();
        ga.endMethod();

        // Main method
        ga = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new Method("main", "([Ljava/lang/String;)V"), null, null, cw);


        ga.push(var1Value);// Push 10 onto the stack
        ga.storeLocal(1, Type.INT_TYPE);
        Label loopStart = ga.newLabel();
        Label loopEnd = ga.newLabel();
        ga.mark(loopStart);
        ga.loadLocal(1, Type.INT_TYPE);
        ga.push(comparativeValue1);                 // Push 0 onto the stack
        ga.ifCmp(Type.INT_TYPE, GeneratorAdapter.LE, loopEnd); // If value <= 0, jump to loopEnd .GE for value>=0
        ga.loadLocal(1, Type.INT_TYPE);
        ga.push(1);                 // Push 1 onto the stack
        ga.math(GeneratorAdapter.SUB, Type.INT_TYPE); // Subtract
        ga.storeLocal(1, Type.INT_TYPE);

        ga.goTo(loopStart);         // Jump back to the start of the loop
        ga.mark(loopEnd);

        ga.returnValue();
        ga.endMethod();

        cw.visitEnd();

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

