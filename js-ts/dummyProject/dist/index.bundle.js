/******/ (() => { // webpackBootstrap
var __webpack_exports__ = {};
/*!**********************!*\
  !*** ./src/index.js ***!
  \**********************/
function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

// import _ from 'lodash'
// import './style.css'
// import koalaImage from './koala.jpg'
// import printcrap from './printintcrap';
function component() {
  var element = document.createElement('div');
  element.innerHTML = _.join(['Hello', 'webpack'], ' ');
  var btn = document.createElement("button");
  btn.innerText = "click and checc console";
  btn.addEventListener("click", printcrap);
  var myimage = new Image();
  myimage.src = koalaImage;
  element.appendChild(myimage);
  element.appendChild(btn);
  return element;
}

document.body.appendChild(component());
var gg = 78;

var human = /*#__PURE__*/function () {
  function human() {
    _classCallCheck(this, human);

    this.gritch = 7;
  }

  _createClass(human, [{
    key: "foo",
    value: function foo() {
      console.log(55);
    }
  }]);

  return human;
}();
/******/ })()
;
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5kZXguYnVuZGxlLmpzIiwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7O0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFFQSxTQUFTQSxTQUFULEdBQXFCO0FBQ2pCLE1BQU1DLE9BQU8sR0FBR0MsUUFBUSxDQUFDQyxhQUFULENBQXVCLEtBQXZCLENBQWhCO0FBRUFGLEVBQUFBLE9BQU8sQ0FBQ0csU0FBUixHQUFvQkMsQ0FBQyxDQUFDQyxJQUFGLENBQU8sQ0FBQyxPQUFELEVBQVUsU0FBVixDQUFQLEVBQTZCLEdBQTdCLENBQXBCO0FBRUEsTUFBTUMsR0FBRyxHQUFHTCxRQUFRLENBQUNDLGFBQVQsQ0FBdUIsUUFBdkIsQ0FBWjtBQUNBSSxFQUFBQSxHQUFHLENBQUNDLFNBQUosR0FBZ0IseUJBQWhCO0FBQ0FELEVBQUFBLEdBQUcsQ0FBQ0UsZ0JBQUosQ0FBcUIsT0FBckIsRUFBOEJDLFNBQTlCO0FBRUEsTUFBTUMsT0FBTyxHQUFHLElBQUlDLEtBQUosRUFBaEI7QUFDQUQsRUFBQUEsT0FBTyxDQUFDRSxHQUFSLEdBQWNDLFVBQWQ7QUFFQWIsRUFBQUEsT0FBTyxDQUFDYyxXQUFSLENBQW9CSixPQUFwQjtBQUNBVixFQUFBQSxPQUFPLENBQUNjLFdBQVIsQ0FBb0JSLEdBQXBCO0FBRUEsU0FBT04sT0FBUDtBQUNEOztBQUVEQyxRQUFRLENBQUNjLElBQVQsQ0FBY0QsV0FBZCxDQUEwQmYsU0FBUyxFQUFuQztBQUVGLElBQUlpQixFQUFFLEdBQUcsRUFBVDs7SUFDTUMsSztBQUNKLG1CQUFhO0FBQUE7O0FBQ1gsU0FBS0MsTUFBTCxHQUFjLENBQWQ7QUFDRDs7OztXQUVELGVBQWE7QUFDWEMsTUFBQUEsT0FBTyxDQUFDQyxHQUFSLENBQVksRUFBWjtBQUNEIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vZHVtbXktcHJvamVjdC8uL3NyYy9pbmRleC5qcyJdLCJzb3VyY2VzQ29udGVudCI6WyIvLyBpbXBvcnQgXyBmcm9tICdsb2Rhc2gnXHJcbi8vIGltcG9ydCAnLi9zdHlsZS5jc3MnXHJcbi8vIGltcG9ydCBrb2FsYUltYWdlIGZyb20gJy4va29hbGEuanBnJ1xyXG4vLyBpbXBvcnQgcHJpbnRjcmFwIGZyb20gJy4vcHJpbnRpbnRjcmFwJztcclxuXHJcbmZ1bmN0aW9uIGNvbXBvbmVudCgpIHtcclxuICAgIGNvbnN0IGVsZW1lbnQgPSBkb2N1bWVudC5jcmVhdGVFbGVtZW50KCdkaXYnKTtcclxuICBcclxuICAgIGVsZW1lbnQuaW5uZXJIVE1MID0gXy5qb2luKFsnSGVsbG8nLCAnd2VicGFjayddLCAnICcpO1xyXG5cclxuICAgIGNvbnN0IGJ0biA9IGRvY3VtZW50LmNyZWF0ZUVsZW1lbnQoXCJidXR0b25cIik7XHJcbiAgICBidG4uaW5uZXJUZXh0ID0gXCJjbGljayBhbmQgY2hlY2MgY29uc29sZVwiO1xyXG4gICAgYnRuLmFkZEV2ZW50TGlzdGVuZXIoXCJjbGlja1wiLCBwcmludGNyYXApO1xyXG5cclxuICAgIGNvbnN0IG15aW1hZ2UgPSBuZXcgSW1hZ2UoKTtcclxuICAgIG15aW1hZ2Uuc3JjID0ga29hbGFJbWFnZTtcclxuXHJcbiAgICBlbGVtZW50LmFwcGVuZENoaWxkKG15aW1hZ2UpO1xyXG4gICAgZWxlbWVudC5hcHBlbmRDaGlsZChidG4pOyBcclxuICBcclxuICAgIHJldHVybiBlbGVtZW50O1xyXG4gIH1cclxuICBcclxuICBkb2N1bWVudC5ib2R5LmFwcGVuZENoaWxkKGNvbXBvbmVudCgpKTtcclxuXHJcbmxldCBnZyA9IDc4O1xyXG5jbGFzcyBodW1hbiB7XHJcbiAgY29uc3RydWN0b3IoKXtcclxuICAgIHRoaXMuZ3JpdGNoID0gNztcclxuICB9XHJcblxyXG4gIGZvbyguLi5hcmdzKSB7XHJcbiAgICBjb25zb2xlLmxvZyg1NSlcclxuICB9XHJcblxyXG5cclxufSJdLCJuYW1lcyI6WyJjb21wb25lbnQiLCJlbGVtZW50IiwiZG9jdW1lbnQiLCJjcmVhdGVFbGVtZW50IiwiaW5uZXJIVE1MIiwiXyIsImpvaW4iLCJidG4iLCJpbm5lclRleHQiLCJhZGRFdmVudExpc3RlbmVyIiwicHJpbnRjcmFwIiwibXlpbWFnZSIsIkltYWdlIiwic3JjIiwia29hbGFJbWFnZSIsImFwcGVuZENoaWxkIiwiYm9keSIsImdnIiwiaHVtYW4iLCJncml0Y2giLCJjb25zb2xlIiwibG9nIl0sInNvdXJjZVJvb3QiOiIifQ==