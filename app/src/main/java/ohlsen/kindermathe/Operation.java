package ohlsen.kindermathe;

public enum Operation {
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

    public static Operation fromId(int id) {
        for(Operation type : Operation.values()) {
            if(type.getValue() == id) {
                return type;
            }
        }
        return None;
    }

    @Override
    public String toString() {
        String retVal = "undef.";
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
