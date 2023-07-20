package egovframework.com.global.common.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.global.common.Globals;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 *               수정일 수정자 수정내용 ------- ------- ------------------- 2009. 3. 25. 이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class FileVO implements Serializable {

    /**
     * 첨부파일 아이디
     */
    public String atchFileId = "";
    /**
     * 생성일자
     */
    public String creatDt = "";
    /**
     * 파일내용
     */
    public String fileCn = "";
    /**
     * 파일확장자
     */
    public String fileExtsn = "";
    /**
     * 파일크기
     */
    public String fileMg = "";
    /**
     * 파일연번
     */
    public String fileSn = "";
    /**
     * 파일저장경로
     */
    public String fileStreCours = "";
    /**
     * 원파일명
     */
    public String orignlFileNm = "";
    /**
     * 저장파일명
     */
    public String streFileNm = "";
    /**
     * 삭제여부
     */
    public int fileDelF = 0;
    /**
     * 파일 URL 링크
     */
    private String fileUrlLink = "";
    /**
     * DRM 암호화 여부
     */
    private int drmF;

    /**
     * 암호화된 파일 경로 for DRM PDF Viewer
     */
    private String strEncOrgFilePath;

    /**
     * atchFileId attribute를 리턴한다.
     * 
     * @return the atchFileId
     */
    public String getAtchFileId() {
        return atchFileId;
    }

    /**
     * atchFileId attribute 값을 설정한다.
     * 
     * @param atchFileId the atchFileId to set
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

    /**
     * creatDt attribute를 리턴한다.
     * 
     * @return the creatDt
     */
    public String getCreatDt() {
        return creatDt;
    }

    /**
     * creatDt attribute 값을 설정한다.
     * 
     * @param creatDt the creatDt to set
     */
    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }

    /**
     * fileCn attribute를 리턴한다.
     * 
     * @return the fileCn
     */
    public String getFileCn() {
        return fileCn;
    }

    /**
     * fileCn attribute 값을 설정한다.
     * 
     * @param fileCn the fileCn to set
     */
    public void setFileCn(String fileCn) {
        this.fileCn = fileCn;
    }

    /**
     * fileExtsn attribute를 리턴한다.
     * 
     * @return the fileExtsn
     */
    public String getFileExtsn() {
        return fileExtsn;
    }

    /**
     * fileExtsn attribute 값을 설정한다.
     * 
     * @param fileExtsn the fileExtsn to set
     */
    public void setFileExtsn(String fileExtsn) {
        this.fileExtsn = fileExtsn;
    }

    /**
     * fileMg attribute를 리턴한다.
     * 
     * @return the fileMg
     */
    public String getFileMg() {
        return fileMg;
    }

    /**
     * fileMg attribute 값을 설정한다.
     * 
     * @param fileMg the fileMg to set
     */
    public void setFileMg(String fileMg) {
        this.fileMg = fileMg;
    }

    /**
     * fileSn attribute를 리턴한다.
     * 
     * @return the fileSn
     */
    public String getFileSn() {
        return fileSn;
    }

    /**
     * fileSn attribute 값을 설정한다.
     * 
     * @param fileSn the fileSn to set
     */
    public void setFileSn(String fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * fileStreCours attribute를 리턴한다.
     * 
     * @return the fileStreCours
     */
    public String getFileStreCours() {
        return fileStreCours;
    }

    /**
     * fileStreCours attribute 값을 설정한다.
     * 
     * @param fileStreCours the fileStreCours to set
     */
    public void setFileStreCours(String fileStreCours) {
        String[] str = Globals.UPLOAD_BASE_PATH.split("/");
        if (fileStreCours.contains(str[str.length - 1])) {
            fileStreCours = fileStreCours.substring(Globals.UPLOAD_BASE_PATH.length());
        }
        this.fileStreCours = fileStreCours;
    }

    /**
     * orignlFileNm attribute를 리턴한다.
     * 
     * @return the orignlFileNm
     */
    public String getOrignlFileNm() {
        return orignlFileNm;
    }

    /**
     * orignlFileNm attribute 값을 설정한다.
     * 
     * @param orignlFileNm the orignlFileNm to set
     */
    public void setOrignlFileNm(String orignlFileNm) {
        this.orignlFileNm = orignlFileNm;
    }

    /**
     * streFileNm attribute를 리턴한다.
     * 
     * @return the streFileNm
     */
    public String getStreFileNm() {
        return streFileNm;
    }

    /**
     * streFileNm attribute 값을 설정한다.
     * 
     * @param streFileNm the streFileNm to set
     */
    public void setStreFileNm(String streFileNm) {
        this.streFileNm = streFileNm;
    }

    public int getFileDelF() {
        return fileDelF;
    }

    public void setFileDelF(int fileDelF) {
        this.fileDelF = fileDelF;
    }

    public String getFileUrlLink() {
        return fileUrlLink;
    }

    public void setFileUrlLink(String fileUrlLink) {
        this.fileUrlLink = fileUrlLink;
    }

    public int getDrmF() {
        return drmF;
    }

    public void setDrmF(int drmF) {
        this.drmF = drmF;
    }

    public String getStrEncOrgFilePath() {
        return strEncOrgFilePath;
    }

    public void setStrEncOrgFilePath(String strEncOrgFilePath) {
        this.strEncOrgFilePath = strEncOrgFilePath;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
