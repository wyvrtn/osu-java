package osuapi.framework.iterator;

public enum ExitType {
    WHILE(0),
    DO_WHILE(0);
    
    private int state;
    
    private ExitType(int state) {
        this.state = state;
    }
    
    public int getState() {
        return this.state;
    }
    
    protected void setState(int val) {
        this.state = val;
    }
}

