package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.main.Symbol;
import com.joshuacrotts.minischeme.main.SymbolTable;
import com.joshuacrotts.minischeme.main.Variable;
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
        this.symbolTable = new SymbolTable();
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
    public void exitExprProcCall(MiniSchemeParser.ExprProcCallContext ctx) {
        super.exitExprProcCall(ctx);
        // TODO check to see if the procedure is defined and is not a variable.
        MSSyntaxTree id = this.map.get(ctx.term());
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (ParseTree pt : ctx.expr()) { args.add(this.map.get(pt)); }
        this.map.put(ctx, new MSProcedureCallNode(id, args));
    }

    @Override
    public void exitExprOp(MiniSchemeParser.ExprOpContext ctx) {
        super.exitExprOp(ctx);
        int symbol = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
        MSOpExpression expr = new MSOpExpression(symbol);
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
                term = new MSDoubleLitNode(ctx.getText());
                break;
            case MiniSchemeParser.ID:
                term = new MSIdentifierNode(ctx.getText());
                break;
        }

        this.map.put(ctx, term);
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}
