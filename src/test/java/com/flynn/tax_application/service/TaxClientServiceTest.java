package com.flynn.tax_application.service;

import com.flynn.tax_application.dto.TaxClientCreateDto;
import com.flynn.tax_application.dto.TaxClientResponseDto;
import com.flynn.tax_application.mapper.TaxClientMapper;
import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.TaxClient;
import com.flynn.tax_application.model.User;
import com.flynn.tax_application.repository.SettlementRepository;
import com.flynn.tax_application.repository.TaxClientRepository;
import com.flynn.tax_application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaxClientServiceTest {

    @Mock
    private TaxClientRepository taxClientRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SettlementRepository settlementRepository;
    @Mock
    private TaxClientMapper taxClientMapper;

    @InjectMocks
    private TaxClientService taxClientService;

    @Test
    void shouldCreateTaxClient() {
        // GIVEN
        TaxClientCreateDto dto = new TaxClientCreateDto("name", "nip", "address", "city", "state", "zip", "phone", "mail", 1L, List.of(1L));
        User user = new User();
        user.setId(1L);

        Settlement settlement = new Settlement();
        settlement.setId(1L);

        TaxClient taxClient = new TaxClient();
        taxClient.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(settlementRepository.findAllById(dto.getSettlementIds())).thenReturn(List.of(settlement));
        when(taxClientMapper.mapToTaxClient(dto, user, List.of(settlement))).thenReturn(taxClient);
        when(taxClientRepository.save(taxClient)).thenReturn(taxClient);
        when(taxClientMapper.mapToTaxClientResponseDto(taxClient)).thenReturn(new TaxClientResponseDto(1L, "name", "nip", "address", "city", "state", "zip", "phone","mail", 1L, List.of(1L)));

        // WHEN
        TaxClientResponseDto response = taxClientService.createTaxClient(dto);

        // THEN
        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(taxClientRepository, times(1)).save(any());
    }

    @Test
    void shouldGetTaxClientById() {
        // GIVEN
        TaxClient taxClient = new TaxClient();
        taxClient.setId(1L);

        when(taxClientRepository.findById(1L)).thenReturn(Optional.of(taxClient));
        when(taxClientMapper.mapToTaxClientResponseDto(taxClient)).thenReturn(new TaxClientResponseDto(1L, "name", "nip", "address", "city", "state", "zip", "phone","mail", 1L, List.of()));

        // WHEN
        TaxClientResponseDto response = taxClientService.getTaxClientById(1L);

        // THEN
        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(taxClientRepository, times(1)).findById(1L);
    }

    @Test
    void shouldUpdateTaxClient() {
        // GIVEN
        TaxClient existing = new TaxClient();
        existing.setId(1L);
        TaxClientCreateDto dto = new TaxClientCreateDto("updated", "nip", "address", "city", "state", "zip", "phone","mail", 1L, null);

        when(taxClientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(taxClientRepository.save(existing)).thenReturn(existing);
        when(taxClientMapper.mapToTaxClientResponseDto(existing)).thenReturn(new TaxClientResponseDto(1L, "updated", "nip", "address", "city", "state", "zip", "phone","mail", 1L, List.of()));

        // WHEN
        TaxClientResponseDto response = taxClientService.updateTaxClient(1L, dto);

        // THEN
        assertEquals("updated", response.getName());
        verify(taxClientRepository, times(1)).save(existing);
    }

    @Test
    void shouldDeleteTaxClient() {
        // GIVEN
        when(taxClientRepository.existsById(1L)).thenReturn(true);

        // WHEN
        taxClientService.deleteTaxClient(1L);

        // THEN
        verify(taxClientRepository, times(1)).deleteById(1L);
    }
}