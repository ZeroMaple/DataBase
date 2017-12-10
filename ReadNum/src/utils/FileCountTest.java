package utils;
import java.io.File;  
import java.util.HashMap;  
import java.util.Map;  
  
/** 
 * 统计指定目录下面的文件类型及数量 
 *  
 * @author zero 
 * 
 */  
public class FileCountTest {  
  
    // 定义静态变量resultMap,存放文件类型和对应数量  
    public static final Map<String, Integer> resultMap = new HashMap<String, Integer>();        
      
    /** 
     * 获取指定路径下的文件类型及数量 
     * @param path 磁盘路径 
     */  
    public static void getFileType(String path) {  
        File file = new File(path);  
          
        // 判断path路径是否存在  
        if (!file.exists()) {  
            return ;  
        }  
          
        // 获取path路径的文件列表，并遍历文件列表  
        File[] fileList = file.listFiles();  
        if(fileList!=null){
            for (File fileTemp : fileList) {  
                
                // 如果当前File是文件  
                if (fileTemp.isFile()) {  
                    // 截取文件后缀名  
    				String endTemp = fileTemp.getName().substring(fileTemp.getName().lastIndexOf(".") + 1);
                    Integer num = resultMap.get(endTemp);
                    if (num == null) {  
                        resultMap.put(endTemp, 1);  
                    } else {  
                        resultMap.put(endTemp, num + 1);  
                    }
                }  
                  
                // 如果当前File是目录  
                if (fileTemp.isDirectory()) {  
                      
                    // 递归调用getFileType()方法  
                    String pathTemp = fileTemp.getAbsolutePath();  
                    getFileType(pathTemp);  
                }  
            } 
        }
    }  
}  