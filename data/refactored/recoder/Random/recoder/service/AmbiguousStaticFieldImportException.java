// This file is part of the RECODER library and protected by the LGPL.

package recoder.service;

import java.util.ArrayList;
import java.util.List;

import recoder.ModelException;
import recoder.abstraction.Field;
import recoder.abstraction.Variable;
import recoder.java.Import;

/**
 * Exception indicating that two static imports find fields with the same name.
 * 
 * @author Tobias Gutzmann
 */
public class AmbiguousStaticFieldImportException extends ModelException {

    /**
	 * serialization id
	 */
    private static final long serialVersionUID = -2587328246662978766L;

    private Import importStatement1, importStatement2;

    private Variable version1;

    private Variable version2;

    /**
     * Constructor without explanation text.
     * 
     * @param importStatement1
     *            the first import found to be ambiguous.
     * @param importStatement2
     *            the second import found to be ambiguous.
     * @param version1
     *            the first possible field.
     * @param version2
     *            the second possible field.
     */
    public AmbiguousStaticFieldImportException(Import importStatement1, Import importStatement2, Variable version1, Variable version2) {
        this.importStatement1 = importStatement1;
        this.importStatement2 = importStatement2;
        this.version1 = version1;
        this.version2 = version2;
    }

    /**
     * Constructor with an explanation text.
     * 
     * @param s
     *            an explanation.
     * @param importStatement
     *            the import found to be ambiguous.
     * @param version1
     *            the first possible type.
     * @param version2
     *            the second possible type.
     */
    public AmbiguousStaticFieldImportException(String s, Import importStatement1, Import importStatement2, Field version1, Field version2) {
        super(s);
        this.importStatement1 = importStatement1;
        this.importStatement2 = importStatement2;
        this.version1 = version1;
        this.version2 = version2;
    }

    /**
     * Returns the import statement that was found ambiguous.
     */
    public List<Import> getAmbiguousImports() {
        List<Import> list = new ArrayList<Import>(2);
        list.add(importStatement1);
        list.add(importStatement2);
        return list;
    }

    /**
     * Returns the possible imported class types.
     */
    public List<Variable> getChoices() {
        List<Variable> list = new ArrayList<Variable>(2);
        list.add(version1);
        list.add(version2);
        return list;
    }
}
