const server = require("http").createServer()
const io = require("socket.io")(server, {
    cors: {
        origin: "*",
    },
})

io.sockets.on("connection", (socket) => {
    console.log(socket.id)
    socket.on("join", (username) => {
        console.log("join " + username)
        socket.join(username)
    })

    socket.on("send", (message) => {
        console.log(message)
        socket.to(message.to).emit("recieve", message)
    })

    socket.on("leave", (username) => {
        console.log("leave " + username)
        socket.leave(username)
    })

    socket.on("disconnect", (username) => {
        socket.leave(username)
    })
})

server.listen(8000)
