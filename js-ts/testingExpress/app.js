const express = require("express");

const app = express();

app.use(express.json());

app.get("/foo", (req, res) => {
  res.json({ john: "doe" });
});

app.post("/foo", (req, res) => {
  if (!req.body) res.sendStatus(400);
  else res.json({ foo: 0 });
});

module.exports = app;
