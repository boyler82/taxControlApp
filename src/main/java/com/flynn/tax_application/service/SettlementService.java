package com.flynn.tax_application.service;

import com.flynn.tax_application.dto.SettlementCreateDto;
import com.flynn.tax_application.dto.SettlementResponseDto;
import com.flynn.tax_application.mapper.SettlementMapper;
import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.TaxClient;
import com.flynn.tax_application.repository.SettlementRepository;
import com.flynn.tax_application.repository.TaxClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final TaxClientRepository taxClientRepository;
    private final SettlementMapper settlementMapper;

    @Autowired
    public SettlementService(SettlementRepository settlementRepository,
                             TaxClientRepository taxClientRepository,
                             SettlementMapper settlementMapper) {
        this.settlementRepository = settlementRepository;
        this.taxClientRepository = taxClientRepository;
        this.settlementMapper = settlementMapper;
    }

    public SettlementResponseDto createSettlement(SettlementCreateDto dto) {
        TaxClient taxClient = taxClientRepository.findById(dto.getTaxClientId())
                .orElseThrow(() -> new RuntimeException("TaxClient not found"));
        Settlement settlement = settlementMapper.mapToSettlement(dto, taxClient);
        Settlement saved = settlementRepository.save(settlement);
        return settlementMapper.mapToSettlementResponseDto(saved);
    }

    public SettlementResponseDto getSettlementById(Long id) {
        Settlement settlement = settlementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Settlement not found"));
        return settlementMapper.mapToSettlementResponseDto(settlement);
    }

    public SettlementResponseDto updateSettlement(Long id, SettlementCreateDto dto) {
        Settlement settlement = settlementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Settlement not found"));
        settlement.setSettlementType(dto.getSettlementType());
        settlement.setUntilWhen(dto.getUntilWhen());
        settlement.setAutoExtend(dto.isAutoExtend());
        settlement.setNotes(dto.getNotes());
        settlementRepository.save(settlement);
        return settlementMapper.mapToSettlementResponseDto(settlement);
    }

    public void deleteSettlement(Long id) {
        if (!settlementRepository.existsById(id)) {
            throw new RuntimeException("Settlement not found");
        }

        settlementRepository.deleteById(id);
    }
}
