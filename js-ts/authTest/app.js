require("dotenv").config();
const express = require("express");
const session = require("express-session");
const passport = require("passport");
const LocalStrategy = require("passport-local").Strategy;
const mongoose = require("mongoose");
const { Schema } = mongoose;
const bcrypt = require("bcryptjs");

// https://levelup.gitconnected.com/everything-you-need-to-know-about-the-passport-local-passport-js-strategy-633bbab6195
// this article got everythang, give it a 20 minutes read before moving on

const mongoDb = process.env.mongoDb;
mongoose.connect(mongoDb);
const db = mongoose.connection;
db.on("error", console.error.bind(console, "mongo connection error"));

const User = mongoose.model(
  "User",
  new Schema({
    username: { type: String, required: true },
    password: { type: String, required: true },
  })
);
/* Passport uses the concept of strategies to authenticate requests.

Strategies can range from verifying username and password credentials,
delegated authentication using OAuth (for example, via Facebook or Twitter),
or federated authentication using OpenID.
Before authenticating requests, the strategy (or strategies) used by an application must be configured. */

// LOCAL STRATEGY ( password + username ) configuration

const strategy = new LocalStrategy(async (username, password, done) => {
  // this callback will be called each time passport.authenticate is called
  try {
    const user = await User.findOne({ username: username });
    if (!user) {
      return done(null, false, { message: "Incorrect username" });
    }
    const isMatching = await bcrypt.compare(password, user.password);
    if (isMatching) {
      // console.log(isMatching); returns true
      return done(null, user);
    }
    // if isMatching is null, auth failed
    return done(null, false, { message: "Incorrect password " });
  } catch (error) {
    console.log(error);
  }
});

passport.use(strategy);
/*
   the post request to login should have the username and password fields, if u wanna
   something different do this
 passport.use({
   usernameField: 'email', passwordField: 'pw'
 }, strategy)
 
 
 */

//-------------------   SESSIONS CRAP    ---------------------------
/* 
Passport will maintain persistent login sessions.
In order for persistent sessions to work the authenticated user must be serialized to the session,
and deserialized when subsequent requests are made ( only store the id in the session not the whole user obj)
*/

passport.serializeUser((user, done) => {
  done(null, user.id);
  // according to the odin project, its done(null,user.id) but i doubt it, i think its user._id but we'll see.
  // update : no, documents in mongoose have an id property that gives the _id
});
// serialize user : from the user, to the cookie

passport.deserializeUser((id, done) => {
  User.findById(id, function (err, user) {
    done(err, user);
  });
});
// deserialize the user : from the cookie, get the user

const app = express();
app.set("view engine", "ejs");

app.use(
  session({
    secret: "cats",
    resave: false,
    saveUninitialized: true,
    // there is another arg i omitted here which is the session store, it's the storage where
    // session data is stored, it's memory by default but in prod you should use a db
  })
);
// app.use(session) should come before the 2 callbacks underenath because they rely on the session
app.use(passport.initialize());
app.use(passport.session());
app.use(express.urlencoded({ extended: false }));

app.use((req, res, next) => {
  res.locals.currentUser = req.user;
  next();
  // this makes req.user available to views as currentuser, look at the route te7t hadi
  // index view uses currentUser even if it wasnt passed to it
});

app.get("/", (req, res) => {
  res.render("index");
});

app.post(
  "/log-in",
  passport.authenticate("local", {
    successRedirect: "/",
    failureRedirect: "/",
  })
);

app.get("/sign-up", (req, res) => {
  res.render("sign-up-form", { msg: null });
});

app.get("/log-out", (req, res) => {
  req.logout();
  res.redirect("/");
});

/**
 *  hashing passwords using bcryptjs library : https://github.com/dcodeIO/bcrypt.js
 *  1 - generate salt and then hash
 *      salt : bcrypt.genSalt( number of rounds to use = 10, callback(err,salt) )
 *      can also work with promises if callback was ommitted, ill stick to promises
 *
 *      hash : bcrypt.hash(string to hash, salt, callback( err, hash ), progressCallback(number))
 *      also returns a promise if the callback is ommitted
 *    - progress callback : called successively with the percentage of rounds completed as argument
 *        idk if i'll ever use it but i dont think so.
 *
 * 2 - or just do it in one function bcrypt.hash('bacon', 8, function(err, hash) {});
 *    the second approach is the way to go
 *    hash( string to hash, salt length or a salt string, callback or use promises)
 *
 *
 */
app.post("/sign-up", async (req, res) => {
  try {
    const hash = await bcrypt.hash(req.body.password, 5);
    const user = new User({
      username: req.body.username,
      password: hash,
    });
    const isAlreadyExisting = await User.findOne({
      username: req.body.username,
    });
    if (!isAlreadyExisting) {
      await user.save();
      res.redirect("/");
    } else {
      res.render("sign-up-form", { msg: "usernamealready exist" });
    }
  } catch (error) {
    console.log(error);
  }
});

app.listen(3000, () => {
  console.log("server listening");
});
