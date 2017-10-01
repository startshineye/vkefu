package com.yxm.core;

public class Context {
  
	public enum NameSpaceEnum{
		IM("/im/user");
		
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
}
