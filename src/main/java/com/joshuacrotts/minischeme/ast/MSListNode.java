package com.joshuacrotts.minischeme.ast;

public class MSListNode extends MSSyntaxTree {

    public MSListNode(final MSSyntaxTree car, final MSSyntaxTree cdr) {
        super(MSNodeType.LIST);
        if (car != null) { this.addChild(car); }
        if (cdr != null) { this.addChild(cdr); }
    }

    public MSListNode() {
        this(null, null);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree carCopy = this.getCar();
        MSSyntaxTree cdrCopy = this.getCdr();
        if (carCopy != null) { carCopy = carCopy.copy(); }
        if (cdrCopy != null) { cdrCopy = cdrCopy.copy(); }
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
        return this.getChildrenSize() >= 1 ? this.getChild(0) : null;
    }

    public void setCar(final MSSyntaxTree newCar) {
        if (this.getCar() != null) { this.setChild(0, newCar); }
        this.addChild(newCar);
    }

    public MSSyntaxTree getCdr() {
        return this.getChildrenSize() >= 2 ? this.getChild(1) : null;
    }

    public void setCdr(final MSSyntaxTree newCdr) {
        if (this.getCdr() != null) { this.setChild(0, newCdr); }
        this.addChild(newCdr);
    }

    public boolean isProper() {
        if (this.getCar() != null && this.getChildrenSize() == 1) {
            return true;
        } else if (this.getCar() != null && this.getCdr() != null && this.getCdr().isList()) {
            return ((MSListNode) this.getCdr()).isProper();
        }
        return false;
    }

    /**
     *
     * @return
     */
    private String getListStringRep() {
        if (this.getChildrenSize() == 0) { return "()"; }
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
        if (currList.getChildrenSize() == 0) {
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
