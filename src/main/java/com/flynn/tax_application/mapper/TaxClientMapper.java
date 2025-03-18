package com.flynn.tax_application.mapper;

import com.flynn.tax_application.dto.TaxClientCreateDto;
import com.flynn.tax_application.dto.TaxClientResponseDto;
import com.flynn.tax_application.model.Settlement;
import com.flynn.tax_application.model.TaxClient;
import com.flynn.tax_application.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class TaxClientMapper {

    public static TaxClient mapToTaxClient(TaxClientCreateDto taxClientCreateDto, User user, List<Settlement> settlements) {
        TaxClient taxClient = new TaxClient();
        taxClient.setName(taxClientCreateDto.getName());
        taxClient.setNip(taxClientCreateDto.getNip());
        taxClient.setAddress(taxClientCreateDto.getAddress());
        taxClient.setCity(taxClientCreateDto.getCity());
        taxClient.setState(taxClientCreateDto.getState());
        taxClient.setZipCode(taxClientCreateDto.getZipCode());
        taxClient.setPhoneNumber(taxClientCreateDto.getPhoneNumber());
        taxClient.setMail(taxClientCreateDto.getMail());
        taxClient.setUser(user);
        taxClient.setSettlementList(settlements);
        return taxClient;
    }

    public static TaxClientResponseDto mapToTaxClientCreateDto(TaxClient taxClient) {
        List<Long> settlementIds = taxClient.getSettlementList() != null ?
                taxClient.getSettlementList().stream().map(Settlement::getId).collect(Collectors.toList()) :
                List.of();

        return new TaxClientResponseDto(
                taxClient.getId(),
                taxClient.getName(),
                taxClient.getNip(),
                taxClient.getAddress(),
                taxClient.getCity(),
                taxClient.getState(),
                taxClient.getZipCode(),
                taxClient.getPhoneNumber(),
                taxClient.getMail(),
                taxClient.getUser().getId(),
                settlementIds
        );
    }
}
