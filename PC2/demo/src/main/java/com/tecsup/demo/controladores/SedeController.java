package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.daos.SedeRepository;
import com.tecsup.demo.modelo.entidades.Sede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("sede")
public class SedeController {

    @Autowired
    private SedeRepository sedeRepository;

    @RequestMapping(value = "/sede", method = RequestMethod.GET)
    public String listarSedes(Model model) {
        List<Sede> sedes = sedeRepository.findAll(); // Obtener la lista de todas las sedes
        model.addAttribute("sedes", sedes); // Pasar la lista al modelo
        model.addAttribute("titulo", "Listado de Sedes"); // Título para la vista
        return "sede"; // Nombre del archivo HTML para la vista de listado
    }

    @GetMapping("/formSede")
    public String mostrarFormSede(Model model) {
        model.addAttribute("sede", new Sede()); // Crea un objeto vacío de Sede
        return "formSede"; // Nombre del archivo HTML para el formulario
    }

    @PostMapping("/guardarSede")
    public String guardarSede(@Valid @ModelAttribute Sede sede, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "formSede"; // Devuelve a la vista con errores
        }
        sedeRepository.save(sede); // Guarda la sede
        status.setComplete(); // Marca la sesión como completa
        return "redirect:/sede"; // Redirige a la lista de sedes
    }

    @GetMapping("/eliminarSede/{id}")
    public String eliminarSede(@PathVariable("id") int id) {
        sedeRepository.deleteById(id); // Llama al método deleteById del repositorio
        return "redirect:/sede"; // Redirige a la lista de sedes
    }

    @GetMapping("/formSede/{id}")
    public String mostrarEditarSede(@PathVariable("id") int id, Model model) {
        Sede sede = sedeRepository.findById(id).orElse(null); // Obtener la sede por ID
        if (sede != null) {
            model.addAttribute("sede", sede); // Pasar la sede al modelo
            return "formSede"; // Nombre del archivo HTML para el formulario de edición
        }
        return "redirect:/sede"; // Redirige si no se encuentra la sede
    }

    @GetMapping("/sede/ver")
    public String verSede(@RequestParam(required = false) String format, Model model) {
        List<Sede> sedes = sedeRepository.findAll(); // Obtener todas las sedes
        model.addAttribute("sedes", sedes); // Pasar la lista al modelo
        model.addAttribute("titulo", "Lista de Sedes"); // Título para la vista

        if ("pdf".equals(format)) {
            return "sede/ver.pdf"; // Aquí se genera el PDF
        } else if ("xlsx".equals(format)) {
            return "sede/ver.xlsx"; // Aquí se genera el archivo Excel
        }

        return "sede/ver"; // Devuelve la vista normal
    }
}
