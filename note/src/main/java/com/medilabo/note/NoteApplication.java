package com.medilabo.note;

import com.medilabo.note.model.Note;
import com.medilabo.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class NoteApplication implements CommandLineRunner {

    @Autowired
    private NoteRepository noteRepository;

    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class, args);
    }

    @Override
    public void run(String... args) {

        if (noteRepository.count() == 0) {
            List<Note> notes = List.of(
                    new Note(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."),
                    new Note(null, 2L, "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement."),
                    new Note(null, 2L, "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale."),
                    new Note(null, 3L, "Le patient déclare qu'il fume depuis peu."),
                    new Note(null, 3L, "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé."),
                    new Note(null, 4L, "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments."),
                    new Note(null, 4L, "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps."),
                    new Note(null, 4L, "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé."),
                    new Note(null, 4L, "Taille, Poids, Cholestérol, Vertige et Réaction.")
            );
            noteRepository.saveAll(notes);
        }
    }
}
