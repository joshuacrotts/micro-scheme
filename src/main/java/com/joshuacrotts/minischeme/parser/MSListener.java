package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import java.util.ArrayList;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MSListener extends MiniSchemeBaseListener {

    /**
     *
     */
    private final MiniSchemeParser parser;

    /**
     * ParseTreeProperty map of parser rules being constructed overtime.
     */
    private final ParseTreeProperty<MSSyntaxTree> map;

    /**
     * Root of the AST being constructed.
     */
    private final MSSyntaxTree root;

    public MSListener(MiniSchemeParser parser) {
        this.parser = parser;
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
        MSSyntaxTree identifier = this.map.get(ctx.term());
        MSSyntaxTree expr = this.map.get(ctx.expr());
        this.map.put(ctx, new MSVariableDeclarationNode(identifier, expr));
    }

    @Override
    public void exitVarDeclRead(MiniSchemeParser.VarDeclReadContext ctx) {
        super.exitVarDeclRead(ctx);
        MSSyntaxTree identifier = this.map.get(ctx.term());
        int readOpType = ((TerminalNode) ctx.readop().getChild(0)).getSymbol().getType();
        this.map.put(ctx, new MSDeclarationReadNode(readOpType, identifier));
    }

    @Override
    public void exitProcDecl(MiniSchemeParser.ProcDeclContext ctx) {
        super.exitProcDecl(ctx);
        MSSyntaxTree id = this.map.get(ctx.term());
        ArrayList<MSSyntaxTree> params = new ArrayList<>();
        // If we have parameters, get them now.
        if (ctx.procParams() != null) {
            for (ParseTree pt : ctx.procParams().expr()) {
                params.add(this.map.get(pt));
            }
        }
        MSSyntaxTree body = this.map.get(ctx.procBody().expr());
        this.map.put(ctx, new MSProcedureDeclarationNode(id, params, body));
    }

    @Override
    public void exitLambdaDecl(MiniSchemeParser.LambdaDeclContext ctx) {
        super.exitLambdaDecl(ctx);
        MSSyntaxTree id = this.map.get(ctx.term());
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        if (ctx.lambdaParams() != null) {
            for (ParseTree pt : ctx.lambdaParams().expr()) {
                lambdaParams.add(this.map.get(pt));
            }
        }
        MSSyntaxTree lambdaBody = this.map.get(ctx.lambdaBody().expr());
        this.map.put(ctx, new MSLambdaDeclarationNode(id, lambdaParams, lambdaBody));
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
        if (ctx.term() != null && ctx.term().ID() != null) {
            this.map.put(ctx, new MSSymbolLiteralNode(ctx.getChild(0).getChild(0).getText()));
        } else if (ctx.term() != null) {
            this.map.put(ctx, this.map.get(ctx.term()));
        } else if (ctx.exprCall() != null) {
            this.map.put(ctx, this.map.get(ctx.exprCall()));
        } else if (ctx.op() != null) {
            this.map.put(ctx, new MSSymbolLiteralNode(ctx.getChild(0).getChild(0).getText()));
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
            parentPair = prevPair != null ? prevPair : new MSPairNode();
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
        parentPair = prevPair != null ? prevPair : new MSPairNode();
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
        MSSyntaxTree identifierNode = this.map.get(ctx.term());
        MSSyntaxTree exprNode = this.map.get(ctx.expr());
        int setOpType = ((TerminalNode) ctx.setop().getChild(0)).getSymbol().getType();
        this.map.put(ctx, new MSSetNode(setOpType, identifierNode, exprNode));
    }

    @Override
    public void exitExprSetRead(MiniSchemeParser.ExprSetReadContext ctx) {
        super.exitExprSetRead(ctx);
        MSSyntaxTree identifier = this.map.get(ctx.term());
        int readOpType = ((TerminalNode) ctx.readop().getChild(0)).getSymbol().getType();
        this.map.put(ctx, new MSSetReadNode(readOpType, identifier));
    }

    @Override
    public void exitExprCall(MiniSchemeParser.ExprCallContext ctx) {
        super.exitExprCall(ctx);
        MSSyntaxTree id = this.map.get(ctx.term());
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

        this.map.put(ctx, new MSCallNode(id, procArgs, lambdaArgs));
    }

    @Override
    public void exitExprLetDecl(MiniSchemeParser.ExprLetDeclContext ctx) {
        super.exitExprLetDecl(ctx);
        // Child 1 is which type of let we're using.
        int letType = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
        ArrayList<MSSyntaxTree> declarations = new ArrayList<>();
        if (ctx.letDecl() != null) {
            // We can't use an enhanced for loop since we're traversing over two distinct rules.
            for (int i = 0; i < ctx.letDecl().expr().size(); i++) {
                MSSyntaxTree term = this.map.get(ctx.letDecl().term(i));
                MSSyntaxTree expr = this.map.get(ctx.letDecl().expr(i));
                declarations.add(new MSVariableDeclarationNode(term, expr));
            }
        }

        MSSyntaxTree letBody = this.map.get(ctx.expr());
        this.map.put(ctx, new MSLetDeclarationNode(letType, declarations, letBody));
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

        MSSyntaxTree lambdaBody = this.map.get(ctx.lambdaBody().expr());
        this.map.put(ctx, new MSLambdaDeclarationNode(lambdaParams, lambdaBody));
    }

    @Override
    public void exitExprLambdaDeclCall(MiniSchemeParser.ExprLambdaDeclCallContext ctx) {
        super.exitExprLambdaDeclCall(ctx);
        MSSyntaxTree lambdaBody = this.map.get(ctx.lambdaBody().expr());

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

        this.map.put(ctx, new MSLambdaDeclarationCallNode(lambdaParams, lambdaBody, lambdaArgs));
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
        int[] opType = getTokenFromSymbol(ctx);
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
            case MiniSchemeParser.NUMBERLIT:
                term = new MSNumberNode(ctx.getText());
                break;
            case MiniSchemeParser.BOOLLIT:
                term = new MSBooleanNode(ctx.getText());
                break;
            case MiniSchemeParser.STRINGLIT:
                term = new MSStringNode(ctx.getText());
                break;
            case MiniSchemeParser.ID:
                term = new MSIdentifierNode(ctx.getText());
                break;
            default:
                throw new UnsupportedOperationException("Cannot support this token yet");
        }

        this.map.put(ctx, term);
    }

    /**
     * Returns the corresponding ANTLR int token from an operator symbol
     * in the ExprOpContext parser rule.
     *
     * @param ctx
     * @return
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