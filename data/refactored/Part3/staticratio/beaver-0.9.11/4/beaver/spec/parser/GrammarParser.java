/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of Beaver Parser Generator.                       *
 * Copyright (C) 2003,2005 Alexander Demenchuk <alder@softanvil.com>.  *
 * All rights reserved.                                                *
 * See the file "LICENSE" for the terms and conditions for copying,    *
 * distribution and modification of Beaver.                            *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package beaver.spec.parser;

import java.util.ArrayList;
import beaver.comp.util.Log;
import beaver.spec.ast.*;
import beaver.*;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "beaver.grammar".
 */
public class GrammarParser extends Parser {
    static public class Terminals {
        static public final short EOF = 0;
        static public final short IDENT = 1;
        static public final short SEMI = 2;
        static public final short TYPEOF = 3;
        static public final short HEADER = 4;
        static public final short PACKAGE = 5;
        static public final short CLASS = 6;
        static public final short EMBED = 7;
        static public final short INIT = 8;
        static public final short GOAL = 9;
        static public final short IMPORT = 10;
        static public final short IMPLEMENTS = 11;
        static public final short TERMINALS = 12;
        static public final short LEFT = 13;
        static public final short RIGHT = 14;
        static public final short NONASSOC = 15;
        static public final short BAR = 16;
        static public final short CODE = 17;
        static public final short AT = 18;
        static public final short COMMA = 19;
        static public final short TEXT = 20;
        static public final short IS = 21;
        static public final short QUESTION = 22;
        static public final short PLUS = 23;
        static public final short STAR = 24;
        static public final short DOT = 25;

        static public final String[] NAMES = {
            "EOF",
            "IDENT",
            "SEMI",
            "TYPEOF",
            "HEADER",
            "PACKAGE",
            "CLASS",
            "EMBED",
            "INIT",
            "GOAL",
            "IMPORT",
            "IMPLEMENTS",
            "TERMINALS",
            "LEFT",
            "RIGHT",
            "NONASSOC",
            "BAR",
            "CODE",
            "AT",
            "COMMA",
            "TEXT",
            "IS",
            "QUESTION",
            "PLUS",
            "STAR",
            "DOT" };
    } final ParsingTables PARSING_TABLES = new ParsingTables(
        "U9pLb#blLbKGnfzxhsMrq58As8M2W2WeBIrG5gcxY89G41K1TwDnmwY94R$uHUCQz#WVO3H" +
        "#DlBTqbAWWa1RHKIsGjcNja1PbF5txctBAt3U61eIlK#UCtFFp9pjzimxbSBlL6QdbA45Ub" +
        "Bpb6lrkaQZTQskqnYDrVMwGUDKh1ADLwdLEsYaXYiawhITrXKQfUds8$7#gBzKeRcQgCcwH" +
        "RThXcPhckxMWrgaX$MO$QaUQQndbMbdlJYfR3lfHRi6MhiN9pJKJdXnN5Fik1V7TBCTyw9D" +
        "DrgR5wswoLgzQD6jrkB5KUOzwiKHtMD7l3YiU#sm5uVqW1toug0gxQ0N1pJV3dYnN$VRVY$" +
        "sCUy#B$PgeUtrOW#nUxnerc9hzc8tiRkzsAL7R9SNJSGsUR5LZzjMBtPAjjEB7LXtUB4Twt" +
        "Oljc7TveLXzJzrLgUhtTstty0cswH1jjfMgy3MWBeOyTrFT1KwAt3MDRfAUSfFT5jfApL04" +
        "rIalkf5dXYc70ssNpH2GsojisBFjOtgtQ4Fm8QlhLLFzS4tieqn6gn1awpMQbLkgsoLfbgD" +
        "rUWsgxPgtU6ie3R6gXWrCQfZhB0LkjCQhL5tQQPjq1pTPp#p$$MssQgS5JJ6s80r2ri$REk" +
        "nLU7nkstMbNYliotsa$rgP#oC#jiVcgM7j4IFwWayra5dswAk1uyAScY2F3e2TZDKkSYGin" +
        "r1rXnraHoPfMpEgvI82HrHHKI5vC0WJPGREoBtPf0xUtBElJcxlgmwK$tK7sjkMklujDOWh" +
        "RMW6x43ks4jlcmZTsVUzFjDRytlngektxp$mrXost$GoXqEuzRTQST9yTkKk4rkVT8Eqc0D" +
        "8OTUs9UqpZyF5gWmreQHLSlHYZIrQwHapCG$3ytvPb5JET#o59$ib7Mbjjd$cFt2DjKpx0J" +
        "xJxIBHtKo1uzSbNHv9oSo#Tmu80VFN9Ybd7C$IlIUW$JKcqcYBOR3Eqwr08w56L8u1lQ04S" +
        "GdV06#14lXk$1b$9OWNuNlmt8uK$5omZvm5fm3vy45y6cOYV4Dv2ZuBFm0Cc$O2tw4hGBEX" +
        "elXSt0PP2vz1R#5os4DR81Di9Lb4nza4JyQMOIS1XaZ68VzDVIvwDFHtuP3qLcNlgR$FVWn" +
        "#lV8#VGlGiw0pw2xjR66y7dqr$6fGly4$HtqDlGNqR#4V3DgI0NiAroA$0Qwite5leNEVi9" +
        "FaJz0Dzvdo6h8liBFaVMGlOLV8AbaWXRtJHYpcRxuoxdtv3k5$LBqaHroeTjJP$$vvAbCPC" +
        "1v1QTyVkdeWYybxb#ElQmRiSiz$UtfuuAo5BqoHL#QedzutfZkgQ2g2618PI4Oa0aSNMqHy" +
        "fSLN09T9HDHbKJK9H6LIKHj4b6THDGd4HLAn6sCg54YgfI8EYMYPidWnYQ$e$aTcRo8h5d4" +
        "M1DvdyZxSAJxZxAGTHQYbo1BuZf5C22BHT1bKCLPb9iBarfa2d5JqJlfyahUt#MdfqG=");

