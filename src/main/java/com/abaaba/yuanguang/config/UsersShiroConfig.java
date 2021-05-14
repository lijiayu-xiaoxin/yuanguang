package com.abaaba.yuanguang.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class UsersShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin","perms[1]");
        filterMap.put("/admin/index","perms[1]");
        filterMap.put("/index","anon");
        filterMap.put("/","anon");
        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/user/myinfo","authc");
        filterMap.put("/user/mycart","authc");
        filterMap.put("/user/mycollect","authc");
        filterMap.put("/user/myorder","authc");
        filterMap.put("/goods/buygood/*","authc");


        bean.setLoginUrl("/login");

        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("usersRealm") UsersRealm usersRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 给安全管理器设置realm
        securityManager.setRealm(usersRealm());
        return securityManager;
    }

    @Bean
    UsersRealm usersRealm(){
        return new UsersRealm();
    }

}
