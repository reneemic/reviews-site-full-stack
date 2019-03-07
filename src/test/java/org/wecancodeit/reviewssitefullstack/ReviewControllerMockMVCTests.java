package org.wecancodeit.reviewssitefullstack;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMVCTests {
	
	
	@Resource
	private MockMvc mvc;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private ReviewEntryRepository reviewEntryRepo;
	
	@Mock
	private Category category;
	
	@Mock
	private ReviewEntry reviewEntry;
	
	@Mock
	private Category anotherCategory;
	
	
	
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleReviewInModel() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", is(review)));
	}
	
	@Test
	public void shouldRouteToAllReviews() throws Exception {
	    mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllReviewsInModel() throws Exception {
		Collection<Review>  allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	
	@Test
	public void shouldBeOkForSingleCategory() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeNotBeOkForSingleCategory() throws Exception {
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleCategoryInModel() throws Exception {
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(model().attribute("categories", is(category)));
	}
	
	@Test
	public void shouldRouteToAllCategories() throws Exception {
	    mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
	}
	
	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllCategoriesInModel() throws Exception {
		Collection<Category>  allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/show-categories")).andExpect(model().attribute("reviews", is(allCategories)));
	}
	
	
}