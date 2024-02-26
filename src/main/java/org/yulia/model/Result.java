package org.yulia.model;

public class Result {
    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    private  boolean out;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Result(boolean out, String name)
    {
        this.out = out;
        this.name = name;
    }
}
