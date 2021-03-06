package com.googlecode.ounit.codesimplifier.processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.googlecode.ounit.codesimplifier.java2simplejava.Java8Lexer;
import com.googlecode.ounit.codesimplifier.java2simplejava.Java8Parser;

public class Java2SimpleJava {
	static final String SEMICOLON_REMOVAL_PATTERN = "(;(;+))";

	public static List<String> removeMainMethod(Set<String> methods) {
		List<String> methodNames = new ArrayList<>();
		methodNames.addAll(methods);
		methodNames.removeAll(new ArrayList<>(Arrays.asList("main")));
		return methodNames;
	}

	public String processString(String code) {
		ANTLRInputStream input = new ANTLRInputStream(code);

		Java8Lexer lexer = new Java8Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Java8Parser parser = new Java8Parser(tokens);
		RuleContext tree = parser.compilationUnit();
		ParseTreeWalker walker = new ParseTreeWalker();

		// composing a list from user defined function- and variable names
		UserDefinedNamesListener collector = new UserDefinedNamesListener();
		walker.walk(collector, tree);

		List<String> declaredMethods = removeMainMethod(collector.getFunctions());
		Set<String> declaredVariables = collector.getVariables();
		// System.out.println("declaredMethods:\n" + declaredMethods.toString());
		// System.out.println("declaredVariables:\n" + declaredVariables.toString());
		/*
		 * PreSimplifier 1. annotations - remove including "@" 2. variable
		 * declarations - remove 3. format string -remove 4. string argument -
		 * remove 6. return statement - remove
		 */
		PreSimplifier preSimplifier = new PreSimplifier(tokens);
		walker.walk(preSimplifier, tree);
		// String afterPresimplifier = preSimplifier.rewriter.getText();
		// System.out.println("afterPresimplifier:\n" + afterPresimplifier);

		/*
		 * RemoveConditionals 8. conditionals -keep only control flow structures
		 */
		RemoveConditionals removeConditionals = new RemoveConditionals(tokens, preSimplifier.rewriter);
		walker.walk(removeConditionals, tree);
		// String afterRemoveConditionals = removeConditionals.rewriter.getText();
		// System.out.println("afterRemoveConditionals:\n" + afterRemoveConditionals);
		/*
		 * RemoveLoops 9. loops - remove conditions
		 */
		RemoveLoops removeLoops = new RemoveLoops(tokens, removeConditionals.rewriter);
		walker.walk(removeLoops, tree);
		// String afterRemoveLoops = removeLoops.rewriter.getText();
		// System.out.println("afterRemoveLoops:\n" + afterRemoveLoops);
		/*
		 * RemoveUserDefinedNames 7a. user defined functions -remove all 7b.
		 * system calls - keep intact
		 */
		RemoveUserDefinedNames removeUserDefinedNames = new RemoveUserDefinedNames(removeLoops.rewriter,
				declaredMethods, declaredVariables);
		walker.walk(removeUserDefinedNames, tree);
		// String afterRemoveUserDefinedNames = removeUserDefinedNames.rewriter.getText();
		// System.out.println("removeUserDefinedNames:\n" + afterRemoveUserDefinedNames);
		/*
		 * RemoveExpressionStatements (InUserFunctionCalls) 5a. expression
		 * statement in system call - keep 5b. expression statement other -
		 * remove
		 */
		RemoveExpressionStatements removeExpressionStatements = new RemoveExpressionStatements(
				removeUserDefinedNames.rewriter);
		walker.walk(removeExpressionStatements, tree);
		// String afterRemoveExpressionStatements = removeExpressionStatements.rewriter.getText();
		// System.out.println("afterRemoveExpressionStatements:\n" + afterRemoveExpressionStatements);

		// we also need abstract user-defined methods keeping only return type
		AbstractUsersMethods abstractUsersMethods = new AbstractUsersMethods(removeExpressionStatements.rewriter,
				declaredMethods);
		walker.walk(abstractUsersMethods, tree);
		// String afterAbstractUsersMethods = abstractUsersMethods.rewriter.getText();
		// System.out.println("afterAbstractUsersMethods:\n" + afterAbstractUsersMethods);

		// we also need to remove package declaration
		RemovePackageDeclaration removePackageDeclaration = new RemovePackageDeclaration(
				removeExpressionStatements.rewriter);
		walker.walk(removePackageDeclaration, tree);
		String afterRemovePackageDeclaration = removePackageDeclaration.rewriter.getText();
		// System.out.println("afterRemovePackageDeclaration:\n" + afterRemovePackageDeclaration);

		// remove leftover semicolons
		String stripped = afterRemovePackageDeclaration.replaceAll(SEMICOLON_REMOVAL_PATTERN, "");
		// final result
		return stripped;
	}
}
