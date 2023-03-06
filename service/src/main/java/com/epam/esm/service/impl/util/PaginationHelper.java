package com.epam.esm.service.impl.util;

import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.ExceptionResult;
import com.epam.esm.exception.IncorrectParameterException;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@UtilityClass
public class PaginationHelper {

    public Pageable createPageRequest(int page, int size) {
        Pageable pageRequest;
        try {
            pageRequest = PageRequest.of(page, size);
        } catch (IllegalArgumentException e) {
            ExceptionResult exceptionResult = new ExceptionResult();
            exceptionResult.addException(ExceptionMessageKey.INVALID_PAGINATION, page, size);
            throw new IncorrectParameterException(exceptionResult);
        }
        return pageRequest;
    }
}
