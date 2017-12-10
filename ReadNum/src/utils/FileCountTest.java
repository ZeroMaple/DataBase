package utils;
import java.io.File;  
import java.util.HashMap;  
import java.util.Map;  
  
/** 
 * ͳ��ָ��Ŀ¼������ļ����ͼ����� 
 *  
 * @author zero 
 * 
 */  
public class FileCountTest {  
  
    // ���徲̬����resultMap,����ļ����ͺͶ�Ӧ����  
    public static final Map<String, Integer> resultMap = new HashMap<String, Integer>();        
      
    /** 
     * ��ȡָ��·���µ��ļ����ͼ����� 
     * @param path ����·�� 
     */  
    public static void getFileType(String path) {  
        File file = new File(path);  
          
        // �ж�path·���Ƿ����  
        if (!file.exists()) {  
            return ;  
        }  
          
        // ��ȡpath·�����ļ��б��������ļ��б�  
        File[] fileList = file.listFiles();  
        if(fileList!=null){
            for (File fileTemp : fileList) {  
                
                // �����ǰFile���ļ�  
                if (fileTemp.isFile()) {  
                    // ��ȡ�ļ���׺��  
    				String endTemp = fileTemp.getName().substring(fileTemp.getName().lastIndexOf(".") + 1);
                    Integer num = resultMap.get(endTemp);
                    if (num == null) {  
                        resultMap.put(endTemp, 1);  
                    } else {  
                        resultMap.put(endTemp, num + 1);  
                    }
                }  
                  
                // �����ǰFile��Ŀ¼  
                if (fileTemp.isDirectory()) {  
                      
                    // �ݹ����getFileType()����  
                    String pathTemp = fileTemp.getAbsolutePath();  
                    getFileType(pathTemp);  
                }  
            } 
        }
    }  
}  