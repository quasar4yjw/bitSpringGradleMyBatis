package java02.test21.server.command;

import java.io.PrintStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import java02.test19.server.annotation.Component;
import java02.test21.server.dao.ProductDao;
import java02.test21.server.annotation.Command;

@Component("common")
public class GeneralCommand {
	@Autowired
  ProductDao productDao;

  @Command("help")
  public void help(Map<String, Object> params) throws Exception {
    PrintStream out = (PrintStream)params.get("out");
    out.println("list");
    out.println("view 제품번호");
    out.println("add");
    out.println("delete 제품번호");
    out.println("update 제품번호");
    out.println();
  }
  
  
}









