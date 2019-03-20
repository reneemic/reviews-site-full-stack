package org.wecancodeit.reviewssitefullstack;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;
	private String comment;

	@ManyToOne
	private Review review;

	public Comment() {

	}

	public Comment(String comment, Review review) {
		this.comment = comment;
		this.review = review;
	}

	public long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Review getReview() {
		return review;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return id == ((Comment) obj).id;
	}
}