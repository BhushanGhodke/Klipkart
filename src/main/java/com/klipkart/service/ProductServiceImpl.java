package com.klipkart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.klipkart.binding.FetchProductBinding;
import com.klipkart.binding.ProductBinding;
import com.klipkart.exception.ProductNotFoundException;
import com.klipkart.model.ImageModel;
import com.klipkart.model.Product;
import com.klipkart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Cloudinary cloudinary;

	@Override
	public Product saveProduct(ProductBinding productBinding) {

		Product p = new Product();
		productBinding.setCategory(productBinding.getCategory().toLowerCase());
		BeanUtils.copyProperties(productBinding, p);

		Product product = productRepository.save(p);

		return product;

	}

	@Override
	public List<FetchProductBinding> getAllProducts() {

		List<Product> productList = productRepository.findAll();

		List<FetchProductBinding> productFetch = new ArrayList<>();

		productList.forEach(x -> {

			FetchProductBinding binding = new FetchProductBinding();

			String imageUrl = x.getProductImages().getImageUrl();

			binding.setImageUrls(imageUrl);

			BeanUtils.copyProperties(x, binding);

			productFetch.add(binding);

		});

		return productFetch;
	}

	@Override
	public ImageModel uploadImage(MultipartFile multipartFile) {

		String imageUrl = null;

		ImageModel imageModel = new ImageModel();

		try {

			Map data = cloudinary.uploader().upload(multipartFile.getBytes(), Map.of());

			imageUrl = (String) data.get("url");

			imageModel.setImageUrl(imageUrl);

			return imageModel;

		} catch (Exception e) {
			throw new RuntimeException("Image Uploading Failed");
		}

	}
	
	
	@Override
	public List<FetchProductBinding> getProductByCategory(String category) {
	
		List<Product> productList = productRepository.findAll();
	
			List<Product> collect = productList.stream()
		
													.filter(product->product.getCategory().equals(category))
													.collect(Collectors.toList());
		
		if(collect.isEmpty()) {
			throw new ProductNotFoundException("No Product Found");
		}
		
		List<FetchProductBinding> productFetch = new ArrayList<>();

		collect.forEach(x -> {

			FetchProductBinding binding = new FetchProductBinding();

			String imageUrl = x.getProductImages().getImageUrl();

			binding.setImageUrls(imageUrl);

			BeanUtils.copyProperties(x, binding);

			productFetch.add(binding);

		});
		
		return productFetch;
		
	}
}
