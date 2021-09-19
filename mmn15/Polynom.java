/**
 * This class represent a Polynom object.
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class Polynom {

    private PolyNode _head;

    /** 
     * Creates a Polynom Object<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     */	
    public Polynom () {
        _head = null;
    }

    /**
     * Creates a Polynom Object and sets the given PolyNode as it's head<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param p the PolyNode to set as the Polynom's head
     */
    public Polynom (PolyNode p) {
        _head = p;
    }

    /**
     * gets the head of the Polynom<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @return the head of the Polynom
     */
    public PolyNode getHead() {
        return _head;

    }

    /**
     * sets the head of the Polynom<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values 
     * @param p the head to set
     */
    public void setHead( PolyNode p ) {
        _head = p;
    }

    /** Adds a new PolyNode to the Polynom<br>
     * Time complexity - O(n) - worst case we go through the entire length of the Polynom<br>
     * Space complexity - O(1)
     * @param p The new polyNode to be add
     * @return The Polynom with the added PolyNode
     */
    public Polynom addNode (PolyNode p) {
        PolyNode newNode = new PolyNode(p);
        PolyNode current = _head;//pointer to move with on this polynom
        PolyNode prevPos = null;//will hold the previous position of current

        //check for the right place to put the given node
        while (current != null && newNode.getPower() < current.getPower()) {
            prevPos = current;
            current = current.getNext();
        }
        //check if exit the while because we found the correct place
        if(current != null && newNode.getPower() == current.getPower()) {
            current.setCoefficient(current.getCoefficient()+newNode.getCoefficient());
            return this;
        }

        newNode.setNext(current);//connecting the given polynode before current
        if (prevPos == null)//means we have not moved at all
            _head = newNode;
        else
            prevPos.setNext(newNode);//connecting the given polynode after current
        return this;
    }

    /** 
     * add this Polynom with a given Polynom<br>
     * Time complexity - O(n) - worst case we go through the entire length of the Polynom<br>
     * Space complexity - O(n)
     * @param p The Polynom to add this with.
     * @return The Polynom after adding the given PolyNode
     */
    public Polynom addPol(Polynom p) {
        if(p == null || p._head == null)
            return this;
        if(_head == null || this == null)
            return p;
        PolyNode  s  = new PolyNode(p._head);
        while(s!= null) {
            //adding the given node to this polynom
            this.addNode(new PolyNode(s));
            s = s.getNext();
        }

        return this;
    }

    /** 
     * Multiply this Polynom with a given Scalar<br>
     * Time complexity - O(n) - worst case we go through the entire length of the Polynom<br>
     * Space complexity - O(1)
     * @param num The Scalar to multiple this with.
     * @return The Polynom after multiplying the given Scalar
     */
    public Polynom multByScalar (int num) {
        if(num == 0)
            _head = null;
        if(num == 1)
            return this;
        PolyNode current = _head;
        while(current != null) {
            //calculating the multiplication
            current.setCoefficient(current.getCoefficient()*num); 
            current = current.getNext();
        }

        return this;
    }

    /** 
     * Multiply this Polynom with a given Polynom
     * Time complexity - O(n*m) - this polynom length * other polynom length = n*m <br>
     * Space complexity - O(n)
     * @param other The Polynom to multiple this with.
     * @return The Polynom after multiplying the given PolyNode
     */
    public Polynom multPol (Polynom other) {

        if(other == null || other._head == null)
            return this;
        PolyNode cur = _head;
        PolyNode otherCur = other._head;
        Polynom mult = new Polynom();

        while(cur != null) {
            while(otherCur != null) {
                //calculating the multiplication
                mult.addNode(new PolyNode(cur.getPower()+otherCur.getPower(),cur.getCoefficient()*otherCur.getCoefficient()));
                otherCur = otherCur.getNext();
            }
            //go back to the first polynode of other polynom
            otherCur = other._head;
            //advance to the next polynodeof this polynom
            cur = cur.getNext();
        }
        _head = mult._head;//assign the result to this polynom
        return this;
    }

    /**
     * gets the differential of a Polynom<br>
     * Time complexity - O(n) - worst case we go through the entire length of the Polynom<br>
     * Space complexity - O(1)
     * @return The differential of the Polynom
     */
    public Polynom differential () {

        if (_head  == null)
            return this;
        PolyNode current = _head;
        while( current != null)	{
            //calculating the differential of the Polynom
            current.setCoefficient(current.getCoefficient() * current.getPower());
            current.setPower(current.getPower() - 1);
            current = current.getNext();
        }
        return this;
    }

    /**
     * returns a String that represents this Polynom <br>
     * Time complexity - O(n) - worst case we go through the entire length of the Polynom<br>
     * Space complexity - O(1)
     * @Override toString in class java.lang.Object
     * @return String that represents this Polynom in the following format: a_nx^n+a_n-1x^n-1+...+a_0x^0<br>
     * for example: 2.8x^10-4.9x^3+6.5x^2-12.0
     */
    public String toString() 
    {
        String str = "";
        PolyNode _current = _head;
        while (_current != null) {
            str += (_current.getCoefficient()>0? "+":"") + _current;
            _current = _current.getNext();
        }
        if(str.startsWith("+"))//if needed - removing unnecessary + signs at the start of str
            str=str.substring(1, str.length());
        return str;
    }
}
