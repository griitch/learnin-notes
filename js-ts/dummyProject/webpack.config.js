//use this as a template for your webpack uses and for documentation

// if a webpack.config.js is present, npx webpack uses it by default, if you
// wanna be verbose use : npx webpack --config webpack.config.js

//config is not even obligatory

//to import a css file witin javascript u need a css loader
// npm install --save-dev style-loader css-loader

//PUT SOURCE FILES IN THE SRC FOLDER WEHTER THEY RE IMAGES OR CSS FILES OR ANYTHING

const path = require("path");
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  //---------------------------------------------------------------------------------------------
  //Section of stuff that are used in developpement and that should be avoided in prod
  mode: 'development', 
  devtool: 'inline-source-map',  /* source map: if we had more than one files bundled in bundle.js
    and one of the source files had an error innit, the stack trace will point to bundle.js which is
    not very helpful as we dont know where the rror is coming from, thats why we use source maps which 
    map compiled code back to original source code and show exactly which file the error comes from
    
    there are many source map options, inline is one of them, the rest is in the docs
  */
  //npm install --save-dev webpack-dev-server
  // devServer: {
  //   contentBase: './dist',
  // }, i won't be using this thing, i'll be using watch mode instead

  //watch mode: if any file within the dependency graph changes, the code will be recompiled
  // to use it add the script "watch": "webpack --watch", in package.json.scripts
  // needs its own terminal, open a terminal and run " npm run watch "

  //---------------------------------------------------------------------------------------------
  entry: {
    
    index : "./src/index.js", 
    printintcrap: './src/printintcrap.js',
  
  } , 
  // the entry is the file which got all the "import" and
  // require to be bundled in the output

  plugins: [ 
    //npm install --save-dev html-webpack-plugin to use html webpack plugin
          new HtmlWebpackPlugin({
            title: 'document title',
          }),
          //what htmlwp plugin does is generate an index.html file with all the bundles included
          ///BE CAREFUL  !! at each compilation it generates a blank html file !!
      ], 
    

  output: {
    //the output is the bundle file that will be included in  
    filename: '[name].bundle.js', // script tag
    path: path.resolve(__dirname, "dist"),
    clean: true,//with this option, the dist folder is being cleand before every build so only used folder
              // will be generated, the old files should be removed
  },
  module: {
    rules: [
      {
        //adding css and style loaders to the module config
        // webpack uses regex to find the types of folder to serve a specific loader
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      }, //style and css loaders allow us to import css files in javascript, and at compilation
      // webpack will put whatever was inside that css in a style tag in the head of the html file

      {
        //for loading images  
        test: /\.(png|svg|jpg|jpeg|gif)$/i,
        type: "asset/resource",
      },
      //For loading fonts, it's the same, add an object to the rules array 
      //the obj got the test and type attributes that ull find in the documentation
      // to use the font, use a @font-face declaration



      //babel-loader for using babel
      // npm install -D babel-loader @babel/core @babel/preset-env webpack

      {
        test: /\.m?js$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: [
              ['@babel/preset-env', { targets: "defaults" }]
            ]
          }
        }
      }
    ],

  },
 
};

