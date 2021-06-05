package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;

@Controller
public class ListadoDocumentosController {
	
	@Autowired
	private IDocumentoService documentoService;
	
	@RequestMapping("/listadoDocumentos") //Para ver todos los documentos disponibles
	public ModelAndView navegar() {
		ModelAndView model = new ModelAndView("listadoDocumentosUniversal");
		model.addObject("listaDocumentos", documentoService.traerDocumentos() );
		return model;
	}
	
	
	@GetMapping("listadoDocumentos/documento") //Para ver cada documento individualmente
	public ModelAndView documentoIndividual(@RequestParam (defaultValue="1", name="id") long id) {
		ModelAndView model = new ModelAndView("documento");
		
		List<DocumentoModel> documentoFiltrado = new ArrayList<DocumentoModel>();
		for(DocumentoModel documento: documentoService.traerDocumentos() ) {
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
	
	@GetMapping("listadoDocumentos/documento/descarga") // Para descargar un archivo en particular
	public void descargarDocumento(@RequestParam (defaultValue="1", name="id") long id, HttpServletResponse response){
		try{
			response.setContentType("application/pdf");
			InputStream is = documentoService.descargarDocumento(id);
			FileCopyUtils.copy(is, response.getOutputStream());
      		response.flushBuffer();
		}
		catch(IOException ex){
      		throw new RuntimeException("IOError writing file to output stream", ex);
		}
	}
	
	
}
