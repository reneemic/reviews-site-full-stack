package org.wecancodeit.reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingTest {

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TestEntityManager entityManager;
	
		
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category>result = categoryRepo.findById(categoryId);
		result.get();
		assertThat(category.getName(), is("category"));
			
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	
	@Test
	public void shouldSaveandLoadReview() {
	Review review = new Review("review name", "description");
	review = reviewRepo.save(review);
	long reviewId = review.getId();
	
	entityManager.flush();
	entityManager.clear();
	
	Optional<Review> result = reviewRepo.findById(reviewId);
	result.get();
	assertThat(review.getName(), is("review name"));
	}
	
	@Test
	public void shouldEstablishReviewtoCategoryRelationships() {
		//category is not the owner categories must be created
		Category food = categoryRepo.save(new Category ("Food Served"));
		Category wine = categoryRepo.save(new Category ("Wine Bar"));
		Category bar = categoryRepo.save(new Category("Bars"));
		Category theme = categoryRepo.save(new Category ("Themes"));
		
		Review review = new Review("Restaurant and Bars", "description", food, wine, bar, theme);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		result.get();
		assertThat(review.getCategories(), containsInAnyOrder(food, wine, bar, theme));
	}

	@Test
	public void shouldFindReviewForCategory() {
		Category food = categoryRepo.save(new Category("food"));
		
		Review theClevelander = reviewRepo.save(new Review("The Clevelander", "description", food));
		Review happyDog = reviewRepo.save(new Review("Happy Dog", "description", food));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewForCategory = reviewRepo.findByCategoriesContains(food);
	}
		
	
		
		

}
