package mainObjects;

class Flags {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Boxes.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Boxes get(Coordinates coordinates) {
        return flagMap.get(coordinates);
    }

    public void setOpenedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Boxes.OPENED);
        --countOfClosedBoxes;
    }

    public void setFlagedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Boxes.FLAGED);
    }

    private void setClosedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Boxes.CLOSED);
    }

    public void setToggleToBox(Coordinates coordinates) {
        switch (flagMap.get(coordinates)) {
            case FLAGED -> {
                setClosedToBox(coordinates);
                break;
            }
            case CLOSED -> {
                setFlagedToBox(coordinates);
                break;
            }
        }
    }


    public int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    public void setBombedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Boxes.BOMBED);
    }

    public void setOpenedToBomb(Coordinates cord) {
        if (flagMap.get(cord) == Boxes.CLOSED)
            flagMap.set(cord, Boxes.OPENED);
    }

    public void setNobombToFlagedSafeBox(Coordinates cord) {
        if (flagMap.get(cord) == Boxes.FLAGED)
            flagMap.set(cord, Boxes.NOBOMB);
    }


    public int getCountofFlagedBoxesAround(Coordinates coordinates) {
        int count = 0;
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
            if (flagMap.get(around) == Boxes.FLAGED)
                ++count;
        }
        return count;
    }

}
