// This file is part of the RECODER library and protected by the LGPL.

package recoder.service;

import java.util.List;

import recoder.abstraction.Constructor;
import recoder.abstraction.Field;
import recoder.abstraction.Method;
import recoder.abstraction.Package;
import recoder.abstraction.Type;
import recoder.abstraction.Variable;
import recoder.java.reference.ConstructorReference;
import recoder.java.reference.FieldReference;
import recoder.java.reference.MemberReference;
import recoder.java.reference.PackageReference;
import recoder.java.reference.TypeReference;
import recoder.java.reference.VariableReference;

/**
 * Source information service supporting cross reference information.
 * 
 * @author AL
 */
public interface CrossReferenceSourceInfo extends SourceInfo {

    /**
     * Returns the list of references to a given method (or constructor). The
     * references stem from all known compilation units.
     * 
     * @param m
     *            a method.
     * @return the possibly empty list of references to the given method.
     */
    List<MemberReference> getReferences(Method m);

    /**
     * Returns the list of references to a given constructor. The references
     * stem from all known compilation units.
     * 
     * @param m
     *            a constructor.
     * @return the possibly empty list of references to the given constructor.
     */
    List<ConstructorReference> getReferences(Constructor m);

    /**
     * Returns the list of references to a given variable. The references stem
     * from all known compilation units.
     * 
     * @param v
     *            a variable.
     * @return the possibly empty list of references to the given variable.
     */
    List<VariableReference> getReferences(Variable v);

    /**
     * Returns the list of references to a given field. The references stem from
     * all known compilation units.
     * 
     * @param f
     *            a field.
     * @return the possibly empty list of references to the given field.
     */
    List<FieldReference> getReferences(Field f);

    /**
     * Returns the list of references to a given type. The references stem from
     * all known compilation units. Does <b>not</b> return references to the
     * array types of the given type.
     * 
     * @param t
     *            a type.
     * @return the possibly empty list of references to the given type.
     */
    List<TypeReference> getReferences(Type t);

    /**
     * Returns the list of references to a given type. The references stem from
     * all known compilation units.
     * 
     * @param t
     *            a type.
     * @param includeArrayTypes whether or not to include references to 
     * 			array types of the given type in the result.
     * 				
     * @return the possibly empty list of references to the given type,
     * 				including reference to array types of this type if 
     * 				<code>includeArrayTypes</code> is <code>true</code>.
     */
    List<TypeReference> getReferences(Type t, boolean includeArrayTypes);

    /**
     * Returns the list of references to a given package. The references stem
     * from all known compilation units.
     * 
     * @param p
     *            a package.
     * @return the possibly empty list of references to the given package.
     */
    List<PackageReference> getReferences(Package p);
}
