package game.xo.view;


import game.xo.controllers.CurrentMoveController;
import game.xo.controllers.MoveController;
import game.xo.controllers.WinnerController;
import game.xo.exception.AlreadyOccupiedException;
import game.xo.exception.InvalidPointException;
import game.xo.model.Field;
import game.xo.model.Figure;
import game.xo.model.Game;

import java.awt.*;
import java.util.Scanner;

public class ConsoleView {

    private CurrentMoveController currentMoveController = new CurrentMoveController();

    private WinnerController winnerController = new WinnerController();

    private MoveController moveController = new MoveController();

    public void show(Game game){
        System.out.println("Game name: "+game.getName());
        Field field = game.getField();
        Figure figure;
        for(int x=0;x<field.getSize();x++){
            System.out.println("~~~~~~~");
            System.out.print("|");
            for(int y=0;y<field.getSize();y++){
                try {
                    figure = field.getFigure(new Point(x,y));
                }catch (InvalidPointException e){
                    throw new RuntimeException(e);
                }
                System.out.print(figure!=null ? figure : " ");
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~");
    }

    public boolean move(Game game){
        Field field = game.getField();
        Figure winner = winnerController.getWinner(field);
        if(winner!=null){
            System.out.println("Winner: "+winner);
            return false;
        }
        Figure figure = currentMoveController.currentMove(field);
        if(figure==null) {
            System.out.println("No winner!");
            return false;
        }
        System.out.println("Please enter move point for: "+figure);
        Point point = askPoint();
        try {
            moveController.apllyFigure(field,point,figure);
        } catch (InvalidPointException | AlreadyOccupiedException e ) {
            System.out.println("Point invalid!");
        }
        return true;
    }

    private int askCoordinate(String coordinateName){
        System.out.println("Please input "+coordinateName);
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private Point askPoint(){
        return new Point(askCoordinate("X"),askCoordinate("Y"));
    }
}
