package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import java.util.ArrayList;
import java.util.Optional;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 */
public class MSListener extends MiniSchemeBaseListener {

    /**
     * ParseTreeProperty map of parser rules being constructed overtime.
     */
    private final ParseTreeProperty<MSSyntaxTree> map;

    /**
     * Root of the AST being constructed.
     */
    private final MSSyntaxTree root;

    public MSListener() {
        this.root = new MSSyntaxTree();
        this.map = new ParseTreeProperty<>();
    }

    @Override
    public void exitMiniScheme(MiniSchemeParser.MiniSchemeContext ctx) {
        super.exitMiniScheme(ctx);
        for (int i = 0; i < ctx.children.size(); i++) {
            if (ctx.getChild(i) != null) {
                this.root.addChild(this.map.get(ctx.getChild(i)));
            }
        }
    }

    @Override
    public void exitDecl(MiniSchemeParser.DeclContext ctx) {
        super.exitDecl(ctx);
        this.map.put(ctx, this.map.get(ctx.getChild(0)));
    }

    @Override
    public void exitVarDecl(MiniSchemeParser.VarDeclContext ctx) {
        super.exitVarDecl(ctx);
        this.map.put(ctx, new MSVariableDeclarationNode(this.map.get(ctx.term()), this.map.get(ctx.expr())));
    }

    @Override
    public void exitVarDeclRead(MiniSchemeParser.VarDeclReadContext ctx) {
        super.exitVarDeclRead(ctx);
        int readOpType = ((TerminalNode) ctx.readop().getChild(0)).getSymbol().getType();
        this.map.put(ctx, new MSDeclarationReadNode(readOpType, this.map.get(ctx.term())));
    }

    @Override
    public void exitProcDecl(MiniSchemeParser.ProcDeclContext ctx) {
        super.exitProcDecl(ctx);
        ArrayList<MSSyntaxTree> params = new ArrayList<>();
        // If we have parameters, get them now.
        if (ctx.procParams() != null) {
            for (ParseTree pt : ctx.procParams().expr()) {
                params.add(this.map.get(pt));
            }
        }
        this.map.put(ctx, new MSProcedureDeclarationNode(this.map.get(ctx.term()),
                                    params, this.map.get(ctx.procBody().expr())));
    }

    @Override
    public void exitLambdaDecl(MiniSchemeParser.LambdaDeclContext ctx) {
        super.exitLambdaDecl(ctx);
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        if (ctx.lambdaParams() != null) {
            for (ParseTree pt : ctx.lambdaParams().expr()) {
                lambdaParams.add(this.map.get(pt));
            }
        }
        this.map.put(ctx, new MSLambdaDeclarationNode(this.map.get(ctx.term()),
                            lambdaParams, this.map.get(ctx.lambdaBody().expr())));
    }

    @Override
    public void exitExpr(MiniSchemeParser.ExprContext ctx) {
        super.exitExpr(ctx);
        this.map.put(ctx, this.map.get(ctx.children.get(0)));
    }

    @Override
    public void exitExprSymbol(MiniSchemeParser.ExprSymbolContext ctx) {
        super.exitExprSymbol(ctx);
        this.map.put(ctx, new MSSymbolNode(this.map.get(ctx.getChild(1))));
    }

    @Override
    public void exitExprSymbolComponent(MiniSchemeParser.ExprSymbolComponentContext ctx) {
        super.exitExprSymbolComponent(ctx);
        if ((ctx.term() != null && ctx.term().ID() != null) || ctx.op() != null) {
            this.map.put(ctx, new MSSymbolLiteralNode(ctx.getChild(0).getChild(0).getText()));
        } else if (ctx.term() != null) {
            this.map.put(ctx, this.map.get(ctx.term()));
        } else if (ctx.exprCall() != null) {
            this.map.put(ctx, this.map.get(ctx.exprCall()));
        } else if (ctx.exprSymbol() != null) {
            this.map.put(ctx, this.map.get(ctx.exprSymbol()));
        } else {
            MSPairNode parentPair = null;
            MSPairNode prevPair = null;
            for (int i = ctx.exprSymbolComponent().size() - 1; i >= 0; i--) {
                MSSyntaxTree rexpr = this.map.get(ctx.exprSymbolComponent(i));
                prevPair = new MSPairNode(MSNodeType.LIST, rexpr, prevPair);
            }
            // If they enter the empty list, then we need to add a "blank" pair node.
            parentPair = Optional.ofNullable(prevPair).orElse(new MSPairNode());
            this.map.put(ctx, parentPair);
        }
    }

    @Override
    public void exitExprCons(MiniSchemeParser.ExprConsContext ctx) {
        super.exitExprCons(ctx);
        MSSyntaxTree lhsExpr = this.map.get(ctx.expr(0));
        MSSyntaxTree rhsExpr = this.map.get(ctx.expr(1));
        this.map.put(ctx, new MSPairNode(MSNodeType.PAIR, lhsExpr, rhsExpr));
    }

