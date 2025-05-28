public class TimeFormat {
    private long h;
    private long m;
    private long s;
    private long nanos;

    public TimeFormat(long nanos) {
        this.nanos = nanos;
        long totalSeconds = nanos / 1000000000;

        h = totalSeconds / 3600;
        m = totalSeconds % 3600 / 60;
        s = totalSeconds % 60;
    }

    public String toString() {
        String hoursStr = "" + h;
        if (h < 10) {
            hoursStr = "0" + hoursStr;
        }

        String minutesStr = "" + m;
        if (m < 10) {
            minutesStr = "0" + minutesStr;
        }

        String secondsStr = "" + s;
        if (s < 10) {
            secondsStr = "0" + secondsStr;
        }
        return hoursStr + ":" + minutesStr + ":" + secondsStr;
    }

    public long nanos() {
        return nanos;
    }
}