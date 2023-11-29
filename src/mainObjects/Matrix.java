package mainObjects;

class Matrix {
    private Boxes[][] matrix;

    Matrix(Boxes defaultBox) {
        matrix = new Boxes[Ranges.getSize().x][Ranges.getSize().y];
        for (Coordinates coordinates : Ranges.getAllCoordinates()) {
            matrix[coordinates.x][coordinates.y] = defaultBox;
        }
    }

    Boxes get(Coordinates coordinates) {
        if (Ranges.inRange (coordinates)) {
            return matrix[coordinates.x][coordinates.y];
        }
        return null;
    }

    void set(Coordinates coordinates, Boxes box) {
        matrix[coordinates.x][coordinates.y] = box;
    }
}
