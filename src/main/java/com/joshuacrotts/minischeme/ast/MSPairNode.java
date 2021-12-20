package com.joshuacrotts.minischeme.ast;

public class MSPairNode extends MSSyntaxTree {

    public MSPairNode(MSNodeType type, MSSyntaxTree car, MSSyntaxTree cdr) {
        super(type, car, cdr);
    }

    public MSPairNode() {
        super(MSNodeType.PAIR);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree carCopy = this.getCar();
        MSSyntaxTree cdrCopy = this.getCdr();
        if (carCopy != null) {
            carCopy = carCopy.copy();
        }
        if (cdrCopy != null) {
            cdrCopy = cdrCopy.copy();
        }
        return new MSPairNode(this.getNodeType(), carCopy, cdrCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType() == MSNodeType.PAIR
            ? this.getPairStringRep()
            : this.getListStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType() == MSNodeType.LIST
            ? this.getPairToString()
            : this.getListToString();
    }

    public MSSyntaxTree getCar() {
        return this.getChildrenSize() < 1 ? null : this.getChild(0);
    }

    public MSSyntaxTree getCdr() {
        return this.getChildrenSize() < 2 ? null : this.getChild(1);
    }

    public boolean isNull() {
        return this.getCar() == null && this.getCdr() == null;
    }

    /**
     * @return
     */
    private String getPairStringRep() {
        if (this.isNull()) {
            return "()";
        } else if (this.getCdr() == null) {
            StringBuilder sb = new StringBuilder("(");
            sb.append(this.getCar().getStringRep());
            sb.append(")");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder("(");
            sb.append(this.getCar().getStringRep());
            sb.append(" . ");
            sb.append(this.getCdr().getStringRep());
            sb.append(")");
            return sb.toString();
        }
    }

    /**
     * @return
     */
    private String getPairToString() {
        if (this.isNull()) {
            return "PAIR ()";
        } else if (this.getCdr() == null) {
            StringBuilder sb = new StringBuilder("PAIR (");
            sb.append(this.getCar().toString());
            sb.append(")");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder("PAIR (");
            sb.append(this.getCar().toString());
            sb.append(" . ");
            sb.append(this.getCdr().toString());
            sb.append(")");
            return sb.toString();
        }
    }

    /**
     * @return
     */
    private String getListStringRep() {
        if (this.isNull()) {
            return "()";
        } else if (this.getCdr() == null) {
            StringBuilder sb = new StringBuilder("(");
            sb.append(this.getCar().getStringRep());
            sb.append(")");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder("(");
            sb.append(this.getCar().getStringRep());
            sb.append(" ");
            sb.append(this.getCdr().getStringRep());
            sb.append(")");
            return sb.toString();
        }
    }

    /**
     * @return
     */
    private String getListToString() {
        if (this.isNull()) {
            return "LIST ()";
        } else if (this.getCdr() == null) {
            StringBuilder sb = new StringBuilder("LIST (");
            sb.append(this.getCar().toString());
            sb.append(")");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder("LIST (");
            sb.append(this.getCar().toString());
            sb.append(" ");
            sb.append(this.getCdr().toString());
            sb.append(")");
            return sb.toString();
        }
    }
}
