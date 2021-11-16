function mostrarPassword(){
	var pass = document.getElementById("floatingPassword");
      if(pass.type == "password"){
          pass.type = "text";
          document.getElementById("pass").src="../../img/visible.png";
      }else{
          pass.type = "password";
          document.getElementById("pass").src="../../img/invisible.png";
      }
}