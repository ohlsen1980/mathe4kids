package ohlsen.kindermathe;

/**
 * Enum for basic mathematical operations
 */
public enum Operation {
    //casting operations to int to be able to randomly generate an operation
    Addition (0),
    Subtraktion (1),
    Multiplikation (2),
    Division (3),
    None(4);


    private int value = 0;

    Operation(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Gets an operation from Id
     * @param id
     * @return operation which mathes given id or None, if id doesnt match
     */
    public static Operation fromId(int id) {
        for(Operation type : Operation.values()) {
            if(type.getValue() == id) {
                return type;
            }
        }
        return None;
    }

    /**
     * Overriding toString() for display in app
     * @return
     */
    @Override
    public String toString() {
        String retVal = "undef."; //Should never be reached
        switch (this.value) {
            case 0:
                retVal = " + ";
                break;
            case 1:
                retVal = " - ";
                break;
            case 2:
                retVal = " * ";
                break;
            case 3:
                retVal = " : ";
                break;
        }
        return retVal;
    }
}
