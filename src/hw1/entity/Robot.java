package hw1.entity;

import hw1.interfaces.Participant;

public class Robot implements Participant {
    private static int id = 0;
    private final String name;
    private final int maxDistance;          // Максимальная дистанция в метрах которую можно пробежать
    private final int maxHeight;            // Максимальная высота в сантиметрах которую можно перепрыгнуть

    public Robot() {
        id++;
        this.name = "Робот-" + ++id;
        this.maxDistance = 100000;
        this.maxHeight = 200;
    }

    public Robot(String name, int maxDistance, int maxHeight) {
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
     * @param height - высота препятствия которое необходимо перепрыгнуть
     * @return реальную высоту на которую прыгнул объек
     */
    @Override
    public int jump(int height) {
        System.out.printf("%s прыгает\n", this, height);
        return Math.min(height, maxHeight);
    }

    @Override
    public String toString() {
        return String.format("Робот \"%s\"", name);
    }
}
