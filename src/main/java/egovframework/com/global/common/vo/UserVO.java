package egovframework.com.global.common.vo;

import java.util.Date;

/**
 * 사용자VO클래스로서 사용자관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		    수정자           수정내용
 *  -------------    --------    ---------------------------
 *  2009.04.10  	   조재영           최초 생성
 *  2017.04.07        suji.h     uniqID->userUniqId, userLoginId->userLoginId,
 *  							 userNm->userNm, emplyNo->employeeNo 변경
 *  							 userTy 삭제 
 * </pre>
 */
public class UserVO extends UserDefaultVO{

	private static final long serialVersionUID = 3640820362821490939L;

	/* 이전비밀번호 - 비밀번호 변경시 사용 */
    private String oldPassword;
    
    /* 비밀번호 확인 - 비밀번호 변경시 사용 */
    private String confirmPassword;
    
    /* 가입일(Date) */
	private String sbscrbDe ;
	
	/* 사용자고유아이디 */
	private String userUniqId;
	
	/* 지역번호 */
	private String areaNo ;
	
	/* 생일 */
	private String brth ;
	
	/* 상세주소 */
	private String detailAdres;
	
	/* 이메일주소 */
	private String emailAdres;
	
	/* 사원번호 */
	private String employeeNo;
	
	/* 로그인 ID */
	private String userLoginId ;
	
	/* 사용자 명 */
	private String userNm;
	
	/* 사용자 상태 */
	private String userSttusCode="P";
	
	/* 팩스번호 */
	private String fxnum;
	
	/* 기관(회사)ID */
	private String companyId;
	
	/* 기관(회사)명 */
	private String companyNm;
	
	/* DDP Code */
	private String companyCd;
	
	/* 그룹 ID */
	private String groupId ;
	
	/* 집 주소 */
	private String homeadres;
	
	/* 집끝전화번호 */
	private String homeendTelno ;
	
	/* 집중간전화번호 */
	private String homemiddleTelno ;
	
	/* 주민등록번호 */
	private String ihidnum;
	
	/* 소속기관코드 */
	private String insttCode;
	
	/* 핸드폰번호 */
	private String moblphonNo;
	
	/* 사무실전화번호 */
	private String offmTelno;
	
	/* 조직(부서) ID */
	private String orgnztId;
	
	/* 조직(부서) 명  */
	private String orgnztNm;
	
	/* 비밀번호 */
	private String password;
	
	/* 비밀번호 정답 */
	private String passwordCnsr ;
	
	/* 비밀번호 힌트 */
	private String passwordHint;
	
	/* 결재 비밀번호  */
	private String approvalPassword;
	
	/* 이전 결재 비밀번호 - 결재 비밀번호 변경시 사용 */
	private String oldApprovalPassword;
	
	/* 검색조건 가입일자 시작일 */
	private String sbscrbDeBegin;
	
	/* 검색조건 가입일자 종료일 */
	private String sbscrbDeEnd;
	
	/* 성별코드 */
	private String sexdstnCode;
	
	/* 우편번호 */
	private String zip ;
	
	/* DN 값 */
	private String subDn;
	
	/* 순번 */
	private int seq;
	
	/* 직위ID */
	private String posiId;
	
	/* 직위명 */
	private String posiNm;
	
	/* 직책ID */
	private String dutyId;
	
	/* 직책명 */
	private String dutyNm;
	
	/* 보안레벨 */
	private int securityLvl;
	
	/* 설명 */
	private String userDesc;
	
	/* 사용자 유형(System admin/Admin/Department admin/User) */
	private String userType;
	
	/* 부재설정 */
	private String userAbsF="0";
	
	/* 프로필 이미지 Path */
    private String photoPath ;
    private String photoDeleteFlag;
    
    /* 서명 이미지 Path */
	private String signPath;
	private String signDeleteFlag;
	
	/* 수정일자 */
	private String updateDt;
	
	/* 비밀번호 수정일자 */
	private String userLpwdDt;
	
	/* 담당 부서 ID */
	private String deptBoxId;
	
	/* 담당 부서 명 */
	private String deptBoxNm;
	
	/* 수신 담당 부서 ID */
	private String deptRboxId;
	
	/* 수신 담당 부서 명  */
	private String deptRboxNm;
	
	/* 발신 담당 부서 ID */
	private String deptSboxId;
	
