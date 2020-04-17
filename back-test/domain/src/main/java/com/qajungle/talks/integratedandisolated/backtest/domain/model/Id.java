package com.qajungle.talks.integratedandisolated.backtest.domain.model;

import java.util.UUID;

public abstract class Id {

    private UUID id;

    protected Id() {
        super();
        this.id = UUID.randomUUID();
    }

    protected Id(final UUID id) {
        this();
        this.id = id;
    }

    public UUID id() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
