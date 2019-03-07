package org.wecancodeit.reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

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
	ReviewEntryRepository reviewEntryRepo;
	
	@Resource
	private TestEntityManager entityManager;
	
		
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category", "image"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category>result = categoryRepo.findById(categoryId);
		result.get();
		assertThat(category.getName(), is("category"));
			
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category", "image"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	
	@Test
	public void shouldSaveandLoadReview() {
	Review review = new Review("review name", "description", "image");
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
		Category food = categoryRepo.save(new Category ("Food Served", "image"));
		Category wine = categoryRepo.save(new Category ("Wine Bar", "image"));
		Category bar = categoryRepo.save(new Category("Bars", "image"));
		Category theme = categoryRepo.save(new Category ("Themes", "image"));
		
		Review review = new Review("Restaurant and Bars", "description","image" ,food, wine, bar, theme);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getCategories(), containsInAnyOrder(food, wine, bar, theme));
	}

	@Test
	public void shouldFindReviewForCategory() {
		
		Category food = categoryRepo.save(new Category("food", "image"));
		
		Review theClevelander = reviewRepo.save(new Review("The Clevelander", "description","image", food));
		Review happyDog = reviewRepo.save(new Review("Happy Dog", "description","image", food));
		Review humbleWineBar = reviewRepo.save(new Review("Humble Wine Bar","description", "image"));
		
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesContains(food);
		
		assertThat(reviewsForCategory, containsInAnyOrder(theClevelander, happyDog));
	}
		
	@Test
	public void shouldFindReviewsForCategoryId() {
		Category wine = categoryRepo.save(new Category("Wine", "image"));
		long categoryId = wine.getId();
		
		Review happyDog = reviewRepo.save(new Review("Happy Dog", "description", "image", wine));
		Review humbleWineBar = reviewRepo.save(new Review("Humble Wine Bar", "description", "image", wine));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review>reviewsForCategory = reviewRepo.findByCategoriesId(categoryId);
		
		assertThat(reviewsForCategory, containsInAnyOrder(happyDog, humbleWineBar));
		
	}
		
		@Test
		public void shouldSaveReviewEntrytoReviewRelationship() {
			Review review = new Review ("name", "description", "image");
			reviewRepo.save(review);
			long reviewId = review.getId();	
			
			ReviewEntry entry = new ReviewEntry("title", review);
			reviewEntryRepo.save(entry);
			
			ReviewEntry entry2 = new ReviewEntry("title two", review);
			reviewEntryRepo.save(entry2);
			
			entityManager.flush();
			entityManager.clear();
			
			Optional<Review>result = reviewRepo.findById(reviewId);
			review = result.get();
			assertThat(review.getReviewEntries(), containsInAnyOrder(entry, entry2));
		}

}
