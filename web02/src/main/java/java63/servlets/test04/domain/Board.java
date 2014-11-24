/* Value Object
 * => Class 문법을 사용하여 사용자 정의 데이터 타입 만들기
 * 
 * 1) Serializable 인터페이스 구현
 *    => SerialVersionUID 스태틱 변수 선언
 * 
 * 2) 인스턴스 변수 선언
 * 
 * 3) setter/getter 생성
 * 
 * 4) 기본 생성자와 파라미터 값을 받는 생성자 선언
 * 
 * 5) equals()/hashCode() 메서드 오버라이딩
 * 
 * 6) toString() 오버라이딩
 */
package java63.servlets.test04.domain;

import java.io.Serializable;

public class Board implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  
  protected int       no;
  protected String    breed;
  protected String    findPlace;
  protected String    findDate;
  protected String    gender;
  protected String    title;
  protected String    content;
  protected String    id;
  
  
  public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getBreed() {
		return breed;
	}


	public void setBreed(String breed) {
		this.breed = breed;
	}


	public String getFindPlace() {
		return findPlace;
	}


	public void setFindPlace(String findPlace) {
		this.findPlace = findPlace;
	}


	public String getFindDate() {
		return findDate;
	}


	public void setFindDate(String findDate) {
		this.findDate = findDate;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	


	@Override
	public String toString() {
		return "Board [no=" + no + ", breed=" + breed + ", findPlace=" + findPlace
				+ ", findDate=" + findDate + ", gender=" + gender + ", title=" + title
				+ ", content=" + content + ", id=" + id + "]";
	}


	public Board(int no, String breed, String findPlace, String findDate,
			String gender, String title, String content, String id) {
		super();
		this.no = no;
		this.breed = breed;
		this.findPlace = findPlace;
		this.findDate = findDate;
		this.gender = gender;
		this.title = title;
		this.content = content;
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((findDate == null) ? 0 : findDate.hashCode());
		result = prime * result + ((findPlace == null) ? 0 : findPlace.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + no;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (findDate == null) {
			if (other.findDate != null)
				return false;
		} else if (!findDate.equals(other.findDate))
			return false;
		if (findPlace == null) {
			if (other.findPlace != null)
				return false;
		} else if (!findPlace.equals(other.findPlace))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (no != other.no)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	public Board() {}
 

  


	@Override
  public Board clone() throws CloneNotSupportedException {
    return (Board) super.clone();
  }
}
  
  













