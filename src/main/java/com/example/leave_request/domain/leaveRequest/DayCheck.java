package com.example.leave_request.domain.leaveRequest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DayCheckValidation.class)
//@Target({ElementType.FIELD, ElementType.PARAMETER})
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DayCheck.List.class)

public @interface DayCheck {
  String message() default "終了日は開始日よりあとを入力してね^^";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.LOCAL_VARIABLE })
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    DayCheck[] value();
  }
}