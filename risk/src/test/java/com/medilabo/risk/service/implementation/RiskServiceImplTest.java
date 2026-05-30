package com.medilabo.risk.service.implementation;

import com.medilabo.risk.dto.DetailsDTO;
import com.medilabo.risk.dto.NoteDTO;
import com.medilabo.risk.dto.PatientDTO;
import com.medilabo.risk.dto.RiskDTO;
import com.medilabo.risk.model.Risks;
import com.medilabo.risk.utils.CalculateAge;
import com.medilabo.risk.utils.CountTriggers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RiskServiceImplTest {

    @Mock
    private CalculateAge calculateAge;

    @Mock
    private CountTriggers countTriggers;

    @InjectMocks
    private RiskServiceImpl classUnderTest;

    @Test
    void evaluationNone() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "M", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(31);
        when(countTriggers.count(any())).thenReturn(0);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.NONE);
    }

    @Test
    void evaluationBorderline() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "M", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(31);
        when(countTriggers.count(any())).thenReturn(3);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.BORDERLINE);
    }

    @Test
    void evaluationDangerUnder30Male() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "M", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(29);
        when(countTriggers.count(any())).thenReturn(3);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.DANGER);
    }

    @Test
    void evaluationDangerUnder30Female() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "F", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(29);
        when(countTriggers.count(any())).thenReturn(4);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.DANGER);
    }

    @Test
    void evaluationDangerAbove30() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "F", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(31);
        when(countTriggers.count(any())).thenReturn(7);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.DANGER);
    }

    @Test
    void evaluationOnSetUnder30Male() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "M", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(29);
        when(countTriggers.count(any())).thenReturn(5);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.ONSET);
    }

    @Test
    void evaluationOnSetUnder30Female() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "F", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(29);
        when(countTriggers.count(any())).thenReturn(7);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.ONSET);
    }

    @Test
    void evaluationOnSetAbove30() {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1L, "Jon", "TH", "2000-01-01", "F", "", "");
        List<NoteDTO> noteDTOList = List.of(
                new NoteDTO(null, 1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé."));
        DetailsDTO detailsDTO = new DetailsDTO(patientDTO, noteDTOList);

        when(calculateAge.calculate(any())).thenReturn(31);
        when(countTriggers.count(any())).thenReturn(8);

        //WHEN
        RiskDTO result = classUnderTest.evaluation(detailsDTO);

        //THEN
        verify(calculateAge).calculate(any());
        verify(countTriggers).count(any());
        assertThat(result.getLevel()).isEqualTo(Risks.ONSET);
    }
}