package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.symbol.SymbolTable;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class MSListener extends MiniSchemeBaseListener {

    /**
     *
     */
    private final ParseTreeProperty<MSSyntaxTree> map;

    /**
     *
     */
    private final MSSyntaxTree root;

    /**
     *
     */
    private final MiniSchemeParser parser;

    /**
     *
     */
    public static SymbolTable symbolTable;

    public MSListener(MiniSchemeParser parser) {
        this.parser = parser;
        this.root = new MSSyntaxTree();
        this.map = new ParseTreeProperty<>();
        symbolTable = new SymbolTable();
    }

    @Override
    public void exitMinischeme(MiniSchemeParser.MinischemeContext ctx) {
        super.exitMinischeme(ctx);
        for (int i = 0; i < ctx.children.size(); i++) {
            if (ctx.getChild(i) != null) {
                this.root.addChild(this.map.get(ctx.getChild(i)));
            }
        }
    }

    @Override
    public void exitVardecl(MiniSchemeParser.VardeclContext ctx) {
        super.exitVardecl(ctx);
        // TODO check to see if it's already defined!
        MSSyntaxTree id = this.map.get(ctx.term());
        MSSyntaxTree expr = this.map.get(ctx.expr());
        MSVariableNode var = new MSVariableNode(id, expr);
        symbolTable.addVariable(ctx.term().getText(), var);
        this.map.put(ctx, var);
    }

    @Override
    public void exitProcdecl(MiniSchemeParser.ProcdeclContext ctx) {
        super.exitProcdecl(ctx);
        // TODO check to see if it's already defined.
        MSSyntaxTree id = this.map.get(ctx.term());
        ArrayList<MSSyntaxTree> params = new ArrayList<>();
        for (ParseTree pt : ctx.procparams().expr()) { params.add(this.map.get(pt)); }
        MSSyntaxTree body = this.map.get(ctx.procbody().expr());
        MSSyntaxTree proc = new MSProcedureDefinitionNode(id, params, body);
        symbolTable.addProcedure(ctx.term().getText(), proc);
        this.map.put(ctx, proc);
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

        parentPair = prevPair;
        this.map.put(ctx, parentPair);
    }

    @Override
    public void exitExprProcCall(MiniSchemeParser.ExprProcCallContext ctx) {
        super.exitExprProcCall(ctx);
        // TODO check to see if the procedure is defined and is not a variable.
        MSSyntaxTree id = this.map.get(ctx.term());
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (ParseTree pt : ctx.expr()) { args.add(this.map.get(pt)); }
        this.map.put(ctx, new MSProcedureCallNode(id, args));
    }

    @Override
    public void exitExprIf(MiniSchemeParser.ExprIfContext ctx) {
        super.exitExprIf(ctx);
        MSSyntaxTree ifCondNode = this.map.get(ctx.ifcond().expr());
        MSSyntaxTree ifBodyNode = this.map.get(ctx.ifbody().expr());
        MSSyntaxTree ifElseNode = this.map.get(ctx.ifelse().expr());
        this.map.put(ctx, new MSIfNode(ifCondNode, ifBodyNode, ifElseNode));
    }

    @Override
    public void exitExprCond(MiniSchemeParser.ExprCondContext ctx) {
        super.exitExprCond(ctx);
        ArrayList<MSSyntaxTree> condCondList = new ArrayList<>();
        ArrayList<MSSyntaxTree> condBodyList = new ArrayList<>();
        for (int i = 0; i < ctx.condcond().size(); i++) {
            condCondList.add(this.map.get(ctx.condcond().get(i).expr()));
            condBodyList.add(this.map.get(ctx.condbody().get(i).expr()));
        }
        condBodyList.add(this.map.get(ctx.condbody().get(ctx.condbody().size() - 1).expr()));
        this.map.put(ctx, new MSCondNode(condCondList, condBodyList));
    }

    @Override
    public void exitExprOp(MiniSchemeParser.ExprOpContext ctx) {
        super.exitExprOp(ctx);
        // If there's an opening parenthesis, we need to grab the second
        // token and not the first.
        int idx = ctx.getChild(0).getText().startsWith("(") ? 1 : 0;
        int symbol = ((TerminalNode) ctx.getChild(idx)).getSymbol().getType();
        MSSyntaxTree expr = new MSOpExpression(symbol);
        for (int i = 0; i < ctx.expr().size(); i++) { expr.addChild(this.map.get(ctx.expr(i))); }
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
                term = new MSDoubleLitNode(ctx.getText());
                break;
            case MiniSchemeParser.BOOLLIT:
                term = new MSBooleanLitNode(ctx.getText());
                break;
            case MiniSchemeParser.ID:
                term = new MSIdentifierNode(ctx.getText());
                break;
            default:
                throw new UnsupportedOperationException("Cannot support this token yet");
        }

        this.map.put(ctx, term);
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}
