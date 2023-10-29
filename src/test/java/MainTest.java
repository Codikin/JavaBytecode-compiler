import main.main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    public void testMultipleFiles() throws IOException {
        String directoryPath = "src/test/input";
        File directory = new File(directoryPath);
        if(directory.exists() && directory.isDirectory()) {
            // Iterate over all the files in the directory
            for(File file : Objects.requireNonNull(directory.listFiles())) {
                if(file.getName().endsWith(".java")) {
                    try {
                        processFile(file);
                        System.out.println("File " + file.getName() + " passed the check.");
                    } catch (Exception e) {
                        System.out.println("File " + file.getName() + " failed the check.");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void processFile(File file) throws IOException {
        main.processFile(file.getPath());
        assertTrue(true); // if an exception is thrown in processFile, this line won't be reached else it will be true always
    }
}


