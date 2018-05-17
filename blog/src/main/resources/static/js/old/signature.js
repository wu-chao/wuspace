+function ($) {
    'use strict';

    function onDocumentTouchStart(event) {
        if (event.touches.length == 1) {
            event.preventDefault();
            // Faking double click for touch devices
            var now = new Date().getTime();
            if (now - timeOfLastTouch < 250) {
                reset();
                return;
            }

            timeOfLastTouch = now;
            mouseX = event.touches[0].pageX;
            mouseY = event.touches[0].pageY;
            isMouseDown = true;
        }
    }

    function onDocumentTouchMove(event) {
        if (event.touches.length == 1) {
            event.preventDefault();
            mouseX = event.touches[0].pageX;
            mouseY = event.touches[0].pageY;
        }
    }

    function onDocumentTouchEnd(event) {
        if (event.touches.length == 0) {
            event.preventDefault();
            isMouseDown = false;
        }
    }

    var canvasDiv = document.getElementById('canvasDiv');
    var canvas = document.createElement('canvas');

    document.addEventListener('touchmove', onDocumentTouchMove, false);

    var point = {};

    point.notFirst = false;

    if (typeof G_vmlCanvasManager != 'undefined') {
        canvas = G_vmlCanvasManager.initElement(canvas);
    }

    var context;

    var img = new Image();
    img.src = $('#pdfToImage').val();

    img.onload = function () {
        $('#width').val(this.width);
        $('#height').val(this.height);

        canvas.setAttribute('width', this.width);
        canvas.setAttribute('height', this.height);
        canvas.setAttribute('id', 'canvas');
        canvasDiv.appendChild(canvas);

        context = canvas.getContext("2d");

        var ptrn = context.createPattern(img, 'no-repeat');
        context.fillStyle = ptrn;

        context.fillRect(0, 0, canvas.width, canvas.height);
    };

    canvas.addEventListener("touchstart", function (e) {
        var mouseX = e.pageX - this.offsetLeft;
        var mouseY = e.pageY - this.offsetTop;
        paint = true;
        addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop);
        redraw();
    });

    canvas.addEventListener("touchend", function (e) {
        paint = false;
    });

    canvas.addEventListener("touchmove", function (e) {
        if (paint) {
            addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true);
            redraw();
        }
    });

    canvas.addEventListener("mousedown", function (e) {
        var mouseX = e.pageX - this.offsetLeft;
        var mouseY = e.pageY - this.offsetTop;
        paint = true;
        addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop);
        redraw();
    });

    canvas.addEventListener("mousemove", function (e) {
        if (paint) {
            addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true);
            redraw();
        }
    });

    canvas.addEventListener("mouseup", function (e) {
        paint = false;
    });

    canvas.addEventListener("mouseleave", function (e) {
        paint = false;
    });

    var clickX = new Array();
    var clickY = new Array();
    var clickDrag = new Array();
    var paint;

    function addClick(x, y, dragging) {
        clickX.push(x);
        clickY.push(y);
        clickDrag.push(dragging);
    }

    function redraw() {
        context.strokeStyle = "#df4b26";
        context.lineJoin = "round";
        context.lineWidth = 2;

        while (clickX.length > 0) {
            point.bx = point.x;
            point.by = point.y;
            point.x = clickX.pop();
            point.y = clickY.pop();
            point.drag = clickDrag.pop();
            context.beginPath();
            if (point.drag && point.notFirst) {
                context.moveTo(point.bx, point.by);
            } else {
                point.notFirst = true;
                context.moveTo(point.x - 1, point.y);
            }

            context.lineTo(point.x, point.y);
            context.closePath();
            context.stroke();
        }
    }

    var clear = document.getElementById("btn_clear");
    var submit = document.getElementById("btn_submit");

    clear.addEventListener("click", function () {
        canvas.width = canvas.width;
    });

    submit.addEventListener("click", function () {
        $("#qmimg").attr("src", canvas.toDataURL("image/png"));

        $('#imageData').val(canvas.toDataURL("image/png"));
        $('#signatureForm').submit();
    });

}(jQuery);