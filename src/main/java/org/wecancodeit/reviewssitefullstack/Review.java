package org.wecancodeit.reviewssitefullstack;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@Lob
	private String description;
	private String image;
	
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	@ManyToMany
	private Collection <Category> category;

	@ManyToMany
	private Collection<Tag> tags;

	
	@OneToMany (mappedBy = "review")
	private Collection<ReviewEntry> reviewEntries;
	
		
	public Review() {
	
	}
		
	public Review(String name, String description, String image, Category...categories, Tag...tags) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.category = (Collection<Category>) category;
		this.tags = new HashSet<>(asList(tags));
		
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public Collection<Comment> getComments() {
		return comments;
	}
  
	public Collection<Category> getCategories() {
		return category;
	}
	public Collection <ReviewEntry> getReviewEntries() {
		return reviewEntries;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

	

	

	
		
	
	}


