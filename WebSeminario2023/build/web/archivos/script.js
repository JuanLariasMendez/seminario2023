/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Habilita o desabilita los botones de guardar y modificar en el formulario de ingreso
function habilita(idElemento, idElemento2){
    document.getElementById(idElemento).style.display="block";
    document.getElementById(idElemento2).style.display="none";
}
//para que al dar clic la opcion pase automaticamente al siguiente campo, sin dar enter
function foco(idElemento){
    document.getElementById(idElemento).focus();
}

//Para que al dar enter, no se ejecute la funcion de guardar o moficar, hasta que el proceso llegeue al boton, y no ejecute cuando aun esta en algun textbox
function focoBoton(botonGuardar,botonModificar){
    if (document.getElementById(botonGuardar).style.display==="block"){
        document.getElementById(botonGuardar).focus();
    }
    
    if (document.getElementById(botonModificar).style.display==="block"){
    document.getElementById(botonModificar).focus();
    }
}