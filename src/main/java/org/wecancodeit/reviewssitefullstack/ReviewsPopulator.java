package org.wecancodeit.reviewssitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.reviewssitefullstack.Category;
import org.wecancodeit.reviewssitefullstack.Review;
import org.wecancodeit.reviewssitefullstack.Tag;

@Component
public class ReviewsPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	private Category createCategory(String catName) {
		Category c = new Category(catName);
		return categoryRepo.save(c);
	}

	private Tag createTag(String tagName) {
		Tag t = new Tag(tagName);
		return tagRepo.save(t);
	}

	@Override
	public void run(String... args) throws Exception {
		Category food = createCategory("Food Served");
		Category wine = createCategory("Wine Served");
		Category bar = createCategory("Bar");
		Category restaurant = createCategory("Restaurant");
		Category beer = createCategory("Beer Served");
			
		Tag tag1 = createTag("hot dog");
		Tag tag2 = createTag("up beat");
		Tag tag3 = createTag("Creative Toppings");
		Tag tag4 = createTag("fun");
		Tag tag5 = createTag("happy hour");
		Tag tag6 = createTag("charming");
		Tag tag7 = createTag("great service");

	
	 reviewRepo.save(new Review(bar, "The Clevelander", "images/wingsandnachos.jpg",  "Was in town for a concert with a group of friends." + 
	 "  We stopped in for lunch an ended up spending the majority of our day there before the concert.  " + 
	 "The girls made us feel welcome and at home from the moment we walked in, we felt like regulars.  " + 
	 "Bottles of beer were only $2.50. Awesome wings and huge plate of nachos!  " + 
	 "Will definitely be back the next time I make it to Cleveland.", tag2, tag4, tag7));
	 
	 reviewRepo.save(new Review(wine, "Humble Wine Bar", "images/wine.jpg", "Spacious patio and very affordable happy hour menu full of $5 items." +  
	 "  The interior is clean and charming with a stone pizza oven as its centerpiece." + 
	 "  Loved our build-your-own cheese plate. Our server was attentive and informative, too." +  
	 "  When the wine I ordered was unavailable, she came prepared with a sample of her suggested substitute", tag5, tag2, tag7));
	 
	 reviewRepo.save(new Review(restaurant, "Red Lantern", "images/wine.jpg", "I was impressed with the interior and bar at Red Lantern.  " + 
	 "The plaza that it's in is nice, but older so I wasn't expecting the restaurant to be so updated.  " +  
	 "Brunch was very tasty and well displayed.  " + 
	 "Their Bloody Mary needs minor work, but still worth trying. "
	 + "I'll definitely be returning to this establishment!" , tag7, tag2));

	 reviewRepo.save(new Review(food, "Happy Dog", "images/happydog.jpg", "Happy Dog is a ton of fun (although fruit loops on a hot dog is a little too  much for me)." + 
	 "The make-your-own dog approach is novel, but what I enjoy most are the speakers and events they've hosted over the years." + 
	  "The underdog is a fun tucked-away venue and they truly do have an underlying social consciousness that I value." ,tag1, tag6, tag4));
	 
	 reviewRepo.save(new Review(beer, "PJ McIntyres", "images/pretzel.jpg", "Recommended by hotel and we are so glad to have accepted the recommendation." + 
	 "Huge fish and chips done perfectly.  Wife had best damn shepards pie...she shared with me." + 
	 "The pretzel appetizer is huge and perfect. Great service Ashley is tremendous and attentive.", tag7));
	}
	
}