package hw1.task5;

public class DayOfWeekMain {
    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.SUNDAY));
        System.out.println(getWorkingHours(DayOfWeek.MONDAY));
        System.out.println(getWorkingHours(DayOfWeek.TUESDAY));
        System.out.println(getWorkingHours(DayOfWeek.WEDNESDAY));
        System.out.println(getWorkingHours(DayOfWeek.THURSDAY));
        System.out.println(getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println(getWorkingHours(DayOfWeek.SATURDAY));
    }

    private static String getWorkingHours(DayOfWeek day) {
        int workingHours = day.getWorkingHours();

        if (workingHours == 0) {
            return String.format("Сегодня %s, это выходной", day);
        }

        DayOfWeek[] days = DayOfWeek.values();
        int index = day.ordinal();
        int hours;
        while ((hours = days[++index].getWorkingHours()) != 0) {
            workingHours += hours;
        }

        return String.format("Сегодня %s, до конца рабочей недели осталось %d рабочих часов", day, workingHours);
    }
}
