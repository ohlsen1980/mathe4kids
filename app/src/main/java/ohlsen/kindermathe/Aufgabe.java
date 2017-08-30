package ohlsen.kindermathe;

import android.util.Log;

/**
 * Created by schoeneo on 24.08.2017.
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

    public Boolean isValid() {
        Boolean retVal = true;
        if(this.getOperation() == Operation.None) {
            retVal = false;
        }
        if(this.getOperation() == Operation.Addition) {
            if(this.getTeil1() + this.getTeil2() > 120) {
                retVal = false;
            }
        }
        if(this.getOperation() == Operation.Subtraktion) {
            if(this.getTeil1() < this.getTeil2()) {
                retVal = false;
            }
        }
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

    public int getTeil1() {
        return Teil1;
    }

    public void setTeil1(int teil1) {
        Teil1 = teil1;
    }

    public int getTeil2() {
        return Teil2;
    }

    public void setTeil2(int teil2) {
        Teil2 = teil2;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return this.Teil1 + this.operation.toString() + this.Teil2;
    }
}