    @Override
    public void exitExprList(MiniSchemeParser.ExprListContext ctx) {
        super.exitExprList(ctx);
        MSPairNode parentPair = null;
        MSPairNode prevPair = null;
        for (int i = ctx.expr().size() - 1; i >= 0; i--) {
            MSSyntaxTree rexpr = this.map.get(ctx.expr(i));
            prevPair = new MSPairNode(MSNodeType.LIST, rexpr, prevPair);
        }
        // If they enter the empty list, then we need to add a "blank" pair node.
        parentPair = Optional.ofNullable(prevPair).orElse(new MSPairNode());
        this.map.put(ctx, parentPair);
    }

    @Override
    public void exitExprVector(MiniSchemeParser.ExprVectorContext ctx) {
        super.exitExprVector(ctx);
        ArrayList<MSSyntaxTree> elements = new ArrayList<>();
        if (ctx.expr() != null) {
            for (ParseTree pt : ctx.expr()) {
                elements.add(this.map.get(pt));
            }
        }
        this.map.put(ctx, new MSVectorNode(elements));
    }

    @Override
    public void exitExprSet(MiniSchemeParser.ExprSetContext ctx) {
        int setOpType = ((TerminalNode) ctx.setop().getChild(0)).getSymbol().getType();
        ArrayList<MSSyntaxTree> setData = new ArrayList<>();
        for (ParseTree pt : ctx.expr()) {
            setData.add(this.map.get(pt));
        }
        this.map.put(ctx, new MSSetNode(setOpType, this.map.get(ctx.term()), setData));
    }

    @Override
    public void exitExprSetRead(MiniSchemeParser.ExprSetReadContext ctx) {
        super.exitExprSetRead(ctx);
        int readOpType = ((TerminalNode) ctx.readop().getChild(0)).getSymbol().getType();
        this.map.put(ctx, new MSSetReadNode(readOpType, this.map.get(ctx.term())));
    }

    @Override
    public void exitExprCall(MiniSchemeParser.ExprCallContext ctx) {
        super.exitExprCall(ctx);
        ArrayList<MSSyntaxTree> procArgs = new ArrayList<>();
        if (ctx.args() != null) {
            for (ParseTree pt : ctx.args().expr()) {
                procArgs.add(this.map.get(pt));
            }
        }

        ArrayList<MSSyntaxTree> lambdaArgs = new ArrayList<>();
        if (ctx.lambdaArgs() != null) {
            for (ParseTree pt : ctx.lambdaArgs().expr()) {
                lambdaArgs.add(this.map.get(pt));
            }
        }
        this.map.put(ctx, new MSCallNode(this.map.get(ctx.term()), procArgs, lambdaArgs));
    }

    @Override
    public void exitExprLetDecl(MiniSchemeParser.ExprLetDeclContext ctx) {
        super.exitExprLetDecl(ctx);
        // Child 1 is which type of let we're using.
        MSLetDeclarationNode.LetType letType = null;

        // Extrapolate the let type.
        if (ctx.exprLetNamed() == null) {
            int intLetType = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
            switch (intLetType) {
                case MiniSchemeParser.LET: letType = MSLetDeclarationNode.LetType.LET; break;
                case MiniSchemeParser.LETSTAR: letType = MSLetDeclarationNode.LetType.LET_STAR; break;
                case MiniSchemeParser.LETREC: letType = MSLetDeclarationNode.LetType.LET_REC; break;
                default: throw new IllegalArgumentException("Parser error");
            }
        } else {
            letType = MSLetDeclarationNode.LetType.LET_NAMED;
        }

        ArrayList<MSSyntaxTree> declarations = new ArrayList<>();
        if (ctx.letDecl() != null) {
            // We can't use an enhanced for loop since we're traversing over two distinct rules.
            for (int i = 0; i < ctx.letDecl().expr().size(); i++) {
                MSSyntaxTree term = this.map.get(ctx.letDecl().term(i));
                MSSyntaxTree expr = this.map.get(ctx.letDecl().expr(i));
                declarations.add(new MSVariableDeclarationNode(term, expr));
            }
        }

        // If they have a name, extract it here and create the node.
        if (ctx.exprLetNamed() != null) {
            MSIdentifierNode idNode = new MSIdentifierNode(ctx.exprLetNamed().ID().getText());
            this.map.put(ctx, new MSLetDeclarationNode(letType, idNode, declarations, this.map.get(ctx.expr())));
        } else {
            this.map.put(ctx, new MSLetDeclarationNode(letType, declarations, this.map.get(ctx.expr())));
        }
    }

