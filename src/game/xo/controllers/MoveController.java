package game.xo.controllers;

import game.xo.model.Field;
import game.xo.model.Figure;
import game.xo.exception.AlreadyOccupiedException;
import game.xo.exception.InvalidPointException;

import java.awt.Point;

public class MoveController {

    public void apllyFigure(Field field, Point point, Figure figure) throws InvalidPointException,AlreadyOccupiedException{
        if(field.getFigure(point)!=null){
            throw new AlreadyOccupiedException();
        }
        field.setFigure(point,figure);
    }
}
