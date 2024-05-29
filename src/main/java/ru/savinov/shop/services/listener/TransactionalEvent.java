package ru.savinov.shop.services.listener;

public class TransactionalEvent {

    private final TransactionalType type;
    private final Object beforeCommit;
    private final Object afterCommit;

    public TransactionalType getType() {
        return type;
    }

    public Object getBeforeCommit() {
        return beforeCommit;
    }

    public Object getAfterCommit() {
        return afterCommit;
    }

    public TransactionalEvent(TransactionalType type, Object beforeCommit, Object afterCommit) {
        this.type = type;
        this.beforeCommit = beforeCommit;
        this.afterCommit = afterCommit;
    }

    public static TransactionalEvent read() {
        return new TransactionalEvent(TransactionalType.READ, null, null);
    }

    public static TransactionalEvent create(Object created) {
        return new TransactionalEvent(TransactionalType.CREATE, null, created);
    }

}