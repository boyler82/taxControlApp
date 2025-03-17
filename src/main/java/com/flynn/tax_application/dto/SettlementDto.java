package com.flynn.tax_application.dto;

import com.flynn.tax_application.model.SettlementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementDto {

    private int id;
    private SettlementType settlementType;
    private boolean isDone = false;
    private String yearMonthStamp;
    private LocalDateTime doneAt;
    private LocalDateTime untilWhen;
    private boolean autoExtend = false;
    private Long taxClientId;
    private String notes;
}
