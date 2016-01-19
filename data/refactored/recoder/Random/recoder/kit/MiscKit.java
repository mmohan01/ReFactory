// This file is part of the RECODER library and protected by the LGPL

package recoder.kit;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recoder.ProgramFactory;
import recoder.convenience.ForestWalker;
import recoder.convenience.TreeWalker;
import recoder.java.Comment;
import recoder.java.CompilationUnit;
import recoder.java.Import;
import recoder.java.NamedProgramElement;
import recoder.java.NonTerminalProgramElement;
import recoder.java.ProgramElement;
import recoder.java.ScopeDefiningElement;
import recoder.java.SourceElement;
import recoder.java.Statement;
import recoder.java.StatementBlock;
import recoder.java.declaration.MemberDeclaration;
import recoder.java.declaration.TypeDeclaration;
import recoder.java.reference.UncollatedReferenceQualifier;
import recoder.list.generic.ASTArrayList;
import recoder.list.generic.ASTList;
import recoder.service.ChangeHistory;
import recoder.util.Debug;

/**
 * This utility class provides queries, factory methods and transformations on
 * abstract entities that do not fall into one of the abstraction categories
 * (Types, Variables and so on).
 * <P>
 * One class of auxiliary methods deals with child-parent relations. There are
 * queries to navigate trees upwards until a certain parent class is reached,
 * most prominently {@link #getParentCompilationUnit}. A related query,
 * {@link #contains}checks the syntactical containment relation
 * (is-equals-or-ancestor-of).
 * <P>
 * Some convenience tranformations allow to remove, prepend or append single
 * childs. Since these transformations report their changes on this low level
 * only, it is admissible to optimize change notifications by leaving the change
 * history object <CODE>null</CODE> and notifying a broader change instead.
 * 
 * @author Andreas Ludwig
 * @author Rainer Neumann
 */
public class MiscKit {

    private MiscKit() {
        super();
    }

    /**
     * Factory method that creates a not yet collated reference qualifier from a
     * qualified name. This can be a reference to a field or parameter or
     * variable, or a reference to a package, an inner or outer or local type.
     * 
     * @return a qualified reference with the given name; parent links of the
     *         reference are made valid.
     */
    public static UncollatedReferenceQualifier createUncollatedReferenceQualifier(ProgramFactory factory, String name) {
        UncollatedReferenceQualifier result = null;
        int i, j = -1;
        do {
            i = j + 1;
            j = name.indexOf(".", i);
            String token = (j > i) ? name.substring(i, j) : name.substring(i);
            result = factory.createUncollatedReferenceQualifier(result, factory.createIdentifier(token));
            // null is admissible as prefix
        } while(
        j > i);
        result.makeAllParentRolesValid();
        return result;
    }

    /**
     * Query checking if a given non terminal is identical to or an ancestor of
     * the given node. If any of the elements is <CODE>null</CODE>, there is
     * no ancestor relation.
     * 
     * @param root
     *            a non terminal that might be the ancestor of the given
     *            element.
     * @param node
     *            a program element that might be a descendant of (or identical
     *            to) the given non terminal.
     * @return <CODE>true</CODE>, if the given root is an ancestor of the
     *         given node, <CODE>false</CODE> otherwise.
     */
    public static boolean contains(NonTerminalProgramElement root, ProgramElement node) {
        if (root == null) {
            return false;
        }
        while (node != null) {
            if (node == root) {
                return true;
            }
            node = node.getASTParent();
        }
        return false;
    }

    /**
     * Auxiliary transformation that sets the name of the specified element to
     * the specified string, creating a proper identifier.
     * 
     * @param e
     *            the element to name.
     * @param name
     *            the future element name.
     * @since 0.63
     */
    public static void setName(NamedProgramElement e, String name) {
        if (!name.equals(e.getName())) {
            Transformation.doAttach(e.getFactory().createIdentifier(name), e);
        }
    }



    /**
     * @deprecated use UnitKit.getCompilationUnit instead
     */
    public static CompilationUnit getParentCompilationUnit(ProgramElement pe) {
        return UnitKit.getCompilationUnit(pe);
    }

