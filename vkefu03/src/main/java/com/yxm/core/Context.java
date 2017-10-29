package com.yxm.core;

public class Context {
	
	public static final String SESSION_USER = "sessionUser";				//session用的用户
  
	public enum NameSpaceEnum{
		IM("/im/user"),
		AGENT("/im/agent");
		
		private String namespace;
		public String getNamespace() {
			return namespace;
		}

		public void setNamespace(String namespace) {
			this.namespace = namespace;
		}
		
		NameSpaceEnum(String namespace){
			this.namespace = namespace ;
		}
		
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum MessageTypeEnum{
		NEW,
		MESSAGE, 
		END,
		TRANS, STATUS , AGENTSTATUS , SERVICE, WRITING;
		
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
}
