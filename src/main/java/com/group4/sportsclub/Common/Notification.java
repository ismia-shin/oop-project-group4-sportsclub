package com.group4.sportsclub.Common;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Notification implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String message;
    protected User fromUser;
    protected User toUser;
    protected LocalDate sentDate;

}
