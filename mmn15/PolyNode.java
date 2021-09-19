/**
 * This class represent a Node in a Polynom.
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class PolyNode {

    private int _power;
    private double _coefficient;
    private PolyNode _next;  

    /** 
     * Creates a PolyNode Object<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param power The power of the PolyNode
     * @param coefficient The coefficient of the PolyNode
     */
    public PolyNode (int power, double coefficient) {
        if(power < 0) {
            _power = 0;
            _coefficient = 0;
        }
        else {
            _power = power;
            _coefficient = coefficient;
        }
        _next = null;
    }

    /** 
     * Creates a PolyNode Object<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param power The power of the PolyNode
     * @param coefficient The coefficient of the PolyNode
     * @param next The next Node in the Polynom 
     */	
    public PolyNode(int power, double coefficient,PolyNode next) {
        if(power < 0) {
            _power = 0;
            _coefficient = 0;
        }
        else {
            _power = power;
            _coefficient = coefficient;
        }
        _next = next;
    }

    /**
     * copy constructor<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param p the PolyNode to be copied
     */
    public PolyNode( PolyNode p ) {
        if(p != null) {
            this._power = p._power;
            this._coefficient = p._coefficient;
            this._next = p._next;
        }
    }

    /**
     * gets the power of the PolyNode<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @return the power of the PolyNode
     */
    public int getPower() {
        return _power;
    }

    /**
     * gets the coefficient of the PolyNode<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @return the coefficient of the PolyNode
     */
    public double getCoefficient() {
        return _coefficient;
    }

    /**
     * gets the next node in the Polynom <br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @return the next node in the Polynom
     */
    public PolyNode getNext() {
        return _next;
    }

    /**
     * sets the power of the PolyNode<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param power the power value to set
     */
    public void setPower(int power) {
        if(power >= 0) //power must be a natural number
            this._power = power;
    }

    /**
     * sets the coefficient of the PolyNode<br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param coefficient the coefficient value to set
     */
    public void setCoefficient(double coefficient) {
        this._coefficient = coefficient;
    }

    /**
     * sets the next node in the Polynom <br>
     * Time complexity - O(1) - the method consists of a single operation<br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @param next the next node in the Polynom to set
     */
    public void setNext(PolyNode next) {
        this._next = next;
    }

    /**
     * returns a String that represents this PolyNode <br>
     * Time complexity - O(1) - <br>
     * Space complexity - O(1) - all memory is allocated for a specific set of primitive values
     * @Override toString in class java.lang.Object
     * @return String that represents this PolyNode in the following format: a_nx^n for example:
     * 6.5x^2, -15.2x^32, x^4, -8.0x, x, 5.33 
     */
    public String toString() {
        //checks all cases, making sure to follow the restrictions in the description of the question
        if(_coefficient == 0) 
            return "";
        if(_coefficient == 1) {
            if(_power == 0)
                return "1.0";
            if(_power == 1)
                return "x";
            return "x^"+_power;
        }
        if(_coefficient == -1) {
            if(_power == 0)
                return "-1.0";
            if(_power == 1)
                return "-x";
            return "-x^"+_power;
        }
        if(_power == 0)
            return ""+_coefficient;
        if(_power == 1)
            return _coefficient+"x";
        return _coefficient+"x^"+_power;
    }
}
