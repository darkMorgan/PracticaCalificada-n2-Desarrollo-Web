package com.tecsup.demo.controladores;


import org.springframework.web.bind.annotation.GetMapping;

import com.tecsup.demo.modelo.entidades.Tipo;
import com.tecsup.demo.servicios.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;



import java.util.List;
import com.tecsup.demo.modelo.daos.SedeRepository;
import com.tecsup.demo.modelo.entidades.Sede;



@Controller
@SessionAttributes("tipo")
public class TipoController {

    @Autowired
    private TipoService servicio;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de tipos");
        model.addAttribute("tipos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Tipo tipo = new Tipo();
        model.put("tipo", tipo);
        model.put("titulo", "Formulario de Tipo");
        return "formView";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Tipo tipo = null;

        if (id > 0) {
            tipo = servicio.buscar(id);
        } else {
            return "redirect:/listar";
        }
        model.put("tipo", tipo);
        model.put("titulo", "Editar Tipo");
        return "formView";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Tipo tipo, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Tipo");
            return "formView";
        }
        servicio.grabar(tipo);
        status.setComplete();
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            servicio.eliminar(id);
        }
        return "redirect:/listar";
    }

    @GetMapping("/tipo")
    public String ver(Model model) {
        model.addAttribute("tipos", servicio.listar()); // Asegúrate de que sea "tipos" aquí
        model.addAttribute("titulo", "Lista de tipos");
        return "tipo/ver"; // Asegúrate de que esta vista exista en la carpeta correcta
    }

    @GetMapping("/tipo/ver")
    public String verTipo(@RequestParam(required = false) String format, Model model) {
        model.addAttribute("tipos", servicio.listar()); // Cambiado de "tipo" a "tipos"
        model.addAttribute("titulo", "Lista de tipos");

        if ("pdf".equals(format)) {
            return "tipo/ver.pdf"; // Aquí se genera el PDF
        } else if ("xlsx".equals(format)) {
            return "tipo/ver.xlsx"; // Aquí se genera el archivo Excel
        }

        return "tipo/ver"; // Devuelve la vista normal
    }

    //para la vista sede

}