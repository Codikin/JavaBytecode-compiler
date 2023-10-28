package main;

import codegenerator.CodeGenerator;
import com.antlr.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import visitor.javaVisitor;

import java.io.FileInputStream;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("src/test/java/myIntro.java");
        CharStream charStream = CharStreams.fromStream(file);
        MiniJavaLexer lexer = new MiniJavaLexer(charStream);
        MiniJavaParser parser = new MiniJavaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();

        javaVisitor visitor = new javaVisitor();
        visitor.visit(tree);
        CodeGenerator codeGenerator = new CodeGenerator();
        ParseTreeWalker.DEFAULT.walk(codeGenerator, tree);
        System.out.println("Successful");
    }
}
