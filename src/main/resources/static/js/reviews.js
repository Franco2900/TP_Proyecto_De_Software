const estrellas = document.getElementsByClassName("valoracion-estrella");

// Hace que funcione el formulario con las estrellas
for(var i = 0; i < estrellas.length; i++){
    estrellas[i].addEventListener("mouseover", (event) => {
        const est = document.getElementsByClassName("valoracion-estrella");
        var invert = false;
        for(var j = 0; j < est.length; j++){
            if(invert){
                est[j].classList.remove("fa-star");
                est[j].classList.add("fa-star-o");
            }
            else{
                est[j].classList.remove("fa-star-o");
                est[j].classList.add("fa-star");
            }
            if(est[j] == event.target){
                invert = true;
            }
        }
    });

    estrellas[i].addEventListener("mouseout", (event) => {
        const est = document.getElementsByClassName("valoracion-estrella");
        for(var j = 0; j < est.length; j++){
            if(est[j].classList.contains("active")){
                est[j].classList.remove("fa-star-o");
                est[j].classList.add("fa-star");
            }
            else{
                est[j].classList.remove("fa-star");
                est[j].classList.add("fa-star-o");
            } 
        }
    });

    estrellas[i].addEventListener("click", (event) => {
        const est = document.getElementsByClassName("valoracion-estrella");
        var invert = false;
        for(var j = 0; j < est.length; j++){
            if(invert){
                est[j].classList.add("fa-star-o");
                est[j].classList.remove("fa-star");
                est[j].classList.remove("active");
            }
            else{
                est[j].classList.remove("fa-star-o");
                est[j].classList.add("fa-star");
                est[j].classList.add("active");
            }
            if(est[j] == event.target){
                invert = true;
                document.getElementById("valoracionReview").value = j + 1;
            }
        }
    });
}

