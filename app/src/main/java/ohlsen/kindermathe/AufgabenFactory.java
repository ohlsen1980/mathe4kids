package ohlsen.kindermathe;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Random;

/**
 * Created by schoeneo on 24.08.2017.
 */

public class AufgabenFactory {

    private static AufgabenFactory instance = null;

    private int addSummand1_MIN = 0;
    private int addSummand2_MIN = 0;
    private int addSummand1_MAX = 100;
    private int addSummand2_MAX = 100;

    private int multFaktor1_MIN = 0;
    private int multFaktor2_MIN = 0;
    private int multFaktor1_MAX = 10;
    private int multFaktor2_MAX = 10;

    private int subFaktor1_MIN = 0;
    private int subFaktor2_MIN = 0;
    private int subFaktor1_MAX = 100;
    private int subFaktor2_MAX = 100;

    private int divFaktor1_MIN = 0;
    private int divFaktor2_MIN = 0;
    private int divFaktor1_MAX = 100;
    private int divFaktor2_MAX = 100;

    private SharedPreferences preferences = null;
    private Activity activity = null;


    //private constructor
    private AufgabenFactory(Activity act) {
        this.activity = act;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
        initializeSettings();
    }

    /**
     * Singelton implementation
     * @param act basic activity
     * @return Instance of AufgabenFactory
     */
    public static AufgabenFactory getInstance(Activity act) {
        if(instance == null) {
            instance = new AufgabenFactory(act);
        }
        return instance;
    }

    /**
     * Initialize the settings from settings fragment, stored in SharedPreferences
     */
    private void initializeSettings() {
        this.addSummand1_MIN = this.preferences.getInt("add1Min", -1) >= 0 ? this.preferences.getInt("add1Min", -1) : this.addSummand1_MIN;
        this.addSummand2_MIN = this.preferences.getInt("add2Min", -1) >= 0 ? this.preferences.getInt("add2Min", -1) : this.addSummand2_MIN;
        this.addSummand1_MAX = this.preferences.getInt("add1Max", -1) >= 0 ? this.preferences.getInt("add1Max", -1) : this.addSummand1_MAX;
        this.addSummand2_MAX = this.preferences.getInt("add2Max", -1) >= 0 ? this.preferences.getInt("add2Max", -1) : this.addSummand2_MAX;
        this.subFaktor1_MIN = this.preferences.getInt("sub1Min", -1) >= 0 ? this.preferences.getInt("sub1Min", -1) : this.subFaktor1_MIN;
        this.subFaktor2_MIN = this.preferences.getInt("sub2Min", -1) >= 0 ? this.preferences.getInt("sub2Min", -1) : this.subFaktor2_MIN;
        this.subFaktor1_MAX = this.preferences.getInt("sub1Max", -1) >= 0 ? this.preferences.getInt("sub1Max", -1) : this.subFaktor1_MAX;
        this.subFaktor2_MAX = this.preferences.getInt("sub2Max", -1) >= 0 ? this.preferences.getInt("sub2Max", -1) : this.subFaktor2_MAX;
        this.multFaktor1_MIN = this.preferences.getInt("mult1Min", -1) >= 0 ? this.preferences.getInt("mult1Min", -1) : this.multFaktor1_MIN;
        this.multFaktor2_MIN = this.preferences.getInt("mult2Min", -1) >= 0 ? this.preferences.getInt("mult2Min", -1) : this.multFaktor2_MIN;
        this.multFaktor1_MAX = this.preferences.getInt("mult1Max", -1) >= 0 ? this.preferences.getInt("mult1Max", -1) : this.multFaktor1_MAX;
        this.multFaktor2_MAX = this.preferences.getInt("mult2Max", -1) >= 0 ? this.preferences.getInt("mult2Max", -1) : this.multFaktor2_MAX;
        this.divFaktor1_MIN = this.preferences.getInt("div1Min", -1) >= 0 ? this.preferences.getInt("div1Min", -1) : this.divFaktor1_MIN;
        this.divFaktor2_MIN = this.preferences.getInt("div2Min", -1) >= 0 ? this.preferences.getInt("div2Min", -1) : this.divFaktor2_MIN;
        this.divFaktor1_MAX = this.preferences.getInt("div1Max", -1) >= 0 ? this.preferences.getInt("div1Max", -1) : this.divFaktor1_MAX;
        this.divFaktor2_MAX = this.preferences.getInt("div2Max", -1) >= 0 ? this.preferences.getInt("div2Max", -1) : this.divFaktor2_MAX;
        saveSettings();
    }

    /**
     * Save settings to SharedPreferences
     */
    public void saveSettings() {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putInt("add1Min", this.addSummand1_MIN);
        editor.putInt("add2Min", this.addSummand2_MIN);
        editor.putInt("add1Max", this.addSummand1_MAX);
        editor.putInt("add2Max", this.addSummand2_MAX);
        editor.putInt("sub1Min", this.subFaktor1_MIN);
        editor.putInt("sub2Min", this.subFaktor2_MIN);
        editor.putInt("sub1Max", this.subFaktor1_MAX);
        editor.putInt("sub2Max", this.subFaktor2_MAX);
        editor.putInt("mult1Min", this.multFaktor1_MIN);
        editor.putInt("mult2Min", this.multFaktor2_MIN);
        editor.putInt("mult1Max", this.multFaktor1_MAX);
        editor.putInt("mult2Max", this.multFaktor2_MAX);
        editor.putInt("div1Min", this.divFaktor1_MIN);
        editor.putInt("div2Min", this.divFaktor2_MIN);
        editor.putInt("div1Max", this.divFaktor1_MAX);
        editor.putInt("div2Max", this.divFaktor2_MAX);
        editor.commit();
    }


