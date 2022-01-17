package com.joshuacrotts.minischeme.ast;

/**
 * Constructs a "list". A MiniScheme "list" consists of a head and a tail,
 * or historically the "car" and the "cdr". There are two representations
 * of lists: improper (in which the last element of the list is the empty
 * list '()), and proper (which is not improper).
 *
 * @author Joshua Crotts
 * @version 12/31/2021
 */
public class MSListNode extends MSSyntaxTree {

    public MSListNode(final MSSyntaxTree car, final MSSyntaxTree cdr) {
        super(MSNodeType.LIST);
        if (car != null) {
            this.addChild(car);
        }
        if (cdr != null) {
            this.addChild(cdr);
        }
    }

    public MSListNode() {
        super(MSNodeType.LIST);
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
        return new MSListNode(carCopy, cdrCopy);
    }

    @Override
    public String getStringRep() {
        return this.getListStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getCar() {
        return this.getChildrenSize() < 1 ? null : this.getChild(0);
    }

    public void setCar(final MSSyntaxTree newCar) {
        if (this.getCar() != null) {
            this.setChild(0, newCar);
        } else {
            this.addChild(newCar);
        }
    }

    public MSSyntaxTree getCdr() {
        return this.getChildrenSize() < 2 ? null : this.getChild(1);
    }

    public void setCdr(final MSSyntaxTree newCdr) {
        if (this.getCdr() != null) {
            this.setChild(1, newCdr);
        } else {
            this.addChild(newCdr);
        }
    }

    public boolean isNull() {
        return this.getCar() == null && this.getCdr() == null;
    }

    /**
     * A list is proper if its last element is the empty list (meaning its head and tail are
     * null). If its tail is not a list then it is by definition improper. We recursively check the
     * tail until we hit the null/empty list or an element as the tail, whichever comes first.
     * <p>
     * A "list" is proper if the last element is the empty list and all previous tails are lists.
     *
     * @return true if the list is proper, false otherwise.
     */
    public boolean isProper() {
        // We're on the empty list.
        if (this.getCar() == null && this.getCdr() == null) {
            return true;
        }
        // We're on the last element of a list and the head is a node but the tail is ().
        else if (this.getCar() != null && this.getCdr() == null) {
            return true;
        }
        // Check to make sure the tail is not either a pair or a list.
        else if (!this.getCdr().isList()) {
            return false;
        }
        // Recurse.
        else {
            return ((MSListNode) this.getCdr()).isProper();
        }
    }

    /**
     * @return
     */
    private String getListStringRep() {
        if (this.isNull()) {
            return "()";
        }
        // If the list is proper, then we don't print dot notation.
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
     * @param curr
     * @param sb
     */
    private void getProperStringRep(final MSSyntaxTree curr, final StringBuilder sb) {
        MSListNode currList = (MSListNode) curr;
        if (currList.isNull()) {
            // Trim the last space.
            sb.setLength(sb.length() - 1);
        } else {
            // Append the head then check the cdr.
            sb.append(currList.getCar().getStringRep());
            if (currList.getCdr() != null) {
                sb.append(" ");
                getProperStringRep(currList.getCdr(), sb);
            }
        }
    }
}
