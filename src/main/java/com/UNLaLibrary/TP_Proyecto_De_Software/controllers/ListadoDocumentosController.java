package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Review;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IUserService;

@Controller
public class ListadoDocumentosController {
	@Autowired
	private IDocumentoService documentoService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/listadoDocumentos") //Para ver todos los documentos disponibles
	public ModelAndView navegar() {
		ModelAndView model = new ModelAndView("listadoDocumentos");
		model.addObject("listaDocumentos", documentoService.traerDocumentos() );
		return model;
	}
	
	
	@GetMapping("listadoDocumentos/documento") //Para ver cada documento individualmente
	public ModelAndView documentoIndividual(@RequestParam (defaultValue="1", name="id") long id) {
		ModelAndView model = new ModelAndView("documento");
		DocumentoModel documento = documentoService.traerDocumento(id);
		int reviewSize = documento.getReviews().size();
		double promedio = 0.0;
		int reviewSum = 0;
		ArrayList<Integer> estrellas = new ArrayList<Integer>();
		estrellas.add(0);
		estrellas.add(0);
		estrellas.add(0);
		estrellas.add(0);
		estrellas.add(0);

		boolean mostrarFormulario = true;
		UserModel userModel = new UserModel();
		userModel.setId(0);
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userModel = userService.traerUserPorUsername(userDetails.getUsername());
		}

		if(reviewSize > 0){
			for(Review r: documento.getReviews()){
				reviewSum += r.getValoracion();
				estrellas.set(r.getValoracion()-1, estrellas.get(r.getValoracion()-1) + 1);
				if(r.getAutor().getId() == userModel.getId()){
					mostrarFormulario = false;
				}
			}
			promedio = ((double)reviewSum / reviewSize);
		}
		
		model.addObject("documento", documento);
		model.addObject("promedio", promedio);
		model.addObject("estrellas", estrellas);
		model.addObject("reviewSize", reviewSize);
		model.addObject("mostrarFormulario", mostrarFormulario);
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
