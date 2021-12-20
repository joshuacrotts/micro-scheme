package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSBooleanNode;
import com.joshuacrotts.minischeme.ast.MSCondNode;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSIdentifierNode;
import com.joshuacrotts.minischeme.ast.MSIfNode;
import com.joshuacrotts.minischeme.ast.MSNodeType;
import com.joshuacrotts.minischeme.ast.MSOpExpression;
import com.joshuacrotts.minischeme.ast.MSPairNode;
import com.joshuacrotts.minischeme.ast.MSProcedureCallNode;
import com.joshuacrotts.minischeme.ast.MSProcedureDefinitionNode;
import com.joshuacrotts.minischeme.ast.MSStringNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableNode;
import com.joshuacrotts.minischeme.symbol.SymbolTable;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MSListener extends MiniSchemeBaseListener {

    /**
     *
     */
    public static SymbolTable symbolTable;
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
        for (ParseTree pt : ctx.procparams().expr()) {
            params.add(this.map.get(pt));
        }
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

        // If they enter the empty list, then we need to add a "blank" pair node.
        parentPair = prevPair != null ? prevPair : new MSPairNode();
        this.map.put(ctx, parentPair);
    }

    @Override
    public void exitExprProcCall(MiniSchemeParser.ExprProcCallContext ctx) {
        super.exitExprProcCall(ctx);
        // TODO check to see if the procedure is defined and is not a variable.
        MSSyntaxTree id = this.map.get(ctx.term());
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (ParseTree pt : ctx.expr()) {
            args.add(this.map.get(pt));
        }
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
        int symbol = getTokenFromSymbol(ctx);
        MSSyntaxTree expr = new MSOpExpression(symbol);
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
     * @param ctx
     * @return
     */
    private static int getTokenFromSymbol(MiniSchemeParser.ExprOpContext ctx) {
        if (ctx.unaryop() != null) {
            return ((TerminalNode) ctx.unaryop().getChild(0)).getSymbol().getType();
        } else if (ctx.naryop() != null) {
            return ((TerminalNode) ctx.naryop().getChild(0)).getSymbol().getType();
        }

        throw new IllegalArgumentException("Internal interpreter error: could not find a unary or "
                                               + "nary op from ExprOpContext. This should never happen...");
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}
