package com.example.leave_request.domain.leaveRequest;

import com.example.leave_request.web.leaveRequest.LeaveRequestForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;

public class DayCheckValidation implements ConstraintValidator<DayCheck, Object> {

  private String requestDate;
  private String startDate;
  private String endDate;

  @Override
  public void initialize(DayCheck annotation) {
    this.requestDate = annotation.requestDate();
    this.startDate = annotation.startDate();
    this.endDate = annotation.endDate();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    BeanWrapper beanWrapper = new BeanWrapperImpl(value);
    LocalDate requestDateValue = (LocalDate) beanWrapper.getPropertyValue(requestDate);
    LocalDate startDateValue = (LocalDate) beanWrapper.getPropertyValue(startDate);
    LocalDate endDateValue = (LocalDate) beanWrapper.getPropertyValue(endDate);

    if (requestDateValue == null || startDateValue == null || endDateValue == null) {
      return false;
    }

    // 申請日が開始日より未来の場合
    if(requestDateValue.isAfter(startDateValue)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("申請日は開始日または終了日より前の日付を指定してください")
        .addPropertyNode(requestDate).addConstraintViolation();
      return false;
    }

    // 開始日が終了日より未来の場合
    if(startDateValue.isAfter(endDateValue)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("終了日は開始日よりあとの日付を指定してください")
        .addPropertyNode(endDate).addConstraintViolation();
      return false;
    }

    return true;
  }

}