    // ---> TypeKit?

    /**
     * Query returning the type declaration the given program element is
     * contained within. Returns <CODE>null</CODE>, if the program element is
     * not part of a type declaration, e.g. a CompilationUnit.
     * 
     * @param pe
     *            a program element.
     * @return the innermost type declaration containing pe, or <CODE>null
     *         </CODE>.
     */
    public static TypeDeclaration getParentTypeDeclaration(ProgramElement pe) {
        do {
            pe = pe.getASTParent();
        } while((pe != null) && !(pe instanceof TypeDeclaration));
        return (TypeDeclaration) pe;
    }

    /**
     * Query returning the innermost scope defining element the given program
     * element is contained within. If the given program element is a scope
     * defining element, it is returned.
     * 
     * @param pe
     *            a program element.
     * @return the innermost scope defining element type declaration equals pe
     *         or containing pe.
     */
    public static ScopeDefiningElement getScopeDefiningElement(ProgramElement pe) {
        while (!(pe instanceof ScopeDefiningElement)) {
            pe = pe.getASTParent();
        }
        return (ScopeDefiningElement) pe;
    }

    /**
     * Query returning the member declaration the given program element is
     * contained within. Returns <CODE>null</CODE>, if the program element is
     * not part of a member declaration, e.g. a CompilationUnit.
     * 
     * @param pe
     *            a program element.
     * @return the innermost member declaration containing pe, or <CODE>null
     *         </CODE>.
     */
    public static MemberDeclaration getParentMemberDeclaration(ProgramElement pe) {
        do {
            pe = pe.getASTParent();
        } while((pe != null) && !(pe instanceof MemberDeclaration));
        return (MemberDeclaration) pe;
    }

