package com.spring.boot.controller.vm;

import com.spring.boot.dto.BundleMessage.BundleMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExceptionResponseVm {

    private List<BundleMessage> errorMessages;



    public ExceptionResponseVm(List<BundleMessage> errorMessages ) {
        this.errorMessages = errorMessages;

    }
}
