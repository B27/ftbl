package com.example.user.secondfootballapp.model;

public class OffsetAndLimit {
    private int offset;
    private int limit;

    public OffsetAndLimit(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}