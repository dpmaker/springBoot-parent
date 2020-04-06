/**
 * 
 */
package bean;

import java.io.Serializable;


/**
 * @author d_mail_p@sina.com
 *
 */

public class Book implements Serializable{

	private int id;
	private String 	bookName;
	private String userId;
	
	private User user;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	private String bookField;
	/**
	 * @return the bookField
	 */
	public String getBookField() {
		return bookField;
	}
	/**
	 * @param bookField the bookField to set
	 */
	public void setBookField(String bookField) {
		this.bookField = bookField;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
