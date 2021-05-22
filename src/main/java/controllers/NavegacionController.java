package controllers;

import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.RequestMapping; //Para indicar la ruta (también llamado URL) por la cual se va a llamar a este controller. EJ: http:/wikipedia/inicio
import org.springframework.web.bind.annotation.GetMapping; //para indicar que metodo ejecutar cuando se cargue el controller
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.DocumentoModel;

@Controller
@RequestMapping("/documentos")
public class NavegacionController {
	
	public List<DocumentoModel> datosDePrueba(){
		ArrayList<DocumentoModel> datos = new ArrayList<>();
		
		datos.add(new DocumentoModel(1, "Buen libro", "Historia de los sistemas", "Jose", "Informatica", "UNLa") );
		datos.add(new DocumentoModel(2, "Libro aburrido", "Matematicas avanzadas", "Pepe", "Fisica", "UNQui") );
		datos.add(new DocumentoModel(3, "Libro corto", "Literatura", "Luis", "Historia", "UTN") );
		
		return datos;
	}
	
	@GetMapping("") 
	public ModelAndView navegar() {
		ModelAndView model = new ModelAndView("navegacion");
		model.addObject("ListaDocumentos", datosDePrueba() );
		return model;
	}
	
	@GetMapping("filtroID")
	public ModelAndView filtro(@RequestParam long id) {
		ModelAndView model = new ModelAndView("navegacion");
		
		List<DocumentoModel> listaFiltrada = datosDePrueba().stream().filter((documento) -> {return documento.getId()==id;} ).collect(Collectors.toList() );
		model.addObject("ListaDocumentos", listaFiltrada);
		
		return model;
	}
	
}
