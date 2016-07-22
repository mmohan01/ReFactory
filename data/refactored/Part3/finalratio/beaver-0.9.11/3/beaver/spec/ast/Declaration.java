/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of Beaver Parser Generator.                       *
 * Copyright (C) 2003,2004 Alexander Demenchuk <alder@softanvil.com>.  *
 * All rights reserved.                                                *
 * See the file "LICENSE" for the terms and conditions for copying,    *
 * distribution and modification of Beaver.                            *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package beaver.spec.ast;

import beaver.Symbol;

/**
 *
 */
public class Declaration extends Node {
    static public abstract class NameContainer extends Declaration {
        public final Symbol name;

        public NameContainer(Symbol name)
         {
            this.name = name;
        }

        public String getName()
         {
            return (String) name.value;
        }
    }

    static public class CodeContainer extends Declaration {

        protected CodeContainer(Symbol code)
         {
            super.code = code;
        }

        public String getCode()
         {
            return (String) code.value;
        }
    }
        public final Symbol[] symbols;

    static public class SymbolsContainer extends Declaration {
        public final Symbol type;

        protected SymbolsContainer(Symbol[] symbols)
         {
            super.symbols = symbols;
        }
    }

    static public class Error extends Declaration {
        public void accept(final TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class Goal extends NameContainer {
        public Goal(Symbol name)
         {
            super(name);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class ListType extends NameContainer {
        public ListType(Symbol name)
         {
            super(name);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class Header extends CodeContainer {
        public Header(Symbol code)
         {
            super(code);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class PackageName extends NameContainer {
        public PackageName(Symbol name)
         {
            super(name);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class Implements extends SymbolsContainer {
        public Implements(Symbol[] names)
         {
            super(names);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class Imports extends SymbolsContainer {
        public Imports(Symbol[] symbols)
         {
            super(symbols);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }
        public final Symbol code;

    static public class ClassName extends NameContainer {
        public ClassName(Symbol name)
         {
            super(name);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class ClassCode extends CodeContainer {
        public ClassCode(Symbol code)
         {
            super(code);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class ConstructorCode extends CodeContainer {
        public ConstructorCode(Symbol code)
         {
            super(code);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class LeftAssoc extends SymbolsContainer {
        public LeftAssoc(Symbol[] symbols)
         {
            super(symbols);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class RightAssoc extends SymbolsContainer {
        public RightAssoc(Symbol[] symbols)
         {
            super(symbols);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class NonAssoc extends SymbolsContainer {
        public NonAssoc(Symbol[] symbols)
         {
            super(symbols);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class Terminals extends SymbolsContainer {
        public Terminals(Symbol[] tokens)
         {
            super(tokens);
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }

    static public class TypeOf extends SymbolsContainer {

        public TypeOf(Symbol[] symbols, Symbol type)
         {
            super(symbols);
            super.type = type;
        }

        public String getTypeName()
         {
            return type == null ? null : (String) type.value;
        }

        public void accept(TreeWalker walker)
         {
            walker.visit(this);
        }
    }
}