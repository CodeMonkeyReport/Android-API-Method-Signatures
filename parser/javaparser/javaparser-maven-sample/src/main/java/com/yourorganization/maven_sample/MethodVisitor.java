package com.yourorganization.maven_sample;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;

public class MethodVisitor extends VoidVisitorAdapter {
    public HashMap<String, String> methods = new HashMap<String, String>();

    public void visit(MethodDeclaration n, Object arg)
    {
        String methodContent = "";
        String methodName;
        String className;
        String fullName;

        methodName = n.getNameAsString();
        methodContent = n.getDeclarationAsString(true, true, true);

        methodContent += n.getBody().orElse(new BlockStmt()).toString();

        fullName = methodName;

        methods.put(fullName, methodContent);
    }
}
