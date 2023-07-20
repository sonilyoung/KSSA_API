package egovframework.com.global.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import egovframework.com.adm.login.vo.Login;
import egovframework.com.global.common.Globals;
import egovframework.com.global.common.GlobalsProperties;
import egovframework.com.global.common.vo.FileVO;
import egovframework.com.global.common.vo.UserVO;


/**
 * <baseDir>/apv : 결재문서 파일 폴더, 날짜/300mode 기반으로 생성되는 하위 폴더가 존대. 폴더에는 결재문서(<문서ID>) <baseDir>/frm :
 * 결재양식 파일 폴더, 하위에 양식ID로 양식 팔일들이 존재 <baseDir>/usr : 사용자들의 싸인 파일 및 프로필 사진들이 존재, <사용자ID>.[gif|jpg|bmp]
 * <baseDir>/tmp : 임시폴더로 결재문서의 싸인파일이 임시로 존재하는 위치이며 tmp 폴더는 웹에서 접근가등하도록 WAS에 alias 혹은 context 설정이
 * 필요하다.
 */
public class PathUtil implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathUtil.class);
    private static String baseDir = Globals.UPLOAD_BASE_PATH;
    private static SimpleDateFormat YYYYMMDDhhmmss = new SimpleDateFormat("YYYYMMddHHmmss");

    public void afterPropertiesSet() {

    }

    public static String getUserPhotoDirWithoutLogin(String companyId) {
        return StringUtils.join(new String[] {baseDir, companyId,
                GlobalsProperties.getProperty("Globals.usrFileStorePath")}, File.separator);
    }

    public static String getBaseDirWithCompanyID() {
        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        if (Login == null) {
            return baseDir;
        } else {
            return "";// StringUtils.join(new String[] {baseDir, Login.getCompanyId()},
                      // File.separator);
        }
    }

    public static String getFullPathOfFile(String subDir) {
        String result = "";
        if (StringUtils.isNotBlank(baseDir)) {
            File baseDirObj = new File(PathUtil.baseDir + File.separator + subDir);
            result = baseDirObj.getPath();
        }
        return result;
    }

    public static String getFileExt(String filename) {
        if (filename == null)
            return null;
        int idx = filename.lastIndexOf(".");
        if (idx < 0)
            return null;
        return filename.substring(idx + 1);
    }

    public static File getTmpDir(String base) {
        File tmp = new File(base + File.separator + "tmp");
        tmp.mkdirs();

        return tmp;
    }

    public static void copy(File src, File tgt) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(tgt);
            IOUtils.copy(in, out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (Exception e) {
            }
        }
    }

    public static File getPathAndCreateDirWithMod(String id, String subDir) {
        StringBuffer dirPath = new StringBuffer();
        dirPath.append(getBaseDirWithCompanyID());
        dirPath.append(File.separator);

        dirPath.append(subDir);
        dirPath.append(File.separator);

        dirPath.append(calcPath());
        dirPath.append(File.separator);

        dirPath.append(OfficeStringUtil.modStr(id));

        File dir = new File(dirPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File getPathAndCreateDir(String subDir) {
        StringBuffer dirPath = new StringBuffer();
        dirPath.append(getBaseDirWithCompanyID());
        dirPath.append(File.separator);

        dirPath.append(subDir);
        dirPath.append(File.separator);

        File dir = new File(dirPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private static File createDir(String baseDir) {
        File dir = new File(getFullPathOfFile(baseDir));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File getFormPath() {
        // if first create form
        StringBuffer formPath = new StringBuffer();
        formPath.append(getBaseDirWithCompanyID());
        formPath.append(File.separator);

        formPath.append(GlobalsProperties.getProperty(Globals.FORM_FILE_PATH));
        formPath.append(File.separator);

        Calendar cal = Calendar.getInstance();
        String yearPath = cal.get(Calendar.YEAR) + "";
        formPath.append(yearPath);
        formPath.append(File.separator);

        File dir = new File(formPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    public File getFormFile(FileVO fvo) {
        return new File(getFullPathOfFile(fvo.getFileStreCours()), fvo.getStreFileNm());
    }

    public static String changeToTmpPath(String streCours) {
        // apv_tmp 디렉토리를 apv로 변경하기
        String docFileTmpPath = GlobalsProperties.getProperty(Globals.DOC_FILE_TMP_PATH)
                .replace("/", File.separator);
        String docFilePath =
                GlobalsProperties.getProperty(Globals.DOC_FILE_PATH).replace("/", File.separator);

        return streCours.replace(docFileTmpPath, docFilePath);
    }

    public static String changeToPath(String streCours) {
        // apv 디렉토리를 apv_tmp로 변경하기
        String docFilePath =
                GlobalsProperties.getProperty(Globals.DOC_FILE_PATH).replace("/", File.separator);
        String docFileTmpPath = GlobalsProperties.getProperty(Globals.DOC_FILE_TMP_PATH)
                .replace("/", File.separator);

        String apvTmpPath = streCours.replace(docFilePath, docFileTmpPath);

        return apvTmpPath;
    }

    public static File getUserSign(final UserVO user) {
        File signDir = new File(baseDir + File.separator + "sign");

        File[] matchingFiles = signDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(user.getOrgnztId())
                        && (name.toLowerCase().endsWith("gif") || name.toLowerCase().endsWith("jpg")
                                || name.toLowerCase().endsWith("bmp"));
            }
        });
        return matchingFiles == null || matchingFiles.length < 1 ? null : matchingFiles[0];
    }

    public static File saveFile(File file, String html) throws Exception {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            IOUtils.write(html, out, "utf-8");

            return file;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 문서 첨부파일 복사
     * 
     * @param tgtAttach
     * @param newFileName
     * @throws Exception
     */
    public static void copyAttachFile(List<FileVO> tgtAttach, String newFileName) throws Exception {
        for (FileVO fileInf : tgtAttach) {
            File srcAttachFile =
                    new File(getFullPathOfFile(fileInf.getFileStreCours()), fileInf.streFileNm);
            File tgtAttachFile = new File(getFullPathOfFile(fileInf.getFileStreCours()),
                    newFileName + "_" + fileInf.getFileSn());
            copy(srcAttachFile, tgtAttachFile);
        }
    }

    /**
     * 문서 임시 첨부파일 정규경로로 복사 후 임시파일 삭제
     * 
     * @param file
     * @throws Exception
     */
    public static FileVO copyDocAttachTempFile(FileVO file) throws Exception {
        // 임시저장된 첨부파일
        File src = new File(getFullPathOfFile(file.getFileStreCours()), file.getStreFileNm());

        // apv_tmp 디렉토리를 apv로 변경하기
        file.setFileStreCours(changeToTmpPath(file.getFileStreCours()));
        // 변경된 디렉토리 생성
        createDir(file.getFileStreCours());

        File tgt = new File(getFullPathOfFile(file.getFileStreCours()), file.getStreFileNm());

        // 복사 후 삭제
        copy(src, tgt);
        // src.delete();
        if (!src.delete()) {
            LOGGER.info("Failed to delete file.");
        }

        return file;
    }

    /**
     * 정규경로의 첨부파일을 임시 경로로 복사
     * 
     * @param file
     * @throws Exception
     */
    public static FileVO copyDocAttachFile(FileVO file) throws Exception {
        File src = new File(getFullPathOfFile(file.getFileStreCours()), file.getStreFileNm());

        // apv디렉토리를 apv_tmp로 변경하기
        file.setFileStreCours(changeToPath(file.getFileStreCours()));
        // 변경된 디렉토리 생성
        createDir(file.getFileStreCours());

        File tgt = new File(getFullPathOfFile(file.getFileStreCours()), file.getStreFileNm());

        copy(src, tgt);
        return file;
    }

    public String getDocBasePath() {
        return getBaseDirWithCompanyID();
    }

    public String getTempPath() {
        String baseDir = this.getClass().getResource("/").getPath();
        return StringUtils.join(new String[] {baseDir, "../../temp"}, File.separator);
    }

    public String getUserDataPath(String userUniqID) {
        return StringUtils.join(new String[] {getBaseDirWithCompanyID(),
                GlobalsProperties.getProperty("Globals.usrFileStorePath")}, File.separator);
    }

    public String getDocumentImagePath() {
        return StringUtils.join(new String[] {getBaseDirWithCompanyID(), "image", "document"},
                File.separator);
    }

    public static String calcPath() {
        Calendar cal = Calendar.getInstance();
        String yearPath = cal.get(Calendar.YEAR) + "";

        String monthPath = yearPath + File.separator
                + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator
                + new DecimalFormat("00").format(cal.get(Calendar.DATE)) + File.separator;

        return datePath;
    }

    public static String getStampPath(String stampID) {
        // 스템프 파일 위치 : ${basedir}/<기관>/stamp/
        // 스템프파일명 : stampID
        File stampFile = new File(getBaseDirWithCompanyID()
                + GlobalsProperties.getProperty(Globals.STAMP_FILE_PATH) + stampID);

        return stampFile.getAbsolutePath();
    }
}
