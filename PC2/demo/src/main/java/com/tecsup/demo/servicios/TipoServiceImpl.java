package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.TipoRepository;
import com.tecsup.demo.modelo.entidades.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class TipoServiceImpl implements TipoService {

    @Autowired
    private TipoRepository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Tipo tipo) {
        dao.save(tipo);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Tipo buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tipo> listar() {
        return (List<Tipo>)dao.findAll();
    }
}


