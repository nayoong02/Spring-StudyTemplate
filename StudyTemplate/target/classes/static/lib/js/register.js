var registerVue = new Vue({
	el : '#registerVue',
	data : {
		userId : '',
		password : '',
		re_password : '',
		userName : '',
		department : '',
		position : '',
		
		isIDSpecialCheck : true,
		isPWSpecialCheck : true,
		isPWSameCheck : true,
		isIDNullCheck : true
	},
	watch : {
		"userId" : function(newVal, oldVal) {
			this.checkRegisterId();
		},
		"password" : function(newVal, oldVal) {
			this.checkRegisterPW();
		},
		"re_password" : function(newVal, oldVal) {
			this.checkPWSame();
		}
	},
	methods : {
		
		register : function() {
			
			var self = this;
			
			var result = false;
			
			result = this.checkRegisterNull();
			
			if (result) {
				var encodedUrl = encodeURI("member/jdbc/register");
				
				$.ajax({
					type : "POST",
					url : encodedUrl,
					data : {
						userId : self.userId,
						password : self.password,
						userName : self.userName,
						department : self.department,
						position : self.position
					},
					success : function(data) {
						if(data) {
							console.log(data);
							window.alert("회원 가입이 완료되었습니다 :)");
							location.href = 'login';
						}
					},
					error : function() {
						
					}
				});
			}
		},
		
		/** id 중복체크 **/
		checkDuplicatedId : function() {
			var userId = this.userId;
			var encodedUrl = encodeURI("member/jdbc/idCheck");
			
			$.ajax({
				type : "GET",
				url : encodedUrl,
				data : {
					userId : this.userId
				},
				success : function(cnt) { //controller에서 넘어온 cnt 
					if(userId.length == 0) {
						alert("아이디를 먼저 입력하세요.");
					}
					if(userId.length != 0 && cnt == 0) { //0이면 사용 가능한 아이디 
						alert("사용 가능한 아이디입니다.");
					}
					if(cnt == 1){ //1이면 이미 존재하는 아이디 
						alert("중복된 아이디입니다.");
					}
				},
				error : function() {
					
				}
			});
		},
		
		/** 아이디 양식 체크 **/
		checkRegisterId : function() {
			var userId = this.userId;
			var IdRegType = /^[a-z0-9]{4,8}$/;
			
			this.isIDSpecialCheck = true;
			
			if(!IdRegType.test(userId) && userId.length != 0){
				this.isIDSpecialCheck = false;
				return false;
			}
		},
		
		/** 비밀번호 양식 체크 **/
		checkRegisterPW : function() {
			var password = this.password;
			var pwRegType = /^[a-z0-9]{8,}$/;
			
			this.isPWSpecialCheck = true;
//			this.isPWSameCheck = true;
			
			if(!pwRegType.test(password) && password.length != 0) {
				this.isPWSpecialCheck = false;
				return false;
			}
		},
		
		/** 비밀번호 일치 체크 **/
		checkPWSame : function() {
			var password = this.password;
			var re_password = this.re_password;
			
			this.isPWSameCheck = true;
			
			if(password != re_password) {
				this.isPWSameCheck = false;
				return false;
			}
		},
		
		
		/** 회원가입 input 값들 공백 체크 **/
		checkRegisterNull : function() {
			var userId = this.userId;
			var password = this.password;
			var re_password = this.re_password;
			var userName = this.userName;
			var department = this.department;
			var position = this.position;
			
			if(userId.length == 0){
				alert("아이디를 입력하세요.");
				$("#userId").focus();
				return false;
			}
			
			if(password.length == 0){
				alert("비밀번호를 입력하세요.");
				$("#password").focus();
				return false;
			}
			
			if(re_password.length == 0){
				alert("비밀번호가 일치하는지 확인하세요.");
				$("#re_password").focus();
				return false;
			}
			
			if(userName.length == 0){
				alert("이름을 입력하세요.");
				$("#userName").focus();
				return false;
			}
			
			if(department.length == 0){
				alert("부서를 입력하세요.");
				$("#department").focus();
				return false;
			}
			
			if(position.length == 0){
				alert("직급을 입력하세요.");
				$("#position").focus();
				return false;
			}

			return true;
		}
		
	}
})