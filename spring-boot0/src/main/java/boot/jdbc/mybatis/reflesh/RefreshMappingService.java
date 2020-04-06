/**
 * 
 */
package boot.jdbc.mybatis.reflesh;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

/**
 * @author d_mail_p@sina.com
 *
 *         这个刷新mapping文件的方法，基本可以用。
 *         但是当xml中通过namespace的全路径形式引用了其他命名空间的元素时就会出错，因为在removeConfig()
 *         方法中清除了相应的元素，但是没有在另外的文件中重新加载
 */
@Service
public class RefreshMappingService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private List<Resource> mapperLocations=new ArrayList<Resource>();
	
	private PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
	
	@Autowired
	private Environment env;

	/**
	 * 读取config中的mybatis.mapperLocations，刷新所有的xml文件；
	 * @throws Exception
	 */
	public void refreshAll() throws Exception {
		Configuration configuration = this.sqlSessionFactory.getConfiguration();

		this.removeConfig(configuration);
		this.mapperLocations.clear();
		
		String path=this.env.getProperty("mybatis.mapperLocations");
		String[] ps=path.split(",");
		for(String p: ps){
			Resource[] object=this.resolver.getResources(p);
			if(object!=null){
				this.mapperLocations.addAll(Arrays.asList(object));
			}
			
		}


		for (Resource configLocation : mapperLocations) {
			XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configLocation.getInputStream(), configuration,
					configLocation.toString(), configuration.getSqlFragments());
			xmlMapperBuilder.parse();

		}

	}

	/**
	 * 刷新指定路径的文件
	 * 这个刷新mapping文件的方法，基本可以用。
	 * 但是当xml中通过namespace的全路径形式引用了其他命名空间的元素时就会出错，因为在removeConfig()
	 * 方法中清除了相应的元素，但是没有在另外的文件中重新加载
	 * @param location
	 * @throws Exception
	 */
	public void refresh(String location) throws Exception {

		Configuration configuration = this.sqlSessionFactory.getConfiguration();
		this.removeConfig(configuration);
		Resource configLocation = this.getResources(location);
		XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configLocation.getInputStream(), configuration,
				configLocation.toString(), configuration.getSqlFragments());
		xmlMapperBuilder.parse();

	}

	private Resource getResources(String location) throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources(location);
		if (resources != null && resources.length > 0)
			return resources[0];
		return null;
	}

	private void removeConfig(Configuration configuration)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> classConfig = configuration.getClass();
		clearMap(classConfig, configuration, "mappedStatements");
		clearMap(classConfig, configuration, "caches");
		clearMap(classConfig, configuration, "resultMaps");
		clearMap(classConfig, configuration, "parameterMaps");
		clearMap(classConfig, configuration, "keyGenerators");
		clearMap(classConfig, configuration, "sqlFragments");

		clearSet(classConfig, configuration, "loadedResources");

	}

	private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = classConfig.getDeclaredField(fieldName);
		field.setAccessible(true);
		Map mapConfig = (Map) field.get(configuration);
		mapConfig.clear();
	}

	@SuppressWarnings("rawtypes")
	private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = classConfig.getDeclaredField(fieldName);
		field.setAccessible(true);
		Set setConfig = (Set) field.get(configuration);
		setConfig.clear();
	}

}
