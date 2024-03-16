package company.model;

import java.util.Map;

import company.domain.CompanyDTO;

public interface CompanyDAO {

	// 기업명검색
	CompanyDTO search_comname(Map<String, String> paraMap);

	// 업종검색
	CompanyDTO search_buss_type(Map<String, String> paraMap);

}
