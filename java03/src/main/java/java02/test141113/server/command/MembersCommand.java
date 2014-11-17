package java02.test141113.server.command;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java02.test141113.server.Members;
import java02.test141113.server.MembersDao;
import java02.test141113.server.annotation.Command;
import java02.test141113.server.annotation.Component;

@Component
public class MembersCommand {
	MembersDao membersDao;
	Scanner scanner;

	public void setMembersDao(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	@Command("add")
	public void add(Map<String, Object> params) {
		PrintStream out = (PrintStream)params.get("out");

		try {
			Members members = new Members();
			members.setId((String)params.get("id"));
			members.setPwd((String)params.get("pwd"));
			members.setEmail((String)params.get("email"));
			members.setName((String)params.get("name"));
			members.setTel((String)params.get("tel"));
			members.setFax((String)params.get("fax"));
			members.setAddr((String)params.get("addr"));
			members.setPhot((String)params.get("phot"));
			members.setAno(Integer.parseInt((String)params.get("ano")));

			membersDao.insert(members);
			out.println("저장하였습니다.");
			out.println();

		} catch (Exception e) {
			e.printStackTrace();
			out.println("서버가 바쁩니다. 잠시 후 다시 시도하세요.");
			out.println();
		}
	}

	@Command("delete")
	public void delete(Map<String, Object> params) {
		PrintStream out = (PrintStream) params.get("out");
		String id = (String)params.get("id");

		Members members = membersDao.selectOne(id);
		if (members == null) {
			out.println("해당 아이디의 멤버정보를 찾을 수 없습니다.");
			out.println();
			return;
		}

		membersDao.delete(id);
		out.println("삭제하였습니다.");
		out.println();
	}

	@Command("list")
	public void list(Map<String, Object> params) {
		int pageNo = 0;
		int pageSize = 0;

		if (params.get("pageNo") != null) {
			pageNo = Integer.parseInt((String)params.get("pageNo"));
			pageSize = 3;
		}

		if (params.get("pageSize") != null) {
			pageSize = Integer.parseInt((String)params.get("pageSize"));
		}

		PrintStream out = (PrintStream)params.get("out");

		for (Members members : membersDao.selectList(pageNo, pageSize)) {
			out.printf("%-10s %-20s %-20s %-15s\n", 
					members.getId(), 
					members.getName(), 
					members.getEmail(), 
					members.getTel());
		}

		out.println();
	}

	@Command("update")
	public void update(Map<String, Object> params) {
		PrintStream out = (PrintStream)params.get("out");

		try {
			Members members = new Members();
			members.setId((String)params.get("id"));
			members.setPwd((String)params.get("pwd"));
			members.setEmail((String)params.get("email"));
			members.setName((String)params.get("name"));
			members.setTel((String)params.get("tel"));
			members.setFax((String)params.get("fax"));
			members.setAddr((String)params.get("addr"));
			members.setPhot((String)params.get("phot"));
			members.setAno(Integer.parseInt((String)params.get("ano")));

			membersDao.update(members);
			out.println("변경하였습니다.");
			out.println();

		} catch (Exception e) {
			e.printStackTrace();
			out.println("서버가 바쁩니다. 잠시 후 다시 시도하세요.");
			out.println();
		}
	}

	@Command("view")
	public void view(Map<String, Object> params) throws Exception {
		String id = (String)params.get("id");

		Members members = membersDao.selectOne(id);

		PrintStream out = (PrintStream)params.get("out");

		if (members == null) {
			out.println("해당 번호의 제품 정보를 찾을 수 없습니다.");
			out.println();
			return;
		}

		out.println("아이디: " + id);
		out.println("패스워드: " + members.getPwd());
		out.println("이메일: " + members.getEmail());
		out.println("주문자명: " + members.getName());
		out.println("전화번호: " + members.getTel());
		out.println("팩스번호: " + members.getFax());
		out.println("상세주소: " + members.getAddr());
		out.println("주문자 사진: " + members.getPhot());
		out.println("주소 번호: " + members.getAno());
		out.println();
	}
}








