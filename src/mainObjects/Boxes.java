package mainObjects;

public enum Boxes {

    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;
    public Object image;

    Boxes getNextNumberBox ()
    {
        return  Boxes.values()[this.ordinal() + 1];
    }
    int getNumber ()
    {
        return this.ordinal();
    }

}
