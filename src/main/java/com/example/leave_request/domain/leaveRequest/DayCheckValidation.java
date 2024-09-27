package com.example.leave_request.domain.leaveRequest;

import com.example.leave_request.web.leaveRequest.LeaveRequestForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;

public class DayCheckValidation implements ConstraintValidator<DayCheck, LeaveRequestForm> {

  private String message;
  private String startDate;
  private String endDate;

//  @Override
  public void initialize(DayCheck constraintAnnotation) {
    this.message = constraintAnnotation.message();
    this.startDate = "startDate";
    this.endDate = "endDate";
  }

//  @Override
  public boolean isValid(LeaveRequestForm value, ConstraintValidatorContext context) {
    BeanWrapper beanWrapper = new BeanWrapperImpl(value);
    Object startDate = beanWrapper.getPropertyValue(this.startDate);
    Object endDate = beanWrapper.getPropertyValue(this.endDate);

    if (value.getRequestDate() == null || value.getStartDate() == null || value.getEndDate() == null) { //(2)
      return false;
    }

    if(value.getStartDate().isAfter(value.getEndDate())) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message).addPropertyNode(this.endDate).addConstraintViolation();
      return false;

    }

    return true;

//    return value.getStartDate().isBefore(value.getEndDate());
  }
}
