let canvas;
let radius;
let itmoRed = "#1e1eec";

window.addEventListener("load",
    function () {
        canvas = document.getElementById("areaCanvas");
        radius = document.getElementById("r_input").value;
        alert(radius);
        drawGraph(canvas, radius, itmoRed);
        canvas.addEventListener("click", function getPosition(event) {
            let [x, y] = convertFromCanvasToPoint(canvas, event.clientX, event.clientY);
            sendValues(x, y, radius);
        });
    });
