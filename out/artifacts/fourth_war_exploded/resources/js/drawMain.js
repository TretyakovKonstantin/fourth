let canvas;
let radius;
let itmoRed = "#1e1eec";

window.addEventListener("load",
    function () {
        canvas = document.getElementById("areaCanvas");
        radius = document.getElementById("r_input").value;
        document.getElementById("radius").value = radius;
        drawGraph(canvas, radius, itmoRed);
        canvas.addEventListener("click", function getPosition(event) {
            let [x, y] = convertFromCanvasToPoint(canvas, event.clientX, event.clientY);
            sendValues(x, y, radius);
        });
    });

function onRChange(){
    radius = document.getElementById('r_input').value;
    drawGraph(canvas, document.getElementById('r_input').value, itmoRed);
    document.getElementById("radius").value = document.getElementById('r_input').value;
}
