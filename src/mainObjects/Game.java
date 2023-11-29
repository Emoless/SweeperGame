package mainObjects;

public class Game {

    private Bombs bombs;
    private Flags flags;

    private GameState state;

    public Game(int cols, int rows, int bombsCount) {
        Ranges.setSize(new Coordinates(cols, rows));
        bombs = new Bombs(bombsCount);
        flags = new Flags();
    }

    public void start() {
        bombs.start();
        flags.start();
        state = GameState.PLAYED;
    }

    public Boxes getBox(Coordinates coordinates) {
        if (flags.get(coordinates) == Boxes.OPENED)
            return bombs.get(coordinates);
        else
            return flags.get(coordinates);

    }

    public void pressLeftButton(Coordinates coordinates) {
        if (gameOver())
            return;
        openBox(coordinates);
        checkWin();
    }

    private boolean gameOver() {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }

    private void checkWin()
    {
        if (state == GameState.PLAYED)
            if (flags.getCountOfClosedBoxes() == bombs.getTotalBombs())
                state = GameState.WINNER;

    }
    private void openBox(Coordinates coordinates) {
        switch (flags.get(coordinates)) {
            case OPENED -> {
                setOpenedToClosedBoxesAroundNumber(coordinates);
                return;
            }
            case FLAGED -> {
                return;
            }
            case CLOSED -> {
                switch (bombs.get(coordinates)) {
                    case ZERO -> {
                        openBoxesAround(coordinates);
                        return;
                    }
                    case BOMB -> {
                        openBombs(coordinates);
                        return;
                    }
                    default -> {
                        flags.setOpenedToBox(coordinates);
                    }
                }
            }
        }
    }

    public void setOpenedToClosedBoxesAroundNumber(Coordinates coordinates) {
        if (bombs.get(coordinates) != Boxes.BOMB)
            if (flags.getCountofFlagedBoxesAround(coordinates) == bombs.get(coordinates).getNumber())
                for (Coordinates around : Ranges.getCoordinatesAround(coordinates))
                    if (flags.get(around) == Boxes.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coordinates coordinates) {
        state = GameState.BOMBED;
        flags.setBombedToBox (coordinates);
        for (Coordinates cord : Ranges.getAllCoordinates())
        {
            if (bombs.get(cord) == Boxes.BOMB)
                flags.setOpenedToBomb(cord);
            else
                flags.setNobombToFlagedSafeBox(cord);
        }
    }

    private void openBoxesAround(Coordinates coordinates) {
        flags.setOpenedToBox(coordinates);
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates))
        {
            openBox(around);
        }
    }

    public void pressRightButton(Coordinates coordinates) {
        if (gameOver())
            return;
        flags.setToggleToBox(coordinates);
    }

    public GameState getState() {
        return state;
    }
}
