package model;

public class CategoryVO {

	private int level;
	private String cateName;
	private String cateCode;
	private String cateCodeRef;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getCateCodeRef() {
		return cateCodeRef;
	}
	public void setCateCodeRef(String cateCodeRef) {
		this.cateCodeRef = cateCodeRef;
	}
	@Override
	public String toString() {
		return "CategoryVO [level=" + level + ", cateName=" + cateName + ", cateCode=" + cateCode + ", cateCodeRef="
				+ cateCodeRef + "]";
	}
	
	
	
	
}
