package logic;

import java.io.Serializable;

public class Die implements Serializable {

    public Die() {}

    public int roll() {
        return  (int) (Math.random() * 6) + 1;
    }
}
