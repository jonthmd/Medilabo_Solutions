package com.medilabo.risk.model;

import lombok.Getter;

/**
 * Enum used to represent a list of risks.
 */
@Getter
public enum Risks {
    NONE("None"),
    BORDERLINE("Borderline"),
    DANGER("In Danger"),
    ONSET("Early Onset");

    private final String level;

    Risks(String value) {
        this.level = value;
    }

}
