package generator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class arraySegmentGen {
    public void generateArraySegmentBytecode(String mainClassName,List<Integer> arrayElements) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        // Define the class
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, mainClassName, null, "java/lang/Object", null);

        // Define the main method signature
        Method m = Method.getMethod("void main (String[])");

        // Create the GeneratorAdapter
        GeneratorAdapter mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, m, null, null, cw);

        // Start the code for the method
        mg.visitCode();

//        List<Integer> list = Arrays.asList(5, 10, 9, 8, 7);

        // int[] a = new int[list.size()];
        mg.push(arrayElements.size()); // Size of the array
        mg.newArray(Type.INT_TYPE);
        for (int i = 0; i < arrayElements.size(); i++) {
            mg.dup();
            mg.push(i);
            mg.push(arrayElements.get(i)); // The actual values from the list
            mg.arrayStore(Type.INT_TYPE);
        }
        mg.visitVarInsn(Opcodes.ASTORE, 1);

        // System.out.println(Arrays.toString(a));
        mg.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
        mg.loadArg(0);
        mg.invokeStatic(Type.getType(Arrays.class), Method.getMethod("String toString (Object[])"));
        mg.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (String)"));

        // End the code for the method
        mg.returnValue();
        mg.endMethod();

        // Finalize the class
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


