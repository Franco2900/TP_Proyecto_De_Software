package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Review;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.ReviewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {
    @Autowired
    UserConverter userConverter;
    @Autowired
    DocumentoConverter documentoConverter;

    public ReviewModel entityToModel(Review review){
        return new ReviewModel(review.getIdReview(), review.getValoracion(), review.getTitulo(), review.getComentario(),
            userConverter.entityToModel(review.getAutor()), documentoConverter.entityToModel(review.getDocumento()));
    }

    public Review modelToEntity(ReviewModel reviewModel){
        return new Review(reviewModel.getIdReview(), reviewModel.getValoracion(), reviewModel.getTitulo(), reviewModel.getComentario(),
            userConverter.modelToEntity(reviewModel.getAutor()), documentoConverter.modelToEntity(reviewModel.getDocumento()));
    }
}
