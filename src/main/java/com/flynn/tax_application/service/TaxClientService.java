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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxClientService {

    private final TaxClientRepository taxClientRepository;
    private final UserRepository userRepository;
    private final SettlementRepository settlementRepository;
    private final TaxClientMapper taxClientMapper;

    @Autowired
    public TaxClientService(TaxClientRepository taxClientRepository,
                            UserRepository userRepository,
                            SettlementRepository settlementRepository,
                            TaxClientMapper taxClientMapper) {
        this.taxClientRepository = taxClientRepository;
        this.userRepository = userRepository;
        this.settlementRepository = settlementRepository;
        this.taxClientMapper = taxClientMapper;
    }

    public TaxClientResponseDto createTaxClient(TaxClientCreateDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Settlement> settlements = dto.getSettlementIds() != null ?
                (List<Settlement>) settlementRepository.findAllById(dto.getSettlementIds()) :
                List.of();
        TaxClient taxClient = taxClientMapper.mapToTaxClient(dto, user, settlements);
        TaxClient saved = taxClientRepository.save(taxClient);
        return taxClientMapper.mapToTaxClientResponseDto(saved);
    }

    public TaxClientResponseDto getTaxClientById(Long id) {
        TaxClient taxClient = taxClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaxClient not found"));
        return taxClientMapper.mapToTaxClientResponseDto(taxClient);
    }

    public TaxClientResponseDto updateTaxClient(Long id, TaxClientCreateDto dto) {
        TaxClient existing = taxClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaxClient not found"));
        existing.setName(dto.getName());
        existing.setNip(dto.getNip());
        existing.setAddress(dto.getAddress());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setZipCode(dto.getZipCode());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setMail(dto.getMail());
        taxClientRepository.save(existing);
        return taxClientMapper.mapToTaxClientResponseDto(existing);
    }

    public void deleteTaxClient(Long id) {
        if (!taxClientRepository.existsById(id)) {
            throw new RuntimeException("TaxClient not found");
        }
        taxClientRepository.deleteById(id);
    }

}
