package CheckSort.analyzer;

/**
 * Created by Kanivets on 02.11.2017
 */
public class Timer {
    private long start;
    private long stop;
    private long finalTime;

    public void start(){
        this.start = System.nanoTime();
    }

    public long stop(){
        this.stop = System.nanoTime();
        return this.finalTime = this.stop - this.start;
    }
}
