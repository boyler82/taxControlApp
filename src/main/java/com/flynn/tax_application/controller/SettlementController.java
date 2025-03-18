package com.flynn.tax_application.controller;

import com.flynn.tax_application.dto.SettlementCreateDto;
import com.flynn.tax_application.dto.SettlementResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settlements")
public class SettlementController {

    @PostMapping
    public ResponseEntity<SettlementResponseDto> createSettlement(@RequestBody SettlementCreateDto settlementCreateDto) {
        return ResponseEntity.ok(new SettlementResponseDto());
    }

    @GetMapping
    public ResponseEntity<List<SettlementResponseDto>> getAllSettlements() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettlementResponseDto> getSettlementById(@PathVariable Long id) {
        return ResponseEntity.ok(new SettlementResponseDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SettlementResponseDto> updateSettlement(@PathVariable Long id, @RequestBody SettlementCreateDto settlementCreateDto) {
        return ResponseEntity.ok(new SettlementResponseDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSettlement(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/extend")
    public ResponseEntity<SettlementResponseDto> extendSettlement(@PathVariable Long id) {
        return ResponseEntity.ok(new SettlementResponseDto());
    }

    @PostMapping("/{id}/done")
    public ResponseEntity<SettlementResponseDto> doneSettlement(@PathVariable Long id) {
        return ResponseEntity.ok(new SettlementResponseDto());
    }
}
