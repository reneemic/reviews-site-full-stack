package org.wecancodeit.reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

@Resource
ReviewRepository reviewRepo;

@Resource
CategoryRepository categoryRepo;

@Resource
ReviewEntryRepository reviewEntryRepo;

private Object newReview;

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
		
		
		
	}
	
	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");
		
		
	}

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value="id") long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);
		
		if(category.isPresent()) {
			model.addAttribute("categories", category.get());
			model.addAttribute("reviews", reviewRepo.findByCategoriesContains(category.get()));
			return "category";
	}
		throw new CategoryNotFoundException();
	}


	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return ("categories");
	}

	@RequestMapping("/entry")
	public String findOneReviewEntry(@RequestParam(value="id") long id, Model model) throws EntryNotFoundException {
		Optional<ReviewEntry> reviewEntry = reviewEntryRepo.findById(id);
		
		if(reviewEntry.isPresent()) {
			model.addAttribute("entry", reviewEntry.get());
			return "entry";
	}
		throw new EntryNotFoundException();
	}

	@RequestMapping("/show-entries")
	public String findAllReviewEntries(Model model) {
		model.addAttribute("entries", reviewEntryRepo.findAll());
		return "all entries";
	}

	@RequestMapping("/add-review")
	public String addReview(String reviewName, String reviewDescription, String categoryName) {
		Category category = categoryRepo.findByName(categoryName);
		Review newReview = reviewRepo.findByName(reviewName);
		if(newReview==null) {
			newReview = new Review(reviewName, reviewDescription, category);
			reviewRepo.save(newReview);
		}
		return "redirect:/show-reviews";
	}

	
	@RequestMapping("/delete-review")
	public String deleteReviewByName(String reviewName) {

		if(reviewRepo.findByName(reviewName) !=null) {
			Review deletedReview = reviewRepo.findByName(reviewName);
			reviewRepo.delete(deletedReview);
		}
		return "redirect:/show-reviews";
	}

	@RequestMapping("/del-review")
	public String deleteReviewById(Long reviewId) {
		
		reviewRepo.deleteById(reviewId);
		
		return "redirect:/reviews";
		
	}

	 
}


