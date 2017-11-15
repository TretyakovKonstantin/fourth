function convertFromPointToCanvas(canvas, xPoint, yPoint)
{
    let xCenter = canvas.width / 2;
    let yCenter = canvas.height / 2;

    let quadrantWidth = (canvas.width - 40) / 2;
    let quadrantHeight = (canvas.height - 40) / 2;

    let xCanvas = xCenter + xPoint / radius * quadrantWidth;
    let yCanvas = yCenter - yPoint / radius * quadrantHeight;

    return [xCanvas, yCanvas];
}

function isPointInArea(x, y, radius)
{
    let inTriangle =
        (x >= -radius && x <= 0) &&
        (y >= 0 && y <= -radius) &&
        (x + y) <= radius;

    let inCircleQuadrant =
        (x >= 0 && y <= 0) &&
        (x * x + y * y <= radius * radius);

    let inSquare =
        (x >= 0 && x <= radius) &&
        (y <= radius / 2 && y >= 0);

    return inCircleQuadrant || inSquare || inTriangle;
}

function convertFromCanvasToPoint(canvas, xClient, yClient)
{
    let rect = canvas.getBoundingClientRect();
    let xCanvas = xClient - rect.left;
    let yCanvas = yClient - rect.top;

    let xCenter = canvas.width / 2;
    let yCenter = canvas.height / 2;

    let quadrantWidth = (canvas.width - 40) / 2;
    let quadrantHeight = (canvas.height - 40) / 2;

    let xPoint = radius * (xCanvas - xCenter) / quadrantWidth;
    let yPoint = radius * (yCenter - yCanvas) / quadrantHeight;

    return [xPoint, yPoint];
}

function clearCanvas(canvas)
{
    canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
}

function drawPoint(canvas, x, y, radius)
{
    let [xCanvas, yCanvas] = convertFromPointToCanvas(canvas, x, y);
    let context = canvas.getContext("2d");
    let pointColor = isPointInArea(x, y, radius) ? "white" : "black";
    context.strokeStyle = pointColor;
    context.fillStyle = pointColor;
    context.fillRect(xCanvas - 1, yCanvas - 1, 2, 2);
}

function drawGraph(canvas, radius, color)
{
    clearCanvas(canvas);
    drawTriangle(canvas, color);
    drawRectangle(canvas, color);
    drawCircleQuadrant(canvas, color);
    drawAxes(canvas, radius);
}

function drawAxes(canvas, radius)
{
    let context = canvas.getContext("2d");
    let width = canvas.width;
    let height = canvas.height;
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.beginPath();
    context.font = "12px Roboto";

    //X
    context.moveTo(0, height / 2);
    context.lineTo(width, height / 2);
    context.moveTo(width, height / 2);
    context.lineTo(width - 15, height / 2 - 5);
    context.moveTo(width, height / 2);
    context.lineTo(width - 15, height / 2 + 5);
    context.fillText("X", width - 10, height / 2 - 10);

    //Y
    context.moveTo(width / 2, 0);
    context.lineTo(width / 2, height);
    context.moveTo(width / 2, 0);
    context.lineTo(width / 2 - 5, 15);
    context.moveTo(width / 2, 0);
    context.lineTo(width / 2 + 5, 15);
    context.fillText("Y", width / 2 + 10, 10);

    //Y palochki
    context.moveTo(width / 2 - 5, 20);
    context.lineTo(width / 2 + 5, 20);

    context.fillText(radius, width / 2 + 10, 25);

    context.moveTo(width / 2 - 5, height / 4 + 10);
    context.lineTo(width / 2 + 5, height / 4 + 10);

    context.fillText((radius / 2), width / 2 + 10, height / 4 + 10);

    context.moveTo(width / 2 - 5, 3 * height / 4 - 10);
    context.lineTo(width / 2 + 5, 3 * height / 4 - 10);

    context.fillText(-(radius / 2), width / 2 + 10, 3 * height / 4 - 10);

    context.moveTo(width / 2 - 5, height - 20);
    context.lineTo(width / 2 + 5, height - 20);

    context.fillText(-radius, width / 2 + 10, height - 15);

    //X palochki
    context.moveTo(20, height / 2 - 5);
    context.lineTo(20, height / 2 + 5);

    context.fillText(-radius, 20, height / 2 + 20);

    context.moveTo(width / 4 + 10, height / 2 - 5);
    context.lineTo(width / 4 + 10, height / 2 + 5);

    context.fillText(-(radius / 2), width / 4 + 10, height / 2 + 20);

    context.moveTo(3 * width / 4 - 10, height / 2 - 5);
    context.lineTo(3 * width / 4 - 10, height / 2 + 5);

    context.fillText((radius / 2), 3 * width / 4 - 10, height / 2 + 20);

    context.moveTo(width - 20, height / 2 - 5);
    context.lineTo(width - 20, height / 2 + 5);

    context.fillText(radius, width - 20, height / 2 + 20);

    context.closePath();
    context.stroke();
}

function drawTriangle(canvas, color)
{
    let context = canvas.getContext("2d");
    let width = canvas.width;
    let height = canvas.height;
    context.beginPath();
    context.moveTo(20, height / 2);
    context.lineTo(width / 2, height / 2);
    context.lineTo(width / 2, 20);
    context.lineTo(20, height / 2);
    context.closePath();
    context.strokeStyle = color;
    context.fillStyle = color;
    context.fill();
    context.stroke();
}

function drawRectangle(canvas, color)
{
    let context = canvas.getContext("2d");
    let width = canvas.width;
    let height = canvas.height;
    context.beginPath();
    context.moveTo(width / 2, height / 2);
    context.lineTo(width / 2, height / 4 + 10);
    context.lineTo(width - 20, height / 4 + 10 );
    context.lineTo(width - 20, height / 2);
    context.closePath();
    context.strokeStyle = color;
    context.fillStyle = color;
    context.fill();
    context.stroke();
}

function drawCircleQuadrant(canvas, color)
{
    let context = canvas.getContext("2d");
    let width = canvas.width;
    let height = canvas.height;
    context.arc(width / 2, height / 2, width / 2 - 20, 2*Math.PI, 5 * Math.PI / 2, false);
    context.closePath();
    context.strokeStyle = color;
    context.fillStyle = color;
    context.fill();
    context.stroke();
}
