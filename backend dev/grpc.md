# What is grpc

A rpc framework developed at google, based on protocol buffers and http/2

## Grpc mode

- unary rpc's : classic client server architecture : single response to a single request.
- server streaming rpc's : stream of responses to single requests.

```proto
    rpc SayHello(HelloRequest) returns (HelloResponse);
```

```proto
    rpc LotsOfReplies(HelloRequest) returns (stream HelloResponse);
```

- client streaming rpc's : client writes a sequence of messages and sends them to the server using a stream, once the client finished sending messages, the server reads them and return its response.

```proto
rpc LotsOfGreetings(stream HelloRequest) returns (HelloResponse);
```

- bidirectional streaming : both sides send sequence of messages using read write streams (2 streams that operate independently)

```proto
    rpc BidiHello(stream HelloRequest) returns (stream HelloResponse);
```

## How to use it

Create a proto file containing `services`, that contains procedures that can be called by the client

```proto
    service TodoService {
    rpc createTodo(TodoItem) returns (TodoItem);
    rpc getTodos(noParams) returns (TodoItemList);
}
```

Add the message type definitions in the proto file as well

```proto
    message TodoItem {
        int32 id = 1;
        string text = 2;
    }

    message TodoItemList {
        repeated TodoItem = 1;
    }

    message noParams {} // analog to void

```

Code the server in any of the supported languages, you should define the functions u specified in the services

```js
const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const packageDefinition = protoLoader.loadSync("todo.proto", {});
const grpcObject = grpc.loadPackageDefinition(packageDefinition);
const { todoPackage } = grpcObject;

const server = new grpc.Server();
server.bind("0.0.0.0:4040", grpc.ServerCredentials.createInsecure());
server.addService(todoPackage.Todo.service, { getTodos, createTodo });
server.start();

function getTodos(call, callback) {
  // call has access to request related stuff
  // callback is the called when responding to the client
}
function createTodo(call, callback) {}
```

Code the client and call the procedures

```js
const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const packageDefinition = protoLoader.loadSync("todo.proto", {});
const grpcObject = grpc.loadPackageDefinition(packageDefinition);
const { todoPackage } = grpcObject;

const client = new todoPackage.Todo(
  "localhost:4040",
  grpc.credentials.createInsecure()
);

client.createTodo({ title: "foobar" }, (err, resp) => {});
client.getTodos({}, (err, resp) => {});
```

## Pros

- fast and compact due to using protocol buffers and http 2
- one client library maintained by google
- progress feedback on streaming rpc (example use case : view uploading percentage, can't be done in rest)

## Cons

- Sticking with a schema
- Heavy client (runs the proto compiler each time the process starts)
- Some layer 7 proxies do not support it yet
- no native browser supports
