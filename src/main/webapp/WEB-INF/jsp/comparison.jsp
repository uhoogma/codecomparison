<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Comparison</title>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/jquery-ui.css"/>">
<script src="<c:url value="/static/js/jquery-ui.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">
<script src="<c:url value="/static/codemirror/lib/codemirror.js"/>"></script>
<link rel="stylesheet"
	href="<c:url value="/static/codemirror/lib/codemirror.css"/>">
<script src="<c:url value="/static/codemirror/mode/clike/clike.js"/>"></script>
<script src="<c:url value="/static/js/codemirror.js"/>"></script>
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<div class="top row">
					<div class="col-sm-12">
						<a href="<c:url value="index"/>"><button type="button"
								class="btn btn-default-left">AVALEHT</button></a> <a
							href="<c:url value="test"/>">
							<button type="button" class="btn btn-primary">TAGASI
								ANALÜÜSI JUURDE</button>
						</a>
					</div>
				</div>
				<div class="row">
					<div class="padding alert alert-danger">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Danger!</strong> This alert box could indicate a dangerous
						or potentially negative action.
					</div>
				</div>
				<div class="row">
					<h3 class="col-sm-12 centered-text">Jaccardi koefitsent 2.25</h3>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div>
							<p>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
								koduülesanne | 2015/2016 | Kevadsemester</p>
						</div>
						<div>Tudengi nimi | Tudengi Id | Katse Id</div>
						<div class="code-div">
							<textarea id="java-code" style="display: none;">
public class TreeNode {

	private String name;
	private TreeNode firstChild;
	private TreeNode nextSibling;

	TreeNode(String n, TreeNode d, TreeNode r) {
		if (n == null) {
			throw new RuntimeException("Node name cannot be null!");
		}

		if (n.contains(" ")) {
			throw new RuntimeException("Node name cannot contain space. Name: "
					+ n);
		}

		this.name = n;
		this.firstChild = d;
		this.nextSibling = r;
	}

	public static TreeNode parsePrefix(String s) {
		if (s.contains("()")) {
			throw new RuntimeException(
					"Node cannot contains empty subtree. Input " + s);
		} else if (!isInputBalanced(s)) {
			throw new RuntimeException(
					"Input format error. Brackets are not balanced. Input: "
							+ s);
		} else if (s.contains("((")) {
			throw new RuntimeException("Nodes must have a name! Input: " + s);
		} else if (s.contains(",,")) {
			throw new RuntimeException("Sibling must have a name! Input: " + s);
		} else if (rootHasSiblings(s)) {
			throw new RuntimeException("Root must not have siblings! Input: "
					+ s);
		}

		return parseTree(s);
	}

	public String rightParentheticRepresentation() {
		StringBuffer b = new StringBuffer();

		if (this.firstChild != null) {
			b.append("(");
			b.append(this.firstChild.rightParentheticRepresentation());
			b.append(")");
		}

		b.append(this.name);

		if (this.nextSibling != null) {
			b.append(",");
			b.append(this.nextSibling.rightParentheticRepresentation());
		}

		return b.toString();
	}

	public static void main(String[] param) {
		// String s = "A(B1,C)";
		String s = "A(B1(D1(E1,G7),F2),C(H))";
		// String s = "6(5(1,3(2),4))";

		System.out.println(s);
		TreeNode node = parsePrefix(s);
		System.out.println(node.rightParentheticRepresentation());
	}

	/* Parsing helper methods */
	private static TreeNode parseTree(String s) {
		String name;
		String childs = null;
		String next = null;

		if (isNode(s)) {
			name = extractName(s);
			childs = getChilds(s);
			next = getNextSiblings(s);

			return getTree(name, childs, next);

		} else {
			String current = getFirstSibling(s);
			next = getNextSiblings(s);
			name = extractName(current);

			if (hasChilds(current)) {
				childs = getChilds(current);
			}

			return getTree(name, childs, next);
		}
	}

	private static TreeNode getTree(String name, String childs, String next) {
		if (childs == null && next == null) {
			return new TreeNode(name, null, null);
		} else if (childs != null && next == null) {
			return new TreeNode(name, parseTree(childs), null);
		} else if (childs == null && next != null) {
			return new TreeNode(name, null, parseTree(next));
		} else {
			return new TreeNode(name, parseTree(childs), parseTree(next));
		}
	}

