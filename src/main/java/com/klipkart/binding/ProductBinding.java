package com.klipkart.binding;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.klipkart.model.ImageModel;

public class ProductBinding {

private Integer id;
	
	private String name;
	
	private String brand;
	
	private String description;
	
	private Integer price;
	
	private ImageModel productImages;
	
	private String category;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public ImageModel getProductImages() {
		return productImages;
	}

	public void setProductImages(ImageModel productImages) {
		this.productImages = productImages;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	 

	
	
}
