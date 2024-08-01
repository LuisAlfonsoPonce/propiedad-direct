package com.devhive.propiedaddirect.web.app.models.services;

import com.devhive.propiedaddirect.web.app.models.entities.State;

import java.util.List;

public interface IStateService {
    List<State> findAll();
}
