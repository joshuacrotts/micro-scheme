package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.MiniSchemeParser.SymbolDatumContext;
import com.joshuacrotts.minischeme.MiniSchemeParser.SymbolExprContext;
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
    public void exitExpr(MiniSchemeParser.ExprContext ctx) {
        super.exitExpr(ctx);
        this.map.put(ctx, this.map.get(ctx.getChild(0)));
    }


    @Override
    public void exitDecl(MiniSchemeParser.DeclContext ctx) {
        super.exitDecl(ctx);
        this.map.put(ctx, this.map.get(ctx.getChild(0)));
    }

    @Override
    public void exitVariableDeclaration(MiniSchemeParser.VariableDeclarationContext ctx) {
        super.exitVariableDeclaration(ctx);
        this.map.put(ctx, new MSDeclaration(this.map.get(ctx.variable()), this.map.get(ctx.expr())));
    }

    @Override
    public void exitProcedureDeclaration(MiniSchemeParser.ProcedureDeclarationContext ctx) {
        super.exitProcedureDeclaration(ctx);
        MSSyntaxTree procedureName = this.map.get(ctx.variable());
        ArrayList<MSSyntaxTree> procedureParameters = new ArrayList<>();
        if (ctx.procedureParameters().expr() != null) {
            for (ParseTree pt : ctx.procedureParameters().expr()) {
                procedureParameters.add(this.map.get(pt));
            }
        }

        MSSyntaxTree procedureBody = this.map.get(ctx.expr());
        MSLambdaNode procedureLambda = new MSLambdaNode(procedureParameters, procedureBody);
        this.map.put(ctx, new MSDeclaration(procedureName, procedureLambda));
    }

    @Override
    public void exitApplicationExpr(MiniSchemeParser.ApplicationExprContext ctx) {
        super.exitApplicationExpr(ctx);
        MSSyntaxTree lhsExpression = this.map.get(ctx.expr());
        ArrayList<MSSyntaxTree> arguments = new ArrayList<>();
        if (ctx.expr() != null) {
            for (ParseTree pt : ctx.applicationArgs().expr()) {
                arguments.add(this.map.get(pt));
            }
        }

        this.map.put(ctx, new MSApplicationNode(lhsExpression, arguments));
    }

    @Override
    public void exitLetExpr(MiniSchemeParser.LetExprContext ctx) {
        super.exitLetExpr(ctx);
        // Convert the let into a lambda as an application.
        ArrayList<MSSyntaxTree> letVariables = new ArrayList<>();
        ArrayList<MSSyntaxTree> letBindings = new ArrayList<>();
        for (int i = 0; i < ctx.letParameters().size(); i++) {
            letVariables.add(this.map.get(ctx.letParameters().get(i).expr(0)));
            letBindings.add(this.map.get(ctx.letParameters().get(i).expr(1)));
        }
        MSSyntaxTree letBody = this.map.get(ctx.expr());
        MSLambdaNode lambdaNode = new MSLambdaNode(letVariables, letBody);
        this.map.put(ctx, new MSApplicationNode(lambdaNode, letBindings));
    }

    @Override
    public void exitLambdaExpr(MiniSchemeParser.LambdaExprContext ctx) {
        super.exitLambdaExpr(ctx);
        ArrayList<MSSyntaxTree> lambdaParameters = new ArrayList<>();
        if (ctx.lambdaParameters().expr() != null) {
            for (ParseTree pt : ctx.lambdaParameters().expr()) {
                lambdaParameters.add(this.map.get(pt));
            }
        }
        MSSyntaxTree lambdaBody = this.map.get(ctx.expr());
        this.map.put(ctx, new MSLambdaNode(lambdaParameters, lambdaBody));
    }

    @Override
    public void exitIfExpr(MiniSchemeParser.IfExprContext ctx) {
        super.exitIfExpr(ctx);
        ArrayList<MSSyntaxTree> condPredicateList = new ArrayList<>();
        ArrayList<MSSyntaxTree> condConsequentList = new ArrayList<>();

        // Add the if statement.
        condPredicateList.add(this.map.get(ctx.expr(0)));

        // Add the if consequent.
        condConsequentList.add(this.map.get(ctx.expr(1)));

        // If there's an alternative add that.
        if (ctx.expr(2) != null) { condConsequentList.add(this.map.get(ctx.expr(2))); }
        this.map.put(ctx, new MSCondNode(condPredicateList, condConsequentList));
    }

    @Override
    public void exitCondExpr(MiniSchemeParser.CondExprContext ctx) {
        super.exitCondExpr(ctx);
        ArrayList<MSSyntaxTree> condPredicateList = new ArrayList<>();
        ArrayList<MSSyntaxTree> condConsequentList = new ArrayList<>();
        for (int i = 0; i < ctx.condForm().size(); i++) {
            condPredicateList.add(this.map.get(ctx.condForm().get(i).expr(0)));
            condConsequentList.add(this.map.get(ctx.condForm().get(i).expr(1)));
        }

        // If the expr is non-null, there's an else statement.
        if (ctx.expr() != null) { condConsequentList.add(this.map.get(ctx.expr())); }
        this.map.put(ctx, new MSCondNode(condPredicateList, condConsequentList));
    }

    @Override
    public void exitSymbolExpr(MiniSchemeParser.SymbolExprContext ctx) {
        super.exitSymbolExpr(ctx);
        // If it's just one symbol datum, then just return that.
        if (ctx.symbolDatum(0) != null) {
            this.map.put(ctx, new MSSymbolNode(this.map.get(ctx.symbolDatum(0))));
        } else {
            // Otherwise, construct a list of SymbolDatums.
            MSSyntaxTree parentList = null;
            MSSyntaxTree currList = null;
            for (int i = ctx.symbolDatum().size() - 1; i >= 0; i--) {
                MSSyntaxTree rhsList = this.map.get(ctx.symbolDatum(i));
                currList = new MSListNode(rhsList, currList);
            }

            parentList = Optional.ofNullable(currList).orElse(MSListNode.EMPTY_LIST);
            this.map.put(ctx, new MSSymbolNode(parentList));
        }
    }

    @Override
    public void exitSymbolDatum(MiniSchemeParser.SymbolDatumContext ctx) {
        super.exitSymbolDatum(ctx);
        // First, check to see if it's a list of expressions. If so, make it a MSListNode.
        if (ctx.expr() != null) {
            MSSyntaxTree parentList = null;
            MSSyntaxTree currList = null;
            for (int i = ctx.expr().size() - 1; i >= 0; i--) {
                MSSyntaxTree rhsList = this.map.get(ctx.expr(i));
                currList = new MSListNode(rhsList, currList);
            }

            parentList = Optional.ofNullable(currList).orElse(MSListNode.EMPTY_LIST);
            this.map.put(ctx, parentList);
        } else {
            // Otherwise, just take the child that's there (either a variable or constant).
            this.map.put(ctx, this.map.get(ctx.getChild(0)));
        }
    }

//    @Override
//    public void exitSetExpr(MiniSchemeParser.SetExprContext ctx) {
//        super.exitSetExpr(ctx);
//        MSSyntaxTree lhs = this.map.get(ctx.expr(0));
//        MSSyntaxTree rhs = this.map.get(ctx.expr(1));
//        this.map.put(ctx, new MSSetNode(lhs, rhs));
//    }

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
