package codegenerator;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class generatorAdaptorCodeGen {
    public static void main(String[] args) throws IOException {
        generateHelloWorldClass();
    }

    public static void generateHelloWorldClass() throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "HelloWorld", null, "java/lang/Object", null);

        generateConstructor(cw);
        generateMainMethod(cw);

        cw.visitEnd();

        byte[] bytecode = cw.toByteArray();

        try (FileOutputStream fos = new FileOutputStream("HelloWorld.class")) {
            fos.write(bytecode);
        }
    }

    private static void generateConstructor(ClassWriter cw) {
        MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructor.visitCode();
        GeneratorAdapter mg = new GeneratorAdapter(constructor, Opcodes.ACC_PUBLIC, "<init>", "()V");

        mg.loadThis();
        mg.invokeConstructor(Type.getType(Object.class), new Method("<init>", Type.VOID_TYPE, new Type[0]));
        mg.returnValue();
        mg.endMethod();

        constructor.visitEnd();
    }

    private static void generateMainMethod(ClassWriter cw) {
        MethodVisitor main = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        main.visitCode();
        GeneratorAdapter mg = new GeneratorAdapter(main, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V");

        mg.getStatic(Type.getType(System.class), "out", Type.getType(System.out.getClass()));
        mg.push("Hello, World!");
        mg.invokeVirtual(Type.getType(PrintStream.class), new Method("println", Type.VOID_TYPE, new Type[]{Type.getType(String.class)}));
        mg.returnValue();
        mg.endMethod();

        main.visitEnd();
    }
    public static void generateClimbStairsMethod() throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Solution", null, "java/lang/Object", null);

        generateClimbStairs(cw);

        cw.visitEnd();

        byte[] bytecode = cw.toByteArray();

        try (FileOutputStream fos = new FileOutputStream("Solution.class")) {
            fos.write(bytecode);
        }
    }

    private static void generateClimbStairs(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "climbStairs", "(I)I", null, null);
        GeneratorAdapter mg = new GeneratorAdapter(mv, Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "climbStairs", "(I)I");

        mg.loadArg(0); // Load the argument 'n' onto the stack
        mg.push(0);
        Label returnZeroLabel = mg.newLabel();
        mg.ifZCmp(Opcodes.IFLE, returnZeroLabel);

        // Initialize steps array
        mg.newInstance(Type.getType("[I"));
        mg.dup();
        mg.loadArg(0);
        mg.visitInsn(Opcodes.ICONST_1);
        mg.visitInsn(Opcodes.IADD);
        mg.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_INT);
        mg.storeLocal(1); // Store the array reference in local variable 1

        mg.loadLocal(1);
        mg.push(0);
        mg.push(1);
        mg.visitInsn(Opcodes.IASTORE); // Initialize steps[0] = 1
        mg.loadLocal(1);
        mg.push(1);
        mg.push(1);
        mg.visitInsn(Opcodes.IASTORE); // Initialize steps[1] = 1

        mg.push(2);
        Label loopConditionLabel = mg.newLabel();
        mg.mark(loopConditionLabel);
        mg.loadLocal(0);
        mg.ifICmp(Opcodes.IF_ICMPGT, loopConditionLabel);

        mg.loadLocal(1);
        mg.loadLocal(0);
        mg.push(2);
        mg.visitInsn(Opcodes.ISUB);
        mg.arrayLoad(Type.INT_TYPE);
        mg.loadLocal(1);
        mg.loadLocal(0);
        mg.push(1);
        mg.visitInsn(Opcodes.ISUB);
        mg.arrayLoad(Type.INT_TYPE);
        mg.visitInsn(Opcodes.IADD);
        mg.arrayStore(Type.INT_TYPE);

        mg.visitIincInsn(0, 1);
        mg.goTo(loopConditionLabel);

        Label returnLabel = mg.newLabel();
        mg.mark(returnLabel);
        mg.loadLocal(1);
        mg.loadLocal(0);
        mg.arrayLoad(Type.INT_TYPE);
        mg.returnValue();

        mg.mark(returnZeroLabel);
        mg.pop();
        mg.push(0);
        mg.returnValue();

        mg.endMethod();
    }
}
