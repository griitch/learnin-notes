const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const packageDefinition = protoLoader.loadSync("todo.proto", {});
const grpcObject = grpc.loadPackageDefinition(packageDefinition);
const { todoPackage } = grpcObject;

const server = new grpc.Server();
server.bind("0.0.0.0:4040", grpc.ServerCredentials.createInsecure());
server.addService(todoPackage.Todo.service, { getTodos, createTodo });
server.start();

const todos = [];

function getTodos(call, callback) {
  callback(null, { TodoItemList: todos });
}

function createTodo(call, callback) {
  const letodo = {
    id: todos.length + 1,
    title: call.request.title,
  };
  todos.push(letodo);
  callback(null, letodo);
}
