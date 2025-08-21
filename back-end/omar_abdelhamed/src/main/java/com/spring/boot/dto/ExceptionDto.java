package com.spring.boot.dto;

import com.spring.boot.dto.BundleMessage.BundleMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    private BundleMessage bundleMessage;
}
