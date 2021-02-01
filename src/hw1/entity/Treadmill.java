package hw1.entity;

import hw1.interfaces.Obstacle;
import hw1.interfaces.Participant;

public class Treadmill implements Obstacle {
    private final int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    /**
     * Заставляет участника пробежать на беговой дорожке и выводит информацию пробежал или не смог
     * @param participant участник
     * @return true если участьник пробежал всю дистанцию, иначе false
     */
    @Override
    public boolean overcome(Participant participant) {
        if (participant.run(distance) == distance) {
            System.out.printf("%s пробежал на беговой дорожке %d м\n", participant, distance);
            return true;
        } else {
            System.out.printf("%s не смог пробежать на беговой дорожке %d м\n", participant, distance);
            return false;
        }
    }
}
