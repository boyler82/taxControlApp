package com.flynn.tax_application.controller;

import com.flynn.tax_application.dto.TaxClientCreateDto;
import com.flynn.tax_application.dto.TaxClientResponseDto;
import com.flynn.tax_application.mapper.TaxClientMapper;
import com.flynn.tax_application.model.TaxClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxclients")
public class TaxClientController {

    @PostMapping
    public ResponseEntity<TaxClientResponseDto> createClient(@RequestBody TaxClientCreateDto taxClientCreateDto) {
        TaxClient taxClient = TaxClientMapper.mapToTaxClient(taxClientCreateDto, null, null);
        TaxClientResponseDto responseDto = TaxClientMapper.mapToTaxClientCreateDto(taxClient);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxClientResponseDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(new TaxClientResponseDto());
    }

    @GetMapping
    public ResponseEntity<List<TaxClientResponseDto>> getAllClients() {
        return ResponseEntity.ok(List.of());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxClientResponseDto> updateClient(@PathVariable Long id, @RequestBody TaxClientCreateDto taxClientCreateDto) {
        return ResponseEntity.ok(new TaxClientResponseDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }


}
