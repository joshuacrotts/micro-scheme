package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Optional;

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
    public void exitVariableDeclaration(MiniSchemeParser.VariableDeclarationContext ctx) {
        super.exitVariableDeclaration(ctx);

    }

    @Override
    public void exitProcedureDeclaration(MiniSchemeParser.ProcedureDeclarationContext ctx) {
        super.exitProcedureDeclaration(ctx);
    }

    @Override
    public void exitExpr(MiniSchemeParser.ExprContext ctx) {
        super.exitExpr(ctx);
        this.map.put(ctx, this.map.get(ctx.getChild(0)));
    }

    @Override
    public void exitApplicationExpr(MiniSchemeParser.ApplicationExprContext ctx) {
        super.exitApplicationExpr(ctx);
        MSVariableNode variableNode = (MSVariableNode) this.map.get(ctx.expr());
        ArrayList<MSSyntaxTree> arguments = new ArrayList<>();
        if (ctx.expr() != null) {
            for (ParseTree pt : ctx.applicationArgs().expr()) {
                arguments.add(this.map.get(pt));
            }
        }

        this.map.put(ctx, new MSApplicationNode(variableNode, arguments));
    }

    @Override
    public void exitLambdaExpr(MiniSchemeParser.LambdaExprContext ctx) {
        super.exitLambdaExpr(ctx);
    }

    @Override
    public void exitCondExpr(MiniSchemeParser.CondExprContext ctx) {
        super.exitCondExpr(ctx);
    }

    @Override
    public void exitConstant(MiniSchemeParser.ConstantContext ctx) {
        super.exitConstant(ctx);
        int tokenType = ((TerminalNode) ctx.getChild(0)).getSymbol().getType();
        MSSyntaxTree constantNode;
        switch (tokenType) {
            case MiniSchemeParser.NUMBERLIT:
                constantNode = new MSNumberNode(ctx.getText());
                break;
            case MiniSchemeParser.BOOLLIT:
                constantNode = new MSBooleanNode(ctx.getText());
                break;
            case MiniSchemeParser.CHARLIT:
                constantNode = new MSCharacterNode(ctx.getText());
                break;
            case MiniSchemeParser.STRINGLIT:
                constantNode = new MSStringNode(ctx.getText());
                break;
            default:
                throw new MSInterpreterException("Invalid token type " + tokenType);
        }

        this.map.put(ctx, constantNode);
    }

    @Override
    public void exitVariable(MiniSchemeParser.VariableContext ctx) {
        super.exitVariable(ctx);
        this.map.put(ctx, new MSVariableNode(ctx.ID().getText()));
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}
