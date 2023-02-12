package com.exemple.config;

import org.springframework.context.annotation.Configuration;

//Dans cet exemple, la classe de configuration étend WebMvcConfigurerAdapter,
// qui est une classe de base qui fournit des méthodes de configuration pour Spring MVC.
// La configuration des vues est définie en utilisant la méthode configureViewResolvers,
// qui définit le préfixe et le suffixe des vues, et le résolveur de vues interne.
// La configuration des ressources statiques est définie en utilisant la méthode addResourceHandlers,
// qui définit le chemin vers les ressources statiques.
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.app"})
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    registry.viewResolver(resolver);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

}

