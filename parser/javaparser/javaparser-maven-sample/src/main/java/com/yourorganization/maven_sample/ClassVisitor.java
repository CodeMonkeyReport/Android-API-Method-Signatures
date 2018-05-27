package com.yourorganization.maven_sample;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter{

    public void visit(ClassOrInterfaceDeclaration n, Object args)
    {

    }
}
