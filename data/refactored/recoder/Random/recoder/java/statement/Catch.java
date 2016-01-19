// This file is part of the RECODER library and protected by the LGPL.

package recoder.java.statement;

import java.util.Collections;
import java.util.List;

import recoder.java.ParameterContainer;
import recoder.java.ProgramElement;
import recoder.java.SourceElement;
import recoder.java.SourceVisitor;
import recoder.java.Statement;
import recoder.java.StatementBlock;
import recoder.java.VariableScope;
import recoder.java.declaration.ParameterDeclaration;
import recoder.java.declaration.VariableSpecification;
import recoder.util.Debug;

/**
 * Catch.
 * 
 * @author <TT>AutoDoc</TT>
 */

public class Catch extends Branch implements ParameterContainer, VariableScope {

    /**
	 * serialization id
	 */
    private static final long serialVersionUID = -6747731923114431193L;

    /**
     * Parameter.
     */

    private ParameterDeclaration parameter;

    /**
     * Body.
     */

    private StatementBlock body;

    /**
     * Catch.
     */

    public Catch() {
        super();
    }

    /**
     * Catch.
     * 
     * @param e
     *            a parameter declaration.
     * @param body
     *            a statement.
     */

    public Catch(ParameterDeclaration e, StatementBlock body) {
        super();
        setBody(body);
        setParameterDeclaration(e);
        makeParentRoleValid();
    }

    /**
     * Catch.
     * 
     * @param proto
     *            a catch.
     */

    protected Catch(Catch proto) {
        super(proto);
        if (proto.parameter != null) {
            parameter = proto.parameter.deepClone();
        }
        if (proto.body != null) {
            body = proto.body.deepClone();
        }
        makeParentRoleValid();
    }

    /**
     * Deep clone.
     * 
     * @return the object.
     */

    public Catch deepClone() {
        return new Catch(this);
    }

    /**
     * Make parent role valid.
     */

    public void makeParentRoleValid() {
        if (parameter != null) {
            parameter.setParameterContainer(this);
        }
        if (body != null) {
            body.setStatementContainer(this);
        }
    }

    public SourceElement getLastElement() {
        return (body != null) ? body.getLastElement() : this;
    }

    /**
     * Returns the number of children of this node.
     * 
     * @return an int giving the number of children of this node
     */

    public int getChildCount() {
        int result = 0;
        if (parameter != null)
            result++;
        if (body != null)
            result++;
        return result;
    }

    /**
     * Returns the child at the specified index in this node's "virtual" child
     * array
     * 
     * @param index
     *            an index into this node's "virtual" child array
     * @return the program element at the given position
     * @exception ArrayIndexOutOfBoundsException
     *                if <tt>index</tt> is out of bounds
     */

    public ProgramElement getChildAt(int index) {
        if (parameter != null) {
            if (index == 0)
                return parameter;
            index--;
        }
        if (body != null) {
            if (index == 0)
                return body;
            index--;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getChildPositionCode(ProgramElement child) {
        // role 0: parameter
        // role 1: body
        if (parameter == child) {
            return 0;
        }
        if (body == child) {
            return 1;
        }
        return -1;
    }

    /**
     * Replace a single child in the current node. The child to replace is
     * matched by identity and hence must be known exactly. The replacement
     * element can be null - in that case, the child is effectively removed. The
     * parent role of the new child is validated, while the parent link of the
     * replaced child is left untouched.
     * 
     * @param p
     *            the old child.
     * @param p
     *            the new child.
     * @return true if a replacement has occured, false otherwise.
     * @exception ClassCastException
     *                if the new child cannot take over the role of the old one.
     */

    public boolean replaceChild(ProgramElement p, ProgramElement q) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (parameter == p) {
            ParameterDeclaration r = (ParameterDeclaration) q;
            parameter = r;
            if (r != null) {
                r.setParameterContainer(this);
            }
            return true;
        }
        if (body == p) {
            StatementBlock r = (StatementBlock) q;
            body = r;
            if (r != null) {
                r.setStatementContainer(this);
            }
            return true;
        }
        return false;
    }

    /**
     * Get the number of statements in this container.
     * 
     * @return the number of statements.
     */

    public int getStatementCount() {
        return (body != null) ? 1 : 0;
    }

    /*
     * Return the statement at the specified index in this node's "virtual"
     * statement array. @param index an index for a statement. @return the
     * statement with the given index. @exception ArrayIndexOutOfBoundsException
     * if <tt> index </tt> is out of bounds.
     */

    public Statement getStatementAt(int index) {
        if (body != null && index == 0) {
            return body;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * Get the number of parameters in this container.
     * 
     * @return the number of parameters.
     */

    public int getParameterDeclarationCount() {
        return (parameter != null) ? 1 : 0;
    }

    /*
     * Return the parameter declaration at the specified index in this node's
     * "virtual" parameter declaration array. @param index an index for a
     * parameter declaration. @return the parameter declaration with the given
     * index. @exception ArrayIndexOutOfBoundsException if <tt> index </tt> is
     * out of bounds.
     */

    public ParameterDeclaration getParameterDeclarationAt(int index) {
        if (parameter != null && index == 0) {
            return parameter;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * Get body.
     * 
     * @return the statement.
     */

    public Statement getBody() {
        return body;
    }

    /**
     * Set body.
     * 
     * @param statement
     *            a statement.
     */

    public void setBody(Statement statement) {
        body = (StatementBlock) statement;
    }

    /**
     * Set parent.
     * 
     * @param parent
     *            a try.
     */

    public void setParent(Try parent) {
        this.parent = parent;
    }

    /**
     * Get parameter declaration.
     * 
     * @return the parameter declaration.
     */

    public ParameterDeclaration getParameterDeclaration() {
        return parameter;
    }

    /**
     * Set parameter declaration.
     * 
     * @param p
     *            a parameter declaration.
     */

    public void setParameterDeclaration(ParameterDeclaration p) {
        parameter = p;
    }

    public boolean isDefinedScope() {
        return true;
    }

    public void setDefinedScope(boolean defined) {
        // ignore
    }

    public List<VariableSpecification> getVariablesInScope() {
        if (parameter != null) {
            return parameter.getVariables();
        }
        return Collections.<VariableSpecification>emptyList();
    }

    public VariableSpecification getVariableInScope(String name) {
        Debug.assertNonnull(name);
        if (parameter != null) {
            VariableSpecification v = parameter.getVariableSpecification();
            if (name.equals(v.getName())) {
                return v;
            }
        }
        return null;
    }

    public void addVariableToScope(VariableSpecification var) {
        Debug.assertNonnull(var);
    }

    public void removeVariableFromScope(String name) {
        Debug.assertNonnull(name);
    }

    public void accept(SourceVisitor v) {
        v.visitCatch(this);
    }
}
