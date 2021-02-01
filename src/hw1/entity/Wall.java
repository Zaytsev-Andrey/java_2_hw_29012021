package hw1.entity;

import hw1.interfaces.Obstacle;
import hw1.interfaces.Participant;

public class Wall implements Obstacle {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    /**
     * Заставляет участника перепрыгнуть через стену и выводит информацию перепрыгнул или не смог
     * @param participant участник
     * @return true если прыгнул на достаточную высоту, иначе false
     */
    @Override
    public boolean overcome(Participant participant) {
        if (participant.jump(height) == height) {
            System.out.printf("%s перепрыгнул стену %d см\n", participant, height);
            return true;
        } else {
            System.out.printf("%s не смог перепрыгнуть стену %d см\n", participant, height);
            return false;
        }
    }
}