	private static String extractName(String node) {
		if (node.indexOf("(") == -1) {
			return node;
		}

		return node.substring(0, node.indexOf("("));
	}

	/*
	 * Extract a first sibling
	 * Input must not be a Node!
	 */
	public static String getFirstSibling(String s) {
		int level = 0;
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '(':
				level++;
				break;
			case ')':
				level--;
				break;
			case ',':
				if (level == 0) {
					return s.substring(0, i);
				}
				break;
			}
		}

		return s;
	}

	/*
	 * Get all other Siblings, besides first
	 */
	public static String getNextSiblings(String s) {
		int level = 0;
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '(':
				level++;
				break;
			case ')':
				level--;
				break;
			case ',':
				if (level == 0) {
					return s.substring(i + 1, s.length());
				}
				break;
			}
		}

		return null;
	}

	/*
	 * Check if input type is node / ( leaf ) string or multiple siblings string
	 */
	public static boolean isNode(String s) {
		int level = 0;
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '(':
				level++;
				break;
			case ')':
				level--;
				break;
			case ',':
				if (level == 0) {
					return false;
				}
				break;
			}
		}

		return true;
	}

	/*
	 * Extract all child from node
	 * Expected input type should be node string
	 */
	public static String getChilds(String s) {
		if (!s.contains("(")) {
			return null;
		}

		return s.substring(s.indexOf("(") + 1, lastIndexOf(s, ')'));
	}

	/*
	 * If node has child / or multiple siblings
	 * Input must not be a multiple siblings string
	 */
	public static boolean hasChilds(String s) {
		return s.contains("(") || s.contains(")");
	}

	public static int lastIndexOf(String input, char c) {
		int index = -1;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == c) {
				index = i;
			}
		}

		return index;
	}

	/* Input validation */
	private static boolean rootHasSiblings(String s) {
		return getNextSiblings(s) != null;
	}

	private static boolean isInputBalanced(String s) {
		int level = 0;
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '(':
				level++;
				break;
			case ')':
				level--;
				break;
			}
		}

		return level == 0;
	}
}
</textarea>
						</div>
					</div>

					<div class="col-sm-6">
						<div>
							<p>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
								koduülesanne | 2015/2016 | Kevadsemester</p>
						</div>
						<div>Tudengi nimi | Tudengi Id | Katse Id</div>
						<div class="code-div">
							<textarea id="java-code2" style="display: none;">
public class TreeNode {

	private String name;
	private TreeNode firstChild;
	private TreeNode nextSibling;

	TreeNode(String a, TreeNode b, TreeNode c) {
		if (a == null) {
			throw new RuntimeException("Node's name can't be null!");
		}

		if (a.contains(" ")) {
			throw new RuntimeException(
					"Name of the node can't contain space. Name of the node: "
							+ a);
		}

		this.name = a;
		this.firstChild = b;
		this.nextSibling = c;
	}

	public static TreeNode parsePrefix(String str) {
		if (str.contains("()")) {
			throw new RuntimeException(
					"Node cannot contains empty subtree. Input " + str);
		} else if (!checkIfInputIsBalanced(str)) {
			throw new RuntimeException(
					"Input format error. Brackets are not balanced. Input: "
							+ str);
		} else if (checkIfRootHasSiblings(str)) {
			throw new RuntimeException("Root must not have siblings! Input: "
					+ str);
		} else if (str.contains("((")) {
			throw new RuntimeException("Nodes must have a name! Input: " + str);
		} else if (str.contains(",,")) {
			throw new RuntimeException("Sibling must have a name! Input: "
					+ str);
		}

		return commitParse(str);
	}

	public String rightParentheticRepresentation() {
		StringBuffer buffer = new StringBuffer();

		if (this.firstChild != null) {
			buffer.append("(");
			buffer.append(this.firstChild.rightParentheticRepresentation());
			buffer.append(")");
		}

		buffer.append(this.name);

		if (this.nextSibling != null) {
			buffer.append(",");
			buffer.append(this.nextSibling.rightParentheticRepresentation());
		}

		return buffer.toString();
	}

