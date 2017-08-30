package ohlsen.kindermathe;

import android.util.Log;

/**
 * Created by schoeneo on 24.08.2017.
 */

/**
 * Represents an equation with basic operations (+,-,*,/)
 */
public class Aufgabe {

    private int Teil1;
    private int Teil2;

    private Operation operation = Operation.None;

    public Aufgabe() {

    }

    public Aufgabe(int Teil1, int Teil2, Operation operation) {
        this.setTeil1(Teil1);
        this.setTeil2(Teil2);
        this.setOperation(operation);
    }

    /**
     * Do some checks, that it this ist suitable for primary school students
     * @return equation is suitable or not
     */
    public Boolean isValid() {
        Boolean retVal = true;
        // No operation defined
        if(this.getOperation() == Operation.None) {
            retVal = false;
        }
        // solution constrained to 120
        // @TODO parameter in settings menu editable
        if(this.getOperation() == Operation.Addition) {
            if(this.getTeil1() + this.getTeil2() > 120) {
                retVal = false;
            }
        }
        // No negative solutions
        if(this.getOperation() == Operation.Subtraktion) {
            if(this.getTeil1() < this.getTeil2()) {
                retVal = false;
            }
        }
        // Only integer divisions with zero rest
        if(this.getOperation() == Operation.Division) {
            if(this.getTeil1() == 0) {
                retVal = false;
            }
            if(this.getTeil1() < this.getTeil2()) {
                retVal = false;
            }
            if(ApplicationManager.DEBUG) {
                Log.d(this.getClass().toString(), "Modulo: Teil 1 "+this.getTeil1() + " Teil 2 "+this.getTeil2() );
            }
            if(this.getTeil1() > 0 && this.getTeil2() > 0) {
                int rest = this.getTeil1() % this.getTeil2();
                if (ApplicationManager.DEBUG) {
                    Log.d(this.getClass().toString(), "Modulo rest: " + rest);
                }
                if (rest != 0) {
                    retVal = false;
                }
            }
            else {
                retVal = false;
            }
        }
        return retVal;
    }

    /**
     * Gets solution of equation
     * @return int solution
     */
    public int getErgebnis() {
        int retVal = -1;
        if(this.isValid()) {
            switch(this.operation) {
                case Addition:
                    retVal = this.Teil1 + this.Teil2;
                    break;
                case Subtraktion:
                    retVal = this.Teil1 - this.Teil2;
                    break;
                case Multiplikation:
                    retVal =  this.Teil1 * this.Teil2;
                    break;
                case Division:
                    retVal = this.Teil1 / this.Teil2;
                    break;
            }
        }
        return retVal;
    }

    /**
     * First part of equation
     * @return int first number
     */
    public int getTeil1() {
        return Teil1;
    }

    public void setTeil1(int teil1) {
        Teil1 = teil1;
    }

    /**
     * Second part of equation
     * @return int second number
     */
    public int getTeil2() {
        return Teil2;
    }

    public void setTeil2(int teil2) {
        Teil2 = teil2;
    }

    /**
     * Operation of equation
     * @return
     */
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Overrides toString() for nice display of equation
     * @return
     */
    @Override
    public String toString() {
        return this.Teil1 + this.operation.toString() + this.Teil2;
    }
}
