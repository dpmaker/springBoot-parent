package boot.start;

import org.apache.activemq.filter.function.makeListFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class SectionMethodRegister
		implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware {

	Logger LOG = LoggerFactory.getLogger(SectionRegister.class);

	public static Map<String, Section> SECTION_MAP = new HashMap<String, Section>();

	public void setSectionMap(Map<String, Section> sectionMap) {
		SECTION_MAP = sectionMap;
	}

	private ResourceLoader resourceLoader;

	private ClassLoader classLoader;

	private Environment environment;

	public void setEnvironment(Environment environment) {
		this.environment = environment;

	}

	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;

	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		//logPackageScan(importingClassMetadata);
		registerSections(importingClassMetadata, registry);

	}

	private void logPackageScan(AnnotationMetadata metadata) {
		Map<String, Object> defaultAttrs = metadata.getAnnotationAttributes(SectionScan.class.getName(), true);
		if (defaultAttrs != null && defaultAttrs.size() > 0) {
			LOG.info("section package scan: " + buildPackages((String[]) defaultAttrs.get("basePackages")));
		}
	}

	private String buildPackages(String[] basePackages) {
		if (basePackages == null || basePackages.length == 0) {
			return "null";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : basePackages) {
			stringBuilder.append(s).append(",");
		}
		stringBuilder.substring(0, stringBuilder.length() - 2);
		return stringBuilder.toString();
	}

	public void registerSections(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		ClassPathScanningCandidateComponentProvider scanner = getScanner();
		scanner.setResourceLoader(this.resourceLoader);
		Set<String> basePackages;
		Map<String, Object> attrs = metadata.getAnnotationAttributes(SectionScan.class.getName());
		AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Section.class);
		scanner.addIncludeFilter(annotationTypeFilter);
		basePackages = getBasePackages(metadata);

		Map<String, Section> sectionMap = new HashMap<String, Section>();

		for (String basePackage : basePackages) {

			Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();

			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

			try {
				// 这里特别注意一下类路径必须这样写
				// 获取指定包下的所有类
				basePackage = basePackage.replace(".", "/");
				Resource[] resources = resourcePatternResolver.getResources("classpath*:" + basePackage);

				MetadataReaderFactory metadata1 = new SimpleMetadataReaderFactory();
				for (Resource resource : resources) {
					MetadataReader metadataReader = metadata1.getMetadataReader(resource);
					ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
					sbd.setResource(resource);
					sbd.setSource(resource);
					candidates.add(sbd);
				}
				for (BeanDefinition beanDefinition : candidates) {
					String classname = beanDefinition.getBeanClassName();
					// 扫描Section注解
					Class clazz=Class.forName(classname);
					Section s =(Section) clazz.getAnnotation(Section.class);
					if (s != null) {
						java.lang.reflect.Method[] methods =clazz.getMethods();
						for(Method m: methods){
							if(m.getAnnotation(Section.class)!=null){
								sectionMap.put(classname, m.getAnnotation(Section.class));
							}
						}
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 使用容器存储扫描出来的对象(类全限定名:section对象)
		setSectionMap(sectionMap);

	}

	protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
		Map<String, Object> attributes = importingClassMetadata
				.getAnnotationAttributes(SectionScan.class.getCanonicalName());

		Set<String> basePackages = new HashSet<String>();
		for (String pkg : (String[]) attributes.get("basePackages")) {
			if (pkg != null && !"".equals(pkg)) {
				basePackages.add(pkg);
			}
		}

		if (basePackages.isEmpty()) {
			basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
		}
		return basePackages;
	}
	
	 

	
	protected ClassPathScanningCandidateComponentProvider getScanner() {

		return new ClassPathScanningCandidateComponentProvider(false, this.environment) {

			/*
			@Override
			protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
				if (beanDefinition.getMetadata().isIndependent()) {

					if (beanDefinition.getMetadata().isInterface()
							&& beanDefinition.getMetadata().getInterfaceNames().length == 1
							&& Annotation.class.getName().equals(beanDefinition.getMetadata().getInterfaceNames()[0])) {
						try {
							Class<?> target = ClassUtils.forName(beanDefinition.getMetadata().getClassName(),
									SectionRegister.this.classLoader);
							return !target.isAnnotation();
						} catch (Exception ex) {
							this.logger.error(
									"Could not load target class: " + beanDefinition.getMetadata().getClassName(), ex);

						}
					}
					return true;
				}
				return false;

			}
			*/
		};
	}
	
	

}