	/* 발신 담당 부서 명 */
	private String deptSboxNm;
	
	/* display 유형 */
	private String displayTypeCd;
	
	/* update 유형 */
	private String updateType;
	
	/* zimbra 로그인용 비번저장 */
	private String emailPw;
	
	/* zimbra 비번변경시 필요 */
	private String emailOldPwd;
	
	/* zimbra 비번변경시 필요 */
	private String emailNewPwd;

	/* cert DN */
	private String cert;
	
	/* 결재인증타입 (1: Apv Password, 2: PKI)*/
	private String scrtyApprTy;	
	
	/* 검색 키워드 (EMAIL에서 사용) */
	private String keyWord;
	
	/* E-document 타부서 public folder 조회 권한 */
	private String deptViewAuthF;
	
	/* E-document private folder 최대 용량 */
	private long edocQuota;
	
	/* 부재설정 ID */
	private String absId;
	
	/* 메일 부재 설정 여부 */
	private int mailF;
	
	/* 대리결재자 지정 여부  */
	private int apprF;
	
	/* 대리결재자 ID */
	private String apprDepuUserUniqId;
	
	/* 대리결재자 명 */
	private String apprDepuUserUniqNM;
	
	/*  */
	private String esntlId;
	/*  */
	private String notiTypeCd;
	
	/*  */
	private Date startDt;
	/*  */
	private Date endDt;
	/*  */
	private String mailMsg;
	
	private String orgnztParId;
	
	private String dutyCd;
	
	private String sortField;
	
	private String sort;
	
	// 현재 workspace Id
	private String wspId;

	private String skin;

