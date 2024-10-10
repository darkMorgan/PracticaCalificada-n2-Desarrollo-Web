package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Sede;

import java.util.List;

public interface SedeService {

    public List<Sede> listar();

    public void grabar(Sede sede);

    public Sede buscar(Integer id);

    public void eliminar(Integer id);

}
