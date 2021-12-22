package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSLambdaDeclarationNode extends MSSyntaxTree {

    public MSLambdaDeclarationNode(ArrayList<MSSyntaxTree> parameters, MSSyntaxTree body) {
        super(MSNodeType.EXPR_LAMBDA_DECL);
        for (MSSyntaxTree parameter : parameters) {
            this.addChild(parameter);
        }
        this.addChild(body);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> paramsCopy = new ArrayList<>();
        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            paramsCopy.add(this.getChild(i).copy());
        }
        MSSyntaxTree bodyCopy = this.getChild(this.getChildrenSize() - 1);
        return new MSLambdaDeclarationNode(paramsCopy, bodyCopy);
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append("(LAMBDA ");
        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            sb.append("(");
            sb.append(this.getChild(i).toString());
            sb.append(" ");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")(");
        sb.append(this.getChild(this.getChildrenSize() - 1).toString());
        sb.append("))");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "LAMBDA_DECL";
    }
}
