package com.briup.estore.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.estore.bean.Category;

public class CategoryEX implements Serializable{
	private static final long serialVersionUID = 1L;
	    private Integer id;

	    private String name;

	    private String description;

	    private Integer parentId;
	    //   一个一级分类对应多个二级分类
	    private List<Category> categorise;
	    

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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

		public List<Category> getCategorise() {
			return categorise;
		}

		public void setCategorise(List<Category> categorise) {
			this.categorise = categorise;
		}

	    
	}
	

