package game.xo.model;

import game.xo.exception.InvalidPointException;

import java.awt.Point;

public class Field {

    private final int fieldSize;
    private final Figure[][] field;

    public Field(int fieldSize){
        this.fieldSize=fieldSize;
        this.field = new Figure[fieldSize][fieldSize];
    }
    public Figure getFigure(Point point) throws InvalidPointException {
        if(!checkPoint(point)){
            throw new InvalidPointException();
        }
        return field[point.x][point.y];
    }

    public void setFigure(Point point,Figure figure)throws InvalidPointException{
        if(!checkPoint(point)){
            throw new InvalidPointException();
        }
        field[point.x][point.y]=figure;
    }

    private boolean checkPoint(Point point){
        return checkCoordinate(point.x) && checkCoordinate(point.y);
    }

    private boolean checkCoordinate(int coordinate){
        return coordinate>=0 && coordinate<fieldSize;
    }

    public int getSize(){
        return fieldSize;
    }
}
