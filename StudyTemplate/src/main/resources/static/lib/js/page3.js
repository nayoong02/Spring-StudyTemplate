var page3Vue = new Vue({
	el : '#page3Vue',
	data : {
		memberList : []
	},
	mounted : function() {
		this.getMemberList();
//		this.addMember();
	},
	methods : {
		getMemberList : function() {
			
			var self = this;
			var encodedUrl = encodeURI("member/jpa/memberlistVue");
			
			$.ajax({		
				type : "POST",
				url : encodedUrl,
				dataType : "json",
				success : function(data){			
					console.log(data.RESULT_DATA.MEMBERLIST);
					self.memberList = data.RESULT_DATA.MEMBERLIST;
				},
				error : function(){
					
				}	
			});
		},
//		addMember : function() {
//			
//			var self = this;
//			var encodedUrl = encodeURI("member/jpa/addMember")
//			
//			$.ajax({		
//				type : "POST",
//				url : encodedUrl,
//				dataType : "json",
//				success : function(data){			
//					console.log(data.RESULT_DATA.MEMBERLIST);
//					self.memberList = data.RESULT_DATA.MEMBERLIST;
//				},
//				error : function(){
//					
//				}	
//			});
//		}
	
	}
})