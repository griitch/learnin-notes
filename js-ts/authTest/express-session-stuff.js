require("dotenv").config();
const express = require("express");
const mongoose = require("mongoose");
const session = require("express-session");

const MongoStore = require("connect-mongo");

const app = express();

const mongoDb = process.env.mongoDb;

// this is a boilerpolate to use the same mongodb connection for the app and for the sessions

const dbCnx = mongoose
  .connect(mongoDb)
  .then((cnx) => {
    console.log("cnx established");
    return cnx.connection.getClient();
  })
  .catch(console.log);

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const sessionStore = MongoStore.create({
  collectionName: "session-stuff",
  clientPromise: dbCnx,
});

app.use(
  session({
    secret: "idk what this secret does but ok",
    resave: false,
    saveUninitialized: true,
    store: sessionStore,
    cookie: { maxAge: 1000 * 60 * 60 * 24 }, // 1 day
  })
);

app.get("/", (req, res) => {
  if (req.session.count) {
    req.session.count++;
  } else {
    req.session.count = 1;
  }
  res.send(`<h2>foo baz tar, visited ${req.session.count}</h2>`);
});

app.listen(3000, () => console.log("server workin"));
