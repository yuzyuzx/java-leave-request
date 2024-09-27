package com.example.leave_request.domain.leaveRequest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DayCheckValidation.class})
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)

public @interface DayCheck {
  String message() default "Default message";

  String requestDate();
  String startDate();
  String endDate();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}