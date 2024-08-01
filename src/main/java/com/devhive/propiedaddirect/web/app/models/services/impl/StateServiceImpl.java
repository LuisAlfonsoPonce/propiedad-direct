package com.devhive.propiedaddirect.web.app.models.services.impl;

import com.devhive.propiedaddirect.web.app.models.daos.IStateDao;
import com.devhive.propiedaddirect.web.app.models.entities.State;
import com.devhive.propiedaddirect.web.app.models.services.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateServiceImpl implements IStateService {

    @Autowired
    private IStateDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public List<State> findAll() {
        return (List<State>) clientDao.findAll();
    }
}
