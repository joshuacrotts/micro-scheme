package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSDoubleLitNode;
import com.joshuacrotts.minischeme.ast.MSOpExpression;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

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

    public MSListener(MiniSchemeParser parser) {
        this.parser = parser;
        this.root = new MSSyntaxTree();
        this.map = new ParseTreeProperty<>();
    }

    @Override
    public void exitMinischeme(MiniSchemeParser.MinischemeContext ctx) {
        super.enterMinischeme(ctx);
        this.root.addChild(this.map.get(ctx.expr(0)));
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

    public void exitExprTerm(MiniSchemeParser.ExprTermContext ctx) {
        super.exitExprTerm(ctx);
        this.map.put(ctx, this.map.get(ctx.term()));
    }

    @Override
    public void exitTerm(MiniSchemeParser.TermContext ctx) {
        super.exitTerm(ctx);
        MSSyntaxTree literal = new MSDoubleLitNode(ctx.getText());
        this.map.put(ctx, literal);
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}
