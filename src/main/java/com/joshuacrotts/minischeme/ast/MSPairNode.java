package com.joshuacrotts.minischeme.ast;

/**
 * Constructs a "pair". A MiniScheme "pair" consists of a head and a tail,
 * or historically the "car" and the "cdr". There are two representations
 * of pairs: improper (in which the last element of the pair is the empty
 * list '()), and proper (which is not improper).
 *
 * @author Joshua Crotts
 * @version 12/31/2021
 */
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
        if (carCopy != null) { carCopy = carCopy.copy(); }
        if (cdrCopy != null) { cdrCopy = cdrCopy.copy(); }
        return new MSPairNode(this.getNodeType(), carCopy, cdrCopy);
    }

    @Override
    public String getStringRep() {
        return this.isPair()
                ? this.getPairStringRep()
                : this.getListStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getCar() {
        return this.getChildrenSize() < 1 ? null : this.getChild(0);
    }

    public void setCar(MSSyntaxTree newCar) {
        this.setChild(0, newCar);
    }

    public MSSyntaxTree getCdr() {
        return this.getChildrenSize() < 2 ? null : this.getChild(1);
    }

    public void setCdr(MSSyntaxTree newCdr) {
        this.setChild(1, newCdr);
    }

    public boolean isNull() {
        return this.getCar() == null && this.getCdr() == null;
    }

    /**
     * A "pair" is proper if its last element is the empty list (meaning its head and tail are
     * null). If its tail is not a pair then it is by definition improper. We recursively check the
     * tail until we hit the null/empty list or an element as the tail, whichever comes first.
     *
     * A "list" is proper if the last element is the empty list and all previous tails are lists.
     *
     * @return true if the pair is proper, false otherwise.
     */
    public boolean isProper() {
        // We're on the empty list.
        if (this.getCar() == null && this.getCdr() == null) { return true; }
        // We're on the last element of a list and the head is a node but the tail is ().
        else if (this.getCar() != null && this.getCdr() == null && this.getNodeType() == MSNodeType.LIST) { return true; }
        // Check to make sure the tail is not either a pair or a list.
        else if (!this.getCdr().isPair() && !this.getCdr().isList()) { return false; }
        // Recurse.
        else { return ((MSPairNode) this.getCdr()).isProper(); }
    }

    /**
     * @return
     */
    private String getPairStringRep() {
        if (this.isNull()) {
            return "()";
        }
        // If the pair is proper, then we don't print dot notation.
        if (this.isProper()) {
            StringBuilder sb = new StringBuilder("(");
            this.getProperStringRep(this, sb);
            sb.append(")");
            return sb.toString();
        } else {
            // If the tail is null then we print the head with parentheses surrounding.
            if (this.getCdr() == null) {
                return "(" + this.getCar().getStringRep() + ")";
            } else {
                return "(" + this.getCar().getStringRep()
                        + (this.isProper() ? " " : " . ")
                        + this.getCdr().getStringRep()
                        + ")";
            }
        }
    }

    /**
     * @return
     */
    private String getListStringRep() {
        return this.getPairStringRep();
    }

    /**
     * @param curr
     * @param sb
     */
    private void getProperStringRep(MSSyntaxTree curr, StringBuilder sb) {
        MSPairNode currPair = (MSPairNode) curr;
        if (currPair.isNull()) {
            // Trim the last space.
            sb.setLength(sb.length() - 1);
        } else {
            // Append the head then check the cdr.
            sb.append(currPair.getCar().getStringRep());
            if (currPair.getCdr() != null) {
                sb.append(" ");
                getProperStringRep(currPair.getCdr(), sb);
            }
        }
    }
}
