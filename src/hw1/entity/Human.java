package hw1.entity;

import hw1.interfaces.Participant;

public class Human implements Participant {
    private static int id = 0;
    private final String name;
    private final int maxDistance;          // Максимальная дистанция в метрах которую можно пробежать
    private final int maxHeight;            // Максимальная высота в сантиметрах которую можно перепрыгнуть

    public Human() {
        this.name = "Человек-" + ++id;
        this.maxDistance = 5000;
        this.maxHeight = 100;
    }

    public Human(String name, int maxDistance, int maxHeight) {
        id++;
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    /**
     * Выводит в консоль информацию от том что объект бежит
     * @param distance - дистанция которую необходимо пробежать
     * @return реальную дистанцию которую пробежал объект
     */
    @Override
    public int run(int distance) {
        System.out.printf("%s бежит\n", this, distance);
        return Math.min(distance, maxDistance);
    }

    /**
     * Выводит в консоль информацию от том что объект прыгает через препятствие
     * @param height - препятствие которое необходимо перепрыгнуть
     * @return реальную высоту на которую прыгнул объект
     */
    @Override
    public int jump(int height) {
        System.out.printf("%s прыгает через препятствие\n", this, height);
        return Math.min(height, maxHeight);
    }

    @Override
    public String toString() {
        return String.format("Человек \"%s\"", name);
    }
}
