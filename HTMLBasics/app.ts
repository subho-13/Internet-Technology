class Username {
    userelem: HTMLElement;

    constructor(elem: HTMLElement) {
        this.userelem = elem;
    }

    setElem (name:string) {
        this.userelem.innerHTML = name;
    }
}

let username = new Username(document.querySelector("#username"));

username.setElem("Hi");
console.log(username.userelem.innerText)