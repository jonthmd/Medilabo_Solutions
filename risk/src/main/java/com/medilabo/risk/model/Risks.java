package com.medilabo.risk.model;

import lombok.Getter;

@Getter
public enum Risks {
    NONE("None"),
    BORDERLINE("Bordeline"),
    DANGER("In Danger"),
    ONSET("Early Onset");

    private final String level;

    Risks(String value) {
        this.level = value;
    }

}
