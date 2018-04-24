/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.controllers;

import edu.epi.jee.dao.ProductDao;
import edu.epi.jee.entities.ProductEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author HLOL
 */
@ManagedBean
@SessionScoped
public class ProductController {

    @EJB
	private ProductDao productDao ;
	private ProductEntity productEntity  = new ProductEntity();
	private List<ProductEntity> listProducts ;
	private List<ProductEntity> filterProduct ;
	ProductEntity productSelected ;
	
	 @PostConstruct
	    public void init() {
		 listProducts = productDao.findAllProducts();
		 productSelected = new ProductEntity();
	 }
	 public String newProduct(){
			ProductEntity c1 = productDao.create(productEntity);
			listProducts = productDao.findAllProducts();
			productEntity = new ProductEntity();
			return null;
		}
	 public String deleteProduct(){
			return null ;
			
		}
	 public String updateProduct(){
			ProductEntity p2 = productDao.update(productSelected);
			listProducts = productDao.findAllProducts();
			return null ;
		}
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public List<ProductEntity> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<ProductEntity> listProducts) {
		this.listProducts = listProducts;
	}

	public List<ProductEntity> getFilterProduct() {
		return filterProduct;
	}

	public void setFilterProduct(List<ProductEntity> filterProduct) {
		this.filterProduct = filterProduct;
	}

	public ProductEntity getProductSelected() {
		return productSelected;
	}

	public void setProductSelected(ProductEntity productSelected) {
		this.productSelected = productSelected;
	}

}
