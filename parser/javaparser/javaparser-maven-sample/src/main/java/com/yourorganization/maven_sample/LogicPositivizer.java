package com.yourorganization.maven_sample;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * Some code that uses JavaParser.
 */
public class LogicPositivizer {
    private static String outputDirectory = "output";
    private static SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(LogicPositivizer.class).resolve("src/main/resources/android-sdk-sources-for-api-level-24"));

    public static void main(String[] args) throws IOException {
        // SourceRoot is a tool that read and writes Java files from packages on a certain root directory.
        // In this case the root directory is found by taking the root from the current Maven module,
        // with src/main/resources appended.
        File rootDirectory = new File("src/main/resources/android-sdk-sources-for-api-level-24");

        String startPackage = "";

        // Each folder inside the 'level' folder will be a package name
        File[] packages = rootDirectory.listFiles();
        for (File pak : packages)
        {
            LogicPositivizer.parseFiles(pak, "");
        }
    }

    private static void parseFiles(File root, String packageRoot) throws IOException {
        File[] directoryListing = root.listFiles();

        if (directoryListing == null) // If directory listing is null then we have a java file
        {
            CompilationUnit cu = sourceRoot.parse(packageRoot, root.getName());
            MethodVisitor mv = new MethodVisitor();
            cu.accept(mv, null);
            for (String key : mv.methods.keySet())
            {
                // Get the class name
                int pos = root.getName().lastIndexOf(".");
                String className = pos > 0 ? root.getName().substring(0, pos) : root.getName();

                // Check and update the current package
                FileOutputStream output = new FileOutputStream(outputDirectory + "/" + packageRoot + "." + className + "."  + key);
                output.write(mv.methods.get(key).getBytes());
            }
        }
        else // Otherwise this is a directory
        {
            for (File child : directoryListing) // Iterate over all files and recurse
            {
                parseFiles(child, packageRoot.equals("") ? root.getName() : packageRoot + "." + root.getName());
            }
        }
    }
}
