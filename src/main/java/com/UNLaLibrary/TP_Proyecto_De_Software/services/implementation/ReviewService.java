package com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation;

import com.UNLaLibrary.TP_Proyecto_De_Software.converters.ReviewConverter;
import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Review;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.ReviewModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.repositories.IReviewRepository;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private ReviewConverter reviewConverter;

    public void agregarReview(ReviewModel reviewModel){
        Review review = reviewConverter.modelToEntity(reviewModel);
        reviewRepository.save(review);
    }
}
