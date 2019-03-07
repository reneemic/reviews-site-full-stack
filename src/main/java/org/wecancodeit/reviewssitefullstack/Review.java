package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.Arrays;

@Entity
public class Review {

	
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@Lob
	private String description;
	
	private String image;
	
	@ManyToMany
	private Collection<Category> categories;
	
	@OneToMany (mappedBy = "review")
	private Collection<ReviewEntry> reviewEntries;
	
	
	public Review() {
	
	}
	
	
	public Review(String name, String description, String image, Category...categories) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.categories = new HashSet<>(Arrays.asList(categories));
		
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

	
	public Collection<Category> getCategories() {
		return categories;
	}
	
	public Collection <ReviewEntry> getReviewEntries() {
		return reviewEntries;
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


