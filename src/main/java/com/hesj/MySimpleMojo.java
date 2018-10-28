package com.hesj;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 自定义插件
 * @auther: hesj
 * @date: 2018/10/22/022 23:09
 */
@Mojo(name = "simpleMojo",defaultPhase = LifecyclePhase.PACKAGE)
public class MySimpleMojo extends AbstractMojo {
    @Parameter(property = "msg",defaultValue = "${basedir}")
    private String msg;

    @Parameter(property = "fileType",defaultValue = "java")//多个用逗号分隔
    private String fileTypes;

    /**
     * 功能描述: 
     * @param:
     * @return: 
     * @auther: hesj
     * @date: 2018/10/22/022 22:14
     */

    public void execute() throws MojoExecutionException, MojoFailureException {
        File file = new File(msg);
        List<String> result = new ArrayList<>();
        getAllFileByPath(file,result);
        Map<String, Integer> fileTypeToCount = countByFileType(result);
        print(fileTypeToCount);
    }

    /**
     * 功能描述: 查找当前文件对象下所有文件名称列表
     * @param: file 文件对象
     * @return: result 返回所有文件名称列表
     * @auther: hesj
     * @date: 2018/10/28/028 14:06
     */
    void getAllFileByPath(File file, List<String> result){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(null != files){
                for(File tempFile : files){
                    getAllFileByPath(tempFile,result);
                }
            }
        }
        else{
            result.add(file.getName());
        }
    }

    /**
     * 功能描述: 统计每类文件数量
     * @param: fileNameList
     * @return:
     * @auther: hesj
     * @date: 2018/10/28/028 14:29
     */
    Map<String,Integer> countByFileType(List<String> fileNameList){
        Map<String,Integer> result = new HashMap<>();
        String tempFileType = "";
        for(String name : fileNameList){
            int pos = name.indexOf(".");
            if(pos != -1){
                tempFileType = name.substring(pos+1).toLowerCase();
            }
            else{
                tempFileType = "";
            }
            Integer count = result.get(tempFileType);
            if(null == count){
                count = 0;

            }
            result.put(tempFileType,count+1);

        }
        return result;
    }

    void print(Map<String,Integer> fileTypeToCount){
        if(fileTypes != null && !fileTypes.equals("")){
            String [] fileTypeArr = fileTypes.split(",");
            Integer count = 0;
            for(String tempFileType : fileTypeArr){
                count = fileTypeToCount.get(tempFileType.toLowerCase());
                System.out.println("文件类型为："+tempFileType+"，数量为："+(count==null?0:count));
            }
        }
    }
}
