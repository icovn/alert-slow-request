package net.friend.model;

import lombok.Data;

@Data
public class BaseLog {
    protected float requestTime;

    protected String event;
}
