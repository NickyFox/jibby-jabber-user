package com.user.model.dto;

public class FollowerDto {

    private long to;
    private long from;

    public FollowerDto() {
    }

    public FollowerDto(long to, long from) {
        this.to = to;
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }
}
