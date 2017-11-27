function startTime() {
    let second = 1000;
    let n = 10;
    let today = new Date();
    let hours = today.getHours();
    let minutes = today.getMinutes();
    let seconds = today.getSeconds();
    minutes = checkTime(minutes);
    seconds = checkTime(seconds);
    document.getElementById('clock').innerHTML =
        hours + ":" + minutes + ":" + seconds;
    let time = setTimeout(startTime, second * n);
}

function checkTime(i) {
    return (i < 10) ? "0" + i : i;
}

window.addEventListener("load", startTime);
