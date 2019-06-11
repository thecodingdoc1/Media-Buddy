package constants;

public enum UserType { // only static final, compile time constants, so no isntance of class
		USER("user"),
		EDITOR("EDITOR"),
		CHIEF_EDITOR("chiefeditor");
		
		private UserType(String name) {
			this.name = name;
		}
		
		private String name;
		public String getName() {
			return name;
		}
		

}
