package comp3350.podcast.objects;

import android.support.annotation.NonNull;

/**
 * Created by Russell on 2018-01-27.
 */
public class Date implements Comparable {

    public int year = -1, month = -1, day = -1, hour = -1, minute = -1, second = -1;


    /**
     * returns true if all parts of this date are equal to target object (year, month, day, etc).
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
        int ret;

        //Check all the stages on the date
        if ((ret = compareTimes(this.year, other.year)) != 0)
            if ((ret = compareTimes(this.month, other.month)) != 0)
                if ((ret = compareTimes(this.day, other.day)) != 0)
                    if ((ret = compareTimes(this.hour, other.hour)) != 0)
                        if ((ret = compareTimes(this.minute, other.minute)) != 0)
                            ret = compareTimes(this.second, other.second);

        return ret;

    }

    /**
     * Returns -1 if thisTime is less than otherTime
     * Returns 0 if thisTime is equal to otherTime
     * Returns 1 if thisTime is greater than otherTime
     *
     * @param thisTime  - The time for this object
     * @param otherTime - The time for the other object
     * @return see description
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
}
