package org.madnews.utils;

import java.util.List;

import org.madnews.entity.Post;

public class SearchResponseWrapper {
	private int count;
	private List<Post>  result;
	
	public SearchResponseWrapper(int count, List<Post> result) {
		this.count = count;
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Post> getResult() {
		return result;
	}

	public void setResult(List<Post> result) {
		this.result = result;
	}

}