	/**
     * deptBoxNm attribute를 리턴한다.
     * @return String deptBoxNm
     */
	public String getDeptBoxNm() {
		return deptBoxNm;
	}
    /**
     * deptBoxNm attribute 값을 설정한다.
     * @param String deptBoxNm
     */
	public void setDeptBoxNm(String deptBoxNm) {
		this.deptBoxNm = deptBoxNm;
	}
    /**
     * deptRboxNm attribute를 리턴한다.
     * @return String deptRboxNm
     */
	public String getDeptRboxNm() {
		return deptRboxNm;
	}
    /**
     * deptRboxNm attribute 값을 설정한다.
     * @param String deptRboxNm
     */
	public void setDeptRboxNm(String deptRboxNm) {
		this.deptRboxNm = deptRboxNm;
	}
    /**
     * deptSboxNm attribute를 리턴한다.
     * @return String deptSboxNm
     */
	public String getDeptSboxNm() {
		return deptSboxNm;
	}
    /**
     * deptSboxNm attribute 값을 설정한다.
     * @param String deptSboxNm
     */
	public void setDeptSboxNm(String deptSboxNm) {
		this.deptSboxNm = deptSboxNm;
	}
    /**
     * deptBoxId attribute를 리턴한다.
     * @return String deptBoxId
     */
	public String getDeptBoxId() {
		return deptBoxId;
	}
    /**
     * deptBoxId attribute 값을 설정한다.
     * @param String deptBoxId
     */
	public void setDeptBoxId(String deptBoxId) {
		this.deptBoxId = deptBoxId;
	}
    /**
     * deptRboxId attribute를 리턴한다.
     * @return String deptRboxId
     */
	public String getDeptRboxId() {
		return deptRboxId;
	}
    /**
     * deptRboxId attribute 값을 설정한다.
     * @param String deptRboxId
     */
	public void setDeptRboxId(String deptRboxId) {
		this.deptRboxId = deptRboxId;
	}
    /**
     * deptSboxId attribute를 리턴한다.
     * @return String deptSboxId
     */
	public String getDeptSboxId() {
		return deptSboxId;
	}
    /**
     * deptSboxId attribute 값을 설정한다.
     * @param String deptSboxId
     */
	public void setDeptSboxId(String deptSboxId) {
		this.deptSboxId = deptSboxId;
	}
	/**
	 * oldPassword attribute 값을  리턴한다.
	 * @return String oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * oldPassword attribute 값을 설정한다.
	 * @param String oldPassword
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * confirmPassword attribute 값을  리턴한다.
	 * @return String confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * confirmPassword attribute 값을 설정한다.
	 * @param String confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/**
	 * sbscrbDe attribute 값을  리턴한다.
	 * @return String sbscrbDe
	 */
	public String getSbscrbDe() {
		return sbscrbDe;
	}
	/**
	 * sbscrbDe attribute 값을 설정한다.
	 * @param String sbscrbDe 
	 */
	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}
	/**
	 * userUniqId attribute 값을  리턴한다.
	 * @return String userUniqId
	 */
	public String getUserUniqId() {
		return userUniqId;
	}
	/**
	 * userUniqId attribute 값을 설정한다.
	 * @param String userUniqId
	 */
	public void setUserUniqId(String userUniqId) {
		this.userUniqId = userUniqId;
	}
	/**
	 * areaNo attribute 값을  리턴한다.
	 * @return String areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * @param String areaNo
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	/**
	 * brth attribute 값을  리턴한다.
	 * @return String brth
	 */
	public String getBrth() {
		return brth;
	}
	/**
	 * brth attribute 값을 설정한다.
	 * @param String brth
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}
	/**
	 * detailAdres attribute 값을  리턴한다.
	 * @return String detailAdres
	 */
	public String getDetailAdres() {
		return detailAdres;
	}
	/**
	 * detailAdres attribute 값을 설정한다.
	 * @param String detailAdres
	 */
	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}
	/**
	 * emailAdres attribute 값을  리턴한다.
	 * @return String emailAdres
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정한다.
	 * @param String emailAdres 
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}
	/**
	 * employeeNo attribute 값을  리턴한다.
	 * @return String employeeNo
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}
	/**
	 * employeeNo attribute 값을 설정한다.
	 * @param String employeeNo
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	/**
	 * userLoginId attribute 값을  리턴한다.
	 * @return String userLoginId
	 */
	public String getUserLoginId() {
		return userLoginId;
	}
	/**
	 * userLoginId attribute 값을 설정한다.
	 * @param String userLoginId
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	/**
	 * userNm attribute 값을  리턴한다.
	 * @return String userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * @param String userNm
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * userSttusCode attribute 값을  리턴한다.
	 * @return String userSttusCode
	 */
	public String getUserSttusCode() {
		return userSttusCode;
	}
	/**
	 * userSttusCode attribute 값을 설정한다.
	 * @param String userSttusCode
	 */
	public void setUserSttusCode(String userSttusCode) {
		this.userSttusCode = userSttusCode;
	}
	/**
	 * fxnum attribute 값을  리턴한다.
	 * @return String fxnum
	 */
	public String getFxnum() {
		return fxnum;
	}
	/**
	 * fxnum attribute 값을 설정한다.
	 * @param String fxnum
	 */
	public void setFxnum(String fxnum) {
		this.fxnum = fxnum;
	}
    /**
     * companyId attribute를 리턴한다.
     * @return String companyId
     */
	public String getCompanyId() {
	    return companyId;
	}
    /**
     * companyId attribute 값을 설정한다.
     * @param String companyId
     */
	public void setCompanyId(String id) {
	    this.companyId = id;
	}
    /**
     * companyNm attribute를 리턴한다.
     * @return String companyNm
     */
	public String getCompanyNm() {
	    return companyNm;
	}
    /**
	 * companyNm attribute 값을 설정한다.
     * @param String companyNm
     */
	public void setCompanyNm(String name) {
	    this.companyNm = name;
	}
	/* 
	 * */
	public String getCompanyCd() {
		return companyCd;
	}
	/* 
	 * */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * groupId attribute 값을  리턴한다.
	 * @return String groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * groupId attribute 값을 설정한다.
	 * @param String groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * homeadres attribute 값을  리턴한다.
	 * @return String homeadres
	 */
	public String getHomeadres() {
		return homeadres;
	}
	/**
	 * homeadres attribute 값을 설정한다.
	 * @param String homeadres
	 */
	public void setHomeadres(String homeadres) {
		this.homeadres = homeadres;
	}
	/**
	 * homeendTelno attribute 값을  리턴한다.
	 * @return String homeendTelno
	 */
	public String getHomeendTelno() {
		return homeendTelno;
	}
	/**
	 * homeendTelno attribute 값을 설정한다.
	 * @param String homeendTelno
	 */
	public void setHomeendTelno(String homeendTelno) {
		this.homeendTelno = homeendTelno;
	}
	/**
	 * homemiddleTelno attribute 값을  리턴한다.
	 * @return String homemiddleTelno
	 */
	public String getHomemiddleTelno() {
		return homemiddleTelno;
	}
	/**
	 * homemiddleTelno attribute 값을 설정한다.
	 * @param String homemiddleTelno
	 */
	public void setHomemiddleTelno(String homemiddleTelno) {
		this.homemiddleTelno = homemiddleTelno;
	}
	/**
	 * ihidnum attribute 값을  리턴한다.
	 * @return String ihidnum
	 */
	public String getIhidnum() {
		return ihidnum;
	}
	/**
	 * ihidnum attribute 값을 설정한다.
	 * @param String ihidnum
	 */
	public void setIhidnum(String ihidnum) {
		this.ihidnum = ihidnum;
	}
	/**
	 * insttCode attribute 값을  리턴한다.
	 * @return String insttCode
	 */
	public String getInsttCode() {
		return insttCode;
	}
	/**
	 * insttCode attribute 값을 설정한다.
	 * @param String insttCode
	 */
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
	}
	/**
	 * moblphonNo attribute 값을  리턴한다.
	 * @return String moblphonNo
	 */
	public String getMoblphonNo() {
		return moblphonNo;
	}
	/**
	 * moblphonNo attribute 값을 설정한다.
	 * @param String moblphonNo
	 */
	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}
	/**
	 * offmTelno attribute 값을  리턴한다.
	 * @return String offmTelno
	 */
	public String getOffmTelno() {
		return offmTelno;
	}
	/**
	 * offmTelno attribute 값을 설정한다.
	 * @param String offmTelno
	 */
	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}
	/**
	 * orgnztId attribute 값을  리턴한다.
	 * @return String orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
     * orgnztId attribute 값을 설정한다.
     * @param String orgnztId
     */
    public void setOrgnztId(String orgnztId) {
        this.orgnztId = orgnztId;
    }
    /**
     * orgnztNm attribute를 리턴한다.
     * @return String orgnztNm
     */
    public String getOrgnztNm() {
        return orgnztNm;
    }
    /**
     * orgnztNm attribute 값을 설정한다.
     * @param String orgnztNm
     */
	public void setOrgnztNm(String name) {
	    this.orgnztNm = name;
	}
	/**
	 * password attribute 값을  리턴한다.
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password attribute 값을 설정한다.
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * passwordCnsr attribute 값을  리턴한다.
	 * @return String passwordCnsr
	 */
	public String getPasswordCnsr() {
		return passwordCnsr;
	}
	/**
	 * passwordCnsr attribute 값을 설정한다.
	 * @param String passwordCnsr
	 */
	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}
	/**
	 * passwordHint attribute 값을  리턴한다.
	 * @return String passwordHint
	 */
	public String getPasswordHint() {
		return passwordHint;
	}
	/**
	 * passwordHint attribute 값을 설정한다.
	 * @param String passwordHint
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
    /**
     * approvalPassword attribute를 리턴한다.
     * @return String approvalPassword
     */
	public String getApprovalPassword() {
        return approvalPassword;
    }
    /**
     * approvalPassword attribute 값을 설정한다.
     * @param String approvalPassword
     */
    public void setApprovalPassword(String approvalPassword) {
        this.approvalPassword = approvalPassword;
    }
    /**
     * oldApprovalPassword attribute를 리턴한다.
     * @return String oldApprovalPassword
     */
    public String getOldApprovalPassword() {
		return oldApprovalPassword;
	}
    /**
     * oldApprovalPassword attribute 값을 설정한다.
     * @param String oldApprovalPassword
     */
	public void setOldApprovalPassword(String oldApprovalPassword) {
		this.oldApprovalPassword = oldApprovalPassword;
	}
	/**
	 * sbscrbDeBegin attribute 값을  리턴한다.
	 * @return String sbscrbDeBegin
	 */
	public String getSbscrbDeBegin() {
		return sbscrbDeBegin;
	}
	/**
	 * sbscrbDeBegin attribute 값을 설정한다.
	 * @param String sbscrbDeBegin
	 */
	public void setSbscrbDeBegin(String sbscrbDeBegin) {
		this.sbscrbDeBegin = sbscrbDeBegin;
	}
	/**
	 * sbscrbDeEnd attribute 값을  리턴한다.
	 * @return String sbscrbDeEnd
	 */
	public String getSbscrbDeEnd() {
		return sbscrbDeEnd;
	}
	/**
	 * sbscrbDeEnd attribute 값을 설정한다.
	 * @param String sbscrbDeEnd
	 */
	public void setSbscrbDeEnd(String sbscrbDeEnd) {
		this.sbscrbDeEnd = sbscrbDeEnd;
	}
	/**
	 * sexdstnCode attribute 값을  리턴한다.
	 * @return String sexdstnCode
	 */
	public String getSexdstnCode() {
		return sexdstnCode;
	}
	/**
	 * sexdstnCode attribute 값을 설정한다.
	 * @param String sexdstnCode
	 */
	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}
	/**
	 * zip attribute 값을  리턴한다.
	 * @return String zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * zip attribute 값을 설정한다.
	 * @param String zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * subDn attribute 값을  리턴한다.
	 * @return String subDn
	 */
	public String getSubDn() {
		return subDn;
	}
	/**
	 * subDn attribute 값을 설정한다.
	 * @param String subDn
	 */
	public void setSubDn(String subDn) {
		this.subDn = subDn;
	}
    /**
     * seq attribute를 리턴한다.
     * @return int seq
     */
	public int getSeq() {
		return seq;
	}
    /**
     * seq attribute 값을 설정한다.
     * @param String seq
     */
	public void setSeq(String seq) {
	    if ((seq != null)
	            && (seq.isEmpty() == false)){
	        this.seq = Integer.parseInt(seq);
	    }
	}
    /**
     * posiId attribute를 리턴한다.
     * @return String posiId
     */
	public String getPosiId() {
		return posiId;
	}
    /**
     * posiId attribute 값을 설정한다.
     * @param String posiId
     */
	public void setPosiId(String posiId) {
		this.posiId = posiId;
	}
    /**
     * posiNm attribute를 리턴한다.
     * @return String posiNm
     */
	public String getPosiNm() {
	    return posiNm;
	}
    /**
     * posiNm attribute 값을 설정한다.
     * @param String posiNm
     */
	public void setPosiNm(String posiNm) {
	    this.posiNm = posiNm;
	}
    /**
     * dutyId attribute를 리턴한다.
     * @return String dutyId
     */
	public String getDutyId() {
		return dutyId;
	}
    /**
     * dutyId attribute 값을 설정한다.
     * @param String dutyId
     */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
    /**
     * dutyNm attribute를 리턴한다.
     * @return String dutyNm
     */
	public String getDutyNm() {
	    return dutyNm;
	}
    /**
     * dutyNm attribute 값을 설정한다.
     * @param String dutyNm
     */
	public void setDutyNm(String name) {
	    this.dutyNm = name;
	}
    /**
     * securityLvl attribute를 리턴한다.
     * @return int securityLvl
     */
	public int getSecurityLvl() {
		return securityLvl;
	}
    /**
     * securityLvl attribute 값을 설정한다.
     * @param int securityLvl
     */
	public void setSecurityLvl(int securityLvl) {
		this.securityLvl = securityLvl;
	}
    /**
     * userDesc attribute를 리턴한다.
     * @return String userDesc
     */
	public String getUserDesc() {
		return userDesc;
	}
    /**
     * ㅍ attribute 값을 설정한다.
     * @param String userDesc
     */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
    /**
     * userType attribute를 리턴한다.
     * @return String userType
     */
	public String getUserType() {
		return userType;
	}
    /**
     * userType attribute 값을 설정한다.
     * @param String userType
     */
	public void setUserType(String userType) {
		this.userType = userType;
	}
    /**
     * userAbsF attribute를 리턴한다.
     * @return String userAbsF
     */
    public String getUserAbsF() {
        return userAbsF;
    }
    /**
     * userAbsF attribute 값을 설정한다.
     * @param String userAbsF
     */
    public void setUserAbsF(String userAbsF) {
        this.userAbsF = userAbsF;
    }
    /**
     * photoPath attribute를 리턴한다.
     * @return String photoPath
     */
    public String getPhotoPath() {
        return photoPath;
    }
    /**
     * photoPath attribute 값을 설정한다.
     * @param String photoPath
     */
    public void setPhotoPath(String photoPath) {
    	this.photoPath = photoPath.replaceAll(System.getProperty("line.separator"), "");
    }
    /**
     * signPath attribute를 리턴한다.
     * @return String signPath
     */
    public String getSignPath() {
        return signPath;
    }
    /**
     * signPath attribute 값을 설정한다.
     * @param String signPath
     */
    public void setSignPath(String signPath) {
    	this.signPath = signPath.replaceAll(System.getProperty("line.separator"), "");
    }
    /**
     * updateDt attribute를 리턴한다.
     * @return String updateDt
     */
    public String getUpdateDt() {
        return updateDt;
    }
    /**
     * updateDt attribute 값을 설정한다.
     * @param String updateDt
     */
    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }
    /**
     * userLpwdDt attribute를 리턴한다.
     * @return String userLpwdDt
     */
    public String getUserLpwdDt() {
        return userLpwdDt;
    }
    /**
     * userLpwdDt attribute 값을 설정한다.
     * @param String userLpwdDt
     */
    public void setUserLpwdDt(String userLpwdDt) {
        this.userLpwdDt = userLpwdDt;
    }
	public String getDisplayTypeCd() {
		return displayTypeCd;
	}
	public void setDisplayTypeCd(String displayTypeCd) {
		this.displayTypeCd = displayTypeCd;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getEmailPw() {
		return emailPw;
	}
	public void setEmailPw(String emailPw) {
		this.emailPw = emailPw;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public String getEmailOldPwd() {
		return emailOldPwd;
	}
	public void setEmailOldPwd(String emailOldPwd) {
		this.emailOldPwd = emailOldPwd;
	}
	public String getEmailNewPwd() {
		return emailNewPwd;
	}
	public void setEmailNewPwd(String emailNewPwd) {
		this.emailNewPwd = emailNewPwd;
	}
	public String getScrtyApprTy() {
		return scrtyApprTy;
	}
	public void setScrtyApprTy(String scrtyApprTy) {
		this.scrtyApprTy = scrtyApprTy;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDeptViewAuthF() {
		return deptViewAuthF;
	}
	public void setDeptViewAuthF(String deptViewAuthF) {
		this.deptViewAuthF = deptViewAuthF;
	}
	public long getEdocQuota() {
		return edocQuota;
	}
	public void setEdocQuota(long edocQuota) {
		this.edocQuota = edocQuota;
	}	
	public String getAbsId() {
		return absId;
	}
	public void setAbsId(String absId) {
		this.absId = absId;
	}
	public int getMailF() {
		return mailF;
	}
	public void setMailF(int mailF) {
		this.mailF = mailF;
	}
	public int getApprF() {
		return apprF;
	}
	public void setApprF(int apprF) {
		this.apprF = apprF;
	}
	public String getApprDepuUserUniqId() {
		return apprDepuUserUniqId;
	}
	public void setApprDepuUserUniqId(String apprDepuUserUniqId) {
		this.apprDepuUserUniqId = apprDepuUserUniqId;
	}
	
	public String getApprDepuUserUniqNM() {
		return apprDepuUserUniqNM;
	}
	public void setApprDepuUserUniqNM(String apprDepuUserUniqNM) {
		this.apprDepuUserUniqNM = apprDepuUserUniqNM;
	}
	
	public String getNotiTypeCd() {
		return notiTypeCd;
	}
	public void setNotiTypeCd(String notiTypeCd) {
		this.notiTypeCd = notiTypeCd;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getEsntlId() {
		return esntlId;
	}
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	
	/* 추가 */
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	public String getMailMsg() {
		return mailMsg;
	}
	public void setMailMsg(String mailMsg) {
		this.mailMsg = mailMsg;
	}
	public String getOrgnztParId() {
		return orgnztParId;
	}
	public void setOrgnztParId(String orgnztParId) {
		this.orgnztParId = orgnztParId;
	}
	public String getDutyCd() {
		return dutyCd;
	}
	public void setDutyCd(String dutyCd) {
		this.dutyCd = dutyCd;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getWspId() {
		return wspId;
	}
	public void setWspId(String wspId) {
		this.wspId = wspId;
	}
	public String getPhotoDeleteFlag() {
		return photoDeleteFlag;
	}
	public String getSignDeleteFlag() {
		return signDeleteFlag;
	}
	public void setPhotoDeleteFlag(String photoDeleteFlag) {
		this.photoDeleteFlag = photoDeleteFlag;
	}
	public void setSignDeleteFlag(String signDeleteFlag) {
		this.signDeleteFlag = signDeleteFlag;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
}