    static class Events extends Parser.Events {
        private final Log log;

        Events(Log log)
         {
            this.log = log;
        }

        public void scannerError(Scanner.Exception e)
         {
            log.error(e);
        }
        public void syntaxError(Symbol token)
         {
            log.error(token, "unexpected token");
        }
        public void unexpectedTokenRemoved(Symbol token)
         {
            log.warning(token, "recovered by removing unexpected token");
        }
        public void missingTokenInserted(Symbol token)
         {
            log.warning(token, "recovered by inserting missing '" + Terminals.NAMES[token.getId()] + "'");
        }
        public void misspelledTokenReplaced(Symbol token)
         {
            log.warning(token, "recovered by replacing misspelled token with '" + Terminals.NAMES[token.getId()] + "'");
        }
        public void errorPhraseRemoved(Symbol error)
         {
            log.warning(error, "recovered by removing error phrase");
        }
    }

    private Log log;

    public GrammarParser(Log log)
     {
        this();
        report = new Events(this.log = log);
    }

    public GrammarParser() {
        super(PARSING_TABLES);
    }

    protected Symbol invokeReduceAction(int rule_num, int offset) {
        switch (rule_num) {
            case 0: // lst$declaration = declaration
                {
                    final
                     ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
                }
            case 1: // lst$declaration = lst$declaration declaration
                {
                    ((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
                }
            case 4: // lst$rule = rule
                {
                    final
                     ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
                }
            case 5: // lst$rule = lst$rule rule
                {
                    ((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
                }
            case 6: // grammar = opt$lst$declaration.decl lst$rule.rules
                {
                    final Symbol _symbol_decl = _symbols[offset + 1];
                    final ArrayList _list_decl = (ArrayList) _symbol_decl.value;
                    final Declaration[] decl = _list_decl == null ? new Declaration[0] : (Declaration[]) _list_decl.toArray(new Declaration[_list_decl.size()]);
                    final Symbol _symbol_rules = _symbols[offset + 2];
                    final ArrayList _list_rules = (ArrayList) _symbol_rules.value;
                    final Rule[] rules = _list_rules == null ? new Rule[0] : (Rule[]) _list_rules.toArray(new Rule[_list_rules.size()]);

                    return new GrammarTreeRoot(decl, rules);
                }
            case 20: // declaration = error.e SEMI
                {
                    final Symbol e = _symbols[offset + 1];

                    log.error(e, "malformed declaration"); return new Declaration.Error();
                }
            case 21: // header = HEADER CODE.code SEMI
                {
                    final Symbol code = _symbols[offset + 2];

                    return new Declaration.Header(code);
                }
            case 22: // package = PACKAGE TEXT.name SEMI
                {
                    final Symbol name = _symbols[offset + 2];

                    return new Declaration.PackageName(name);
                }
            case 23: // import = IMPORT txt_list.names SEMI
                {
                    final Symbol _symbol_names = _symbols[offset + 2];
                    final ArrayList _list_names = (ArrayList) _symbol_names.value;
                    final beaver.Symbol[] names = _list_names == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_names.toArray(new beaver.Symbol[_list_names.size()]);

                    return new Declaration.Imports(names);
                }
            case 24: // class_name = CLASS TEXT.name SEMI
                {
                    final Symbol name = _symbols[offset + 2];

                    return new Declaration.ClassName(name);
                }
            case 25: // class_code = EMBED CODE.code SEMI
                {
                    final Symbol code = _symbols[offset + 2];

                    return new Declaration.ClassCode(code);
                }
            case 26: // class_init = INIT CODE.code SEMI
                {
                    final Symbol code = _symbols[offset + 2];

                    return new Declaration.ConstructorCode(code);
                }
            case 27: // class_implements = IMPLEMENTS txt_list.names SEMI
                {
                    final Symbol _symbol_names = _symbols[offset + 2];
                    final ArrayList _list_names = (ArrayList) _symbol_names.value;
                    final beaver.Symbol[] names = _list_names == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_names.toArray(new beaver.Symbol[_list_names.size()]);

                    return new Declaration.Implements(names);
                }
            case 28: // grammar_goal = GOAL IDENT.name SEMI
                {
                    final Symbol name = _symbols[offset + 2];

                    return new Declaration.Goal(name);
                }
            case 29: // terminals = TERMINALS sym_list.symbols SEMI
                {
                    final Symbol _symbol_symbols = _symbols[offset + 2];
                    final ArrayList _list_symbols = (ArrayList) _symbol_symbols.value;
                    final beaver.Symbol[] symbols = _list_symbols == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_symbols.toArray(new beaver.Symbol[_list_symbols.size()]);

                    return new Declaration.Terminals(symbols);
                }
            case 30: // left_assoc = LEFT sym_list.symbols SEMI
                {
                    final Symbol _symbol_symbols = _symbols[offset + 2];
                    final ArrayList _list_symbols = (ArrayList) _symbol_symbols.value;
                    final beaver.Symbol[] symbols = _list_symbols == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_symbols.toArray(new beaver.Symbol[_list_symbols.size()]);

                    return new Declaration.LeftAssoc(symbols);
                }
            case 31: // right_assoc = RIGHT sym_list.symbols SEMI
                {
                    final Symbol _symbol_symbols = _symbols[offset + 2];
                    final ArrayList _list_symbols = (ArrayList) _symbol_symbols.value;
                    final beaver.Symbol[] symbols = _list_symbols == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_symbols.toArray(new beaver.Symbol[_list_symbols.size()]);

                    return new Declaration.RightAssoc(symbols);
                }
            case 32: // nonassoc = NONASSOC sym_list.symbols SEMI
                {
                    final Symbol _symbol_symbols = _symbols[offset + 2];
                    final ArrayList _list_symbols = (ArrayList) _symbol_symbols.value;
                    final beaver.Symbol[] symbols = _list_symbols == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_symbols.toArray(new beaver.Symbol[_list_symbols.size()]);

                    return new Declaration.NonAssoc(symbols);
                }
            case 33: // typeof = TYPEOF sym_list.symbols IS TEXT.type SEMI
                {
                    final Symbol _symbol_symbols = _symbols[offset + 2];
                    final ArrayList _list_symbols = (ArrayList) _symbol_symbols.value;
                    final beaver.Symbol[] symbols = _list_symbols == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_symbols.toArray(new beaver.Symbol[_list_symbols.size()]);
                    final Symbol type = _symbols[offset + 4];

                    return new Declaration.TypeOf(symbols, type);
                }
            case 34: // txt_list = TEXT
                {
                    final
                     ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
                }
            case 35: // txt_list = txt_list COMMA TEXT
                {
                    ((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3]); return _symbols[offset + 1];
                }
            case 36: // sym_list = IDENT
                {
                    final
                     ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
                }
            case 37: // sym_list = sym_list COMMA IDENT
                {
                    ((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3]); return _symbols[offset + 1];
                }
            case 38: // rule = IDENT.name IS def_list.defs SEMI
                {
                    final Symbol name = _symbols[offset + 1];
                    final Symbol _symbol_defs = _symbols[offset + 3];
                    final ArrayList _list_defs = (ArrayList) _symbol_defs.value;
                    final Rule.Definition[] defs = _list_defs == null ? new Rule.Definition[0] : (Rule.Definition[]) _list_defs.toArray(new Rule.Definition[_list_defs.size()]);

                    return new Rule(name, defs);
                }
            case 39: // rule = error.e SEMI
                {
                    final Symbol e = _symbols[offset + 1];

                    log.error(e, "malformed production");
                    return new Rule(new Symbol(""), new Rule.Definition[] { });
                }
            case 40: // def_list = definition
                {
                    final
                     ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
                }
            case 41: // def_list = def_list BAR definition
                {
                    ((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
                }
            case 42: // lst$def_element = def_element
                {
                    final
                     ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
                }
            case 43: // lst$def_element = lst$def_element def_element
                {
                    ((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
                }
            case 50: // definition = opt$lst$def_element.elts opt$rule_precedence.prec opt$CODE.code
                {
                    final Symbol _symbol_elts = _symbols[offset + 1];
                    final ArrayList _list_elts = (ArrayList) _symbol_elts.value;
                    final Rule.Definition.Element[] elts = _list_elts == null ? new Rule.Definition.Element[0] : (Rule.Definition.Element[]) _list_elts.toArray(new Rule.Definition.Element[_list_elts.size()]);
                    final Symbol prec = _symbols[offset + 2];
                    final Symbol code = _symbols[offset + 3];

                    return new Rule.Definition(elts, prec, code);
                }
            case 51: // definition = error.e
                {
                    final Symbol e = _symbols[offset + 1];

                    log.error(e, "malformed nonterminal definition");
                    return new Rule.Definition(null, new Symbol(null), new Symbol(null));
                }
            case 56: // def_element = IDENT.name opt$alias.alias opt$ebnf_symbol.ebnf_mark
                {
                    final Symbol name = _symbols[offset + 1];
                    final Symbol alias = _symbols[offset + 2];
                    final Symbol ebnf_mark = _symbols[offset + 3];

                    return new Rule.Definition.Element(name, alias, ebnf_mark);
                }
            case 2: // opt$lst$declaration =
            case 44: // opt$lst$def_element =
            case 46: // opt$rule_precedence =
            case 48: // opt$CODE =
            case 52: // opt$alias =
            case 54: // opt$ebnf_symbol =
                {
                    return new Symbol(null);
                }
            case 3: // opt$lst$declaration = lst$declaration
            case 7: // declaration = header
            case 8: // declaration = package
            case 9: // declaration = import
            case 10: // declaration = class_name
            case 11: // declaration = class_code
            case 12: // declaration = class_init
            case 13: // declaration = class_implements
            case 14: // declaration = grammar_goal
            case 15: // declaration = typeof
            case 16: // declaration = terminals
            case 17: // declaration = left_assoc
            case 18: // declaration = right_assoc
            case 19: // declaration = nonassoc
            case 45: // opt$lst$def_element = lst$def_element
            case 47: // opt$rule_precedence = rule_precedence
            case 49: // opt$CODE = CODE
            case 53: // opt$alias = alias
            case 55: // opt$ebnf_symbol = ebnf_symbol
            case 58: // ebnf_symbol = QUESTION
            case 59: // ebnf_symbol = PLUS
            case 60: // ebnf_symbol = STAR
                {
                    return _symbols[offset + 1];
                }
            case 57: // alias = DOT IDENT
            case 61: // rule_precedence = AT IDENT
                {
                    return _symbols[offset + 2];
                }
            default:
                throw new IllegalArgumentException("unknown production #" + rule_num);
        }
    }
}
