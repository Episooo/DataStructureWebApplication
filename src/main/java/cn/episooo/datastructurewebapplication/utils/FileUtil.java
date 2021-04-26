package cn.episooo.datastructurewebapplication.utils;

import cn.episooo.datastructurewebapplication.domain.CourseSource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/2/8 10:54
 * @Description：封装了文件上传和下载功能，所有的path都需要以'/'为结尾。
 */
public class FileUtil {
    private static String appPath = System.getProperty("user.dir") +"/files/";
    public static void saveFile(String filePath, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("没有文件！");
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(appPath + filePath + fileName);
        if(!dest.exists()){
            dest.mkdirs();
        }
        file.transferTo(dest);
    }
    public static void downloadFile(HttpServletResponse res, String fileName, String filePath) throws IOException {
        File file = new File(appPath + filePath + fileName);
        if(!file.exists()){
            throw new RuntimeException("文件不存在！");
        }

        res.setHeader("content-disposition","attachment; filename="+ URLEncoder.encode(fileName, "utf-8"));
        //已文件流的方式 进行下载操作
        FileInputStream in = new FileInputStream(file);
        OutputStream out =	res.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环读取
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
    public static void downloadFile(HttpServletResponse res, CourseSource source) throws IOException {
        downloadFile(res,source.getFileName(),source.getFilePath());
    }
    public static void deleteFile(String fileName,String filePath){
        File file = new File(appPath + filePath + fileName);
        if(file.exists()) file.delete();
    }

    public static void deleteFile(CourseSource source){
        deleteFile(source.getFileName(),source.getFilePath());
    }
}
