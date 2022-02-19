package com.mavericsystems.account.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceDTO {
    @Min(value = 1, message = "should be a valid amount")
    private Integer amountReceived;
    private LocalDateTime localDateTime;

    public UpdateBalanceDTO(Integer amountReceived) {
        this.amountReceived = amountReceived;
    }
}
