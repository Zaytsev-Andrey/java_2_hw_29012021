package hw1;

import hw1.entity.*;
import hw1.interfaces.Obstacle;
import hw1.interfaces.Participant;

public class Main {
    public static void main(String[] args) {
        Participant[] participants = {
            new Human("Александр", 4000, 120),
            new Cat("Барсик", 1500, 140),
            new Robot("C-3PO", 7000, 50),
            new Human(),
            new Cat(),
            new Robot()
        };

        Obstacle[] obstacles = {
            new Treadmill(500),
            new Wall(50),
            new Treadmill(1500),
            new Wall(100),
            new Treadmill(5000),
            new Wall(200)
        };

        for (Participant participant : participants) {
            System.out.println("Участник: " + participant);
            for (Obstacle obstacle : obstacles) {
                if (!obstacle.overcome(participant)) {
                    break;
                }
            }
            System.out.println();
        }
    }
}
