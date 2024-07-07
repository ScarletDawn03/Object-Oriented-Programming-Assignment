public class UserInfo {

		private String userID, name;
		

		public UserInfo() {
			name = " ";
			userID = " ";
		}

		public String getName() {
			return name;
		}
		
		public String getUserID() {
			return userID;
		}
		

		public void setName(String name) {
			this.name = name;
		}
		
		//Validate the name and assign userID
		public void detectSpace(String name) {
			setName(name);
			
			if (name.contains(" ")) {
				int surname = name.lastIndexOf(" ");
				userID = name.substring(0, 1).toUpperCase() + 
						name.substring( surname+ 1, surname + 2).toUpperCase() + name.substring(surname + 2);
			}
			else userID = "guest";
			
			/*if (name.contains(" ")) {
				String[] fullName = name.split("[ ]");
				
				userID = fullName[0].substring(0, 1).toUpperCase() + 
						fullName[fullName.length - 1].substring(0, 1).toUpperCase()
						+ fullName[fullName.length - 1].substring(1);
			}
			else userID = "guest";
			
		}
*/
  }
}