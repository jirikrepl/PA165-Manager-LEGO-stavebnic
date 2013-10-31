package cz.muni.fi.PA165.dao;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException{

    public DaoException(String msg) {
        super(msg);
    }

}
