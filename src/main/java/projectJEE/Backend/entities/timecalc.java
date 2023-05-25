package projectJEE.Backend.entities;

public class timecalc {
    private long time;
    private discipline discipline;

    public timecalc(long time, discipline discipline) {
        this.time = time;
        this.discipline = discipline;
    }

    public void updatetime (long time) {
        this.time = this.time + time;
    }

    public long gettime () {
        return this.time;
    }

    public discipline getdiscipline () {
        return this.discipline;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
