package lt.vtmc.groups.dto;

public class CreateGroupCommand {

	private String[] userList;
	
	private String description;
	
	private String groupName;
	
	private String[]docTypesToCreate;
	
	private String[]docTypesToSign;
	/**
	 * Constructor method for CreateGroupCommand
	 * 
	 * @param userList
	 * @param description
	 */
	public CreateGroupCommand(String[] userList, String description, String groupName, String[]docTypesToSign, String[]docTypesToCreate ) { //
		super();
		this.groupName = groupName;
		this.description = description;
		this.userList = userList;
		this.docTypesToCreate = docTypesToCreate;
		this.docTypesToSign = docTypesToSign;
	}
	/**
	 * 
	 * @param name
	 */
	public String[] getUserList() {
		return userList;
	}
	/**
	 * 
	 * @return name
	 */
	public void setUserList(String[] userList) {
		this.userList = userList;
	}
	/**
	 * 
	 * @param description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 
	 * @return description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String[] getDocTypesToCreate() {
		return docTypesToCreate;
	}
	public void setDocTypesToCreate(String[] docTypesToCreate) {
		this.docTypesToCreate = docTypesToCreate;
	}
	public String[] getDocTypesToSign() {
		return docTypesToSign;
	}
	public void setDocTypesToSign(String[] docTypesToSign) {
		this.docTypesToSign = docTypesToSign;
	}
	
	
	
}
