package codegenerator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class asmCodeGenerator {

    public byte[] generateHelloWorld() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "HelloWorld", null, "java/lang/Object", null);

        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello, World!");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }
    public byte[] generateAddNumbers() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Main", null, "java/lang/Object", null);

        // Constructor for the Main class
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // Main method
        MethodVisitor mv2 = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);

        // int first = 10;
        mv2.visitIntInsn(Opcodes.BIPUSH, 10);
        mv2.visitVarInsn(Opcodes.ISTORE, 1);

        // int second = 20;
        mv2.visitIntInsn(Opcodes.BIPUSH, 20);
        mv2.visitVarInsn(Opcodes.ISTORE, 2);

        // int sum = first + second;
        mv2.visitVarInsn(Opcodes.ILOAD, 1);
        mv2.visitVarInsn(Opcodes.ILOAD, 2);
        mv2.visitInsn(Opcodes.IADD);
        mv2.visitVarInsn(Opcodes.ISTORE, 3);

        // System.out.println(first + " + " + second + " = " + sum);
        mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv2.visitVarInsn(Opcodes.ILOAD, 1);
        mv2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V", false);
        mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv2.visitLdcInsn(" + ");
        mv2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv2.visitVarInsn(Opcodes.ILOAD, 2);
        mv2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V", false);
        mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv2.visitLdcInsn(" = ");
        mv2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv2.visitVarInsn(Opcodes.ILOAD, 3);
        mv2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        // return statement
        mv2.visitInsn(Opcodes.RETURN);
        mv2.visitMaxs(2, 4); // Maximum stack and local variable table
        mv2.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }
}

