package egovframework.com.global.common;

/**
 * Class Name : Globals.java Description : 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다. Modification
 * Information
 *
 * 수정일 수정자 수정내용 ------- -------- --------------------------- 2009.01.19 박지욱 최초 생성
 *
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009. 01. 19
 * @version 1.0
 * @see
 *
 */

public class Globals {
    public static final String CONST_YES = "Y";
    public static final String CONST_NO = "N";

    // OS 유형
    public static final String OS_TYPE = GlobalsProperties.getProperty("Globals.OsType");
    // DB 유형
    public static final String DB_TYPE = GlobalsProperties.getProperty("Globals.DbType");
    // 메인 페이지
    public static final String MAIN_PAGE = GlobalsProperties.getProperty("Globals.MainPage");
    // ShellFile 경로
    public static final String SHELL_FILE_PATH =
            GlobalsProperties.getPathProperty("Globals.ShellFilePath");
    // 퍼로퍼티 파일 위치
    public static final String CONF_PATH = GlobalsProperties.getPathProperty("Globals.ConfPath");
    // Server정보 프로퍼티 위치
    public static final String SERVER_CONF_PATH =
            GlobalsProperties.getPathProperty("Globals.ServerConfPath");
    // Client정보 프로퍼티 위치
    public static final String CLIENT_CONF_PATH =
            GlobalsProperties.getPathProperty("Globals.ClientConfPath");
    // 파일포맷 정보 프로퍼티 위치
    public static final String FILE_FORMAT_PATH =
            GlobalsProperties.getPathProperty("Globals.FileFormatPath");

    // 파일 업로드 원 파일명
    public static final String ORIGIN_FILE_NM = "originalFileName";
    // 파일 확장자
    public static final String FILE_EXT = "fileExtension";
    // 파일크기
    public static final String FILE_SIZE = "fileSize";
    // 업로드된 파일명
    public static final String UPLOAD_FILE_NM = "uploadFileName";
    // 파일경로
    public static final String FILE_PATH = "filePath";

    // 메일발송요청 XML파일경로
    public static final String MAIL_REQUEST_PATH =
            GlobalsProperties.getPathProperty("Globals.MailRequestPath");
    // 메일발송응답 XML파일경로
    public static final String MAIL_RESPONSE_PATH =
            GlobalsProperties.getPathProperty("Globals.MailRResponsePath");

    // G4C 연결용 IP (localhost)
    public static final String LOCAL_IP = GlobalsProperties.getProperty("Globals.LocalIp");

    // 첨부파일 기본경로
    public static final String UPLOAD_BASE_PATH =
            GlobalsProperties.getProperty("Globals.data.basedir");

    // 첨부파일 경로
    public static final String UPLOAD_FILE_PATH = "Globals.uploadFileStorePath";

    // 게시판 첨부파일 경로
    public static final String BBS_FILE_PATH = "Globals.bbsFileStorePath";

    // 서식 첨부파일 경로
    public static final String FORM_FILE_PATH = "Globals.formFileStorePath";

    // 결재문서 첨부파일 경로
    public static final String DOC_FILE_PATH = "Globals.docFileStorePath";

    // 임시 저장된 결재문서 첨부파일 경로
    public static final String DOC_FILE_TMP_PATH = "Globals.docFileTmpStorePath";

    // 결재문서 발송 경로
    public static final String DOC_FILE_SEND_PATH = "Globals.docFileSendStorePath";

    public static final String SNS_FILE_PATH = "Globals.snsFileStorePath";

    // E-Folder 첨부파일/파일 경로
    public static final String EDM_FILE_PATH = "Globals.edmFileStorePath";

    // TMS 첨부파일 경로
    public static final String TMS_FILE_PATH = "Globals.tmsFileStorePath";


    // DRM Mac PDF Convert Temp 경로
    public static final String DRM_PDF_TMP_PATH =
            GlobalsProperties.getProperty("Globals.drmPDFTmpStorePath");

    // stamp 파일 경로
    public static final String STAMP_FILE_PATH = "Globals.stampFileStorePath";

    // stamp 파일 경로
    public static final String STAMP_DATE_FORMAT = "stamp.dateformat";
}
