package egovframework.com.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.common.dao.CommonDAO;
import egovframework.com.common.vo.Common;
import lombok.extern.log4j.Log4j2;


/**
 * 사용자관리에 관한 비지니스 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2014.12.08	 이기하			암호화방식 변경(OfficeFileScrty.encryptPassword)
 *
 *      </pre>
 */
@Log4j2
@Service("commonService")
@SuppressWarnings("unchecked")
public class CommonServiceImpl implements CommonService {

    @Resource(name = "CommonDAO")
	private CommonDAO commonDAO;

	@Override
	public Common selectSeq(Common params) {
		// TODO Auto-generated method stub
		return commonDAO.selectSeq(params);
	}
    


}
