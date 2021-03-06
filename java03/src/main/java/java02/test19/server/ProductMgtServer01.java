package java02.test19.server;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java02.test19.server.annotation.Command;
import java02.test19.server.annotation.Component;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductMgtServer01 {
  static class CommandInfo {
    public Object instance;
    public Method method;
  }
  
  Scanner scanner; 
  ProductDao productDao;
  HashMap<String,CommandInfo> commandMap;
  ApplicationContext appCtx;
  
  public void init() throws Exception {
    // MyBatis 설정 파일 경로
    String resource = "java02/test19/server/mybatis-config.xml";
    
    // 설정 파일을 읽어 들일 입력 스트림 객체를 준비한다.
    // Resources의 getResourceAsStream()을 사용하면,
    // mybatis 설정 파일을 클래스 경로에서 찾는 스트림 객체를 리턴한다.
    InputStream inputStream = Resources.getResourceAsStream(resource);
    
    // mybatis 설정 파일대로 동작할 SqlSessionFactory를 얻는다.
    // 빌더 역할을 수행하는 객체를 통해서 얻는다.
    SqlSessionFactory sqlSessionFactory = 
        new SqlSessionFactoryBuilder().build(inputStream);
    
    scanner = new Scanner(System.in);
    commandMap = new HashMap<>();
    productDao.setSqlSessionFactory(sqlSessionFactory);
    
    
    // java02.test19.server 패키지 및 하위 패키지의 모든 클래스를 뒤진다.
    // @Component 애노테이션이 붙은 클래스를 찾는다.
    // 해당 클래스의 인스턴스를 생성하여 보관한다.
    appCtx = new ApplicationContext("java02.test19.server");
    
    
    
    Reflections reflections = 
        new Reflections("java02.test19.server.command");
    Set<Class<?>> clazzList = 
        reflections.getTypesAnnotatedWith(Component.class);
    
    Object command = null;
    Component component = null;
    Method method = null;
    CommandInfo commandInfo = null;
    Command commandAnno = null;
    
    for (Class clazz : clazzList) {
      component = (Component) clazz.getAnnotation(Component.class);
      command = clazz.newInstance();

      Set<Method> methods = ReflectionUtils.getMethods(
          clazz,
          ReflectionUtils.withAnnotation(Command.class));
      
      for (Method m :methods) {
        commandAnno = m.getAnnotation(Command.class);
        commandInfo = new CommandInfo();
        commandInfo.instance = command;
        commandInfo.method = m;
        commandMap.put(commandAnno.value(), commandInfo);
      }
      
      try { 
        method = clazz.getMethod("setProductDao", ProductDao.class);
        method.invoke(command, productDao);
      } catch (Exception e) {}
      
      try { 
        method = clazz.getMethod("setScanner", Scanner.class);
        method.invoke(command, scanner);
      } catch (Exception e) {}
    }
  }
  
  class ServiceThread extends Thread {
    Socket socket;
    Scanner in;
    PrintStream out;
    
    public ServiceThread(Socket socket) throws Exception {
      this.socket = socket;
      in = new Scanner(socket.getInputStream());
      out = new PrintStream(socket.getOutputStream());
    }
    
    private void parseQueryString(
        String query, HashMap<String,Object> map) {
      //예) query :  name=제품명&qty=20&mkno=6
      // ==> {"name=제품명","qty=20","mkno=6"}
      String[] entryList = query.split("&");
      String[] token = null;
      
      for (String entry : entryList) {
        token = entry.split("="); // 예)name=제품명
        map.put(token[0], token[1]);
      }
    }
    
    @Override
    public void run() {
      CommandInfo commandInfo = null;
      try {
        String[] token = in.nextLine().split("\\?");
        commandInfo = commandMap.get(token[0]);
        
        if (commandInfo == null) {
          out.println("해당 명령을 지원하지 않습니다.");
          out.println();
          return;
        }
        
        HashMap<String,Object> params = 
            new HashMap<String,Object>();
        
        params.put("out", out);
        
        if (token.length > 1) {
          parseQueryString(token[1], params);
        }
        
        commandInfo.method.invoke(commandInfo.instance, params);
        
      } catch (Exception e) {
        e.printStackTrace();
        out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
        out.println();
        
      } finally {
        try {in.close();} catch (Exception e) {}
        try {out.close();} catch (Exception e) {}
        try {socket.close();} catch (Exception e) {}
      }
    }
  }
  
  public void service() throws Exception {
    ServerSocket serverSocket = new ServerSocket(8888);
    Socket socket = null;
    
    while (true) {
      socket = serverSocket.accept();
      new ServiceThread(socket).start();
    }
  }
  
  public void destroy() {
    scanner.close();
  }

  private String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }

  public static void main(String[] args) throws Exception {
    ProductMgtServer01 app = new ProductMgtServer01();
    app.init();
    app.service();
    app.destroy();
  }

}







