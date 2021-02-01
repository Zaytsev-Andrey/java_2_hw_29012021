package hw1.task5;

public enum DayOfWeek {
    SUNDAY("Воскресенье",0),            // выходной
    MONDAY("Понедельник",8),
    TUESDAY("Вторник",8),
    WEDNESDAY("Среда",8),
    THURSDAY("Четверг",8),
    FRIDAY("Пятница",7),                // короткий рабочий день
    SATURDAY("Суббота",0);

    private String ruName;
    private int workingHours;

    DayOfWeek(String ruName, int workingHours) {
        this.ruName = ruName;
        this.workingHours = workingHours;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    @Override
    public String toString() {
        return ruName;
    }
}
