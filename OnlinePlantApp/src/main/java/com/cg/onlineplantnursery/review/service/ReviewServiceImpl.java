package com.cg.onlineplantnursery.review.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineplantnursery.review.entity.Review;
import com.cg.onlineplantnursery.review.exception.EntityNotFoundException;

import com.cg.onlineplantnursery.review.repository.IReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	IReviewRepository reviewRepository;

	@Override
	public Review registerReview(Review review) {
		review.setReviewDate(LocalDate.now());
		return reviewRepository.save(review);
	}

	@Override
	public Review getReviewByCustomerName(String customerName) {
		// TODO Auto-generated method stub
		return reviewRepository.getReviewByCustomerName(customerName);
	}

	@Override
	public List<Review> getReviewsBySeedId(int seedId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if (reviewRepository.existsById(seedId)) {
			return reviewRepository.getReviewsBySeedId(seedId);
		} else {
			throw new EntityNotFoundException("No order exists with this key = " + seedId);
		}
	}

	@Override
	public List<Review> getReviewsByPlantId(int plantId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if (reviewRepository.existsById(plantId)) {
			return reviewRepository.getReviewsByPlantId(plantId);
		} else {
			throw new EntityNotFoundException("No order exists with this key = " + plantId);
		}
	}

	@Override
	public List<Review> getReviewByPlanterId(int planterId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if (reviewRepository.existsById(planterId)) {
			return reviewRepository.getReviewsByPlanterId(planterId);
		} else {
			throw new EntityNotFoundException("No order exists with this key = " + planterId);
		}
	}

	@Override
	public List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

	@Override
	public Review getReviewBystarRating(int starRating) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return reviewRepository.getReviewBystarRating(starRating);
	}

	@Override
	public List<Review> getReviewByStarRating(int starRating) {
		// TODO Auto-generated method stub
		return reviewRepository.getReviewByStarRating(starRating);
	}

	@Override
	public List<Review> getReviewByOrderId(int orderId) throws EntityNotFoundException {
		if (reviewRepository.existsById(orderId)) {

			return reviewRepository.getReviewsByOrderId(orderId);
		} else {
			throw new EntityNotFoundException("No order exists with this key = " + orderId);
		}

	}



	@Override
	public List<Review> getRatingByPlantId(int searchplantId, int starRating,String action) {
		List<Review> allreviews = getReviewByPlantId(searchplantId);
		List<Review> dtoList = new ArrayList<>();
		for (Review review : allreviews) {
			int star = review.getStarRating();
			if (action.equalsIgnoreCase("below") && star <= starRating) {
				dtoList.add(review);

			} else if (action.equalsIgnoreCase("above") && star >= starRating) {
				dtoList.add(review);
			}
		}
		return allreviews;
	}

private List<Review> getReviewByPlantId(int plantId) {
	// TODO Auto-generated method stub
	return (List<Review>) reviewRepository.getReviewByStarRating(plantId);
}
	}


