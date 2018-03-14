package comp3350.podcast.objects;
import android.support.annotation.NonNull;
import java.io.Serializable;

//================================ CONSTRUCTORS ==========================================//

public class Date implements Comparable, Serializable {

    public int year = -1, month = -1, day = -1, hour = -1, minute = -1, second = -1;

    public Date() {
    }

    public Date(int year) {
        this.year = year;
    }

    public Date(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(int year, int month, int day, int hour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
    }

    public Date(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public Date(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Returns true if all parts of this date are equal to target object (year, month, day, etc).
     *
     * @param obj - The date to compare against
     * @return - True if equal dates
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date other = (Date) obj;

            return (other.year == this.year) &&
                    (other.month == this.month) &&
                    (other.day == this.day) &&
                    (other.hour == this.hour) &&
                    (other.minute == this.minute) &&
                    (other.second == this.second);
        }
        return false;
    }

    /**
     * Compares this date to the target date
     * if this date comes before the target return -1
     * if this date is the same as the target return 0
     * if this date comes after the target return 1
     *
     * @param obj - The date to compare against
     * @return -1, 0, or 1
     */
    @Override
    public int compareTo(@NonNull Object obj) {
        Date other = (Date) obj;
        int result = 0;

        //Check all the stages on the date
        if ((result = compareTimes(this.year, other.year)) == 0)
            if ((result = compareTimes(this.month, other.month)) == 0)
                if ((result = compareTimes(this.day, other.day)) == 0)
                    if ((result = compareTimes(this.hour, other.hour)) == 0)
                        if ((result = compareTimes(this.minute, other.minute)) == 0)
                            result = compareTimes(this.second, other.second);

        return result;

    }

    /**
     * Returns -1 if thisTime is less than otherTime
     * Returns 0 if thisTime is equal to otherTime
     * Returns 1 if thisTime is greater than otherTime
     *
     * @param thisTime  - The time for this object
     * @param otherTime - The time for the other object
     * @return returns integer comparison of given times as ints. Note this is intended for comparing components of a time, not
     * the whole time object.
     */
    private int compareTimes(int thisTime, int otherTime) {

        if (thisTime < otherTime) {
            return -1;
        }

        if (thisTime > otherTime) {
            return 1;
        }

        return 0;
    }
    
    /**
     * To String
     * 
     * @return Returns string representing the date in YYYY-MM-DD format
     */ 
    public String toString()
    {
        return (year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
    }
}
