package com.medilabo.risk.model;

import lombok.Getter;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

/**
 * Enum used to represent a list of triggers.
 */
@Getter
public enum Triggers {
    HEMOGLOBINE("Hémoglobine A1C"),
    MICROALBUMINE("Microalbumine"),
    TAILLE("Taille"),
    POIDS("Poids"),
    FUMEUR("Fumeur", "Fumeuse"),
    ANORMAL("Anormal"),
    CHOLESTEROL("Cholestérol"),
    VERTIGE("Vertiges"),
    RECHUTE("Rechute"),
    REACTION("Réaction"),
    ANTICORPS("Anticorps");

    private final List<String> triggers;

    Triggers(String... triggers) {

        this.triggers = Arrays.stream(triggers)
                .map(trigger ->
                        Normalizer.normalize(trigger, Normalizer.Form.NFD)
                                .replaceAll("\\p{M}", "")
                                .toLowerCase()
                )
                .toList();
    }
}
