package aaa.edu.net.entity;

/**
 * Created by aaa on 2016/6/23.
 */
public enum Priority {
    LOWEST(1),LOW(3), NORMAL(5),HIGN(8),HIGHEST(5);
    int level=5;
    private Priority(int level){
        this.level=level;
    }
    public int getLevel() {
        return level;
    }
}
