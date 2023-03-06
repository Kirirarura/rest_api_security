package com.epam.esm.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessageKey {


    public static final String NO_ENTITY = "exception.identifiable.noObject";

    public static final String BAD_SORT_TYPE = "exception.sort.badSortType";

    public static final String BAD_TAG_NAME = "exception.tag.badName";
    public static final String TAG_EXIST = "exception.tag.alreadyExist";
    public static final String TAG_NOT_FOUND = "exception.tag.notFound";

    public static final String BAD_GIFT_CERTIFICATE_NAME = "exception.certificate.badName";
    public static final String GIFT_CERTIFICATE_EXIST = "exception.certificate.alreadyExist";

    public static final String BAD_USER_ID = "exception.order.badUserID";

    public static final String INVALID_PAGINATION = "exception.pagination.invalid";
}
