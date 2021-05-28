package controllers;

import org.springframework.stereotype.Controller; //Para indicar que esta clase es un Controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; //Para indicar la ruta (también llamado URL) por la cual se va a llamar a este controller y sus metodos. EJ: http:/wikipedia/inicio
import org.springframework.web.servlet.ModelAndView; //Para poder usar mezclar las vistas html con los modelos

import models.DocumentoModel;

import org.springframework.web.bind.annotation.RequestParam;//Para pedir parametros y trabajar con ellos en los ModelAndView

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NavegacionController {
	
	public List<DocumentoModel> datosDePrueba(){
		ArrayList<DocumentoModel> datos = new ArrayList<>();
		
		datos.add(new DocumentoModel(1, "La primera computadora", "Buen libro", "Historia de los sistemas", "Jose", "Informatica", "Desarrollo Productivo y Tecnológico", "UNLa", "") );
		datos.add(new DocumentoModel(2, "Ecuaciones y ecuaciones", "Libro aburrido", "Matematicas avanzadas", "Pepe", "Fisica", "Desarrollo Productivo y Tecnológico", "UNQui", "") );
		datos.add(new DocumentoModel(3, "Historia de la literatura", "Libro denso", "Literatura", "Luis", "Historia", "Humanidades y Artes", "UTN", "") );
		
		return datos;
	}
	
	
	@RequestMapping("/listadoDocumentos") //Para ver todos los documentos disponibles
	public ModelAndView navegar() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		model.addObject("ListaDocumentos", datosDePrueba() );
		return model;
	}
	
	
	@GetMapping("listadoDocumentos/documento") //Para ver cada documento individualmente
	public ModelAndView documentoIndividual(@RequestParam (defaultValue="1", name="id") long id) {
		ModelAndView model = new ModelAndView("documento");
		
		List<DocumentoModel> documentoFiltrado = new ArrayList<DocumentoModel>();
		for(DocumentoModel documento: datosDePrueba() ) {
			if(documento.getId() == id) {
				documentoFiltrado.add(documento);
				break;
			}
		}
		
		//Codigo para filtrar por id según el tuto que paso Fede
		//List<DocumentoModel> documentoFiltrado = this.datosDePrueba().stream().filter((documento)->{return documento.getId()==id;}).collect(Collectors.toList());
		
		model.addObject("documento", documentoFiltrado.get(0) );
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos") //Para ver los departamentos
	public ModelAndView navegarDepartamentos() {
		ModelAndView model = new ModelAndView("filtrarPorDepartamento");
		return model;
	}
	
	
	@GetMapping("/listadoDepartamentos/Productivo_Tecnologico") //Para ver los documentos del departamento Desarrollo Productivo y Tecnológico
	public ModelAndView Productivo_Tecnologico() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: datosDePrueba() ) {
			if(documento.getDepartamento().equals("Desarrollo Productivo y Tecnológico") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/Humanidades_Artes") //Para ver los documentos del departamento Humanidades y Artes
	public ModelAndView Humanidades_Artes() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: datosDePrueba() ) {
			if(documento.getDepartamento().equals("Humanidades y Artes") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/Politicas_Publicas") //Para ver los documentos del departamento Planificación y Políticas Públicas
	public ModelAndView Politicas_Publicas() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: datosDePrueba() ) {
			if(documento.getDepartamento().equals("Planificación y Políticas Públicas") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
	@GetMapping("/listadoDepartamentos/Salud") //Para ver los documentos del departamento Salud Comunitaria
	public ModelAndView Salud() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		
		List<DocumentoModel> listaDocumentosFiltrados = new ArrayList<DocumentoModel>();
		
		for(DocumentoModel documento: datosDePrueba() ) {
			if(documento.getDepartamento().equals("Salud Comunitaria") ) {
				listaDocumentosFiltrados.add(documento);
			}
		}
		
		model.addObject("ListaDocumentos", listaDocumentosFiltrados);
		return model;
	}
	
}