    /**
     * Generates a random mathematical operation
     * @return operation (enum)
     */
    private Operation getRandomOperation() {
        int randomNum = getRandomNum(0, 3);
        return Operation.fromId(randomNum);
    }

    /**
     * Generates a random equation with the given operation
     * @param operation
     * @return equation
     */
    private Aufgabe generate(Operation operation) {
        Aufgabe retVal = new Aufgabe();
        retVal.setOperation(operation);
        switch(operation) {
            case Addition:
                retVal.setTeil1(getRandomNum(this.getAddSummand1_MIN(), this.getAddSummand1_MAX()));
                retVal.setTeil2(getRandomNum(this.getAddSummand2_MIN(), this.getAddSummand2_MAX()));
                break;
            case Multiplikation:
                retVal.setTeil1(getRandomNum(this.getMultFaktor1_MIN(), this.getMultFaktor1_MAX()));
                retVal.setTeil2(getRandomNum(this.getMultFaktor2_MIN(), this.getMultFaktor2_MAX()));
                break;
            case Subtraktion:
                retVal.setTeil1(getRandomNum(this.getSubFaktor1_MIN(), this.getSubFaktor1_MAX()));
                retVal.setTeil2(getRandomNum(this.getSubFaktor2_MIN(), this.getSubFaktor2_MAX()));
                break;
            case Division:
                retVal.setTeil1(getRandomNum(this.getDivFaktor1_MIN(), this.getDivFaktor1_MAX()));
                retVal.setTeil2(getRandomNum(this.getDivFaktor2_MIN(), this.getDivFaktor2_MAX()));
                break;
        }
        return retVal;
    }

    /**
     * Gets a valid equation, which is in the defined bounds and solveable for primary school students
     * @return equation
     */
    public Aufgabe getAufgabe() {
        Aufgabe retVal = new Aufgabe();
        Operation operation = getRandomOperation();
        do {
            retVal = this.generate(operation);
        }while (retVal.isValid() == false);
        return retVal;
    }

    private int getRandomNum(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    //Here are some bounds for the 4 basic mathematic operations
    // @TODO all parameters in settings fragment editable

    public int getAddSummand1_MIN() {
        return addSummand1_MIN;
    }

    public void setAddSummand1_MIN(int addSummand1_MIN) {
        this.addSummand1_MIN = addSummand1_MIN;
    }

    public int getAddSummand2_MIN() {
        return addSummand2_MIN;
    }

    public void setAddSummand2_MIN(int addSummand2_MIN) {
        this.addSummand2_MIN = addSummand2_MIN;
    }

    public int getAddSummand1_MAX() {
        return addSummand1_MAX;
    }

    public void setAddSummand1_MAX(int addSummand1_MAX) {
        this.addSummand1_MAX = addSummand1_MAX;
    }

    public int getAddSummand2_MAX() {
        return addSummand2_MAX;
    }

    public void setAddSummand2_MAX(int addSummand2_MAX) {
        this.addSummand2_MAX = addSummand2_MAX;
    }

    public int getMultFaktor1_MIN() {
        return multFaktor1_MIN;
    }

    public void setMultFaktor1_MIN(int multFaktor1_MIN) {
        this.multFaktor1_MIN = multFaktor1_MIN;
    }

    public int getMultFaktor2_MIN() {
        return multFaktor2_MIN;
    }

    public void setMultFaktor2_MIN(int multFaktor2_MIN) {
        this.multFaktor2_MIN = multFaktor2_MIN;
    }

    public int getMultFaktor1_MAX() {
        return multFaktor1_MAX;
    }

    public void setMultFaktor1_MAX(int multFaktor1_MAX) {
        this.multFaktor1_MAX = multFaktor1_MAX;
    }

    public int getMultFaktor2_MAX() {
        return multFaktor2_MAX;
    }

    public void setMultFaktor2_MAX(int multFaktor2_MAX) {
        this.multFaktor2_MAX = multFaktor2_MAX;
    }

    public int getSubFaktor1_MIN() {
        return subFaktor1_MIN;
    }

    public void setSubFaktor1_MIN(int subFaktor1_MIN) {
        this.subFaktor1_MIN = subFaktor1_MIN;
    }

    public int getSubFaktor2_MIN() {
        return subFaktor2_MIN;
    }

    public void setSubFaktor2_MIN(int subFaktor2_MIN) {
        this.subFaktor2_MIN = subFaktor2_MIN;
    }

    public int getSubFaktor1_MAX() {
        return subFaktor1_MAX;
    }

    public void setSubFaktor1_MAX(int subFaktor1_MAX) {
        this.subFaktor1_MAX = subFaktor1_MAX;
    }

    public int getSubFaktor2_MAX() {
        return subFaktor2_MAX;
    }

    public void setSubFaktor2_MAX(int subFaktor2_MAX) {
        this.subFaktor2_MAX = subFaktor2_MAX;
    }

    public int getDivFaktor1_MIN() {
        return divFaktor1_MIN;
    }

    public void setDivFaktor1_MIN(int divFaktor1_MIN) {
        this.divFaktor1_MIN = divFaktor1_MIN;
    }

    public int getDivFaktor2_MIN() {
        return divFaktor2_MIN;
    }

    public void setDivFaktor2_MIN(int divFaktor2_MIN) {
        this.divFaktor2_MIN = divFaktor2_MIN;
    }

    public int getDivFaktor1_MAX() {
        return divFaktor1_MAX;
    }

    public void setDivFaktor1_MAX(int divFaktor1_MAX) {
        this.divFaktor1_MAX = divFaktor1_MAX;
    }

    public int getDivFaktor2_MAX() {
        return divFaktor2_MAX;
    }

    public void setDivFaktor2_MAX(int divFaktor2_MAX) {
        this.divFaktor2_MAX = divFaktor2_MAX;
    }
}

