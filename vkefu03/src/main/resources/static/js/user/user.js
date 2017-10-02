    //var socket = io.connect('${schema}://'+hostname+':${port}/im/user?userid=${userid!''}&userName=${userName!''}&session=${sessionid!''}');
     var socket = io.connect('http://localhost:8078/im/user?userId='+userId+'&userName='+userName+'&sessionId='+sessionId+'');
    //var socket = io.connect('http://localhost:8078');
    socket.on('connect',function() {
        output('<span class="connect-msg">Client has connected to the server!</span>');
    });
    
    socket.on('message', function(data) {
        output('<span class="username-msg">' + data.username + ' : </span>'
                + data.message);
    });
    
    socket.on('disconnect',function() {
        output('<span class="disconnect-msg">The client has disconnected! </span>');
    });
    
    function sendDisconnect() {
        socket.disconnect();
    };
    
    function sendMessage() {
       // var userName = $("#name").val();
        var message = $('#msg').val();
        $('#msg').val('');
        socket.emit('message', {
            username : userName,
            message : message
        });
    };
    
    function output(message) {
        var currentTime = "<span>" + new Date() + "</span>";
        var element = $("<div>" + currentTime + " " + message + "</div>");
        $('#console').prepend(element);
    };
