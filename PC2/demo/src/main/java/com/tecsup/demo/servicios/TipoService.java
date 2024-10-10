package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Tipo;

import java.util.List;

public interface TipoService {

    public List<Tipo> listar();

    public void grabar(Tipo tipo);

    public Tipo buscar(Integer id);

    public void eliminar(Integer id);

}
