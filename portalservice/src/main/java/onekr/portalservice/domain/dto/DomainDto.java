package onekr.portalservice.domain.dto;

public class DomainDto {

	private Boolean available;
	
	private String domain;
	
	private String whoisInfo;
	
	
	private String name;
	
	private String suffix;
	
	
	private String baiduSuggest;
	
	private Boolean recommend;

	
	public String getBaiduSuggest() {
		return baiduSuggest;
	}

	public void setBaiduSuggest(String baiduSuggest) {
		this.baiduSuggest = baiduSuggest;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWhoisInfo() {
		return whoisInfo;
	}

	public void setWhoisInfo(String whoisInfo) {
		this.whoisInfo = whoisInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
