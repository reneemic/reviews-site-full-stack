package org.wecancodeit.reviewssitefullstack;

import org.springframework.data.repository.CrudRepository;

import org.wecancodeit.reviewssitefullstack.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findOne(Long id);

}
	
