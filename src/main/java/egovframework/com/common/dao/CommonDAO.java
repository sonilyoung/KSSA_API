package egovframework.com.common.dao;

import egovframework.com.common.vo.Common;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CommonDAO")
public class CommonDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.common.dao.CommonDAO";

	
	public Common selectSeq(Common params) {
		return selectOne(Namespace + ".selectSeq", params);
	}	
}
