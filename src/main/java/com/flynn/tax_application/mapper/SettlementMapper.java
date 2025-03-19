package com.flynn.tax_application.mapper;

import com.flynn.tax_application.dto.SettlementCreateDto;
import com.flynn.tax_application.dto.SettlementResponseDto;
import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.TaxClient;
import org.springframework.stereotype.Component;

@Component
public class SettlementMapper {

    public Settlement mapToSettlement(SettlementCreateDto settlementCreateDto, TaxClient taxClient) {
        Settlement settlement = new Settlement();
        settlement.setSettlementType(settlementCreateDto.getSettlementType());
        settlement.setUntilWhen(settlementCreateDto.getUntilWhen());
        settlement.setAutoExtend(settlementCreateDto.isAutoExtend());
        settlement.setTaxClient(taxClient);
        settlement.setNotes(settlementCreateDto.getNotes());

        return settlement;
    }

    public SettlementResponseDto mapToSettlementResponseDto(Settlement settlement) {
        return new SettlementResponseDto(
                settlement.getId(),
                settlement.getSettlementType(),
                settlement.isDone(),
                settlement.getYearMonthStamp(),
                settlement.getDoneAt(),
                settlement.getUntilWhen(),
                settlement.isAutoExtend(),
                settlement.getTaxClient().getId(),
                settlement.getNotes()
        );

    }
}
