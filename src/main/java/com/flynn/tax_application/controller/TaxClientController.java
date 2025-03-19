package com.flynn.tax_application.controller;

import com.flynn.tax_application.dto.TaxClientCreateDto;
import com.flynn.tax_application.dto.TaxClientResponseDto;
import com.flynn.tax_application.service.TaxClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxclients")
@RequiredArgsConstructor
public class TaxClientController {

    private final TaxClientService taxClientService;

    @PostMapping
    public ResponseEntity<TaxClientResponseDto> createClient(@RequestBody TaxClientCreateDto taxClientCreateDto) {
        TaxClientResponseDto responseDto = taxClientService.createTaxClient(taxClientCreateDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxClientResponseDto> getClientById(@PathVariable Long id) {
        TaxClientResponseDto responseDto = taxClientService.getTaxClientById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<TaxClientResponseDto>> getAllClients() {
        // Na później: jak zaimplementujesz w serwisie metodę getAllClients()
        return ResponseEntity.ok(List.of());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxClientResponseDto> updateClient(@PathVariable Long id, @RequestBody TaxClientCreateDto taxClientCreateDto) {
        TaxClientResponseDto responseDto = taxClientService.updateTaxClient(id, taxClientCreateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        taxClientService.deleteTaxClient(id);
        return ResponseEntity.ok().build();
    }
}