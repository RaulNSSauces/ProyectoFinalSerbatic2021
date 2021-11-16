function validarRegistro(){
	var entradaOk = true;
	//VALIDACIÓN DEL EMAIL
	var email = document.getElementById("email").value;
	if(email.length === 0){
		document.getElementById("alerta1").innerHTML="Este campo es obligatorio";
        document.getElementById("alerta1").style.color="red";
        entradaOk = false;
	}else if(!(/^\w+([\.\+\-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(email))){
		document.getElementById("alerta1").innerHTML="Introduce una dirección válida de correo";
		document.getElementById("alerta1").style.color="red";
		entradaOk = false;
	}else{
		document.getElementById("alerta1").innerHTML="";
	}
	//VALIDACIÓN DE LA CONTRASEÑA
	var clave = document.getElementById("clave").value;
	if(clave.length === 0){
		document.getElementById("alerta2").innerHTML="Este campo es obligatorio";
        document.getElementById("alerta2").style.color="red";
        entradaOk = false;
	}else{
		document.getElementById("alerta2").innerHTML="";
	}
	//VALIDACIÓN DE LA DOBLE CONTRASEÑA
	var claveConfirmada = document.getElementById("claveConfirmada").value;
	if(claveConfirmada.length === 0){
		document.getElementById("alerta3").innerHTML="Este campo es obligatorio";
        document.getElementById("alerta3").style.color="red";
        entradaOk = false;
	}else if(claveConfirmada !== clave){
		document.getElementById("alerta3").innerHTML="Las contraseñas no coinciden";
        document.getElementById("alerta3").style.color="red";
        entradaOk = false;
	}else{
		document.getElementById("alerta3").innerHTML="";
	}
    return entradaOk;
}