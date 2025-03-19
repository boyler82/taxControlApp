package com.flynn.tax_application.service;

import com.flynn.tax_application.dto.SettlementCreateDto;
import com.flynn.tax_application.dto.SettlementResponseDto;
import com.flynn.tax_application.mapper.SettlementMapper;
import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.SettlementType;
import com.flynn.tax_application.model.TaxClient;
import com.flynn.tax_application.repository.SettlementRepository;
import com.flynn.tax_application.repository.TaxClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SettlementServiceTest {

    @Mock
    private SettlementRepository settlementRepository;
    @Mock
    private TaxClientRepository taxClientRepository;
    @Mock
    private SettlementMapper settlementMapper;

    @InjectMocks
    private SettlementService settlementService;

    @Test
    void shouldCreateSettlement() {
        // GIVEN
        SettlementCreateDto createDto = new SettlementCreateDto(SettlementType.PIT, null, false, 1L, null);
        TaxClient taxClient = new TaxClient();
        taxClient.setId(1L);

        Settlement settlement = new Settlement();
        settlement.setId(1L);
        settlement.setSettlementType(SettlementType.PIT);

        SettlementResponseDto expectedDto = new SettlementResponseDto(1L, SettlementType.PIT, false, null, null, null, false, 1L, null);

        when(taxClientRepository.findById(1L)).thenReturn(Optional.of(taxClient));
        when(settlementMapper.mapToSettlement(createDto, taxClient)).thenReturn(settlement);
        when(settlementRepository.save(settlement)).thenReturn(settlement);
        when(settlementMapper.mapToSettlementResponseDto(settlement)).thenReturn(expectedDto);

        // WHEN
        SettlementResponseDto result = settlementService.createSettlement(createDto);

        // THEN
        assertEquals(1L, result.getId());
        assertEquals(SettlementType.PIT, result.getSettlementType());
    }

    @Test
    void shouldGetSettlementById() {
        // GIVEN
        Settlement settlement = new Settlement();
        settlement.setId(1L);
        settlement.setSettlementType(SettlementType.PIT);

        SettlementResponseDto expectedDto = new SettlementResponseDto(1L, SettlementType.PIT, false, null, null, null, false, 1L, null);

        when(settlementRepository.findById(1L)).thenReturn(Optional.of(settlement));
        when(settlementMapper.mapToSettlementResponseDto(settlement)).thenReturn(expectedDto);

        // WHEN
        SettlementResponseDto result = settlementService.getSettlementById(1L);

        // THEN
        assertEquals(1L, result.getId());
        assertEquals(SettlementType.PIT, result.getSettlementType());
    }

    @Test
    void shouldUpdateSettlement() {
        // GIVEN
        Settlement settlement = new Settlement();
        settlement.setId(1L);
        settlement.setSettlementType(SettlementType.PIT);

        SettlementCreateDto updateDto = new SettlementCreateDto(SettlementType.INNE, null, false, 1L, null);

        when(settlementRepository.findById(1L)).thenReturn(Optional.of(settlement));
        when(settlementRepository.save(settlement)).thenReturn(settlement);
        when(settlementMapper.mapToSettlementResponseDto(settlement)).thenReturn(
                new SettlementResponseDto(1L, SettlementType.INNE, false, null, null, null, false, 1L, null)
        );

        // WHEN
        SettlementResponseDto result = settlementService.updateSettlement(1L, updateDto);

        // THEN
        assertEquals(1L, result.getId());
        assertEquals(SettlementType.INNE, result.getSettlementType());
    }

    @Test
    void shouldDeleteSettlement() {
        // GIVEN
        when(settlementRepository.existsById(1L)).thenReturn(true);

        // WHEN
        settlementService.deleteSettlement(1L);

        // THEN
        verify(settlementRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenSettlementNotFound() {
        // GIVEN
        when(settlementRepository.findById(999L)).thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(RuntimeException.class, () -> settlementService.getSettlementById(999L));
    }
}