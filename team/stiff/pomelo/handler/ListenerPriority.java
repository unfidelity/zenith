/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.handler;

public enum ListenerPriority {
    LOWEST(-750),
    LOWER(-500),
    LOW(-250),
    NORMAL(0),
    HIGH(250),
    HIGHER(500),
    HIGHEST(750);

    private final int priorityLevel;

    private ListenerPriority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }
}

