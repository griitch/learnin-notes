const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const packageDefinition = protoLoader.loadSync("todo.proto", {});
const grpcObject = grpc.loadPackageDefinition(packageDefinition);
const { todoPackage } = grpcObject;

const client = new todoPackage.Todo(
  "localhost:4040",
  grpc.credentials.createInsecure()
);

if (process.argv[2] === "add") {
  client.createTodo(
    {
      title: process.argv[3] || "test",
      complete: false,
    },
    (err, resp) => {
      console.log(resp);
    }
  );
} else {
  client.getTodos({}, (err, resp) => {
    if (err) console.log(err);
    console.log(resp);
  });
}
