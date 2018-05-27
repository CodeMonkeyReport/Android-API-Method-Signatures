package com.yourorganization.maven_sample;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;

public class PackageVisitor extends VoidVisitorAdapter {

    HashMap<String, ClassVisitor> classMap = new HashMap<String, ClassVisitor>();

    public void visit(PackageDeclaration n, Object arg)
    {

    }

}
