package DevJavaTutorial.src;
import java.io.IOException;

import java.util.ArrayList;

public interface Parser {
    //We need a method called .parse on this to return an Article
    // datatype methodName(typeofarg1 argument1, typeofarg2 argument2);
    ArrayList<Article> parse() throws IOException, InterruptedException;
}

//public ArrayList<Article> parse()

/*
public interface OperateCar {

   // constant declarations, if any

   // method signatures
   
   // An enum with values RIGHT, LEFT
   int turn(Direction direction,
            double radius,
            double startSpeed,
            double endSpeed);
   int changeLanes(Direction direction,
                   double startSpeed,
                   double endSpeed);
   int signalTurn(Direction direction,
                  boolean signalOn);
   int getRadarFront(double distanceToCar,
                     double speedOfCar);
   int getRadarRear(double distanceToCar,
                    double speedOfCar);
         ......
   // more method signatures
}
*/