    /**
     * Query that checks if all parent links are made valid in the given
     * subtree; useful for debugging purposes. Returns the first program element
     * encountered that has an invalid parent link, or <CODE>null</CODE> if
     * every element is linked correctly.
     * 
     * @param root
     *            the root of the subtree to check (is not checked itself).
     * @return an descendant of root that has a bad parent link, or <CODE>null
     *         </CODE> if there is no such element.
     */
    public static ProgramElement checkParentLinks(ProgramElement root) {
        if (root instanceof NonTerminalProgramElement) {
            NonTerminalProgramElement nt = (NonTerminalProgramElement) root;
            for (int s = nt.getChildCount(), i = 0; i < s; i += 1) {
                ProgramElement child = nt.getChildAt(i);
                if (child.getASTParent() != nt) {
                    return child;
                }
                child = checkParentLinks(child);
                if (child != null) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * Transformation that removes a single child from its parent. This method
     * conveniently computes the position of the child and handles the change
     * history notification. The method can also handle compilation units
     * properly, but otherwise assumes that the parent link is defined. This
     * method is not very useful if the change history is left undefined, but
     * will work nethertheless.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param child
     *            the child to remove from its parent.
     * @deprecated replaced by Transformation.replace
     */
    public static void remove(ChangeHistory ch, ProgramElement child) {
        Debug.assertNonnull(child);
        if (child instanceof CompilationUnit) {
            if (ch != null) {
                ch.detached(child, 0);
            }
        } else {
            NonTerminalProgramElement parent = child.getASTParent();
            Debug.assertNonnull(parent);
            int oldIndex = parent.getChildPositionCode(child);
            parent.replaceChild(child, null);
            if (ch != null) {
                ch.detached(child, parent, oldIndex);
            }
        }
    }

    /**
     * Transformation that replaces a single child by a different one. This
     * method performs the exchange and handles the change history notification.
     * The method can also handle compilation units properly, but otherwise
     * assumes that the parent link is defined. This method is not very useful
     * if the change history is left undefined, but will work nethertheless.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param child
     *            the child to remove from its parent.
     * @param Replacement
     *            the child to replace its original (must be of appropriate
     *            type).
     * @deprecated replaced by Transformation.replace
     */
    public static void replace(ChangeHistory ch, ProgramElement child, ProgramElement replacement) {
        Debug.assertNonnull(child, replacement);
        if (child != replacement) {
            if (!(child instanceof CompilationUnit)) {
                NonTerminalProgramElement parent = child.getASTParent();
                parent.replaceChild(child, replacement);
            }
            if (ch != null) {
                ch.replaced(child, replacement);
            }
        }
    }

    /**
     * Transformation that appends or prepends the given child to the list in
     * the given parent. No checks for redundancy or validity are performed.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param parent
     *            the parent containing a list of childs to append to.
     * @param child
     *            the child to be appended to the list in the parent.
     */
    private static void add(ChangeHistory ch, CompilationUnit parent, Import child, boolean asHead) {
        Debug.assertNonnull(parent, child);
        ASTList<Import> list = parent.getImports();
        if (list == null) {
            parent.setImports(new ASTArrayList<Import>(child));
        } else {
            if (asHead) {
                list.add(0, child);
            } else {
                list.add(child);
            }
        }
        child.setParent(parent); // make parent link valid
        if (
        ch != null) {
            ch.attached(child);
        }
    }

    /**
     * Transformation that appends the given Import to the list in the given
     * Compilation Unit. No checks for redundancy or validity are performed.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param parent
     *            the parent containing a list of childs to append to.
     * @param child
     *            the child to be appended to the list in the parent.
     */
    public static void append(ChangeHistory ch, CompilationUnit parent, Import child) {
        add(ch, parent, child, false);
    }

    /**
     * Transformation that prepends the given Import to the list in the given
     * Compilation Unit. No checks for redundancy or validity are performed.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param parent
     *            the parent containing a list of childs to prepend.
     * @param child
     *            the child to be prepended to the list in the parent.
     */
    public static void prepend(ChangeHistory ch, CompilationUnit parent, Import child) {
        add(ch, parent, child, true);
    }

    /**
     * Transformation that appends or prepends the given child to the list in
     * the given parent. No checks for redundancy or vadility are performed.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param parent
     *            the parent containing a list of childs to append to.
     * @param child
     *            the child to be appended to the list in the parent.
     */
    private static void add(ChangeHistory ch, StatementBlock parent, Statement child, boolean asHead) {
        Debug.assertNonnull(parent, child);
        ASTList<Statement> list = parent.getBody();
        if (list == null) {
            parent.setBody(new ASTArrayList<Statement>(child));
        } else {
            if (asHead) {
                list.add(0, child);
            } else {
                list.add(child);
            }
        }
        child.setStatementContainer(parent); // make parent link valid
        if (
        ch != null) {
            ch.attached(child);
        }
    }

    /**
     * Transformation that appends the given Statement to the list in the given
     * StatementBlock. No checks for redundancy or vadility are performed.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param parent
     *            the parent containing a list of childs to append to.
     * @param child
     *            the child to be appended to the list in the parent.
     */
    public static void append(ChangeHistory ch, StatementBlock parent, Statement child) {
        add(ch, parent, child, false);
    }

    /**
     * Transformation that prepends the given Statement to the list in the given
     * StatementBlock. No checks for redundancy or vadility are performed.
     * 
     * @param ch
     *            the change history to notify (may be <CODE>null</CODE>).
     * @param parent
     *            the parent containing a list of childs to prepend.
     * @param child
     *            the child to be prepended to the list in the parent.
     */
    public static void prepend(ChangeHistory ch, StatementBlock parent, Statement child) {
        add(ch, parent, child, true);
    }

    /**
     * Removes relative and absolute indentation information from the given
     * tree.
     * 
     * @param root
     *            the root of the subtree.
     */
    public static void unindent(ProgramElement root) {
        TreeWalker w = new TreeWalker(root);
        SourceElement.Position undef = SourceElement.Position.UNDEFINED;
        while (w.next()) {
            ProgramElement pe = w.getProgramElement();
            pe.setRelativePosition(undef);
            pe.setStartPosition(undef);
            pe.setEndPosition(undef);
        }
    }

    /**
     * Tries to minimize memory consumption of the given tree by "nulling" of empty ASTLists,
     * as well as  calling trimToSize() for non-empty ASTLists. The method itself is implemented
     * by reflection and may thus be quite slow.
     * @param root the tree root to start from
     * @since 0.93
     */
    public static void compact(ProgramElement root) {
        TreeWalker w = new TreeWalker(root);
        while (w.next()) {
            ProgramElement pe = w.getProgramElement();
            Field[] fields = pe.getClass().getDeclaredFields();
            for (Field field: fields) {
                if (ASTList.class.isAssignableFrom(field.getType())) {
                    try {
                        field.setAccessible(true);
                        ASTList<?> aal = (ASTList<?>)field.get(pe);
                        if (aal == null) continue;
                        if (aal.size() == 0)
                            field.set(pe, null);
                         else {
//							try {
//							Field f = ArrayList.class.getDeclaredField("elementData");
//							f.setAccessible(true);
//							if (((Object[])f.get(aal)).length != aal.size())
//								System.out.println("Trimming: " + pe.getClass().getName() + "." + field.getName());
//							} catch (Exception e) {
//								e.printStackTrace();
//								System.exit(-1);
//							}
                            aal.trimToSize();
                        }
                    } catch (Exception e) {
                        // ignore!
    }
                }
            }
        }
    }

    /**
     * Adds object mappings from original to cloned versions for each node in a
     * tree to an existing map.
     * 
     * @param originalRoot
     *            the root of the original tree.
     * @param cloneRoot
     *            the root of the cloned tree.
     * @param map
     *            the map in which to put the node pairs.
     */
    public static void mapClones(ProgramElement originalRoot, ProgramElement cloneRoot, Map<ProgramElement, ProgramElement> map) {
        Debug.assertNonnull(originalRoot, cloneRoot, map);
        for (TreeWalker w1 = new TreeWalker(originalRoot), w2 = new TreeWalker(cloneRoot); w1.next() & w2.next(); map.put(w1.getProgramElement(), w2.getProgramElement())) {
            // logic contained in loop control
    }
    }

    /**
     * Returns the element in the cloned tree corresponding to the original node
     * in the original tree. This method will report the original element if it
     * does not occur within the first tree, or if the second tree is smaller
     * than the first one.
     * <P>
     * If there are many queries for clones, it is more efficient to instantiate
     * an own mapping using a hash table and the {@link #mapClones}auxiliary.
     * 
     * @param original
     *            the element to find the corresponding clone for.
     * @param originalRoot
     *            the root of the original tree.
     * @param cloneRoot
     *            the root of the cloned tree.
     */
    public static ProgramElement getClone(ProgramElement original, ProgramElement originalRoot, ProgramElement cloneRoot) {
        Debug.assertNonnull(original, originalRoot, cloneRoot);
        TreeWalker w1 = new TreeWalker(originalRoot);
        TreeWalker w2 = new TreeWalker(cloneRoot);
        while (w1.next() & w2.next()) {
            if (w1.getProgramElement() == original) {
                return w2.getProgramElement();
            }
        }
        return original;
    }

    public static void printASTStatistics(List<CompilationUnit> cul) {
        HashMap<Class<?>, Integer> countMap = new HashMap<Class<?>, Integer>();
        ForestWalker fw = new ForestWalker(cul);
        while (fw.next()) {
            ProgramElement pe = fw.getProgramElement();
            Integer i = countMap.get(pe.getClass());
            if (i == null)
                i = 1;
             else
                i++;
            countMap.put(pe.getClass(), i);
            if (pe.getComments() != null) {
                Integer cc = countMap.get(Comment.class);
                if (cc == null)
                    cc = pe.getComments().size();
                 else
                    cc += pe.getComments().size();
                countMap.put(Comment.class, cc);
            }
        }
        int total = 0;
        for (Map.Entry<Class<?>, Integer> me: countMap.entrySet()) {
            System.out.println(me.getKey().getName() + " " + me.getValue());
            total += me.getValue();
        }
        System.out.println("Total: " + total + " ProgramElements / comments");
    }
}
