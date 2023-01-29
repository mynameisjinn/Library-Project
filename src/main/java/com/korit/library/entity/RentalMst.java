package com.korit.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalMst {
    private int rentalId;
    private int userId;
    private LocalDate rentalDate;
    private LocalDate fixedDate;

}
