package boot.start.example;

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
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import boot.start.Section;
import boot.start.SectionMethodRegister;
import boot.start.SectionRegister;
import boot.start.SectionScan;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;




/**
 * 
 * @author d_mail_p@sina.com
 * 这个抽象类用在springboot框架中，通过扫描自定义注解的方式，将被注解的类和方法保存在静态Map中。
 * 这个抽象类需要子类实现具体方法，同时需要两个注解配合使用，其中一个注解在启动类中标记扫描的包，另外一个注解则是目标注解，所有被注解标记的类或方法将保存起来。
 * 
 * 1、目前只能处理TYPE、METHOD的注解；
 * 2、子类通过returnAnnotationType()方法返回一个注释的class对象，也就是需要解析的注解类；
 * 3、子类可以覆盖initTypeAnnotation()和iniMethodAnnotation()方法，来处理每个被注解标记的类和方法；
 * 3、在springboot的启动类中，通过一个扫描注解来规定需要扫描哪些包下面的类，这个扫描注解必须通过basePackages来表示包名；这个注解可以是这种形式；其中的@Import注解引入
 * 的就是对应的注册类；
 * 
 *  @Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Target(ElementType.TYPE)
	@Import({SectionMethodRegister.class})
	public @interface SectionMethodScan{ 
		//这个属性用来标记需要扫描的包
		String[] value() default {};
	}

	4、如果目标注解定义的Target为METHOD，那么只要类在对应扫描的路径下面，即时类没有标记目标注解，这个被标记的方法也会被扫描到。也就是说，方法注解并不依赖类上面的注解，类似RequestMapping注解；

 */

public abstract class AbstractBasicScannerRegister
		implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware {

	private Logger LOG = LoggerFactory.getLogger(AbstractBasicScannerRegister.class);

	
	/**
	 * @return：由子类重写的抽象方法，返回具体的需要解析的注解的Class对象；
	 */
	protected abstract Class<? extends Annotation> returnAnnotationType();
	
	/**
	 * @param className：全限定类名
	 * @param annotion：类上标记的注解，记录了相关信息
	 * @return
	 */
	protected Annotation initTypeAnnotation(String className,Annotation annotion){
		return annotion;
	};
	
	/**
	 * @param className：全限定类名+方法名
	 * @param annotion：方法上标记的注解，记录了相关信息
	 * @return
	 */
	protected Annotation iniMethodAnnotation(String className,Annotation annotion){
		return annotion;
	};

	
	
	private static Map<Object, Annotation> SCANNER_TYPE_MAP = new HashMap<Object, Annotation>();

	/**
	 * @return：返回所有标记了目标注解的类的集合
	 */
	public  Map<Object, Annotation>  getScannerTypeMap() {
		return AbstractBasicScannerRegister.SCANNER_TYPE_MAP;
	}

	private static Map<Object, Annotation> SCANNER_METHOD_MAP = new HashMap<Object, Annotation>();

 
	/**
	 * @return：返回所有标记了目标注解的方法的集合
	 */
	public  Map<Object, Annotation>  getScannerMethodMap() {
		return AbstractBasicScannerRegister.SCANNER_METHOD_MAP;
	}

	private ResourceLoader resourceLoader = null;

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

	
	/* 
	 	这个方法是在接口ImportBeanDefinitionRegistrar中定义的。
	 * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// logPackageScan(importingClassMetadata);
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
		AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Section.class);
		scanner.addIncludeFilter(annotationTypeFilter);
		basePackages = getBasePackages(metadata);

		Map<Object, Object> SCANNER_MAP = new HashMap<Object, Object>();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				.concat(ClassUtils
						.convertClassNameToResourcePath(
								SystemPropertyUtils.resolvePlaceholders(basePackages.toArray()[0].toString()))
						.concat(""));

		for (String basePackage : basePackages) {

			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

			try {
				// 这里特别注意一下类路径必须这样写
				// 获取指定包下的所有类
				basePackage = basePackage.replace(".", "/");

				resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
				packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage)
						+ '/' + "**/*.class";
				Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

				MetadataReaderFactory metadata1 = new SimpleMetadataReaderFactory();
				for (Resource resource : resources) {
					if (!resource.getFilename().endsWith(".class"))
						continue;
					MetadataReader metadataReader = metadata1.getMetadataReader(resource);
					ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
					sbd.setResource(resource);
					sbd.setSource(resource);
					
					
					BeanDefinition beanDefinition=sbd;
					String classname = beanDefinition.getBeanClassName();
					Class clazz = Class.forName(classname);
					Annotation s = Class.forName(classname).getAnnotation(this.returnAnnotationType());

					Target meta = this.returnAnnotationType().getAnnotation(Target.class);
					ElementType[] values = meta.value();
					
					
					//处理类上面的注解
					if (s != null) {
						//由子类覆盖的初始化方法
						initTypeAnnotation(classname, s);
						AbstractBasicScannerRegister.SCANNER_TYPE_MAP.put(classname, s);
								
						
					}
					
					//处理方法上的注解
					for (ElementType type : values) {
						if ("METHOD".equals(type.name())) {
							java.lang.reflect.Method[] methods = clazz.getMethods();
							for (Method m : methods) {
								if (m.getAnnotation(this.returnAnnotationType()) != null) {
									Annotation a = m.getAnnotation(this.returnAnnotationType());
									//由子类覆盖的初始化方法
									iniMethodAnnotation(classname + "." + m.getName(),a);
									AbstractBasicScannerRegister.SCANNER_METHOD_MAP.put(classname + "." + m.getName(), a);
								}
							}
						}
					}
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	protected String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(this.environment.resolveRequiredPlaceholders(basePackage));
	}

	protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
		Map<String, Object> attributes = importingClassMetadata
				.getAnnotationAttributes(SectionScan.class.getCanonicalName());

		Set<String> basePackages = new HashSet<String>();
		for (String pkg : (String[]) attributes.get("value")) {
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


		};
	}

}
