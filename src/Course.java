import java.time.LocalTime;

public class Course {
    private String name, year, delivery, ltlDay;
    private int capacity;
    private LocalTime ltlTime;
    private double duration;


    public Course(String name, int capacity, String year, String delivery,
                  String ltlDay, LocalTime ltlTime, double duration) {
        this.name = name;
        this.capacity = capacity;
        this.year = year;
        this.delivery = delivery;
        this.ltlDay = ltlDay;
        this.ltlTime= ltlTime;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getLtlDay() {
        return ltlDay;
    }

    public void setLtlDay(String ltlDay) {
        this.ltlDay = ltlDay;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalTime getLtlTime() {
        return ltlTime;
    }

    public void setLtlTime(LocalTime ltlTime) {
        this.ltlTime = ltlTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
