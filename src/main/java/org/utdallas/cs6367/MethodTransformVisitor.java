package org.utdallas.cs6367;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * @author Kevin Nguyen/ kxn120430
 * A modified version of MethodTransformVisitor.java from 
 * https://github.com/lingming/CS6367-ASM . Implements statement level coverage 
 * and is able to trace the executed lines of a given program
 */
class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String methodName;
	String className;
	int line;
	
    public MethodTransformVisitor(final MethodVisitor mv, String className, String methodName) {
        super(ASM5, mv);
        this.className = className;
        this.methodName = methodName;
    }

    // method coverage collection
    @Override
    public void visitCode(){
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn(methodName+" executed!");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	super.visitCode();
    }
    
    //statement coverage collection
    @Override
    public void visitLineNumber(int line, Label start) {
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn("line " + line + " executed");
    	
    	//TODO replace with code instrumentation (i.e. visitMethodIns and the like)
    	//in order to record code coverage
    	LoggerSingleton.getInstance().logMessage("line " + line + "executed!");
    	
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	this.line = line;
    	super.visitLineNumber(line, start);
    }
    
    //additional override for statement level coverage
    @Override
    public void visitLabel(Label label) {
    	visitLineNumber(this.line, label);
    	super.visitLabel(label);
    }

}