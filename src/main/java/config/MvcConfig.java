package config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import util.AdminLoginInterceptor;
import util.LoginInterceptor;

@Configuration // spring 설정파일
@ComponentScan(basePackages = {"main,admin,adminLog,util,freeboard,concernboard,comment,chat,user,like,videoboard,bookmark,imageboard,report,question,friends,friendsAdd,notice,announce,myPage"}) //하위 패키지 전부 스캔
@EnableWebMvc //spring mvc 활성화
@EnableTransactionManagement // 트랜잭션 기능 활성화
public class MvcConfig implements WebMvcConfigurer { 
	
	//Html, css ... 등을 처리하기 위한 설정 (외울필요없다. 나중에 갖다쓰기만 하면됨)
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer cnf) {
		cnf.enable();
	}	
	
	// view 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.jsp("/WEB-INF/view/", ".jsp");
	}
	
	// DataSource 객체 등록
	// DB접속정보 설정
	@Bean (destroyMethod = "close") //알아서 close 해줌
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/thejoun");
        ds.setUsername("thejounuser");
        ds.setPassword("thejoun1234");
		return ds;
	}
	
	// SqlSessionFactory
	// **Factory or **Builder : 직접생성이 아니라 팩토리가 생성 
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource()); //주입
		
		// sql이 들어있는 xml경로 설정
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml")); // 클래스패스의 mapper폴더 밑의 모든 xml
		return ssfb.getObject();
	}
	
	//SqlSessionTemplate
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	//파일업로드
	@Bean
	public CommonsMultipartResolver multipartResolver () {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		cmr.setMaxUploadSize(1024*1024); // 최대업로드 사이즈
		return cmr;
	}
	
	//인터셉터
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	//인터셉터
	@Bean
	public AdminLoginInterceptor adminLoginInterceptor() {
		return new AdminLoginInterceptor();
	}
		
	//인터셉터 설정
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor())
						.addPathPatterns("/freeboard/write.do")
						.addPathPatterns("/freeboard/insert.do")
						.addPathPatterns("/freeboard/edit.do")
						.addPathPatterns("/freeboard/update.do")
						.addPathPatterns("/freeboard/delete.do")
						.addPathPatterns("/concernboard/write.do")
						.addPathPatterns("/concernboard/insert.do")
						.addPathPatterns("/concernboard/edit.do")
						.addPathPatterns("/concernboard/update.do")
						.addPathPatterns("/concernboard/delete.do")
						.addPathPatterns("/imageboard/write.do")
						.addPathPatterns("/imageboard/insert.do")
						.addPathPatterns("/imageboard/edit.do")
						.addPathPatterns("/imageboard/update.do")
						.addPathPatterns("/imageboard/delete.do")
						.addPathPatterns("/videoboard/write.do")
						.addPathPatterns("/videoboard/insert.do")
						.addPathPatterns("/videoboard/edit.do")
						.addPathPatterns("/videoboard/update.do")
						.addPathPatterns("/videoboard/delete.do")
						.addPathPatterns("/question/write.do")
						.addPathPatterns("/question/insert.do")
						.addPathPatterns("/question/edit.do")
						.addPathPatterns("/question/update.do")
						.addPathPatterns("/question/delete.do")
						.addPathPatterns("/chat/index.do");
		
		reg.addInterceptor(adminLoginInterceptor())
						.addPathPatterns("/admin/member/index.do")
						.addPathPatterns("/admin/freeboard/index.do")
						.addPathPatterns("/admin/freeboard/write.do")
						.addPathPatterns("/admin/freeboard/insert.do")
						.addPathPatterns("/admin/freeboard/edit.do")
						.addPathPatterns("/admin/freeboard/update.do")
						.addPathPatterns("/admin/freeboard/delete.do")
						.addPathPatterns("/admin/concernboard/index.do")
						.addPathPatterns("/admin/concernboard/write.do")
						.addPathPatterns("/admin/concernboard/insert.do")
						.addPathPatterns("/admin/concernboard/edit.do")
						.addPathPatterns("/admin/concernboard/update.do")
						.addPathPatterns("/admin/concernboard/delete.do")
						.addPathPatterns("/admin/imageboard/index.do")
						.addPathPatterns("/admin/imageboard/edit.do")
						.addPathPatterns("/admin/imageboard/update.do")
						.addPathPatterns("/admin/imageboard/delete.do")
						.addPathPatterns("/admin/videoboard/index.do")
						.addPathPatterns("/admin/videoboard/edit.do")
						.addPathPatterns("/admin/videoboard/update.do")
						.addPathPatterns("/admin/videoboard/delete.do")
						.addPathPatterns("/admin/notice/index.do")
						.addPathPatterns("/admin/notice/write.do")
						.addPathPatterns("/admin/notice/insert.do")
						.addPathPatterns("/admin/notice/edit.do")
						.addPathPatterns("/admin/notice/update.do")
						.addPathPatterns("/admin/notice/delete.do")
						.addPathPatterns("/admin/question/index.do")
						.addPathPatterns("/admin/question/write.do")
						.addPathPatterns("/admin/question/insert.do")
						.addPathPatterns("/admin/question/replyUpdate.do")
						.addPathPatterns("/admin/question/delete.do")
						.addPathPatterns("/admin/admin/index.do")
						.addPathPatterns("/admin/admin/adminAdd.do")
						.addPathPatterns("/admin/admin/insert.do")
						.addPathPatterns("/admin/admin/delete.do")
						.addPathPatterns("/admin/loginInfo/index.do")
						.addPathPatterns("/admin/loginInfo/insert.do");

	}
	
	//트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
}
