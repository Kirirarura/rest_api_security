package com.epam.esm.dao.query;

import com.epam.esm.entity.Order;
import lombok.experimental.UtilityClass;

/**
 * Class that contains all queries.
 */
@UtilityClass
public class Queries {

    public static final String FIND_BY_USER_ID_QUERY = "SELECT o FROM " + Order.class.getName()
            + " o WHERE o.user.id = :user_id";
}
