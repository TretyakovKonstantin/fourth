function sendValues(x, y, r) {

    let inputR = document.getElementById("r");
    let inputX = document.getElementById("x");
    let inputY = document.getElementById("y");

    inputR.value = r;
    inputX.value = x;
    inputY.value = y;
    alert(inputY.value+"пипчек");
    alert(y+" пипка");

    document.getElementById("submit").click();
}
