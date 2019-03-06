package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategoriesContains(Category food);

}
