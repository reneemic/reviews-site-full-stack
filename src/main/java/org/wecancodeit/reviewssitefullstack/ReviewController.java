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

@Resource
private TagRepository tagRepo;


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

	@RequestMapping(value = "tags")
	public String getAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
	}

	@RequestMapping("tag")
	public String getATag(@RequestParam Long id, Model model) {
		model.addAttribute("tag", tagRepo.findById(id));
		return "tag";
	}
	}
	 



