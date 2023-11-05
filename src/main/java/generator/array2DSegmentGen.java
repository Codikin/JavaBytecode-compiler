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

public class array2DSegmentGen {
    public void generateArray2DSegment(String mainClassName, List<List<Integer>> array2DElements) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        Method m;

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, mainClassName, null, "java/lang/Object", null);

        // Default constructor
        m = Method.getMethod("void <init> ()");
        GeneratorAdapter mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, m, null, null, cw);
        mg.loadThis();
        mg.invokeConstructor(Type.getType(Object.class), m);
        mg.returnValue();
        mg.endMethod();

        // main method
        m = Method.getMethod("void main (String[])");
        mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, m, null, null, cw);

        // Initialize and load values into 2D array using the List
        mg.push(array2DElements.size()); // Size of the first dimension
        mg.newArray(Type.getType("[I")); // Create a new array of int arrays

        for (int i = 0; i < array2DElements.size(); i++) {
            List<Integer> innerList = array2DElements.get(i);
            mg.dup(); // Duplicate the reference to the array
            mg.push(i); // Index for the first dimension
            mg.push(innerList.size()); // Size of the second dimension
            mg.newArray(Type.INT_TYPE); // Create a new int array

            for (int j = 0; j < innerList.size(); j++) {
                mg.dup(); // Duplicate the reference to the int array
                mg.push(j); // Index for the second dimension
                mg.push(innerList.get(j)); // The actual value
                mg.arrayStore(Type.INT_TYPE); // Store the value in the array
            }

            mg.arrayStore(Type.getType("[I")); // Store the int array in the array of arrays
        }

        // Invoke Arrays.deepToString(array2D)
        mg.invokeStatic(Type.getType(Arrays.class), Method.getMethod("String deepToString (Object[])"));

        // Print the result
        mg.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
        mg.swap();
        mg.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (String)"));
        mg.returnValue();
        mg.endMethod();

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
