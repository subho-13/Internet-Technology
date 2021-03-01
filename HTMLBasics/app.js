var Username = /** @class */ (function () {
    function Username(elem) {
        this.userelem = elem;
    }
    Username.prototype.setElem = function (name) {
        this.userelem.innerHTML = name;
    };
    return Username;
}());
var username = new Username(document.querySelector("#username"));
username.setElem("Hi");
console.log(username.userelem.innerText);