    @Override
    public void exitExprLambdaDecl(MiniSchemeParser.ExprLambdaDeclContext ctx) {
        super.exitExprLambdaDecl(ctx);
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        if (ctx.lambdaParams() != null) {
            for (ParseTree pt : ctx.lambdaParams().expr()) {
                lambdaParams.add(this.map.get(pt));
            }
        }
        this.map.put(ctx, new MSLambdaDeclarationNode(lambdaParams, this.map.get(ctx.lambdaBody().expr())));
    }

    @Override
    public void exitExprLambdaDeclCall(MiniSchemeParser.ExprLambdaDeclCallContext ctx) {
        super.exitExprLambdaDeclCall(ctx);
        // Now retrieve the params.
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        if (ctx.lambdaParams() != null) {
            for (ParseTree pt : ctx.lambdaParams().expr()) {
                lambdaParams.add(this.map.get(pt));
            }
        }

        // Lastly, retrieve the args.
        ArrayList<MSSyntaxTree> lambdaArgs = new ArrayList<>();
        if (ctx.lambdaArgs() != null) {
            for (ParseTree pt : ctx.lambdaArgs().expr()) {
                lambdaArgs.add(this.map.get(pt));
            }
        }

        this.map.put(ctx, new MSLambdaDeclarationCallNode(lambdaParams,
                this.map.get(ctx.lambdaBody().expr()), lambdaArgs));
    }

    @Override
    public void exitExprIf(MiniSchemeParser.ExprIfContext ctx) {
        super.exitExprIf(ctx);
        MSSyntaxTree ifCondNode = this.map.get(ctx.ifCond().expr());
        MSSyntaxTree ifBodyNode = this.map.get(ctx.ifBody().expr());
        MSSyntaxTree ifElseNode = this.map.get(ctx.ifElse().expr());
        this.map.put(ctx, new MSIfNode(ifCondNode, ifBodyNode, ifElseNode));
    }

    @Override
    public void exitExprCond(MiniSchemeParser.ExprCondContext ctx) {
        super.exitExprCond(ctx);
        ArrayList<MSSyntaxTree> condCondList = new ArrayList<>();
        ArrayList<MSSyntaxTree> condBodyList = new ArrayList<>();
        for (int i = 0; i < ctx.condCond().size(); i++) {
            condCondList.add(this.map.get(ctx.condCond().get(i).expr()));
            condBodyList.add(this.map.get(ctx.condBody().get(i).expr()));
        }
        condBodyList.add(this.map.get(ctx.condBody().get(ctx.condBody().size() - 1).expr()));
        this.map.put(ctx, new MSCondNode(condCondList, condBodyList));
    }

    @Override
    public void exitExprOp(MiniSchemeParser.ExprOpContext ctx) {
        super.exitExprOp(ctx);
        int[] opType = this.getTokenFromSymbol(ctx);
        MSSyntaxTree expr = new MSOpNode(opType[0], opType[1]);
        for (int i = 0; i < ctx.expr().size(); i++) {
            expr.addChild(this.map.get(ctx.expr(i)));
        }
        this.map.put(ctx, expr);
    }

    @Override
    public void exitExprTerm(MiniSchemeParser.ExprTermContext ctx) {
        super.exitExprTerm(ctx);
        this.map.put(ctx, this.map.get(ctx.term()));
    }

    @Override
    public void exitTerm(MiniSchemeParser.TermContext ctx) {
        super.exitTerm(ctx);
        MSSyntaxTree term = null;
        int tokType = ((TerminalNode) ctx.getChild(0)).getSymbol().getType();
        switch (tokType) {
            case MiniSchemeParser.NUMBERLIT: term = new MSNumberNode(ctx.getText()); break;
            case MiniSchemeParser.BOOLLIT: term = new MSBooleanNode(ctx.getText()); break;
            case MiniSchemeParser.STRINGLIT: term = new MSStringNode(ctx.getText()); break;
            case MiniSchemeParser.ID: term = new MSIdentifierNode(ctx.getText()); break;
            default: throw new UnsupportedOperationException("Cannot support this token yet");
        }

        this.map.put(ctx, term);
    }

    /**
     * Returns the corresponding ANTLR int token from an operator symbol
     * in the ExprOpContext parser rule.
     *
     * @param ctx
     * @return int[] array. arr[0] represents token type from parser. arr[1]
     *                      represents the rule index.
     */
    private int[] getTokenFromSymbol(MiniSchemeParser.ExprOpContext ctx) {
        int[] opTypePair = new int[2];
        int offset = ctx.OPEN_PAREN() != null ? 1 : 0;
        opTypePair[0] = ((TerminalNode) (ctx.getChild(offset).getChild(0))).getSymbol().getType();
        opTypePair[1] = ((RuleContext) ctx.getChild(offset)).getRuleIndex();
        return opTypePair;
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}