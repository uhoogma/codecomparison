package com.googlecode.ounit.codesimplifier.processing;

import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;

public class Util {

	public static void removeChild(ParseTree child, TokenStreamRewriter rewriter) {
		Interval interval = child.getSourceInterval();
		removeChildsTokens(interval, rewriter);
	}

	public static void removeChildsTokens(Interval interval, TokenStreamRewriter rewriter) {
		if (interval != null) {
			for (int i = interval.a; i < interval.b; i++) {
				rewriter.delete(i);
			}
		}
	}
}
