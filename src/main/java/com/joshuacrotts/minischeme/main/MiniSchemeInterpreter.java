package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSListener;

import java.util.ArrayList;

public class MiniSchemeInterpreter {

    /**
     *
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
        this.tree = tree;
    }

    public void execute() {
        for (MSSyntaxTree ch : this.tree.getChildren()) {
            LValue lhs = this.interpretTree(ch);
            if (lhs.type == LValue.LValueType.LVAL_NUM) { System.out.println(lhs); }
        }
    }

    /**
     *
     * @param tree
     * @return
     */
    protected LValue interpretTree(MSSyntaxTree tree) {
        if (tree == null) { return new LValue(LValue.LValueType.LVAL_NULL); }
        switch (tree.getNodeType()) {
            case MS_ROOT: return this.interpretTree(tree.getChild(0));
            case MS_NUM: return this.interpretNumber(tree);
            case MS_OP: return this.interpretOperator(tree);
            case MS_ID: return this.interpretIdentifier(tree);
            case MS_PROCCALL: return this.interpretProcCall(tree);
        }
        return new LValue();
    }

    /**
     *
     * @param tree
     */
    private LValue interpretNumber(MSSyntaxTree tree) {
        return new LValue(((MSDoubleLitNode) tree).getValue());
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretOperator(MSSyntaxTree tree) {
        int opType = ((MSOpExpression) tree).getOpType();
        LValue res = null;
        // Determine if it's a unary operator or nary.
        if (tree.getChildrenSize() == 1) {
            res = this.interpretPrimitiveUnaryOp(this.interpretTree(tree.getChild(0)), opType);
        } else {
            res = this.interpretTree(tree.getChild(0));
            for (int i = 1; i < tree.getChildrenSize(); i++) {
                res = this.interpretPrimitiveBinaryOp(res, opType, this.interpretTree(tree.getChild(i)));
            }
        }
        return res;
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretIdentifier(MSSyntaxTree tree) {
        String id = ((MSIdentifierNode) tree).getStringRep();
        MSSyntaxTree expr = MSListener.symbolTable.getVariable(id).getExpression().getChild(1);
        return this.interpretTree(expr);
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretProcCall(MSSyntaxTree tree) {
        MSProcedureCallNode procCall = (MSProcedureCallNode) tree;
        String id = procCall.getIdentifier().getStringRep();
        MSProcedureDefinitionNode def = (MSProcedureDefinitionNode) MSListener.symbolTable.getProcedure(id).getProcDef();
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (int i = 0; i < procCall.getArguments().size(); i++) {
            LValue lhs = this.interpretTree(procCall.getArguments().get(i));
            if (lhs.type == LValue.LValueType.LVAL_NUM) {
                args.add(new MSDoubleLitNode(lhs.dval));
            }
        }

        MSSyntaxTree body = def.getBody().copy();
        replaceParams(def, body, args);
        return this.interpretTree(body);
    }

    /**
     *
     * @param lhs
     * @param opType
     * @param rhs
     * @return
     */
    private LValue interpretPrimitiveBinaryOp(LValue lhs, int opType, LValue rhs) {
        switch (opType) {
            case MiniSchemeParser.PLUS: return new LValue(lhs.dval + rhs.dval);
            case MiniSchemeParser.MINUS: return new LValue(lhs.dval - rhs.dval);
            case MiniSchemeParser.STAR: return new LValue(lhs.dval * rhs.dval);
            case MiniSchemeParser.SLASH: return new LValue(lhs.dval / rhs.dval);
            case MiniSchemeParser.MODULO: return new LValue(lhs.dval % rhs.dval);
            case MiniSchemeParser.EXPONENTIATION: return new LValue(Math.pow(lhs.dval, rhs.dval));
        }

        throw new IllegalArgumentException("ERR invalid binop type " + opType);
    }

    /**
     *
     * @param lhs
     * @param opType
     * @return
     */
    private LValue interpretPrimitiveUnaryOp(LValue lhs, int opType) {
        switch (opType) {
            case MiniSchemeParser.SIN: return new LValue(Math.sin(lhs.dval));
            case MiniSchemeParser.COS: return new LValue(Math.cos(lhs.dval));
            case MiniSchemeParser.TAN: return new LValue(Math.tan(lhs.dval));
            case MiniSchemeParser.ASIN: return new LValue(Math.asin(lhs.dval));
            case MiniSchemeParser.ACOS: return new LValue(Math.acos(lhs.dval));
            case MiniSchemeParser.ATAN: return new LValue(Math.atan(lhs.dval));
            case MiniSchemeParser.SQRT: return new LValue(Math.sqrt(lhs.dval));
        }

        throw new IllegalArgumentException("ERR invalid unary type " + opType);
    }

    /**
     *
     * @param procDef
     * @param body
     * @param args
     */
    private static void replaceParams(MSProcedureDefinitionNode procDef,
                                      MSSyntaxTree body, ArrayList<MSSyntaxTree> args) {
        for (int i = 0; i < args.size(); i++) {
            replaceParamsHelper(procDef, body, args.get(i), i);
        }
    }

    /**
     *
     * @param procDef
     * @param body
     * @param arg
     * @param replaceIdx
     */
    private static void replaceParamsHelper(MSProcedureDefinitionNode procDef,
                                            MSSyntaxTree body, MSSyntaxTree arg, int replaceIdx) {
        for (int i = 0; i < body.getChildrenSize(); i++) {
            MSSyntaxTree child = body.getChild(i);
            if (child.getNodeType() == MSNodeType.MS_ID) {
                MSIdentifierNode id = (MSIdentifierNode) child;
                if (procDef.getArgumentLoc(id.getIdentifier()) == replaceIdx) {
                    body.setChild(i, arg);
                }
            } else {
                replaceParamsHelper(procDef, child, arg, replaceIdx);
            }
        }
    }
}