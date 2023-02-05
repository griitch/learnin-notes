var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
var houses = [
    { "name": "Atreides", "planets": "Calladan" },
    { "name": "Corrino", "planets": ["Kaitan", "Salusa Secundus"] },
    { "name": "Harkonnen", "planets": ["Giedi Prime", "Arrakis"] }
];
;
function findHouses(houses, filter) {
    var housesObj = (typeof houses === "string") ?
        JSON.parse(houses) : houses;
    if (filter) {
        housesObj = housesObj.filter(filter);
    }
    return housesObj.map(function (hou, i) { return (__assign(__assign({}, hou), { id: i })); });
}
console.log(findHouses(JSON.stringify(houses), function (_a) {
    var name = _a.name;
    return name === "Atreides";
}));
console.log(findHouses(houses, function (_a) {
    var name = _a.name;
    return name === "Harkonnen";
}));
