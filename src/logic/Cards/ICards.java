package logic.Cards;

public interface ICards {

    int getCardNumber();
    String getEvent(int day);
    void setEffect(int day);
    void setMovement(int day);
}

