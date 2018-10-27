package com.hesj;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * 功能描述: 自定义插件
 * @auther: hesj
 * @date: 2018/10/22/022 23:09
 */
@Mojo(name = "simpleMojo",defaultPhase = LifecyclePhase.PACKAGE)
public class MySimpleMojo extends AbstractMojo {
    @Parameter(property = "msg",defaultValue = "${basedir}")
    private String msg;
    /**
     * 功能描述: 
     * @param:
     * @return: 
     * @auther: hesj
     * @date: 2018/10/22/022 22:14
     */

    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("我的插件开始运行"+msg);
    }
}
