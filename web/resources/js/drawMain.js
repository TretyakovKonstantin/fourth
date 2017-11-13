let canvas;
let radius;
let itmoRed = "#EC0B43";

window.addEventListener("load",
    function () {
        canvas = document.getElementById("areaCanvas");
        radius = document.getElementById("r_input").value;
        drawGraph(canvas, radius, itmoRed);
        canvas.addEventListener("click", function getPosition(event) {
            let [x, y] = convertFromCanvasToPoint(canvas, event.clientX, event.clientY);
            sendValues(x, y, radius);
        });
        let rButtons = Array.from(document.getElementsByName("r"));
        for (let button of rButtons) {
            button.onclick = function () {
                radius = document.querySelector('input[name="r"]:checked').value;
                drawGraph(canvas, radius, itmoRed);
            };
        }
    });
