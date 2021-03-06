package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String image;
    
	@ManyToMany (mappedBy = "categories")
	private Collection<Review> reviews;

	
	
	public Category() {
	}
    
	
	public Category(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}
	
	public String getImage() {
		return image;
	}
	
	public Long getId() {
		return id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
