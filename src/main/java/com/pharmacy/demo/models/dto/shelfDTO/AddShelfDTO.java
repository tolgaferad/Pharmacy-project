package com.pharmacy.demo.models.dto.shelfDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
public class AddShelfDTO {
    @NotBlank(message = "Name of shelf cannot be empty")
    private String name;
    @NotNull(message = "Capacity of shelf should be defined")
    private int capacity;
}
