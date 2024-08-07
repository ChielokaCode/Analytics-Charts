package com.chielokacodes.userorgapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalesDTO {
    private String month;
    private Double totalSales;
}
