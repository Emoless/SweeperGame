package mainObjects;

public class Bombs {
    private Matrix bombMap;
    private int totalBombs;

    Bombs(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Boxes.ZERO);
        for (int i = 0; i < totalBombs; ++i) {
            placeBomb();
        }
    }

    Boxes get(Coordinates coordinates) {
        return bombMap.get(coordinates);
    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBomb() {
        while (true) {
            Coordinates coordinates = Ranges.getRandomCoordinate();
            if (Boxes.BOMB == bombMap.get(coordinates))
                continue;
            bombMap.set(coordinates, Boxes.BOMB);
            incNumbersAroundBomb(coordinates);
            break;
        }

    }

    private void incNumbersAroundBomb(Coordinates coordinates) {
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
            if (Boxes.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
        }
    }


    public int getTotalBombs() {
        return totalBombs;
    }
}
