// This file is part of the RECODER library and protected by the LGPL.

package recoder.java.expression;

import recoder.java.Expression;
import recoder.java.ExpressionContainer;
import recoder.java.JavaProgramElement;
import recoder.java.NonTerminalProgramElement;
import recoder.java.TerminalProgramElement;

/**
 * Literal.
 * 
 * @author <TT>AutoDoc</TT>
 */

public abstract class Literal extends JavaProgramElement implements Expression, TerminalProgramElement {

    /**
	 * 
	 */
    private static long serialVersionUID = 1L;

    /**
     * Expression parent.
     */

    private ExpressionContainer expressionParent;

    /**
     * Literal.
     */

    public Literal() {
        // nothing to do
    }

    /**
     * Literal.
     * 
     * @param proto
     *            a literal.
     */

    protected Literal(Literal proto) {
        super(proto);
    }

    /**
     * Get AST parent.
     * 
     * @return the non terminal program element.
     */

    public NonTerminalProgramElement getASTParent() {
        return expressionParent;
    }

    /**
     * Get expression container.
     * 
     * @return the expression container.
     */

    public ExpressionContainer getExpressionContainer() {
        return expressionParent;
    }

    /**
     * Set expression container.
     * 
     * @param c
     *            an expression container.
     */

    public void setExpressionContainer(ExpressionContainer c) {
        expressionParent = c;
    }

    public abstract Object getEquivalentJavaType();
}
