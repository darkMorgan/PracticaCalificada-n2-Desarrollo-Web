package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.SedeRepository;
import com.tecsup.demo.modelo.entidades.Sede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Sede sede) {
        dao.save(sede);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Sede buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sede> listar() {
        return (List<Sede>)dao.findAll();
    }
}


