package game.xo.controllers;


import game.xo.model.Field;
import game.xo.model.Figure;
import game.xo.exception.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public Figure getWinner(Field field){
        try {
            for (int i=0;i<3;i++) {
                if (check(field, new Point(i, 0), p-> new Point(p.x,p.y+1)))
                    return field.getFigure(new Point(i, 0));
            }
            for (int i=0;i<3;i++) {
                if (check(field, new Point(0, i), p-> new Point(p.x+1,p.y)))
                    return field.getFigure(new Point(0, i));
            }
            if (check(field, new Point(0, 0), p-> new Point(p.x+1,p.y+1)))
                return field.getFigure(new Point(0, 0));

            if (check(field, new Point(0, 2), p-> new Point(p.x+1,p.y-1)))
                return field.getFigure(new Point(0, 2));
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean check(Field field, Point currentPoint,PointGenerator pointGenerator){
        Figure currentFigure;
        Figure nextFigure;
        Point nextPoint = pointGenerator.next(currentPoint);
        try {
            currentFigure = field.getFigure(currentPoint);
            if(currentFigure==null)return false;
            nextFigure = field.getFigure(currentPoint);
        } catch (InvalidPointException e) {
            return true;
        }
        if(currentFigure!=nextFigure)return false;
        return check(field,nextPoint,pointGenerator);
    }

    private interface PointGenerator{
        Point next(Point point);
    }
}
