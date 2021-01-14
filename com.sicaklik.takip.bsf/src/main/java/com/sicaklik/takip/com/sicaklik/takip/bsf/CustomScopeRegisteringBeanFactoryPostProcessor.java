package com.sicaklik.takip.com.sicaklik.takip.bsf;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import com.sicaklik.takip.com.sicaklik.takip.bsf.scpoe.ScopeName;
import com.sicaklik.takip.com.sicaklik.takip.bsf.scpoe.ViewScope;


/**
 * BeanPostProcessor that registeres the view scope.
 * 
 * @author MIS
 *
 */
@Component
public class CustomScopeRegisteringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(ScopeName.VIEW, new ViewScope());
    }
}
