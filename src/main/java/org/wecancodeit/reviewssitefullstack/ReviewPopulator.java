package org.wecancodeit.reviewssitefullstack;

import javax.annotation.Resource;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewEntryRepository reviewEntryRepo;

	
	@Override
	public void run(String... args) throws Exception {
		
		Category food = new Category("Food Served", "/images/food.jpg");
		categoryRepo.save(food);

		Category wine = new Category("Wine Served", "/images/wine.jpg");
		categoryRepo.save(wine);

		Category bar = new Category("Bar", "/images/bar.jpg");
		categoryRepo.save(bar);
		
		Category restaurant = new Category("Restaurant", "/images/restaurant.jpg");
		categoryRepo.save(restaurant);

		Category beer = new Category("Beer Served", "/images/beer.jpg");
		categoryRepo.save(beer);
		
		
		Review theClevelander = new Review("The Clevelander", 
				"We stopped in for lunch an ended up spending the majority of our day there before the concert. The girls made us feel welcome and at home from the moment we walked in, we felt like regulars.  Bottles of beer were only $2.50. Awesome wings and huge plate of nachos! Will definitely be back the next time I make it to Cleveland.", 
				"/images/wingsandnachos.jpg", bar, beer);
			   theClevelander = reviewRepo.save(theClevelander);
			   
		Review pjMcIntyres = new Review("PJ McIntyres", 
				"Recommended by hotel and we are so glad to have accepted the recommendation.  Huge fish and chips done perfectly.  Wife had best damn shepards pie...she shared with me. The pretzel appetizer is huge and perfect. Great service Ashley is tremendous and attentive.", 
				"/images/pretzel.jpg", beer, restaurant);
			   pjMcIntyres = reviewRepo.save(pjMcIntyres);
			   
	    Review redLantern = new Review ("Red Lantern",
	    	 "I was impressed with the interior and bar at Red Lantern. The plaza that it's in is nice, but older so I wasn't expecting the restaurant to be so updated. Brunch was very tasty and well displayed. Their Bloody Mary needs minor work, but still worth trying. I'll definitely be returning to this establishment!",  
	    	 "/images/bloodymary.jpg", restaurant, bar, wine, food);
	           redLantern = reviewRepo.save(redLantern);
	           
	    Review happyDog = new Review ("Happy Dog", 
	    		"Happy Dog is a ton of fun (although fruit loops on a hot dog is a little too  much for me). The make-your-own dog approach is novel, but what I enjoy most are the speakers and events they've hosted over the years. The underdog is a fun tucked-away venue and they truly do have an underlying social consciousness that I value.", 
	    		"/images/happydog.jpg", food, beer, bar);
	    	   happyDog = reviewRepo.save(happyDog);
	    	   
	    Review humbleWineBar = new Review ("Humble Wine Bar",
	    		"Happy Dog is a ton of fun (although fruit loops on a hot dog is a little too  much for me). The make-your-own dog approach is novel, but what I enjoy most are the speakers and events they've hosted over the years. The underdog is a fun tucked away venue and they truly do have an underlying social consciousness that I value.", 
	    		"images/wine.jpg", wine, bar, food);
	    	   humbleWineBar = reviewRepo.save(humbleWineBar);
	    	   
    	reviewEntryRepo.save(new ReviewEntry("The Clevelander", theClevelander));
    	reviewEntryRepo.save(new ReviewEntry("Pj McIntyres", pjMcIntyres));	
    	reviewEntryRepo.save(new ReviewEntry("Red Lantern", redLantern));
    	reviewEntryRepo.save(new ReviewEntry("Happy Dog", happyDog));
    	reviewEntryRepo.save(new ReviewEntry("Humble Wine Bar", humbleWineBar));
	
	}

}
