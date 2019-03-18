package org.wecancodeit.reviewssitefullstack;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {
	
	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private Review review;
	Long reviewId;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private ReviewEntry reviewEntry;
	
	@Mock
	private ReviewEntry anotherReviewEntry;
	
	@Mock
	private ReviewEntryRepository reviewEntryRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException	{
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", review);
		
	}
	
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
		
	}
	
	@Test
	public void ShouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		
		underTest.findOneCategory(arbitraryCategoryId, model);
		
		verify(model).addAttribute("categories", category);
	}

	
	@Test
	public void ShouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	@Test
	public void ShouldAddSingleEntryToModel() throws EntryNotFoundException {
		long arbitraryEntryId = 1;
		when(reviewEntryRepo.findById(arbitraryEntryId)).thenReturn(Optional.of(reviewEntry));
		
		underTest.findOneReviewEntry(arbitraryEntryId, model);
		
		verify(model).addAttribute("entry", reviewEntry);
	}
	
	@Test
	public void ShouldAddAllEntriesToModel() {
		Collection<ReviewEntry> allEntries = Arrays.asList(reviewEntry, anotherReviewEntry);
		when(reviewEntryRepo.findAll()).thenReturn(allEntries);
		
		underTest.findAllReviewEntries(model);
		verify(model).addAttribute("entries", allEntries);
	}
	
	@Test
	public void ShouldAddMoreReviewsToModel() {
		String categoryName = "category name";
		Category newCategory = categoryRepo.findByName(categoryName);
		String reviewName = "new review";
		String reviewDescription ="new review description";
		underTest.addReview(reviewName, reviewDescription, categoryName);
		Review newReview = new Review (reviewName, reviewDescription, newCategory);
		when(reviewRepo.save(newReview)).thenReturn(newReview);
	}
	
	@Test
	public void ShouldBeAbleToRemoveReviewFromModelByName() {
		String reviewName =review.getName();
		when(reviewRepo.findByName(reviewName)).thenReturn(review);
		underTest.deleteReviewByName(reviewName);
		verify(reviewRepo).delete(review);
	}
	
	@Test
	public void ShouldBeAbleToRemoveById() {
		underTest.deleteReviewById(reviewId);
		verify(reviewRepo).deleteById(reviewId);
	}
	
	
}