package egovframework.com.file.dao;

import java.util.List;

import egovframework.com.file.vo.AttachFile;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("FileDAO")
public class FileDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.file.dao.FileDAO";
	
	
	public List<?> selectFileAll(AttachFile params) {
		return (List<?>)selectList(Namespace + ".selectFileAll", params);
	}	
	
	public AttachFile selectFile(AttachFile params) {
		return selectOne(Namespace + ".selectFile", params);
	}
	
	public int insertFile(AttachFile params) {
		return insert(Namespace + ".insertFile", params);
	}
	
	public int deleteFile(AttachFile params) {
		return delete(Namespace + ".deleteFile", params);
	}
	
	public int deleteFileAll(AttachFile params) {
		return delete(Namespace + ".deleteFileAll", params);
	}	


}
