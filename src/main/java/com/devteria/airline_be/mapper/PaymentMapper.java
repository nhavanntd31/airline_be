package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.PaymentRequest;
import com.devteria.airline_be.dto.response.PaymentResponse;
import com.devteria.airline_be.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toPayment(PaymentRequest request);

    PaymentResponse toPaymentResponse(Payment payment);

    void updatePayment(@MappingTarget Payment payment, PaymentRequest request);
}
