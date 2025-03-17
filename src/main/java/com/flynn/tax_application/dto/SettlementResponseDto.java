package com.flynn.tax_application.dto;

import com.flynn.tax_application.model.SettlementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementResponseDto {

    private Long id;
    private SettlementType settlementType;
    private boolean isDone;
    private String yearMonthStamp;
    private LocalDateTime doneAt;
    private LocalDateTime untilWhen;
    private boolean autoExtend;
    private Long taxClientId;
    private String notes;
}
