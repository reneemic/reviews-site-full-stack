package org.wecancodeit.reviewssitefullstack;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	Comment findByComment(String comment);

	Comment findOne(Long commentId);
}
