package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.MateriaModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.ReviewModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IMateriaService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IReviewService;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IUserService;

@Controller
public class DocumentoController {
    @Autowired
    private IDocumentoService documentoService;
    @Autowired
    private IMateriaService materiaService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IReviewService reviewService;

    // Formulario basico de un documento
    @GetMapping("/agregarDocumento/materia")
    public ModelAndView formularioDocumento(@RequestParam("id") long idMateria){
        ModelAndView model = new ModelAndView("agregarDocumento");

        model.addObject("idMateria", idMateria);
        return model;
    }

    // Obtengo la informacion del formulario y agrego el documento, por ahora no tiene mensaje de exito
    @PostMapping("/agregarDocumento/materia")
    public String agregarDocumento(@RequestParam(value = "archivoPDF") MultipartFile archivoPDF, 
        @RequestParam("titulo") String titulo, @RequestParam("descripcion") String descripcion, 
        @RequestParam("id") long idMateria, Principal principal) throws NoSuchAlgorithmException, IOException{
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MateriaModel materiaModel = materiaService.traerMateria(idMateria);
        
        DocumentoModel documentoModel = new DocumentoModel(0L, titulo, descripcion, materiaModel, userDetails.getUsername(), null, "");
        documentoService.agregarDocumento(documentoModel, archivoPDF);

        return "redirect:/misCursos/materia?id=" + materiaModel.getIdMateria();
    }

    @GetMapping("/eliminarDocumento")
    public String eliminarDocumento(@RequestParam("id") long idDocumento){
        DocumentoModel documentoModel = documentoService.traerDocumento(idDocumento);
        documentoService.eliminarDocumento(idDocumento);

        return "redirect:/misCursos/materia?id=" + documentoModel.getMateria().getIdMateria();
    }

    @PostMapping("/agregarReview/documento")
    public String agregarReview(@RequestParam("id") long idDocumento, @RequestParam("valoracionReview") int valoracion,
        @RequestParam("tituloReview") String titulo, @RequestParam("comentarioReview") String comentario){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel autor = userService.traerUserPorUsername(userDetails.getUsername());
        DocumentoModel documentoModel = documentoService.traerDocumento(idDocumento);

        ReviewModel reviewModel = new ReviewModel(0L, valoracion, titulo, comentario, autor, documentoModel);
        reviewService.agregarReview(reviewModel);

        return "redirect:/listadoDocumentos/documento?id=" + idDocumento;
    }
}