	public static void main(String[] param) {
		// String s = "A(B1,C)";
		String s = "A(B1(D1(E1,G7),F2),C(H))";
		// String s = "6(5(1,3(2),4))";

		System.out.println(s);
		TreeNode testNode = parsePrefix(s);
		System.out.println(testNode.rightParentheticRepresentation());
	}

	/* Parsing helper methods */
	private static String separateName(String node) {
		if (node.indexOf("(") == -1) {
			return node;
		}

		return node.substring(0, node.indexOf("("));
	}

	private static TreeNode formTree(String name, String children,
			String nextSiblings) {
		if (children == null && nextSiblings == null) {
			return new TreeNode(name, null, null);
		} else if (children == null && nextSiblings != null) {
			return new TreeNode(name, null, commitParse(nextSiblings));
		} else if (children != null && nextSiblings == null) {
			return new TreeNode(name, commitParse(children), null);
		} else {
			return new TreeNode(name, commitParse(children),
					commitParse(nextSiblings));
		}
	}

	private static TreeNode commitParse(String str) {
		String name;
		String children = null;
		String nextSiblings = null;

		if (checkForNode(str)) {
			name = separateName(str);
			children = separateChildren(str);
			nextSiblings = separateNextSiblings(str);

			return formTree(name, children, nextSiblings);

		} else {
			String current = separateFirstSibling(str);
			nextSiblings = separateNextSiblings(str);
			name = separateName(current);

			if (checkForChildren(current)) {
				children = separateChildren(current);
			}

			return formTree(name, children, nextSiblings);
		}
	}

	/*
	 * Extract a first sibling Input must not be a Node!
	 */
	public static String separateFirstSibling(String str) {
		int currentLevel = 0;
		for (int index = 0; index < str.length(); index++) {
			switch (str.charAt(index)) {
			case '(':
				currentLevel++;
				break;
			case ')':
				currentLevel--;
				break;
			case ',':
				if (currentLevel == 0) {
					return str.substring(0, index);
				}
				break;
			}
		}

		return str;
	}

	/*
	 * Extract all child from node Expected input type should be node string
	 */
	public static String separateChildren(String str) {
		if (!str.contains("(")) {
			return null;
		}

		return str.substring(str.indexOf("(") + 1, lastIndexOf(str, ')'));
	}

	/*
	 * If node has child / or multiple siblings Input must not be a multiple
	 * siblings string
	 */
	public static boolean checkForChildren(String s) {
		return s.contains("(") || s.contains(")");
	}

	/*
	 * Get all other Siblings, besides first
	 */
	public static String separateNextSiblings(String str) {
		int currentLevel = 0;
		for (int index = 0; index < str.length(); index++) {
			switch (str.charAt(index)) {
			case ')':
				currentLevel--;
				break;
			case '(':
				currentLevel++;
				break;
			case ',':
				if (currentLevel + 1 == 1) {
					return str.substring(index + 1, str.length());
				}
				break;
			}
		}

		return null;
	}

	/*
	 * Check if input type is node / ( leaf ) string or multiple siblings string
	 */
	public static boolean checkForNode(String str) {
		int currentLevel = 0;
		for (int index = 0; index < str.length(); index++) {
			switch (str.charAt(index)) {
			case ')':
				currentLevel--;
				break;
			case '(':
				currentLevel++;
				break;
			case ',':
				if (currentLevel + 1 == 1) {
					return false;
				}
				break;
			}
		}

		return true;
	}

	public static int lastIndexOf(String str, char character) {
		int startingIndex = -1;
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) == character) {
				startingIndex = index;
			}
		}

		return startingIndex;
	}

	/* Input validation */
	private static boolean checkIfInputIsBalanced(String str) {
		int currentLevel = 0;
		for (int index = 0; index < str.length(); index++) {
			switch (str.charAt(index)) {
			case ')':
				currentLevel--;
				break;
			case '(':
				currentLevel++;
				break;
			}
		}

		return currentLevel == 0;
	}

	private static boolean checkIfRootHasSiblings(String str) {
		return separateNextSiblings(str) != null;
	}

}
</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
