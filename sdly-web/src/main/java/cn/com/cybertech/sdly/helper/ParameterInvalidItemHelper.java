package cn.com.cybertech.sdly.helper;

import cn.com.cybertech.sdly.model.other.ParameterInvalidItem;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * Created by huangkd on 2019/1/23.
 * 无效参数处理工具
 */
public class ParameterInvalidItemHelper {



    public static List<ParameterInvalidItem> convertCVEToParameterInvalidItem(ConstraintViolationException cve){

        if(CollectionUtils.isEmpty(cve.getConstraintViolations())){
            return null;
        }
        List<ParameterInvalidItem> parameterInvalidItems= Lists.newArrayList();
        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        for(ConstraintViolation constraintViolation:constraintViolations){
            ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
            String propertyPath = constraintViolation.getPropertyPath().toString();
            if (propertyPath.contains(".")) {
                String[] propertyPathArr = propertyPath.split("\\.");
                parameterInvalidItem.setFiled(propertyPathArr[propertyPathArr.length - 1]);
            } else {
                parameterInvalidItem.setFiled(propertyPath);
            }
            parameterInvalidItem.setMessage(constraintViolation.getMessage());
            parameterInvalidItems.add(parameterInvalidItem);
        }
        return parameterInvalidItems;
    }


    public static List<ParameterInvalidItem> converBRToParameterInvalidItem(BindingResult bindingResult){
        if (bindingResult == null) {
            return null;
        }

        List<ParameterInvalidItem> parameterInvalidItems = Lists.newArrayList();

        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
            parameterInvalidItem.setFiled(fieldError.getField());
            parameterInvalidItem.setMessage(fieldError.getDefaultMessage());
            parameterInvalidItems.add(parameterInvalidItem);
        }

        return parameterInvalidItems;
    }


}
