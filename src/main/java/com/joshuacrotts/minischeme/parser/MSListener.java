/******************************************************************************
 *  File: MSListener.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.parser;

import com.joshuacrotts.minischeme.MiniSchemeBaseListener;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Optional;

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
    public void exitMiniScheme(final MiniSchemeParser.MiniSchemeContext ctx) {
        super.exitMiniScheme(ctx);
        for (int i = 0; i < ctx.children.size(); i++) {
            if (ctx.getChild(i) != null) {
                this.root.addChild(this.map.get(ctx.getChild(i)));
            }
        }
    }

    @Override
    public void exitDecl(final MiniSchemeParser.DeclContext ctx) {
        super.exitDecl(ctx);
        this.map.put(ctx, this.map.get(ctx.getChild(0)));
    }

    @Override
    public void exitExpr(final MiniSchemeParser.ExprContext ctx) {
        super.exitExpr(ctx);
        this.map.put(ctx, this.map.get(ctx.getChild(0)));
    }

    @Override
    public void exitBeginExpr(final MiniSchemeParser.BeginExprContext ctx) {
        super.exitBeginExpr(ctx);
        ArrayList<MSSyntaxTree> expressions = new ArrayList<>();
        for (ParseTree pt : ctx.expr()) {
            expressions.add(this.map.get(pt));
        }
        this.map.put(ctx, new MSSequenceNode(expressions));
    }

    @Override
    public void exitVariableDeclaration(final MiniSchemeParser.VariableDeclarationContext ctx) {
        super.exitVariableDeclaration(ctx);
        this.map.put(ctx, new MSDeclarationNode(this.map.get(ctx.variable()), this.map.get(ctx.expr())));
    }

    @Override
    public void exitProcedureDeclaration(final MiniSchemeParser.ProcedureDeclarationContext ctx) {
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
        this.map.put(ctx, new MSDeclarationNode(procedureName, procedureLambda));
    }

    @Override
    public void exitApplicationExpr(final MiniSchemeParser.ApplicationExprContext ctx) {
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
    public void exitDoExpr(final MiniSchemeParser.DoExprContext ctx) {
        super.exitDoExpr(ctx);
        // First, collect the variable declarations and set expressions.
        ArrayList<MSSyntaxTree> doDeclarations = new ArrayList<>();
        ArrayList<MSSyntaxTree> doSetExpressions = new ArrayList<>();
        for (int i = 0; i < ctx.doDecl().size(); i++) {
            MSSyntaxTree varNode = this.map.get(ctx.doDecl().get(i).variable());
            MSSyntaxTree expressionNode = this.map.get(ctx.doDecl().get(i).expr(0));
            doDeclarations.add(new MSDeclarationNode(varNode, expressionNode));
            // Check to see if there's a second expression, indicating we need a SET.
            if (ctx.doDecl().get(i).expr(1) != null) {
                MSSyntaxTree setExpression = this.map.get(ctx.doDecl().get(i).expr(1));
                ArrayList<MSSyntaxTree> setData = new ArrayList<>();
                setData.add(varNode);
                setData.add(setExpression);
                doSetExpressions.add(new MSSetNode(MiniSchemeParser.SET, setData));
            }
        }

        // Now, retrieve the test condition and what to do when it is true.
        MSSyntaxTree doTestExpression = this.map.get(ctx.doTest().expr());
        ArrayList<MSSyntaxTree> doTrueExpressions = new ArrayList<>();
        for (int i = 0; i < ctx.doTrueExpr().size(); i++) {
            doTrueExpressions.add(this.map.get(ctx.doTrueExpr().get(i).expr()));
        }

        // Finally, retrieve the body.
        ArrayList<MSSyntaxTree> doBodyList = new ArrayList<>();
        for (int i = 0; i < ctx.doBody().expr().size(); i++) {
            doBodyList.add(this.map.get(ctx.doBody().expr().get(i)));
        }

        MSSequenceNode doBody = new MSSequenceNode(doBodyList);
        this.map.put(ctx, new MSDoNode(doDeclarations, doSetExpressions, doTestExpression, doTrueExpressions, doBody));
    }

    @Override
    public void exitLetExpr(final MiniSchemeParser.LetExprContext ctx) {
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
    public void exitLetStarExpr(final MiniSchemeParser.LetStarExprContext ctx) {
        super.exitLetStarExpr(ctx);
        MSApplicationNode rootApplication = null;
        for (int i = ctx.letParameters().size() - 1; i >= 0; i--) {
            // Retrieve the variable and its data. Create a lambda node from
            // them and attach it as the body of the next outer lambda.
            MSSyntaxTree variable = this.map.get(ctx.letParameters().get(i).expr(0));
            MSSyntaxTree expression = this.map.get(ctx.letParameters().get(i).expr(1));
            ArrayList<MSSyntaxTree> letArgument = new ArrayList<>();
            ArrayList<MSSyntaxTree> letParameter = new ArrayList<>();
            letParameter.add(variable);
            letArgument.add(expression);
            // If we're on the first expression, we need to create the lambda with the body as the expr.
            MSLambdaNode lambdaNode;
            if (i == ctx.letParameters().size() - 1) { lambdaNode = new MSLambdaNode(letParameter, this.map.get(ctx.expr())); }
            else { lambdaNode = new MSLambdaNode(letParameter, rootApplication); }
            rootApplication = new MSApplicationNode(lambdaNode, letArgument);
        }

        this.map.put(ctx, rootApplication);
    }

    @Override
    public void exitLambdaExpr(final MiniSchemeParser.LambdaExprContext ctx) {
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
    public void exitIfExpr(final MiniSchemeParser.IfExprContext ctx) {
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
    public void exitCondExpr(final MiniSchemeParser.CondExprContext ctx) {
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
    public void exitSymbolExpr(final MiniSchemeParser.SymbolExprContext ctx) {
        super.exitSymbolExpr(ctx);
        // If it's just one symbol datum, then just return that.
        this.map.put(ctx, new MSSymbolNode(this.map.get(ctx.symbolDatum())));
    }

    @Override
    public void exitSymbolDatum(final MiniSchemeParser.SymbolDatumContext ctx) {
        super.exitSymbolDatum(ctx);
        // First, check to see if it's a list of expressions. If so, make it a MSListNode.
        if (ctx.variable() == null && ctx.constant() == null) {
            MSSyntaxTree parentList;
            MSSyntaxTree currList = null;
            for (int i = ctx.symbolDatum().size() - 1; i >= 0; i--) {
                MSSyntaxTree rhsList = this.map.get(ctx.symbolDatum(i));
                currList = new MSListNode(rhsList, currList);
            }

            parentList = Optional.ofNullable(currList).orElse(MSListNode.EMPTY_LIST);
            this.map.put(ctx, parentList);
        } else {
            // Otherwise, just take the child that's there (either a variable or constant).
            this.map.put(ctx, this.map.get(ctx.getChild(0)));
        }
    }

    @Override
    public void exitSetExpr(final MiniSchemeParser.SetExprContext ctx) {
        super.exitSetExpr(ctx);
        ArrayList<MSSyntaxTree> setData = new ArrayList<>();
        setData.add(this.map.get(ctx.variable()));
        setData.add(this.map.get(ctx.expr()));
        this.map.put(ctx, new MSSetNode(MiniSchemeParser.SET, setData));
    }

    @Override
    public void exitSetListExpr(final MiniSchemeParser.SetListExprContext ctx) {
        super.exitSetListExpr(ctx);
        ArrayList<MSSyntaxTree> setData = new ArrayList<>();
        for (ParseTree pt : ctx.expr()) { setData.add(this.map.get(pt)); }
        this.map.put(ctx, new MSSetNode(((TerminalNode) ctx.getChild(1)).getSymbol().getType(), setData));
    }

    @Override
    public void exitConstant(final MiniSchemeParser.ConstantContext ctx) {
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
    public void exitVariable(final MiniSchemeParser.VariableContext ctx) {
        super.exitVariable(ctx);
        this.map.put(ctx, new MSVariableNode(ctx.ID().getText()));
    }

    public MSSyntaxTree getSyntaxTree() {
        return this.root;
    }
}
